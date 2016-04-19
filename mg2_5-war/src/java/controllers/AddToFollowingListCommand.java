/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.FollowerListFacade;
import controller.UsersFacade;
import entities.Users;
import entities.FollowerList;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class AddToFollowingListCommand extends FrontCommand{

    @Override
    public void process() {
        try {

            HttpSession session = request.getSession(true);
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            String nametofollow = request.getParameter("nametofollow");
            FollowerListFacade followerListFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/FollowerListFacade");
            UsersFacade users = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> userlist = users.findAll();
            
            for (Users listuser : userlist) {
                if (listuser.getName().toLowerCase().equals(nametofollow.toLowerCase())){ 
                    FollowerList list=new FollowerList();
                    list.setUserId(loggedUser.getUserId());
                    list.setFollower(listuser.getUserId());
                    followerListFacade.create(list);
                    break;
                    
                }
                
            }
            forward("/FrontController?command=GetInitialDataCommand");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(AddToFollowingListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
