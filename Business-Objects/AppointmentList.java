/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * Patient Business Object
 * Author: Pradsley D'Haiti, Lyons Kevin
 * Date: 10/20/2022
 *********************************************************************************/
package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
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
  //======================== display() ============================================
 /*****************************************************************************************************************************
 * The display method return the values of all the Appointment class fields.
 ******************************************************************************************************************************/ 
    public void display(){
        for(int i = 0 ; i < appointment.size(); i++){
         System.out.println(appointment.get(i).toString());
        }
    }// end of display()
 //======================== addAppoinment() ============================================
 /*****************************************************************************************************************************
 * The addAppoinment method add an appointment object to the appointment arrayList.
 ******************************************************************************************************************************/ 
    public void addAppoinment(Appointments appt){
        appointment.add(appt);
        System.out.println("===============");
    }//end of addAppointment()
 //======================== reverseAppointment() ============================================
 /*****************************************************************************************************************************
 * The reverseAppointment method reverse the order of the appointments inside the arraylist. the last index  become the first index.
 ******************************************************************************************************************************/    
    public void reverseAppointment(Appointments appt){
        Collections.reverse(appointment);
    }// end of reverseAppointment()
    //===================== getAppointments()===========================================
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * getAppointments() gets multiple Appointment Classes with the same Patient ID
    *************************************************************************/
   String PatientID ;
   public ArrayList<Appointments>getAppointments(String id){
       
         p_id = id;         
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
                    PatientID = Patient_id;
                    appointment.add(appt);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        finally{
            closeConnection(con1,stmt,rs);
        }     
         return appointment;
    }// end of getAppointments()
  //===================== getChiroAppointments()===========================================
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * getAppointments() gets multiple Appointment Classes with the same Chiropractor ID
    *************************************************************************/
   public ArrayList<Appointments>getChiroAppointments(String id){
       
        chiroprac_id = id;
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
                    p.selectDB(Patient_id);
                    String Chiroprac_id = rs.getString(3);
                    String proc_Code = rs.getString(4);
                    int office_num =rs.getInt(5);
                    
                    PatientID = Patient_id ;
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
    }// End of getChiroAppointments
     //===================== SearchAppointment()===========================================
    /***********************************************************************************************************
    * SearchAppointment() find and return a single appointment from the appointment arrayList based on the date.
    *************************************************************************************************************/ 
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
    }// End of SearchAppointment   
    //===================== FindPatientID()===========================================
    /***********************************************************************************************************
    * FindPatientID() find and return the patient ID  from appointment arrayList.
    *************************************************************************************************************/  
    public Appointments FindPatientID(String id){
       
       AppointmentList allApp = new AppointmentList();
       Collections.reverse(allApp.appointment);
       allApp.getAppointments(id);
       String patId= "";
       Appointments newApp = new Appointments();
       String patID = "";
       p.selectDB(id);
       String idDB = p.getPatientId();
       idDB = id;
       allApp.display();
       
       for(int i = 0; i < appointment.size(); i++){
           
          patId = allApp.appointment.get(i).getPatId();
          String apDt = allApp.appointment.get(i).getApptDateTime();
          String chiroID = allApp.appointment.get(i).getChiropractorId();
          int officeNum = allApp.appointment.get(i).getOfficeNum();
          String proc_Code =  allApp.appointment.get(i).getProcCode();
          int tableId =  allApp.appointment.get(i).getId();
          
          if(patId.equals(idDB) ){
               newApp.setApptDateTime(apDt);
               newApp.setChiropractorId(chiroID);
               newApp.setId(officeNum);
               newApp.setOfficeNum(officeNum);
               newApp.setProcCode(proc_Code);
               newApp.setPatId(p.getFirstName());
            }
       }
        return newApp;
    }// end of FindPatientID()
    //===================== RecentAppointment()===========================================
    /***********************************************************************************************************
    * RecentAppointment() display the latest patient appointment from appointment arrayList.
    *************************************************************************************************************/  
    public Appointments RecentAppointment(ArrayList appL){
    
        AppointmentList allApp = new AppointmentList();
        Appointments app = new Appointments();
        allApp.getChiroAppointments(chiroprac_id);
        Collections.reverse(appL);
        for(int i = 0 ; i < allApp.appointment.size(); i++){
           
           app.setApptDateTime(appointment.get(i).getApptDateTime());
           app.setChiropractorId( appointment.get(i).getChiropractorId());
           app.setId( appointment.get(i).getId());
           app.setOfficeNum(appointment.get(i).getOfficeNum());
           app.setProcCode( appointment.get(i).getProcCode());
           app.setPatId( appointment.get(i).getPatId());
        
        }
        allApp.appointment.get(appointment.size()-1);
        return app;
    }
  // closeDB connection, resultset and statement 
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
   // ======= Testing ============== 
public static void main(String[] args){
      AppointmentList appL = new AppointmentList();
      appL.getChiroAppointments("C510");
      appL.display();
    }
}

