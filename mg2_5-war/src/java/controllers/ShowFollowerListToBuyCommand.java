/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.FollowerListFacade;
import controller.UsersFacade;
import entities.FollowerList;
import entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
            List<FollowerList> followersListFiltre = new ArrayList<>();
            List<NameAndFollowID> namesAndFollowsIDs = new ArrayList<>();

            for (FollowerList follower : followersList) {
                if (follower.getUserId() == loggedUser.getUserId()) {
                    for (Users user : usersList) {
                        if (user.getUserId() == follower.getUserId()) {
                            namesAndFollowsIDs.add(new NameAndFollowID(user.getName(), user.getUserId()));
                            break;
                        }
                    }
                }
            }

            request.setAttribute("namesAndFollowsIDs", namesAndFollowsIDs);
            forward("/buyForFriend.jsp");

        } catch (NamingException ex) {
            Logger.getLogger(ShowFollowerListToBuyCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ShowFollowerListToBuyCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShowFollowerListToBuyCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    private class NameAndFollowID {

        public String name;
        public int id;
        
        public void setName(String name) {
            this.name = name;
        }

        public void setId(int id) {
            this.id = id;
        }

        public NameAndFollowID(String name, int id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public int getId() {
            return id;
        }
    }

}
