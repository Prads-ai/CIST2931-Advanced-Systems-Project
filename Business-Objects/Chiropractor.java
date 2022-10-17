
package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Chiropractor {
                private String chiroprac_id;
		private String firstName;
		private String lastName;
		private String email;
                private String office_num;
                private String admin_id;
                private String password;
                private Appointments appt = new Appointments();
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
    }
// Constructor with arguments
    public Chiropractor(String i, String fn, String ln, String ed, String ap, String ad, String pass, Appointments appt) {
		this.chiroprac_id=i;
		this.firstName=fn;
		this.lastName=ln;
		this.email=ed;
                this.office_num=ap;
                this.admin_id= ad;
                this.password = pass;
                this.appt = appt;
    }           
 /*********************************************************************
 *
 * The selectDB gets dentist info from database
 * 
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
            
      // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * select Chiropractor () gets the chiropractor data and information from the Database
    *************************************************************************/    
        
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
    
    }catch(Exception ex){
        ex.printStackTrace();
    }
    
    
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
        
	public void display() {
        	System.out.println("ID             =   "+ chiroprac_id);                
		System.out.println("First Name     =   "+ firstName);
		System.out.println("Last Name      =   "+ lastName);	
		System.out.println("Email            =   "+ email);
                System.out.println("Office           =   "+ office_num);
                System.out.println("Admin id = " + admin_id);
                System.out.println("Password = " + password);
        }
        
        public void DisplayChiroAppointments(){
                System.out.println("ID             =   "+ chiroprac_id);                
		System.out.println("ApptDateTime   =   "+ appt.getApptDateTime());
		System.out.println("patient_id     =   "+ appt.getPatId());	
		System.out.println("Proc_Code      =   "+ appt.getProcCode());
                System.out.println("Office_num = " + appt.getOfficeNum());
        }
	
        public static void main(String args[]) {
		
         Chiropractor chiro1 = new Chiropractor();
         chiro1.selectDB("C510");
         chiro1.display();
         
         AppointmentList appL = new AppointmentList();
         appL.getChiroAppointments("C510");
         appL.display();
                        
        }
}
