/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JTabbedPane;
import vistas.vistaIngresarOts;

/**
 *
 * @author diego
 */
public class controladorIngresarOts {

    static vistas.vistaIngresarOts vistaIO;
    
    void mostrarVistaIngresarOts(String[] data) {
        vistaIO = new vistaIngresarOts(new javax.swing.JFrame(), true, data);
        vistaIO.setLocationRelativeTo(null);
        vistaIO.setVisible(true);        
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(3);
        tabs.insertTab("Jornada diaria", null, miControlador.crearControladorJornadaP(), null, 3);
        tabs.setSelectedIndex(3);
    }
    
    public boolean irVistaJornadasP(String id) {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] rut_dv = vistaIO.getTextoRutCliente().split("-");
        String[] data = {rut_dv[0], rut_dv[1], vistaIO.getTextoContacto(), vistaIO.getTextoDespachado(), vistaIO.getTextoObs(), id};
        boolean flag = miControlador.ingresarOt(data);
        return flag;
    } 
    
    public String camposVacios(){
        return "";
    }
    
}
