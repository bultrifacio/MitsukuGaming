package controllers;

import controller.UsersFacade;
import entities.PasswordEncryption;
import entities.Users;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class RegisterCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade users = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            
            users.create(new Users(1, request.getParameter("name"), request.getParameter("email"), PasswordEncryption.encrypt(request.getParameter("password"))));
            forward("/FrontController?command=GetInitialDataCommand");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(RegisterCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}