package controllers;

import controller.UsersFacade;
import entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class UnbanCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            Users oldUser = usersFacade.find(Integer.parseInt(request.getParameter("id")));
            Users user = new Users(oldUser.getUserId(),oldUser.getName(),oldUser.getEmail(),oldUser.getPassword(),1);
            usersFacade.edit(user);
            forward("/bannedUsers.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(UnbanCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
