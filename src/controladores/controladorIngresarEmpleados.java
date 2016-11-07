/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JOptionPane;
import vistas.vistaIngresarEmpleados;

/**
 *
 * @author diego
 */
public class controladorIngresarEmpleados {
    
    static vistas.vistaIngresarEmpleados vistaIE;
    
    public void mostrarVistaIngresarEmpleados(Object[][] dataRegiones) {
        controladorPrincipal miControlador = new controladorPrincipal();
        int sueldo_min = miControlador.obtenerSueldoMin();
        int sueldo_base = miControlador.obtenerSueldoBase();
        vistaIE = new vistaIngresarEmpleados(new javax.swing.JFrame(), true, dataRegiones);
        vistaIE.setSueldoMin(sueldo_min);
        vistaIE.setSueldoBase(sueldo_base);
        vistaIE.setTextoSueldo(String.valueOf(sueldo_min));
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
            vistaIE.getComboComuna(), vistaIE.getIsapre(), vistaIE.getValorIsapre(), vistaIE.getTextoColacion(), 
            vistaIE.getTextoTransporte(), vistaIE.getTextoBonoAd(), vistaIE.getTextoValorBonoAd(), vistaIE.getTextoCaja(),
            vistaIE.getTextoAF()};
        boolean flag = miControlador.ingresarEmpleado(data);
        return flag;    
    }
    
    public boolean verificarRut(String rut) {
        if (rut.compareTo("") != 0) {
            String[] rut_dv = rut.split("-");
            if (rut_dv.length == 2) {
                try {
                    int num = Integer.parseInt(rut_dv[0]);
                    char dv = rut_dv[1].charAt(0);
                    if (validarRut(num, dv)) {
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(vistaIE, "Rut incorrecto");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(vistaIE, "Rut incorrecto");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(vistaIE, "Rut incorrecto");
            return false;
        }
        JOptionPane.showMessageDialog(vistaIE, "Rut incorrecto");
        return false;
    }
    
    public static boolean validarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 107);
    }

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

    public Object[] cargarComunas(int region) {
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.cargarComunas(region);
    }
}
