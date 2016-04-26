package controllers;

import controller.PackContentFacade;
import controller.PackDetailsFacade;
import controller.ProductFacade;
import entities.PackContent;
import entities.PackDetails;
import entities.Product;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class CopyPackDetailsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            PackDetailsFacade packFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackDetailsFacade");
            PackDetails pack = packFacade.find(Integer.parseInt(request.getParameter("id")));
            List<PackDetails> packList = new ArrayList<>();
            packList.add(pack);
            
            grabMyItems();
            
            request.setAttribute("packList", packList);
            forward("/modifyPack.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(CopyPackDetailsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void grabMyItems() throws NamingException {
        
        List<Product> itemsInPack = new ArrayList<>();
        List<Product> itemsNotInPack = new ArrayList<>();
        List<PackDetails> packList = new ArrayList<>();
        PackContentFacade contentFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackContentFacade");
        ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
        List<Product> productList = productFacade.findAll();
        
        List<PackContent> contentList = contentFacade.findAll();

        for (PackContent Product : contentList) {
            if (Product.getPackId() == Integer.parseInt(request.getParameter("id"))) {
                itemsInPack.add(productFacade.find((Product.getProductId())));
            }
        }

        for (Product element : productList) {
            if(!itemsInPack.contains(element)){
                itemsNotInPack.add(element);
            }
        }
        
        request.setAttribute("itemsInPack", itemsInPack);
        request.setAttribute("itemsNotInPack", itemsNotInPack);
        
    }
}
