package controllers;

import controller.ProductFacade;
import entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ShowProductDetailsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            String aux = request.getParameter("id");
            
            Product product = productFacade.find(Integer.parseInt(request.getParameter("id")));
            List<Product> selectedProduct = new ArrayList<Product>();
            selectedProduct.add(product);
            
            request.setAttribute("selectedProduct", selectedProduct);
            forward("/productDetails.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ShowProductDetailsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}