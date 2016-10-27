/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import vistas2.vistaGenerarLibroAtrasado;

/**
 *
 * @author diego
 */
public class controladorGenerarLibroAtrasado {
    static vistas2.vistaGenerarLibroAtrasado vistaGenerarLibroAtrasado;

    public void mostrarVistaGenerarLibroAtrasado() {
        if(vistaGenerarLibroAtrasado != null) vistaGenerarLibroAtrasado.setVisible(false);
        vistaGenerarLibroAtrasado = new vistaGenerarLibroAtrasado();
        vistaGenerarLibroAtrasado.setVisible(true);
        vistaGenerarLibroAtrasado.setLocationRelativeTo(null);
    }
}
