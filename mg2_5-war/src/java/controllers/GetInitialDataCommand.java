/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import controller.SalesFacade;
import entities.Product;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class GetInitialDataCommand extends FrontCommand {

    @Override
    public void process() {
        ProductFacade productFacade;
        try {
            HttpSession session = request.getSession(true);
            
            // Cada vez que se vuelve a la pagina principal, se reinicia la moneda actual a Euro
            // Hay que cambiar esto de sitio
            session.setAttribute("currency", "Euro");
            
            session.setAttribute("actualPage", "FrontController");
            productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            SalesFacade salesFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/SalesFacade");
            
            List<Product> productList = productFacade.findAll();
            
            String currency = (String) session.getAttribute("currency");
            if (!currency.equals("Euro")) {
                for (Product product : productList) {
                    if (currency.equals("Dollar")) {
                        product.setPrice((float) 1.11970 * product.getPrice());
                    }
                }
            }
            /*List<Product> productList = new ArrayList<>();
            List<Sales> sales = salesFacade.findAll();
            TreeMap<Integer,Integer> sinRepeticion = new TreeMap<>();
            for (Sales sale : sales) {
                if(sinRepeticion.containsKey(sale.getProductId())){
                    sinRepeticion.put(sale.getProductId(), sinRepeticion.get(sale.getProductId())+1);
                }else{
                    sinRepeticion.put(sale.getProductId(), 1);
                }
                
            }
            for (Integer id : sinRepeticion.keySet()) {
                for (Product product : productListT) {
                    if ((product.getProductId()).equals(id)) {
                        productList.add(product);
                        break;
                    }
                }
            }/*
            for (Sales sale : sales) {
                for (Product product : productListT) {
                    if (product.getProductId()==sale.getProductId()) {
                        productList.add(product);
                        break;
                    }
                }
            }*/
            request.setAttribute("productList", productList);
            forward("/index.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(GetInitialDataCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
