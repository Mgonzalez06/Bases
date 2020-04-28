/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Menus;
import Partes.*;

/**
 *
 * @author Liseth
 */
public class CrearVentanasPartes {
    InsertarParte ventanaIP;
    BorrarParte ventanaBP;
    ListarPartes ventanaLP;

    public CrearVentanasPartes() {
        this.ventanaIP = new InsertarParte();
        this.ventanaBP = new BorrarParte();
        this.ventanaLP = new ListarPartes();
    }

    public InsertarParte getVentanaIP() {
        return ventanaIP;
    }

    public BorrarParte getVentanaBP() {
        return ventanaBP;
    }

    public ListarPartes getVentanaLP() {
        return ventanaLP;
    }

    public void setVentanaIP(InsertarParte ventanaIP) {
        this.ventanaIP = ventanaIP;
    }

    public void setVentanaBP(BorrarParte ventanaBP) {
        this.ventanaBP = ventanaBP;
    }

    public void setVentanaLP(ListarPartes ventanaLP) {
        this.ventanaLP = ventanaLP;
    }
    
    
    
    
}
