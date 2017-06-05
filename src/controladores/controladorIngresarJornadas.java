/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import vistas.vistaIngresarJornadas;

/**
 *
 * @author diego
 */
public class controladorIngresarJornadas {

    static vistas.vistaIngresarJornadas vistaIJ;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    void mostrarVistaIngresarJornadas(Object[] clientes, Object[] gruas, Object[] empleados) {
        vistaIJ = new vistaIngresarJornadas(new javax.swing.JFrame(), true, clientes, gruas, empleados);
        vistaIJ.setLocationRelativeTo(null);
        vistaIJ.setVisible(true);    
    }

    public String camposVacios() {
        String respuesta = "";
        System.out.println(vistaIJ);
        if (vistaIJ.getTextoFechaSalida().compareTo("") == 0) {
            respuesta += "-Fecha de salida.\n";
        }
        if (vistaIJ.getTextoHoraSalida().compareTo("") == 0) {
            respuesta += "-Hora de salida.\n";
        }
        if (vistaIJ.getTextoFechaRegreso().compareTo("") == 0) {
            respuesta += "-Fecha de regreso.\n";
        }
        if (vistaIJ.getTextoObs().compareTo("") == 0) {
            respuesta += "-Observaciones.\n";
        }
        if (vistaIJ.getTextoHoraRegreso().compareTo("") == 0) {
            respuesta += "-Hora de regreso.\n";
        }
        return respuesta;
    }

    public boolean irVistaJornadasP() throws ParseException {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String rut_cli = miControlador.obtenerClientePorRazon(vistaIJ.getComboCliente());
        String rut_emp = "", pat_gru = "";
        if(vistaIJ.getTextoOperador().compareTo("") != 0){
            rut_emp = miControlador.obtenerEmpleadoPorNombre(vistaIJ.getTextoOperador());
            if(miControlador.checkEmpDisp(rut_emp, vistaIJ.getTextoFechaSalida(), 
                vistaIJ.getTextoHoraSalida(), vistaIJ.getTextoFechaRegreso(), vistaIJ.getTextoHoraRegreso()) > 0){
            return false;
            }
            
        }
        if(vistaIJ.getTextoGrua().compareTo("") != 0){
            pat_gru = miControlador.obtenerGruaPorDesc(vistaIJ.getTextoGrua());
            if(miControlador.checkGruaDisp(pat_gru, vistaIJ.getTextoFechaSalida(), 
                vistaIJ.getTextoHoraSalida(), vistaIJ.getTextoFechaRegreso(), vistaIJ.getTextoHoraRegreso())
                 > 0){
                return false;
            }
        }
        Date fecha1 = formatDate.parse(vistaIJ.getTextoFechaSalida());
        Date fecha2 = formatDate.parse(vistaIJ.getTextoFechaRegreso());
        String fhsal = vistaIJ.getTextoFechaSalida() + " " + vistaIJ.getTextoHoraSalida();
        String fhreg = vistaIJ.getTextoFechaRegreso() + " " + vistaIJ.getTextoHoraRegreso();
        if(fecha2.before(fecha1)){
            JOptionPane.showMessageDialog(vistaIJ, "La fecha de llegada debe ser posterior a la fecha de salida", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(rut_cli == null | rut_emp == null | pat_gru == null){
            return false;
        }
        String[] data = {vistaIJ.getTextoFechaSalida(), vistaIJ.getTextoHoraSalida(), pat_gru, rut_cli, 
            rut_emp, vistaIJ.getTextoFechaRegreso(), vistaIJ.getTextoHoraRegreso(), vistaIJ.getTextoObs(),
            vistaIJ.getDiaSalida(), vistaIJ.getDiaRegreso(), fhsal, fhreg};
        boolean flag = miControlador.ingresarJornada(data);
        return flag;
    }    
}
