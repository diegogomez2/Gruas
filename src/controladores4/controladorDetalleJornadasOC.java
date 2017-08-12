/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.text.ParseException;
import vistas4.vistaDetalleJornadasOC;
import vistas4.vistaDetalleJornadasTraspaletaOC;

/**
 *
 * @author diego
 */
public class controladorDetalleJornadasOC {
    static vistas4.vistaDetalleJornadasOC vistaDJOC;
    static vistas4.vistaDetalleJornadasTraspaletaOC vistaDJTOC;
    
public void mostrarVistaDetalleJornadasOC(String[] data, String id) throws ParseException{
//        controladorPrincipal miControlador = new controladorPrincipal();
//        String data[] = miControlador.obtenerJornadaOCPorId(id);
        vistaDJOC = new vistaDetalleJornadasOC(new javax.swing.JFrame(), true);
        vistaDJOC.setTextoFechaSalida(data[0]);
//        vistaDJOC.setTextoHoraSalida(data[1]);
        vistaDJOC.setTextoFechaRegreso(data[2]);
//        vistaDJOC.setTextoHoraRegreso(data[3]);
        vistaDJOC.setTextoObs(data[4]);
        vistaDJOC.setTextoCliente(data[7]);
        vistaDJOC.setTextoObsCliente(data[13]);
        vistaDJOC.setSpinnerGruas(data[14]);
        vistaDJOC.setSpinnerEmpleados(data[15]);
        vistaDJOC.agregarGruas(id);
        vistaDJOC.agregarEmpleados(id);
        vistaDJOC.agregarHoras(id);
        vistaDJOC.setLocationRelativeTo(null);
        vistaDJOC.setVisible(true);
    }

    public void mostrarVistaDetalleJornadasTraspaletaOC(String[] data, String id) throws ParseException {
        vistaDJTOC = new vistaDetalleJornadasTraspaletaOC(new javax.swing.JFrame(), true);
        vistaDJTOC.setTextoFechaSalida(data[0]);
//        vistaDJOC.setTextoHoraSalida(data[1]);
        vistaDJTOC.setTextoFechaRegreso(data[2]);
//        vistaDJOC.setTextoHoraRegreso(data[3]);
        vistaDJTOC.setTextoCliente(data[7]);
        vistaDJTOC.setTextoObsCliente(data[13]);
        vistaDJTOC.setTextoObs(data[4]);
        vistaDJTOC.setSpinnerNumTras(data[16]);
        vistaDJTOC.setLocationRelativeTo(null);
        vistaDJTOC.setVisible(true);
    }
}
