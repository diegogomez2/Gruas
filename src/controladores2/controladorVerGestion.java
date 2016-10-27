/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import vistas2.vistaVerGestion;

/**
 *
 * @author diego
 */
public class controladorVerGestion {
    static vistaVerGestion vistaVG;
    
    public void mostrarVistaVerGestion(String id, String tipo){
        controladorPrincipal miControlador = new controladorPrincipal();
        Object[][] data = miControlador.obtenerGestion(id, tipo);
        vistaVG = new vistaVerGestion(new javax.swing.JFrame(), true, data);
        vistaVG.setLocationRelativeTo(null);
        vistaVG.setVisible(true);
    }
    
//    public boolean irVistaCobranzasP() {
//        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
//        String folio = vistaVG.getFolio();
//        String tipo = vistaVG.getTipo();
//        String ges = vistaVG.getComboTipoGes();
//        String res = vistaVG.getRadioGestion();
//        String fec = vistaVG.getFecha();
//        String obs = vistaVG.getObs();
//        if(!miControlador.gestionCobranza(folio, tipo, ges, res, fec, obs)){
//            return false;
//        }
//        return true;
//    }
}
