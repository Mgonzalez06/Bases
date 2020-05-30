/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;
import Ordenes.*;

/**
 *
 * @author Liseth
 */
public class CrearVentanasOrdenes {
    InsertarOrden ventanaIO;
    LocalizarProveedores ventanaLP;
    InsertarParteOrden ventanaIP;

    public CrearVentanasOrdenes() {
        this.ventanaIO = new InsertarOrden();
        this.ventanaLP = new LocalizarProveedores();
        this.ventanaIP = new InsertarParteOrden();
        this.ventanaIO.ventanaP=ventanaIP;
    }

    public InsertarOrden getVentanaIO() {
        return ventanaIO;
    }

    public LocalizarProveedores getVentanaLP() {
        return ventanaLP;
    }

    public void setVentanaIO(InsertarOrden ventanaIO) {
        this.ventanaIO = ventanaIO;
    }

    public InsertarParteOrden getVentanaIP() {
        return ventanaIP;
    }
    
    public void setVentanaLP(LocalizarProveedores ventanaLP) {
        this.ventanaLP = ventanaLP;
    }

    public void setVentanaIP(InsertarParteOrden ventanaIP) {
        this.ventanaIP = ventanaIP;
    }
    
    
    
}
