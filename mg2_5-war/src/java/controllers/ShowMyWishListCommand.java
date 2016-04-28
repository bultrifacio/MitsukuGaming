/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import controller.WishlistFacade;
import entities.Product;
import entities.Users;
import entities.Wishlist;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Jusio
 */
public class ShowMyWishListCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            WishlistFacade wishListFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/WishlistFacade");
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");

            List<Wishlist> wishlist = wishListFacade.findAll();
            List<Product> products = productFacade.findAll();
            List<Product> productListWished = new ArrayList<>();
            
            for (Wishlist wish : wishlist) {
                for (Product product : products) {
                    if((wish.getProductId()==product.getProductId())&&(wish.getUserId() == loggedUser.getUserId())){
                        productListWished.add(product);
                    }
                }
            }
            request.setAttribute("productListWished", productListWished);
            forward("/wishList.jsp");
        } catch (NamingException ex) {
            Logger.getLogger(ShowMyWishListCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(ShowMyWishListCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ShowMyWishListCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
