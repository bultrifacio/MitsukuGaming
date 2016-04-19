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

public class ShowProfileCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            Users user = usersFacade.find(1);
            List<Users> userList = new ArrayList<Users>();
            userList.add(user);
            request.setAttribute("userInfo", userList);
            forward("/modifyProfile.jsp");
        } catch (NamingException | IOException | ServletException ex) {
            Logger.getLogger(ShowProfileCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
