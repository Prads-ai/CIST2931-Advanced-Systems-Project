/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * Procedure Business Object
 * Author: Wilcox, Devares
 * Date: 10/20/2022
 *********************************************************************************/
package Business_Object;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
/************************************
 * Admin Object
 ************************************/
public class Admin {

    String admin_id;
    String firstName;
    String lastName;
    String email;
    String phoneNum;
    String password;
    Chiropractor chiro;
    
    //Blank constructor
    public Admin() {
        admin_id = "";
        firstName = "";
        lastName = "";
        email  = "";
        phoneNum = "";
        password = "";
        chiro = new Chiropractor();
    }

    public Admin( String fn, String ln, String em, String num, String adm, String pass) {
        this.firstName = fn;
        this.lastName = ln;
        this.email = em;
        this.phoneNum = num;
        this.admin_id = adm;
        this.password = pass;
        this.chiro = new Chiropractor();
    }

    //Getters and Setters
    public void setAdminfname(String fn) {
        firstName = fn;
    }

    public String getAdminfname() {
        return firstName;
    }

    public void setChirolname(String ln) {
        lastName = ln;
    }

    public String getAdminlname() {
        return lastName;
    }

    public void setAdminemail(String em) {
        email = em;
    }

    public String getAdminemail() {
        return email;
    }

    public void setAdminNum(String off) {
        phoneNum = off;
    }

    public String getAdminNum() {
        return phoneNum;
    }
    
    public void setAdminid(String adm) {
        admin_id = adm;
    }
   
    
    public String getAdminid() {
        return admin_id;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }
    
   

    public void display() {
        System.out.println("Your first name  is " + firstName);
        System.out.println("Your lastname is " + lastName);
        System.out.println("Your email is " + email);
        System.out.println("Your phone number is " + phoneNum);
        System.out.println("Your admin id is " + admin_id);
        System.out.println("Your password is : " + password);
        
    }

    public void selectDB(String id) {
        admin_id = id;
        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/")) {
                Statement stmt = con.createStatement();
                String sql = "SELECT * FROM Admin WHERE admin_id = '"+ admin_id +"'";
                System.out.println(sql);
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    admin_id = rs.getString(1);
                    firstName = rs.getString(2);
                    lastName = rs.getString(3);
                    email = rs.getString(4);
                    phoneNum = rs.getString(5);
                    password = rs.getString(6);
                }
            }

        } catch (ClassNotFoundException | SQLException ae) {
            System.out.println(ae);
        }
    }
    //====================== insertDB()=========================================
    /*************************************************************************************
    * insertDB() adds a Chiropractor to the ChiropractorSchedule table of the Database.
    *************************************************************************************/
    public void insertDB(Chiropractor ch){
        
        this.chiro = ch;
        chiro.setchiroprac_id(ch.getchiroprac_id());
        chiro.setfirstName(ch.getfirstName());
        chiro.setlastName(ch.getlastName());
        chiro.setemail(ch.getemail());
        chiro.setoffice_num(ch.getoffice_num());
        chiro.setSchedule(ch.getSchedule());
        chiro.setadmin_id(ch.getadmin_id());
        chiro.setPassword(ch.getPassword());
        chiro.setSchedule(ch.getSchedule());
        
        try {    //Load DB Driver
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	Connection con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
		//Execute SQL Statement
		Statement stmt =con1.createStatement();
		String sql = "INSERT INTO ChiropractorSchedule(Chiroprac_Id,Firstname,Lastname,email,office_num,Schedule,Admin_Id,password) VALUES("
                        + "'"+chiro.getchiroprac_id()+"',"
                        + "'"+chiro.getfirstName()+"',"
                        + "'"+chiro.getlastName()+"',"
                        + "'"+chiro.getemail()+"',"
                        + "'"+chiro.getoffice_num()+"',"
                        + "'"+chiro.getSchedule()+"',"
                        + "'"+chiro.getadmin_id()+"',"
                        + "'"+chiro.getPassword()+"')";
		System.out.println(sql);
                int n = stmt.executeUpdate(sql);
                if(n == 1){
                    System.out.println("Data Succesfully inserted !!!");
                }else{
                    System.out.println("Insert Failed !!!");
                }
	
            }catch(Exception se) {
		System.out.println(se);
            	}
            System.out.println("--------------------------------------------------------");
            
    } //end InsertDB()
  

    /*****************************************************************
     * This method is to update the information to the dentist table.
     ***************************************************************/
    public void updateDB() {

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\khali\\Desktop\\Advanced Projects\\ChiropractorOfficeMDB.mdb")) {
                Statement stmt = con.createStatement();
                
                String sql = "Update Admin"  + "', firstName ='" + getAdminfname()
                        + "', lastName ='" + getAdminlname() + "', email ='" + getAdminemail() + "', office ='" + getAdminNum()
                        + "' Admin Id '" + getAdminid();
                
                System.out.println(sql);
                int n;
                n = stmt.executeUpdate(sql);
                if (n == 1) {
                    System.out.println("Update Successful!!");
                }
            }
        } catch (ClassNotFoundException | SQLException ae) {
        }
    }

    public static void main(String[] args) {
        // Testing
        Admin a1 = new Admin();
        Chiropractor hired = new Chiropractor("C590", "Denis", "Pajuta", "dp@gmail.com","3","A101","123");
        hired.setSchedule("MF-8-5PM");
        a1.insertDB(hired); 
    }
}