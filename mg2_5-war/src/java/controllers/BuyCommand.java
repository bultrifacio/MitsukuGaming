/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import controller.SalesFacade;
import entities.Product;
import entities.Sales;
import entities.ShoppingCartLocal;
import entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ENTRAR
 */
public class BuyCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            SalesFacade salesFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/SalesFacade");

            HttpSession session = request.getSession(true);
            ShoppingCartLocal cart = (ShoppingCartLocal) session.getAttribute("Cart");
            Iterator<Product> itr = cart.getContents().iterator();

            while (itr.hasNext()) {
                Product element = itr.next();
                Product newElement = productFacade.find(element.getProductId());
                newElement.setQuantity(newElement.getQuantity() - 1);
                productFacade.edit(newElement);

                int randomId = new Random().nextInt(1000000);
                List<Sales> randomList = salesFacade.findAll();
                for (Sales productRandom : randomList) {
                    if (randomId == productRandom.getProductId()) {
                        randomId = new Random().nextInt(1000000);
                    }
                }

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String date = new Date().toString();
                if (session.getAttribute("loggedUser") == null) {
                    Sales sale = new Sales();
                    sale.setDate(new Date());
                    sale.setMethod(request.getParameter("payment"));
                    sale.setProductId(element.getProductId());
                    sale.setUserId(0);
                    salesFacade.create(sale);
                } else {
                    Users loggedUser = (Users) session.getAttribute("loggedUser");
                    Sales sale = new Sales();
                    sale.setDate(new Date());
                    sale.setMethod(request.getParameter("payment"));
                    sale.setProductId(element.getProductId());
                    sale.setUserId(loggedUser.getUserId());
                    salesFacade.create(sale);
                }
            }
            // CAMBIAR UBICACION FICHERO
            //try (PrintWriter writer = new PrintWriter("Ticket.txt", "UTF-8")) {
            try (PrintWriter writer = new PrintWriter(System.getProperty("user.home") + "/Desktop/Ticket.txt", "UTF-8")) {
                writer.println("Thanks for your purchase:");
                writer.println("Product name - Price");
                for (Product product : cart.getContents()) {
                    if (product.getDiscount() > 0) {
                        writer.println(product.getName() + " - " + (product.getPrice() - (product.getPrice() * (product.getDiscount() / 100))));
                    } else {
                        writer.println(product.getName() + " - " + product.getPrice());
                    }
                }
            }
            cart.remove();
            session.setAttribute("Cart", null);
            forward("/purchaseCompleted.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(BuyCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
