/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modelos.modeloFacturas;
import modelos.modeloOts;
import vistas.vistaFacturasP;

/**
 *
 * @author diego
 */
public class controladorFacturas {
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    static vistas.vistaFacturasP vistaFacturasP;
    
    JPanel mostrarTabControlFacturas(String tipo, Object[][] data) {
        vistaFacturasP = new vistas.vistaFacturasP(tipo, data);  
        return vistaFacturasP;
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(5);
        tabs.insertTab("A Facturar", null, miControlador.crearControladorFacturasP(), null, 5);
        tabs.setSelectedIndex(5);
    }
    
    public void irVistaDetalleFacturas(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleFacturas(id);
    }
    
    public String archivarFacturas(String[] idOts, int neto, int iva, int total){
        modelos.modeloFacturas factura = new modeloFacturas();
        modelos.modeloOts ot = new modeloOts();
        String id = factura.ingresarFacturada(formatDate.format(new Date()), neto, iva, total);
        for(int i = 0; i < idOts.length; i++){
            ot.archivarFactura(idOts[i], id);
        }
        return "correcto";
    }
}
