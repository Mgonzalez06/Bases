/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexion;
import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;
/**
 *
 * @author Liseth
 */
public class Conexion {
    Connection cn;
    public Conexion(){
        this.cn=null;
    }
    public Connection Entrar(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            cn=DriverManager.getConnection("jdbc:sqlserver://LISETHGF:1433;DatabaseName = ventaRepuestos;user =sa"
                    + ";password= 012345");   //LISETHGF:1433  
            System.out.println("todo bien");
        }
        catch(Exception e)
        {      
            System.out.println("todo mal");
        }
        return cn;
    }
    public void Cerrar(){
        try{
            cn.close();           
        }
        catch(Exception e){
            
        }
    }
    
}
