package controllers;

import controller.ProductFacade;
import controller.SalesFacade;
import controller.WishlistFacade;
import entities.Product;
import entities.Sales;
import entities.Wishlist;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ShowProductStatsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            Product product = productFacade.find(Integer.parseInt(request.getParameter("id")));
            
            SalesFacade salesFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/SalesFacade");
            List<Sales> sales = salesFacade.findAll();
            
            WishlistFacade wishlistfacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/WishlistFacade");
            List<Wishlist> wishlist = wishlistfacade.findAll();
            
            int salesProduct = 0; //Number of sales of this product.
            for (Sales sale : sales) {
                if (( (int) sale.getProductId()) == Integer.parseInt(request.getParameter("id"))) {
                    salesProduct++;
                }
            }

            int wishProduct = 0; //Number of wish of this product.
            for (Wishlist wish : wishlist) {
                if (( (int) wish.getProductId()) == Integer.parseInt(request.getParameter("id"))) {
                    wishProduct++;
                }
            }
            //METHOD of payment
            int visa = 0;
            int mastercard = 0;
            int paypal = 0;
            int wire_transfer = 0;
            for (Sales sale : sales) {
                if ((sale.getProductId() == Integer.parseInt(request.getParameter("id"))) && (sale.getMethod().equals("Visa"))) {
                    visa++;
                }
                if ((sale.getProductId() == Integer.parseInt(request.getParameter("id"))) && (sale.getMethod().equals("MasterCard"))) {
                    mastercard++;
                }
                if ((sale.getProductId() == Integer.parseInt(request.getParameter("id"))) && (sale.getMethod().equals("PayPal"))) {
                    paypal++;
                }
                if ((sale.getProductId() == Integer.parseInt(request.getParameter("id"))) && (sale.getMethod().equals("Wire transfer"))) {
                    wire_transfer++;
                }
            }
            //HttpSession session = request.getSession(true);
            //session.setAttribute("actualPage", "/FrontController?command=ShowProductDetailsCommand");
            request.setAttribute("name", product.getName());
            request.setAttribute("salesProduct", salesProduct);
            request.setAttribute("wishProduct", wishProduct);
            request.setAttribute("MethodVisa", visa);
            request.setAttribute("MethodMasterCard", mastercard);
            request.setAttribute("MethodPaypal", paypal);
            request.setAttribute("MethodWireTranfer", wire_transfer);
            
            forward("/productStats.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ShowProductStatsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

