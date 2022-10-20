/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * Appointments Business Object
 * Author: Pradsley D'Haiti, Lyons Kevin
 * Date: 10/20/2022
 *********************************************************************************/
package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
/******************************************************************
 * The Appointment Class is used to hold a patient's appointment
 ******************************************************************/
public class Appointments {

    private String apptDateTime;
    private String patient_id;
    private String chiroprac_id;
    private String proc_code;
    private int office_num;
    private int id;
    //============================== Overloading constructor ================================
    //Empty constructor
    public Appointments() {
        
        apptDateTime = "";
        patient_id = "";
        chiroprac_id = "";
        proc_code = "";
        office_num = 0;  
        id = 0;
    }
    // Constructor for all Appointment fields
    public Appointments(String apptDateTime, String patId, String chiropractorId, String procCode, int office, int id) {
        this.apptDateTime = apptDateTime;
        this.patient_id = patId;
        this.chiroprac_id = chiropractorId;
        this.proc_code = procCode;
        this.office_num = office;
        this.id = id;
    } 
    // Constructor for all Appointment fields
    public Appointments(String apptDateTime, String patId, String chiropractorId, String procCode, int office) {
        this.apptDateTime = apptDateTime;
        this.patient_id = patId;
        this.chiroprac_id = chiropractorId;
        this.proc_code = procCode;
        this.office_num = office;
        this.id = id;
    }
    //============================================= Setters =========================================
    public void setApptDateTime(String apptDateTime ){
        this.apptDateTime = apptDateTime;
    }
    public void setId(int id){ this.id = id;}
    public void setPatId(String patId){
        this.patient_id = patId;
    }
    public void setChiropractorId(String chiroId){
        this.chiroprac_id = chiroId;
    }
    public void setProcCode(String procCode){
        this.proc_code = procCode;
    }
    public void setOfficeNum(int office){this.office_num = office;}
    //============================ Getters ============================================
    public String getApptDateTime(){
        return apptDateTime;
    }
    public String getPatId(){
        return patient_id;
    }
    public String getChiropractorId(){
        return chiroprac_id;
    }
    public String getProcCode(){
        return proc_code;
    }
    public int getOfficeNum(){return office_num;}
    
    public int getId(){return id;}
    // ====================== selectDB() =========================
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * selectDB() gets an appointment from the Database using the Patient ID 
    *************************************************************************/
    public void selectDB(String pId){
        this.patient_id = pId;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            Statement stmt = con.createStatement();
            //Execute statement
            String sql;
            sql = "SELECT * FROM Appointments WHERE patient_id = '"+ getPatId() +"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            //Process Data
            rs.next();
                setApptDateTime(rs.getString(1));
                setId(rs.getInt(6));
                setPatId(rs.getString(2));
                setChiropractorId(rs.getString(3));
                setProcCode(rs.getString(4));
                setOfficeNum(rs.getInt(5));

                rs.close();
                con.close();
                stmt.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
        System.out.println("--------------------------------------------------------");

    }//end selectDB()
    //====================== insertDB()=========================================
    /************************************************************************
    * insertDB() adds an appointment to the Database for the Patient ID 
    *************************************************************************/
    public void insertDB(String apptDT, String pid, String cid, String pcd, int office){
        this.apptDateTime=apptDT;
        this.patient_id=pid;
        this.chiroprac_id=cid;
        this.proc_code =pcd;
        this.office_num = office;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            Statement stmt = con.createStatement();
            String sql = "Insert into Appointments(apptDateTime,patient_id,chiroprac_id,proc_code,office_num) values('"+getApptDateTime()+"',"+
                                                      "'"+getPatId()+"',"+ 
                                                      "'"+getChiropractorId()+"',"+ 
                                                      "'"+getProcCode()+"',"+""
                                                        + "'"+getOfficeNum()+"')"; 
            System.out.println(sql);
            int n1 = stmt.executeUpdate(sql);
            if (n1==1)
                System.out.println("INSERT Successful!!!");
            else
                System.out.println("INSERT FAILED***********");
            con.close();
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }//end insertDB()
    //========================== update()====================================
    /************************************************************************
    * updateDB() updates an appointment in the Database for the Patient ID 
    *************************************************************************/
    public void updateDB(int id){
        
        this.id = id;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            Statement stmt = con.createStatement();         
            String sql = "UPDATE Appointments SET apptDateTime = '"+ getApptDateTime() + "',"+
                                            "patient_id ='"+ getPatId() +"',"+
                                            "chiroprac_id ='"+ getChiropractorId() +"',"+
                                            "proc_code ='"+ getProcCode() +"',"+
                                            "office_num ='"+ getOfficeNum() +"'"+
                                            "WHERE patient_id='"+getPatId()+"' AND id = '"+ id +"' "; 
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n==1)
                System.out.println("UPDATE Successful!!!");
            else
                System.out.println("UPDATE FAILED***********");
            con.close();
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }//end updateDB()
    //============================ delete() ====================================
    /************************************************************************
    * deleteDB() deletes an appointment from the Database for the Patient ID 
    *************************************************************************/
    public void deleteDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            
            Statement stmt = con.createStatement();
            String sql = "DELETE from Appointments where apptDateTime='"+getApptDateTime()+"'";
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n==1)
                System.out.println("DELETE Successful!!!");
            else
                System.out.println("DELETE FAILED***********");
            con.close();
        }
        catch(Exception e1){
            System.out.println(e1);
        }
    }//end deleteDB()
    //======================== display() and toString() ============================================
 /*****************************************************************************************************************************
 * The display and toString method return the values of all the patient class fields.
 ******************************************************************************************************************************/  
    public void display(){
        System.out.println("Appointment Date and Time : " + getApptDateTime());
        System.out.println("Patient id: " + getPatId());
        System.out.println("Chiropractor id: " + getChiropractorId());
        System.out.println("Procedure Code: "+ getProcCode());
        System.out.println("Office Number: " + getOfficeNum());
        System.out.println("Id : " + getId());
    }  
    public String toString(){
        return "  Appointment Date and Time: " + this.apptDateTime + "\n" +
                  "Patient id: " + this.patient_id + "\n" +
                  "Chiropractor id: " + this.chiroprac_id + "\n" +
                  "Procedure code: " + this.proc_code + "\n" +
                  "Office Number: " + this.office_num + "\n" +
                  "Id : " + this.id;
                }          
    public static void main(String args[]) { //testing
     //==================== Testing =======================    
        Appointments appt = new Appointments();

        appt.selectDB("P201");
        appt.setApptDateTime("09/28/2022");
        appt.setChiropractorId("C510");
        appt.setProcCode("PR303");
        appt.setOfficeNum(1);
        appt.updateDB(4);
        appt.display();
    
    }//end main 
}
