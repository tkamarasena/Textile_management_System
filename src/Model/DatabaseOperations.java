/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;
import java.sql.*;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


/**
 *
 * @author Adithya
 */
public class DatabaseOperations {
    
    private static String userLoginDB ="jdbc:mysql://localhost:3306/clothmgt_userLogin";
    private static String BrandsDB = "jdbc:mysql://localhost:3306/clothmgt_brands";
            
    public static ResultSet getLoginSet(String userName){
        
        Statement state;
        ResultSet rs = null;
            
        try{
            String usName = userName;
        
            state = DatabaseConnection.getStatementConnection(userLoginDB);
            
            
           rs = state.executeQuery("SELECT * FROM passmgt where user_name='"+usName+"'");
           String st = rs.getString("user_name");
        
        }
        catch(Exception ex){
        ex.printStackTrace();
        }
    return rs;
    }
    
    public static boolean checkPassword(String userName,String password){
        
        Statement state;
        boolean b = false;
            
        try{
            
        
            state = DatabaseConnection.getStatementConnection(userLoginDB);
            
           ResultSet rs =state.executeQuery("SELECT * FROM passmgt");
           
           while(rs.next()){
               
           if(rs.getString("password").equals(password)||rs.getString("user_name").equals(userName)){
           
               b = true;
              break;
              
               
           }
           }
           
           System.out.println(b);
        
        }
        catch(Exception ex){
        ex.printStackTrace();
        }
    return b;
    }
    
    public static void setLoginSet(String userName, String password){
        
        Statement state;
        
              try{
            
        
            state = DatabaseConnection.getStatementConnection(userLoginDB);
            
           state.execute("INSERT INTO passmgt (user_name,password) VALUES('"+userName+"','"+password+"')");
           
        
        }
        catch(Exception ex){
        ex.printStackTrace();
        }
        
    }
    
    public static void createBrandTable(String brandName){
        
        Statement state;
        ResultSet rs = null;
        
    state = DatabaseConnection.getStatementConnection(BrandsDB);
   
           try {    
                    state.execute("CREATE TABLE "+brandName+"(" +  	
                    "	itemId INT NOT NULL AUTO_INCREMENT," +
                    "  	item_name VARCHAR(30)," +
                    "  	category VARCHAR(20)," +
                    "   price VARCHAR (20),"+
                    "  	no_of_items VARCHAR (20)," +
                    "  	PRIMARY KEY(itemId));");
                    
                    
                    
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
        
    }
    
    public static void dropBrandTable(String brandName){
        
        Statement state;
        ResultSet rs = null;
        
    state = DatabaseConnection.getStatementConnection(BrandsDB);
    
           try {    
                    state.execute("DROP TABLE "+brandName+";");
                    
                    
                    
                } 
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
        
        
    }
    
    public static void insertItem(String brandTbl,String item,String category,String price,String noOfItems){
        
        Statement state;
        
        
        state = DatabaseConnection.getStatementConnection(BrandsDB);
                try {
                    state.execute("INSERT INTO "+brandTbl+"(item_name,category,price,no_of_items) VALUES('"+item+"','"+category+"','"+price+"','"+noOfItems+"')");
                } 
                catch (SQLException ex) {
                    
                    ex.printStackTrace();
                    
                }
    
        
        
    }

    public static ResultSet getItems(String brandTbl){
        
        Statement state;
        ResultSet rs = null;
   
            state = DatabaseConnection.getStatementConnection(BrandsDB);
                try {
                    rs = state.executeQuery("SELECT * FROM "+brandTbl);
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        return rs;
    }
    //@override
    public static ResultSet getItems(String brandTbl,String category){
        
        Statement state;
        ResultSet rs = null;
   
            state = DatabaseConnection.getStatementConnection(BrandsDB);
                try {
                    rs = state.executeQuery("SELECT * FROM "+brandTbl+" WHERE category='"+category+"';");
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        return rs;
    }
    
    public static void removeItem(String brandTbl,String itemName){
        
        Statement state;
        
            state = DatabaseConnection.getStatementConnection(BrandsDB);
                try {
                    state.execute("DELETE FROM "+brandTbl+" WHERE item_name='"+itemName+"' AND itemid='"+DatabaseOperations.getitemId(brandTbl, itemName)+"';");
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        
    }
    
    public static void setNoOfItems(String brandName,String itemName,String newValue){
        Statement state;
        state = DatabaseConnection.getStatementConnection(BrandsDB);
        try {
            
            state.execute("UPDATE "+brandName+" SET no_of_items = '"+newValue+"' WHERE item_name = '"+itemName+"';");
            
        } 
        catch (Exception ex2) {
                    ex2.printStackTrace();
                }
    
    }
    
    public static String getNoOfItems(String brandName,String itemName){
        ResultSet rs;
        Statement state;
        String rtn = "";
        state = DatabaseConnection.getStatementConnection(BrandsDB);
        
            
        try {
            rs = state.executeQuery("SELECT * FROM "+brandName+" WHERE item_name='"+itemName+"';");
            rs.next();
            rtn = rs.getString("no_of_items");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rtn;
    }
    public static String getPrice(String brandName,String itemName){
        ResultSet rs;
        Statement state;
        String rtn = "";
        state = DatabaseConnection.getStatementConnection(BrandsDB);
        try {
            rs = state.executeQuery("SELECT * FROM "+brandName+" WHERE item_name='"+itemName+"'");
            rs.next();
            rtn = rs.getString("price");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rtn;
    }
    
    public static String getitemId(String brandName,String itemName){
        ResultSet rs;
        Statement state;
        String rtn = "";
        state = DatabaseConnection.getStatementConnection(BrandsDB);
        try {
            rs = state.executeQuery("SELECT * FROM "+brandName+" WHERE item_name='"+itemName+"'");
            rs.next();
            rtn = rs.getString("itemId");
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseOperations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return rtn;
    }
    
    public static ResultSet getBrandTableNames(){
        
        Statement state;
        ResultSet rs = null;
   
            state = DatabaseConnection.getStatementConnection(BrandsDB);
                try {
                    rs = state.executeQuery("SELECT table_name FROM information_schema.tables WHERE table_schema = 'clothmgt_brands'");
                    
                } 
                catch (Exception ex2) {
                    ex2.printStackTrace();
                }
        return rs;
    }

}
    
   
