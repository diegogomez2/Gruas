/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JOptionPane;
import vistas.vistaModificarClientes;

/**
 *
 * @author Diego
 */
public class controladorModificarClientes {
    static vistas.vistaModificarClientes vistaMC;
    
    public void mostrarVistaModificarCliente(String rut, String nombres, Object[][] dataRegiones){
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerClientePorRut(rut);
        vistaMC = new vistaModificarClientes(new javax.swing.JFrame(), true, dataRegiones, data[8], data[9],
        data[10]);
        vistaMC.setTextoRut(data[0]);
        vistaMC.setTextoContacto(data[1]);
        vistaMC.setTextoRazon(data[2]);
        vistaMC.setTextoGiro(data[3]);
        vistaMC.setTextoCorreo(data[4]);
        vistaMC.setTextoTelefono(data[5]);
        vistaMC.setTextoCel(data[6]);
        vistaMC.setTextoDireccion(data[7]);
        vistaMC.setComboRegion(data[8]);
        vistaMC.setComboCiudad(data[9]);
        vistaMC.setComboComuna(data[10]);
        vistaMC.setTextoObs(data[11]);
        vistaMC.setRut(Integer.parseInt(data[0].split("-")[0]));
        vistaMC.setLocationRelativeTo(null);
        vistaMC.setVisible(true);
    }
    
    public boolean irVistaClientesP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] rut_dv = vistaMC.getTextoRut().split("-");
        String[] data = {rut_dv[0], rut_dv[1], vistaMC.getTextoContacto(), vistaMC.getTextoRazon(),
            vistaMC.getTextoGiro(), vistaMC.getTextoCorreo(), vistaMC.getTextoTelefono(), 
            vistaMC.getTextoCel(), vistaMC.getTextoDireccion(),
            vistaMC.getComboRegion(), vistaMC.getComboCiudad(), vistaMC.getComboComuna(), vistaMC.getTextoObs()};
        boolean flag = miControlador.modificarCliente(data, vistaMC.getRut());
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
                        JOptionPane.showMessageDialog(vistaMC, "Rut incorrecto");
                        return false;
                    }
                }catch(Exception e){
                    System.out.println(e);
                }
            }else{
                JOptionPane.showMessageDialog(vistaMC, "Rut incorrecto");
                return false;
            }
        }else{
            JOptionPane.showMessageDialog(vistaMC, "Rut incorrecto");
            return false;
        }
        JOptionPane.showMessageDialog(vistaMC, "Rut incorrecto");
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
        if(vistaMC.getTextoRut().compareTo("") == 0) respuesta += "-Rut.\n";
        //if(vistaMC.getTextoContacto().compareTo("") == 0) respuesta += "-Nombres.\n";
        if(vistaMC.getTextoRazon().compareTo("") == 0) respuesta += "-Razón social.\n";
        if(vistaMC.getTextoGiro().compareTo("") == 0) respuesta += "-Giro.\n";
        //if(vistaMC.getTextoCorreo().compareTo("") == 0) respuesta += "-Correo.\n";
        if(vistaMC.getTextoDireccion().compareTo("") == 0) respuesta += "-Dirección.\n";
        if(vistaMC.getComboRegion().compareTo("") == 0) respuesta += "-Región.\n";
        if(vistaMC.getComboComuna().compareTo("") == 0) respuesta += "-Comuna.\n";
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
