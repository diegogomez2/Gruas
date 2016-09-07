/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import static controladores.controladorUsuarios.vistaUsuariosP;
import java.awt.Frame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaCambioClave;
import vistas.vistaUsuariosP;

/**
 *
 * @author Diego
 */
public class controladorUsuarios {
    static vistas.vistaCambioClave vistaCambioClave;
    static vistas.vistaUsuariosP vistaUsuariosP;
    public controladorUsuarios() {
    }

    void mostrarVistaCambioClave(String tipo, String data) {
        if(vistaCambioClave != null) vistaCambioClave.setVisible(false);
        vistaCambioClave = new vistaCambioClave(tipo, data);
        vistaCambioClave.setVisible(true);
    }
    
    public void irVistaIngresarUsuarios(JTabbedPane tab) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorAgregarUsuario(tab);
    }
    
    public JPanel mostrarTabControlUsuarios(String tipo, Object[][] data) {
        vistaUsuariosP = new vistaUsuariosP(tipo, data);
        return vistaUsuariosP;
    }
    
    public void eliminarUsuario(String user) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarUsuario(user);
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(0);
        tabs.insertTab("Usuarios", null, miControlador.crearControladorUsuariosP(), null, 0);
        tabs.setSelectedIndex(0);
    }
}
