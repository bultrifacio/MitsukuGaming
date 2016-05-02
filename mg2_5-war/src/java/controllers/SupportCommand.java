package controllers;

import controller.UsersFacade;
import entities.Users;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;

public class SupportCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
            HttpSession session = request.getSession(true);
            Users loggedUser = (Users) session.getAttribute("loggedUser");
            String data = request.getParameter("support");
            //C:\Users\Usuario\GlassFish_Server\glassfish\domains\domain1\config\.\SuggestionFile.txt
            try ( //Esta es -> C:\Users\alumno\AppData\Roaming\NetBeans\8.0\config\GF_4.0\domain1\config uyh
                    //BufferedWriter out = new BufferedWriter(new FileWriter("./support.txt"))
                    BufferedWriter out = new BufferedWriter(new FileWriter(System.getProperty("user.home") + "/Desktop/Support.txt"))
                ) 
            {
                out.write("Message send by: " + loggedUser.getName());
                out.newLine();
                out.write("Email: " + loggedUser.getEmail());
                out.newLine();
                out.write("Message: ");
                out.newLine();
                out.write(data);
            }
            //forward("/supportform.jsp");
            forward("/FrontController?command=GetInitialDataCommand");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(SupportCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}