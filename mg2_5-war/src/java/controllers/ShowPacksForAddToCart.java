/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.FollowerListFacade;
import controller.PackContentFacade;
import controller.PackDetailsFacade;
import entities.PackContent;
import entities.PackDetails;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/**
 *
 * @author Jusio
 */
public class ShowPacksForAddToCart extends FrontCommand {

    @Override
    public void process() {
        try {
            PackDetailsFacade packDetailsFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackDetailsFacade");
            List<PackDetails> packDetailsList = packDetailsFacade.findAll();

            request.setAttribute("packDetailsList", packDetailsList);
            forward("/showPacksToBuy.jsp");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(ShowPacksForAddToCart.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
