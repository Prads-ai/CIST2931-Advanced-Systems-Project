/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Business_Object.Servlets;

import Business_Object.Patient;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author pach3
 */
@WebServlet(name = "UpdatePatientInfoServlet", urlPatterns = {"/UpdatePatientInfoServlet"})
public class UpdatePatientInfoServlet extends HttpServlet {

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
            /* TODO output your page here. You may use following sample code. */
            // Create Session 
            HttpSession session = request.getSession();
           Patient p = (Patient)session.getAttribute("currentPatient");
           
           out.println(p.getFirstName());
           out.println(p.getLastName());
           out.println(p.getEmail());
           out.println(p.getAddress());
           out.println(p.getCity());
           out.println(p.getState());
           out.println(p.getZip());
           out.println(p.getPassword());
           
           System.out.println(p.getChiropractor().getchiroprac_id());
            
          // create variables
            String fname, lname, email, address, city, state , pass,zip;
  
            // Assigned each variable 
            fname = request.getParameter("fname");
            lname = request.getParameter("lname");
            email = request.getParameter("email");
            address = request.getParameter("address");
            city = request.getParameter("city");
            state = request.getParameter("state");
            zip = request.getParameter("zip");
            pass = request.getParameter("pass");
            
            // Update the field
            p.setFirstName(fname);
            p.setLastName(lname);
            p.setEmail(email);
            p.setAddress(address);
            p.setCity(city);
            p.setState(state);
            p.setZip(zip);
            p.setPassword(pass);
            
            p.updateDB();
            
            //Request Dispatcher
            RequestDispatcher dispatcher = request.getRequestDispatcher("patient_dashboard.jsp");
            dispatcher.forward(request, response);
            
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
