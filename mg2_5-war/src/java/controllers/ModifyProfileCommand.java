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
import javax.servlet.http.HttpSession;

public class ModifyProfileCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            Users user = new Users(Integer.parseInt(
                    request.getParameter("id")),
                    request.getParameter("name"),
                    request.getParameter("email"),
                    request.getParameter("password"));
            usersFacade.edit(user);
            
            Users users = usersFacade.find(Integer.parseInt(request.getParameter("id")));
            List<Users> userList = new ArrayList<Users>();
            userList.add(users);
            session.setAttribute("loggedUser", users);
            request.setAttribute("userInfo", userList);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ModifyProfileCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}