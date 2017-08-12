/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import modelos.modeloEmpleados;
import modelos.modeloGruas;
import vistas4.vistaIngresarOcs;
import vistas4.vistaIngresarTraspaletaOcs;

/**
 *
 * @author diego
 */
public class controladorIngresarOcs {
    
    static vistas4.vistaIngresarOcs vistaIO;
    static vistas4.vistaIngresarTraspaletaOcs vistaITO;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public void mostrarVistaIngresarOcs(String[] data, Object[] ciudades, Object[] dataTon) throws ParseException {
        vistaIO = new vistaIngresarOcs(new javax.swing.JFrame(), true, dataTon);
        vistaIO.setDiaInicio(data[0]);
        vistaIO.setHoraInicio(data[1]);
        vistaIO.setDiaFin(data[2]);
        vistaIO.setHoraFin(data[3]);
        vistaIO.setTextoObs(data[4]);
        vistaIO.setTextoRutCliente(data[5] + "-" + data[6]);
        vistaIO.setTextoRazon(data[7]);
        vistaIO.setTextoRazon2(data[7]);
        vistaIO.setTextoGiro(data[8]);
        vistaIO.setTextoDireccion(data[9]);
        vistaIO.setTextoTelefono(data[10]);
        vistaIO.setId(data[11]);
        vistaIO.setTextoCiudad(data[12]);
        vistaIO.setTextoFechaOt();
        vistaIO.setTextoFechaInicio(data[0]);
        vistaIO.setTextoFechaTermino(data[2]);
//        int size = valores.size();
        vistaIO.setTextoNeto("0");
        vistaIO.setTextoIva("0");
        vistaIO.setTextoBruto("0");
        vistaIO.setTextoDescuento("0");
        vistaIO.setNuevoNeto(0);
        vistaIO.setTextoNuevoNeto("0");
        vistaIO.agregarGruas(data[11]);
        vistaIO.agregarEmpleados(data[11]);
        vistaIO.agregarHoras(data[11]);
//        vistaIO.setHoras((int)Float.parseFloat(valores.get(size - 1).get(3)));
        vistaIO.setLocationRelativeTo(null);
        vistaIO.setVisible(true);  
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(0);
        tabs.insertTab("Jornadas OC", null, miControlador.crearControladorJornadasOCP(), null, 0);
        tabs.setSelectedIndex(0);
    }
    
    public boolean irVistaJornadasOCP(String id, int horas) {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String textoNeto, textoIva, textoBruto, textoDesc;
        textoNeto = Integer.toString(Integer.parseInt(vistaIO.getTextoNuevoNeto()));
        textoDesc = Integer.toString(Integer.parseInt(vistaIO.getTextoDescuento()));
        textoIva = Integer.toString(/*iva*/ + Integer.parseInt(vistaIO.getTextoIva()));
        textoBruto = Integer.toString(/*bruto*/ + Integer.parseInt(vistaIO.getTextoBruto()));
        String[] data = {vistaIO.getTextoContacto(), vistaIO.getTextoFechaOc(), vistaIO.getComboFormaPago(),
            vistaIO.getComboCondPago(), vistaIO.getTextoDespachado(), id, vistaIO.getTextoCodigo(),
            textoNeto, textoIva, textoBruto, vistaIO.getCheckDespacho(), vistaIO.getTextoDespacho(), textoDesc};
        boolean flag = miControlador.ingresarOc(data);
        return flag;
    } 
    
    public String camposVacios(){
        String respuesta = "";
        if (vistaIO.getTextoCodigo().compareTo("") == 0) {
            respuesta += "-Código de OC.\n";
        }
//        if (vistaIO.getTextoObs().compareTo("") == 0) {
//            respuesta += "-Observaciones.\n";
//        }
        if (vistaIO.getTextoFechaOc().compareTo("") == 0) {
            respuesta += "-Fecha de OT.\n";
        }
        if(vistaIO.getHorasEmpleadosVacia().compareTo("") == 0){
            respuesta += "Falta asignar horas al (los) trabajador(es)";
        }
        return respuesta;
    }
    
    public String camposVacios2(){
        String respuesta = "";
        if (vistaITO.getTextoCodigo().compareTo("") == 0) {
            respuesta += "-Código de OC.\n";
        }
//        if (vistaIO.getTextoObs().compareTo("") == 0) {
//            respuesta += "-Observaciones.\n";
//        }
        if (vistaITO.getTextoFechaOc().compareTo("") == 0) {
            respuesta += "-Fecha de OT.\n";
        }
        return respuesta;
    }
    
    public void actualizarHoras(String id){
        JTable tablaHoras = vistaIO.getTablaHoras();
        int numHoras = tablaHoras.getRowCount();
        String[][] horas = new String[numHoras][5];
        modelos4.modeloOcs ocs = new modelos4.modeloOcs();
        for(int i = 0; i < numHoras; i++){
            horas[i] = new String[]{tablaHoras.getValueAt(i, 4).toString(), tablaHoras.getValueAt(i, 5).toString(), tablaHoras.getValueAt(i, 6).toString(), 
                tablaHoras.getValueAt(i, 7).toString(), tablaHoras.getValueAt(i, 0).toString()};
        }
        for(String[] hora: horas){
            ocs.actualizarHoras(id, hora);
        }
    }
    
