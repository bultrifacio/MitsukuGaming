package controllers;

import controller.ReportFacade;
import controller.ReviewFacade;
import entities.Report;
import entities.Review;
import entities.Users;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class ReportReviewCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ReviewFacade reviewFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReviewFacade");
            ReportFacade reportFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ReportFacade");
            Review review = reviewFacade.find(Integer.parseInt(request.getParameter("reviewId")));
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            Report report = new Report(0, review.getReviewId(), loggedUser.getUserId());
            report.setReason(request.getParameter("reason"));
            reportFacade.create(report);
            
            request.setAttribute("productId", request.getAttribute("productId"));
            request.setAttribute("id", request.getAttribute("productId"));
            request.setAttribute("category", request.getAttribute("category"));
            request.setAttribute("price", request.getAttribute("price"));
            forward("/FrontController?id=" + request.getParameter("productId") + "&command=ShowProductDetailsCommand");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(ReportReviewCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}