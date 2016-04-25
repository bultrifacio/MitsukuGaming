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

public class SearchByCategoryCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");
            
            List<Product> productList = productFacade.findAll();
            List<Review> reviewList = reviewFacade.findAll();
            
            String category = request.getParameter("category");
            float rate = (float) session.getAttribute("rate");
            List<Product> matchesList = new ArrayList<>();
            for (Product product : productList) {
                if (product.getCategory().equals(category)) {
                    product.setPrice(product.getPrice() * rate);
                    matchesList.add(product);
                }
            }
            request.setAttribute("productList", matchesList);
            request.setAttribute("reviewList", reviewList);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(SearchByCategoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}