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
import javax.servlet.http.HttpSession;

public class ChangeCurrencyCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");

            // Alterna entre Euro y Dolar
            if (request.getParameter("currency").equals("Euro")) {
                session.setAttribute("currency", "Dollar");
                session.setAttribute("rate", (float) 1.11970);
            } else {
                if (request.getParameter("currency").equals("Dollar")) {
                    session.setAttribute("currency", "Euro");
                    session.setAttribute("rate", (float) 1.0);
                }
            }

            List<Product> productList = productFacade.findAll();

            float rate = (float) session.getAttribute("rate");
            for (Product product : productList) {
                product.setPrice(rate * product.getPrice());
            }

            forward("/FrontController?command=GetInitialDataCommand");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ChangeCurrencyCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
