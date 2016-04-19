package controllers;

import controller.UsersFacade;
import entities.Users;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ShowUsersCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> usersList = usersFacade.findAll();
            request.setAttribute("usersList", usersList);
            forward("/manageUsers.jsp");
        } catch (NamingException | IOException | ServletException ex) {
            Logger.getLogger(ShowUsersCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
