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
 * @author diego
 */
public class controladorFacturadas {
    static vistas.vistaFacturadasP vistaFacturadasP;
    
    JPanel mostrarTabControlFacturadasP(String tipo, Object[][] data) throws ParseException {
        vistaFacturadasP = new vistas.vistaFacturadasP(tipo, data);  
        return vistaFacturadasP;
    }
    
    public void irVistaDetalleFacturadas(String id, String tipo) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleFacturadas(id, tipo);
    }
    
    public String ingresarNotaCredito(String id, String razon, String tipo){
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.ingresarNotaCredito(id, razon, tipo);
    }
    
    public String borrarNCDuplicada(String id){
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.borrarNCDuplicada(id);
    }
    
    public String[] obtenerOtsPorIdNC(String id, String tipo){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String[] data;
        if(tipo.compareTo("notadebito") == 0){
            data = nc.obtenerOtsPorIdNDNC(id);
        }else{
            data = nc.obtenerOtsPorIdNC(id);
        }
        return data;
    }
    
    public String[] obtenerValoresNC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String valores[];
        valores = nc.obtenerValoresNC(id);
        return valores;
    }
    
    public String[] obtenerValoresNDNC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String valores[];
        valores = nc.obtenerValoresNDNC(id);
        return valores;
    }
    
    public String[] obtenerOtsPorIdFacturada(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String[] data;
        data = nc.obtenerOtsPorIdFacturada(id);
        return data;
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(6);
        tabs.insertTab("Facturadas", null, miControlador.crearControladorFacturadasP(), null, 6);
        tabs.setSelectedIndex(6);
    }
    
    public void liberarNC(String[] ots){
        modelos.modeloFacturas jor = new modelos.modeloFacturas();
        jor.liberarNC(ots);
    }
}
