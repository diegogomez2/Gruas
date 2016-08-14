/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import vistas2.vistaDetalleProveedores;

/**
 *
 * @author Diego
 */
public class controladorDetalleProveedores {
    static vistas2.vistaDetalleProveedores vistaDP;
    
    public void mostrarVistaDetalleProveedor(String rut){
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerProveedorPorRut(rut);
        vistaDP = new vistaDetalleProveedores(new javax.swing.JFrame(), true);
        vistaDP.setTextoRut(data[0]);
        vistaDP.setTextoContacto(data[1]);
        vistaDP.setTextoRazon(data[2]);
        vistaDP.setTextoGiro(data[3]);
        vistaDP.setTextoCorreo(data[4]);
        vistaDP.setTextoTelefono(data[5]);
        vistaDP.setTextoDireccion(data[6]);
        vistaDP.setComboRegion(data[7]);
        vistaDP.setComboCiudad(data[8]);
        vistaDP.setComboComuna(data[9]);
        vistaDP.setTextoObs(data[10]);
        vistaDP.setComboForma(data[11]);
        vistaDP.setComboMedio(data[12]);
        vistaDP.setLocationRelativeTo(null);
        vistaDP.setVisible(true);
    }
    
}
