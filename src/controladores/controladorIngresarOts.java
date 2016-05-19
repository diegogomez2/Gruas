/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
        //setters
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
        String[] data = {vistaIO.getTextoContacto(), vistaIO.getTextoFechaOt(), vistaIO.getComboFormaPago(),
            vistaIO.getComboCondPago(), vistaIO.getTextoDespachado(), id, vistaIO.getTextoCodigo(),
            removeDots(vistaIO.getTextoNeto()), removeDots(vistaIO.getTextoIva()), 
            removeDots(vistaIO.getTextoBruto()), vistaIO.getSpinnerFinFaena(), Integer.toString(horas), vistaIO.getSpinnerHoraSalida(),
        vistaIO.getSpinnerHoraLlegada()};
        boolean flag = miControlador.ingresarOt(data);
        return flag;
    } 
    
    public String camposVacios(){
        String respuesta = "";
        if (vistaIO.getTextoCodigo().compareTo("") == 0) {
            respuesta += "-Código de OT.\n";
        }
//        if (vistaIO.getTextoContacto().compareTo("") == 0) {
//            respuesta += "-Contacto.\n";
//        }
//        if (vistaIO.getTextoDespachado().compareTo("") == 0) {
//            respuesta += "-Despachado por.\n";
//        }
        if (vistaIO.getTextoObs().compareTo("") == 0) {
            respuesta += "-Observaciones.\n";
        }
        if (vistaIO.getTextoFechaOt().compareTo("") == 0) {
            respuesta += "-Fecha de OT.\n";
        }
        return respuesta;
    }
    
    public String[] calcularTarifa(String diaInicio, String diaFin, String horaInicio, String horaFin, String ton) throws ParseException{
        int horasTotales = 0;
        modelos.modeloOts ots = new modelos.modeloOts();
        String[] data;// = {};
        int tarifa;// = 0;
        Date fecha = formatDate.parse(diaInicio);
        String day1 = formatDay.format(fecha);
        Date fecha2 = formatDate.parse(diaFin);
        //String day2 = formatDay.format(fecha);
        
        long diff = fecha2.getTime() - fecha.getTime();
        long difDias = diff/(60*60*1000*24);
        String[] horas= new String[2*((int)difDias+1)];
        horas[0] = horaInicio;
        horas[2*((int)difDias+1)-1] = horaFin;
        for(int i = 1; i < 2*(difDias)+1; i++){
            //System.out.println(i%2);
            if(i%2 == 0) {
                horas[i] = "00:00";
            }else{
                horas[i] = "24:00";
            }
        }        
        long totalTarifa = 0;
        String hora;
        for(int i = 0; i < difDias+1; i++){
            //System.out.println("IIIII "+i);
        boolean flag = true;
            while(flag){
                data = ots.getTarifa(diaInicio, horas[2*i], getIdDia(day1), ton);
                tarifa = Integer.parseInt(data[0]);
                hora = data[1];
                if(hora.compareTo("00:00:00") == 0) hora = "24:00:00";
                //System.out.println("tarifa "+tarifa + " hora "+hora );
                Date horaIn = formatClock.parse(horas[2*i]);
                Date horaF = formatClock.parse(horas[2*i+1]);
//                System.out.println("hora ini " +horaIn + "ms: " +horaIn.getTime());
//                System.out.println("hora final "+horaF + " ms: " + horaF.getTime() );
                Date finTramo = formatClock.parse(hora);
//                System.out.println("fin tramo " + hora + " ms: " +finTramo.getTime());
                long tramoDiff;// = 0;
                if(finTramo.getTime() > horaF.getTime()){
                    tramoDiff = horaF.getTime() - horaIn.getTime();
                }else{
                    tramoDiff = finTramo.getTime() - horaIn.getTime();
                }
//                System.out.println("primer tramo " + tramoDiff);
                long minutes = (tramoDiff / (1000 * 60)) % 60;
                long hours = (tramoDiff / (1000 * 60 * 60)) % 24;
                float duracionTramo = hours + (float)minutes/60;
                horasTotales += duracionTramo;
//                System.out.println("duracion tramo " +duracionTramo);
                totalTarifa += duracionTramo * tarifa;
//                System.out.println("tarifa parcial "+totalTarifa);
                tramoDiff = horaF.getTime() - finTramo.getTime();
//                System.out.println("tramo restante " + tramoDiff);
                if(tramoDiff <= 0)  flag = false;
                horas[2*i] = formatClock.format(finTramo.getTime());
//                System.out.println("hora inicio nueva "+horas[2*i]);
            }
            day1 = nextDay(day1);
        }
        data = new String[]{Integer.toString((int)totalTarifa), String.valueOf((int)(totalTarifa * 0.19)),
            String.valueOf((int)(totalTarifa * 1.19)), Integer.toString(horasTotales)};
//        StringBuilder str = new StringBuilder(data[0]);
//        data[0] = addDots(str);
//        str = new StringBuilder(data[1]);
//        data[1] = addDots(str);
//        str = new StringBuilder(data[2]);
//        data[2] = addDots(str);
        //actualizarHorometro(horasTotales, desc);
        //
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
        //System.out.println("data "+data);
        String new_data = "";
        String[] data_dev = data.split(Pattern.quote("."));
        for(int i = 0; i < data_dev.length; i++){
            new_data = new_data + data_dev[i];
        }
        //System.out.println("new data "+new_data);
        return new_data;
    }
    
    public void actualizarHorometro(int horas, String desc){
        modelos.modeloGruas grua = new modelos.modeloGruas();
        grua.actualizarHorometro(horas, desc);
    }
    
}
