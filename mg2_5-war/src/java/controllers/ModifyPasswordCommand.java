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

public class ModifyPasswordCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> usersList = usersFacade.findAll();
            for (Users users : usersList) {
                if(users.getEmail().equals(session.getAttribute("userEmail"))){
                    Users user = new Users(users.getUserId(),
                            users.getName(),
                            users.getEmail(),
                            request.getParameter("pass1"));   
                    usersFacade.edit(user);
                    break;
                }
                
            }
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ModifyPasswordCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}