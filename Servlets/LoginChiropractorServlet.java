/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * LoginChiropractorServlet
 * Author: Daria Morhun
 * Date: 10/20/2022
 *********************************************************************************/
package Business_Object.Servlets;

import Business_Object.AppointmentList;
import Business_Object.Appointments;
import Business_Object.Chiropractor;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
 //====================== LoginChiropractorServlet=========================================
 /****************************************************************************************************************
 *- LoginChiropractorServlet : 
 * Get the values of all the fields of the chiropractor-login.jsp form
 * Create and call the selectDB from the chiropractor class
 * Get the chiropractor id and name from the selectDB method
 * Validate the authenticity of the chiropractor
 * Forward the current chiropractor to the chiropractor dashboard page or to an error page if there is a credential problem
 *****************************************************************************************************************************/
@WebServlet(name = "LoginChiropractorServlet", urlPatterns = {"/LoginChiropractorServlet"})
public class LoginChiropractorServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {

           HttpSession sess = request.getSession();
           HttpSession sessList = request.getSession();
           //Storing user information
          String chiroprac_id = request.getParameter("chiroprac_id");
          String password = request.getParameter("password");
          
          Chiropractor cc = new Chiropractor();
          Appointments appt = new Appointments();
          AppointmentList appL = new AppointmentList();
          
          appL.getChiroAppointments(chiroprac_id);
       
          cc.selectDB(chiroprac_id);
          out.print(cc.getPassword());
         
          boolean isValid = cc.getchiroprac_id().equals(chiroprac_id) && cc.getPassword().equals(password);
         
          if(isValid){
                 if(appL.appointment.size() == 0){
                     
                    sess.setAttribute("cc",cc);
                    RequestDispatcher dispatcher = request.getRequestDispatcher("NoAppointmentDashboard.jsp");
                    dispatcher.forward(request, response);                  
                }
              System.out.println("Valid credentials");
              sess.setAttribute("cc",cc);
              sessList.setAttribute("recentAppointment", appL);
              RequestDispatcher dispatcher = request.getRequestDispatcher("chiropractor_dashboard.jsp");
              dispatcher.forward(request, response);
          }else{
              RequestDispatcher dispatcher = request.getRequestDispatcher("404.html");
              dispatcher.forward(request, response);
          }
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
