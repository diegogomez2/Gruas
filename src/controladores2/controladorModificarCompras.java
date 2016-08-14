/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import vistas2.vistaModificarCompras;
import controladores.controladorPrincipal;
import java.text.ParseException;
import javax.swing.JTable;
import modelos2.modeloCompras;

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
        vistaMC.setId(id);
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
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        modelos2.modeloCompras compra = new modeloCompras();
        String folioInterno = compra.folioFac();
        boolean flag;
        String id = vistaMC.getId();
        String[] rut_dv = vistaMC.getComboRut().split("-");
        String[] data = {rut_dv[0], vistaMC.getComboTipoDTE(), vistaMC.getTextoFolio(), folioInterno, 
            vistaMC.getTextoFechaIngreso(), vistaMC.getTextoOrden(), vistaMC.getTextoFechaPago(),
            vistaMC.getComboForma(), vistaMC.getTextoAsunto(), vistaMC.getTextoObs(), 
            vistaMC.getComboMedio(), vistaMC.getTextoBanco(), vistaMC.getTextoNumTC()};
        if(!miControlador.modificarCompra(data, id)){
            return false;
        }
        int cuotas;
        miControlador.borrarCheques(id);
        JTable tabla;
        switch(vistaMC.getCuotas()){
            case 1:
                cuotas = vistaMC.getSpinnerCant();
                String[][] dataCQ = new String[cuotas][5];
                tabla = vistaMC.getTablaCuotas();
                for(int i = 0; i < cuotas; i++){
                    dataCQ[i] = new String[]{tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(),
                        vistaMC.getfechaTablaCheques(i), tabla.getValueAt(i, 3).toString(),
                        vistaMC.getEstadoTablaCheques(i)};
                }
                flag = miControlador.ingresarCheques(dataCQ, id);
                return flag;
            case 2:
                cuotas = vistaMC.getSpinnerCant();
                String[][] dataTC = new String[cuotas][4];
                tabla = vistaMC.getTablaCuotas();
                for(int i = 0; i < cuotas; i++){
                    dataTC[i] = new String[]{tabla.getValueAt(i, 0).toString(),vistaMC.getfechaTablaTC(i),
                        tabla.getValueAt(i, 2).toString(), vistaMC.getEstadoTablaTC(i)};
                }
                flag = miControlador.ingresarCuotas(dataTC, id);
                return flag;
            default:
                return true;
        }
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
