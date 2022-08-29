/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * Business Objects
 * Author: Lyons Kevin, Pradsley D'Haiti, Morhun Daria, Jarmal Richardson, Wilcox Devares
 * Date: 8/27/2022
 *********************************************************************************/
package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Patient {

// ==================== Properties =========================
    private String patient_id;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String email;
    private String ins_co;
    
    //============================ Overloading Constructors ==================================================
    //Constructor with no arguments
    public Patient(){
        patient_id = "";
        firstName = "";
        lastName = "";
        address = "";
        city = "";
        state = "";
        zip = "";
        email = "";
        ins_co = "";
    }
    //Constructor with arguments
    public Patient(String p,String f, String l, String a, String c , String s, String z , String e , String ins){
        this.patient_id = p;
        this.firstName = f;
        this.lastName = l;
        this.address = a;
        this.city = c;
        this.state = s;
        this.zip = z;
        this.email = e;
        this.ins_co = ins;
    }
    
    //====================== Behaviors ==============================================================
    //Setters and getters
    public String getPatientId(){return patient_id;}
    public void setPatientId(String patId){this.patient_id = patId;}
    
    public String getFirstName (){return firstName;}
    public void setFirstName(String f){this.firstName = f;}
    
    public String getLastName(){return lastName;}
    public void setLastname(String l){this.lastName = l;}
    
    
    public String getAddress() { return address; }
    public void setAddress(String p) { this.address=p; }
		
    public String getCity() { return city; }
    public void setCity(String ci) { this.city=ci; }
                            
    public String getState() { return state; }
    public void setState(String st) { this.state =st; }
		
    public String getZip() { return zip; }
    public void setZip(String zp) {this.zip=zp; }

    public String getEmail() { return email; }
    public void setEmail(String em) { this.email=em; }
		
    public String getIns_co() { return ins_co; }
    public void setIns_co(String in) { ins_co=in; }
    
    // ====================== Connection with DataBase =========================
       
    public void display() {
        System.out.println("ID             =   "+ patient_id);
        System.out.println("FirstName     =   "+ firstName);
	System.out.println("LastName      =   "+ lastName);
	System.out.println("Address        =   "+ address);
	System.out.println("City           =   "+ city);
        System.out.println("State          =   "+ state);
	System.out.println("Zip            =   "+ zip);
	System.out.println("Email          =   "+ email);
        System.out.println("Ins_Co         =   "+ ins_co);
    }
    
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * selectDB() gets the patient data and information from the Database 
    *************************************************************************/
        public void selectDB(String id){
        patient_id = id;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            System.out.println("Database connected");
              
            Statement stmt = con.createStatement();
            String sql = "Select * from Patients where Patient_id='"+getPatientId()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            
            setFirstName(rs.getString(1));
            setLastname(rs.getString(2));
            setAddress(rs.getString(3));
            setCity(rs.getString(4));
            setState(rs.getString(5));  
            setZip(rs.getString(6));
            setEmail(rs.getString(7));
            setIns_co(rs.getString(8));
        }
        catch(Exception e){
            System.out.println(e);
        }
        
    }//end selectDB()
            
    
    public static void main(String arg[]){
        
        Patient p2 = new Patient();
        p2.selectDB("P202");
        p2.display();
    }
}// end of class
