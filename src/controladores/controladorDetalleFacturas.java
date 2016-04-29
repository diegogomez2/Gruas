/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import vistas.vistaDetalleFacturas;

/**
 *
 * @author diego
 */
public class controladorDetalleFacturas {
    static vistas.vistaDetalleFacturas vistaDF;
    
    public void mostrarVistaDetalleFacturas(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerOtPorId(id);
        vistaDF = new vistaDetalleFacturas(new javax.swing.JFrame(), true);
        vistaDF.setSpinnerHoraSalida(data[1]);
        vistaDF.setSpinnerHoraLlegada(data[3]);
        vistaDF.setTextoGrua(data[4]);
        vistaDF.setTextoEmpleado(data[5]);
        vistaDF.setTextoObs(data[6]);
        vistaDF.setTextoRutCliente(data[7]+"-"+data[8]);
        vistaDF.setTextoRazon(data[9]);
        vistaDF.setTextoRazon2(data[9]);
        vistaDF.setTextoGiro(data[10]);
        vistaDF.setTextoDireccion(data[11]);
        vistaDF.setTextoTelefono(data[12]);
        vistaDF.setTextoFechaOt(data[15]);
        vistaDF.setComboCondPago(data[16]);
        vistaDF.setComboFormaPago(data[17]);
        vistaDF.setTextoContacto(data[18]);
        vistaDF.setTextoBruto(data[19]);
        vistaDF.setTextoNeto(data[20]);
        vistaDF.setTextoIva(data[21]);
        vistaDF.setTextoDespachado(data[22]);
        vistaDF.setSpinnerFinFaena(data[23]);
        vistaDF.setTextoCodigo(id);
        vistaDF.setLocationRelativeTo(null);
        vistaDF.setVisible(true);
    }
}
