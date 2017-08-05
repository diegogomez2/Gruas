/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import java.text.ParseException;
import modelos2.modeloCobranzas;
import vistas2.vistaModificarGestion;

/**
 *
 * @author diego
 */
public class controladorModificarGestion {
    static vistaModificarGestion vistaMG;
    
    public void mostrarVistaModificarGestion(Object[] data) throws ParseException{
        vistaMG = new vistaModificarGestion(new javax.swing.JFrame(), true);
        modelos2.modeloCobranzas cobranza = new modeloCobranzas();
        vistaMG.setComboTipoGes(data[1].toString());
        vistaMG.setRadioGestion(data[2].toString());
        vistaMG.setId(data[0].toString());
        vistaMG.setTextoFechaRes(data[3].toString());
        vistaMG.setObs(data[4].toString());
        vistaMG.setLocationRelativeTo(null);
        vistaMG.setVisible(true);
    }
    
    public boolean irVistaCobranzasP() {
        modelos2.modeloCobranzas cobranza = new modeloCobranzas();
        String id = vistaMG.getId();
        String[] data = {vistaMG.getComboTipoGes(), vistaMG.getRadioGestion(), vistaMG.getFecha(), vistaMG.getObs()};
        return cobranza.modificarGestion(data, id); 
    }
}
