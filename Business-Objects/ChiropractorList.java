/********************************************************************************
 * CIST2931 Advanced Systems Project 
 * ChiropractorList Business Object
 * Author: Pradsley D'Haiti
 * Date: 10/20/2022
 *********************************************************************************/
package Business_Object;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
/*****************************************************************************************************
 * The ChiropractorList Class is used to hold multiple Chiropractor Class 
 *****************************************************************************************************/
public class ChiropractorList {
    
    public ArrayList<Chiropractor> chiroList = new ArrayList<Chiropractor>();
    public int count;
    public int table_id;
    
    public ChiropractorList(){
        chiroList = new ArrayList<Chiropractor>();
        count = 0;
    }
  //======================== addChiro(parameter) ============================================
 /*****************************************************************************************************************************
 * addChiro(parameter) add a chiropractor object to the chiroList Collection.
 ******************************************************************************************************************************/    
    public void addChiro(Chiropractor ch){
        chiroList.add(ch);
        count++;
    }// end of addChiro()
   //======================== displayChiropract() ============================================
 /*****************************************************************************************************************************
 * The display method return the values of all the Chiropractor class fields for the ChiropractorSchedule table (DB).
 ******************************************************************************************************************************/    
    public void displayChiropract(){
        
        for(int i = 0; i< chiroList.size(); i++){
            chiroList.get(i).DisplayChiropractorSchedule();
            System.out.println("==========================");
            count++;
        }
    }//end of displayChiropract()
   //=============================== getSingleChiropractor(parameter) ============================================
 /*****************************************************************************************************************************
 * The getSingleChiropractor method return a single Chiropractor object from the getAllchiropractor method.
 ******************************************************************************************************************************/       
    public Chiropractor getSingleChiropractor(int id){
        
        table_id = id;
        Chiropractor chiro = new Chiropractor();
        
      Connection con1 = null;
         Statement stmt = null;
         ResultSet rs = null;
         
        try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
                System.out.println("Database connected");
                stmt = con1.createStatement();
                String sql = "SELECT * FROM ChiropractorSchedule WHERE id = "+table_id+"";
                rs = stmt.executeQuery(sql);
                
                rs.next();
                    int theId = rs.getInt(1);
                    String chiroprac_id = rs.getString(2);
                    String firstName = rs.getString(3);
                    String lastName = rs.getString(4);
                    String email    = rs.getString(5);
                    String office_num =rs.getString(6);
                    String schedule = rs.getString(7);
                    String adminId = rs.getString(8);
                    String password = rs.getString(9);
                
                   // Chiropractor chiro = new Chiropractor(chiroprac_id,firstName,lastName,email,office_num,adminId,password);
                   chiro.setId(theId);
                   chiro.setchiroprac_id(chiroprac_id);
                   chiro.setfirstName(firstName);
                   chiro.setlastName(lastName);
                   chiro.setemail(email);
                   chiro.setoffice_num(office_num);
                   chiro.setadmin_id(adminId);
                   chiro.setPassword(password);
                    chiro.setSchedule(schedule);
                    chiro.setId(id);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        return chiro;
    }//end of getSingleChiropractor
       //=============================== updateSingleChiroctorFromAList(parameter) ============================================
 /***************************************************************************************************************************************
 *  updateSingleChiroctorFromAList(parameter) update a single chiropractor object data from an the chiroList Collection 
 ***************************************************************************************************************************************/ 
     public Chiropractor updateSingleChiroctorFromAList(int chiropractorID){
            
            ChiropractorList listChiro = new ChiropractorList();
            listChiro.getAllchiropractor();
            Chiropractor chiro = new Chiropractor();
            
            //Convert the listChiro to an array to have have access to all the indexes
            Object[] indexListChiro = listChiro.chiroList.toArray();
           
            // Loop through the converted list
           for(int i = 0 ; i < indexListChiro.length ; i++){
                System.out.println( i + " "+ indexListChiro[i].toString());
                if(listChiro.chiroList.get(i).getId() != chiropractorID){
                    continue;
                }
                else{
              
                    int id = listChiro.chiroList.get(i).getId();
                    chiro = listChiro.getSingleChiropractor(id);
                    
                    chiro.setId(listChiro.chiroList.get(i).getId());
                    chiro.setchiroprac_id(listChiro.chiroList.get(i).getchiroprac_id());
                    chiro.setfirstName(listChiro.chiroList.get(i).getfirstName());
                    chiro.setlastName(listChiro.chiroList.get(i).getlastName());
                    chiro.setemail(listChiro.chiroList.get(i).getemail());
                    chiro.setoffice_num(listChiro.chiroList.get(i).getSchedule());
                    chiro.setadmin_id(listChiro.chiroList.get(i).getadmin_id());
                    chiro.setPassword(listChiro.chiroList.get(i).getPassword());           
          }
       }
           listChiro.displayChiropract();
      return chiro;   
   }
    // ====================== getAllchiropractor() =========================
    // ++++++++++ DB Behaviors +++++++++++++
    /*****************************************************************************************
    * getAllchiropractor() gets all the chiropractors data and information from the Database 
    ******************************************************************************************/ 
    public ArrayList<Chiropractor>getAllchiropractor(){
         
         Connection con1 = null;
         Statement stmt = null;
         ResultSet rs = null;
         
        try{
                Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
            	con1 = DriverManager.getConnection("jdbc:ucanaccess://C://Users//pach3//Downloads//ChiropractorOfficeMDB.accdb/");
                System.out.println("Database connected");
                stmt = con1.createStatement();
                String sql = "SELECT * FROM ChiropractorSchedule";
                rs = stmt.executeQuery(sql);
                
                while(rs.next()){
                    
                    int id = rs.getInt(1);
                    String chiroprac_id = rs.getString(2);
                    String firstName = rs.getString(3);
                    String lastName = rs.getString(4);
                    String email    = rs.getString(5);
                    String office_num =rs.getString(6);
                    String schedule = rs.getString(7);
                    String adminId = rs.getString(8);
                    String password = rs.getString(9);
                    

                    Chiropractor chiro = new Chiropractor(chiroprac_id,firstName,lastName,email,office_num,adminId,password);
                    chiro.setSchedule(schedule);
                    chiro.setId(id);
                    chiroList.add(chiro);
                }
        }catch(Exception ex){
            ex.printStackTrace();
        } 
        finally{
            closeConnection(con1,stmt,rs);
        }
        
         return chiroList;
    }
    // close the connection, resultset and the statement from the DB
       private void closeConnection(Connection con1, Statement stmt, ResultSet rs){
       
       try{
           if(con1 != null){
               con1.close();
           }
           if(stmt != null){
               stmt.close();
           }
           if(rs != null){
               rs.close();
           }
       }catch(Exception ex){
           ex.printStackTrace();
       }
   }     
   public static void main(String[] arg){
    // ------------ Testing -----------
       ChiropractorList chiroList = new ChiropractorList();
       Chiropractor chiro = chiroList.updateSingleChiroctorFromAList(4);
       chiro.setfirstName("Prads");
       chiro.UpdateChiropractorSchedule(chiro.getId());
       chiroList.displayChiropract();
       
   }
}
