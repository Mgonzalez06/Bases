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

/**
 *
 * @author Liseth
 */
public class ManipularAgregacionPartes {
    Connection con;
    Conexion enlace;
    PreparedStatement ps;
    ResultSet rs;
    String fila="";
    String numeroOrden="";
    String precio="";
    String cantidad = "";
    String nombreEP="";
    
    public ManipularAgregacionPartes(){
        this.con=null;
        this.enlace=new Conexion();
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
    }
   
    public String ObtenerOrden() throws SQLException {
       
       String fila="";
        while (rs.next()) {
            fila+= rs.getString(1);
                    
        }
        
      return(fila);
    }
     public boolean verificarParte(String nombreEP){
        leerDatosEspecificos("parte","nombreE",nombreEP);
        try{
           
        if (rs.next()) {
            return true;           
        }
        }
        catch(Exception e){       
        }   
        return false;
             
    }
    public void insertarParteEnOrden(String nombreEP){
        String insertar = "insert into relacionRegistroPartes (numeroCO,nombreEP)values (?,?)";
        ultimaOrden();
        this.nombreEP=nombreEP;
     
         try {
            numeroOrden =ObtenerOrden();
        } catch (SQLException ex) {
            
        }
        try
        {
            con = enlace.Entrar(); 
            ps = con.prepareCall(insertar);
            ps.setString(1,numeroOrden);
            ps.setString(2,nombreEP);
            if(verificarParte(nombreEP))
                ps.executeUpdate(); 
            else{
                System.out.println("No existe parte registrada con ese nombre");
            }
        }
        catch(Exception e)
        {
            
        }  
    }
    public boolean verificarProveedor(String nombreP){
        leerDatosEspecificos("empresaProveedora","nombre",nombreP);
        try{
           
        if (rs.next()) {
            return true;           
        }
        }
        catch(Exception e){       
        }   
        return false;
        
      
    }
    public void agregarDetalle(String cantidad,String precio,String nombreP){
        String insertar = "insert into detalle(precio,nombreP,cantidadV) values (?,?,?)";
        this.precio=precio;
        this.cantidad = cantidad;
        try
        {
            con = enlace.Entrar(); 
            ps = con.prepareCall(insertar);
            ps.setString(1,precio);
            ps.setString(2,nombreP);
            ps.setString(3, cantidad);
            if(verificarProveedor(nombreP)){
                ps.executeUpdate(); 
            }
            else{
                System.out.println("No existe Proveedor con ese nombre");
            }
        }
        catch(Exception e)
        {
            
        }  
    }
    public void detalle(){
      
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT TOP (1) * FROM detalle ORDER BY codigo  DESC";
            rs = stmt.executeQuery(SQL);      
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public String ObtenerIDDetalle() throws SQLException {
       
       String fila="";
        while (rs.next()) {
            fila+= rs.getString(1);
                    
        }
      return(fila);
    }
    public void asociarDetalleParte(){
        String codigoD="";
        try{
            detalle();
            codigoD= ObtenerIDDetalle();
            reemplazarDatosDobles("relacionRegistroPartes", "codigoD",codigoD ,"numeroCO","nombreEP",numeroOrden, nombreEP);
        }
        catch(Exception e){
                    
        }          
        
        
    }
    public Double obtenerMontoVentaOrden() throws SQLException {
        leerDatosEspecificos("orden","numeroC",numeroOrden);
        String fila="";
        while (rs.next()) {
            fila+= rs.getString(3);
                
        }
      
        return Double.parseDouble(fila);
    }
    public void cambiarMontoVentaOrden() throws SQLException{
        String precioVenta= String.valueOf(obtenerMontoVentaOrden()+ (Double.parseDouble(precio)*Double.parseDouble(cantidad)));
        reemplazarDatos("orden","montoVenta",precioVenta,"numeroC", numeroOrden);
        
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
    public void reemplazarDatosDobles(String tabla,String atributoD,String dato,String atributoL1,String atributoL2,
            String llave1,String llave2)
    {
       
        if(dato.length()!=0){
         
             String reemplazo="UPDATE "+tabla+" SET "+atributoD+"=? WHERE "+atributoL1+"=?" +" AND "+
                   atributoL2+"=?";  
           
            try
            {
                con = enlace.Entrar(); 
                ps = con.prepareCall(reemplazo);
                ps.setString(1, dato);
                ps.setString(2, llave1);
                ps.setString(3,llave2);
                ps.executeUpdate();
                enlace.Cerrar();
                    
            }
            catch(Exception e)
                
                        
            {

            }
        }
    }
}