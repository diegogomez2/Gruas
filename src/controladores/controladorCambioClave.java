/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
class controladorCambioClave {

    int cambiarClave(String pwNueva) {
        modelos.modeloUsuarios usuario = new modelos.modeloUsuarios();
        String respuesta = usuario.cambiarClave(pwNueva);
        if(respuesta.compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(null, "Contraseña cambiada con éxito");
            return 1;
        }else{
            System.out.println("error");
            return 0;
        }
    }
    
}
