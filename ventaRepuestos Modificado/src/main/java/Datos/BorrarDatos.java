/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.swing.JOptionPane;
/**
 *
 * @author Liseth
 */
public class BorrarDatos {
    Conexion enlace;
    PreparedStatement ps;
    public BorrarDatos(){
        enlace = new Conexion();
    }
    public void borrar(String tabla){
        String eliminar = "DELETE FROM "+ tabla;
        try
        {
            Connection cin = enlace.Entrar(); 
            ps = cin.prepareCall(eliminar);
            ps.executeUpdate();
            
                        
        }
        catch(Exception e)
        {
            
        }
    }
}
