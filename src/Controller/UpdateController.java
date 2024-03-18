/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Model.DatabaseOperations;
import java.sql.*;

/**
 *
 * @author Adithya
 */
public class UpdateController {
    ResultSet rs;
    public ResultSet updateBrandsCmb(){
    
        try{
           
            rs = DatabaseOperations.getBrandTableNames();
            
            }
        catch(Exception ex){
            ex.printStackTrace();
        }
        
        return rs;
    }  
    
    public ResultSet updateItemCmb(String brandTbl){
    
        ResultSet rs = DatabaseOperations.getItems(brandTbl);
    
        return rs;                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    }
    //@override
    public ResultSet updateItemCmb(String brandTbl,String category){
    
        ResultSet rs = DatabaseOperations.getItems(brandTbl,category);
    
        return rs;                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    }
}
