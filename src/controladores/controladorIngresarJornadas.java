/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import javax.swing.JRootPane;
import javax.swing.JTabbedPane;
import vistas.vistaIngresarJornadas;

/**
 *
 * @author diego
 */
public class controladorIngresarJornadas {

    static vistas.vistaIngresarJornadas vistaIJ;
    
    void mostrarVistaIngresarJornadas(Object[][] clientes, Object[][] gruas, Object[][] empleados) {
        vistaIJ = new vistaIngresarJornadas(new javax.swing.JFrame(), true, clientes, gruas, empleados);
        vistaIJ.setLocationRelativeTo(null);
        vistaIJ.setVisible(true);    
    }

    public String camposVacios() {
        String respuesta = "";
        if (vistaIJ.getTextoFechaSalida().compareTo("") == 0) {
            respuesta += "-Fecha de alida.\n";
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

    public boolean irVistaPrincipal() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] data = {vistaIJ.getTextoFechaSalida(), vistaIJ.getTextoHoraSalida(), 
            vistaIJ.getTextoGrua(), vistaIJ.getTextoCliente(), vistaIJ.getTextoOperador(), 
            vistaIJ.getTextoFechaRegreso(), vistaIJ.getTextoHoraRegreso(), vistaIJ.getTextoObs()};
        boolean flag = miControlador.ingresarJornada(data);
        return flag;
    }
    
}
