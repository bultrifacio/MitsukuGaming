package controllers;

import controller.ProductFacade;
import controller.ReviewFacade;
import entities.Product;
import entities.Review;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SortByPriceCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");

            List<Product> productList = productFacade.findAll();
            List<Review> reviewList = reviewFacade.findAll();

            ArrayList<Product> sortedList = new ArrayList<>();

            boolean comparableVector[] = new boolean[productList.size()];
            for (int i = 0; i < comparableVector.length; i++) {
                comparableVector[i] = false;
            }

            int index = -1;
            int indexProductLowerPrice = 0;
            float isGreater = 100;
            Product productLowerPrice = new Product();

            for (Product product : productList) {
                productLowerPrice = product;
                for (Product elementToCompare : productList) {
                    index++;
                    if (elementToCompare.getPrice() < isGreater) {
                        if (comparableVector[index] == false) {
                            isGreater = elementToCompare.getPrice();
                            indexProductLowerPrice = index;
                            productLowerPrice = elementToCompare;
                        }
                    }
                }
                comparableVector[indexProductLowerPrice] = true;
                isGreater = 100;
                index = -1;
                productLowerPrice.setPrice(productLowerPrice.getPrice() * (float) session.getAttribute("rate"));
                sortedList.add(productLowerPrice);
            }

            request.setAttribute("productList", sortedList);
            request.setAttribute("reviewList", reviewList);
            HashMap<String, Integer> starsMap = GetInitialDataCommand.countStars(sortedList);
            session.setAttribute("stars", starsMap);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(SortByPriceCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
