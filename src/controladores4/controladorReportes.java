/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.modeloClientes;
import modelos.modeloFacturas;
import modelos.modeloGruas;
import modelos.modeloOts;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 *
 * @author diego
 */
public class controladorReportes {

    DateFormat perDate = new SimpleDateFormat("MMMM-yyyy");
    DateFormat numDate = new SimpleDateFormat("yyyy-MM");
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat formatDate2 = new SimpleDateFormat("MMMM/yyyy");

    public void generarReporteClientes() {
        Thread runnable = new Thread() {
            public void run() {
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reporte clientes-" + per;
                    File dir = new File(path);
                    dir.mkdir();
                    modeloClientes clientes = new modeloClientes();
                    Object[][] data = clientes.listarReporteClientes();
                    int numClientes = data.length;
                    String file = path + ".xls";
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("FirstSheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell(0).setCellValue("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA");
                    rowhead = sheet.createRow(1);
                    rowhead.createCell(3).setCellValue("1 Fecha de informe: " + formatDate.format(new Date()));
                    rowhead = sheet.createRow(2);
                    rowhead = sheet.createRow(4);
                    rowhead.createCell(0).setCellValue("RUT");
                    rowhead.createCell(1).setCellValue("RAZÓN SOCIAL");
                    rowhead.createCell(2).setCellValue("GIRO");
                    rowhead.createCell(3).setCellValue("TELÉFONO");
                    rowhead.createCell(4).setCellValue("CELULAR");
                    rowhead.createCell(5).setCellValue("CORREO");
                    rowhead.createCell(6).setCellValue("DIRECCIÓN");
                    rowhead.createCell(7).setCellValue("REGIÓN");
                    rowhead.createCell(8).setCellValue("CIUDAD");
                    rowhead.createCell(9).setCellValue("COMUNA");
                    rowhead.createCell(10).setCellValue("OBSERVACIONES");
                    rowhead.createCell(11).setCellValue("CONTACTO");
                    rowhead = sheet.createRow(5);
                    int j = 0;
                    for (Object[] cliente : data) {
                        rowhead = sheet.createRow(j + 6);
                        rowhead.createCell(0).setCellValue(cliente[0].toString());
                        rowhead.createCell(1).setCellValue(cliente[1].toString());
                        rowhead.createCell(2).setCellValue(cliente[2].toString());
                        rowhead.createCell(3).setCellValue(cliente[3].toString());
                        rowhead.createCell(4).setCellValue(cliente[4].toString());
                        rowhead.createCell(5).setCellValue(cliente[5].toString());
                        rowhead.createCell(6).setCellValue(cliente[6].toString());
                        rowhead.createCell(7).setCellValue(cliente[7].toString());
                        rowhead.createCell(8).setCellValue(cliente[8].toString());
                        rowhead.createCell(9).setCellValue(cliente[9].toString());
                        rowhead.createCell(10).setCellValue(cliente[10].toString());
                        rowhead.createCell(11).setCellValue(cliente[11].toString());
                        j++;
                    }
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(file);
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Reporte clientes generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

            }
        };
        runnable.start();

    }
    
    public void generarReporteGruas() {
        Thread runnable = new Thread() {
            public void run() {
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reporte gruas-" + per;
                    File dir = new File(path);
                    dir.mkdir();
                    modeloGruas gruas = new modeloGruas();
                    Object[][] data = gruas.listarReporteGruas();
                    int numGruas = data.length;
                    String file = path + ".xls";
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("FirstSheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell(0).setCellValue("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA");
                    rowhead = sheet.createRow(1);
                    rowhead.createCell(3).setCellValue("1 Fecha de informe: " + formatDate.format(new Date()));
                    rowhead = sheet.createRow(4);
                    rowhead.createCell(0).setCellValue("PATENTE");
                    rowhead.createCell(1).setCellValue("AÑO");
                    rowhead.createCell(2).setCellValue("DESCRIPCIÓN");
                    rowhead.createCell(3).setCellValue("MARCA");
                    rowhead.createCell(4).setCellValue("MODELO");
                    rowhead.createCell(5).setCellValue("TONELAJE");
                    rowhead.createCell(6).setCellValue("PESO");
                    rowhead.createCell(7).setCellValue("TIPO NEUMÁTICOS");
                    rowhead.createCell(8).setCellValue("TIPO NEUMÁTICOS 2");
                    rowhead.createCell(9).setCellValue("NÚMERO DE CHASIS");
                    rowhead.createCell(10).setCellValue("TIPO DE COMBUSTIBLE");
                    rowhead.createCell(11).setCellValue("MÁSTIL");
                    rowhead.createCell(12).setCellValue("ALTURA MÁSTIL");
                    rowhead.createCell(13).setCellValue("ANCHO");
                    rowhead.createCell(14).setCellValue("LARGO");
                    rowhead.createCell(15).setCellValue("LARGO UÑAS");
                    rowhead.createCell(16).setCellValue("ALTURA LEVANTE");
                    rowhead.createCell(17).setCellValue("MEDIDA NEUMÁTICOS DELANTEROS");
                    rowhead.createCell(18).setCellValue("MEDIDA NEUMÁTICOS TRASEROS");
                    rowhead.createCell(19).setCellValue("NÚMERO DE MOTOR");
                    rowhead.createCell(20).setCellValue("NÚMERO DE SERIE");
                    rowhead.createCell(21).setCellValue("FECHA REVISIÓN TÉCNICA");
                    rowhead.createCell(22).setCellValue("FECHA ÚLTIMA MANTENCIÓN");
                    rowhead.createCell(23).setCellValue("KM/H ÚLTIMA MANTENCIÓN");
                    rowhead.createCell(24).setCellValue("FECHA DE BAJA");
                    rowhead.createCell(25).setCellValue("OBSERVACIONES");
                    rowhead.createCell(26).setCellValue("FECHA DE INGRESO");
                    rowhead.createCell(27).setCellValue("HORÓMETRO");
                    rowhead = sheet.createRow(5);
                    int j = 0;
                    for (Object[] grua : data) {
                        rowhead = sheet.createRow(j + 6);
                        rowhead.createCell(0).setCellValue(grua[0].toString());
                        rowhead.createCell(1).setCellValue(grua[1].toString());
                        rowhead.createCell(2).setCellValue(grua[2].toString());
                        rowhead.createCell(3).setCellValue(grua[3].toString());
                        rowhead.createCell(4).setCellValue(grua[4].toString());
                        rowhead.createCell(5).setCellValue(grua[5].toString());
                        rowhead.createCell(6).setCellValue(grua[6].toString());
                        rowhead.createCell(7).setCellValue(grua[7].toString());
                        rowhead.createCell(8).setCellValue(grua[8].toString());
                        rowhead.createCell(9).setCellValue(grua[9].toString());
                        rowhead.createCell(10).setCellValue(grua[10].toString());
                        rowhead.createCell(11).setCellValue(grua[11].toString());
                        rowhead.createCell(12).setCellValue(grua[12].toString());
                        rowhead.createCell(13).setCellValue(grua[13].toString());
                        rowhead.createCell(14).setCellValue(grua[14].toString());
                        rowhead.createCell(15).setCellValue(grua[15].toString());
                        rowhead.createCell(16).setCellValue(grua[16].toString());
                        rowhead.createCell(17).setCellValue(grua[17].toString());
                        rowhead.createCell(18).setCellValue(grua[18].toString());
                        rowhead.createCell(19).setCellValue(grua[19].toString());
                        rowhead.createCell(20).setCellValue(grua[20].toString());
                        rowhead.createCell(21).setCellValue(grua[21].toString());
                        rowhead.createCell(22).setCellValue(grua[22].toString());
                        rowhead.createCell(23).setCellValue(grua[23].toString());
                        rowhead.createCell(24).setCellValue(grua[24].toString());
                        rowhead.createCell(25).setCellValue(grua[25].toString());
                        rowhead.createCell(26).setCellValue(grua[26].toString());
                        rowhead.createCell(27).setCellValue(grua[27].toString());
                        j++;
                    }
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(file);
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Reporte grúas generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);

            }
        };
        runnable.start();
    }
    
    public void generarReporteHistoricoOT(final String fecIn, final String fecFin) {
        Thread runnable = new Thread() {
            public void run() {
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reporte historico ot-" + per;
                    File dir = new File(path);
                    dir.mkdir();
                    modeloOts ots = new modeloOts();
                    Object[][] data = ots.listarReporteHistoricoOt(fecIn, fecFin);
                    int numOts = data.length;
                    String file = path + ".xls";
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("FirstSheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell(0).setCellValue("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA");
                    rowhead = sheet.createRow(1);
                    rowhead.createCell(3).setCellValue("1 Fecha de informe: " + formatDate.format(new Date()));
                    rowhead = sheet.createRow(4);
                    rowhead.createCell(0).setCellValue("CÓDIGO OT");
                    rowhead.createCell(1).setCellValue("RUT CLIENTE");
                    rowhead.createCell(2).setCellValue("RAZÓN SOCIAL");
                    rowhead.createCell(3).setCellValue("GIRO");
                    rowhead.createCell(4).setCellValue("DIRECCIÓN");
                    rowhead.createCell(5).setCellValue("CIUDAD");
                    rowhead.createCell(6).setCellValue("COMUNA");
                    rowhead.createCell(7).setCellValue("FECHA OT");
                    rowhead.createCell(8).setCellValue("TOTAL NETO");
                    rowhead.createCell(9).setCellValue("IVA");
                    rowhead.createCell(10).setCellValue("TOTAL BRUTO");
                    rowhead.createCell(11).setCellValue("ESTADO");
                    rowhead.createCell(12).setCellValue("OPERADOR");
                    rowhead.createCell(13).setCellValue("GRÚA");
                    rowhead = sheet.createRow(5);
                    int j = 0;
                    for (Object[] ot : data) {
                        rowhead = sheet.createRow(j + 6);
                        rowhead.createCell(0).setCellValue(ot[0].toString());
                        rowhead.createCell(1).setCellValue(ot[1].toString());
                        rowhead.createCell(2).setCellValue(ot[2].toString());
                        rowhead.createCell(3).setCellValue(ot[3].toString());
                        rowhead.createCell(4).setCellValue(ot[4].toString());
                        rowhead.createCell(5).setCellValue(ot[5].toString());
                        rowhead.createCell(6).setCellValue(ot[6].toString());
                        rowhead.createCell(7).setCellValue(ot[7].toString());
                        rowhead.createCell(8).setCellValue(ot[8].toString());
                        rowhead.createCell(9).setCellValue(ot[9].toString());
                        rowhead.createCell(10).setCellValue(ot[10].toString());
                        rowhead.createCell(11).setCellValue(ot[11].toString());
                        rowhead.createCell(12).setCellValue(ot[12].toString());
                        rowhead.createCell(13).setCellValue(ot[13].toString());
                        j++;
                    }
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(file);
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Reporte histórico ots generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        runnable.start();
    }
    
    public void generarReporteHistoricoFacturas(final String fecIn, final String fecFin) {
        Thread runnable = new Thread() {
            public void run() {
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reporte historico facturas-" + per;
                    File dir = new File(path);
                    dir.mkdir();
                    modeloFacturas facturas = new modeloFacturas();
                    Object[][] data = facturas.listarReporteFacturadas(fecIn, fecFin);
                    int numFacturas = data.length;
                    System.out.println(numFacturas);
                    String file = path + ".xls";
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("FirstSheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell(0).setCellValue("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA");
                    rowhead = sheet.createRow(1);
                    rowhead.createCell(3).setCellValue("1 Fecha de informe: " + formatDate.format(new Date()));
                    rowhead = sheet.createRow(4);
                    rowhead.createCell(0).setCellValue("FOLIO");
                    rowhead.createCell(1).setCellValue("RAZÓN SOCIAL");
                    rowhead.createCell(2).setCellValue("GIRO");
                    rowhead.createCell(3).setCellValue("DIRECCIÓN");
                    rowhead.createCell(4).setCellValue("CIUDAD");
                    rowhead.createCell(5).setCellValue("COMUNA");
                    rowhead.createCell(6).setCellValue("FECHA");
                    rowhead.createCell(7).setCellValue("TOTAL NETO");
                    rowhead.createCell(8).setCellValue("IVA");
                    rowhead.createCell(9).setCellValue("TOTAL BRUTO");
                    rowhead.createCell(10).setCellValue("TIPO");
                    //rowhead = sheet.createRow(5);
                    int j = 0;
                    for (Object[] factura : data) {
                        rowhead = sheet.createRow(j + 6);
                        if(factura[0] == null) break;
                        rowhead.createCell(0).setCellValue(factura[0].toString());
                        rowhead.createCell(1).setCellValue(factura[1].toString());
                        rowhead.createCell(2).setCellValue(factura[2].toString());
                        rowhead.createCell(3).setCellValue(factura[3].toString());
                        rowhead.createCell(4).setCellValue(factura[4].toString());
                        rowhead.createCell(5).setCellValue(factura[5].toString());
                        rowhead.createCell(6).setCellValue(factura[6].toString());
                        rowhead.createCell(7).setCellValue(factura[7].toString());
                        rowhead.createCell(8).setCellValue(factura[8].toString());
                        rowhead.createCell(9).setCellValue(factura[9].toString());
                        rowhead.createCell(10).setCellValue(factura[10].toString());
                        j++;
                    }
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(file);
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Reporte histórico de facturas generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        runnable.start();
    }

    public void generarReporteCompras(final String fecIn, final String fecFin) {
        Thread runnable = new Thread() {
            public void run() {
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reporte historico compras-" + per;
                    File dir = new File(path);
                    dir.mkdir();
                    modeloFacturas facturas = new modeloFacturas();
                    Object[][] data = facturas.listarReporteCompras(fecIn, fecFin);
                    int numFacturas = data.length;
                    System.out.println(numFacturas);
                    String file = path + ".xls";
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFSheet sheet = workbook.createSheet("FirstSheet");
                    HSSFRow rowhead = sheet.createRow((short) 0);
                    rowhead.createCell(0).setCellValue("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA");
                    rowhead = sheet.createRow(1);
                    rowhead.createCell(3).setCellValue("1 Fecha de informe: " + formatDate.format(new Date()));
                    rowhead = sheet.createRow(4);
                    rowhead.createCell(0).setCellValue("FOLIO");
                    rowhead.createCell(1).setCellValue("RAZÓN PROVEEDOR");
                    rowhead.createCell(2).setCellValue("GIRO");
                    rowhead.createCell(3).setCellValue("DIRECCIÓN");
                    rowhead.createCell(4).setCellValue("CIUDAD");
                    rowhead.createCell(5).setCellValue("COMUNA");
                    rowhead.createCell(6).setCellValue("FECHA");
                    rowhead.createCell(7).setCellValue("TOTAL NETO");
                    rowhead.createCell(8).setCellValue("IVA");
                    rowhead.createCell(9).setCellValue("TOTAL BRUTO");
                    rowhead.createCell(10).setCellValue("IMPUESTO ESPECÍFICO");
                    rowhead.createCell(11).setCellValue("IMPUESTO VARIABLE");
                    rowhead.createCell(12).setCellValue("TIPO");
                    rowhead.createCell(13).setCellValue("CLASE");
                    rowhead.createCell(14).setCellValue("ESTADO");
                    //rowhead = sheet.createRow(5);
                    int j = 0;
                    for (Object[] factura : data) {
                        rowhead = sheet.createRow(j + 6);
                        if(factura[0] == null) break;
                        rowhead.createCell(0).setCellValue(factura[0].toString());
                        rowhead.createCell(1).setCellValue(factura[1].toString());
                        rowhead.createCell(2).setCellValue(factura[2].toString());
                        rowhead.createCell(3).setCellValue(factura[3].toString());
                        rowhead.createCell(4).setCellValue(factura[4].toString());
                        rowhead.createCell(5).setCellValue(factura[5].toString());
                        rowhead.createCell(6).setCellValue(factura[6].toString());
                        rowhead.createCell(7).setCellValue(factura[7].toString());
                        rowhead.createCell(8).setCellValue(factura[8].toString());
                        rowhead.createCell(9).setCellValue(factura[9].toString());
                        rowhead.createCell(10).setCellValue(factura[10].toString());
                        rowhead.createCell(11).setCellValue(factura[11].toString());
                        rowhead.createCell(12).setCellValue(factura[12].toString());
                        rowhead.createCell(13).setCellValue(factura[13].toString());
                        rowhead.createCell(14).setCellValue(factura[14].toString());
                        j++;
                    }
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(file);
                    workbook.write(fileOut);
                    fileOut.close();
                } catch (IOException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(null, "Reporte histórico de facturas generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        runnable.start();
    }

    public void generarReporteCobranza(final String est) {
//        Thread runnable = new Thread() {
//            public void run() {
//                String per = perDate.format(new Date());
//                String fec = numDate.format(new Date());
//                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
//                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
//                try {
//                    String path = "Reporte cobranza " + est + "-" + per;
//                    File dir = new File(path);
//                    dir.mkdir();
//                    modeloFacturas facturas = new modeloFacturas();
//                    Object[][] data = facturas.listarReporteCobranza(est);
//                    int numFacturas = data.length;
//                    System.out.println(numFacturas);
//                    String file = path + ".xls";
//                    HSSFWorkbook workbook = new HSSFWorkbook();
//                    HSSFSheet sheet = workbook.createSheet("FirstSheet");
//                    HSSFRow rowhead = sheet.createRow((short) 0);
//                    rowhead.createCell(0).setCellValue("GRUAS HORQUILLA SANTA TERESITA F.M.LTDA");
//                    rowhead = sheet.createRow(1);
//                    rowhead.createCell(3).setCellValue("1 Fecha de informe: " + formatDate.format(new Date()));
//                    rowhead = sheet.createRow(4);
//                    rowhead.createCell(0).setCellValue("FOLIO");
//                    rowhead.createCell(1).setCellValue("RAZÓN PROVEEDOR");
//                    rowhead.createCell(2).setCellValue("GIRO");
//                    rowhead.createCell(3).setCellValue("DIRECCIÓN");
//                    rowhead.createCell(4).setCellValue("CIUDAD");
//                    rowhead.createCell(5).setCellValue("COMUNA");
//                    rowhead.createCell(6).setCellValue("FECHA");
//                    rowhead.createCell(7).setCellValue("TOTAL NETO");
//                    rowhead.createCell(8).setCellValue("IVA");
//                    rowhead.createCell(9).setCellValue("TOTAL BRUTO");
//                    rowhead.createCell(10).setCellValue("IMPUESTO ESPECÍFICO");
//                    rowhead.createCell(11).setCellValue("IMPUESTO VARIABLE");
//                    rowhead.createCell(12).setCellValue("TIPO");
//                    rowhead.createCell(13).setCellValue("CLASE");
//                    rowhead.createCell(14).setCellValue("ESTADO");
//                    //rowhead = sheet.createRow(5);
//                    int j = 0;
//                    for (Object[] factura : data) {
//                        rowhead = sheet.createRow(j + 6);
//                        if(factura[0] == null) break;
//                        rowhead.createCell(0).setCellValue(factura[0].toString());
//                        rowhead.createCell(1).setCellValue(factura[1].toString());
//                        rowhead.createCell(2).setCellValue(factura[2].toString());
//                        rowhead.createCell(3).setCellValue(factura[3].toString());
//                        rowhead.createCell(4).setCellValue(factura[4].toString());
//                        rowhead.createCell(5).setCellValue(factura[5].toString());
//                        rowhead.createCell(6).setCellValue(factura[6].toString());
//                        rowhead.createCell(7).setCellValue(factura[7].toString());
//                        rowhead.createCell(8).setCellValue(factura[8].toString());
//                        rowhead.createCell(9).setCellValue(factura[9].toString());
//                        rowhead.createCell(10).setCellValue(factura[10].toString());
//                        rowhead.createCell(11).setCellValue(factura[11].toString());
//                        rowhead.createCell(12).setCellValue(factura[12].toString());
//                        rowhead.createCell(13).setCellValue(factura[13].toString());
//                        rowhead.createCell(14).setCellValue(factura[14].toString());
//                        j++;
//                    }
//                    FileOutputStream fileOut;
//                    fileOut = new FileOutputStream(file);
//                    workbook.write(fileOut);
//                    fileOut.close();
//                } catch (IOException ex) {
//                    ex.printStackTrace();
//                    Logger.getLogger(controladorPrincipal.class.getName()).log(Level.SEVERE, null, ex);
//                    JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
//                } catch (Exception e) {
//                    e.printStackTrace();
//                    JOptionPane.showMessageDialog(null, e.getMessage(), "ERROR", JOptionPane.INFORMATION_MESSAGE);
//                }
//                JOptionPane.showMessageDialog(null, "Reporte histórico de facturas generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
//            }
//        };
//        runnable.start();
    }
}
