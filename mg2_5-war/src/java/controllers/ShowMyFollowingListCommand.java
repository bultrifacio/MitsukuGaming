package controllers;

import controller.FollowerListFacade;
import controller.SalesFacade;
import controller.UsersFacade;
import entities.Users;
import entities.FollowerList;
import entities.Sales;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class ShowMyFollowingListCommand extends FrontCommand {

    @Override
    public void process() {
        try {

            HttpSession session = request.getSession(true);
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            FollowerListFacade FollowerListFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/FollowerListFacade");
            List<Users> usersList = new ArrayList<>();;
            List<FollowerList> followlist = FollowerListFacade.findAll();
            for (FollowerList followlistuser : followlist) {
                if (followlistuser.getUserId() == loggedUser.getUserId()) {
                    usersList.add(usersFacade.find(followlistuser.getFollower()));
                }
            }

            getMyRecomendedUsers(loggedUser);

            request.setAttribute("usersList", usersList);
            forward("/followList.jsp");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(ShowMyFollowingListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void getMyRecomendedUsers(Users loggedUser) throws NamingException {
        SalesFacade saleFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/SalesFacade");
        UsersFacade UsersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
        List<Sales> saleList = saleFacade.findAll();
        List<Sales> mySaleList = new ArrayList<>();
        List<Users> recomendedUsersList = new ArrayList<>();

        for (Sales sale : saleList) {
            if (sale.getUserId() == loggedUser.getUserId()) {
                mySaleList.add(sale);
            }
        }

        for (Sales sale : saleList) {
            for (Sales mySale : mySaleList) {
                if ((sale.getProductId() == mySale.getProductId()) && (sale.getUserId() != loggedUser.getUserId())) {
                    if (!userInMyList(recomendedUsersList, sale.getUserId())) {
                        recomendedUsersList.add(UsersFacade.find(sale.getUserId()));
                    }
                }
            }
        }
        request.setAttribute("recomendedUsersList", recomendedUsersList);
    }

    private boolean userInMyList(List<Users> recomendedUsersList, int userId) {
        for (Users user : recomendedUsersList) {
            if (user.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }
}
