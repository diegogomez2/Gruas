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
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import vistas4.vistaModificarJornadasOC;

/**
 *
 * @author diego
 */
public class controladorModificarJornadasOC {
    static vistas4.vistaModificarJornadasOC vistaMJOC;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public void mostrarVistaModificarJornadasOC(String id, Object[] clientes, Object[] gruas, Object[] empleados) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerJornadaOCPorId(id);
        vistaMJOC = new vistaModificarJornadasOC(new javax.swing.JFrame(), true, clientes, gruas, empleados);
        vistaMJOC.setTextoFechaSalida(data[0]);
        vistaMJOC.setTextoFechaRegreso(data[2]);
        vistaMJOC.setTextoObs(data[4]);
        vistaMJOC.setComboCliente(data[7]);
        vistaMJOC.setTextoObsCliente(data[13]);
        vistaMJOC.setSpinnerGruas(data[14]);
        vistaMJOC.setSpinnerEmpleados(data[15]);
        vistaMJOC.setId(id);
        if(Integer.parseInt(data[16]) > 0){
            vistaMJOC.setRadioTras();
            vistaMJOC.hideOC();
            vistaMJOC.setSpinnerTras(data[16]);
        }else{
            vistaMJOC.setRadioTras();
            vistaMJOC.hideTras();
            vistaMJOC.agregarGruas(id);
            vistaMJOC.agregarEmpleados(id);
            vistaMJOC.agregarHoras(id);
        }
        vistaMJOC.setLocationRelativeTo(null);
        vistaMJOC.setVisible(true);
    }
    
    public String camposVacios() {
        String respuesta = "";
        if (vistaMJOC.getTextoFechaSalida().compareTo("") == 0) {
            respuesta += "-Fecha de alida.\n";
        }
        if (vistaMJOC.getTextoFechaRegreso().compareTo("") == 0) {
            respuesta += "-Fecha de regreso.\n";
        }
//        if (vistaMJOC.getTextoObs().compareTo("") == 0) {
//            respuesta += "-Observaciones.\n";
//        }
        if(vistaMJOC.getGruasVacia().compareTo("") == 0){
            respuesta += "-Tabla de grúas.\n";
        }
        if(vistaMJOC.getEmpleadosVacia().compareTo("") == 0){
            respuesta += "-Tabla de operadores.\n";
        }
        return respuesta;
    }
    
    public String camposVaciosTras() {
        String respuesta = "";
        if (vistaMJOC.getTextoFechaSalida().compareTo("") == 0) {
            respuesta += "-Fecha de alida.\n";
        }
        if (vistaMJOC.getTextoFechaRegreso().compareTo("") == 0) {
            respuesta += "-Fecha de regreso.\n";
        }
        return respuesta;
    }
    
    public boolean irVistaJornadasOCP() throws ParseException {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String rut_cli = miControlador.obtenerClientePorRazon(vistaMJOC.getComboCliente());
        int numGruas = vistaMJOC.getSpinnerGruas(), numEmpleados = vistaMJOC.getSpinnerEmpleados();
        String[] rut_emp = new String[numEmpleados], pat_gru = new String[numGruas];
        String[][] fh_emp = new String[numEmpleados][2], fh_gru = new String[numGruas][2];
        JTable tablaGruas = vistaMJOC.getTablaGruas(), tablaEmpleados = vistaMJOC.getTablaEmpleados(), tablaHoras = vistaMJOC.getTablaHoras();
        int numTon = tablaHoras.getRowCount();
        String[][] horas = new String[numTon][4];
        for(int i = 0; i < numGruas; i++){
            if(tablaGruas.getValueAt(i, 0).toString().compareTo("") != 0){
                pat_gru[i] = miControlador.obtenerGruaPorDesc(tablaGruas.getValueAt(i, 0).toString());
                fh_gru[i][0] = vistaMJOC.getTextoFechaSalidaGrua(i) + " " + vistaMJOC.getTextoHoraSalidaGrua(i);
                fh_gru[i][1] = vistaMJOC.getTextoFechaRegresoGrua(i) + " " + vistaMJOC.getTextoHoraRegresoGrua(i);
                //NO SE CHECKEA DISP
//                if(miControlador.checkGruaDisp(pat_gru[i], vistaMJOC.getTextoFechaSalidaGrua(i), vistaMJOC.getTextoHoraSalidaGrua(i), vistaMJOC.getTextoFechaRegresoGrua(i), 
//                        vistaMJOC.getTextoHoraRegresoGrua(i)) > 0) {
//                    JOptionPane.showMessageDialog(null, "Grúa '" + pat_gru[i] + "' no disponible para la fecha agendada.", "Error", JOptionPane.INFORMATION_MESSAGE);
//                    return false;
//                }
            }
        }
        for(int i = 0; i < numEmpleados; i++){
            if(tablaEmpleados.getValueAt(i, 0).toString().compareTo("") != 0){
                rut_emp[i] = miControlador.obtenerEmpleadoPorNombre(tablaEmpleados.getValueAt(i, 0).toString());
                fh_emp[i][0] = vistaMJOC.getTextoFechaSalidaEmp(i) + " " + vistaMJOC.getTextoHoraSalidaEmp(i);
                fh_emp[i][1] = vistaMJOC.getTextoFechaRegresoEmp(i) + " " + vistaMJOC.getTextoHoraRegresoEmp(i);
                //NO SE CHECKEA DISP
//                if(miControlador.checkEmpDisp(rut_emp[i], vistaMJOC.getTextoFechaSalidaEmp(i), vistaMJOC.getTextoHoraSalidaEmp(i), vistaMJOC.getTextoFechaRegresoEmp(i), 
//                        vistaMJOC.getTextoHoraRegresoEmp(i)) > 0) {
//                    JOptionPane.showMessageDialog(null, "Operador '" + rut_emp[i] + "' no disponible para la fecha agendada.", "Error", JOptionPane.INFORMATION_MESSAGE);
//                    return false;
//                }
            }
//            else{
//                rut_emp[i] = "";
//            }
        }
        for(int i = 0; i < numTon; i++){
            horas[i] = new String[]{tablaHoras.getValueAt(i, 0).toString(), tablaHoras.getValueAt(i, 1).toString(), tablaHoras.getValueAt(i, 2).toString(), 
                tablaHoras.getValueAt(i, 3).toString() };
        }
        if(rut_cli == null | rut_emp == null | pat_gru == null){
            return false;
        }
        Date fecha1 = formatDate.parse(vistaMJOC.getTextoFechaSalida());
        Date fecha2 = formatDate.parse(vistaMJOC.getTextoFechaRegreso());
        String fhsal = vistaMJOC.getTextoFechaSalida() + " ";
        String fhreg = vistaMJOC.getTextoFechaRegreso() + " ";
        if(fecha2.before(fecha1)){
            JOptionPane.showMessageDialog(vistaMJOC, "La fecha de llegada debe ser posterior a la fecha de salida", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        String[] data = {vistaMJOC.getTextoFechaSalida(), "", rut_cli, vistaMJOC.getTextoFechaRegreso(), "", vistaMJOC.getTextoObs(), vistaMJOC.getDiaSalida(), vistaMJOC.getDiaRegreso(), fhsal, fhreg};
        if(miControlador.modificarJornadaOC(data, vistaMJOC.getId()).compareTo("correcto") == 0){
            miControlador.borrarDetalleGruas(vistaMJOC.getId());
            miControlador.borrarDetalleEmpleados(vistaMJOC.getId());
            miControlador.borrarHorasBase(vistaMJOC.getId());
            int i = 0;
            for(String grua: pat_gru){
                miControlador.ingresarDetalleGrua(vistaMJOC.getId(), grua, fh_gru[i][0], fh_gru[i][1]);
                i++;
            }
            i = 0;
            for(String emp: rut_emp){
                miControlador.ingresarDetalleEmpleado(vistaMJOC.getId(), emp, fh_emp[i][0], fh_emp[i][1]);
                i++;
            }
            for(String[] hora: horas){
                miControlador.ingresarHoras(vistaMJOC.getId(), hora);
            }
        }else{
            return false;
        }
        return true;
//        return flag;
    }
    
    public boolean irVistaJornadasTrasOCP() throws ParseException {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String rut_cli = miControlador.obtenerClientePorRazon(vistaMJOC.getComboCliente());
        String numTras = String.valueOf(vistaMJOC.getSpinnerTras()); 
        Date fecha1 = formatDate.parse(vistaMJOC.getTextoFechaSalida());
        Date fecha2 = formatDate.parse(vistaMJOC.getTextoFechaRegreso());
        String fhsal = vistaMJOC.getTextoFechaSalida() + " ";
        String fhreg = vistaMJOC.getTextoFechaRegreso() + " ";
        if(fecha2.before(fecha1)){
            JOptionPane.showMessageDialog(vistaMJOC, "La fecha de llegada debe ser posterior a la fecha de salida", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        String[] data = {vistaMJOC.getTextoFechaSalida(), "", rut_cli, vistaMJOC.getTextoFechaRegreso(), "", vistaMJOC.getTextoObs(), vistaMJOC.getDiaSalida(), 
            vistaMJOC.getDiaRegreso(), fhsal, fhreg, numTras};
        if(miControlador.modificarTraspaleta(data, vistaMJOC.getId()).compareTo("correcto") == 0){
            miControlador.borrarDetalleGruas(vistaMJOC.getId());
            miControlador.borrarDetalleEmpleados(vistaMJOC.getId());
            miControlador.borrarHorasBase(vistaMJOC.getId());
        }else{
            return false;
        }
        return true;
//        return flag;
    }
}
