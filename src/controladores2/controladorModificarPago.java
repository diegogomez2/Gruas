/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import java.text.ParseException;
import modelos2.modeloCobranzas;
import vistas2.vistaModificarPago;

/**
 *
 * @author diego
 */
public class controladorModificarPago {
    static vistaModificarPago vistaMP;
    
    public void mostrarVistaModificarPago(Object[] data) throws ParseException{
        vistaMP = new vistaModificarPago(new javax.swing.JFrame(), true);
        modelos2.modeloCobranzas cobranza = new modeloCobranzas();
        int saldo = cobranza.getSaldo(data[0].toString()) + Integer.parseInt(data[2].toString());
        int montoAnt = Integer.parseInt(data[2].toString());
        vistaMP.setSaldo(saldo);
        vistaMP.setMontoAnt(montoAnt);
        vistaMP.setId(data[0].toString());
        vistaMP.setComboOpcionPago(data[1].toString());
        vistaMP.setTextoMonto(data[2].toString());
        vistaMP.setTextoFechaPago(data[3].toString());
        vistaMP.setComboMedioPago(data[4].toString());
        vistaMP.setTextoBanco(data[5].toString());
        vistaMP.setTextoNumeroCuentaCheque(data[6].toString());
        vistaMP.setLocationRelativeTo(null);
        vistaMP.setVisible(true);
    }
    
    public boolean irVistaCobranzasP() {
        modelos2.modeloCobranzas cobranza = new modeloCobranzas();
        String id = vistaMP.getId();
        String[] data = {vistaMP.getTextoOpcionPago(), vistaMP.getTextoMonto(), vistaMP.getFecha(), vistaMP.getComboMedio(), vistaMP.getTextoBanco(), 
        vistaMP.getTextoNum(), String.valueOf(vistaMP.getMontAnt())};
        if(cobranza.modificarPago(data, id)){
            return true;
        }
        return false; 
    }
        
}
