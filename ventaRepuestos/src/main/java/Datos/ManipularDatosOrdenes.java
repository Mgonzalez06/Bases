/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Conexion.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;
import javax.swing.JTextField;

/**
 *
 * @author Liseth
 */
public class ManipularDatosOrdenes {
    Connection con;
    Conexion enlace;
    public JTextArea areaTexto;
    PreparedStatement ps;
    ResultSet rs;
    ResultSet rsDato2;
    String fila="";
    String nombreC="";
    String numeroOrden="";
    public ManipularDatosOrdenes(){
        this.con=null;
        this.enlace=new Conexion();
    }
    public void insertarOrden(String fecha,String nombreC,String IVA){
        String insertar = "insert into orden(fecha,nombreC,montoVenta,montoIVA) values (?,?,?,?)";
        this.nombreC = nombreC;
        try
        {
            con = enlace.Entrar(); 
            ps = con.prepareCall(insertar);
            ps.setString(1,fecha);
            ps.setString(2,nombreC);
            ps.setString(3,"0");
            ps.setString(4, IVA);
            if(verificarCliente(nombreC)){
                ps.executeUpdate(); 
            }
            else{
                System.out.println("No existe cliente con ese nombre");
            }
        }
        catch(Exception e)
        {
            
        }  
    }
    
    public void ultimaOrden(){
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT TOP (1) * FROM orden ORDER BY numeroC DESC";
            rs = stmt.executeQuery(SQL);      
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
        fila="";
    }
    
    public String ObtenerIDCliente() throws SQLException {
       
       String fila="";
        while (rs.next()) {
            fila+= rs.getString(1);
                
        }
      return(fila);
    }
    
    public String ObtenerOrden() throws SQLException {
       
       String fila="";
        while (rs.next()) {
            fila= rs.getString(1);
                    
        }
      numeroOrden=fila;
      return(fila);
    }
    public void verprecios(JTextField montoV,JTextField montoT){
        leerDatosEspecificos("orden", "numeroC", numeroOrden);
         try{
            while (rs.next()) {
               
                
                montoV.setText(rs.getString(3).toString());
                montoT.setText(rs.getString(5).toString());
            }
            }
        catch(Exception e){
            
        }  
 
    }
    public void realizarOrden(){
      
        String numeroCO = "";
        String idC="";
        String insertar = "insert into realizaOrden(idC,numeroCO) values (?,?)";
        try{
            ultimaOrden();
            numeroCO= ObtenerOrden();
            leerDatosEspecificos("persona","nombreC",nombreC);
            if (rs.next()) 
                idC=rs.getString(5);

            leerDatosEspecificos("organizacion","nombre", nombreC);
            if (rs.next()) 
                idC=rs.getString(8);
            con = enlace.Entrar(); 
            ps = con.prepareCall(insertar);
            ps.setString(1,idC);
            ps.setString(2,numeroCO);
            ps.executeUpdate();    
        }
        catch(Exception e){
            
        }
        
    }
    public boolean verificarCliente(String nombre){
        
        leerDatosEspecificos("persona","nombreC",nombre);
       
        try{
           
        if (rs.next()) {
            return true;           
        }
        leerDatosEspecificos("organizacion","nombre", nombre);
        if (rs.next()) {
            return true;           
        }
        }
        catch(Exception e){       
        }   
        return false;
    }
    
    public void borrarDatosTablas(String tabla){
        fila="";
        String eliminar = "DELETE FROM "+ tabla;
        try
        {
            ps = con.prepareCall(eliminar);
            ps.executeUpdate();
            
                        
        }
        catch(Exception e)
        {
            
        }
    }
     public void leerDatosEspecificos(String tabla,String  atributo,String dato)
     {
        
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM " +tabla+" WHERE "+ atributo+" ='"+dato+"';";
            rs = stmt.executeQuery(SQL);
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }      
         
    }
     public void leerDatosEspecificosSegundoDato(String tabla,String  atributo,String dato)
     {
        
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM " +tabla+" WHERE "+ atributo+" ='"+dato+"';";
            rsDato2 = stmt.executeQuery(SQL);
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }      
         
    }
     
     
     public void eliminarDatosEspecificos(String tabla,String atributoL,String llave){
        if(llave.length()!=0){
         
             String reemplazo="DELETE FROM "+tabla+" WHERE "+atributoL+"=?";
           
            try
            {
                con = enlace.Entrar(); 
                ps = con.prepareCall(reemplazo);
                ps.setString(1, llave);
                ps.executeUpdate();
                enlace.Cerrar();
                    
            }
            catch(Exception e)
                
                        
            {

            }
        }
     }
     public void reemplazarDatos(String tabla,String atributoD,String dato,String atributoL,String llave)
    {
       
        if(dato.length()!=0){
         
             String reemplazo="UPDATE "+tabla+" SET "+atributoD+"=? WHERE "+atributoL+"=?";
           
            try
            {
                con = enlace.Entrar(); 
                ps = con.prepareCall(reemplazo);
                ps.setString(1, dato);
                ps.setString(2, llave);
                ps.executeUpdate();
                enlace.Cerrar();
                    
            }
            catch(Exception e)
                
                        
            {

            }
        }
    }
     public void verProveedoresPartes(JTextArea area){
        this.areaTexto=area;
        try{
            fila+="Empresas proveedoras\n\n"+"Nombre                      "+"Dirección                                             \n\n";
            while (rs.next()) {
                fila+=rs.getString(5)+"                      "+rs.getString(6)+"\n";
                areaTexto.setText(fila);
                areaTexto.setText(fila);
            }
            }
        catch(Exception e){
            
        }  
     }
     public void verDetalles(String tabla,String llave,JTextArea detalles){
     
         detalles.setText("");
         
        fila+="Detalles orden\n\n"+"Nombre parte                      "+"Cantidad             "+"Precio\n\n";
        try{
           
           
            leerDatosEspecificos("relacionRegistroPartes","numeroCO",numeroOrden);
            System.out.println(numeroOrden);
             while (rs.next())
             {
                fila+=rs.getString(2)+"                                    "; 
                leerDatosEspecificosSegundoDato("detalle","codigo",rs.getString(3));
                
            
                while (rsDato2.next()) {
                    fila+=rsDato2.getString(4)+"                               "+rsDato2.getString(2)+"\n";
                    
                    detalles.setText(fila);
                    
                    System.out.println(fila);
                }
             }
           
            }
        catch(Exception e){
            
        }  
        
          fila="";  
     }
     
}
