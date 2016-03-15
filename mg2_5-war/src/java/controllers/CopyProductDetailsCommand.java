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

public class CopyProductDetailsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            Product product = productFacade.find(Integer.parseInt(request.getParameter("id")));
            List<Product> productList = new ArrayList<Product>();
            productList.add(product);
            
            request.setAttribute("productList", productList);
            forward("/modifyProduct.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(CopyProductDetailsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
