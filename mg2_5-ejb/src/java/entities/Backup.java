package entities;

import controller.SalesFacade;
import controller.UsersFacade;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;
import javax.naming.InitialContext;
import javax.naming.NamingException;

@Singleton
@LocalBean
public class Backup {

    
    public static void backup () throws NamingException {
        UsersFacade userFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
        List<Users> usersList = userFacade.findAll();
        try (PrintWriter writer = new PrintWriter(System.getProperty("user.home") + "/Desktop/BackupUsers.txt", "UTF-8")) {
                writer.println("CREATE TABLE USERS (USER_ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, \"NAME\" VARCHAR(30) NOT NULL, EMAIL VARCHAR(40) NOT NULL, PASSWORD VARCHAR(32) NOT NULL, STATE INTEGER NOT NULL);");
                for (Users user : usersList) {
                    writer.println("INSERT INTO APP.USERS (NAME, EMAIL, PASSWORD, STATE) VALUES ('"+user.getName()+"', '"+user.getEmail()+"', '"+ user.getPassword() +"', '"+ user.getState()+"');");
                }
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        SalesFacade salesFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/SalesFacade!controller.SalesFacade");
        List<Sales> salesList = salesFacade.findAll();
        try (PrintWriter writer = new PrintWriter(System.getProperty("user.home") + "/Desktop/BackupSales.txt", "UTF-8")) {
                writer.println("CREATE TABLE SALES (SALE_ID INTEGER GENERATED ALWAYS AS IDENTITY PRIMARY KEY, PRODUCT_ID INTEGER NOT NULL, USER_ID INTEGER NOT NULL, \"DATE\" DATE NOT NULL, \"METHOD\" VARCHAR(144) DEFAULT 'Other'  NOT NULL);");
                
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                
                for (Sales sale : salesList) {
                    Date date = sale.getDate();
                    String dateInString = formatter.format(date);
                    writer.println("INSERT INTO APP.SALES (PRODUCT_ID, USER_ID, \"DATE\", \"METHOD\") VALUES ("+ sale.getProductId() +", "+ sale.getUserId() +", '" + dateInString + "', '"+ sale.getMethod() +"');");
                }
            } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(Backup.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
