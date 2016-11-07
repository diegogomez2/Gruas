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
    
    public void irVistaRemuneraciones(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorRemuneracionesEmpleado(rut); 
    }
    
    public void irVistaPresAdelanto(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorPresAdelanto(rut); 
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
    
    public void agregarColacion(String rut, int horas){
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        empleado.agregarColacion(rut, horas);
    }
    
    public void agregarHoras(String rut, int horas){
        modelos.modeloEmpleados emp = new modelos.modeloEmpleados();
        emp.actualizarHorasMes(rut, horas);
    }
    
    public void agregarHorasExtra(String rut, int horas, int horasCalc){
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        empleado.agregarHorasExtra(rut, horas, horasCalc);
    }
    
    
}
