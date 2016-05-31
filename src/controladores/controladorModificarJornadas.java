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
import vistas.vistaModificarJornadas;

/**
 *
 * @author diego
 */
public class controladorModificarJornadas {
    static vistas.vistaModificarJornadas vistaMJ;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public void mostrarVistaModificarJornadas(String id, Object[] clientes, Object[] gruas, Object[] empleados) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerJornadaPorId(id);
        vistaMJ = new vistaModificarJornadas(new javax.swing.JFrame(), true, clientes, gruas, empleados);
        vistaMJ.setTextoFechaSalida(data[0]);
        vistaMJ.setTextoHoraSalida(data[1]);
        vistaMJ.setTextoFechaRegreso(data[2]);
        vistaMJ.setTextoHoraRegreso(data[3]);
        vistaMJ.setTextoGrua(data[4]);
        vistaMJ.setTextoOperador(data[5]);
        vistaMJ.setTextoObs(data[6]);
        vistaMJ.setTextoCliente(data[9]);
        vistaMJ.setTextoObsCliente(data[16]);
        vistaMJ.setId(id);
        vistaMJ.setLocationRelativeTo(null);
        vistaMJ.setVisible(true);
    }
    
    public String camposVacios() {
        String respuesta = "";
        if (vistaMJ.getTextoFechaSalida().compareTo("") == 0) {
            respuesta += "-Fecha de alida.\n";
        }
        if (vistaMJ.getTextoHoraSalida().compareTo("") == 0) {
            respuesta += "-Hora de salida.\n";
        }
        if (vistaMJ.getTextoFechaRegreso().compareTo("") == 0) {
            respuesta += "-Fecha de regreso.\n";
        }
        if (vistaMJ.getTextoObs().compareTo("") == 0) {
            respuesta += "-Observaciones.\n";
        }
        if (vistaMJ.getTextoHoraRegreso().compareTo("") == 0) {
            respuesta += "-Hora de regreso.\n";
        }
        return respuesta;
    }
    
    public boolean irVistaJornadasP() throws ParseException {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String rut_cli = miControlador.obtenerClientePorRazon(vistaMJ.getComboCliente());
        String rut_emp = "", pat_gru = "";
        if(vistaMJ.getComboOperador().compareTo("") != 0){
            rut_emp = miControlador.obtenerEmpleadoPorNombre(vistaMJ.getComboOperador());
            if(miControlador.checkEmpDispId(rut_emp, vistaMJ.getTextoFechaSalida(), 
                vistaMJ.getTextoHoraSalida(), vistaMJ.getTextoFechaRegreso(), vistaMJ.getTextoHoraRegreso(),
                vistaMJ.getId()) > 0){
            return false;
            }
        }
        if(vistaMJ.getComboGrua().compareTo("") != 0){
            pat_gru = miControlador.obtenerGruaPorDesc(vistaMJ.getComboGrua());  
            if(miControlador.checkGruaDispId(pat_gru, vistaMJ.getTextoFechaSalida(), 
                vistaMJ.getTextoHoraSalida(), vistaMJ.getTextoFechaRegreso(), vistaMJ.getTextoHoraRegreso(),
                vistaMJ.getId()) > 0){
                return false;
            }
        }
        if(rut_cli == null | rut_emp == null | pat_gru == null){
            return false;
        }
        Date fecha1 = formatDate.parse(vistaMJ.getTextoFechaSalida());
        Date fecha2 = formatDate.parse(vistaMJ.getTextoFechaRegreso());
        if(fecha2.before(fecha1)){
            JOptionPane.showMessageDialog(vistaMJ, "La fecha de llegada debe ser posterior a la fecha de salida", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        String[] data = {vistaMJ.getTextoFechaSalida(), vistaMJ.getTextoHoraSalida(), pat_gru, rut_cli, 
            rut_emp, vistaMJ.getTextoFechaRegreso(), vistaMJ.getTextoHoraRegreso(), vistaMJ.getTextoObs(),
            vistaMJ.getDiaSalida(), vistaMJ.getDiaRegreso()};
        boolean flag = miControlador.modificarJornada(data, vistaMJ.getId());
        return flag;
    }
}
