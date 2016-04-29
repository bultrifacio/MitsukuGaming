package controllers;

import controller.ReviewFacade;
import controller.ReviewScoreFacade;
import entities.Review;
import entities.ReviewScore;
import entities.Users;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class RateReviewCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");
            ReviewScoreFacade reviewScoreFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewScoreFacade");
            /*List<Review> reviewList = reviewFacade.findAll();
            Review review = new Review();
            for (Review actualReview : reviewList) {
                if (actualReview.getReviewId() == Integer.parseInt(request.getParameter("reviewId"))) {
                    review = actualReview;
                    break;
                }
            }*/
            Review review = reviewFacade.find(Integer.parseInt(request.getParameter("reviewId")));
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            int score = Integer.parseInt(request.getParameter("score"));
            ReviewScore reviewScore = new ReviewScore(0, review.getReviewId(), loggedUser.getUserId(), score);
            reviewScoreFacade.create(reviewScore);
            
            request.setAttribute("productId", request.getAttribute("productId"));
            request.setAttribute("category", request.getAttribute("category"));
            request.setAttribute("price", request.getAttribute("price"));
            forward("/FrontController?id=" + request.getParameter("productId") + "&command=ShowProductDetailsCommand");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(RateReviewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}