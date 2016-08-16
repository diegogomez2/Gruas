/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.*;

/**
 *
 * @author Diego
 */
public class controladorEliminarCompras {
    String tipo;
    Object[][] data;
    
    public boolean irVistaComprasP(String id){
        controladorPrincipal miControlador = new controladorPrincipal();
        boolean flag = miControlador.eliminarCompras(id);
        return flag;
    }
}
