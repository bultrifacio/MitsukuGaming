/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import controller.SalesFacade;
import entities.Product;
import entities.Sales;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

/**
 *
 * @author Jusio
 */
public class GetInitialDataCommand extends FrontCommand {

    @Override
    public void process() {
        ProductFacade productFacade;
        try {
            productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            SalesFacade salesFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/SalesFacade");
            List<Product> productList = productFacade.findAll();
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
