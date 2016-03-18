/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import entities.Product;
import entities.ShoppingCart;
import entities.ShoppingCartLocal;
import java.io.IOException;
import static java.lang.System.out;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author alumno
 */
public class RemoveFromCartCommand extends FrontCommand {

    @Override
    public void process() {
        out.println("<h1>Prueba Shooping cart/h1>");
        ShoppingCartLocal cart = null;
        try {
            //cart = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ShoppingCart!controller.ShoppingCartLocal");
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            HttpSession session = request.getSession(true);
            cart = (ShoppingCartLocal) session.getAttribute("Cart");
            if (cart == null) {
                cart = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ShoppingCart");
                session.setAttribute("Cart", cart);
            }
            cart.removeProduct(productFacade.find(Integer.parseInt(request.getParameter("id"))));
            request.setAttribute("cart", cart.getContents());
            forward("/showCart.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(RemoveFromCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}