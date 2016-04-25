package controllers;

import controller.PackDetailsFacade;
import entities.PackDetails;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ModifyPackCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            PackDetailsFacade packFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackDetailsFacade");
            PackDetails pack = new PackDetails();
            pack.setPackId(Integer.parseInt(request.getParameter("id")));
            pack.setName(request.getParameter("name"));
//            product.setLogo(request.getParameter("logo"));
            pack.setDiscount(Integer.parseInt(request.getParameter("discount")));
            pack.setDescription(request.getParameter("description"));
            packFacade.edit(pack);
            List<PackDetails> packList = packFacade.findAll();
            request.setAttribute("packList", packList);
            forward("/managePacks.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ModifyPackCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
