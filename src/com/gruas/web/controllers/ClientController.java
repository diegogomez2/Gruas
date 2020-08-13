/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruas.web.controllers;

import controladores.controladorPrincipal;
import vistas.vistaDetalleClientes;

/**
 *
 * @author Diego
 */
public class ClientController {
    
    static vistas.vistaDetalleClientes vistaDC;
    
    void ver(String rut){
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerClientePorRut(rut);
        vistaDC = new vistaDetalleClientes(new javax.swing.JFrame(), true);
        vistaDC.setTextoRut(data[0]);
        vistaDC.setTextoContacto(data[1]);
        vistaDC.setTextoRazon(data[2]);
        vistaDC.setTextoGiro(data[3]);
        vistaDC.setTextoCorreo(data[4]);
        vistaDC.setTextoTelefono(data[5]);
        vistaDC.setTextoCel(data[6]);
        vistaDC.setTextoDireccion(data[7]);
        vistaDC.setComboRegion(data[8]);
        vistaDC.setComboCiudad(data[9]);
        vistaDC.setComboComuna(data[10]);
        vistaDC.setTextoObs(data[11]);
        vistaDC.setLocationRelativeTo(null);
        vistaDC.setVisible(true);
    }
    
    
}
