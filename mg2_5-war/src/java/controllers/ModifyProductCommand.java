package controllers;

import controller.ProductFacade;
import entities.Product;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ModifyProductCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = request.getParameter("release");
            Date releaseDate = null;
            try {
                releaseDate = formatter.parse(dateInString);
                //out.println(releaseDate);
                //out.println(formatter.format(releaseDate));

            } catch (ParseException e) {
                //e.printStackTrace();
            }
            
            Product product = new Product(
                    Integer.parseInt(request.getParameter("id")), 
                    request.getParameter("name"), 
                    Float.parseFloat(request.getParameter("price")), 
                    Float.parseFloat(request.getParameter("cost")), 
                    Integer.parseInt(request.getParameter("quantity")),
                    releaseDate,
                    Integer.parseInt(request.getParameter("available")));
            product.setDescription(request.getParameter("description"));
            product.setSynopsis(request.getParameter("synopsis"));
            productFacade.edit(product);
            
            List<Product> productList = productFacade.findAll();
            request.setAttribute("productList", productList);
            forward("/manageProducts.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ModifyProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
