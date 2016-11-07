/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaPresAdelanto;

/**
 *
 * @author diego
 */
public class controladorPresAdelanto {
    static vistas3.vistaPresAdelanto vistaPA;
    
    public void mostrarVistaPresAdelanto(String rut){
        controladorPrincipal miControlador = new controladorPrincipal();
        String[] data = miControlador.obtenerPresAdelantoEmpleadoPorRut(rut);
        vistaPA = new vistaPresAdelanto(new javax.swing.JFrame(), true);
        vistaPA.setRut(rut);
        vistaPA.setTextoAnticipo(data[0]);
        vistaPA.setTextoAdelanto(data[1]);
        vistaPA.setTextoPrestamo(data[2]);
        vistaPA.setSpinnerCuotas(data[3]);
        vistaPA.setSpinnerCuotasRes(data[4]);
        vistaPA.setLocationRelativeTo(null);
        vistaPA.setVisible(true);
    }
    
    public boolean ingresarPresAdelanto(){
        controladorPrincipal miControlador = new controladorPrincipal();
        String[] rut_dv = vistaPA.getRut().split("-");
        String[] data = {vistaPA.getTextoAnticipo(), vistaPA.getTextoAdelanto(), vistaPA.getTextoPrestamo(),
        vistaPA.getSpinnerCuotas()};
        boolean flag = miControlador.ingresarPresAdelanto(rut_dv[0], data);
        return flag;
    }
}
