/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JPanel;

/**
 *
 * @author diego
 */
public class controladorFacturadas {
    static vistas.vistaFacturadasP vistaFacturadasP;
    
    JPanel mostrarTabControlFacturadasP(String tipo, Object[][] data) {
        vistaFacturadasP = new vistas.vistaFacturadasP(tipo, data);  
        return vistaFacturadasP;
    }
}
