package controllers;

import controller.FollowerListFacade;
import controller.UsersFacade;
import entities.Users;
import entities.FollowerList;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class ShowMyFollowingListCommand extends FrontCommand {

    @Override
    public void process() {
        try {

            HttpSession session = request.getSession(true);
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            FollowerListFacade FollowerListFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/FollowerListFacade");
            List<Users> usersList =new ArrayList<>();;
            List<FollowerList> followlist = FollowerListFacade.findAll();
            for (FollowerList followlistuser : followlist) {
                if (followlistuser.getUserId() == loggedUser.getUserId()){ 
                    usersList.add(usersFacade.find(followlistuser.getFollower()));
                }
            }
            request.setAttribute("usersList", usersList);
            forward("/followList.jsp");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(ShowMyFollowingListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
