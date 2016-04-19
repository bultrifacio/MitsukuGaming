package controllers;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;

public class UnknownCommand extends FrontCommand {
    @Override
    public void process() {
        try {
            forward("/unknown.jsp");
        } catch (ServletException | IOException ex) {
            Logger.getLogger(UnknownCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