    public void actualizarHorometro(){
        JTable tablaGruas = vistaIO.getTablaGruas();
        int numGruas = tablaGruas.getRowCount();
        String[][] horas = new String[numGruas][3];
        for(int i = 0; i < numGruas; i++){
            System.out.println(tablaGruas.getModel().getValueAt(i, 6));
            horas[i] = new String[]{tablaGruas.getValueAt(i, 5).toString(), tablaGruas.getValueAt(i, 0).toString(), tablaGruas.getModel().getValueAt(i, 6).toString()};
        }
        modelos.modeloGruas grua = new modeloGruas();
        for(String[] hora: horas){
            System.out.println(hora[0] + " " + hora[1] + " " + hora[2]);
            int h = (int)Double.parseDouble(hora[0]);
            int id = Integer.parseInt(hora[2]);
            grua.actualizarHorometro(h, hora[1]);
            grua.actualizarHorasDetalleGruas(id, h);
        }
    }
    
    public void agregarMensualidad(int mes, int year){
        JTable tablaEmpleados = vistaIO.getTablaEmpleados();
        int numEmp = tablaEmpleados.getRowCount();
        String[][] horas = new String[numEmp][8];
        for(int i = 0; i < numEmp; i++){
            System.out.println(tablaEmpleados.getModel().getValueAt(i, 11));
            horas[i] = new String[]{tablaEmpleados.getValueAt(i, 0).toString(), tablaEmpleados.getValueAt(i, 5).toString(), tablaEmpleados.getValueAt(i, 6).toString(), tablaEmpleados.getValueAt(i, 7).toString(), 
            tablaEmpleados.getValueAt(i, 8).toString(), tablaEmpleados.getValueAt(i, 9).toString(), tablaEmpleados.getValueAt(i, 10).toString(), tablaEmpleados.getModel().getValueAt(i, 11).toString()};
        }
        modelos.modeloEmpleados empleado = new modeloEmpleados();
        for(String[] hora: horas){
            String rut = empleado.obtenerEmpleadoPorNombre(hora[0]);
            int id = Integer.parseInt(hora[7]);
             empleado.agregarMensualidad(rut, mes, year, Double.parseDouble(hora[1]), Double.parseDouble(hora[2]), Double.parseDouble(hora[3]), Integer.parseInt(hora[4]), 
                    Integer.parseInt(hora[5]), hora[6]);
             empleado.agregarHorasDetalleEmp(id, Double.parseDouble(hora[1]), Double.parseDouble(hora[2]), Double.parseDouble(hora[3]), Double.parseDouble(hora[4]), 
                     Integer.parseInt(hora[5]), hora[6]);
        }
    }

    public void mostrarVistaIngresarTraspaletaOcs(String[] data, Object[] ciudades) throws ParseException {
        vistaITO = new vistaIngresarTraspaletaOcs(new javax.swing.JFrame(), true);
//        vistaITO.setDiaInicio(data[0]);
//        vistaITO.setHoraInicio(data[1]);
//        vistaITO.setDiaFin(data[2]);
//        vistaITO.setHoraFin(data[3]);
        vistaITO.setTextoObs(data[4]);
        vistaITO.setTextoRutCliente(data[5] + "-" + data[6]);
        vistaITO.setTextoRazon(data[7]);
        vistaITO.setTextoRazon2(data[7]);
        vistaITO.setTextoGiro(data[8]);
        vistaITO.setTextoDireccion(data[9]);
        vistaITO.setTextoTelefono(data[10]);
        vistaITO.setId(data[11]);
        vistaITO.setTextoCiudad(data[12]);
        vistaITO.setTextoFechaOt();
        vistaITO.setTextoFechaInicio(data[0]);
        vistaITO.setTextoFechaTermino(data[2]);
        vistaITO.setTextoIva("0");
        vistaITO.setTextoBruto("0");
        vistaITO.setTextoDescuento("0");
        vistaITO.setNuevoNeto(0);
        vistaITO.setTextoNuevoNeto("0");
        vistaITO.setNumTras(data[16]);
//        vistaIO.setHoras((int)Float.parseFloat(valores.get(size - 1).get(3)));
        vistaITO.setLocationRelativeTo(null);
        vistaITO.setVisible(true);  
    }

    public boolean irVistaJornadasTraspaletaOCP(String id) {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String textoNeto, textoIva, textoBruto, textoDesc;
        textoNeto = Integer.toString(Integer.parseInt(vistaITO.getTextoNuevoNeto()));
        textoDesc = Integer.toString(Integer.parseInt(vistaITO.getTextoDescuento()));
        textoIva = Integer.toString(/*iva*/ + Integer.parseInt(vistaITO.getTextoIva()));
        textoBruto = Integer.toString(/*bruto*/ + Integer.parseInt(vistaITO.getTextoBruto()));
        String[] data = {vistaITO.getTextoContacto(), vistaITO.getTextoFechaOc(), vistaITO.getComboFormaPago(),
            vistaITO.getComboCondPago(), vistaITO.getTextoDespachado(), id, vistaITO.getTextoCodigo(),
            textoNeto, textoIva, textoBruto, vistaITO.getCheckDespacho(), vistaITO.getTextoDespacho(), textoDesc};
        boolean flag = miControlador.ingresarOc(data);
        return flag;
    }
    
}
