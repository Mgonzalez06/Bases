package basedatos1;

import Conexion.Conexion;
import Menus.Menu;

public class Main {
     public static void main(String args[]) {
     Conexion co = new Conexion();
     co.Entrar();
     Menu ventanaM=new Menu();
     ventanaM.setVisible(true);
    
        
     }
    
}
