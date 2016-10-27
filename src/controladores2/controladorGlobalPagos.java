/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas2.vistaGlobalPagosP;

/**
 *
 * @author diego
 */
public class controladorGlobalPagos {
    static vistaGlobalPagosP vistaGlobalPagosP;

    public void irVistaCambiarEstado(String id, String fac) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorCambiarEstadoPago(id, fac);
    }
    
//    public void irVistaIngresarCompras() {
//        controladorPrincipal miControlador = new controladorPrincipal();
//        miControlador.crearControladorIngresarCompras();
//    }
//
//    public void eliminarCompras(String id) {
//        controladorPrincipal miControlador = new controladorPrincipal();
//        miControlador.crearControladorEliminarCompras(id);
//    }
//
//    public void irVistaCambiarEstado(String id) {
//        controladorPrincipal miControlador = new controladorPrincipal();
//        miControlador.crearControladorCambiarEstadoPago(id);
//    }
//
//    public void irVistaDetalleCompras(String id) throws ParseException {
//        controladorPrincipal miControlador = new controladorPrincipal();
//        miControlador.crearControladorDetalleCompra(id);
//    }

    public JPanel mostrarTabControlGlobalPagos(String tipo, Object[][] data, Object[][] data2) {
        vistaGlobalPagosP = new vistaGlobalPagosP(tipo, data, data2);
        return vistaGlobalPagosP;
    }

    public void crearControladorPrincipal(JTabbedPane tabs)  {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(1);
        tabs.insertTab("Compras", null, miControlador.crearControladorComprasP(), null, 1);
        tabs.remove(2);
        tabs.remove(3);
        tabs.insertTab("Agenda de pagos", null, miControlador.crearControladorAgendaDePagosP(), null, 2);
        tabs.insertTab("Global de pagos", null, miControlador.crearControladorGlobalPagosP(), null, 4);
        tabs.setSelectedIndex(4);
    }
}
