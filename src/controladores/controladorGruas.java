/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaControlGruas;

/**
 *
 * @author Diego
 */
public class controladorGruas {
    static vistas.vistaControlGruas vistaGruas;
    
    public void mostrarVistaControlGruas(String tipo, Object[][] data){
        if(vistaGruas != null) vistaGruas.setVisible(false);
        vistaGruas = new vistaControlGruas(tipo, data);
        vistaGruas.setVisible(true);
    }

    public void irVistaIngresarGruas() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarGruas();
    }
    
    public void eliminarGruas(String patente){
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarGruas(patente);
    }

    public void irVistaModificarGruas(String patente, String descripcion) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarClientes(patente, descripcion);
    }
    
    
}
