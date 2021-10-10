/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.DefaultListModel;
import javax.swing.JList;

/**
 *
 * @author Oskr
 */
public class ConsultaParaAutoCompletado extends Conexion{
    public void nombres (TextAutoCompleter textAutoCompleter){
         try{
    String SQL = "SELECT NOMBRE_CLIENTE FROM CLIENTES";         
    conexion();
    ResultSet result = null;
    PreparedStatement select = conexion.prepareStatement(SQL);
    result = select.executeQuery();
    if(result!= null){
        while (result.next()) {            
          textAutoCompleter.addItem(result.getString(1));
          
        } }    
    select.close(); 
         }
    catch(SQLException e){
     
    }
    }
}
