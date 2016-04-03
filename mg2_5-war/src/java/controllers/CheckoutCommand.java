package controllers;

import entities.Product;
import entities.ShoppingCartLocal;
import java.io.IOException;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class CheckoutCommand extends FrontCommand {

    @Override
    public void process() {
        try {
        HttpSession session = request.getSession(true);
        ShoppingCartLocal cart = (ShoppingCartLocal) session.getAttribute("Cart");
        if (cart == null){
            forward("/showCart.jsp");
        }
        Iterator<Product> itr = cart.getContents().iterator();
        float price = 0;
        while (itr.hasNext()) {
          Product element = itr.next();
          price += element.getPrice();
        }
        request.setAttribute("priceTotalProduct", price);
        forward("/checkout.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(CheckoutCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
