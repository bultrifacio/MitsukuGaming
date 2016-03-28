package controllers;

import controller.ProductFacade;
import controller.WishlistFacade;
import entities.Product;
import entities.Users;
import entities.Wishlist;
import java.io.IOException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class AddToWishListCommand extends FrontCommand {

    @Override
    public void process() {
        try {

            HttpSession session = request.getSession(true);
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            WishlistFacade wishList = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/WishlistFacade");
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            Product product = productFacade.find(Integer.parseInt(request.getParameter("id")));
            wishList.create(new Wishlist(new Random().nextInt(10000000), loggedUser.getUserId(), product.getProductId()));

            forward("/FrontController?command=GetInitialDataCommand");

        } catch (NamingException ex) {
            Logger.getLogger(AddToWishListCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AddToWishListCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddToWishListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
