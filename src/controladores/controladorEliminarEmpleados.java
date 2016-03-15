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
class controladorEliminarEmpleados {

    public boolean irVistaEmpleadosP(String rut) {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] rut_dv = rut.split("-");
        boolean flag = miControlador.eliminarEmpleados(rut_dv[0]);
        return flag;
    }
    
}
