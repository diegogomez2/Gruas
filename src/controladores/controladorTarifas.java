/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaTarifas;

/**
 *
 * @author diego
 */
public class controladorTarifas {
    static vistas.vistaTarifas vistaTarifas;

    void mostrarVistaTarifas(String tipo, Object[][] data) {
        if(vistaTarifas != null) vistaTarifas.setVisible(true);
        vistaTarifas = new vistaTarifas(tipo, data);
        vistaTarifas.setVisible(true);
    }
    
    public JPanel mostrarTabControlTarifas(String tipo, Object[][] data) {
        vistaTarifas = new vistaTarifas(tipo, data);
        return vistaTarifas;
    } 
    
    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(1);
        tabs.insertTab("Tarifas", null, miControlador.crearControladorTarifasP(), null, 1);
        tabs.setSelectedIndex(1);
    }
}
