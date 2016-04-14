/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaIngresarOts;

/**
 *
 * @author diego
 */
public class controladorIngresarOts {

    static vistas.vistaIngresarOts vistaIO;
    
    void mostrarVistaIngresarOts(String[] data) {
        vistaIO = new vistaIngresarOts(new javax.swing.JFrame(), true, data);
        vistaIO.setLocationRelativeTo(null);
        vistaIO.setVisible(true);        
    }
    
}
