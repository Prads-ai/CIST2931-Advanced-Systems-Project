
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/************************************
 *
 * Admin Object
 ************************************/
public class Admin {

    String admin_id;
    String firstName;
    String lastName;
    String email;
    String phoneNum;
 

    //Blank constructor
    public Admin() {

    }

    public Admin( String fn, String ln, String em, String num, String adm) {
        firstName = fn;
        lastName = ln;
        email = em;
        phoneNum = num;
        admin_id = adm;

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

    public void display() {
        System.out.println("Your first name  is " + firstName);
        System.out.println("Your lastname is " + lastName);
        System.out.println("Your email is " + email);
        System.out.println("Your phone number is " + phoneNum);
        System.out.println("Your admin id is " + admin_id);
        

    }

    public void selectDB(String id) {

        try {
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            try (Connection con = DriverManager.getConnection("jdbc:ucanaccess://C:\\Users\\khali\\Desktop\\Advanced Projects\\ChiropractorOfficeMDB.mdb")) {
                Statement stmt = con.createStatement();
                String sql = "select * from Admin WHERE id = " + "'" + id + "'";
                ResultSet rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    
                    firstName = rs.getString(2);
                    lastName = rs.getString(3);
                    email = rs.getString(4);
                    phoneNum = rs.getString(5);
                    
                }
            }

        } catch (ClassNotFoundException | SQLException ae) {
            System.out.println(ae);
        }

   

    }
    /***************************************************************
     * 
     * This method is to update the information to the dentist table
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
        Chiropactors p2 = new Chiropactors();
        String tap = "";
        p2.selectDB(tap);
       p2.display();
        p2.updateDB();
        
    }
}
