package controllers;

import controller.PackContentFacade;
import entities.PackContent;
import controller.PackDetailsFacade;
import entities.PackDetails;
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

public class CopyPackDetailsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            PackDetailsFacade packFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackDetailsFacade");
            PackDetails pack = packFacade.find(Integer.parseInt(request.getParameter("id")));
            List<PackDetails> packList = new ArrayList<>();
            packList.add(pack);
            request.setAttribute("packList", packList);
            forward("/modifyPacks.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(CopyPackDetailsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
