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
import javax.servlet.http.HttpSession;

public class LoginCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> userList = usersFacade.findAll();
            boolean wrongPassword = false;
            session.setAttribute("wrongEmail", false);
            for (Users user : userList) {
                if (user.getEmail().equals(request.getParameter("email"))) {
                    if (user.getPassword().equals(request.getParameter("password"))) {
                        session.setAttribute("loggedUser", user);
                        wrongPassword = false;
                        session.setAttribute("wrongPassword", false);
                        break;
                    } else {
                        session.setAttribute("wrongPassword", true);
                        wrongPassword = true;
                        break;
                    }
                }
            }
            if (wrongPassword){
                session.setAttribute("wrongEmail", true);
            }
            forward("/FrontController?command=GetInitialDataCommand");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}