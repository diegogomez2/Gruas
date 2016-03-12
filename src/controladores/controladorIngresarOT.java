/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaIngresarOT;

/**
 *
 * @author Diego
 */
class controladorIngresarOT {

    static vistas.vistaIngresarOT vistaOT;
    void mostrarVistaIngresarOT() {
        vistaOT = new vistaIngresarOT(new javax.swing.JFrame(), true);
        vistaOT.setLocationRelativeTo(null);
        vistaOT.setVisible(true);    }
    
}
