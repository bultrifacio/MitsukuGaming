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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class GetInitialDataCommand extends FrontCommand {

    @Override
    public void process() {
        ProductFacade productFacade;
        try {
            HttpSession session = request.getSession(true);

            // Cada vez que se vuelve a la pagina principal, se reinicia la moneda actual a Euro
            // Hay que cambiar esto de sitio
            if (session.getAttribute("currency") == null) {
                session.setAttribute("currency", "Euro");
            }

            session.setAttribute("actualPage", "FrontController");
            productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");

            List<Product> productList = productFacade.findAll();

            String currency = (String) session.getAttribute("currency");
            if (!currency.equals("Euro")) {
                for (Product product : productList) {
                    if (currency.equals("Dollar")) {
                        product.setPrice((float) 1.11970 * product.getPrice());
                    }
                }
            }
            request.setAttribute("productList", productList);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
