/*
    TAREAS POR HACER
    - Cargar combobox  -- ya 
    - obtener el id de la enfermedad y el nombre del sintom) -- ya
    - hacer busqueda de diagnostico -- ya 
    - mostrar enfermedades que coinciden con 2 o 3 sintomas 
    - dar la opción para ingresar y modificar triadas 
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import modelo.Sql;
import vista.ViewDiagnostico;
/**
 *
 * @author Gustavo Davila
 */
public class Controlador implements ActionListener{
    
    ViewDiagnostico view_diagnostico = new ViewDiagnostico();
    Sql sql = new Sql();
    
    public Controlador(){
        
         
        // Botones vista diagnostico
        this.view_diagnostico.btn_diagnosticar.addActionListener(this);
        this.view_diagnostico.btn_diagnosticar.setActionCommand("Diagnostico"); 
    }
    
    public void iniciar(){
        sql.cargarComboBox(view_diagnostico); 
        view_diagnostico.setTitle("Diagnostico");
        view_diagnostico.setLocationRelativeTo(null);
        view_diagnostico.setVisible(true);
        
        
    }
    @Override
    public void actionPerformed(ActionEvent ae) {
        String command = ae.getActionCommand();
        
        switch (command){
            case "Diagnostico": 
                String sintoma1 = view_diagnostico.cb_sintoma1.getSelectedItem().toString();
                String sintoma2 = view_diagnostico.cb_sintoma2.getSelectedItem().toString();
                String sintoma3 = view_diagnostico.cb_sintoma3.getSelectedItem().toString();
                
                //System.out.println(sql.filtroSintoma(sintoma1, sintoma2, sintoma3)); 
                view_diagnostico.A_descripcion.setText(sql.descripcionDiagnostico(sintoma1, sintoma2, sintoma3)); 
                //JOptionPane.showMessageDialog(null,sql.filtroSintoma(sintoma1, sintoma2, sintoma3));
                break;
             
        } 
    }
}
