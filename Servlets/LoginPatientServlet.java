package Business_Object.Servlets;

import Business_Object.Appointments;
import Business_Object.Chiropractor;
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

@WebServlet(name = "LoginPatientServlet", urlPatterns = {"/LoginPatientServlet"})
public class LoginPatientServlet extends HttpServlet {

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
            
            //Creating a session for the user
            HttpSession sess = request.getSession();
            HttpSession chiroSession = request.getSession();
            HttpSession appointmentSession = request.getSession();
            HttpSession procedureSession = request.getSession();

           //Storing user information
          String patient_id = request.getParameter("patientId");
          String password = request.getParameter("password");
          
          // Create a new patient object
          Patient currentPatient = new Patient();
          currentPatient.selectDB(patient_id);
          String patientDB = currentPatient.getPatientId(); // Patient id in the database
          String patientPassDB = currentPatient.getPassword();
          
         // Checking to see if there is a match
         boolean credentialsValid = patientDB.equals(patient_id) && patientPassDB.equals(password);
        
         // Getting all the object needed for this patient
         Appointments appt = new Appointments();
         Chiropractor chiro = new Chiropractor();
         
        // Comparing user credentials 
          
          
          if(credentialsValid){            
              sess.setAttribute("currentPatient",currentPatient); // Create a session for this user
              sess.setAttribute("appt", appt);
              sess.setAttribute("chiro", chiro);
   
              RequestDispatcher dispatcher = request.getRequestDispatcher("patient_dashboard.jsp");
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
