/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaDetalleClientes;

/**
 *
 * @author Diego
 */
class controladorDetalleClientes {
    static vistas.vistaDetalleClientes vistaDC;
    
    void mostrarVistaDetalleCliente(String rut){
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
        vistaDC.setTextoRegion(data[8]);
        vistaDC.setTextoComuna(data[9]);
        vistaDC.setTextoObs(data[10]);
        vistaDC.setLocationRelativeTo(null);
        vistaDC.setVisible(true);
    }
    
}
