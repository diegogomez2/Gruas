/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JPanel;
import vistas.vistaFacturasP;

/**
 *
 * @author diego
 */
public class controladorFacturas {
    
    static vistas.vistaFacturasP vistaFacturasP;
    
    JPanel mostrarTabControlFacturas(String tipo, Object[][] data) {
        vistaFacturasP = new vistas.vistaFacturasP(tipo, data);  
        return vistaFacturasP;
    }
}
