/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaCambiarBono;

/**
 *
 * @author diego
 */
public class controladorTonelajeBono300 {
    static vistas3.vistaCambiarBono vistaCB;
    
    public void mostrarVistaTonelajeBono300(){
        controladorPrincipal miControlador = new controladorPrincipal();
        Object[][] data = miControlador.obtenerTonelajeBono300();
        vistaCB = new vistaCambiarBono(new javax.swing.JFrame(), true, data);
        vistaCB.setLocationRelativeTo(null);
        vistaCB.setVisible(true);
    }
    
    public void irVistaAgregarTonelaje(){
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorAgregarTonelaje();
    }
    
    public void irVistaModificarTonelaje(String pes){
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarTonelaje(pes);
    }
}
