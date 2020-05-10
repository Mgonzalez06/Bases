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
    public void agregarPersona(String cedula,String nombre,String direccion,String ciudad){
        agregarCliente();
        String idC="";
        ManipularClientes cliente = new ManipularClientes();
        cliente.cliente(enlace);
        try {
            idC =cliente.ObtenerIDCliente();
        } catch (SQLException ex) {
            
        }
        String insertar = "insert into persona (cedula,nombreC,direccionE,ciudad,idC)values (?,?,?,?,?)";
        try
        {
            con = enlace.Entrar(); 
            ps = con.prepareCall(insertar);
            ps.setString(1,cedula);
            ps.setString(2,nombre);
            ps.setString(3,direccion);
            ps.setString(4,ciudad);
            ps.setString(5,idC);
            ps.executeUpdate();
                      
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
                con= enlace.Entrar(); 
                ps = con.prepareCall(agregar);
                ps.setString(1,telefono);
                ps.setString(2,cedula);                   
                ps.executeUpdate();                         
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
        try {
            idC =cliente.ObtenerIDCliente();
        } catch (SQLException ex) {
            
        }
        String insertar = "insert into organizacion (cedulaJ,nombre,direccionE,ciudad,nombreCC,telefonoC,cargoC,idC)values (?,?,?,?,?,?,?,?)";
        try{
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
            
        }
        catch(Exception e){
            
        }
    }
    public boolean agregarParte(String nombreParte,String marca,String modelo,String nombreFab) throws SQLException{
        String insertar = "insert into parte(nombreE,marca,automovilC,nombreF,codigoA) values (?,?,?,?,?)";
        try{
            int codigoCarro = 0;
            con = enlace.Entrar();
           Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM automovil WHERE modelo ='" +modelo+"';";
            
            rs = stmt.executeQuery(SQL);
            
            
           
            ps = con.prepareCall(insertar);
            ps.setString(1, nombreParte);
            ps.setString(2, marca);
            ps.setString(3, modelo);
            ps.setString(4, nombreFab);
            while(rs.next()){
                ps.setInt(5, rs.getInt(1));
            }
            
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
            String listado = null;
            ArrayList<Integer> array = new ArrayList<>();
            while(rs.next()){
                array.add(rs.getInt(1));
            }
            
            for(int i=0;i<array.size();i++){
                SQL = "SELECT * FROM parte";
                rs = stmt.executeQuery(SQL);
                while(rs.next()){
                    System.out.println(rs.getInt(5));
                    if(rs.getInt(5) == array.get(i))
                        listado = rs.getString(1) + "\t" + rs.getString(2) + " \t" + rs.getString(3) +"\t" + rs.getString(4) + "\n" ;
                }
            }
            return listado;
        }
        catch(SQLException ex){
            
        }
        return "";
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


    
