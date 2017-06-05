/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author diego
 */
public class controladorFacturadasOC {
    static vistas4.vistaFacturadasOCP vistaFacturadasOCP;
    
    public JPanel mostrarTabControlFacturadasOCP(String tipo, Object[][] data) {
        vistaFacturadasOCP = new vistas4.vistaFacturadasOCP(tipo, data);  
        return vistaFacturadasOCP;
    }
    
    public void irVistaDetalleFacturadasOC(String id, String tipo) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleFacturadasOC(id, tipo);
    }
    
    public String ingresarNotaCreditoOC(String id, String razon, String tipo){
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.ingresarNotaCredito(id, razon, tipo);
    }
    
    public String borrarNCDuplicadaOC(String id){
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.borrarNCDuplicada(id);
    }
    
    public String[] obtenerOcsPorIdNC(String id, String tipo){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String[] data;
        if(tipo.compareTo("notadebito") == 0){
            data = nc.obtenerOcsPorIdNDNC(id);
        }else{
            data = nc.obtenerOcsPorIdNC(id);
        }
        return data;
    }
    
    public String[] obtenerValoresNCOC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String valores[];
        valores = nc.obtenerValoresNC(id);
        return valores;
    }
    
    public String[] obtenerValoresNDNCOC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String valores[];
        valores = nc.obtenerValoresNDNC(id);
        return valores;
    }
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
        tabs.remove(3);
        tabs.insertTab("Facturadas", null, miControlador.crearControladorFacturadasOCP(), null, 3);
        tabs.setSelectedIndex(3);
    }
    
    public void liberarNCOC(String[] ocs){
        modelos.modeloFacturas jor = new modelos.modeloFacturas();
        jor.liberarNCOC(ocs);
    }
}
