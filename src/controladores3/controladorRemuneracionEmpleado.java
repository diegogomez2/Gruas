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
        String[][] imp2cat = miControlador.obtenerTablaImpuesto();
//        float uf = miControlador.obtenerUF() / 100;
        float uf = miControlador.obtenerUF();
        //SUELDO BASE
        int base = Integer.parseInt(data[2]) * Integer.parseInt(data[22]) / 30;
        //GRATIFICACION
        int grat = (int)(base * 0.25);
        //BONO ANTIGUEDAD
        int bonoAnt = miControlador.obtenerBonoAnt(data[5]);
        //BONO 300
        //BACKUP
//        int bono300 = miControlador.obtenerBono300();
//        int totalBon300 = bono300 * Integer.parseInt(data[9]);
        int totalBon300 = Integer.parseInt(data[21]);
        //BONO ADICIONAL
        int bonoAd = Integer.parseInt(data[10]);
        //BONO RESPONSABILIDAD
        int bonoResp = 0;
        //BONO ASIGNACION VOLUNTARIA
        int bonoAV = Integer.parseInt(data[13]);
        int totalBonoAV = (int)((double) base * 0.0077777 * bonoAV);
        //BONO ADICIONAL
        int bonoCol = Integer.parseInt(data[8]);
        int totalBonCol = (int)(((double) base * 0.0077777) * ((double)bonoCol / 2));
        //HORAS EXTRA
        int horasEx = Integer.parseInt(data[11]);
        int cantHorEx = Integer.parseInt(data[12]);
        int totalHorEx = (int)((double) base * 0.0077777 * horasEx);
//        System.out.println(horasEx);
//        System.out.println(cantHorEx);
//        System.out.println(totalHorEx);
        //TOTAL IMPONIBLE
        int totImp = base + grat + bonoAnt + bonoAd + bonoResp + totalBonoAV + totalBonCol + totalBon300 + totalHorEx;
        //DESCUENTO AFP
        int descAFP = miControlador.obtenerDescAFP(data[3]);
        int totalAFP = (int)(totImp * ((double)descAFP / 10000));
        int sis = (int)(totImp * 0.0141);
        //DESCUENTO SALUD
        double descSalud = 0, totalSalud = 0;
        String salud;
        if(data[4].toLowerCase().compareTo("fonasa") == 0){
            salud = "FONASA";
            descSalud = miControlador.obtenerDescSalud(data[4]);
            totalSalud = (int)(totImp * ((double)descSalud / 10000));
        }else{
            salud = miControlador.obtenerIsapreEmpleado(rut);
            descSalud = (miControlador.obtenerDescIsapre(rut) / 1000.0) * uf;
            totalSalud = descSalud;
        }
        //DESCUENTO CESANTIA
        int ces = (int)(totImp * 0.006);
        int cesEmp = (int)(totImp * 0.024);
        //TOTAL TRIBUTABLE
        double totTrib = totImp - totalAFP - totalSalud - ces;
        int descRenta = 0;
        double totAux = 0;
        for(String[] imp2cat1: imp2cat){
            if(totTrib > Float.parseFloat(imp2cat1[0]) / 10 && totTrib <= Float.parseFloat(imp2cat1[1]) / 10){
                descRenta = (int) (totTrib * Float.parseFloat(imp2cat1[2]) / 1000 - Float.parseFloat(imp2cat1[3]) / 100);
                totAux = totTrib - descRenta;
                break;
            }
        }
        //CAJA COMPENSACION
        int caja = Integer.parseInt(data[14]);
        //ASIGNACION FAMILIAR
        int af = Integer.parseInt(data[15]);
        //LIQ ALCANZADO
        double liqAl = totAux - caja;
        //COLACION 
        int col = Integer.parseInt(data[6]);
        //TRANSPORTE
        int trans = Integer.parseInt(data[7]);
        //ANTICIPO ADELANTO PRESTAMOS
        int antic = Integer.parseInt(data[16]);
        int adel = Integer.parseInt(data[17]);
        int pres = Integer.parseInt(data[18]);
        int cuo = Integer.parseInt(data[19]);
        int cuoPres = 0;
        if(cuo != 0){
            cuoPres = pres / cuo;
        }
        int cuoRes = Math.max(0, Integer.parseInt(data[20]) - 1);
        //DESCUENTOS
        int totalDesc = caja + antic + adel + cuoPres;
        //TOTAL NO IMP
        int noImp = col + trans + af;
        //TOTAL HABERES
        int totalHaberes = noImp + totImp;
        //LIQUIDO
        double liq = liqAl + col + trans + af - antic - adel - cuoPres;
        vistaRE = new vistaRemuneracionEmpleado(new javax.swing.JFrame(), true);
        vistaRE.setTextoRut(data[0]);
        vistaRE.setTextoNombre(data[1]);
        vistaRE.setTextoSueldoBase(String.valueOf(base));
        vistaRE.setTextoGratLegal(String.valueOf(grat));
        vistaRE.setTextoBonoAntiguedad(String.valueOf(bonoAnt));
        vistaRE.setTextoBono300(String.valueOf(totalBon300));
        vistaRE.setTextoBonoAV(String.valueOf(totalBonoAV));
        vistaRE.setTextoBonoAd(String.valueOf(totalBonCol));
        vistaRE.setTextoOtrosBonos(String.valueOf(bonoAd));
        vistaRE.setTextoHorEx(String.valueOf(totalHorEx));
        vistaRE.setTextoTotalImponible(String.valueOf(totImp));
        vistaRE.setTextoAFP(data[3]);
        vistaRE.setTextoSalud(salud);
        vistaRE.setTextoDescAFP(String.valueOf(totalAFP));
        vistaRE.setTextoDescSalud(String.valueOf(totalSalud));
        vistaRE.setTextoCesantia(String.valueOf(ces));
        vistaRE.setTextoColacion(data[6]);
        vistaRE.setTextoTransporte(data[7]);
        vistaRE.setTextoTotalTributable(String.valueOf(totTrib));
        vistaRE.setTextoCaja(String.valueOf(caja));
        vistaRE.setTextoAF(String.valueOf(af));
        vistaRE.setTextoNoImp(String.valueOf(noImp));
        vistaRE.setTextoTotalHaberes(String.valueOf(totalHaberes));
        vistaRE.setTextoAnticipo(String.valueOf(antic));
        vistaRE.setTextoAdelanto(String.valueOf(adel));
        vistaRE.setTextoPrestamo(String.valueOf(cuoPres));
        vistaRE.setTextoSueldoLiquido(String.valueOf(liq));
        vistaRE.setCantHorEx(String.valueOf(cantHorEx));
        vistaRE.setCuotasRes(String.valueOf(cuoRes));
        vistaRE.setTextoImpRenta(String.valueOf(descRenta));
        vistaRE.setTextoSIS(String.valueOf(sis));
        vistaRE.setTextoCesantiaEmp(String.valueOf(cesEmp));
        vistaRE.setTextoTotalDesc(String.valueOf(totalDesc));
        vistaRE.setLocationRelativeTo(null);
        vistaRE.setVisible(true);
    }
}
