/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;
import controladores.*;
import modelos.modeloFacturas;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author diego
 */
public class controladorCobranzas {
    static vistas2.vistaCobranzasP vistaCobranzasP;
    
    public JPanel mostrarTabControlCobranzasP(String tipo, Object[][] data) {
        vistaCobranzasP = new vistas2.vistaCobranzasP(tipo, data);  
        return vistaCobranzasP;
    }
    
    public void irVistaGestionCobranza(String id, String tipo) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorGestionCobranza(id, tipo);
    }
    
    public void irVistaVerGestion(String id, String tipo) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorVerGestion(id, tipo);
    }
    
    public void irVistaGestionPago(String fol, String tipo) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorGestionPago(fol, tipo);
    }

//    
//    public void irVistaDetalleFacturadas(String id, String tipo) throws ParseException{
//        controladorPrincipal miControlador = new controladorPrincipal();
//        miControlador.crearControladorDetalleFacturadas(id, tipo);
//    }
//    
//    public String ingresarNotaCredito(String id, String razon, String tipo){
//        controladorPrincipal miControlador = new controladorPrincipal();
//        return miControlador.ingresarNotaCredito(id, razon, tipo);
//    }
//    
//    public String[] obtenerOtsPorIdNC(String id){
//        modelos.modeloFacturas nc = new modelos.modeloFacturas();
//        String[] data;
//        data = nc.obtenerOtsPorIdNC(id);
//        return data;
//    }
//    
//    public String[] obtenerValoresNC(String id){
//        modelos.modeloFacturas nc = new modelos.modeloFacturas();
//        String valores[];
//        valores = nc.obtenerValoresNC(id);
//        return valores;
//    }
//    
//    public String[] obtenerOtsPorIdFacturada(String id){
//        modelos.modeloFacturas nc = new modelos.modeloFacturas();
//        String[] data;
//        data = nc.obtenerOtsPorIdFacturada(id);
//        return data;
//    }
//    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(0);
        tabs.insertTab("Cobranza", null, miControlador.crearControladorCobranzasP(), null, 0);
        tabs.setSelectedIndex(0);
    }
}
