/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import controller.SalesFacade;
import entities.Product;
import java.io.IOException;
import java.util.ArrayList;
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
            
            float rate = (float) 0.0;
            if (request.getParameter("currency").equals("Euro")) {
                rate = (float) 1.11970;
                session.setAttribute("currency", "Dollar");
            } else {
                if (request.getParameter("currency").equals("Dollar")) {
                    rate = (float) 1.0;
                    session.setAttribute("currency", "Euro");
                }
            }
            
            List<Product> productList = productFacade.findAll();
            List<Product> changedCurrencyList = new ArrayList<>();
            
            for (Product product : productList) {
                product.setPrice(rate * product.getPrice());
                changedCurrencyList.add(product);
            }
            request.setAttribute("productList", changedCurrencyList);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ChangeCurrencyCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
