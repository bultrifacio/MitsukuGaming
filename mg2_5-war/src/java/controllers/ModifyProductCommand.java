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

            } catch (ParseException e) {
            }
            Product product = new Product();
            product.setProductId(Integer.parseInt(request.getParameter("id")));
            product.setName(request.getParameter("name"));
            product.setPrice(Float.parseFloat(request.getParameter("price")));
            product.setCost(Float.parseFloat(request.getParameter("cost")));
            product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            product.setCategory(request.getParameter("category"));
            product.setReleaseDate(releaseDate);
            product.setLogo(request.getParameter("logo"));
            product.setDiscount(Integer.parseInt(request.getParameter("discount")));
            product.setAvailable(Integer.parseInt(request.getParameter("available")));
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
