package entities;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Singleton;
import javax.ejb.LocalBean;
import javax.ejb.Schedule;
import javax.naming.NamingException;

@Singleton
@LocalBean
public class TimerAdmin {

    @Schedule (second="*/30", minute="*", hour="*")
    public void scheduleTimer(){
        try {
            Backup.backup();
        } catch (NamingException ex) {
            Logger.getLogger(TimerAdmin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
}