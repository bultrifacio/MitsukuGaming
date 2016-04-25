package controllers;

import controller.ProductFacade;
import controller.ReviewFacade;
import entities.Product;
import entities.Review;
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
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");
            
            List<Product> productList = productFacade.findAll();
            List<Review> reviewList = reviewFacade.findAll();
            Float minimum;
            Float maximum;
            if (!request.getParameter("minimum").equals("")) {
                minimum = Float.parseFloat(request.getParameter("minimum"));
            } else {
                minimum = (float) 0;
            }
            if (!request.getParameter("maximum").equals("")) {
                maximum = Float.parseFloat(request.getParameter("maximum"));
            } else {
                maximum = Float.MAX_VALUE;
            }
            if (minimum < maximum) {
                float rate = (float) session.getAttribute("rate");
                List<Product> matchesList = new ArrayList<>();
                for (Product product : productList) {
                    if (product.getPrice() * rate >= minimum) {
                        if (product.getPrice() * rate <= maximum) {
                            product.setPrice(product.getPrice() * rate);
                            matchesList.add(product);
                        }
                    }
                }
                int pages = 0;
                if ((productList.size() % 6) != 0) {
                    pages = (productList.size() / 6) + 1;
                } else {
                    pages = (productList.size() / 6);
                }
                session.setAttribute("pages", pages);
                request.setAttribute("productList", matchesList);
                request.setAttribute("reviewList", reviewList);
                session.setAttribute("wrongRange", 0);
            } else {
                session.setAttribute("pages", 1);
                request.setAttribute("productList", new ArrayList<Product>());
                request.setAttribute("reviewList", new ArrayList<Review>());
                session.setAttribute("wrongRange", 1);
            }
            
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(SearchByPriceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}