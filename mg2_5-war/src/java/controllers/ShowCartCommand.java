package controllers;

import controller.ProductFacade;
import entities.Product;
import entities.ShoppingCartLocal;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class ShowCartCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            ShoppingCartLocal cart = (ShoppingCartLocal) session.getAttribute("Cart");
            if (cart == null) {
                cart = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ShoppingCart");
                session.setAttribute("Cart", cart);
            }
            ArrayList<Product> myCart = cart.getContents();
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            ArrayList<Product> selectedProducts = new ArrayList<>();

            String currency = (String) session.getAttribute("currency");
            for (Product cartProduct : myCart) {
                if (currency.equals("Dollar")) {
                    cartProduct.setPrice((float) 1.11970 * cartProduct.getPrice());
                }
                selectedProducts.add(cartProduct);
            }
            cart.setContents(selectedProducts);
            session.setAttribute("Cart", cart);
            session.setAttribute("total", cart.getTotal());
            request.setAttribute("cart", selectedProducts);
            forward("/showCart.jsp");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(ShowCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
