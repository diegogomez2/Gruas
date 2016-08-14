/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import vistas2.vistaModificarCompras;
import controladores.controladorPrincipal;
import java.text.ParseException;

/**
 *
 * @author diego
 */
public class controladorModificarCompras {
    static vistaModificarCompras vistaMC;
    
    public void mostrarVistaModificarCompras(String id, Object proveedores[]) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerCompraPorId(id);
        vistaMC = new vistaModificarCompras(new javax.swing.JFrame(), true, proveedores);
        vistaMC.setComboTipoDTE(data[0]);
        vistaMC.setTextoFolio(data[1]);
        vistaMC.setComboRut(data[2]);
        vistaMC.setTextoRazon(data[3]);
        vistaMC.setTextoGiro(data[4]);
        vistaMC.setTextoDireccion(data[5]);
        vistaMC.setTextoContacto(data[6]);
        vistaMC.setTextoFechaIngreso(data[7]);
        vistaMC.setTextoOrden(data[8]);
        vistaMC.setTextoFechaPago(data[9]);
        vistaMC.setComboForma(data[10]);
        vistaMC.setTextoAsunto(data[11]);
        vistaMC.setTextoObs(data[12]);
        vistaMC.setComboMedio(data[13]);
        vistaMC.setTextoBanco(data[14]);
        vistaMC.setTextoNumTC(data[15]);
        if(data[10].compareTo("Otros pagos") != 0){
            vistaMC.hideOtrosPagos();
        }
        if(data[13].compareTo("Cheque") == 0){
            vistaMC.showCheques(id);
        }
        else if(data[13].compareTo("Tarjeta de crédito") == 0){
            vistaMC.showTC(id);
        }else{
            vistaMC.hideMedioPago();
        }
        vistaMC.setLocationRelativeTo(null);
        vistaMC.setVisible(true);
    }
    
    public boolean irVistaComprasP() {
//        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
//        String[] rut_dv = vistaMC.getTextoRut().split("-");
//        String[] data = {rut_dv[0], rut_dv[1], vistaMC.getTextoContacto(), vistaMC.getTextoRazon(),
//            vistaMC.getTextoGiro(), vistaMC.getTextoCorreo(), vistaMC.getTextoTelefono(), 
//            vistaMC.getTextoDireccion(), vistaMC.getComboRegion(), 
//            vistaMC.getComboCiudad(), vistaMC.getComboComuna(), vistaMC.getTextoObs(), vistaMC.getComboForma(),
//            vistaMC.getComboMedio()};
//        boolean flag = miControlador.modificarProveedor(data, vistaMC.getRut());
//        return flag;
        return false;
    }
        
    public String camposVacios() {
        String respuesta = "";
        if (vistaMC.getTextoFechaIngreso().compareTo("") == 0) {
            respuesta += "-Fecha de ingreso.\n";
        }
        if (vistaMC.getTextoFolio().compareTo("") == 0) {
            respuesta += "-Folio.\n";
        }
        if (vistaMC.getTextoFechaPago().compareTo("") == 0) {
            respuesta += "-Fecha de pago.\n";
        }
        return respuesta;
    }
}
