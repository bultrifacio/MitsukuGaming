package controllers;

import controller.ReviewFacade;
import entities.Review;
import entities.Users;
import java.io.IOException;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class WriteReviewCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");
            Date date = new Date();
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            String aux = request.getParameter("productId");
            Review review = new Review(
                    new Random().nextInt(1000000), 
                    Integer.parseInt(request.getParameter("productId")), 
                    request.getParameter("text"),
                    Integer.parseInt(request.getParameter("score")),
                    loggedUser.getUserId(),
                    date);
            //request.setAttribute(/*"id"*/"productId", request.getAttribute("productId"));
            //request.setAttribute("category", request.getAttribute("category"));
            reviewFacade.create(review);
            //request.setAttribute("price", request.getAttribute("price"));
            forward("/FrontController?id=" + request.getParameter("productId") + "&command=ShowProductDetailsCommand");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(WriteReviewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}