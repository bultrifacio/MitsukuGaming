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

public class CopyUsersDetailsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade userFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            Users product = userFacade.find(Integer.parseInt(request.getParameter("id")));
            List<Users> usersList = new ArrayList<Users>();
            usersList.add(product);
            
            request.setAttribute("usersList", usersList);
            forward("/modifyUser.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(CopyUsersDetailsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
