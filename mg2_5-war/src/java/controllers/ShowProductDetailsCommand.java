package controllers;

import controller.ImageFacade;
import controller.ProductFacade;
import controller.ReviewFacade;
import controller.ReviewScoreFacade;
import controller.UsersFacade;
import controller.VideoFacade;
import entities.Image;
import entities.Product;
import entities.Review;
import entities.ReviewScore;
import entities.Users;
import entities.Video;
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

public class ShowProductDetailsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            Product product = productFacade.find(Integer.parseInt(request.getParameter("productId")));
            String currency = (String) session.getAttribute("currency");
            if (!currency.equals("Euro")) {
                if (currency.equals("Dollar")) {
                    product.setPrice((float) 1.11970 * product.getPrice());
                }
            }
            List<Product> selectedProduct = new ArrayList<>();
            selectedProduct.add(product);
            List<Product> productCategory = productFacade.findAll();
            List<Product> productList = new ArrayList<>();
            for (Product product2 : productCategory) {
                if (product2.getCategory().equals(request.getParameter("category")) && (product2.getProductId() != Integer.parseInt(request.getParameter("productId")))) {
                    productList.add(product2);
                }
            }
            request.setAttribute("category", request.getAttribute("category"));
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");
            List<Review> reviewList = reviewFacade.findAll();
            List<Review> productReviews = new ArrayList<>();
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> reviewOwners = new ArrayList<>();

            ReviewScoreFacade reviewScoreFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewScoreFacade");
            List<ReviewScore> reviewScoreList = reviewScoreFacade.findAll();

            int score = 0;
            HashMap<Integer, Integer> scores = new HashMap<>();
            HashMap<Integer, Boolean> allowedToRate = new HashMap<>();
            for (Review review : reviewList) {
                if (review.getProductId() == Integer.parseInt(request.getParameter("productId"))) {
                    productReviews.add(review);
                    allowedToRate.put(review.getReviewId(), true);
                    reviewOwners.add(usersFacade.find(review.getUserId()));
                    for (ReviewScore reviewScore : reviewScoreList) {
                        if (review.getReviewId() == reviewScore.getReviewId()) {
                            score += reviewScore.getScore();
                            Users loggedUser = (Users) session.getAttribute("loggedUser");
                            if (loggedUser != null) {
                                if (loggedUser.getUserId() == reviewScore.getUserId()) {
                                    allowedToRate.put(review.getReviewId(), false);
                                } else {
                                    allowedToRate.put(review.getReviewId(), true);
                                }
                            }
                        }
                    }
                    scores.put(review.getReviewId(), score);
                    score = 0;
                }
            }
            ImageFacade imageFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ImageFacade");
            List<Image> imageList = imageFacade.findAll();
            List<Image> imageFilter = new ArrayList<>();
            for (Image image : imageList) {
                if (image.getProductId() == product.getProductId()) {
                    imageFilter.add(image);
                }
            }
            VideoFacade videoFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/VideoFacade");
            List<Video> videoList = videoFacade.findAll();
            List<Video> videoFilter = new ArrayList<>();
            for (Video video : videoList) {
                if (video.getProductId() == product.getProductId()) {
                    videoFilter.add(video);
                }
            }

            request.setAttribute("name", product.getName());
            request.setAttribute("available", product.getAvailable());
            request.setAttribute("imageFilter", imageFilter);
            request.setAttribute("videoFilter", videoFilter);
            request.setAttribute("selectedProduct", selectedProduct);
            request.setAttribute("productList", productList);
            request.setAttribute("productReviews", productReviews);
            request.setAttribute("reviewOwners", reviewOwners);
            request.setAttribute("scores", scores);
            request.setAttribute("allowedToRate", allowedToRate);
            request.setAttribute("productId", request.getAttribute("productId"));
            request.setAttribute("category", request.getAttribute("category"));
            request.setAttribute("price", request.getAttribute("price"));
            forward("/productDetails.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ShowProductDetailsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
