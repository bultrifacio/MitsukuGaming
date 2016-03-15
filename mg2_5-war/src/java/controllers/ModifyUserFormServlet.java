
import controller.UsersFacade;
import entities.Users;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="ModifyUserFormServlet", urlPatterns={"/ModifyUserFormServlet"})

public class ModifyUserFormServlet extends HttpServlet {

    @EJB
    private UsersFacade usersFacade;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet userFormServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet userFormServlet at " + request.getContextPath() + "</h1>");
 
            
            Users user = new Users(4,request.getParameter("name"),request.getParameter("email"),request.getParameter("password"));
            usersFacade.edit(user);
            List<Users> lista = usersFacade.findAll();
            for (Users c : lista) {
                    out.println("Id: " + c.getUserId() + " Name: " + c.getName() + " Email: " + c.getEmail() + "<br>");
  
            }
           
            out.println("</body>");
            out.println("</html>");
            /*
            String id = request.getParameter("id");
            if (id != null) {
                Customer customerdelete = customerfacade.find(Integer.parseInt(id));
                if (customerdelete != null) {
                    customerfacade.remove(customerdelete);
                }
            }
            List<Customer> lista = customerfacade.findAll();
            for (Customer c : lista) {
                out.println("ID: " + c.getCustomerId()
                        + " Nombre: " + c.getName()
                        + " Email: " + c.getEmail() + "<br>");
            }
             */
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
