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

public class UsersCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            //UsersFacade users = InitialContext.doLookup("java:module/UsersFacade");
            UsersFacade users = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            users.create(new Users(new Random().nextInt(1000000), request.getParameter("name"), request.getParameter("email"), request.getParameter("password")));
        } catch (NamingException ex) {
            Logger.getLogger(UsersCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}