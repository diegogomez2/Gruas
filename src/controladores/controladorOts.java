/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaOtsP;

/**
 *
 * @author Diego
 */
public class controladorOts {
    static vistas.vistaOtsP vistaOtsP;

//    void mostrarVistaOT(String tipo, Object[][] data) {
//        if(vistaOT != null) vistaOT.setVisible(false);
//        vistaOT = new vistaOT(tipo, data);
//        vistaOT.setVisible(true);    
//    }
    
    JPanel mostrarTabControlOts(String tipo, Object[][] data) {
        vistaOtsP = new vistas.vistaOtsP(tipo, data);  
        return vistaOtsP;
    }

    public void irVistaIngresarOT(String[] data) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarOT(data);
    }
    
    public String getIdFactura(String id){
        String idFact;
        modelos.modeloOts ots = new modelos.modeloOts();
        idFact = ots.getIdFactura(id);
        return idFact;
    }

    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(4);
        //tabs.remove(4);
        tabs.insertTab("OTs", null, miControlador.crearControladorOtsP(), null, 4);
        //tabs.insertTab("Facturas", null, miControlador.crearControladorFacturasP(), null, 5);
        tabs.setSelectedIndex(4);
    }
    
    public String ingresarFactura(String idOt){
        String respuesta = "";
        modelos.modeloOts ots = new modelos.modeloOts();
        respuesta = ots.ingresarFactura(idOt);
        return respuesta;
    }
    
    public void irVistaDetalleOts(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleOts(id);
    }
}
