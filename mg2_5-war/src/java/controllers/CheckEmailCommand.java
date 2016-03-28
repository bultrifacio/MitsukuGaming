package controllers;

import controller.UsersFacade;
import entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class CheckEmailCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> usersList = usersFacade.findAll();
            for (Users users : usersList) {
                if (users.getEmail().equals(request.getParameter("email"))) {
                    PrintWriter writer = new PrintWriter("C:\\Users\\ENTRAR\\Documents\\Recover_Password.txt", "UTF-8");
                    writer.println("Your can reset your password in the following link: ");
                    writer.println("http://localhost:8080/mg2_5-war/newPassword.jsp");
                    writer.close();
                    session.setAttribute("validEmail", 1);
                    session.setAttribute("userEmail", users.getEmail());

                    forward("/resetPassword.jsp");
                }
            }
            session.setAttribute("validEmail", 0);
            forward("/resetPassword.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(CheckEmailCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
