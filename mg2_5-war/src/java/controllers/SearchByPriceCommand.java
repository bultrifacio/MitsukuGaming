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

public class SearchByPriceCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            List<Product> productList = productFacade.findAll();
            String minimum = request.getParameter("minimum");
            String maximum = request.getParameter("maximum");
            float rate = (float) session.getAttribute("rate");
            List<Product> matchesList = new ArrayList<>();
            for (Product product : productList) {
                if (product.getPrice() * rate >= Float.parseFloat(minimum)) {
                    if (product.getPrice() * rate <= Float.parseFloat(maximum)) {
                        product.setPrice(product.getPrice() * rate);
                        matchesList.add(product);
                    }
                }
            }
            request.setAttribute("productList", matchesList);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(SearchByPriceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}