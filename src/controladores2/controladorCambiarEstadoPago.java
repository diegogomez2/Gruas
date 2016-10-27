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
    
    public void mostrarVistaCambiarEstadoPago(String id, String fac){
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerCuotaPorId(id);
        vistaCEP = new vistaCambiarEstadoPago(new javax.swing.JFrame(), true);
        vistaCEP.setEstado(data[3]);
        vistaCEP.setId(id);
        vistaCEP.setFac(fac);
        vistaCEP.setLocationRelativeTo(null);
        vistaCEP.setVisible(true);
    }
    
    public boolean irVistaAgendaDePagosP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        modelos2.modeloCompras compra = new modeloCompras();
        String id = vistaCEP.getId();
        String fac = vistaCEP.getFac();
        String estado = vistaCEP.getEstado();
        if(!miControlador.cambiarEstadoPago(estado, id, fac)){
            return false;
        }
        return true;
    }

}
