package controllers;

import controller.ProductFacade;
import controller.UsersFacade;
import entities.Product;
import entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
public class SendOffersCommand extends FrontCommand{ 

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            List<Product> productList = productFacade.findAll();
            List<Product> productLista = new ArrayList<>();
            for (Product product : productList) {
                if(product.getDiscount()>1){
                    productLista.add(product);
                }
            }
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            List<Users> usersList = usersFacade.findAll();
            String emails="";
            for (Users users : usersList) {
                emails+=users.getEmail()+";";
            }
            //Se mandan los correos con la lista de productos a los emails
            forward("/sendOffers.jsp");
            
        } catch (NamingException | IOException | ServletException ex) {
            Logger.getLogger(ShowProductsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
