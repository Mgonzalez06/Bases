/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Liseth
 */
public class ManipularClientes {
    Connection con;
    ResultSet rs;
    public ManipularClientes(){
        this.con=null;
        this.rs=null;
    }
    public void cliente(Conexion enlace){
      
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT TOP (1) * FROM cliente ORDER BY id DESC";
            rs = stmt.executeQuery(SQL);      
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public String ObtenerIDCliente() throws SQLException {
       
       String fila="";
        while (rs.next()) {
            fila+= rs.getString(1);
            System.out.println(fila);            
        }
      return(fila);
    }
    
}
