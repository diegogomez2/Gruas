/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaCambiarUTM;

/**
 *
 * @author diego
 */


public class controladorCambiarUTM {
    
    static vistaCambiarUTM vistaCU;
    
    public void mostrarVistaCambiarUTM(){
        controladorPrincipal miControlador = new controladorPrincipal();
        int utm = miControlador.obtenerUTM();
        vistaCU = new vistaCambiarUTM(new javax.swing.JFrame(), true);
        vistaCU.setTextoNuevoValorUTM(String.valueOf(0));
        vistaCU.setTextoValorUTM(String.valueOf(utm));
        vistaCU.setLocationRelativeTo(null);
        vistaCU.setVisible(true);
    }
    
    public boolean irVistaUTMP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String utm = vistaCU.getTextoNuevoValorUTM();
        return miControlador.cambiarUTM(utm);
    } 
}
