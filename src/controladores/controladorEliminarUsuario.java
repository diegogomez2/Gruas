/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author diego
 */
public class controladorEliminarUsuario {
    String tipo;
    Object[][] data;
    
    public boolean irVistaUsuariosP(String user){
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        boolean flag = miControlador.eliminarUsuario(user);
        return flag;
    }
}
