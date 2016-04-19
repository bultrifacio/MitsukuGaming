/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
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
                session.setAttribute("rate", (float) 1.0);
            }

            session.setAttribute("actualPage", "FrontController");
            productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");

            List<Product> productList = productFacade.findAll();
            if (session.getAttribute("indexPagination") == null) {
                session.setAttribute("indexPagination", 1);
            } else {
                session.setAttribute("indexPagination", Integer.parseInt(request.getParameter("index")));
            }

            int indexPagination = (Integer) session.getAttribute("indexPagination");
            int pages = 0;
            if ((productList.size() % 6) != 0) {
                pages = (productList.size() / 6) + 1;
            } else {
                pages = (productList.size() / 6);
            }
            
            List<Product> paginatedList = productFacade.findRange(new int[]{indexPagination * 6 - 5, indexPagination * 6});
            List<Product> convertedList = new ArrayList<>();
            for (Product product : paginatedList) {
                product.setPrice((float) session.getAttribute("rate") * product.getPrice());
                convertedList.add(product);
            }
            
            session.setAttribute("pages", pages);
            //request.setAttribute("productList", productList);
            request.setAttribute("productList", convertedList);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
