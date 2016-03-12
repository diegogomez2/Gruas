/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Frame;
import vistas.vistaCambioClave;

/**
 *
 * @author Diego
 */
class controladorUsuarios {
    static vistas.vistaCambioClave vistaCambioClave;
    public controladorUsuarios() {
    }

    void mostrarVistaCambioClave(String tipo, String data) {
        if(vistaCambioClave != null) vistaCambioClave.setVisible(false);
        vistaCambioClave = new vistaCambioClave(tipo, data);
        vistaCambioClave.setVisible(true);
    }
    
}
