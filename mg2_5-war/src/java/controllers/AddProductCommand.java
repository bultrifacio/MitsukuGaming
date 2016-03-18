/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import entities.Product;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/**
 *
 * @author juancarlos
 */
public class AddProductCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            String date = request.getParameter("date");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            
            Product product = new Product(
                    new Random().nextInt(1000000),
                    request.getParameter("name"), 
                    Float.parseFloat(request.getParameter("price")),
                    Float.parseFloat(request.getParameter("cost")), 
                    Integer.parseInt(request.getParameter("quantity")), 
                    formatter.parse(date), 1);
            
            if (request.getParameter("available").equals("True")) {
                product.setAvailable(1);
            } else {
                product.setAvailable(0);
            }
            
            product.setDescription(request.getParameter("description"));
            product.setSynopsis(request.getParameter("synopsis"));
            
            productFacade.create(product);
            List<Product> productList = productFacade.findAll();
            request.setAttribute("productList", productList);
            forward("/manageProducts.jsp");
        } catch (NamingException | ParseException | ServletException | IOException ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
