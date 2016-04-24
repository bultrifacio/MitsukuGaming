/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

public class BanToCommand extends FrontCommand{

    @Override
    public void process() {
        try {

            String nametoban = request.getParameter("nametoban");
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> userlist = usersFacade.findAll();
            
            //(Integer userId, String name, String email, String password, int state)
            for (Users listuser : userlist) {
                if (nametoban.equalsIgnoreCase(listuser.getName())){ 
                    Users user = new Users(listuser.getUserId(),listuser.getName(),listuser.getEmail(),listuser.getPassword(),0);
                    usersFacade.edit(user);
                }
                
            }
            
            forward("/FrontController?command=GetInitialDataCommand");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(BanToCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
