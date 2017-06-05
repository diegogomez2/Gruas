/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
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
        miControlador.crearControladorIngresarEmpleados();    
    }

    public void eliminarEmpleados(String rut) {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEliminarEmpleados(rut);    
    }

    public void irVistaModificarEmpleados(String rut, String nombres) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorModificarEmpleados(rut, nombres);
    }
    
    public void agregarColacion(String rut, int horas){
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        empleado.agregarColacion(rut, horas);
    }
    
    public void agregarHoras(String rut, int horas, String ton){
        modelos.modeloEmpleados emp = new modelos.modeloEmpleados();
        emp.actualizarHorasMes(rut, horas, ton); //agregar ton
    }
    
    public void agregarHorasExtra(String rut, double horas, double horasCalc){
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        empleado.agregarHorasExtra(rut, horas, horasCalc);
    }
    
    public void agregarMensualidad(String rut, int mes, int year, double horas, double horasNormales, double horasFestivas, int horasColacion30, int horasColacion1, String ton){
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        empleado.agregarMensualidad(rut, mes, year, horas, horasNormales, horasFestivas, horasColacion30, horasColacion1, ton);
    }
    
    public void restarMensualidad(String idOt) throws ParseException{
        int mes, year;
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        String[] data = empleado.obtenerHorasOt(idOt);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = sdf.parse(data[1]);
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        if(cal.get(Calendar.DAY_OF_MONTH) > 25){
            mes = cal.get(Calendar.MONTH) + 2;
        }else{
            mes = cal.get(Calendar.MONTH) + 1;
        }
        year = cal.get(Calendar.YEAR);
        empleado.restarMensualidad(data[0], mes, year, data[2], data[3], data[4], data[5], data[6], data[7]);
        
    }
    
}
