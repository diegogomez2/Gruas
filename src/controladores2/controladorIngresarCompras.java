/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores2;

import controladores.*;
import java.awt.Frame;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import modelos2.modeloCompras;
import vistas.vistaIngresarClientes;
import vistas2.vistaIngresarCompras;

/**
 *
 * @author Diego
 */
public class controladorIngresarCompras {

    static vistaIngresarCompras vistaIC;

    public void mostrarVistaIngresarCompras(Object proveedores[]) {
        vistaIC = new vistaIngresarCompras(new javax.swing.JFrame(), true, proveedores);
        vistaIC.setLocationRelativeTo(null);
        vistaIC.setVisible(true);
    }

    public boolean irVistaComprasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        modelos2.modeloCompras compra = new modeloCompras();
        String folioInterno = compra.folioFac();
        boolean flag;
        String[] rut_dv = vistaIC.getComboRut().split("-");
        String[] data = {rut_dv[0], vistaIC.getComboTipoDTE(), vistaIC.getTextoFolio(), folioInterno, 
            vistaIC.getTextoFechaIngreso(), vistaIC.getTextoOrden(), vistaIC.getTextoFechaPago(),
            vistaIC.getComboForma(), vistaIC.getTextoAsunto(), vistaIC.getTextoObs(), 
            vistaIC.getComboMedio(), vistaIC.getTextoBanco(), vistaIC.getTextoNumTC(), vistaIC.getCheckEstado()};
        String id = miControlador.ingresarCompra(data);
        if(id.compareTo("incorrecto") == 0){
            return false;
        }
        int cuotas;
        JTable tabla;
        switch(vistaIC.getCuotas()){
            case 1:
                cuotas = vistaIC.getSpinnerCant();
                String[][] dataCQ = new String[cuotas][5];
                tabla = vistaIC.getTablaCuotas();
                for(int i = 0; i < cuotas; i++){
                    dataCQ[i] = new String[]{tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(),
                        vistaIC.getfechaTablaCheques(i), tabla.getValueAt(i, 3).toString(),
                        vistaIC.getEstadoTablaCheques(i)};
                }
                flag = miControlador.ingresarCheques(dataCQ, id);
                if(!flag) return flag;
                break;
            case 2:
                cuotas = vistaIC.getSpinnerCant();
                String[][] dataTC = new String[cuotas][4];
                tabla = vistaIC.getTablaCuotas();
                for(int i = 0; i < cuotas; i++){
                    dataTC[i] = new String[]{tabla.getValueAt(i, 0).toString(),vistaIC.getfechaTablaTC(i),
                        tabla.getValueAt(i, 2).toString(), vistaIC.getEstadoTablaTC(i)};
                }
                flag = miControlador.ingresarCuotas(dataTC, id);
                if(!flag) return flag;
                break;
            default:
                break;
        }
        tabla = vistaIC.getTablaDetalle();
        if(tabla.getRowCount() > 0) return ingresarProductos(tabla, id);
        return true;
    }

    public boolean verificarRut(String rut) {
        if (rut.compareTo("") != 0) {
            String[] rut_dv = rut.split("-");
            if (rut_dv.length == 2) {
                try {
                    int num = Integer.parseInt(rut_dv[0]);
                    char dv = rut_dv[1].charAt(0);
                    if (validarRut(num, dv)) {
                        return true;
                    } else {
                        JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
                        return false;
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }
            } else {
                JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
                return false;
            }
        } else {
            JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
            return false;
        }
        JOptionPane.showMessageDialog(vistaIC, "Rut incorrecto");
        return false;
    }

    public static boolean validarRut(int rut, char dv) {
        int m = 0, s = 1;
        for (; rut != 0; rut /= 10) {
            s = (s + rut % 10 * (9 - m++ % 6)) % 11;
        }
        return dv == (char) (s != 0 ? s + 47 : 107);
    }

    public String camposVacios() {
        String respuesta = "";
        if (vistaIC.getTextoFechaIngreso().compareTo("") == 0) {
            respuesta += "-Fecha de ingreso.\n";
        }
        if (vistaIC.getTextoFolio().compareTo("") == 0) {
            respuesta += "-Folio.\n";
        }
        if (vistaIC.getTextoFechaPago().compareTo("") == 0) {
            respuesta += "-Fecha de pago.\n";
        }
        if(vistaIC.getTablaCuotas().getRowCount() > 0){
            if(!vistaIC.checkTablaDatos()){
                respuesta += "-Tabla de cuotas.\n";
            }
        }
        if(vistaIC.getTablaDetalle().getRowCount() > 0){
            if(!vistaIC.checkTablaDetalles()){
                respuesta += "-Tabla detalles.\n";
            }
        }
        return respuesta;
    }

    public Object[] cargarComunas(int region) {
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.cargarComunas(region);
    }
    
    public Object[] cargarCiudades(int region) {
        controladorPrincipal miControlador = new controladorPrincipal();
        return miControlador.cargarCiudades(region);
    }
    
    public boolean ingresarProductos(JTable tabla, String id){
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        int cant = tabla.getRowCount();
        String[][] dataDetalle = new String[cant][5];
        for(int i = 0; i < cant; i++){
            dataDetalle[i] = new String[]{tabla.getValueAt(i, 0).toString(), tabla.getValueAt(i, 1).toString(),
                        tabla.getValueAt(i, 2).toString(), tabla.getValueAt(i, 3).toString(), 
                        vistaIC.getIva(i)};
        }
        return miControlador.ingresarProductos(dataDetalle, id);
    }
}
