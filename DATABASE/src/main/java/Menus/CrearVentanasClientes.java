package Menus;


import Organizaciones.InsertarOrganización;
import Organizaciones.ModificarOrganización;
import Organizaciones.SuspenderOrganización;
import Personas.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Liseth
 */
public class CrearVentanasClientes {
    InsertarPersona ventanaIP;
    ModificarPersona ventanaMP;
    SuspenderPersona ventanaSP;
    ListarPersonas ventanaLP;
    InsertarOrganización ventanaIO;
    ModificarOrganización ventanaMO;
    SuspenderOrganización ventanaSO;
    

    public CrearVentanasClientes() {
        this.ventanaIP=new InsertarPersona();
        this.ventanaMP= new ModificarPersona();
        this.ventanaSP=  new SuspenderPersona();
        this.ventanaLP= new ListarPersonas();
        this.ventanaIO = new InsertarOrganización();
        this.ventanaMO = new ModificarOrganización();
        this.ventanaSO = new SuspenderOrganización();
    }

    public InsertarPersona getVentanaIP() {
        return ventanaIP;
    }

    public ModificarPersona getVentanaMP() {
        return ventanaMP;
    }

    public SuspenderPersona getVentanaSP() {
        return ventanaSP;
    }

    public ListarPersonas getVentanaLP() {
        return ventanaLP;
    }

    public InsertarOrganización getVentanaIO() {
        return ventanaIO;
    }

    public ModificarOrganización getVentanaMO() {
        return ventanaMO;
    }

    public SuspenderOrganización getVentanaSO() {
        return ventanaSO;
    }

    public void setVentanaIP(InsertarPersona ventanaIP) {
        this.ventanaIP = ventanaIP;
    }

    public void setVentanaMP(ModificarPersona ventanaMP) {
        this.ventanaMP = ventanaMP;
    }

    public void setVentanaSP(SuspenderPersona ventanaSP) {
        this.ventanaSP = ventanaSP;
    }

    public void setVentanaLP(ListarPersonas ventanaLP) {
        this.ventanaLP = ventanaLP;
    }

    public void setVentanaIO(InsertarOrganización ventanaIO) {
        this.ventanaIO = ventanaIO;
    }

    public void setVentanaMO(ModificarOrganización ventanaMO) {
        this.ventanaMO = ventanaMO;
    }

    public void setVentanaSO(SuspenderOrganización ventanaSO) {
        this.ventanaSO = ventanaSO;
    }
    
}
