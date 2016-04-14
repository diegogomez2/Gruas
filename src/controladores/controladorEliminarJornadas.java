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
class controladorEliminarJornadas {
    String tipo;
    Object[][] data;

    public boolean irVistaJornadasP(String id) {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        boolean flag = miControlador.eliminarJornadas(id);
        return flag;
    }
}
