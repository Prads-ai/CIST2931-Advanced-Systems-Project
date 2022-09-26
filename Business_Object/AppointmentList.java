
package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*****************************************************************************************************
 * The Appointment List Class is used to hold multiple Appointment Classes 
 *****************************************************************************************************/
public class AppointmentList {
    //================== Properties =========================
    private ArrayList<Appointments> appointment;
    Patient p = new Patient();
    String p_id = p.getPatAppt().getPatId();
    
    //================ Constructors=======================
    public AppointmentList(){
       appointment = new ArrayList<Appointments>();

    }
    //================= Behaviors ===========================
    //Getters and Setters
    public void setAppointment(int index,Appointments appt){
       appointment.set(index, appt);
    }
    
    // Display method
    public void display(){
        for(int i = 0 ; i < appointment.size(); i++){
         System.out.println(appointment.get(i).toString());
        }
    }
    public void addAppoinment(Appointments appt){
        appointment.add(appt);
        System.out.println("===============");
    }
     
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * selectDB() gets multiple Appointment Classes with the same Chiropractor ID 
    *************************************************************************/

   public ArrayList<Appointments>getAppointments(String id){
       
        p_id = id;
        //ArrayList<Appointments> appointment = new ArrayList<Appointments>();
         
         Connection con1 = null;
         Statement stmt = null;
         ResultSet rs = null;
         
        try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
                System.out.println("Database connected");
                stmt = con1.createStatement();
                String sql = "SELECT * FROM Appointments WHERE patient_id ='"+p_id+"'";
                rs = stmt.executeQuery(sql);
                
                while(rs.next()){
                    
                    String date_time = rs.getString(1);
                    String Patient_id = rs.getString(2);
                    String Chiroprac_id = rs.getString(3);
                    String proc_Code = rs.getString(4);
                    int office_num =rs.getInt(5);

                    Appointments appt = new Appointments(date_time,Patient_id,Chiroprac_id,proc_Code,office_num);
                    appointment.add(appt);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        finally{
            closeConnection(con1,stmt,rs);
        }
        
         return appointment;
    }
    
       
    
   private void closeConnection(Connection con1, Statement stmt, ResultSet rs){
       
       try{
           if(con1 != null){
               con1.close();
           }
           if(stmt != null){
               stmt.close();
           }
           if(rs != null){
               rs.close();
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }
public static void main(String[] args){
   
           
        AppointmentList appL = new AppointmentList();
        Appointments appt = new Appointments();
       
        appt.selectDB("P201");
        appL.getAppointments(appt.getPatId());
        
        appt.setChiropractorId("P201");
        appt.setOfficeNum(1);
        appt.setApptDateTime("9/12/2025");
        
        appt.updateDB();
        appL.setAppointment(0, appt);
        appL.display();
       
       
     
    }
}

