/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaEditarSueldos;

/**
 *
 * @author diego
 */
public class controladorEditarSueldos {
    
    static vistaEditarSueldos vistaES;
    
    public void mostrarVistaEditarSueldos(){
        controladorPrincipal miControlador = new controladorPrincipal();
        int sueldo_min = miControlador.obtenerSueldoMin();
        int sueldo_base = miControlador.obtenerSueldoBase();
        vistaES = new vistaEditarSueldos(new javax.swing.JFrame(), true);
        vistaES.setSueldoMin(sueldo_min);
        vistaES.setSueldoBase(sueldo_base);
        vistaES.setTextoSueldoMin(String.valueOf(sueldo_min));
        vistaES.setTextoSueldoBase(String.valueOf(sueldo_base));
        vistaES.setLocationRelativeTo(null);
        vistaES.setVisible(true);
    }
    
    public boolean irVistaSueldosP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String sueldo_min = vistaES.getTextoSueldoMin();
        String sueldo_base = vistaES.getTextoSueldoBase();
        return miControlador.editarSueldos(sueldo_min, sueldo_base);
    } 
}
