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
import java.util.Date;
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
            ProductFacade producto = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            String name = request.getParameter("name");
            String quantity = request.getParameter("quantity");
            String price = request.getParameter("price");
            String cost = request.getParameter("cost");
            String description = request.getParameter("description");
            String avaible = request.getParameter("avaible");
            String date = request.getParameter("date");
            String synopsis = request.getParameter("synopsis");
            Product product = new Product();
            product.setName(name);
            product.setQuantity(Integer.parseInt(quantity));
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date releaseDate = formatter.parse(date);
            product.setReleaseDate(releaseDate);
            if ("True".equals(avaible)) {
                product.setAvailable(1);
            } else {
                product.setAvailable(0);
            }
            product.setCost(Float.parseFloat(cost));
            product.setPrice(Float.parseFloat(price));
            product.setDescription(description);
            product.setProductId(producto.count() + 1);
            product.setSynopsis(synopsis);
            producto.create(product);
            forward("/manageProducts.jsp");
        } catch (NamingException ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ServletException ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AddProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        ;
    }

}
