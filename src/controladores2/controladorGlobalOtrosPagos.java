/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas2.vistaGlobalOtrosPagosP;

/**
 *
 * @author diego
 */
public class controladorGlobalOtrosPagos {
    static vistaGlobalOtrosPagosP vistaGlobalOtrosPagosP;

    public void irVistaCambiarEstado(String id) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorCambiarEstadoPago(id);
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

    public JPanel mostrarTabControlGlobalOtrosPagos(String tipo, Object[][] data, Object[][] data2) {
        vistaGlobalOtrosPagosP = new vistaGlobalOtrosPagosP(tipo, data, data2);
        return vistaGlobalOtrosPagosP;
    }

    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(5);
        tabs.insertTab("Global otros pagos", null, miControlador.crearControladorGlobalOtrosPagosP(), null, 5);
        tabs.setSelectedIndex(5);
    }
}
