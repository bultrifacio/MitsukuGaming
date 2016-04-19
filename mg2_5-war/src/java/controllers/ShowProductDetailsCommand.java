package controllers;

import controller.ImageFacade;
import controller.ProductFacade;
import controller.ReviewFacade;
import controller.UsersFacade;
import entities.Image;
import entities.Product;
import entities.Review;
import entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class ShowProductDetailsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            Product product = productFacade.find(Integer.parseInt(request.getParameter("id")));
            String currency = (String) session.getAttribute("currency");
            if (!currency.equals("Euro")) {
                if (currency.equals("Dollar")) {
                    product.setPrice((float) 1.11970 * product.getPrice());
                }
            }
            List<Product> selectedProduct = new ArrayList<>();
            selectedProduct.add(product);
            List<Product> Productcategory = productFacade.findAll();
            List<Product> productList = new ArrayList<>();
            for (Product product2 : Productcategory) {
                if (product2.getCategory().equals(request.getParameter("category")) && (product2.getProductId() != Integer.parseInt(request.getParameter("id")))) {
                    productList.add(product2);
                }
            }
            request.setAttribute("category", request.getAttribute("category"));
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");
            List<Review> reviewList = reviewFacade.findAll();
            List<Review> productReviews = new ArrayList<>();
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> reviewOwners = new ArrayList<>();
            for (Review review : reviewList) {
                if (review.getProductId() == Integer.parseInt(request.getParameter("id"))) {
                    productReviews.add(review);
                    reviewOwners.add(usersFacade.find(review.getUserId()));
                }
            }
            ImageFacade imageFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ImageFacade");
            List<Image> imageList = imageFacade.findAll();
            List<Image> imageFilter = new ArrayList<>();
            for (Image image : imageList) {
                if (image.getProductId() == product.getProductId()){
                    imageFilter.add(image);
                }
            }
            request.setAttribute("name", product.getName());
            request.setAttribute("available", product.getAvailable());
            request.setAttribute("imageFilter", imageFilter);
            request.setAttribute("selectedProduct", selectedProduct);
            request.setAttribute("productList", productList);
            request.setAttribute("productReviews", productReviews);
            request.setAttribute("reviewOwners", reviewOwners);
            forward("/productDetails.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ShowProductDetailsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
