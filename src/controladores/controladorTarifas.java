/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaTarifas;

/**
 *
 * @author diego
 */
public class controladorTarifas {
    static vistas.vistaTarifas vistaTarifas;

    void mostrarVistaTarifas(String tipo, Object[][] data) {
        if(vistaTarifas != null) vistaTarifas.setVisible(false);
        vistaTarifas = new vistaTarifas(tipo, data);
        vistaTarifas.setVisible(true);
    }
}
