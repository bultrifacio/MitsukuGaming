/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controllers;

import controller.FollowerListFacade;
import entities.FollowerList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ShowFollowerListToBuyCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            FollowerListFacade followerFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/FollowerListFacade");
        } catch (NamingException ex) {
            Logger.getLogger(ShowFollowerListToBuyCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
