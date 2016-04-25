package controllers;

import controller.PackDetailsFacade;
import entities.PackDetails;
import controller.PackContentFacade;
import entities.PackContent;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class RemovePackCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            PackDetailsFacade packFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackDetailsFacade");
            
            
            PackContentFacade contentFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackContentFacade");
            
            List<PackContent> contentList = contentFacade.findAll();
            for (PackContent Product : contentList) {
                if (Product.getPackId() == Integer.parseInt(request.getParameter("id"))){
                    PackContent contentDelete = contentFacade.find((Product.getId()));
                    contentList.remove(contentDelete);
                }               
            }
            
            /*
            String actualId = request.getParameter("id");
            if (actualId != null) {
                PackDetails packDelete = packFacade.find(Integer.parseInt(actualId));
                if (packDelete != null) {
                    packFacade.remove(packDelete);
                }
            }
            */
            List<PackDetails> packList = packFacade.findAll();
            request.setAttribute("packList", packList);
            forward("/managePacks.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(RemovePackCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}