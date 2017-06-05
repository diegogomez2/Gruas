/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author diego
 */
public class controladorJornadasOC {
    
    vistas4.vistaJornadasOCP vistaOCP;
    
    public JPanel mostrarTabControlJornadasOC(String tipo, Object[][] data) {
        vistaOCP = new vistas4.vistaJornadasOCP(tipo, data);  
        return vistaOCP;
    }
    
    public void irVistaIngresarOC() throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarJornadasOC();
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(0);
        tabs.insertTab("Jornadas OC", null, miControlador.crearControladorJornadasOCP(), null, 0);
        tabs.setSelectedIndex(0);
    }
    
    public void eliminarJornadasOC(String id) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarJornadasOC(id);
    }
    
    public void irVistaDetalleJornadaOC(String id) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleJornadasOC(id); 
    }
    
    public void irVistaModificarJornadasOC(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarJornadasOC(id);
    }
    
    public void irVistaIngresarOcs(String id) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarOcs(id);
    }
    
}
