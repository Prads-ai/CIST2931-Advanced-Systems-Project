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
/*********************************************************************
 * The Patient Class is used to hold a patient's data or information
 *********************************************************************/
public class Patient {
// ==================== Properties =========================
    private String patientId;
    private String firstName;
    private String lastName;
    private String address;
    private String city;
    private String state;
    private String zip;
    private String email;
    private String insCo;
    private String password;
    private Appointments p_appointment;
    private Chiropractor chiro;  
    //============================ Overloading Constructors ==================================================
    //Constructor with no arguments
    public Patient(){
        patientId = "";
        firstName = "";
        lastName = "";
        address = "";
        city = "";
        state = "";
        zip = "";
        email = "";
        insCo = "";
        password = "";
        p_appointment = new Appointments();
        chiro = new Chiropractor();
    }
    //================ Constructor with arguments ============================
    public Patient(String p,String f, String l, String a, String c , String s, String z , String e , String ins, String pass){
        this.patientId = p;
        this.firstName = f;
        this.lastName = l;
        this.address = a;
        this.city = c;
        this.state = s;
        this.zip = z;
        this.email = e;
        this.insCo = ins;
        this.password = pass;
        this.p_appointment = new Appointments();
        this.chiro = new Chiropractor();
    }  
    //====================== Behaviors ==============================================================
    //Setters and getters
    public String getPatientId(){return patientId;}
    public void setPatientId(String patId){this.patientId = patId;}
    
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
		
    public String getInsco() { return insCo; }
    public void setInsco(String in) { insCo = in; }
    
    public String getPassword(){return password;}
    public void setPassword(String p){this.password = p;}
    
    public void setPatAppt(Appointments appt){this.p_appointment=appt;}
    public Appointments getPatAppt() {return p_appointment;}
    
    public void setChiro(Chiropractor chi){this.chiro = chi;}
    public Chiropractor getChiropractor(){return chiro;}
//======================== display() ============================================
 /*****************************************************************************************************************************
 * The display method return the values of all the patient class fields except for the appointment  and the chiropractor object 
 ******************************************************************************************************************************/  
    public void display() {
        System.out.println("ID             =   "+ patientId);
        System.out.println("FirstName     =   "+ firstName);
	System.out.println("LastName      =   "+ lastName);
	System.out.println("Address        =   "+ address);
	System.out.println("City           =   "+ city);
        System.out.println("State          =   "+ state);
	System.out.println("Zip            =   "+ zip);
	System.out.println("Email          =   "+ email);
        System.out.println("Password       =   " + password);
        System.out.println("Ins_Co         =   "+ insCo);
    }
 //=========================== findPatientName()================================
 /*****************************************************************************************************************************
 * The findPatientName method returns the name of the selected patient from the database. 
 ******************************************************************************************************************************/     
    public String findPatientName(){
        String patientName ;
        Patient p = new Patient();
        p.selectDB(patientId);
        patientName = p.getFirstName();
        return patientName;
    }    
    // ====================== selectDB() =========================
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * selectDB() gets the patient data and information from the Database 
    *************************************************************************/
        public void selectDB(String id){
        patientId = id;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            System.out.println("Database connected");
            Statement stmt = con.createStatement();
            String sql = "SELECT * FROM Patients where Patient_id='"+getPatientId()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            setPatientId(rs.getString(1));
            setFirstName(rs.getString(2));
            setLastName(rs.getString(3));
            setAddress(rs.getString(4));
            setCity(rs.getString(5));
            setState(rs.getString(6));  
            setZip(rs.getString(7));
            setEmail(rs.getString(8));
            setInsco(rs.getString(9));
            setPassword(rs.getString(10)); 
            p_appointment.selectDB(id);
            Chiropractor chiro  = new Chiropractor();
            String chiro_id =  p_appointment.getChiropractorId();
            chiro.selectDB(chiro_id);
            chiro.display();
            con.close();
            System.out.println("=========================================================");
        }
        catch(Exception e){
            System.out.println(e);
        } 
    }//end selectDB()   
    // ====================== insert() =========================
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * insertDB() add patient data and information into the Database 
    *************************************************************************/ 
    public void insertDB(String p,String f, String l, String a, String c , String s, String z , String e , String ins, String pass){
        // Updating all field in the Patient object.
        this.patientId = p;
        this.firstName = f;
        this.lastName = l;
        this.address = a;
        this.city = c;
        this.state = s;
        this.zip = z;
        this.email = e;
        this.insCo = ins;
        this.password = pass;
        // Connection with the database.
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            System.out.println("Database connected"); 
            Statement stmt = con1.createStatement();
            String sql = "INSERT INTO Patients (patient_id,firstName,lastName,address,city,state,zip,email,ins_co,password) VALUES"
                    + "('"+getPatientId()+"',"
                    + ""+"'"+getFirstName()+"',"
                    + ""+"'"+getLastName()+"',"
                    + ""+"'"+getAddress()+"',"
                    + ""+"'"+getCity()+"',"
                    + ""+"'"+getState()+"',"
                    + ""+"'"+getZip()+"',"
                    + ""+"'"+getEmail()+"',"
                    + ""+"'"+getInsco()+"',"
                    + ""+getPassword()+")";     
            System.out.println(sql);
            int n = stmt.executeUpdate(sql);
            if (n==1)
                System.out.println("INSERT was successful");
            else
                System.out.println("INSERT failed");
            con1.close();
        }
        catch(Exception ex){
                 ex.printStackTrace();;
        }
    }
    // ====================== update() =========================
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
    //============ Testing ==========================   
     Patient p3 = new Patient();
     p3.selectDB("P201");
     p3.display();
     
     String name =  p3.findPatientName();
     System.out.println(name);
    }
}// end of class
