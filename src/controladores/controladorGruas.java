/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaGruasP;

/**
 *
 * @author Diego
 */
public class controladorGruas {
    static vistas.vistaGruasP vistaGruasP;


    public void irVistaIngresarGruas() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarGruas();
    }
    
    public void eliminarGruas(String patente){
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarGruas(patente);
    }

    public void irVistaModificarGruas(String patente) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarGruas(patente);
    }

    public void irVistaDetalleGrua(String patente) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleGrua(patente);    
    }

    JPanel mostrarTabControlGruas(String tipo, Object[][] data) {
        vistaGruasP = new vistaGruasP(tipo, data);
        return vistaGruasP;
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(1);
        tabs.insertTab("Grúas", null, miControlador.crearControladorGruasP(), null, 1);
        tabs.setSelectedIndex(1);
    }
    
    public void agregarHoras(String desc, int horas){
        modelos.modeloGruas grua = new modelos.modeloGruas();
        grua.actualizarHorometro(horas, desc);
    }
}
