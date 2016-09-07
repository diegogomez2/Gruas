/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JTabbedPane;
import vistas.vistaAgregarUsuario;
import vistas.vistaAgregarUsuario2;

/**
 *
 * @author diego
 */
public class controladorAgregarUsuario {
    static vistas.vistaAgregarUsuario vistaAgregarUsuario;
    static vistas.vistaAgregarUsuario2 vistaAgregarUsuario2;
            
    void mostrarVistaAgregarUsuario(JTabbedPane tab) {
        if(vistaAgregarUsuario != null) vistaAgregarUsuario.setVisible(false);
        vistaAgregarUsuario = new vistaAgregarUsuario(tab);
        vistaAgregarUsuario.setVisible(true);
        vistaAgregarUsuario.setLocationRelativeTo(null);
    }
    
    void mostrarVistaAgregarUsuario2() {
        if(vistaAgregarUsuario2 != null) vistaAgregarUsuario2.setVisible(false);
        vistaAgregarUsuario2 = new vistaAgregarUsuario2();
        vistaAgregarUsuario2.setVisible(true);
        vistaAgregarUsuario2.setLocationRelativeTo(null);
    }
}
