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
        vistaDC.setTextoNombres(data[1]);
        vistaDC.setTextoApPaterno(data[2]);
        vistaDC.setTextoApMaterno(data[3]);
        vistaDC.setTextoRazon(data[4]);
        vistaDC.setTextoGiro(data[5]);
        vistaDC.setTextoCorreo(data[6]);
        vistaDC.setTextoTelefono(data[7]);
        vistaDC.setTextoFax(data[8]);
        vistaDC.setTextoCel(data[9]);
        vistaDC.setTextoDireccion(data[10]);
        vistaDC.setTextoRegion(data[11]);
        vistaDC.setTextoComuna(data[12]);
        vistaDC.setTextoObs(data[13]);
        vistaDC.setVisible(true);
    }
    
}
