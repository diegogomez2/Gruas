/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

/**
 *
 * @author Diego
 */
class controladorEliminarGruas {
    String tipo;
    Object[][] data;
    
    public boolean irVistaControlGruas(String patente){
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        boolean flag = miControlador.eliminarGruas(patente);
        return flag;
    }
}
