/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import vistas4.vistaIngresarOC;

/**
 *
 * @author diego
 */
public class controladorIngresarOC {
    
    static vistas4.vistaIngresarOC vistaOC;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public void mostrarVistaIngresarOC(Object[] clientes, Object[] gruas, Object[] empleados, Object[] tonelajes) {
        vistaOC = new vistaIngresarOC(new javax.swing.JFrame(), true, clientes, tonelajes, gruas);
        vistaOC.setLocationRelativeTo(null);
        vistaOC.setVisible(true);    
    }
}
