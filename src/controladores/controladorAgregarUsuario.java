/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaAgregarUsuario;

/**
 *
 * @author diego
 */
public class controladorAgregarUsuario {
    static vistas.vistaAgregarUsuario vistaAgregarUsuario;

    void mostrarVistaAgregarUsuario() {
        if(vistaAgregarUsuario != null) vistaAgregarUsuario.setVisible(false);
        vistaAgregarUsuario = new vistaAgregarUsuario();
        vistaAgregarUsuario.setVisible(true);
    }
}
