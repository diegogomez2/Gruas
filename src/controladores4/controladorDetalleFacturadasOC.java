/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import vistas4.vistaDetalleFacturadasOC;

/**
 *
 * @author diego
 */
public class controladorDetalleFacturadasOC {
    vistas4.vistaDetalleFacturadasOC vistaDF;
    public void mostrarVistaDetalleFacturadasOC(String id, String tipo) {
        controladorPrincipal miControlador = new controladorPrincipal();
        Object[][] data = miControlador.obtenerOcPorIdFacturada(id, tipo);
        vistaDF = new vistaDetalleFacturadasOC(new javax.swing.JFrame(), true, data);
        vistaDF.setLocationRelativeTo(null);
        vistaDF.setVisible(true);
    }

}
