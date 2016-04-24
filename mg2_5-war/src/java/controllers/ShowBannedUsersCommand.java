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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Hector
 */
public class ShowBannedUsersCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            //String nametoban = request.getParameter("nametoban");
            List<Users> usersList = usersFacade.findAll();
            List<Users> bannedUsersList = new ArrayList();
            
            for (Users listuser : usersList) {
                if(listuser.getState()==0){
                    bannedUsersList.add(listuser);
                }
            }
            //System.out.println(bannedUsersList.get(0).getName());
            request.setAttribute("bannedUsersList", bannedUsersList);
            forward("/bannedUsers.jsp");
            
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(ShowBannedUsersCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
