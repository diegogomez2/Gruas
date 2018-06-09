/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Diego
 */public class controladorOts {
    static vistas.vistaOtsP vistaOtsP;
    
    JPanel mostrarTabControlOts(String tipo, Object[][] data) throws ParseException {
        vistaOtsP = new vistas.vistaOtsP(tipo, data);  
        return vistaOtsP;
    }

    public void irVistaIngresarOT(String[] data, Object[] ciudades) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarOT(data, ciudades);
    }
    
    public String getIdFactura(String id){
        String idFact;
        modelos.modeloOts ots = new modelos.modeloOts();
        idFact = ots.getIdFactura(id);
        return idFact;
    }

    public void crearControladorPrincipal(JTabbedPane tabs) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(4);
        tabs.remove(4);
        tabs.remove(4);
        tabs.remove(4);
        tabs.insertTab("OTs", null, miControlador.crearControladorOtsP(), null, 4);
        tabs.insertTab("A facturar", null, miControlador.crearControladorFacturasP(), null, 5);
        tabs.insertTab("Facturadas", null, miControlador.crearControladorFacturadasP(), null, 6);
        tabs.insertTab("Histórico", null, miControlador.crearControladorHistoricoP(), null, 7);
        tabs.setSelectedIndex(4);
    }
    
    public String ingresarFactura(String idOt){
        String respuesta = "";
        modelos.modeloOts ots = new modelos.modeloOts();
        respuesta = ots.ingresarFactura(idOt);
        return respuesta;
    }
    
    public String anularFactura(String idOt){
        String respuesta = "";
        modelos.modeloOts ots = new modelos.modeloOts();
        respuesta = ots.anularFactura(idOt);
        return respuesta;
    }
    
    public String eliminarOt(String idOt){
        String respuesta = "";
        modelos.modeloOts ots = new modelos.modeloOts();
        respuesta = ots.eliminarOt(idOt);
        return respuesta;
    }
    
    public String quitarFactura(String idOt){
        String respuesta = "";
        modelos.modeloOts ots = new modelos.modeloOts();
        respuesta = ots.quitarFactura(idOt);
        return respuesta;
    }
    
    public void irVistaDetalleOts(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleOts(id);
    }
}
