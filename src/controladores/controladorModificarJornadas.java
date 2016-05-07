/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import vistas.vistaModificarJornadas;

/**
 *
 * @author diego
 */
public class controladorModificarJornadas {
    static vistas.vistaModificarJornadas vistaMJ;
    
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
    
    public boolean irVistaJornadasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String rut_cli = miControlador.obtenerClientePorRazon(vistaMJ.getTextoCliente());
        String rut_emp = miControlador.obtenerEmpleadoPorNombre(vistaMJ.getTextoOperador());
        String pat_gru = miControlador.obtenerGruaPorDesc(vistaMJ.getTextoGrua());
        if(rut_cli == null | rut_emp == null | pat_gru == null){
            return false;
        }
        String[] data = {vistaMJ.getTextoFechaSalida(), vistaMJ.getTextoHoraSalida(), pat_gru, rut_cli, 
            rut_emp, vistaMJ.getTextoFechaRegreso(), vistaMJ.getTextoHoraRegreso(), vistaMJ.getTextoObs(),
            vistaMJ.getDiaSalida(), vistaMJ.getDiaRegreso()};
        boolean flag = miControlador.modificarJornada(data, vistaMJ.getId());
        return flag;
    }
}
