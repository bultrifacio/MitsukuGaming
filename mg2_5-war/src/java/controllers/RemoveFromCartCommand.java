/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import entities.ShoppingCart;
import entities.ShoppingCartLocal;
import java.io.IOException;
import static java.lang.System.out;
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
            cart = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ShoppingCart");
            HttpSession session = request.getSession(true);
            cart = (ShoppingCartLocal) session.getAttribute("Cart");
            if (cart == null) {
                cart = new ShoppingCart();
                session.setAttribute("Cart", cart);
            }
            cart.removeProduct(request.getParameter("id"));
            forward("/showCart.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(RemoveFromCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}