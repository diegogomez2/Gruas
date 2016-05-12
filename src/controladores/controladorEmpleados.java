/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaEmpleadosP;

/**
 *
 * @author Diego
 */
public class controladorEmpleados {

    public JPanel mostrarTabControlEmpleados(String tipo, Object[][] data) {
        vistaEmpleadosP vistaEmpleadosP = new vistaEmpleadosP(tipo, data);
        return vistaEmpleadosP;    
    }

    public void irVistaDetalleEmpleado(String rut) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleEmpleado(rut); 
    }

    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(2);
        tabs.insertTab("Trabajadores", null, miControlador.crearControladorEmpleadosP(), null, 2);
        tabs.setSelectedIndex(2);   
    }

    public void irVistaIngresarEmpleados() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarEmpleados();    }

    public void eliminarEmpleados(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarEmpleados(rut);    }

    public void irVistaModificarEmpleados(String rut, String nombres) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarEmpleados(rut, nombres);
    }
    
}
