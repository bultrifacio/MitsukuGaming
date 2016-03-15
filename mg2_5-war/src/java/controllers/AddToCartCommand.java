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
import java.util.ArrayList;
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
public class AddToCartCommand extends FrontCommand {

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
            cart.addProduct(request.getParameter("id"));
            forward("/showCart.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(AddToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*cart.addProduct("1");
        cart.addProduct("7");
        cart.addProduct("3");
        cart.addProduct("8");
        cart.addProduct("10");
        cart.removeProduct("7");
        ArrayList<String> shooppingCart = cart.getContents();
        for (String product : shooppingCart) {
        out.println(product + "<br>");
        }
        cart.removeProduct("7");
        cart.removeProduct("10");
        cart.removeProduct("3");
        for (String product : shooppingCart) {
        out.println(product + "<br>");
        }*/ 

        /*cart.addProduct("1");
        cart.addProduct("7");
        cart.addProduct("3");
        cart.addProduct("8");
        cart.addProduct("10");

        cart.removeProduct("7");

        ArrayList<String> shooppingCart = cart.getContents();
        for (String product : shooppingCart) {
            out.println(product + "<br>");
        }

        cart.removeProduct("7");
        cart.removeProduct("10");
        cart.removeProduct("3");

        for (String product : shooppingCart) {
            out.println(product + "<br>");
        }*/
    }

}
