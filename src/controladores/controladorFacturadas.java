/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;
import modelos.modeloFacturas;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author diego
 */
public class controladorFacturadas {
    static vistas.vistaFacturadasP vistaFacturadasP;
    
    JPanel mostrarTabControlFacturadasP(String tipo, Object[][] data) {
        vistaFacturadasP = new vistas.vistaFacturadasP(tipo, data);  
        return vistaFacturadasP;
    }
    
    public void irVistaDetalleFacturadas(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleFacturadas(id);
    }
    
    public String ingresarNotaCredito(String id, String razon){
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.ingresarNotaCredito(id, razon);
    }
    
    public String[] obtenerOtsPorIdNC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String[] data;
        data = nc.obtenerOtsPorIdNC(id);
        return data;
    }
    
    public String[] obtenerValoresNC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String valores[];
        valores = nc.obtenerValoresNC(id);
        return valores;
    }
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(6);
        tabs.insertTab("Facturas", null, miControlador.crearControladorFacturadasP(), null, 6);
        tabs.setSelectedIndex(6);
    }
}
