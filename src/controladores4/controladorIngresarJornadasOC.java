/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import vistas4.vistaIngresarJornadasOC;

/**
 *
 * @author diego
 */
public class controladorIngresarJornadasOC {
    
    static vistas4.vistaIngresarJornadasOC vistaIJOC;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat dt = new SimpleDateFormat("yyyy-MM-dd HH:mm");
    
    public void mostrarVistaIngresarJornadasOC(Object[] clientes, Object[] gruas, Object[] empleados, Object[] tonelajes) throws ParseException {
        vistaIJOC = new vistaIngresarJornadasOC(new javax.swing.JFrame(), true, clientes, gruas, empleados, tonelajes);
        vistaIJOC.setLocationRelativeTo(null);
        vistaIJOC.setVisible(true);    
    }

    public String camposVacios() {
        String respuesta = "";
        if (vistaIJOC.getTextoFechaSalida().compareTo("") == 0) {
            respuesta += "-Fecha de salida.\n";
        }
        if (vistaIJOC.getTextoFechaRegreso().compareTo("") == 0) {
            respuesta += "-Fecha de regreso.\n";
        }
        if (vistaIJOC.getTextoObs().compareTo("") == 0) {
            respuesta += "-Observaciones.\n";
        }
        if(vistaIJOC.getGruasVacia().compareTo("") == 0){
            respuesta += "-Tabla de grúas.\n";
        }
        if(vistaIJOC.getEmpleadosVacia().compareTo("") == 0){
            respuesta += "-Tabla de operadores.\n";
        }
        return respuesta;
    }

    public boolean irVistaJornadasOCP() throws ParseException {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String rut_cli = miControlador.obtenerClientePorRazon(vistaIJOC.getComboCliente());
        int numGruas = vistaIJOC.getSpinnerGruas(), numEmpleados = vistaIJOC.getSpinnerEmpleados();
        String[] rut_emp = new String[numEmpleados], pat_gru = new String[numGruas];
        String[][] fh_emp = new String[numEmpleados][2], fh_gru = new String[numGruas][2];
        JTable tablaGruas = vistaIJOC.getTablaGruas(), tablaEmpleados = vistaIJOC.getTablaEmpleados(), tablaHoras = vistaIJOC.getTablaHoras();
        int numTon = tablaHoras.getRowCount();
        String[][] horas = new String[numTon][4];
        for(int i = 0; i < numGruas; i++){
            if(tablaGruas.getValueAt(i, 0).toString().compareTo("") != 0){
                pat_gru[i] = miControlador.obtenerGruaPorDesc(tablaGruas.getValueAt(i, 0).toString());
                fh_gru[i][0] = vistaIJOC.getTextoFechaSalidaGrua(i) + " " + vistaIJOC.getTextoHoraSalidaGrua(i);
                fh_gru[i][1] = vistaIJOC.getTextoFechaRegresoGrua(i) + " " + vistaIJOC.getTextoHoraRegresoGrua(i);
                if(miControlador.checkGruaDisp(pat_gru[i], vistaIJOC.getTextoFechaSalidaGrua(i), vistaIJOC.getTextoHoraSalidaGrua(i), vistaIJOC.getTextoFechaRegresoGrua(i), 
                        vistaIJOC.getTextoHoraRegresoGrua(i)) > 0) {
                    JOptionPane.showMessageDialog(null, "Grúa '" + pat_gru[i] + "' no disponible para la fecha agendada.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }
        }
        for(int i = 0; i < numEmpleados; i++){
            if(tablaEmpleados.getValueAt(i, 0).toString().compareTo("") != 0){
                rut_emp[i] = miControlador.obtenerEmpleadoPorNombre(tablaEmpleados.getValueAt(i, 0).toString());
                fh_emp[i][0] = vistaIJOC.getTextoFechaSalidaEmp(i) + " " + vistaIJOC.getTextoHoraSalidaEmp(i);
                fh_emp[i][1] = vistaIJOC.getTextoFechaRegresoEmp(i) + " " + vistaIJOC.getTextoHoraRegresoEmp(i);
                if(miControlador.checkEmpDisp(rut_emp[i], vistaIJOC.getTextoFechaSalidaEmp(i), vistaIJOC.getTextoHoraSalidaEmp(i), vistaIJOC.getTextoFechaRegresoEmp(i), 
                        vistaIJOC.getTextoHoraRegresoEmp(i)) > 0) {
                    JOptionPane.showMessageDialog(null, "Operador '" + rut_emp[i] + "' no disponible para la fecha agendada.", "Error", JOptionPane.INFORMATION_MESSAGE);
                    return false;
                }
            }else{
                rut_emp[i] = "";
            }
        }
        for(int i = 0; i < numTon; i++){
            horas[i] = new String[]{tablaHoras.getValueAt(i, 0).toString(), tablaHoras.getValueAt(i, 1).toString(), tablaHoras.getValueAt(i, 2).toString(), 
                tablaHoras.getValueAt(i, 3).toString() };
        }
        
        Date fecha1 = formatDate.parse(vistaIJOC.getTextoFechaSalida());
        Date fecha2 = formatDate.parse(vistaIJOC.getTextoFechaRegreso());
        String fhsal = vistaIJOC.getTextoFechaSalida() + " " + vistaIJOC.getTextoHoraSalida();
        String fhreg = vistaIJOC.getTextoFechaRegreso() + " " + vistaIJOC.getTextoHoraRegreso();
        if(fecha2.before(fecha1)){
            JOptionPane.showMessageDialog(vistaIJOC, "La fecha de llegada debe ser posterior a la fecha de salida", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(rut_cli == null | rut_emp == null | pat_gru == null){
            return false;
        }
        boolean flag = true;
        
        String[] data = {vistaIJOC.getTextoFechaSalida(), vistaIJOC.getTextoHoraSalida(), rut_cli, vistaIJOC.getTextoFechaRegreso(), vistaIJOC.getTextoHoraRegreso(), 
            vistaIJOC.getTextoObs(), vistaIJOC.getDiaSalida(), vistaIJOC.getDiaRegreso(), fhsal, fhreg};
        String idJor = miControlador.ingresarJornadaOC(data);
        if(idJor.compareTo("incorrecto") != 0){
            int i = 0;
            for(String grua: pat_gru){
                miControlador.ingresarDetalleGrua(idJor, grua, fh_gru[i][0], fh_gru[i][1]);
                i++;
            }
            i = 0;
            for(String emp: rut_emp){
                miControlador.ingresarDetalleEmpleado(idJor, emp, fh_emp[i][0], fh_emp[i][1]);
                i++;
            }
            for(String[] hora: horas){
                miControlador.ingresarHoras(idJor, hora);
            }
        }else{
            return false;
        }
        return flag;
    }    
}
