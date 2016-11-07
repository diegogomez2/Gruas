/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import vistas2.vistaVerPagos;

/**
 *
 * @author diego
 */
public class controladorVerPagos {
    static vistaVerPagos vistaVP;
    
    public void mostrarVistaVerPagos(String id, String tipo){
        controladorPrincipal miControlador = new controladorPrincipal();
        Object[][] data = miControlador.obtenerPagos(id, tipo);
        vistaVP = new vistaVerPagos(new javax.swing.JFrame(), true, tipo, data);
        vistaVP.setLocationRelativeTo(null);
        vistaVP.setVisible(true);
    }
}
