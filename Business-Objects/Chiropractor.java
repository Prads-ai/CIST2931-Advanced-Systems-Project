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
/*******************************************************************************
 * The Chiropractor Class is used to hold a Chiropractor's data or information
 ******************************************************************************/
public class Chiropractor {
                private String chiroprac_id;
		private String firstName;
		private String lastName;
		private String email;
                private String office_num;
                private String admin_id;
                private String password;
                private Appointments appt = new Appointments();
                private String schedule;
                private int id;
//=============================Overloading Constructors
//Empty Constructor                
    public Chiropractor() {
		
                chiroprac_id="";     
		firstName="";
		lastName="";
		email="";
                office_num="";
                admin_id="";
                password = "";
                appt = new Appointments();
                schedule = "";
                id = 0;
    }
//Constructor with parameters
    public Chiropractor(String i, String fn, String ln, String ed, String ap, String ad, String pass, Appointments appt, Patient p) {
		this.chiroprac_id=i;
		this.firstName=fn;
		this.lastName=ln;
		this.email=ed;
                this.office_num=ap;
                this.admin_id= ad;
                this.password = pass;
                this.appt = appt;
                this.schedule = "";
                this.id = 0;
    }     
//Constructor with parameters 
    public Chiropractor(String i, String fn, String ln, String ed, String office, String ad, String pass){
               this.chiroprac_id = i;
               this.firstName = fn;
               this.lastName = ln;
               this.email = ed;
               this.office_num = office;
               this.admin_id = ad;
               this.password = pass;
               this.schedule = "";
    }
//================== selectDB() ===========================================    
 /*********************************************************************
 * The selectDB gets a Chiropractor info from database
 *******************************************************************/                                                
    public void selectDB(String i) {
        chiroprac_id=i; 
        
	try {    //Load DB Driver
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
		//Execute SQL Statement
		Statement stmt =con1.createStatement();
		String sql = "Select * from Chiropractors where chiroprac_id='"+getchiroprac_id()+"'";
		//Process ResultSet
		System.out.println(sql);
                
                ResultSet rs = stmt.executeQuery(sql);
                rs.next();
                setchiroprac_id(rs.getString(1));   
                setfirstName(rs.getString(2));
                setlastName(rs.getString(3));
                setemail(rs.getString(4));
                setoffice_num(rs.getString(5));
                setadmin_id(rs.getString(6)); 
                setPassword(rs.getString(7));
            }catch(Exception se) {
		System.out.println(se);
            	}
            System.out.println("--------------------------------------------------------");
	} //end selectDB()
// //================== selectChiropractorFromScheduleTable() ===========================================      
 /*********************************************************************
 * The selectChiropractorFromScheduleTable(parameter) gets dentist info from the ChiropractorSchedule table of the database
 *******************************************************************/                                            
    public void selectChiropractorFromScheduleTable(int theId){
        
        id = theId;
        Chiropractor chiro = new Chiropractor();
     
        Connection con1 = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
                System.out.println("Database connected");
                stmt = con1.createStatement();
                String sql = "SELECT * FROM ChiropractorSchedule WHERE id = "+id+"";
                rs = stmt.executeQuery(sql);
                
                rs.next();
                    setId (rs.getInt(1));
                    setchiroprac_id (rs.getString(2));
                    setfirstName(rs.getString(3));
                    setlastName(rs.getString(4));
                    setemail(rs.getString(5));
                    setoffice_num(rs.getString(6));
                    setSchedule(rs.getString(7));
                    setadmin_id(rs.getString(8));
                    setPassword(rs.getString(9));
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
// ======================= UpdateDB ==============================================
    // ++++++++++ DB Behaviors +++++++++++++
    /***************************************************************************
    * UpdateDB() update the chiropractor data and information from the Database
    ****************************************************************************/    
    public void UpdateDB(){
        try{
                //Load DB Driver
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
		//Execute SQL Statement
		Statement stmt =con1.createStatement();
		String sql = "UPDATE Chiropractors SET "
                        + "firstName ='"+getfirstName()+"',"
                        + "lastName = '"+getlastName()+"',"
                        + "email = '"+getemail()+"',"
                        + "office_num = '"+getoffice_num()+"'"
                        + " WHERE chiroprac_id='"+getchiroprac_id()+"'";
		//Process ResultSet
		System.out.println(sql);
                
                int n = stmt.executeUpdate(sql);
                    // Checking if the code has been executed
                if(n == 1){
                System.out.println("Success !!! ");
                }
                 else{
                System.out.println("Failed !!!");
                }
        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
// ============================ UpdateChiropractorSchedule(parameter) ====================================
    // ++++++++++ DB Behaviors +++++++++++++
    /********************************************************************************************************************************
    * UpdateChiropractorSchedule() update a chiropractor data and information from the ChiropractorSchedule table of the Database 
    *********************************************************************************************************************************/    
    public void UpdateChiropractorSchedule(int id){
              
        try{
                //Load DB Drivers
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
		//Execute SQL Statement
		Statement stmt =con1.createStatement();
		String sql = "UPDATE ChiropractorSchedule SET "
                        + "Chiroprac_Id ='"+getchiroprac_id()+"',"
                        + "Firstname ='"+getfirstName()+"',"
                        + "Lastname = '"+getlastName()+"',"
                        + "email = '"+getemail()+"',"
                        + "office_num = '"+getoffice_num()+"',"
                        + "Schedule = '"+getSchedule()+"',"
                        + "Admin_Id = '"+getadmin_id()+"',"
                        + "password = '"+getPassword()+"'"
                        + " WHERE ID ='"+id+"'";
		//Process ResultSet
		System.out.println(sql);
                
                int n = stmt.executeUpdate(sql);
                    // Checking if the code has been executed
                if(n == 1){
                System.out.println("Success !!! ");
                }
                 else{
                System.out.println("Failed !!!");
                }        
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }
// ============================== SelectChiroFromAppTable(parameter) ===================================================
      // ++++++++++ DB Behaviors +++++++++++++
    /********************************************************************************************************************
    * SelectChiroFromAppTable() gets the chiropractor data and information from the Appointments table of the Database
    ********************************************************************************************************************/    
     String pName; 
    public void SelectChiroFromAppTable(String chiro){

     chiroprac_id = chiro;
    try{
        Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
        System.out.println("Database connected");
        Statement stmt = con.createStatement();
        
        String sql = "SELECT * FROM Appointments WHERE chiroprac_id = '"+chiroprac_id+"'";
        System.out.println(sql);
        
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
                setchiroprac_id(rs.getString(3));
                appt.setApptDateTime(rs.getString(1));
                appt.setPatId(rs.getString(2));
                appt.setProcCode(rs.getString(4));
                appt.setOfficeNum(rs.getInt(5)); 
                
                Patient patient = new Patient();
                patient.selectDB(appt.getPatId());
                pName = patient.getFirstName();
    
    }catch(Exception ex){
        ex.printStackTrace();
        } 
    }
// ============================= Behaviors ============================
    //Getters and Setters
        public String getchiroprac_id() { return chiroprac_id; }
	public void setchiroprac_id(String i) { chiroprac_id=i; }
                
        public String getfirstName() { return firstName; }
	public void setfirstName(String fn) { firstName=fn; }
                
                
        public String getlastName() { return lastName; }
	public void setlastName(String ln) { lastName=ln; }

	public String getemail() { return email; }
	public void setemail(String ed) { email=ed; }
                
                
        public String getoffice_num() { return office_num; }
	public void setoffice_num(String ap) { office_num=ap; }
                
        public String getadmin_id() { return admin_id; }
        public void setadmin_id(String ad) { admin_id=ad; }
        
        public void setSchedule(String sched){this.schedule = sched;}
        public String getSchedule(){return schedule;}
        
        public void setId(int id) {
            this.id = id;
        }

        public int getId() {
            return id;
        }
           
        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
//================== display() ===========================================
/*****************************************************************************************************************************
 * The display method return the values of all the chiropractor class fields
 ******************************************************************************************************************************/      
	public void display() {
        	System.out.println("ID             =   "+ chiroprac_id);                
		System.out.println("First Name     =   "+ firstName);
		System.out.println("Last Name      =   "+ lastName);	
		System.out.println("Email          =   "+ email);
                System.out.println("Office         =   "+ office_num);
                System.out.println("Admin id       =   " + admin_id);
                System.out.println("Password       =   " + password);
        }
//================== DisplayChiroAppointments() ===========================================
 /*****************************************************************************************************************************
 * The DisplayChiroAppointments method return the values of all the Appointment classes for the selected chiropactor 
 ******************************************************************************************************************************/  
        public void DisplayChiroAppointments(){
            
                System.out.println("ChiropractID   =   "+ chiroprac_id);                
		System.out.println("ApptDateTime   =   "+ appt.getApptDateTime());
		System.out.println("patient_id     =   "+ appt.getPatId());	
		System.out.println("Proc_Code      =   "+ appt.getProcCode());
                System.out.println("Office_num = " + appt.getOfficeNum());
                System.out.println("Patient Firstname" + pName);
        }
//================== DisplayChiropractorSchedule() ===========================================
/*****************************************************************************************************************************
 * The DisplayChiroAppointments method return the values of all the Appointment classes for the selected chiropactor 
 ******************************************************************************************************************************/ 	
       public void DisplayChiropractorSchedule(){
           System.out.println("ID  = "+ id);
           System.out.println("Chiroprac ID : " + chiroprac_id);
           System.out.println("Firstname: " + firstName);
           System.out.println("Lastname  : " + lastName);
           System.out.println("Email: " + email);
           System.out.println("Office number: " + office_num);
           System.out.println("Schedule : "+ schedule);
           System.out.println("Admin ID: " + admin_id);
           System.out.println("Password " + password);
       }
//================== toString() ===========================================
/*****************************************************************************************************************************
 * The toString method return the values of all the chiropractor class fields
 ******************************************************************************************************************************/        
       public String toString(){
           return " ID : " + this.id +
                   " Chiropract_ Id: " + this.chiroprac_id +
                   " FirstName: " + this.firstName +
                   " LastName: " + this.lastName +
                   " Email: " + this.email +
                   " Office Number : " + this.office_num +
                   " Schedule : " + this.schedule +
                   " Chiropract_ Id: " + this.admin_id +
                   " Admin Id: " + this.password ;
       }
        public static void main(String args[]) {
	// ======= Testing ================	
         Chiropractor chiro1 = new Chiropractor();
         
         chiro1.selectChiropractorFromScheduleTable(4);
         chiro1.setfirstName("Pradsley");
         chiro1.UpdateChiropractorSchedule(4);
         chiro1.DisplayChiropractorSchedule();
                        
        }
}
