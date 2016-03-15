/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Component;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import vistas.vistaControlEmpleados;
import vistas.vistaEmpleadosP;

/**
 *
 * @author Diego
 */
public class controladorEmpleados {
    static vistas.vistaControlEmpleados vistaEmpleados;

    public void mostrarVistaControlEmpleados(String tipo, Object[][] data) {
        if(vistaEmpleados != null) vistaEmpleados.setVisible(false);
        vistaEmpleados = new vistaControlEmpleados(tipo, data);
        vistaEmpleados.setVisible(true);
    }

    public JPanel mostrarTabControlEmpleados(String tipo, Object[][] data) {
        //if(vistaClientes != null) vistaClientes.setVisible(false);
        vistaEmpleadosP vistaEmpleadosP = new vistaEmpleadosP(tipo, data);
        
        //vistaClientes.setVisible(true);   
        return vistaEmpleadosP;    
    }

    public void irVistaDetalleEmpleado(String rut) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleEmpleado(rut); 
    }

    public void crearControladorPrincipal(JTabbedPane tabs) {
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(3);
        tabs.insertTab("Empleados", null, miControlador.crearControladorEmpleadosP(), null, 3);
        tabs.setSelectedIndex(3);    }

    public void irVistaIngresarEmpleados() {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorIngresarEmpleados();    }
    
}
