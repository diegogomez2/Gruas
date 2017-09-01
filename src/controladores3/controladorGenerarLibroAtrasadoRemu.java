/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import vistas3.vistaGenerarLibroAtrasadoRemu;

/**
 *
 * @author diego
 */
public class controladorGenerarLibroAtrasadoRemu {
    static vistas3.vistaGenerarLibroAtrasadoRemu vistaGenerarLibroAtrasadoRemu;

    public void mostrarVistaGenerarLibroAtrasadoRemu() {
        if(vistaGenerarLibroAtrasadoRemu != null) vistaGenerarLibroAtrasadoRemu.setVisible(false);
        vistaGenerarLibroAtrasadoRemu = new vistaGenerarLibroAtrasadoRemu(null, true);
        vistaGenerarLibroAtrasadoRemu.setVisible(true);
        vistaGenerarLibroAtrasadoRemu.setLocationRelativeTo(null);
    }
}
