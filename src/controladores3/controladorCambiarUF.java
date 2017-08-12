/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaCambiarUF;

/**
 *
 * @author diego
 */


public class controladorCambiarUF {
    
    static vistaCambiarUF vistaCU;
    
    public void mostrarVistaCambiarUF(){
        controladorPrincipal miControlador = new controladorPrincipal();
        double utm = miControlador.obtenerUF();
        vistaCU = new vistaCambiarUF(new javax.swing.JFrame(), true);
        vistaCU.setTextoNuevoValorUF(String.valueOf(0));
        vistaCU.setTextoValorUF(String.valueOf(utm));
        vistaCU.setLocationRelativeTo(null);
        vistaCU.setVisible(true);
    }
    
    public boolean irVistaUFP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String uf = vistaCU.getTextoNuevoValorUF();
        return miControlador.cambiarUF(uf);
    } 
}
