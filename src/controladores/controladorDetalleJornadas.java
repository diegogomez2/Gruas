/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import vistas.vistaDetalleJornadas;

/**
 *
 * @author diego
 */
public class controladorDetalleJornadas {
    static vistas.vistaDetalleJornadas vistaDJ;
    
public void mostrarVistaDetalleJornadas(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerJornadaPorId(id);
        vistaDJ = new vistaDetalleJornadas(new javax.swing.JFrame(), true);
        vistaDJ.setTextoFechaSalida(data[0]);
        vistaDJ.setTextoHoraSalida(data[1]);
        vistaDJ.setTextoFechaRegreso(data[2]);
        vistaDJ.setTextoHoraRegreso(data[3]);
        vistaDJ.setTextoGrua(data[4]);
        vistaDJ.setTextoOperador(data[5]);
        vistaDJ.setTextoObs(data[6]);
        vistaDJ.setTextoCliente(data[9]);
        vistaDJ.setTextoObsCliente(data[16]);
        vistaDJ.setLocationRelativeTo(null);
        vistaDJ.setVisible(true);
    }

}
