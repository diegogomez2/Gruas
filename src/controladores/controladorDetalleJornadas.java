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
        vistaDJ.setTextoHoraSalida(data[0]);
        vistaDJ.setTextoGrua(data[1]);
        vistaDJ.setTextoCliente(data[2]);
        vistaDJ.setTextoOperador(data[3]);
        vistaDJ.setTextoFechaRegreso(data[4]);
        vistaDJ.setTextoHoraRegreso(data[4]);
        vistaDJ.setTextoObs(data[5]);
        vistaDJ.setVisible(true);
    }

}
