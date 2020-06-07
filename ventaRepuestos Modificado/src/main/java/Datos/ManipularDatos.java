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
    PreparedStatement ps2;
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
            String SQL = "SELECT * FROM " +tabla+" WHERE idC IS NOT NULL;";
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
    public int borrarParte(String nombreE, String marca, String nombreF){
        String eliminar = "DELETE FROM parte WHERE nombreE='"+nombreE+"' and marca='"+marca+"' and nombreF='"+nombreF+"';";
        String consulta = "SELECT * FROM parte WHERE nombreE = '"+nombreE+"' and marca = '"+marca+"' and nombreF ='"+nombreF+"';";
        boolean verificador=verificarParte(nombreE, marca, nombreF);
        try
        {   
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            rs = stmt2.executeQuery(consulta);
            int cont = 0;
            while(rs.next())
                cont++;
            if(cont==0)
                return 1;
            
            if(verificador==true)
                stmt.executeQuery(eliminar); 
            else
                return 2;
            return 0;
            
        }
        catch(Exception e)
        {     
            return 2;
        }
    }
    public boolean verificarParte(String nombreE,String marca,String nombreF){
        String SQL="";     
        Statement stmt;             
        try{ 
            con= enlace.Entrar();
            stmt = con.createStatement();           
            SQL = "SELECT * FROM relacionRegistroPartes WHERE nombreEP='"+nombreE+"' AND marcaP='"+marca+"' AND nombreFP='"+nombreF+"';";        
            rs = stmt.executeQuery(SQL);
            if (rs.next()) {                                
                JOptionPane.showMessageDialog(null,"Parte asociada a orden");
                return false;
            }
            else{
                
                return true;
            }
        }
        catch(Exception e){
            
        }                     
      return true;
    }
    public void resetearIdentity(String tabla){        
        String identity = "DBCC CHECKIDENT ("+tabla+", RESEED,0)";
        try
        {
            ps = con.prepareCall(identity);
            ps.executeUpdate();                                    
        }
        catch(Exception e){}
        }
    public void leerDatosEspecificos(String tabla,String  atributo,String dato){
        
        try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM " + tabla+" WHERE "+ atributo+" ="+dato+";";
            rs = stmt.executeQuery(SQL);
            
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {}          
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
            verEstadoCliente(area,rs.getString(5));
        }
        }
        catch(Exception e){ }       
    }
    public void verDatosEspecificosOrganizacion(JTextArea area){
        this.areaTexto=area;
        try{
        while (rs.next()) {
            fila+="Cédula Jurídica: "+ rs.getString(1)+"\nNombre: "+ rs.getString(2)+"\nDirección: "+ rs.getString(3)+"\nCiudad: "+ 
            rs.getString(4)+"\n\nDatos de contacto\n\n"+"Nombre: "+ rs.getString(5)+"\nCargo: "+ rs.getString(6)+
            "\nTeléfono: "+  rs.getString(7);
            fila+="\n\nID Cliente: "+ rs.getString(8);
            verEstadoCliente(area,rs.getString(8));
            areaTexto.setText(fila);            
        }
        }
        catch(Exception e){}       
    }
    public void verEstadoCliente(JTextArea area,String id){
        this.areaTexto=area;
         try{
            con= enlace.Entrar();
            Statement stmt = con.createStatement();
            String SQL = "SELECT estado FROM cliente WHERE id="+id+";";
            rs = stmt.executeQuery(SQL);
            while (rs.next()) {
                fila+="\tEstado: "+ rs.getString(1)+"\n";             
                areaTexto.setText(fila);  
            }           
        }
        catch (SQLException e) {}    
       
        
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
    //Aca implementamos las ultimas consultas DML vistas en clase 
    public void cambiarEstadoCliente(String estado,String llave,String llaveD){
    String reemplazo="";
        if(llave=="cedula"){
            if(estado.length()!=0){         
                reemplazo="UPDATE cliente SET estado=? FROM persona,cliente WHERE persona.idC=cliente.id AND persona.cedula=?";
                try
                {
                    con = enlace.Entrar(); 
                    ps = con.prepareCall(reemplazo);
                    ps.setString(1, estado);
                    ps.setString(2, llaveD);
                    ps.executeUpdate();
                    enlace.Cerrar();

                }
                catch(Exception e)              
                {

                }
            }
        }
        else if(llave=="cedulaJ"){
            if(estado.length()!=0){         
                reemplazo="UPDATE cliente SET estado=? FROM organizacion,cliente WHERE organizacion.idC=cliente.id AND organizacion.cedulaJ=?";
                try
                {
                    con = enlace.Entrar(); 
                    ps = con.prepareCall(reemplazo);
                    ps.setString(1, estado);
                    ps.setString(2, llaveD);
                    ps.executeUpdate();
                    enlace.Cerrar();

                }
                catch(Exception e)              
                {

                }
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
        String insertar= "insert into cliente (estado)values ('ACTIVO')";
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
    public int agregarParte(String nombreParte,String marca,String nombreFab) throws SQLException{
        String insertar = "insert into parte(nombreE,marca,nombreF) values (?,?,?)";
        try{
           
            con = enlace.Entrar();
           Statement stmt = con.createStatement();
           String SQL = "SELECT * FROM parte WHERE nombreE ='" +nombreParte+"' and marca = '"+marca+"' and nombreF ='"+nombreFab+"';";
            
            rs = stmt.executeQuery(SQL);
            int cont = 0;
            while(rs.next())
                cont++;
            if(cont != 0)
                return 1;
            
            
           
            ps = con.prepareCall(insertar);
            ps.setString(1, nombreParte);
            ps.setString(2, marca);
            ps.setString(3, nombreFab);
            
            
            ps.executeUpdate();
            con.close();
            return 0;
            
            
            
        }
        catch(Exception e){
            System.out.println("salio mal");
            con.close();
            return 2;
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
            
            
                SQL = "SELECT nombreParte, marcaParte, fabricanteParte FROM (automovil INNER JOIN vehiculosPartes ON  codigo = codigoVehiculo AND codigo = " + array.get(0)+");"; //"SELECT * FROM vehiculosPartes;"; 
                rs = stmt.executeQuery(SQL);
                while(rs.next()){
                    
                    //if(rs.getInt(1) == array.get(0))
                    listado+=rs.getString(1) + "\t" + rs.getString(2) + " \t" + modelo + "\t" +ano + "\t" + rs.getString(3) + "\n" ;
                }
            
            return listado;
        }
        catch(SQLException ex){
            
        }
        return "";
    }
    
    public int agregarProvedor_Parte(String nombreParte,String marcaParte,String nomFabricante,String nombreProvedor,String direccionProvedor,int precioCosto,int precioParte){
        String insertar = "insert into venta(precioP,PrecioC,nombreE,direccionE,nombreEP,marcaParte,nomFab) values (?,?,?,?,?,?,?)";
        String SQL = "SELECT * FROM venta WHERE nombreE = '"+nombreProvedor+"' and direccionE = '"+direccionProvedor+"' and nombreEP = '"+nombreParte+
                "' and marcaParte = '" +marcaParte+"' and nomFab = '"+nomFabricante+"';";
        
        try {
            con = enlace.Entrar();
            Statement stmt = con.createStatement();
            rs = stmt.executeQuery(SQL);
            int cont =0 ;
            while(rs.next())
                cont++;
            if(cont != 0)
                return 1;
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
            return 0;
        } catch (SQLException ex) {
            System.out.println("Fallo  en la conexion");
            return 2;
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
    public int asociarAutosPartes(String nombreParte,String marcaParte,String nomFabricante,String modeloAutomovil,String anoAutomovil){
        con = enlace.Entrar();
            Statement stmt;
        try {
            stmt = con.createStatement();
            Statement stmt2 = con.createStatement();
            String SQL = "SELECT * FROM automovil WHERE modelo ='" + modeloAutomovil +"' AND añoF = '"+anoAutomovil+"';" ;
            
            rs = stmt.executeQuery(SQL);
            int codigoCarro = 0;
            while(rs.next())
                codigoCarro = rs.getInt(1);
            String insertar = "insert into vehiculosPartes(codigoVehiculo,nombreParte,marcaParte,fabricanteParte) values (?,?,?,?)";
            String consulta = "SELECT * FROM vehiculoSPartes WHERE codigoVehiculo = '"+ codigoCarro + "' and nombreParte ='"+nombreParte+"' and"
                    + " marcaParte = '"+marcaParte+"' and fabricanteParte = '"+nomFabricante+"';";
            rs = stmt2.executeQuery(consulta);
            int cont = 0;
            while(rs.next())
                cont++;
            if(cont!=0)
                return 1;
            ps = con.prepareCall(insertar);
            ps.setInt(1,codigoCarro);
            ps.setString(2, nombreParte);
            ps.setString(3,marcaParte);
            ps.setString(4, nomFabricante);
            ps.executeUpdate();
            con.close();
            return 0;
            
        } catch (SQLException ex) {
           return 2;
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


    
