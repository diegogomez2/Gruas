/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import javax.swing.JTabbedPane;
import vistas.vistaIngresarOts;
import vistas.vistaJornadasP;

/**
 *
 * @author diego
 */
public class controladorIngresarOts {

    static vistas.vistaIngresarOts vistaIO;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatClock = new SimpleDateFormat("HH:mm");
    SimpleDateFormat formatDay = new SimpleDateFormat("E");
    
    void mostrarVistaIngresarOts(String[] data, Object[] ciudades) throws ParseException {
        vistaIO = new vistaIngresarOts(new javax.swing.JFrame(), true, data, ciudades);
        vistaIO.setDiaInicio(data[0]);
        vistaIO.setHoraInicio(data[1]);
        vistaIO.setDiaFin(data[2]);
        vistaIO.setHoraFin(data[3]);
        vistaIO.setTextoGrua(data[4]);
        vistaIO.setTextoEmpleado(data[5]);
        vistaIO.setTextoObs(data[6]);
        vistaIO.setTextoRutCliente(data[7] + "-" + data[8]);
        vistaIO.setTextoRazon(data[9]);
        vistaIO.setTextoRazon2(data[9]);
        vistaIO.setTextoGiro(data[10]);
        vistaIO.setTextoDireccion(data[11]);
        vistaIO.setTextoTelefono(data[12]);
        vistaIO.setId(data[13]);
        vistaIO.setSpinnerHoraSalida(data[1]);
        vistaIO.setSpinnerFinFaena(data[3]);
        vistaIO.setSpinnerHoraLlegada(data[3]);
        vistaIO.setTon(data[14]);
        vistaIO.setTextoCiudad(data[15]);
        vistaIO.setTextoFechaOt();
        List<List<String>> valores = calcularTarifa(data[0], data[2], data[1], data[3], data[14]);
        int size = valores.size();
        vistaIO.setTextoNeto(String.format("%,d", Integer.parseInt(valores.get(size - 1).get(0))));
        vistaIO.setTextoIva(String.format("%,d", Integer.parseInt(valores.get(size - 1).get(1))));
        vistaIO.setTextoBruto(String.format("%,d", Integer.parseInt(valores.get(size - 1).get(2))));
        vistaIO.setHoras((int)Float.parseFloat(valores.get(size - 1).get(3)));
        vistaIO.setLocationRelativeTo(null);
        vistaIO.setVisible(true);        
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(3);
        tabs.insertTab("Jornada diaria", null, miControlador.crearControladorJornadaP(), null, 3);
        tabs.setSelectedIndex(3);
    }
    
    public boolean irVistaJornadasP(String id, int horas) {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String textoNeto, textoIva, textoBruto;
        if(vistaIO.getCheckDespacho().compareTo("1") == 0){
            int valor = Integer.parseInt(vistaIO.getTextoDespacho());
            int iva = (int)(valor * 0.19);
            int bruto = (int)(valor * 1.19);
            textoNeto = Integer.toString(valor + Integer.parseInt(removeDots(vistaIO.getTextoNeto())));
            textoIva = Integer.toString(iva + Integer.parseInt(removeDots(vistaIO.getTextoIva())));
            textoBruto = Integer.toString(bruto + Integer.parseInt(removeDots(vistaIO.getTextoBruto())));
        }else{
            textoNeto = removeDots(vistaIO.getTextoNeto());
            textoIva = removeDots(vistaIO.getTextoIva());
            textoBruto = removeDots(vistaIO.getTextoBruto());
        }
        String[] data = {vistaIO.getTextoContacto(), vistaIO.getTextoFechaOt(), vistaIO.getComboFormaPago(),
            vistaIO.getComboCondPago(), vistaIO.getTextoDespachado(), id, vistaIO.getTextoCodigo(),
            textoNeto, textoIva, textoBruto, vistaIO.getSpinnerFinFaena(), Integer.toString(horas), vistaIO.getSpinnerHoraSalida(),
        vistaIO.getSpinnerHoraLlegada(), vistaIO.getCheckDespacho(), vistaIO.getTextoDespacho()};
        boolean flag = miControlador.ingresarOt(data);
        return flag;
    } 
    
    public String camposVacios(){
        String respuesta = "";
        if (vistaIO.getTextoCodigo().compareTo("") == 0) {
            respuesta += "-Código de OT.\n";
        }
        if (vistaIO.getTextoObs().compareTo("") == 0) {
            respuesta += "-Observaciones.\n";
        }
        if (vistaIO.getTextoFechaOt().compareTo("") == 0) {
            respuesta += "-Fecha de OT.\n";
        }
        return respuesta;
    }
    
