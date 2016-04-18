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

public class BuyForFollowerCommand extends FrontCommand {

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
                Sales venta;
                //String timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
                if (session.getAttribute("loggedUser") == null) {
                    venta = new Sales(randomId, element.getProductId(), 0, new Date(),request.getParameter("payment"));
                    salesFacade.create(venta);
                } else {
                    Users loggedUser = (Users) session.getAttribute("loggedUser");
                    String aux = request.getParameter("payment");
                    venta = new Sales(randomId, element.getProductId(), loggedUser.getUserId(), new Date(),request.getParameter("payment"));
                    salesFacade.create(venta);
                }
            }
            // CAMBIAR UBICACION FICHERO
            try (PrintWriter writer = new PrintWriter("Ticket.txt", "UTF-8")) {
                writer.println("Thanks for your purchase:");
                writer.println("Product name - Price");
                for (Product product : cart.getContents()) {
                    writer.println(product.getName() + " - " + product.getPrice());
                }
            }
            cart.remove();
            session.setAttribute("Cart", null);
            forward("/purchaseCompleted.jsp");
            //forward("/FrontController?command=GetInitialDataCommand");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(BuyForFollowerCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
