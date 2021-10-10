package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class Conexion {
    Connection conexion; 
    public void conexion(){
        try {
            String ruta=System.getProperty("user.dir");
            conexion = DriverManager.getConnection("jdbc:sqlite:"+ruta+"\\DBSX01.db");
            
           //conexion = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\oscar_000\\Documents\\SQL\\DBSX01.db");
            if(conexion!=null){
           
            }
            
        } catch (SQLException e) {
            System.err.println("Conexion no establecida "+e.getMessage());
            JOptionPane.showMessageDialog(null,"ERROR EN CONEXIÓN A LA BASE DE DATOS . . .","ERROR",JOptionPane.ERROR_MESSAGE);
        }
    }
    public void cerrarConexion(){
        try {
            conexion.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    
}