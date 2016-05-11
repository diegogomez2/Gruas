/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaDetalleFacturadas;

/**
 *
 * @author diego
 */
public class controladorDetalleFacturadas {
    
    vistas.vistaDetalleFacturadas vistaDF;
    public void mostrarVistaDetalleFacturadas(String id) {
        controladorPrincipal miControlador = new controladorPrincipal();
        Object[][] data = miControlador.obtenerOtPorIdFacturada(id);
        vistaDF = new vistaDetalleFacturadas(new javax.swing.JFrame(), true, data);
        vistaDF.setLocationRelativeTo(null);
        vistaDF.setVisible(true);
    }
}