    public List<List<String>> calcularTarifa(String diaInicio, String diaFin, String horaInicio, String horaFin, String ton) throws ParseException{
        float horasTotales = 0;
        modelos.modeloOts ots = new modelos.modeloOts();
        List<List<String>> data = new ArrayList<>();
        String[] datos;
        int tarifa = 0;
        Date fecha = formatDate.parse(diaInicio);
        String day1 = formatDay.format(fecha);
        Date fecha2 = formatDate.parse(diaFin);
        int minHor;
        int horMin = 0, minMin = 0;
        if(Integer.parseInt(ton) >= 11){
            minHor = 3;
        }
        minHor = 2;
        long diff = fecha2.getTime() - fecha.getTime();
        long difDias = diff/(60*60*1000*24);
        if(difDias == 0){
            Date horaIn = formatClock.parse(horaInicio);
            Date horaF = formatClock.parse(horaFin);
            long diff2 = horaF.getTime() - horaIn.getTime();
            long minutes = (diff2 / (1000 * 60)) % 60;
            long hours = (diff2 / (1000 * 60 * 60)) % 24;
//            if((hours == minHor && minutes == 0) || (hours < minHor)){
////                tarifa = Integer.parseInt(ots.getMaxTarifa(diaInicio, horaInicio, getIdDia(day1), ton));
////                System.out.println(tarifa);
////                long totalTarifa;
//////                if(Integer.parseInt(ton) < 11){
//////                    totalTarifa = tarifa * 2;
//////                }else{
//////                    totalTarifa = tarifa * 3;
//////                }
////                totalTarifa = tarifa * minHor;
////                data.add(Arrays.asList(Integer.toString((int)totalTarifa), String.valueOf((int)
////                        (totalTarifa * 0.19)), String.valueOf((int)(totalTarifa * 1.19)),
////                        Integer.toString(3), String.valueOf(minHor)));
////                //data = new String[]{Integer.toString((int)totalTarifa), String.valueOf((int)(totalTarifa * 0.19)),
////            //String.valueOf((int)(totalTarifa * 1.19)), Integer.toString(3)};
////            return data;
//            }
            if(hours < minHor){
                horMin = minHor - (int)hours - 1;
                minMin = 60 - (int)minutes;
            }
        }
        String[] horas= new String[2*((int)difDias+1)];
        horas[0] = horaInicio;
        horas[2*((int)difDias+1)-1] = horaFin;
        for(int i = 1; i < 2*(difDias)+1; i++){
            if(i%2 == 0) {
                horas[i] = "00:00";
            }else{
                horas[i] = "24:00";
            }
        }        
        long totalTarifa = 0;
        String hora;
        for(int i = 0; i < difDias+1; i++){
        boolean flag = true;
            while(flag){
                datos = ots.getTarifa(diaInicio, horas[2*i], getIdDia(day1), ton);
                tarifa = Integer.parseInt(datos[0]);
                hora = datos[1];
                if(hora.compareTo("00:00:00") == 0) hora = "24:00:00";
                Date horaIn = formatClock.parse(horas[2*i]);
                Date horaF = formatClock.parse(horas[2*i+1]);
                Date finTramo = formatClock.parse(hora);
                long tramoDiff;// = 0;
                if(finTramo.getTime() > horaF.getTime()){
                    tramoDiff = horaF.getTime() - horaIn.getTime();
                }else{
                    tramoDiff = finTramo.getTime() - horaIn.getTime();
                }
                long minutes = (tramoDiff / (1000 * 60)) % 60;
                long hours = (tramoDiff / (1000 * 60 * 60)) % 24;
                float duracionTramo = hours + (float)minutes/60;
                horasTotales += duracionTramo;
                totalTarifa += duracionTramo * tarifa;
                tramoDiff = horaF.getTime() - finTramo.getTime();
                if(tramoDiff <= 0)  flag = false;
                horas[2*i] = formatClock.format(finTramo.getTime());
                data.add(Arrays.asList(Integer.toString((int)tarifa),String.valueOf((int)(tarifa * 0.19)),
                    String.valueOf((int)(tarifa * 1.19)), Float.toString(duracionTramo)));
            }
            day1 = nextDay(day1);
        }
        if(horMin > 0 || minMin > 0){
            float duracionTramo = horMin + (float)minMin/60;
            totalTarifa += duracionTramo * tarifa;
            data.add(Arrays.asList(Integer.toString((int)tarifa),String.valueOf((int)(tarifa * 0.19)),
             String.valueOf((int)(tarifa * 1.19)), Float.toString(duracionTramo)));
        }
        data.add(Arrays.asList(Integer.toString((int)totalTarifa),String.valueOf((int)(totalTarifa * 0.19)),
        String.valueOf((int)(totalTarifa * 1.19)), Float.toString(horasTotales)));
        return data;
    }
    
    public String nextDay(String dia){
        switch(dia){
            case("lun"):
                return "mar";
            case("mar"):
                return "mié";
            case("mié"):
                return "jue";
            case("jue"):
                return "vie";
            case("vie"):
                return "sáb";
            case("sáb"):
                return "dom";
            case("dom"):
                return "lun";
        }
        return dia;
    }
    
    public String getIdDia(String dia){
        switch(dia){
            case("sáb"):
                return "2";
            case("dom"):
                return "3";
            default:
                return "1";
        }
    }
    
    public String addDots(StringBuilder str){
        int idx = str.length() - 3;
        while(idx > 0){
            str.insert(idx, ".");
            idx -= 3;
        }
        return str.toString();
    }
    
    public String removeDots(String data){
        String new_data = "";
        String[] data_dev = data.split(Pattern.quote("."));
        for(int i = 0; i < data_dev.length; i++){
            new_data = new_data + data_dev[i];
        }
        return new_data;
    }
    
    public void actualizarHorometro(int horas, String desc){
        modelos.modeloGruas grua = new modelos.modeloGruas();
        grua.actualizarHorometro(horas, desc);
    }
}
