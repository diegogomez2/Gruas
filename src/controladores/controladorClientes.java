/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaClientesP;

/**
 *
 * @author Diego
 */
public class controladorClientes {

    static vistas.vistaClientesP vistaClientesP;

    public void irVistaIngresarClientes() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarClientes();
    }

    public void eliminarClientes(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarClientes(rut);
    }

    public void irVistaModificarClientes(String rut, String nombres) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarClientes(rut, nombres);
    }

    public void irVistaDetalleCliente(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleCliente(rut);
    }

    public JPanel mostrarTabControlClientes(String tipo, Object[][] data) {
        //if(vistaClientes != null) vistaClientes.setVisible(false);
        vistaClientesP = new vistaClientesP(tipo, data);
        //vistaClientes.setVisible(true);   
        return vistaClientesP;
    }

    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(0);
        tabs.insertTab("Clientes", null, miControlador.crearControladorClientesP(), null, 0);
        tabs.setSelectedIndex(0);
    }
}
