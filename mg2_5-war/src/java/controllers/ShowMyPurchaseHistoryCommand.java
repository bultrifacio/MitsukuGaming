package controllers;

import controller.ProductFacade;
import controller.SalesFacade;
import entities.Product;
import entities.Sales;
import entities.Users;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class ShowMyPurchaseHistoryCommand extends FrontCommand {

    ProductFacade productFacade;
    SalesFacade salesFacade;

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            
            productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            salesFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/SalesFacade");
            
            List<Product> productList = productFacade.findAll();
            List<Product> productListFiltre = new ArrayList<>();
            List<Sales> salesList = salesFacade.findAll();
            List<Sales> userSales = new ArrayList<>();
            
            for (Sales sale : salesList) {
                if (sale.getUserId() == loggedUser.getUserId()) {
                    userSales.add(sale);
                }
            }
            for (Sales sale : userSales) {
                for (Product product : productList) {
                    if (sale.getProductId() == product.getProductId()) {
                        productListFiltre.add(product);
                    }
                }
            }

            request.setAttribute("productListFiltre", productListFiltre);
            forward("/showMyPurchaseHistory.jsp");
        } catch (ServletException | IOException | NamingException ex) {
            Logger.getLogger(ShowMyPurchaseHistoryCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
