package controllers;

import controller.ProductFacade;
import entities.Product;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ShowProductsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            List<Product> productList = productFacade.findAll();
            request.setAttribute("productList", productList);
            forward("/manageProducts.jsp");
        } catch (NamingException | IOException | ServletException ex) {
            Logger.getLogger(ShowProductsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
