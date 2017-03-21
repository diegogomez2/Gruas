/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author diego
 */
public class controladorOC {
    
    vistas4.vistaOCP vistaOCP;
    
    public JPanel mostrarTabControlOC(String tipo, Object[][] data) {
        vistaOCP = new vistas4.vistaOCP(tipo, data);  
        return vistaOCP;
    }
    
    public void irVistaIngresarOC() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarOC();
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(0);
        tabs.insertTab("OCS", null, miControlador.crearControladorOCP(), null, 0);
        tabs.setSelectedIndex(0);
    }
    
}
