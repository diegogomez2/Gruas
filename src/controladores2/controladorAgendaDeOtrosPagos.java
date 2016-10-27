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
import vistas2.vistaAgendaDeOtrosPagosP;

/**
 *
 * @author diego
 */
public class controladorAgendaDeOtrosPagos {
    static vistaAgendaDeOtrosPagosP vistaAgendaDeOtrosPagosP;

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
    public void irVistaCambiarEstado(String id, String fac) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorCambiarEstadoPago(id, fac);
    }
//
//    public void irVistaDetalleCompras(String id) throws ParseException {
//        controladorPrincipal miControlador = new controladorPrincipal();
//        miControlador.crearControladorDetalleCompra(id);
//    }

    public JPanel mostrarTabControlAgendaDeOtrosPagos(String tipo, Object[][] data) {
        vistaAgendaDeOtrosPagosP = new vistaAgendaDeOtrosPagosP(tipo, data);
        return vistaAgendaDeOtrosPagosP;
    }

    public void crearControladorPrincipal(JTabbedPane tabs)  {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(1);
        tabs.insertTab("Compras", null, miControlador.crearControladorComprasP(), null, 1);
        tabs.remove(3);
        tabs.insertTab("Agenda de otros pagos", null, miControlador.crearControladorAgendaDeOtrosPagosP(), null, 3);
        tabs.remove(5);
        tabs.insertTab("Global otros pagos", null, miControlador.crearControladorGlobalOtrosPagosP(), null, 5);
        tabs.setSelectedIndex(3);
    }
}
