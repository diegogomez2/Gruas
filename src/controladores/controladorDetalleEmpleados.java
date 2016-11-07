/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import vistas.vistaDetalleEmpleados;

/**
 *
 * @author diego
 */
class controladorDetalleEmpleados {
    
    static vistas.vistaDetalleEmpleados vistaDE;

    void mostrarVistaDetalleEmpleado(String rut) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerEmpleadoPorRut(rut);
        vistaDE = new vistaDetalleEmpleados(new javax.swing.JFrame(), true);
        if(data[10].toLowerCase().compareTo("fonasa") == 0){
            vistaDE.hideIsapre();
        }
        vistaDE.setTextoRut(data[0]);
        vistaDE.setTextoNombres(data[1]);
        vistaDE.setTextoApPaterno(data[2]);
        vistaDE.setTextoApMaterno(data[3]);
        vistaDE.setTextoFechaNac(data[4]);
        vistaDE.setTextoTelefono(data[5]);
        vistaDE.setTextoCorreo(data[6]);
        vistaDE.setComboCargo(data[7]);
        vistaDE.setTextoSueldo(data[8]);
        vistaDE.setComboAFP(data[9]);
        vistaDE.setComboSalud(data[10]);
        vistaDE.setTextoFechaIn(data[11]);
        vistaDE.setTextoDir(data[12]);
        vistaDE.setComboRegion(data[13]);
        vistaDE.setComboComuna(data[14]);
        vistaDE.setTextoColacion(data[15]);
        vistaDE.setTextoTransporte(data[16]);
        vistaDE.setTextoIsapre(data[17]);
        vistaDE.setTextoValorPlan(data[18]);
        vistaDE.setTextoBonoAd(data[19]);
        vistaDE.setTextoValorBonoAd(data[20]);
        vistaDE.setTextoCaja(data[21]);
        vistaDE.setTextoAF(data[22]);
        vistaDE.setLocationRelativeTo(null);
        vistaDE.setVisible(true);
    }
    
}
