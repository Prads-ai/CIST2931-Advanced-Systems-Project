/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * Business Objects
 * Author: Lyons Kevin, Pradsley D'Haiti
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
    private String password;
    private Appointments p_appointment;
    
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
        password = "";
        p_appointment = new Appointments();
    }
    //Constructor with arguments
    public Patient(String p,String f, String l, String a, String c , String s, String z , String e , String ins, String pass){
        this.patient_id = p;
        this.firstName = f;
        this.lastName = l;
        this.address = a;
        this.city = c;
        this.state = s;
        this.zip = z;
        this.email = e;
        this.ins_co = ins;
        this.password = pass;
        this.p_appointment = new Appointments();
    }  
    //====================== Behaviors ==============================================================
    //Setters and getters
    public String getPatientId(){return patient_id;}
    public void setPatientId(String patId){this.patient_id = patId;}
    
    public String getFirstName(){return firstName;}
    public void setFirstName(String f){this.firstName = f;}
    
    public String getLastName(){return lastName;}
    public void setLastName(String l){this.lastName = l;}
    
    
    public String getAddress() { return address; }
    public void setAddress(String p) { this.address = p; }
		
    public String getCity() { return city; }
    public void setCity(String ci) { this.city = ci; }
                            
    public String getState() { return state; }
    public void setState(String st) { this.state = st; }
		
    public String getZip() { return zip; }
    public void setZip(String zp) {this.zip = zp; }

    public String getEmail() { return email; }
    public void setEmail(String em) { this.email = em; }
		
    public String getInsco() { return ins_co; }
    public void setInsco(String in) { ins_co = in; }
    
    public String getPassword(){return password;}
    public void setPassword(String p){this.password = p;}
    
    public void setPatAppt(Appointments appt){this.p_appointment=appt;}
    public Appointments getPatAppt() {return p_appointment;}
    
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
        System.out.println("Password       =   " + password);
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
            String sql = "SELECT * FROM Patients where Patient_id='"+getPatientId()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            
            setFirstName(rs.getString(1));
            setLastName(rs.getString(2));
            setAddress(rs.getString(3));
            setCity(rs.getString(4));
            setState(rs.getString(5));  
            setZip(rs.getString(6));
            setEmail(rs.getString(7));
            setInsco(rs.getString(8));
            setPassword(rs.getString(10)); 
            p_appointment.selectDB(id);
            rs.close();
        }
        catch(Exception e){
            System.out.println(e);
        } 
    }//end selectDB()
        
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * updateDB() update the patient data and information from the Database 
    *************************************************************************/
     
    public void updateDB(){
        try{
            //Loading the driver
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            System.out.println("Database connected and ready to be updated");
            Statement stmt = con.createStatement();
            String sql = "UPDATE  Patients  SET firstname = '"+ getFirstName() +"',"
                    + "lastName = '"+ getLastName() +"',"
                    + "address = '"+ getAddress() +"',"
                    + "city = '"+ getCity() +"',"
                    + "state = '"+ getState() +"',"
                    + "zip = '"+ getZip() +"',"
                    + "email = '"+ getEmail() +"',"
                    + "ins_co = '"+ getInsco() +"'"
                    + "password = '"+ getPassword() +"',"
                    + " WHERE  Patient_id ='"+ getPatientId() +"'" ;
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
     }//end of updateDB
    
    public static void main(String arg[]){
        
        Patient p2 = new Patient();
        p2.selectDB("P201");
        Appointments appt = new Appointments();
        appt.selectDB(p2.getPatientId());
        p2.display();
        System.out.println("============= Appointment =================");
        appt.display();
    }
}// end of class
