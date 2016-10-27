/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import modelos.modeloFacturas;
import vistas2.vistaGestionCobranza;

/**
 *
 * @author diego
 */
public class controladorGestionCobranza {
    static vistaGestionCobranza vistaGC;
    
    public void mostrarVistaGestionCobranza(String id, String tipo){
        controladorPrincipal miControlador = new controladorPrincipal();
        vistaGC = new vistaGestionCobranza(new javax.swing.JFrame(), true);
        vistaGC.setFolio(id);
        vistaGC.setTipo(tipo);
        vistaGC.setLocationRelativeTo(null);
        vistaGC.setVisible(true);
    }
    
    public boolean irVistaCobranzasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String folio = vistaGC.getFolio();
        String tipo = vistaGC.getTipo();
        String ges = vistaGC.getComboTipoGes();
        String res = vistaGC.getRadioGestion();
        String fec = vistaGC.getFecha();
        String obs = vistaGC.getObs();
        if(!miControlador.gestionCobranza(folio, tipo, ges, res, fec, obs)){
            return false;
        }
        return true;
    }
}
