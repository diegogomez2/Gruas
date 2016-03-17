/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JOptionPane;
import vistas.vistaModificarEmpleados;

/**
 *
 * @author diego
 */
public class controladorModificarEmpleados {

    static vistas.vistaModificarEmpleados vistaME;
    
    void mostrarVistaModificarEmpleados(String rut, String nombres, Object[][] dataRegiones) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerEmpleadoPorRut(rut);
        vistaME = new vistaModificarEmpleados(new javax.swing.JFrame(), true, dataRegiones);
        vistaME.setTextoRut(data[0]);
        vistaME.setTextoNombres(data[1]);
        vistaME.setTextoApPaterno(data[2]);
        vistaME.setTextoApMaterno(data[3]);
        vistaME.setTextoFechaNac(data[4]);
        vistaME.setTextoTelefono(data[5]);
        vistaME.setTextoCorreo(data[6]);
        vistaME.setComboCargo(data[7]);
        vistaME.setTextoSueldo(data[8]);
        vistaME.setComboAFP(data[9]);
        vistaME.setComboSalud(data[10]);
        vistaME.setTextoFechaIn(data[11]);
        vistaME.setTextoDir(data[12]);
        vistaME.setComboRegion(data[13]);
        vistaME.setComboComuna(data[14]);
        vistaME.setRut(Integer.parseInt(data[0].split("-")[0]));
        vistaME.setLocationRelativeTo(null);
        vistaME.setVisible(true);
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
                        JOptionPane.showMessageDialog(vistaME, "Rut incorrecto");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(vistaME, "Rut incorrecto");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(vistaME, "Rut incorrecto");
            return false;
        }
        JOptionPane.showMessageDialog(vistaME, "Rut incorrecto");
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
        if (vistaME.getTextoRut().compareTo("") == 0) {
            respuesta += "-Rut.\n";
        }
        if (vistaME.getTextoNombres().compareTo("") == 0) {
            respuesta += "-Nombres.\n";
        }
        if (vistaME.getTextoApPaterno().compareTo("") == 0) {
            respuesta += "-Apellido paterno.\n";
        }
        if (vistaME.getTextoApMaterno().compareTo("") == 0) {
            respuesta += "-Apellido materno.\n";
        }
        if (vistaME.getTextoFechaNac().compareTo("") == 0) {
            respuesta += "-Fecha de Nacimiento.\n";
        }
        if (vistaME.getTextoTelefono().compareTo("") == 0) {
            respuesta += "-Teléfono.\n";
        }
        if (vistaME.getTextoCorreo().compareTo("") == 0) {
            respuesta += "-Correo.\n";
        }
        if (vistaME.getComboCargo().compareTo("") == 0) {
            respuesta += "-Cargo.\n";
        }
        if (vistaME.getTextoSueldo().compareTo("") == 0) {
            respuesta += "-Sueldo.\n";
        }
        if (vistaME.getComboAFP().compareTo("") == 0) {
            respuesta += "-AFP.\n";
        }
        if (vistaME.getComboSalud().compareTo("") == 0) {
            respuesta += "-Salud.\n";
        }
        if (vistaME.getTextoFechaIn().compareTo("") == 0) {
            respuesta += "-Fecha de ingreso.\n";
        }
        if (vistaME.getTextoDir().compareTo("") == 0) {
            respuesta += "-Dirección.\n";
        }
        if (vistaME.getComboRegion().compareTo("") == 0) {
            respuesta += "-Región.\n";
        }
        if (vistaME.getComboComuna().compareTo("") == 0) {
            respuesta += "-Comuna.\n";
        }
        return respuesta;
    }

    public boolean irVistaEmpleadosP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] rut_dv = vistaME.getTextoRut().split("-");
        String[] data = {rut_dv[0], rut_dv[1], vistaME.getTextoNombres(), vistaME.getTextoApPaterno(),
            vistaME.getTextoApMaterno(), vistaME.getTextoFechaNac(), vistaME.getTextoTelefono(),
            vistaME.getTextoCorreo(), vistaME.getComboCargo(), vistaME.getTextoSueldo(), vistaME.getComboAFP(),
            vistaME.getComboSalud(), vistaME.getTextoFechaIn(), vistaME.getTextoDir(), vistaME.getComboRegion(),
            vistaME.getComboComuna()};
        boolean flag = miControlador.modificarEmpleado(data, vistaME.getRut());
        return flag;
    }    
}
