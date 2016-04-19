package controllers;

import controller.PackDetailsFacade;
import entities.PackDetails;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ShowPacksCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            PackDetailsFacade packsDetailsFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/PackDetailsFacade");
            List<PackDetails> packList = packsDetailsFacade.findAll();
            request.setAttribute("packList", packList);
            forward("/managePacks.jsp");
        } catch (NamingException | IOException | ServletException ex) {
            Logger.getLogger(ShowPacksCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
