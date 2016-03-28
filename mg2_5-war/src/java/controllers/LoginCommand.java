package controllers;

import entities.ShoppingCartLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class LoginCommand extends FrontCommand{

    @Override
    public void process() {
        ShoppingCartLocal cart;
        try {
            HttpSession session = request.getSession(true);
            cart = (ShoppingCartLocal) session.getAttribute("Cart");
            if (cart == null) {
                cart = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ShoppingCart");
                session.setAttribute("Cart", cart);
            }
            request.setAttribute("cart", cart.getContents());
            forward("/showCart.jsp");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(LoginCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}