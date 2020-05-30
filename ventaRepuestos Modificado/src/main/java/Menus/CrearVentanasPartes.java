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
    AsociarParteProvedor ventanaAsociarParteProvedor;
    ActualizarPrecioParte actualizarPreciosParte;
    AsociarVehiculoParte ventanaAsociarParteVehiculo;
    

    public CrearVentanasPartes() {
        this.ventanaIP = new InsertarParte();
        this.ventanaBP = new BorrarParte();
        this.ventanaLP = new ListarPartes();
        this.ventanaAsociarParteProvedor = new AsociarParteProvedor();
        this.actualizarPreciosParte = new ActualizarPrecioParte();
        this.ventanaAsociarParteVehiculo = new AsociarVehiculoParte();
    }

    public AsociarVehiculoParte getVentanaAsociarParteVehiculo() {
        return ventanaAsociarParteVehiculo;
    }

    public void setVentanaAsociarParteVehiculo(AsociarVehiculoParte ventanaAsociarParteVehiculo) {
        this.ventanaAsociarParteVehiculo = ventanaAsociarParteVehiculo;
    }

    public ActualizarPrecioParte getActualizarPreciosParte() {
        return actualizarPreciosParte;
    }

    public void setActualizarPreciosParte(ActualizarPrecioParte actualizarPreciosParte) {
        this.actualizarPreciosParte = actualizarPreciosParte;
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

    public AsociarParteProvedor getVentanaAsociarParteProvedor() {
        return ventanaAsociarParteProvedor;
    }

    public void setVentanaAsociarParteProvedor(AsociarParteProvedor ventanaAsociarParteProvedor) {
        this.ventanaAsociarParteProvedor = ventanaAsociarParteProvedor;
    }
    
    
    
    
}
