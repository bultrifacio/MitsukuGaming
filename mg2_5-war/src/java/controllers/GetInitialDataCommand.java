/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import entities.Product;
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
public class GetInitialDataCommand extends FrontCommand {

    @Override
    public void process() {
        ProductFacade productFacade;
        try {
            productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            List<Product> productList = productFacade.findAll();
            request.setAttribute("productList", productList);
            forward("/index.html");
        } catch (NamingException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
