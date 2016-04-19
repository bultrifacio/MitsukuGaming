package controllers;

import controller.ProductFacade;
import entities.ShoppingCartLocal;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class RemoveFromCartCommand extends FrontCommand {

    @Override
    public void process() {
        ShoppingCartLocal cart = null;
        try {
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