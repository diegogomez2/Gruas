/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.controladorPrincipal;
import java.text.ParseException;
import vistas2.vistaDetalleCompras;
/**
 *
 * @author diego
 */
public class controladorDetalleCompras {
    static vistaDetalleCompras vistaDC;
    
    public void mostrarVistaDetalleCompras(String id) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerCompraPorId(id);
        vistaDC = new vistaDetalleCompras(new javax.swing.JFrame(), true);
        vistaDC.setComboTipoDTE(data[0]);
        vistaDC.setTextoFolio(data[1]);
        vistaDC.setTextoRut(data[2]);
        vistaDC.setTextoRazon(data[3]);
        vistaDC.setTextoGiro(data[4]);
        vistaDC.setTextoDireccion(data[5]);
        vistaDC.setTextoContacto(data[6]);
        vistaDC.setTextoFechaIngreso(data[7]);
        vistaDC.setTextoOrden(data[8]);
        vistaDC.setTextoFechaPago(data[9]);
        vistaDC.setComboForma(data[10]);
        vistaDC.setTextoAsunto(data[11]);
        vistaDC.setTextoObs(data[12]);
        vistaDC.setComboMedio(data[13]);
        vistaDC.setTextoBanco(data[14]);
        vistaDC.setTextoNumTC(data[15]);
        vistaDC.setCheckEstado(data[16]);
        vistaDC.setComboClas(data[17]);
        vistaDC.setTextoNeto(data[18]);
        vistaDC.setTextoIva(data[19]);
        vistaDC.setTextoTot(data[20]);
        vistaDC.setTextoImpuestoEsp(data[21]);
        vistaDC.setTextoImpuestoVar(data[22]);
        if(data[10].compareTo("Otros pagos") != 0){
            vistaDC.hideOtrosPagos();
        }
        if(data[13].compareTo("Cheque") == 0){
            vistaDC.showCheques(id);
        }
        else if(data[13].compareTo("Tarjeta de crédito") == 0){
            vistaDC.showTC(id);
        }else if(data[13].compareTo("Transferencia") == 0){
            vistaDC.showTR(id);
        }else{
            vistaDC.hideMedioPago();
        }
        vistaDC.agregarProductos(id);
        vistaDC.agregarImpuestos(id);
        vistaDC.setLocationRelativeTo(null);
        vistaDC.setVisible(true);
    }
}
