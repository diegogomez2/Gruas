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
public class controladorEliminarProveedores {
    String tipo;
    Object[][] data;
    
    public boolean irVistaProveedoresP(String rut){
        controladorPrincipal miControlador = new controladorPrincipal();
        String[] rut_dv = rut.split("-");
        boolean flag = miControlador.eliminarProveedores(rut_dv[0]);
        return flag;
    }
}
