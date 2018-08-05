/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import vista.ViewDiagnostico;

/**
 *
 * @author Gustavo Davila
 */
public class Sql extends Conexion{ 
    
    public void cargarComboBox(ViewDiagnostico view_diagnostico){
        
        Connection con = getConexion(); 
        String sql = "SELECT * FROM sintoma";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
             // limpia todo los items que estaban antes
            //view_paquete.CB_listapaquetes.removeAllItems();
            
            while(rs.next()){
                view_diagnostico.cb_sintoma1.addItem(rs.getString("nombre_sintoma"));
                view_diagnostico.cb_sintoma2.addItem(rs.getString("nombre_sintoma"));
                view_diagnostico.cb_sintoma3.addItem(rs.getString("nombre_sintoma")); 
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                JOptionPane.showMessageDialog(null, ea.getMessage(),"error",JOptionPane.OK_OPTION);
            }       
    }
    
    // descripcion del diagnostico
    public String descripcionDiagnostico(String sin1,String sin2,String sin3){
        
        int id= 0;
        int id_aux= 0;
        int contador = 0; 
        String name_enf = "";
        
        Connection con = getConexion();
        //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
        String sql = "SELECT * FROM sintoma WHERE nombre_sintoma = '"+sin1+"' OR"+
                " nombre_sintoma = '"+sin2+"' OR nombre_sintoma = '"+sin3+"' ";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            
            while(rs.next()){
               id = rs.getInt("id_enf");
               if(id == id_aux){
                   contador = contador +1;  
               }
               id_aux = id;     
            }
            if(contador == 2){
               name_enf = this.getEnfermedad(id);
            }
            //else if(contador == 1){
                
            //}
            else{
                name_enf = "Enfermedad no encontrada"; 
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                    System.out.println("Exception");   

            }
            
        return name_enf;  
    } 
    
    public String getEnfermedad(int id_enfermedad){
        
        String descrip_enf = ""; 
        //int id_enf;
        Connection con = getConexion();

        String sql = "SELECT * FROM enfermedad WHERE id_enf = '"+id_enfermedad+"' "; 
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
               //id_enf = rs.getInt("id_enf");
               descrip_enf = "Enfermedad: "+rs.getString("nombre_enf") +
                       "\nCorresponde a la "+  rs.getString("nombre_triada");
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                    System.out.println("Exception");   

            }
            
    return descrip_enf;  
    }
    /*
    
    // metodo para verificar 
    public boolean ConsultaAdministrador(){
        Connection con = getConexion();
        //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
        String sql = "SELECT * FROM empleado WHERE id_tipo = 0";
                  
        try{
            
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            if(rs.next()){
                rs.close();
                st.close();
                con.close();
                return true;
            }else{
                rs.close();
                st.close();
                con.close();
                return false;
            }
            

            } catch (SQLException ea) {
                System.out.println("No hay administradores en la base de datos");
                  return false; 
            }       
    }
    
    // metodo para consultar inicio 
    public int ConsultaInicio(String Codigo, String Clave){
        int tipo = 3; 
        Connection con = getConexion();
        //SELECT * FROM `empleados` WHERE id_emp = "1088345137" AND password = "cafeoscuro"; 
        String sql = "SELECT * FROM empleado WHERE id_admin = '"+Codigo+"' AND"
         + " password = '"+Clave+"'";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while(rs.next()){
                 tipo = rs.getInt("id_tipo");
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                    System.out.println("Exception");
                    tipo = 3;    

            }
            
    return tipo;  
    }
    
    // metodo para insertar un nuevo empleado
    public boolean AgregarAdminstrador(Administrador Admin){
       
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO empleado(id_admin,nombre,password,"
               + "id_tipo) VALUES(?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, Admin.getCodigo());
           ps.setString(2, Admin.getUsuario());
           ps.setString(3, Admin.getClave());
           ps.setInt(4, 0);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
          
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "La cedula ya esta resgistrada","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
       
   }
    
    public boolean AgregarVendedor(Vendedor vendedor){
       
       // para insertar en mysql vamos a prepara la consulta 
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO empleado(id_admin,nombre,password,"
               + "id_tipo) VALUES(?,?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, vendedor.getCodigo());
           ps.setString(2, vendedor.getUsuario());
           ps.setString(3, vendedor.getClave());
           ps.setInt(4, 1);
           ps.execute();
           
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {
          
           if(ex.getErrorCode()==1062){
               JOptionPane.showMessageDialog(null, "La cedula ya esta resgistrada","error al registrar",JOptionPane.OK_OPTION);
           }
           else{
               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
       }
       
   }
    


    public boolean agregarPaquete(String nombre_paquete, String destino,double precio){

        
       PreparedStatement ps = null; 
       Connection con = getConexion();   
       
       String sql= "INSERT INTO paquete(nombre,destino,precio) VALUES(?,?,?)"; 
       
       try {
           ps = con.prepareStatement(sql);
           ps.setString(1, nombre_paquete);
           ps.setString(2, destino);
           ps.setDouble(3, precio);
           ps.execute();
           ps.close();
           con.close();
           return true; 
           
       } catch (SQLException ex) {

               JOptionPane.showMessageDialog(null, ex.getMessage(),"error",JOptionPane.OK_OPTION); 
           }
           
           return false; 
    } 
    
    public void cargarComboBox(ViewCrearPaquete view_paquete){
        
        Connection con = getConexion(); 
        String sql = "SELECT * FROM paquete";
                  
        try{
            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);
            
             // limpia todo los items que estaban antes
            //view_paquete.CB_listapaquetes.removeAllItems();
            
            while(rs.next()){
                view_paquete.CB_listapaquetes.addItem(rs.getString("nombre"));
            }
            
            rs.close();
            st.close();
            con.close();
            
           
            } catch (SQLException ea) {
                    
                JOptionPane.showMessageDialog(null, ea.getMessage(),"error",JOptionPane.OK_OPTION);
            }       
    }
    
*/
    
}
