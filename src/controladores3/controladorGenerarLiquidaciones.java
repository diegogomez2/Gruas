/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.xml.transform.TransformerException;
import modelos.modeloEmpleados;
import modelos.modeloFacturas;
import modelos3.modeloRemuneraciones;
import org.apache.fop.apps.FOPException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

/**
 *
 * @author diego
 */
public class controladorGenerarLiquidaciones {
    
    DateFormat perDate = new SimpleDateFormat("MMMM-yyyy");
    DateFormat date2 = new SimpleDateFormat("dd-MMMM-yyyy");
    String per = perDate.format(new Date());
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    
    public boolean generarLiquidaciones() throws FileNotFoundException, TransformerException, IOException, FOPException{
                
        Thread runnable = new Thread() {
            public void run() {
                try {
                    modeloFacturas rutas = new modeloFacturas();
                    dfs.setCurrencySymbol("$ ");
                    dfs.setGroupingSeparator('.');
                    dfs.setMonetaryDecimalSeparator('.');
                    ((DecimalFormat) FORMAT).setDecimalFormatSymbols(dfs);
                    controladores.controladorPrincipal miControlador = new controladorPrincipal();
                    modelos.modeloEmpleados liquidaciones = new modeloEmpleados();
                    modelos3.modeloRemuneraciones remuneraciones = new modeloRemuneraciones();
                    String[][] data = liquidaciones.obtenerRemuneraciones2(getMes(), getYear());
                    String[][] imp2cat = remuneraciones.obtenerTablaImpuesto();
//                    float uf = remuneraciones.obtenerUF() / 100;
                    double uf = remuneraciones.obtenerUF();
                    int numEmp = data.length;
                    String path = "Liquidaciones " + per;
                    File dir = new File(path);
                    dir.mkdir();
//                    int bono300 = miControlador.obtenerBono300();
                    for(int i = 0; i < numEmp; i++){
                        String fileName = path+"/"+data[i][0]+".pdf"; // name of our file
                        try{
                            PDDocument doc = new PDDocument(); // creating instance of pdfDoc
                            PDPage page = new PDPage();
                            doc.addPage(page); // adding page in pdf doc file
                            int base = Integer.parseInt(data[i][2]) * Integer.parseInt(data[i][28]) / 30;
                            //GRATIFICACION
                            int grat = (int)(base * 0.25);
                            //BONO ANTIGUEDAD
                            int bonoAnt = miControlador.obtenerBonoAnt(data[i][5]);
                            //BONO 300
//                            int totalBon300 = bono300 * Integer.parseInt(data[i][9]);
                            int totalBon300 = Integer.parseInt(data[i][27]);
                            //BONO ADICIONAL
                            int bonoAd = Integer.parseInt(data[i][11]);
                            //BONO RESPONSABILIDAD
                            int bonoResp = 0;
                            //BONO ADICIONAL
                            double bonoCol1 = Double.parseDouble(data[i][8]);
                            double bonoCol30 = Double.parseDouble(data[i][9]);
                            double bonoCol = bonoCol1 + bonoCol30/2;
                            int totalBonCol = (int)Math.round(((double) base * 0.0077777) * bonoCol);
                            //HORAS EXTRA
                            double horasExNor = Double.parseDouble(data[i][12]);
                            double horasExFes = Double.parseDouble(data[i][13]);
                            double horasEx = 0;
                            double bonoHor = 0;
                            double cantHorEx = 0;
                            //total de horas extras normales = 1; festivas = 2
                            double totalHorex = 0;
                            double resHorEx = 0;
                            if(horasExNor > 45){
                                cantHorEx = 45;
                                totalHorex = 45;
                                resHorEx = horasExNor - 45;
                            }else{
                                cantHorEx = horasExNor;
                                totalHorex = cantHorEx;
                            }
                            if(cantHorEx + horasExFes > 45) {
                                resHorEx = resHorEx + (horasExFes - 45 + cantHorEx) * 2;
                                totalHorex = 45 - cantHorEx;
                                cantHorEx = 45;
                            }else{
                                cantHorEx += horasExFes;
                                totalHorex += horasExFes * 2;
                            }
                            
                            //BONO ASIGNACION VOLUNTARIA
                            double totalBonoAV = base * 0.0077777 * resHorEx;
                            double valorHorEx = (int)((double) base * 0.0077777 * totalHorex);
                            //TOTAL IMPONIBLE
                            double totImp = base + grat + bonoAnt + bonoAd + bonoResp + totalBonoAV + totalBonCol + totalBon300 + valorHorEx;
                            //DESCUENTO AFP
                            int descAFP = Integer.parseInt(data[i][21]);
                            int totalAFP = (int)(totImp * ((double)descAFP / 10000));
                            int sis = (int)(totImp * 0.0141);
                            //DESCUENTO SALUD
                            double descSalud = 0, totalSalud = 0;
                            String salud;
                            if(data[i][4].toLowerCase().compareTo("fonasa") == 0){
                                salud = "FONASA";
                                descSalud = Integer.parseInt(data[i][22]);
                                totalSalud = (int)(totImp * ((double)descSalud / 10000));
                            }else{
                                if(data[i][23].compareTo("") == 0){
                                    salud = data[i][4];
                                }else{
                                    salud = data[i][23];
                                }
                                descSalud = ((double)Integer.parseInt(data[i][24]) / 1000) * uf;
                                totalSalud = descSalud;
                            }
                            //DESCUENTO CESANTIA
                            int ces = (int)(totImp * 0.006);
                            int cesEmp = (int) (totImp * 0.024);
                            //DESCUENTOS LEGALES
                            double descLegales = ces + totalSalud + totalAFP;
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
                            int caja = Integer.parseInt(data[i][15]);
                            //ASIGNACION FAMILIAR
                            int af = Integer.parseInt(data[i][16]);
                            //LIQ ALCANZADO
                            double liqAl = totAux - caja;
                            //COLACION 
                            int col = Integer.parseInt(data[i][6]);
                            //TRANSPORTE
                            int trans = Integer.parseInt(data[i][7]);
                            //TOTAL NO IMPONIBLE
                            int noImp = trans + col + af;
                            //ANTICIPO ADELANTO PRESTAMOS
                            int antic = Integer.parseInt(data[i][17]);
                            int adel = Integer.parseInt(data[i][18]);
                            int pres = Integer.parseInt(data[i][19]);
                            int cuo = Integer.parseInt(data[i][20]);
                            int cuoPres = 0;
                            int cuores = Math.max(0, Integer.parseInt(data[i][26]) - 1);
                            if(cuo != 0){
                                cuoPres = pres / cuo;
                            }
                            //DESCUENTOS MENSUALES
                            int descMensuales = caja + antic + adel + cuoPres + descRenta;
                            //TOTAL HABERES
                            double totalHaberes = noImp + totImp;
                            //TOTAL DESCUENTOS
                            int totDesc = antic + adel + cuoPres + caja;
                            //LIQUIDO
                            double liq = liqAl + col + trans + af - antic - adel - cuoPres;

                            PDPageContentStream content = new PDPageContentStream(doc, page);

                            //HEADER
                            
                            content.beginText();
                            content.setFont(PDType1Font.HELVETICA, 10);
                            content.setLeading(14.5f);
                            content.moveTextPositionByAmount(50, 770);
                            content.showText("GRUAS SANTA TERESITA LTDA.");
                            content.newLineAtOffset(200, 0);
                            content.showText("LIQUIDACIÓN TRABAJADOR");
                            content.newLineAtOffset(200, 0);
                            content.showText(per);
                            content.newLineAtOffset(-400, 0);
                            content.newLine();
                            content.showText("77.037.960-1");
                            content.newLine();
                            content.newLine();
                            content.showText("Nombre: " + data[i][1]);
                            content.newLineAtOffset(400, 0);
                            content.showText("Contrato: " + data[i][25]);
                            content.newLineAtOffset(-400, 0);
                            content.newLine();
                            content.showText("Rut: " + data[i][0]);
                            content.endText();
                            content.drawLine(30, 700, 600, 700);

                            //LEFT SIDE
                            
                            content.beginText();
                            content.setFont(PDType1Font.HELVETICA, 9);
                            content.setLeading(14.5f);
                            content.moveTextPositionByAmount(120, 650);
                            content.showText("HABERES");
                            content.endText();
                            content.drawLine(45, 645, 245, 645);
                            
                            content.beginText();
                            content.moveTextPositionByAmount(50, 635);
                            content.showText("Sueldo base proporcional");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(base));
                            content.newLineAtOffset(-150, -15);
                            double horex = Double.parseDouble(data[i][13]);
                            if(horex > 45){
                                horex = 45;
                            }
                            content.showText("Horas extra ( " + cantHorEx + " horas )" );
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(valorHorEx));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Gratificación:");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(grat));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Bono años trabajados");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(bonoAnt));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Bono horas");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(totalBon300));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Bono asignación voluntaria");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(totalBonoAV));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Bono adicional");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(totalBonCol));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Otros bonos");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(bonoAd));
                            content.endText();
                            
                            content.drawLine(45, 515, 245, 515);
                            content.beginText();
                            content.moveTextPositionByAmount(50, 500);
                            content.showText("Imponible");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(totImp));
                            content.newLineAtOffset(-150, -15);
                            content.endText();
                            content.drawLine(45, 493, 245, 493);
                            
                            content.beginText();
                            content.moveTextPositionByAmount(50, 470);
                            content.showText("Colación");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(col));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Transporte");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(trans));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Asignación familiar");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(af));
                            content.endText();
                            
                            content.drawLine(45, 425, 245, 425);
                            content.beginText();
                            content.moveTextPositionByAmount(50, 410);
                            content.showText("No imponible");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(noImp));
                            content.endText();
                            content.drawLine(45, 403, 245, 403);
                            
                            content.drawLine(45, 380, 245, 380);
                            content.beginText();
                            content.moveTextPositionByAmount(50, 365);
                            content.showText("Total haberes");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(totalHaberes));
                            content.endText();
                            
                            content.beginText();
                            content.setFont(PDType1Font.HELVETICA, 9);
                            content.setLeading(14.5f);
                            content.moveTextPositionByAmount(65, 280);
                            content.showText("APORTES LEGALES EMPLEADOR");
                            content.endText();
                            content.drawLine(45, 275, 245, 275);
                            content.beginText();
                            content.moveTextPositionByAmount(50, 265);
                            content.showText("SIS");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(sis));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Seguro de cesantía empleador");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(cesEmp));
                            content.endText();
                            content.drawLine(45, 243, 245, 243);

                            //RIGHT SIDE
                            content.beginText();
                            content.moveTextPositionByAmount(410, 650);
                            content.showText("DESCUENTOS");
                            content.endText();
                            content.drawLine(345, 645, 545, 645);

                            content.beginText();
                            content.moveTextPositionByAmount(350, 635);
                            content.showText("Descuento AFP");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(totalAFP));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Descuento salud");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(totalSalud));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Seguro de cesantía trabajador");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(ces));
                            content.endText();
                            
                            content.drawLine(345, 585, 545, 585);
                            content.beginText();
                            content.moveTextPositionByAmount(350, 570);
                            content.showText("Descuentos legales");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(descLegales));
                            content.endText();
                            content.drawLine(345, 563, 545, 563);
                            
                            content.beginText();
                            content.moveTextPositionByAmount(350, 545);
                            content.showText("Impuesto a la renta");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(descRenta));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Caja de compensación");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(caja));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Anticipo");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(antic));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Adelanto");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(adel));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Préstamo");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(cuoPres));
                            content.newLineAtOffset(-150, -15);
                            content.showText("Cuotas restantes");
                            content.newLineAtOffset(150, 0);
                            content.showText(String.valueOf(cuores));
                            content.endText();
                            
                            content.drawLine(345, 455, 545, 455);
                            content.beginText();
                            content.moveTextPositionByAmount(350, 440);
                            content.showText("Descuentos mensuales");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(descMensuales));
                            content.endText();
                            content.drawLine(345, 433, 545, 433);
                            
                            content.drawLine(345, 400, 545, 400);
                            content.beginText();
                            content.moveTextPositionByAmount(350, 385);
                            content.showText("Total descuentos");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(descLegales + descMensuales));
                            content.endText();
                            content.drawLine(345, 378, 545, 378);
                            
                            
                            //CENTER
                            content.drawLine(180, 335, 400, 335);
                            content.beginText();
                            content.moveTextPositionByAmount(190, 320);
                            content.showText("Total a pago");
                            content.newLineAtOffset(150, 0);
                            content.showText(FORMAT.format(liq));
                            content.endText();
                            content.drawLine(180, 313, 400, 313);


                            content.close();
                            doc.save(fileName); // saving as pdf file with name perm 
                            doc.close(); // cleaning memory       

                        }catch(IOException e){
                            System.out.println(e.getMessage());
                        }
                    }
                    JOptionPane.showMessageDialog(null, "Liquidaciones de sueldo generadas con éxito", "Operación exitosa", 
                                    JOptionPane.INFORMATION_MESSAGE);
//                    liquidaciones.limpiarRemuneraciones();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        //runnable.run();
        runnable.start();
        return true;
    }
    
    public int getMes(){
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.MONTH) + 1;  
    }
    
    public int getYear(){
        Date fecha = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(fecha);
        return cal.get(Calendar.YEAR);  
    }

}
