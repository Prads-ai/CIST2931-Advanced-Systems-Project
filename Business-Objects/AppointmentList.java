
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
    public ArrayList<Appointments> appointment;
    Patient p = new Patient();
    String p_id = p.getPatAppt().getPatId();
    String chiroprac_id = p.getPatAppt().getChiropractorId();
    int count;
    //================ Constructors=======================
    public AppointmentList(){
       appointment = new ArrayList<Appointments>();
       count++;
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
                    int theId = rs.getInt(6);
                    String Patient_id = rs.getString(2);
                    String Chiroprac_id = rs.getString(3);
                    String proc_Code = rs.getString(4);
                    int office_num =rs.getInt(5);

                    Appointments appt = new Appointments(date_time,Patient_id,Chiroprac_id,proc_Code,office_num,theId);
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
   
public ArrayList<Appointments>getChiroAppointments(String id){
       
        chiroprac_id = id;
        //ArrayList<Appointments> appointment = new ArrayList<Appointments>();
         
         Connection con1 = null;
         Statement stmt = null;
         ResultSet rs = null;
         
        try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
                System.out.println("Database connected");
                stmt = con1.createStatement();
                String sql = "SELECT * FROM Appointments WHERE chiroprac_id ='"+chiroprac_id+"'";
                rs = stmt.executeQuery(sql);
                
                while(rs.next()){
                    
                    String date_time = rs.getString(1);
                    int theId = rs.getInt(6);
                    String Patient_id = rs.getString(2);
                    String Chiroprac_id = rs.getString(3);
                    String proc_Code = rs.getString(4);
                    int office_num =rs.getInt(5);

                    Appointments appt = new Appointments(date_time,Patient_id,Chiroprac_id,proc_Code,office_num,theId);
                    appointment.add(appt);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        finally{
            closeConnection(con1,stmt,rs);
        }
        
         return appointment;
    }// End of search Appointment
   
   //--------------------------------------------------------------------------
      public Appointments SearchAppointment(String date){
         
          Appointments appt = new Appointments();
          for(int i = 0 ; i < appointment.size(); i++){
              if(appointment.get(i).getApptDateTime().equalsIgnoreCase(date)){
                appt.setApptDateTime( appointment.get(i).getApptDateTime());
                appt.setChiropractorId(appointment.get(i).getChiropractorId());
                appt.setId(appointment.get(i).getId());
                appt.setPatId(appointment.get(i).getPatId());
                appt.setOfficeNum(appointment.get(i).getOfficeNum());
                appt.setProcCode(appointment.get(i).getProcCode());
              }
          }
          return appt;
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
        appL.display();
        
        System.out.println("This is the result page");
        Appointments result = appL.SearchAppointment("2022-15-14");
        
        if(result.getApptDateTime().equalsIgnoreCase("")){
            result.setApptDateTime("No Data");
            result.setChiropractorId("No Data");
            System.out.println(result);
        }
       // System.out.println( "This is the result " + result.getApptDateTime());
        
        
       // appt.setApptDateTime("9/12/2022");
       // appt.setChiropractorId("C510");
       // appt.setProcCode("PR303");
       // appt.setOfficeNum(5);
       // appt.updateDB(4);
        
       // appL.setAppointment(1, appt);
    
        //appt.display();
        
      
     
    }
}

