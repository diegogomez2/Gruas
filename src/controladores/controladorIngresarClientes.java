/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Frame;
import javax.swing.JOptionPane;
import vistas.vistaIngresarClientes;

/**
 *
 * @author Diego
 */
public class controladorIngresarClientes {

    static vistas.vistaIngresarClientes vistaIC;

    public void mostrarVistaIngresarClientes(Object[][] dataRegiones) {
        vistaIC = new vistaIngresarClientes(new javax.swing.JFrame(), true, dataRegiones);
        vistaIC.setLocationRelativeTo(null);
        vistaIC.setVisible(true);
    }

    public boolean irVistaClientesP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] rut_dv = vistaIC.getTextoRut().split("-");
        String[] data = {rut_dv[0], rut_dv[1], vistaIC.getTextoContacto(), vistaIC.getTextoRazon(), 
            vistaIC.getTextoGiro(), vistaIC.getTextoCorreo(), vistaIC.getTextoTelefono(), 
            vistaIC.getTextoCel(), vistaIC.getTextoDireccion(), vistaIC.getComboRegion(),
            vistaIC.getComboCiudad(), vistaIC.getComboComuna(), vistaIC.getTextoObs()};
        boolean flag = miControlador.ingresarCliente(data);
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
                        JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
            return false;
        }
        JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
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
        if (vistaIC.getTextoRut().compareTo("") == 0) {
            respuesta += "-Rut.\n";
        }
//        if (vistaIC.getTextoContacto().compareTo("") == 0) {
//            respuesta += "-Contacto.\n";
        if (vistaIC.getTextoRazon().compareTo("") == 0) {
            respuesta += "-Razón social.\n";
        }
        if (vistaIC.getTextoGiro().compareTo("") == 0) {
            respuesta += "-Giro.\n";
        }
//        if (vistaIC.getTextoCorreo().compareTo("") == 0) {
//            respuesta += "-Correo.\n";
//        }
        if (vistaIC.getTextoDireccion().compareTo("") == 0) {
            respuesta += "-Dirección.\n";
        }
//        if (vistaIC.getTextoRegion().compareTo("") == 0) {
//            respuesta += "-Región.\n";
//        }
//        if (vistaIC.getTextoComuna().compareTo("") == 0) {
//            respuesta += "-Comuna.\n";
//        }
//        if(vistaIC.getTextoTelefono().compareTo("") == 0){
//            respuesta += "-Teléfono.\n";
//        }
        return respuesta;
    }

    public Object[] cargarComunas(int region) {
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.cargarComunas(region);
    }
    
    public Object[] cargarCiudades(int region) {
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.cargarCiudades(region);
    }
}
