/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaCambiarRutas;

/**
 *
 * @author diego
 */


public class controladorCambiarRutas {
    
    static vistaCambiarRutas vistaCR;
    
    public void mostrarVistaCambiarRutas(){
        controladorPrincipal miControlador = new controladorPrincipal();
        String ruta = miControlador.obtenerRuta();
        vistaCR = new vistaCambiarRutas(new javax.swing.JFrame(), true);
        vistaCR.setRutaActual(ruta);
        vistaCR.setLocationRelativeTo(null);
        vistaCR.setVisible(true);
    }
    
    public boolean irVistaRutasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String ruta = vistaCR.getTextoNuevaRuta();
        return miControlador.cambiarRuta(ruta);
    } 
}
