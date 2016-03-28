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

public class ChangeCurrencyCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            List<Product> productList = productFacade.findAll();
            List<Product> changedCurrencyList = new ArrayList<>();
            float rate = (float) 1.2;
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
