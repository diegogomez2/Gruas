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
    
    public void mostrarVistaGestionPago(String id, String fol, String tipo){
        modelos2.modeloCobranzas cobranza = new modeloCobranzas();
        int saldo = cobranza.getSaldo(fol, tipo);
        vistaGP = new vistaGestionPago(new javax.swing.JFrame(), true, saldo);
        vistaGP.hideMedioPago();
        vistaGP.setIdFac(id);
        vistaGP.setFolio(fol);
        vistaGP.setTipo(tipo);
        vistaGP.setLocationRelativeTo(null);
        vistaGP.setVisible(true);
    }
    
    public boolean irVistaCobranzasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String id = vistaGP.getIdFac();
        String folio = vistaGP.getFolio();
        String tipo = vistaGP.getTipo();
        String tipoPag = vistaGP.getTextoOpcionPago();
        String monto = vistaGP.getTextoMonto();
        String fec = vistaGP.getFecha();
        String med = vistaGP.getComboMedio();
        String ban = vistaGP.getTextoBanco();
        String num = vistaGP.getTextoNum();
        if(!miControlador.gestionPago(id, folio, tipo, tipoPag, monto, fec, med, ban, num)){
            return false;
        }
        return true;
    }
}
