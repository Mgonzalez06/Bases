package Datos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTextArea;

public class RetrieveResultSet {
    Connection con;
    public JTextArea areaTexto;
    PreparedStatement ps;
    public RetrieveResultSet(){
        this.con=null;
    }
    public void leerDatosTablas(String tabla,JTextArea area){
        this.areaTexto=area;
        String connectionUrl = "jdbc:sqlserver://LISETHGF:1433;DatabaseName=ventaRepuestos;user=sa;"
                + "password= 012345";

        try{
            con= DriverManager.getConnection(connectionUrl);
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM " + tabla+" WHERE idC IS NOT NULL;";
            ResultSet rs = stmt.executeQuery(SQL);
            displayRow(tabla, rs);
        }
        // Handle any errors that may have occurred.
        catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void displayRow(String title,ResultSet rs) throws SQLException {
        System.out.println(title);
       String fila="Cédula            "+"Nombre                                                         "+
               "Dirección                                                         "+
               "Ciudad             "+"ID Cliente           "+"CédulaJ Organización"+"\n\n\n";
        while (rs.next()) {
             fila+= rs.getString(1)+"     "+ rs.getString(2)+"                    "+ rs.getString(3)+"                 "+ rs.getString(4)+
                    "              "+ rs.getString(5)+"                         "+ rs.getString(6)+"\n"+"\n";
            System.out.println(fila);  
            areaTexto.setText(fila);
            
        }
      
    }
    public void borrar(String tabla){
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

    
}