/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaControlGruas;
import vistas.vistaGruasP;

/**
 *
 * @author Diego
 */
public class controladorGruas {
    static vistas.vistaControlGruas vistaGruas;
    static vistas.vistaGruasP vistaGruasP;
    
    public void mostrarVistaControlGruas(String tipo, Object[][] data){
        if(vistaGruas != null) vistaGruas.setVisible(false);
        vistaGruas = new vistaControlGruas(tipo, data);
        vistaGruas.setVisible(true);
    }

    public void irVistaIngresarGruas() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarGruas();
    }
    
    public void eliminarGruas(String patente){
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarGruas(patente);
    }

    public void irVistaModificarGruas(String patente, String descripcion) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarGruas(patente, descripcion);
    }

    public void irVistaDetalleGrua(String patente) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleGrua(patente);    
    }

    JPanel mostrarTabControlGruas(String tipo, Object[][] data) {
        //if(vistaGruas != null) vistaGruas.setVisible(false);
        vistaGruasP = new vistaGruasP(tipo, data);
        //vistaGruas.setVisible(true);
        return vistaGruasP;
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(1);
        tabs.insertTab("Grúas", null, miControlador.crearControladorGruasP(), null, 1);
        tabs.setSelectedIndex(1);
    }
}
