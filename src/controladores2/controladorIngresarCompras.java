/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.*;
import java.awt.Frame;
import javax.swing.JOptionPane;
import vistas.vistaIngresarClientes;
import vistas2.vistaIngresarCompras;

/**
 *
 * @author Diego
 */
public class controladorIngresarCompras {

    static vistaIngresarCompras vistaIC;

    public void mostrarVistaIngresarCompras() {
        vistaIC = new vistaIngresarCompras(new javax.swing.JFrame(), true);
        vistaIC.setLocationRelativeTo(null);
        vistaIC.setVisible(true);
    }

    public boolean irVistaComprasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
//        String[] rut_dv = vistaIP.getTextoRut().split("-");
//        String[] data = {rut_dv[0], rut_dv[1], vistaIP.getTextoContacto(), vistaIP.getTextoRazon(), 
//            vistaIP.getTextoGiro(), vistaIP.getTextoCorreo(), vistaIP.getTextoTelefono(),
//            vistaIP.getTextoDireccion(), vistaIP.getComboRegion(), vistaIP.getComboComuna(), 
//            vistaIP.getComboCiudad(), vistaIP.getTextoObs(), vistaIP.getComboForma(), vistaIP.getComboMedio()};
        String [] data ={};
        boolean flag = miControlador.ingresarProveedor(data);
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
//        if (vistaIP.getTextoRut().compareTo("") == 0) {
//            respuesta += "-Rut.\n";
//        }
//        if (vistaIP.getTextoRazon().compareTo("") == 0) {
//            respuesta += "-Razón social.\n";
//        }
//        if (vistaIP.getTextoGiro().compareTo("") == 0) {
//            respuesta += "-Giro.\n";
//        }
//        if (vistaIP.getTextoDireccion().compareTo("") == 0) {
//            respuesta += "-Dirección.\n";
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
