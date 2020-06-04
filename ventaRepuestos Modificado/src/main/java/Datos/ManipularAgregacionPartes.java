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
import javax.swing.JTextField;

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
    int precio=0;
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
            String SQL = "SELECT TOP (1) numeroC FROM orden ORDER BY numeroC DESC";
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
    public void insertarParteEnOrden(String nombreEP,String marca,String fabricante){
        String insertar = "insert into relacionRegistroPartes (numeroCO,nombreEP,codigoD,marcaP,nombreFP)values (?,?,?,?,?)";
        ultimaOrden();
        String codigoD="";
        this.nombreEP=nombreEP;
     
         try {
            numeroOrden =ObtenerOrden();
            detalle();
            codigoD= ObtenerIDDetalle();
        } catch (SQLException ex) {
             System.out.println("Salio mal");
        }
        try
        {
            con = enlace.Entrar(); 
            ps = con.prepareCall(insertar);
            ps.setString(1,numeroOrden);
            ps.setString(2,nombreEP);
            ps.setString(3, codigoD);
            ps.setString(4, marca);
            ps.setString(5, fabricante);
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
    public boolean buscarPrecioParte(String nombreP,String nombreEP){
        String leer = "SELECT precioP,precioC"+
                " FROM venta WHERE nombreE ='"+nombreP+"' AND nombreEP='"+nombreEP+"';";
        
         try
            {   con= enlace.Entrar();
                Statement stmt = con.createStatement();           
                rs = stmt.executeQuery(leer);                           
            }
            catch(Exception e)                       
            {

            }
        try{
            if(rs.next()){
                this.precio=(Math.round(Float.valueOf(rs.getString(1))));
                System.err.println(rs.getString(1)+rs.getString(2)+"BUSCANDO EL PRECIO DE LA PARTE");
                return true;
                
            }            
        }
        catch(Exception e){
            
        }
        return false;
    }
    public void agregarDetalle(String cantidad,String nombreP,String nombreEP){
        String insertar = "insert into detalle(precio,nombreP,cantidadV) values (?,?,?)";
        this.cantidad = cantidad;
        boolean verificador =buscarPrecioParte(nombreP, nombreEP);   
        String precioNuevo=String.valueOf(((precio))*(Integer.parseInt(cantidad)));
        
        try
        {   if(verificador==true){
                con = enlace.Entrar(); 
                ps = con.prepareCall(insertar);     
                ps.setString(1,precioNuevo);
                ps.setString(2,nombreP);
                ps.setString(3, cantidad);
                ps.executeUpdate(); 
            }
        }
        catch(Exception e)
        {
            System.out.println("Salio mal");
        }  
    }
    public void detalle(){
      
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT TOP (1) codigo FROM detalle ORDER BY codigo  DESC";
            rs = stmt.executeQuery(SQL);      
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
           
        }
    }
     public String ObtenerIDDetalle() throws SQLException {
       
       String fila="";
        while (rs.next()) {
            fila+= rs.getString(1);
                    
        }
      return(fila);
    }
    
    public Double obtenerMontoVentaOrden() throws SQLException {
        String fila="";
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT montoVenta FROM orden WHERE numeroC='"+numeroOrden+"';";
            rs = stmt.executeQuery(SQL);
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }      
        while (rs.next()) {
            fila+= rs.getString(1);
                
        }
      
        return Double.parseDouble(fila);
    }
    public void cambiarMontoVentaOrden() throws SQLException{
        String precioVenta= String.valueOf(obtenerMontoVentaOrden()+ ((precio)*Integer.parseInt(cantidad)));
        reemplazarDatos("orden","montoVenta",precioVenta,"numeroC", numeroOrden);
        
    }
    public void actualizarMontoIVA(){
        leerDatosEspecificos("orden", "numeroC", numeroOrden);
        String montoIVa="";
        float modificado;
         try{
            while (rs.next()) {                             
                montoIVa= String.valueOf(rs.getString(3).toString());
                modificado = Float.parseFloat(montoIVa)* (float)0.13;
                montoIVa=String.valueOf(modificado);
                System.out.println(montoIVa + "Monto IVA");
                reemplazarDatos("orden","montoIVA",montoIVa,"numeroC", numeroOrden);
            }
         }
         catch(Exception e){
             
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
    public void leerDatosEspecificosDobles(String tabla,String llave1,String llave2,String dato1,String dato2){
        String leer = "SELECT * FROM "+tabla+" WHERE "+llave1+" ='"+dato1+"' AND "+llave2+"='"+dato2+"';";
        System.out.println(leer);
         try
            {   con= enlace.Entrar();
                Statement stmt = con.createStatement();           
                rs = stmt.executeQuery(leer);                           
            }
            catch(Exception e)
                
                        
            {

            }
    }
    
}
