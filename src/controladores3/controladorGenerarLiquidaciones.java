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
import java.util.Date;
import javax.xml.transform.TransformerException;
import modelos.modeloEmpleados;
import modelos3.modeloRemuneraciones;
import org.apache.fop.apps.FOPException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import static org.apache.poi.hssf.usermodel.HeaderFooter.page;

/**
 *
 * @author diego
 */
public class controladorGenerarLiquidaciones {
    
    DateFormat perDate = new SimpleDateFormat("MMMM-yyyy");
    String per = perDate.format(new Date());
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    
    public boolean generarLiquidaciones() throws FileNotFoundException, TransformerException, IOException, FOPException{
                
        Thread runnable = new Thread() {
            public void run() {
                try {
        dfs.setCurrencySymbol("$ ");
        dfs.setGroupingSeparator('.');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) FORMAT).setDecimalFormatSymbols(dfs);
        controladores.controladorPrincipal miControlador = new controladorPrincipal();
        modelos.modeloEmpleados liquidaciones = new modeloEmpleados();
        String[][] data = liquidaciones.obtenerRemuneraciones();
        int numEmp = data.length;
        String path = "Liquidaciones " + per;
        File dir = new File(path);
        dir.mkdir();
        int bono300 = miControlador.obtenerBono300();
        for(int i = 0; i < numEmp; i++){
            String fileName = path+"/"+data[i][0]+".pdf"; // name of our file
            try{
                PDDocument doc = new PDDocument(); // creating instance of pdfDoc
                PDPage page = new PDPage();
                doc.addPage(page); // adding page in pdf doc file

                int base = Integer.parseInt(data[i][2]);
                //GRATIFICACION
                int grat = (int)(base * 0.25);
                //BONO ANTIGUEDAD
                int bonoAnt = miControlador.obtenerBonoAnt(data[i][5]);
                //BONO 300
                int totalBon300 = bono300 * Integer.parseInt(data[i][9]);
                //BONO ADICIONAL
                int bonoAd = Integer.parseInt(data[i][10]);
                //BONO RESPONSABILIDAD
                int bonoResp = 0;
                //BONO ASIGNACION VOLUNTARIA
                int bonoAV = Integer.parseInt(data[i][13]);
                int totalBonoAV = (int)((double) base * 0.0077777 * bonoAV);
                //BONO COLACION
                int bonoCol = Integer.parseInt(data[i][8]);
                int totalBonCol = (int)(((double) base * 0.0077777) * ((double)bonoCol / 2));
                //HORAS EXTRA
                int horasEx = Integer.parseInt(data[i][11]);
                int cantHorEx = Integer.parseInt(data[i][12]);
                int totalHorEx = (int)((double) base * 0.0077777 * horasEx);
                //TOTAL IMPONIBLE
                int totImp = base + grat + bonoAnt + bonoAd + bonoResp + totalBonoAV + totalBonCol + totalBon300 + totalHorEx;
                //DESCUENTO AFP
                int descAFP = Integer.parseInt(data[i][20]);
                int totalAFP = (int)(totImp * ((double)descAFP / 1000));
                //DESCUENTO SALUD
                int descSalud = 0, totalSalud = 0;
                String salud;
                if(data[i][4].toLowerCase().compareTo("fonasa") == 0){
                    salud = "FONASA";
                    descSalud = Integer.parseInt(data[i][21]);
                    totalSalud = (int)(totImp * ((double)descSalud / 10000));
                }else{
                    if(data[i][22].compareTo("") == 0){
                        salud = data[i][4];
                    }else{
                        salud = data[i][22];
                    }
                    descSalud = Integer.parseInt(data[i][23]);
                    totalSalud = descSalud;
                }
                //DESCUENTO CESANTIA
                int ces = (int)(totImp * 0.006);
                //TOTAL TRIBUTABLE
                int totTrib = totImp - totalAFP - totalSalud - ces;
                //CAJA COMPENSACION
                int caja = Integer.parseInt(data[i][14]);
                //ASIGNACION FAMILIAR
                int af = Integer.parseInt(data[i][15]);
                //LIQ ALCANZADO
                int liqAl = totTrib - caja;
                //COLACION 
                int col = Integer.parseInt(data[i][6]);
                //TRANSPORTE
                int trans = Integer.parseInt(data[i][7]);
                //ANTICIPO ADELANTO PRESTAMOS
                int antic = Integer.parseInt(data[i][16]);
                int adel = Integer.parseInt(data[i][17]);
                int pres = Integer.parseInt(data[i][18]);
                int cuo = Integer.parseInt(data[i][19]);
                int cuoPres = 0;
                if(cuo != 0){
                    cuoPres = pres / cuo;
                }
                //LIQUIDO
                int liq = liqAl + col + trans + af - antic - adel - cuoPres;

                PDPageContentStream content = new PDPageContentStream(doc, page);

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
                content.showText("Contrato: 01/01/2005");
                content.newLineAtOffset(-400, 0);
                content.newLine();
                content.showText("Rut: " + data[i][0]);
                content.endText();
                content.drawLine(30, 700, 600, 700);
                content.setLineDashPattern(new float[]{3}, 0);

                content.beginText();
                content.setFont(PDType1Font.HELVETICA, 9);
                content.setLeading(14.5f);
                content.moveTextPositionByAmount(50, 650);
                content.showText("Sueldo base: ");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(base));
                content.newLineAtOffset(-150, -15);
                int horex = Integer.parseInt(data[i][12]);
                if(horex > 45){
                    horex = 45;
                }
                content.showText("Horas extra ( " + horex + " horas ):" );
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totalHorEx));
                content.newLineAtOffset(-150, -15);
                content.showText("Gratificación:");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(grat));
                content.newLineAtOffset(-150, -15);
                content.showText("Bono años trabajados");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(bonoAnt));
                content.newLineAtOffset(-150, -15);
                content.showText("Bono 300");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totalBon300));
                content.newLineAtOffset(-150, -15);
                content.showText("Bono asignación voluntaria");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totalBonoAV));
                content.newLineAtOffset(-150, -15);
                content.showText("Bono colación");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totalBonCol));
                content.newLineAtOffset(-150, -15);
                content.showText("Otros bonos");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(bonoAd));
                content.newLine();
                content.showText("----------------------------");
                content.newLineAtOffset(-150, -15);
                content.showText("Total imponible");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totImp));
    //            content.moveTextPositionByAmount(-150, -15);
                content.newLineAtOffset(-150, -15);
    //            content.newLine();
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(350, 650);
                content.showText("Descuento AFP");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totalAFP));
                content.newLineAtOffset(-150, -15);
                content.showText("Descuento salud");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totalSalud));
                content.newLineAtOffset(-150, -15);
                content.showText("Seguro de cesantía");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(ces));
                content.newLine();
                content.showText("----------------------------");
                content.newLineAtOffset(-150, -15);
                content.showText("Total tributable");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(totTrib));
                content.newLineAtOffset(-150, -15);
                content.newLine();
                content.showText("Impuesto a la renta");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(0));
    //            content.newLineAtOffset(-150, -15);

                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(50, 485);
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
                content.newLineAtOffset(-150, -15);
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(350, 485);
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
                content.showText(String.valueOf(cuo));
                content.newLine();
                content.showText("----------------------------");
                content.newLineAtOffset(-150, -15);
                content.showText("Total descuentos");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(0));
                content.endText();

                content.beginText();
                content.moveTextPositionByAmount(50, 370);
                content.newLineAtOffset(150, 0);
                content.showText("----------------------------");
                content.newLineAtOffset(-150, -15);
                content.showText("Total líquido");
                content.newLineAtOffset(150, 0);
                content.showText(FORMAT.format(liq));
                content.endText();


                content.close();
                doc.save(fileName); // saving as pdf file with name perm 
                doc.close(); // cleaning memory         
            }catch(IOException e){
                System.out.println(e.getMessage());
            }
        }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        };
        runnable.run();
        return true;
    }

}
