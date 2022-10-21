/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * Appointments Business Object
 * Author: Wilcox, Devares, Pradsley D'Haiti
 * Date: 10/20/2022
 *********************************************************************************/
package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/********************************************************************************************
 * The Procedure Class is used to hold a procedure's data or information including the cost
 ********************************************************************************************/
public class Procedure {
    // ======================  Properties  =============================
    private String procCode, procName, procDesc;
    private Double cost;

    // ======================== Constructors ============================
    public Procedure() {
        procCode="";
        procName="";
        procDesc="";
        cost=0.0;
    }
    public Procedure(String pc, String pn, String pd, Double c) {
        procCode=pc;
        procName=pn;
        procDesc=pd;
        cost=c;
    }
    // ==================================  Behaviors ===============================
    public void setProcCode(String pc){procCode=pc;}
    public String getProcCode() {return procCode; }
    
    public void setProcName(String pn){procName=pn;}
    public String getProcName() {return procName; }
    
    public void setProcDesc(String pd){procDesc=pd;}
    public String getProcDesc() {return procDesc; }
    
    public void setCost(Double c){cost=c;}
    public Double getCost() {return cost; }
    
    public void display() {
        System.out.println("\t\tProcedure Code = "+getProcCode());
        System.out.println("\t\tProcedure Name = "+getProcName());
        System.out.println("\t\tProcedure Description = "+getProcDesc());
        System.out.println("\t\tProcedure Cost = "+getCost()); 
    }
    
    public String toString() {
        return "~Procedure~\nName: " + getProcName() + "\nDescription: " + getProcDesc() + "\nPrice: $" + getCost();
    }
    
    // ++++++++++ DB Behaviors +++++++++++++
    /************************************************************************
    * selectDB() gets a procedure from the Database 
    *************************************************************************/
    public void selectDB(String pc){
        procCode = pc;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            
            Statement stmt = con.createStatement();
            String sql = "Select procName, description, proc_cost from Procedures where procCode='"+getProcCode()+"'";
            System.out.println(sql);
            ResultSet rs = stmt.executeQuery(sql);
            rs.next();
            setProcName(rs.getString(1));
            setProcDesc(rs.getString(2));
            setCost(Double.parseDouble(rs.getString(3)));
        }
        catch(Exception e){
            System.out.println(e);
        }
    }//end selectDB()
    /************************************************************************
    * insertDB() adds a procedure to the Database
    *************************************************************************/
    public void insertDB(String pc, String pn, String pd, Double c){
        procCode=pc;
        procName=pn;
        procDesc=pd;
        cost=c;
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");           
            Statement stmt = con.createStatement();
            String sql = "Insert into Procedures values('"+getProcCode()+"',"+
                                                      "'"+getProcName()+"',"+ 
                                                      "'"+getProcDesc()+"',"+ 
                                                      "'"+getCost()+"')"; 
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
    * updateDB() updates a procedure in the Database 
    *************************************************************************/
    public void updateDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            
            Statement stmt = con.createStatement();
            String sql = "update Procedures set procName = '"+getProcName() + "',"+ 
                                            " procDesc ='"+getProcDesc()+"',"+
                                            " cost ='"+getCost()+"'"+
                                            " where procCode='"+getProcCode()+"'";
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
    * deleteDB() deletes a procedure from the Database 
    *************************************************************************/
    public void deleteDB(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            Connection con = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
            
            Statement stmt = con.createStatement();
            String sql = "Delete from Procedures where procCode='"+getProcCode()+"'";
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
    
    // Testing class
    public static void main(String[] arg){
        Procedure pc = new Procedure();
        pc.selectDB("PR301");
        pc.display();
    }
}
