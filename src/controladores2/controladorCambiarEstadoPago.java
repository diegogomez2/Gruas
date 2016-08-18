/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import modelos2.modeloCompras;
import vistas2.vistaCambiarEstadoPago;

/**
 *
 * @author diego
 */
public class controladorCambiarEstadoPago {
    static vistaCambiarEstadoPago vistaCEP;
    
    public void mostrarVistaCambiarEstadoPago(String id){
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerCuotaPorId(id);
        vistaCEP = new vistaCambiarEstadoPago(new javax.swing.JFrame(), true);
        vistaCEP.setEstado(data[3]);
        vistaCEP.setId(id);
        vistaCEP.setLocationRelativeTo(null);
        vistaCEP.setVisible(true);
    }
    
    public boolean irVistaAgendaDePagosP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        modelos2.modeloCompras compra = new modeloCompras();
        String id = vistaCEP.getId();
        String estado = vistaCEP.getEstado();
        if(!miControlador.cambiarEstadoPago(estado, id)){
            return false;
        }
        return true;
    }

}
