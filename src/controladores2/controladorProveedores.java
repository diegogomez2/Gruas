/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.*;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas2.vistaProveedoresP;

/**
 *
 * @author Diego
 */
public class controladorProveedores {

    static vistas2.vistaProveedoresP vistaProveedoresP;

    public void irVistaIngresarProveedores() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarProveedores();
    }

    public void eliminarProveedores(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarProveedores(rut);
    }

    public void irVistaModificarProveedores(String rut, String nombres) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarProveedores(rut, nombres);
    }

    public void irVistaDetalleProveedor(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleProveedor(rut);
    }

    public JPanel mostrarTabControlProveedores(String tipo, Object[][] data) {
        vistaProveedoresP = new vistaProveedoresP(tipo, data);
        return vistaProveedoresP;
    }

    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(0);
        tabs.insertTab("Proveedores", null, miControlador.crearControladorProveedoresP(), null, 0);
        tabs.setSelectedIndex(0);
    }
}
