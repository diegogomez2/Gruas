/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.*;
import static controladores2.controladorIngresarProveedores.vistaIP;
import javax.swing.JOptionPane;
import vistas.vistaIngresarClientes;
import vistas2.vistaModificarProveedores;

/**
 *
 * @author Diego
 */
public class controladorModificarProveedores {
    static vistaModificarProveedores vistaMP;
    
    public void mostrarVistaModificarProveedor(String rut, String nombres, Object[][] dataRegiones){
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerProveedorPorRut(rut);
        vistaMP = new vistaModificarProveedores(new javax.swing.JFrame(), true, dataRegiones, data[8], data[9],
        data[10]);
        vistaMP.setTextoRut(data[0]);
        vistaMP.setTextoContacto(data[1]);
        vistaMP.setTextoRazon(data[2]);
        vistaMP.setTextoGiro(data[3]);
        vistaMP.setTextoCorreo(data[4]);
        vistaMP.setTextoTelefono(data[5]);
        vistaMP.setTextoDireccion(data[6]);
        vistaMP.setComboRegion(data[7]);
        vistaMP.setComboCiudad(data[8]);
        vistaMP.setComboComuna(data[9]);
        vistaMP.setTextoObs(data[10]);
        vistaMP.setComboForma(data[11]);
        vistaMP.setComboMedio(data[12]);
        vistaMP.setRut(Integer.parseInt(data[0].split("-")[0]));
        vistaMP.setLocationRelativeTo(null);
        vistaMP.setVisible(true);
    }
    
    public boolean irVistaProveedoresP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] rut_dv = vistaMP.getTextoRut().split("-");
        String[] data = {rut_dv[0], rut_dv[1], vistaMP.getTextoContacto(), vistaMP.getTextoRazon(),
            vistaMP.getTextoGiro(), vistaMP.getTextoCorreo(), vistaMP.getTextoTelefono(), 
            vistaMP.getTextoDireccion(), vistaMP.getComboRegion(), 
            vistaMP.getComboCiudad(), vistaMP.getComboComuna(), vistaMP.getTextoObs(), vistaMP.getComboForma(),
            vistaMP.getComboMedio()};
        boolean flag = miControlador.modificarProveedor(data, vistaMP.getRut());
        return flag;
    }
    
    public  boolean verificarRut(String rut){
        if(rut.compareTo("") != 0){
            String[] rut_dv = rut.split("-");
            if(rut_dv.length == 2){
                try{
                    int num = Integer.parseInt(rut_dv[0]);
                    char dv = rut_dv[1].charAt(0);
                    if(validarRut(num, dv)){
                        return true;
                    }else{
                        JOptionPane.showMessageDialog(vistaMP, "Rut incorrecto");
                        return false;
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }else{
                JOptionPane.showMessageDialog(vistaMP, "Rut incorrecto");
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(vistaMP, "Rut incorrecto");
            return false;
        }
        JOptionPane.showMessageDialog(vistaMP, "Rut incorrecto");
        return false;
    }
    
    public static boolean validarRut(int rut, char dv){
        int m = 0, s = 1;
        for(; rut != 0; rut /= 10){
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char)(s != 0 ? s + 47 : 107);
    }
    
    public String camposVacios(){
        String respuesta = "";
        if(vistaMP.getTextoRut().compareTo("") == 0) respuesta += "-Rut.\n";
        if(vistaMP.getTextoRazon().compareTo("") == 0) respuesta += "-Razón social.\n";
        if(vistaMP.getTextoGiro().compareTo("") == 0) respuesta += "-Giro.\n";
        if(vistaMP.getTextoDireccion().compareTo("") == 0) respuesta += "-Dirección.\n";
        if(vistaMP.getComboRegion().compareTo("") == 0) respuesta += "-Región.\n";
        if(vistaMP.getComboComuna().compareTo("") == 0) respuesta += "-Comuna.\n";
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
