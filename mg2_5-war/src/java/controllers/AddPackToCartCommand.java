/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.PackContentFacade;
import controller.ProductFacade;
import entities.PackContent;
import entities.Product;
import entities.ShoppingCartLocal;
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
public class AddPackToCartCommand extends FrontCommand {

    @Override
    public void process() {

        try {
            PackContentFacade packContentFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackContentFacade");
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            List<PackContent> packContentList = packContentFacade.findAll();
            
            HttpSession session = request.getSession(true);
            ShoppingCartLocal cart = (ShoppingCartLocal) session.getAttribute("Cart");
            if (cart == null) {
                cart = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ShoppingCart");
                session.setAttribute("Cart", cart);
            }
            
            List<Product> productListPack = new ArrayList<>();

            for (PackContent element : packContentList) {
                if (element.getPackId() == Integer.parseInt(request.getParameter("productId"))) {
                    productListPack.add(productFacade.find(element.getProductId()));
                }
            }

            float discount = Float.parseFloat(request.getParameter("discount")) / 100;
            for (Product product : productListPack) {
                product.setPrice(product.getPrice() - (discount * product.getPrice()));
                // Modificamos el descuento del producto para NO aplicar dos tipos de descuento diferentes :
                // 1 del pack y otro el que lleve.
                product.setDiscount(0);
                cart.addProduct(product);
            }

            forward("/showCart.jsp");

        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(AddPackToCartCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
