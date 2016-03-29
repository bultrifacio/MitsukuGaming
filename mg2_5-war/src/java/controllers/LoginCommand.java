package controllers;

import controller.UsersFacade;
import entities.Users;
import java.io.IOException;
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
            // Modificar ID que se busca
            Users loggedUser = usersFacade.find(1);
            if (loggedUser.getPassword().equals(request.getParameter("password"))) {
                session.setAttribute("loggedUser", loggedUser);
            }
            String aux = (String) session.getAttribute("actualPage");
            
            request.setAttribute("id",(String) request.getParameter("id"));
            
            forward((String) session.getAttribute("actualPage"));
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}