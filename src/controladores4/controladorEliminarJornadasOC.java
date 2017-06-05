/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

/**
 *
 * @author diego
 */
public class controladorEliminarJornadasOC {
    public boolean irVistaJornadasOCP(String id) {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        boolean flag = miControlador.eliminarJornadasOC(id);
        return flag;
    }
}
