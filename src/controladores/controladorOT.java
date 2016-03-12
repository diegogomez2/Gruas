/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaOT;

/**
 *
 * @author Diego
 */
public class controladorOT {
    static vistas.vistaOT vistaOT;

    void mostrarVistaOT(String tipo, Object[][] data) {
        if(vistaOT != null) vistaOT.setVisible(false);
        vistaOT = new vistaOT(tipo, data);
        vistaOT.setVisible(true);    
    }

    public void irVistaIngresarOT() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarOT();
    }

}
