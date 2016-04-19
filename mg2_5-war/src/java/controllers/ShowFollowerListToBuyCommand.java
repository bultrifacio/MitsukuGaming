package controllers;

import controller.FollowerListFacade;
import controller.UsersFacade;
import entities.FollowerList;
import entities.NameAndFollowID;
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

public class ShowFollowerListToBuyCommand extends FrontCommand {

    @Override
    public void process() {
        HttpSession session = request.getSession(true);
        try {
            FollowerListFacade followerListFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/FollowerListFacade");
            UsersFacade users = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            List<FollowerList> followersList = followerListFacade.findAll();
            List<Users> usersList = users.findAll();
            List<NameAndFollowID> namesAndFollowsIDs = new ArrayList<>();
            for (FollowerList follower : followersList) {
                if (follower.getUserId() == loggedUser.getUserId()) {
                    for (Users user : usersList) {
                        if (user.getUserId() == follower.getFollower()) {
                            namesAndFollowsIDs.add(new NameAndFollowID(user.getName(), user.getUserId()));
                            break;
                        }
                    }
                }
            }
            request.setAttribute("namesAndFollowsIDs", namesAndFollowsIDs);
            String aux = (String) request.getParameter("payment");
            request.setAttribute("payment", aux);
            forward("/buyForFriend.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ShowFollowerListToBuyCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
