/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import vistas.vistaDetalleOts;

/**
 *
 * @author diego
 */
public class controladorDetalleOts {
    static vistas.vistaDetalleOts vistaDO;
    
    public void mostrarVistaDetalleOts(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerOtPorId(id);
        vistaDO = new vistaDetalleOts(new javax.swing.JFrame(), true);
        vistaDO.setSpinnerHoraSalida(data[1]);
        vistaDO.setSpinnerHoraLlegada(data[3]);
        vistaDO.setTextoGrua(data[4]);
        vistaDO.setTextoEmpleado(data[5]);
        vistaDO.setTextoObs(data[6]);
        vistaDO.setTextoRutCliente(data[7]+"-"+data[8]);
        vistaDO.setTextoRazon(data[9]);
        vistaDO.setTextoRazon2(data[9]);
        vistaDO.setTextoGiro(data[10]);
        vistaDO.setTextoDireccion(data[11]);
        vistaDO.setTextoTelefono(data[12]);
        vistaDO.setTextoFechaOt(data[15]);
        vistaDO.setComboCondPago(data[16]);
        vistaDO.setComboFormaPago(data[17]);
        vistaDO.setTextoContacto(data[18]);
        vistaDO.setTextoBruto(data[19]);
        vistaDO.setTextoNeto(String.valueOf(Integer.parseInt(data[20]) - Integer.parseInt(data[25]) + Integer.parseInt(data[27])));
        vistaDO.setTextoIva(data[21]);
//        vistaDO.setTextoNuevoNeto(String.valueOf(Integer.parseInt(data[20]) - Integer.parseInt(data[27])));
        vistaDO.setTextoNuevoNeto(String.valueOf(Integer.parseInt(data[20])));
        vistaDO.setTextoDescuento(data[27]);
        vistaDO.setTextoDespachado(data[22]);
        vistaDO.setSpinnerFinFaena(data[23]);
        vistaDO.setCheckDespacho(data[24]);
        vistaDO.setCheckHorMin(data[28]);
        vistaDO.setTextoValorDespacho(data[25]);
        vistaDO.setTextoCiudad(data[26]);
        vistaDO.setSpinnerHoraExNormales(data[29]);
        vistaDO.setSpinnerHoraExFestivos(data[30]);
        vistaDO.setSpinnerHoraColacion30(data[31]);
        vistaDO.setSpinnerHoraColacion1(data[32]);
        vistaDO.setTextoCodigo(id);
        vistaDO.setLocationRelativeTo(null);
        vistaDO.setVisible(true);
    }
}
