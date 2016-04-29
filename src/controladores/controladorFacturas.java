/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modelos.modeloOts;
import vistas.vistaFacturasP;

/**
 *
 * @author diego
 */
public class controladorFacturas {
    
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
    
    public String archivarFacturas(String[] idOts){
        modelos.modeloOts factura = new modeloOts();
        for(int i = 0; i < idOts.length; i++){
            factura.archivarFactura(idOts[i]);
        }
        return "correcto";
    }
}
