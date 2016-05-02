/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import controller.ProductFacade;
import controller.WishlistFacade;
import entities.Product;
import entities.Users;
import entities.Wishlist;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

/**
 *
 * @author hector
 */
public class ArrowsCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            HttpSession session = request.getSession(true);
            int pagesAux = (Integer) session.getAttribute("pages");
            int indexpag = (Integer) session.getAttribute("indexPagination");
            if (request.getParameter("direction").equals("1")&& (pagesAux!=indexpag)) {
                int aux = (Integer) session.getAttribute("indexPagination");
                aux++;
                session.setAttribute("indexPagination", aux);
            } else if(request.getParameter("direction").equals("-1")&& (1!=indexpag)){
                int aux = (Integer) session.getAttribute("indexPagination");
                aux--;
                session.setAttribute("indexPagination", aux);

            }
            forward("/FrontController?command=GetInitialDataCommand");

        } catch (ServletException | IOException ex) {
            Logger.getLogger(ArrowsCommand.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
