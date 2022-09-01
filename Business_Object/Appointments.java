package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Appointments {

    private String apptDateTime;
    private String patient_id;
    private String chiroprac_id;
    private String proc_code;
    private String office_num;
    //============================== Overloading constructor ================================
    //Empty constructor
    public Appointments() {
        apptDateTime = "";
        patient_id = "";
        chiroprac_id = "";
        proc_code = "";
        office_num = "";        
    }
    // Constructor for all Appointment fields
    public Appointments(String apptDateTime, String patId, String chiropractorId, String procCode, String office) {
        this.apptDateTime = apptDateTime;
        this.patient_id = patId;
        this.chiroprac_id = chiropractorId;
        this.proc_code = procCode;
        this.office_num = office;
    }
    //============================================= Setters =========================================
    public void setApptDateTime(String apptDateTime ){
        this.apptDateTime = apptDateTime;
    }
    public void setPatId(String patId){
        this.patient_id = patId;
    }
    public void setChiropractorId(String chiroId){
        this.chiroprac_id = chiroId;
    }
    public void setProcCode(String procCode){
        this.proc_code = procCode;
    }
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
    public String getOfficeNum(){return office_num;}
    public void setOfficeNum(String officeNum){this.office_num = officeNum;}
    // Behaviors
    public void selectDB(String pId){
         this.patient_id = pId;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            Statement stmt = con.createStatement();
            //Execute statement
            String sql;
            sql = "Select * from Appointments WHERE patient_id = '"+ getPatId() +"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            //Process Data
            rs.next();
                setApptDateTime(rs.getString(4));
                setPatId(rs.getString(1));
                setChiropractorId(rs.getString(2));
                setProcCode(rs.getString(3));
                setOfficeNum(rs.getString(5));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }//end selectDB()
    /************************************************************************
    * insertDB() adds an appointment to the Database for the Patient ID 
    *************************************************************************/
    public void insertDB(String apptDT, String pid, String cid, String pcd){
        this.apptDateTime=apptDT;
        this.patient_id=pid;
        this.chiroprac_id=cid;
        this.proc_code =pcd;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            
            Statement stmt = con.createStatement();
            String sql = "Insert into Appointments(apptDateTime,patient_id,dentId,procCode) values('"+getApptDateTime()+"',"+
                                                      "'"+getPatId()+"',"+ 
                                                      "'"+getChiropractorId()+"',"+ 
                                                      "'"+getProcCode()+"')"; 
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
    /************************************************************************
    * updateDB() updates an appointment in the Database for the Patient ID 
    *************************************************************************/
    public void updateDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            
            Statement stmt = con.createStatement();
           
            String sql = "UPDATE Appointments SET apptDateTime = '"+getApptDateTime()+ "',"+ 
                                            " dentId ='"+getChiropractorId()+"',"+
                                            " procCode ='"+getProcCode()+"'"+
                                            " where patId='"+getPatId()+"'";    
            
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
    
    public void display(){
        System.out.println("Appointment Date and Time : " + getApptDateTime());
        System.out.println("Patient id: " + getPatId());
        System.out.println("Chiropractor id: " + getChiropractorId());
        System.out.println("Procedure Code: "+ getProcCode());
        System.out.println("Office Number: " + getOfficeNum());
    }
  
    public static void main(String args[]) { //testing
         
        Appointments appt2 = new Appointments();
        appt2.selectDB("P202");
        appt2.display();
        
        //Appointments appt = new Appointments();
       //appt.insertDB("July 3 2019 6PM", "A609", "D390", "P321");
       //Procedure pro = new Procedure();
       //pro.selectDB(appt.getProcCode());
       //appt.display();
       //pro.display();
      // appt.selectDB("A900");
       //appt.setApptDateTime("May 1, 2018, 9PM");
       //appt.setPatId("A900");
       //appt.setDentistId("D201");
       //appt.setProcCode("P321");
       //appt.updateDB();
       //appt.display();
      
       
         
        
         
    }//end main
   
}
