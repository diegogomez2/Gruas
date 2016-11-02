/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaRemuneracionEmpleado;

/**
 *
 * @author diego
 */
public class controladorRemuneracionEmpleado {
    static vistas3.vistaRemuneracionEmpleado vistaRE;

    public void mostrarVistaRemuneracionEmpleado(String rut){
        controladorPrincipal miControlador = new controladorPrincipal();
        String[] data = miControlador.obtenerRemuneracionEmpleadoPorRut(rut);
        //SUELDO BASE
        int base = Integer.parseInt(data[2]);
        //GRATIFICACION
        int grat = (int)(base * 0.25);
        //BONO ANTIGUEDAD
        int bonoAnt = miControlador.obtenerBonoAnt(data[5]);
        //BONO 300
        int bono300 = miControlador.obtenerBono300();
        int totalBon300 = bono300 * Integer.parseInt(data[9]);
        //BONO RESPONSABILIDAD
        int bonoResp = 0;
        //BONO ASIGNACION VOLUNTARIA
        int bonoAV = 0;
        //BONO COLACION
        int bonoCol = Integer.parseInt(data[8]);
        int totalBonCol = (int)(((double) base * 0.0077777) * ((double)bonoCol / 2));
        //HORAS EXTRA
        int horasEx = 0;
        //TOTAL IMPONIBLE
        int totImp = base + grat + bonoAnt + bonoResp + bonoAV + totalBonCol + totalBon300 + horasEx;
        //DESCUENTO AFP
        int descAFP = miControlador.obtenerDescAFP(data[3]);
        int totalAFP = (int)(totImp * ((double)descAFP / 1000));
        //DESCUENTO SALUD
        int descSalud = 0, totalSalud = 0;
        String salud;
        if(data[4].toLowerCase().compareTo("fonasa") == 0){
            salud = "FONASA";
            descSalud = miControlador.obtenerDescSalud(data[4]);
            totalSalud = (int)(totImp * ((double)descSalud / 10000));
        }else{
            salud = miControlador.obtenerIsapreEmpleado(rut);
            descSalud = miControlador.obtenerDescIsapre(rut);
            totalSalud = descSalud;
        }
        //DESCUENTO CESANTIA
        int ces = (int)(totImp * 0.006);
        //COLACION 
        int col = Integer.parseInt(data[6]);
        //TRANSPORTE
        int trans = Integer.parseInt(data[7]);
        //TOTAL TRIBUTABLE
        int totTrib = totImp - totalAFP - totalSalud - ces + col + trans;
        //LIQUIDO
        int liq = totTrib;
        vistaRE = new vistaRemuneracionEmpleado(new javax.swing.JFrame(), true);
        vistaRE.setTextoRut(data[0]);
        vistaRE.setTextoNombre(data[1]);
        vistaRE.setTextoSueldoBase(data[2]);
        vistaRE.setTextoGratLegal(String.valueOf(grat));
        vistaRE.setTextoBonoAntiguedad(String.valueOf(bonoAnt));
        vistaRE.setTextoBono300(String.valueOf(totalBon300));
        vistaRE.setTextoBonoCol(String.valueOf(totalBonCol));
        vistaRE.setTextoOtrosBonos(String.valueOf(0));
        vistaRE.setTextoTotalImponible(String.valueOf(totImp));
        vistaRE.setTextoAFP(data[3]);
        vistaRE.setTextoSalud(salud);
        vistaRE.setTextoDescAFP(String.valueOf(totalAFP));
        vistaRE.setTextoDescSalud(String.valueOf(totalSalud));
        vistaRE.setTextoCesantia(String.valueOf(ces));
        vistaRE.setTextoColacion(data[6]);
        vistaRE.setTextoTransporte(data[7]);
        vistaRE.setTextoTotalTributable(String.valueOf(totTrib));
        vistaRE.setTextoSueldoLiquido(String.valueOf(liq));
        vistaRE.setLocationRelativeTo(null);
        vistaRE.setVisible(true);
    }
}
