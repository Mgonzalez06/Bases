package Datos;

import Conexion.Conexion;
import Menus.ManipularClientes;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;

public class ManipularDatos {
    Connection con;
    Conexion enlace;
    public JTextArea areaTexto;
    PreparedStatement ps;
    ResultSet rs;
    String fila="";
    public ManipularDatos(){
        this.con=null;
        this.enlace=new Conexion();
    }
    public void leerDatosTablas(String tabla,JTextArea area){
        this.areaTexto=area;
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM " + tabla+" WHERE idC IS NOT NULL;";
            rs = stmt.executeQuery(SQL);
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void verDatosPersona() throws SQLException {
       
        fila+="Personas"+"\n\n";
        fila+="Cédula            "+"Nombre                                                         "+
               "Dirección                                                                      "+
               "Ciudad                    "+"ID Cliente             "+"CédulaJ Organización"+"\n\n";
        while (rs.next()) {
             fila+= rs.getString(1)+"     "+ rs.getString(2)+"                    "+ rs.getString(3)+"                                       "+ 
                     rs.getString(4)+"                        "+ rs.getString(5)+"                                "+ rs.getString(6)+"\n\n";
            System.out.println(fila);  
            areaTexto.setText(fila);
            
        }
        
      
    }
    public void verDatosOrganizacion()throws SQLException{
        
        fila+="\n\nOrganizaciones\n\n";
        fila+="Cédula            "+"Nombre                                                            "+
               "Dirección                                                                      "+
               "Ciudad                    "+"ID Cliente\n\n";
        while (rs.next()) {
             fila+= rs.getString(1)+"     "+ rs.getString(2)+"                                              "+ rs.getString(3)+"                                                 "+ 
                     rs.getString(4)+"                       "+ rs.getString(8)+"\n"+"\n";
            System.out.println(fila);  
            areaTexto.setText(fila);
            
        }
        fila="";
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
    public void resetearIdentity(String tabla){
        
        String identity = "DBCC CHECKIDENT ("+tabla+", RESEED,0)";
        try
        {
            ps = con.prepareCall(identity);
            ps.executeUpdate();
            
                        
        }
        catch(Exception e)
        {
            
        }
        }
    public void leerDatosEspecificos(String tabla,String  atributo,String dato){
        
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM " + tabla+" WHERE "+ atributo+" ="+dato+";";
            rs = stmt.executeQuery(SQL);
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }      
         
    }
    
    
    public void verDatosEspecificosPersona(JTextArea area){
        this.areaTexto=area;
        try{
        while (rs.next()) {
             fila+="Cédula: "+ rs.getString(1)+"\nNombre: "+ rs.getString(2)+"\nDirección: "+ rs.getString(3)+"\nCiudad: "+ 
                     rs.getString(4)+"\n";
             if(rs.getString(6)==null){
                 fila+= "No pertenece a ninguna organización\n";
             }
             else{
                 fila+="Cédula Jurídica de la Organización a la que pertenece: "+rs.getString(6)+"\n";
             }
            System.out.println(fila);  
            areaTexto.setText(fila);
            fila+="ID cliente: "+rs.getString(5);
            leerDatosEspecificos("cliente", "id", rs.getString(5));
            verEstadoCliente(area);
        }
        }
        catch(Exception e){
            
        }  
     
    }
    public void verDatosEspecificosOrganizacion(JTextArea area){
        this.areaTexto=area;
        try{
        while (rs.next()) {
             fila+="Cédula Jurídica: "+ rs.getString(1)+"\nNombre: "+ rs.getString(2)+"\nDirección: "+ rs.getString(3)+"\nCiudad: "+ 
                     rs.getString(4)+"\n\nDatos de contacto\n\n"+"Nombre: "+ rs.getString(5)+"\nCargo: "+ rs.getString(6)+
                     "\nTeléfono: "+  rs.getString(7);
                     fila+="\n\nID Cliente: "+ rs.getString(8);
                     leerDatosEspecificos("cliente", "id", rs.getString(8));
                     verEstadoCliente(area);
             
             
          
            System.out.println(fila);
            areaTexto.setText(fila);
            
        }
        }
        catch(Exception e){
            
        }  
     
    }
    public void verEstadoCliente(JTextArea area){
        this.areaTexto=area;
        try{
        while (rs.next()) {
             fila+="\tEstado: "+ rs.getString(2)+"\n";
             
            areaTexto.setText(fila);
            
        }
        }
        catch(Exception e){
            
        }  
        
    }
    
    public void VerTelefonosPersonaEspecifica(JTextArea area){
        this.areaTexto=area;
        try{
            fila+="Telefonos\n";
        while (rs.next()) {
             fila+= rs.getString(1)+"\n";
            System.out.println(fila);  
            areaTexto.setText(fila);
            
        }
        }
        catch(Exception e){
            
        }   
        fila="";
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
    public void cambiarEstadoCliente(String tabla,String estado,String llave,String llaveD){
      
        leerDatosEspecificos(tabla,llave, llaveD);
        if(llave=="cedula"){
            try{
                while (rs.next()) {


                    reemplazarDatos("cliente","estado",estado,"id", rs.getString(5));
                }
            }
            catch(Exception e){

            }  
        }
        else if(llave=="cedulaJ"){
             try{
                while (rs.next()) {


                    reemplazarDatos("cliente","estado",estado,"id", rs.getString(8));
                }
            }
            catch(Exception e){

            }  
        }
        
    }
    public void reemplazarDatos(String tabla,String atributoD,String dato,String atributoL,String llave)
    {
       
        if(dato.length()!=0){
         
             String reemplazo="UPDATE "+tabla+" SET "+atributoD+"=? WHERE "+atributoL+"=?";
             System.out.println(reemplazo+"Entro");
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
    public void agregarCliente(){
        String insertar= "insert into cliente (estado)values ('INACTIVO')";
        try{
         con = enlace.Entrar();
         ps = con.prepareCall(insertar);
         ps.executeUpdate();
        }
        catch(Exception e){
            
        }
    }
    public void agregarPersona(String cedula,String nombre,String direccion,String ciudad,String telefono){
        agregarCliente();
        String idC="";
        ManipularClientes cliente = new ManipularClientes();
        cliente.cliente(enlace);
        leerDatosEspecificos("persona","cedula", cedula);
        String insertar = "insert into persona (cedula,nombreC,direccionE,ciudad,idC)values (?,?,?,?,?)";      
        try
        {   if(rs.next()){
                JOptionPane.showMessageDialog(null,"Ya existe un cliente asociado con esa cédula");
            }
            else{
                idC =cliente.ObtenerIDCliente();
                con = enlace.Entrar(); 
                ps = con.prepareCall(insertar);
                ps.setString(1,cedula);
                ps.setString(2,nombre);
                ps.setString(3,direccion);
                ps.setString(4,ciudad);
                ps.setString(5,idC);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Cliente registrado con éxito");
                agregarTelefonos(telefono, cedula);
            }         
        }
        catch(Exception e)
        {
            
        }
    }
    public void agregarTelefonos(String telefono,String cedula){
        if(telefono.length()!=0)
        {
            String agregar = "insert into telefonosPersona(numero,cedulaP)values (?,?)";
            try
            {   
                leerDatosEspecificos("telefonosPersona", "numero", telefono);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"El teléfono ya se encuentra asociado a otro cliente");
                }
                else{
                    con= enlace.Entrar(); 
                    ps = con.prepareCall(agregar);
                    ps.setString(1,telefono);
                    ps.setString(2,cedula);                   
                    ps.executeUpdate();                   
                }
            }
            catch(Exception e){

            }
        
        }
    }
    public void agregarOrganizacion(String cedula,String nombre,String direccion,String ciudad,
        String nombreCont,String telefonoCont,String cargoC){
        agregarCliente();
        String idC="";
        ManipularClientes cliente = new ManipularClientes();
        cliente.cliente(enlace);
        String insertar = "insert into organizacion (cedulaJ,nombre,direccionE,ciudad,nombreCC,telefonoC,cargoC,idC)values (?,?,?,?,?,?,?,?)";
        try{

            idC =cliente.ObtenerIDCliente();
            leerDatosEspecificos("organizacion","cedulaJ", cedula);
            if(rs.next()){
                JOptionPane.showMessageDialog(null,"Ya existe un cliente asociado con esa cédula jurídica");
            }
            else{
                con = enlace.Entrar();
                ps = con.prepareCall(insertar);
                ps.setString(1,cedula);
                ps.setString(2,nombre);
                ps.setString(3,direccion);
                ps.setString(4,ciudad);
                ps.setString(5,nombreCont);
                ps.setString(6,telefonoCont);
                ps.setString(7,cargoC);
                ps.setString(8, idC);
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Cliente registrado con éxito");
            }
            
        }
        catch(Exception e){
            
        }
    }
    public boolean agregarParte(String nombreParte,String marca,String nombreFab) throws SQLException{
        String insertar = "insert into parte(nombreE,marca,nombreF) values (?,?,?)";
        try{
           
            con = enlace.Entrar();
          // Statement stmt = con.createStatement();
          //  String SQL = "SELECT * FROM automovil WHERE modelo ='" +modelo+"';";
            
          //  rs = stmt.executeQuery(SQL);
            
            
           
            ps = con.prepareCall(insertar);
            ps.setString(1, nombreParte);
            ps.setString(2, marca);
            ps.setString(3, nombreFab);
            
            
            ps.executeUpdate();
            con.close();
            return true;
            
            
            
        }
        catch(Exception e){
            System.out.println("salio mal");
            con.close();
            return false;
        }
    }
    public String listarPartes(String modelo, int ano){
        try{
            con = enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM automovil WHERE modelo ='" + modelo +"' AND añoF = '"+ano+"';" ;
            rs = stmt.executeQuery(SQL);
            String listado = "";
            ArrayList<Integer> array = new ArrayList<>();
            while(rs.next()){
                array.add(rs.getInt(1));
            }
            
            
                SQL = "SELECT * FROM vehiculosPartes;";
                rs = stmt.executeQuery(SQL);
                while(rs.next()){
                    System.out.println(rs.getInt(1));
                    if(rs.getInt(1) == array.get(0))
                        listado+=rs.getString(2) + "\t" + rs.getString(3) + " \t" + modelo + "\t" +ano + "\t" + rs.getString(4) + "\n" ;
                }
            
            return listado;
        }
        catch(SQLException ex){
            
        }
        return "";
    }
    
    public boolean agregarProvedor_Parte(String nombreParte,String marcaParte,String nomFabricante,String nombreProvedor,String direccionProvedor,int precioCosto,int precioParte){
        String insertar = "insert into venta(precioP,PrecioC,nombreE,direccionE,nombreEP,marcaParte,nomFab) values (?,?,?,?,?,?,?)";
        
        con = enlace.Entrar();
        try {
            ps = con.prepareCall(insertar);
            ps.setInt(1, precioParte);
            ps.setInt(2, precioCosto);
            ps.setString(3,nombreProvedor);
            ps.setString(4,direccionProvedor);
            ps.setString(5,nombreParte);
            ps.setString(6,marcaParte);
            ps.setString(7, nomFabricante);
            ps.executeUpdate();
            con.close();
            return true;
        } catch (SQLException ex) {
            System.out.println("Fallo  en la conexion");
            return false;
        }
       
    }
    
    public boolean cambiarPrecios(String nombreParte,String marcaP,String nomFab,String nombreProvedor,String direccionProvedor,int precioNuevo,boolean decision){  //DECISION = TRUE : PRECIO VENTA....DECISION = FALSE : PRECIO COSTO
        String actualizacion = "";
        if(decision)
            actualizacion = "UPDATE venta SET precioP= ? WHERE nombreE='"+nombreProvedor+"' AND direccionE ='"+direccionProvedor+"' AND nombreEP ='"+nombreParte+"' AND marcaParte = '"+marcaP +"' AND nomFab = '"+nomFab +"';";
        
        else
            actualizacion = "UPDATE venta SET precioC=? WHERE nombreE='"+nombreProvedor+"' AND direccionE ='"+direccionProvedor+"' AND nombreEP ='"+nombreParte+"' AND marcaParte = '"+marcaP +"' AND nomFab = '"+nomFab +"';";
        
        
        try {
            con = enlace.Entrar();
            ps = con.prepareCall(actualizacion);
            ps.setInt(1, precioNuevo);
            ps.executeUpdate();
            con.close();
            return true;
            
        } catch (SQLException ex) {
           return false;
        }
            
    }
    public boolean asociarAutosPartes(String nombreParte,String marcaParte,String nomFabricante,String modeloAutomovil,String anoAutomovil){
        con = enlace.Entrar();
            Statement stmt;
        try {
            stmt = con.createStatement();
            String SQL = "SELECT * FROM automovil WHERE modelo ='" + modeloAutomovil +"' AND añoF = '"+anoAutomovil+"';" ;
            rs = stmt.executeQuery(SQL);
            int codigoCarro = 0;
            while(rs.next())
                codigoCarro = rs.getInt(1);
            String insertar = "insert into vehiculosPartes(codigoVehiculo,nombreParte,marcaParte,fabricanteParte) values (?,?,?,?)";
            
            ps = con.prepareCall(insertar);
            ps.setInt(1,codigoCarro);
            ps.setString(2, nombreParte);
            ps.setString(3,marcaParte);
            ps.setString(4, nomFabricante);
            ps.executeUpdate();
            con.close();
            return true;
            
        } catch (SQLException ex) {
           return false;
        }
            
    }
    public void cerrarConexion(){
        try{
            enlace.Cerrar();
            
            
        }
        // Handle any errors that may have occurred.
        catch (Exception e) {
            e.printStackTrace();
        }      
        
    }
    }


    
