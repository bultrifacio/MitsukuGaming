package controllers;

import controller.UsersFacade;
import entities.Users;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;

public class ModifyUsersCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            Users user = new Users(Integer.parseInt(request.getParameter("id")),request.getParameter("name"),request.getParameter("email"),request.getParameter("password"));
            usersFacade.edit(user);
        } catch (NamingException ex) {
            Logger.getLogger(ModifyUsersCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}