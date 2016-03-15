/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaIngresarEmpleados;

/**
 *
 * @author diego
 */
public class controladorIngresarEmpleados {
    
    static vistas.vistaIngresarEmpleados vistaIE;
    
    public void mostrarVistaIngresarEmpleados() {
        vistaIE = new vistaIngresarEmpleados(new javax.swing.JFrame(), true);
        vistaIE.setLocationRelativeTo(null);
        vistaIE.setVisible(true);
    }

    public boolean irVistaEmpleadosP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] rut_dv = vistaIE.getTextoRut().split("-");
        String[] data = {rut_dv[0], rut_dv[1], vistaIE.getTextoNombres(), vistaIE.getTextoApPaterno(),
            vistaIE.getTextoApMaterno(), vistaIE.getTextoFechaNac(), vistaIE.getTextoTelefono(), 
            vistaIE.getTextoCorreo(), vistaIE.getComboCargo(), vistaIE.getTextoSueldo(), vistaIE.getComboAFP(),
            vistaIE.getComboSalud(), vistaIE.getTextoFechaIn(), vistaIE.getTextoDir(), vistaIE.getComboRegion(),
            vistaIE.getComboComuna()};
        boolean flag = miControlador.ingresarEmpleado(data);
        return flag;    }

    public String camposVacios() {
        String respuesta = "";
        if (vistaIE.getTextoRut().compareTo("") == 0) {
            respuesta += "-Rut.\n";
        }
        if (vistaIE.getTextoNombres().compareTo("") == 0) {
            respuesta += "-Nombres.\n";
        }
        if (vistaIE.getTextoApPaterno().compareTo("") == 0) {
            respuesta += "-Apellido paterno.\n";
        }
        if (vistaIE.getTextoApMaterno().compareTo("") == 0) {
            respuesta += "-Apellido materno.\n";
        }
        if (vistaIE.getTextoFechaNac().compareTo("") == 0) {
            respuesta += "-Fecha de Nacimiento.\n";
        }
        if (vistaIE.getTextoTelefono().compareTo("") == 0) {
            respuesta += "-Teléfono.\n";
        }
        if (vistaIE.getTextoCorreo().compareTo("") == 0) {
            respuesta += "-Correo.\n";
        }
        if (vistaIE.getComboCargo().compareTo("") == 0) {
            respuesta += "-Cargo.\n";
        }
        if (vistaIE.getTextoSueldo().compareTo("") == 0) {
            respuesta += "-Sueldo.\n";
        }
        if (vistaIE.getComboAFP().compareTo("") == 0) {
            respuesta += "-AFP.\n";
        }
        if (vistaIE.getComboSalud().compareTo("") == 0) {
            respuesta += "-Salud.\n";
        }
        if (vistaIE.getTextoFechaIn().compareTo("") == 0) {
            respuesta += "-Fecha de ingreso.\n";
        }
        if (vistaIE.getTextoDir().compareTo("") == 0) {
            respuesta += "-Dirección.\n";
        }
        if (vistaIE.getComboRegion().compareTo("") == 0) {
            respuesta += "-Región.\n";
        }
        if (vistaIE.getComboComuna().compareTo("") == 0) {
            respuesta += "-Comuna.\n";
        }
        return respuesta;
    }
}
