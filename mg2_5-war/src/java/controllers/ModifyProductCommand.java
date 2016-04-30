package controllers;

import controller.ProductFacade;
import controller.UsersFacade;
import controller.WishlistFacade;
import entities.Product;
import entities.Users;
import entities.Wishlist;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;

public class ModifyProductCommand extends FrontCommand {

    @Override
    public void process() {
        try {
            ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            String dateInString = request.getParameter("release");
            Date releaseDate = null;
            try {
                releaseDate = formatter.parse(dateInString);

            } catch (ParseException e) {
            }
            Product product = new Product();
            String aux = request.getParameter("quantity");
            Product productoOriginal = productFacade.find(Integer.parseInt(request.getParameter("id")));

            product.setProductId(Integer.parseInt(request.getParameter("id")));
            product.setName(request.getParameter("name"));
            product.setPrice(Float.parseFloat(request.getParameter("price")));
            product.setCost(Float.parseFloat(request.getParameter("cost")));

            if (productoOriginal.getQuantity() == 0) {
                if (Integer.parseInt(request.getParameter("quantity")) > 0) {
                    avisaAUsuarios(productoOriginal);
                }
            }

            product.setQuantity(Integer.parseInt(request.getParameter("quantity")));
            product.setCategory(request.getParameter("category"));
            product.setReleaseDate(releaseDate);
            product.setLogo(request.getParameter("logo"));
            product.setDiscount(Integer.parseInt(request.getParameter("discount")));
            product.setAvailable(Integer.parseInt(request.getParameter("available")));
            product.setDescription(request.getParameter("description"));
            product.setSynopsis(request.getParameter("synopsis"));
            product.setDeveloper(request.getParameter("developer"));
            product.setPlatform(request.getParameter("platform"));
            productFacade.edit(product);
            List<Product> productList = productFacade.findAll();
            request.setAttribute("productList", productList);
            forward("/manageProducts.jsp");
        } catch (NamingException | ServletException | IOException ex) {
            Logger.getLogger(ModifyProductCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void avisaAUsuarios(Product productoOriginal) throws NamingException, IOException {
        WishlistFacade wishlistFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/WishlistFacade");
        List<Wishlist> wishList = wishlistFacade.findAll();
        UsersFacade usersFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/UsersFacade");
        ProductFacade productFacade = InitialContext.doLookup("java:global/mg2_5/mg2_5-ejb/ProductFacade");
        List<Users> usersList = usersFacade.findAll();

        //-> C:\Users\alumno\AppData\Roaming\NetBeans\8.0\config\GF_4.0\domain1\config
        for (Wishlist userAdvice : wishList) {
            if (userAdvice.getProductId() == productoOriginal.getProductId()) {
                Users userToAdvice = usersFacade.find(userAdvice.getUserId());
                Product productInStock = productFacade.find(userAdvice.getProductId());
                //"C:\\Users\\Jusio\\Desktop\\" + (productInStock.getName() + "-" + userToAdvice.getName()) + ".txt", Para comprobar que se genera bien
                // he puesto la misma ruta que esta en MailBoxCommand pero no parece que funcione. 
                FileWriter out = new FileWriter("./" + (productInStock.getName() + "-" + userToAdvice.getName()) + ".txt");
                out.write("Buenos días compañero " + userToAdvice.getName() + ", nos complace informarte que el juego " + productInStock.getName() + "ya está en stock, puedes proceder a su"
                        + " compra desde la lista de deseados. \r\n"
                        + "Que tengas un buen día y esperamos verte pronto.\r\n"
                        + "Servicio de atención al cliente de MitsukuGaming.");
                out.close();
            }

        }

    }

}
