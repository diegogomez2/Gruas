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
        vistaDE.setVisible(true);
    }
    
}
