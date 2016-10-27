/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import modelos.modeloFacturas;
import modelos2.modeloCobranzas;
import vistas2.vistaGestionPago;

/**
 *
 * @author diego
 */
public class controladorGestionPago {
    static vistaGestionPago vistaGP;
    
    public void mostrarVistaGestionPago(String id){
        controladorPrincipal miControlador = new controladorPrincipal();
        modelos2.modeloCobranzas cobranza = new modeloCobranzas();
        int saldo = cobranza.getSaldo(id);
        vistaGP = new vistaGestionPago(new javax.swing.JFrame(), true, saldo);
        vistaGP.setFolio(id);
        vistaGP.setLocationRelativeTo(null);
        vistaGP.setVisible(true);
    }
    
    public boolean irVistaCobranzasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String folio = vistaGP.getFolio();
        String monto = vistaGP.getTextoMonto();
//        String
//        String tipo = vistaGP.getComboTipo();
//        String res = vistaGC.getRadioGestion();
//        String fec = vistaGC.getFecha();
//        String obs = vistaGC.getObs();
        if(!miControlador.gestionPago(folio, monto)){
            return false;
        }
        return true;
    }
}
