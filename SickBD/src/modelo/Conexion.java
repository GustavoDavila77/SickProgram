/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Gustavo Davila
 */
public class Conexion {
  
    private final String base = "sickprogram"; // Nombre de la base de datos  
    private final String user = "root";   // contraseña por defecto en wampserver
    private final String password = "";   // password por defecto en wampserver
    private final String url = "jdbc:mysql://localhost/"+base; // url del servidor
    private Connection con = null; 
    
    public Connection getConexion() {
         
        try {
            //Class.forName("org.gjt.mm.mysql.Driver");
            Class.forName("com.mysql.jdbc.Driver"); // indicamos el medio por el que nos vamos a conectar(driver)
            con = DriverManager.getConnection(url,user,password); // conectar con la BD 
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
            
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
         
         return con; // retornamos la conexion a la base de datos     
        
    }
}
