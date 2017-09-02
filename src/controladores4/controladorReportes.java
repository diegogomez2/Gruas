/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import clases.AfpInfo;
import clases.SaludInfo;
import controladores.controladorPrincipal;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelos.modeloClientes;
import modelos.modeloEmpleados;
import modelos.modeloFacturas;
import modelos.modeloGruas;
import modelos.modeloOts;
import modelos3.modeloRemuneraciones;
import modelos4.modeloOcs;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
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
                modeloFacturas rutas = new modeloFacturas();
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reportes clientes";
                    File dir = new File(path);
                    dir.mkdir();
                    modeloClientes clientes = new modeloClientes();
                    Object[][] data = clientes.listarReporteClientes();
                    int numClientes = data.length;
                    String file = path + "/Reporte clientes - " + per + ".xls";
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
                modeloFacturas rutas = new modeloFacturas();
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reportes gruas";
                    File dir = new File(path);
                    dir.mkdir();
                    modeloGruas gruas = new modeloGruas();
                    Object[][] data = gruas.listarReporteGruas();
                    int numGruas = data.length;
                    String file = path + "/Reporte gruas - " + per + ".xls";
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
                modeloFacturas rutas = new modeloFacturas();
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reportes historicos ot_oc";
                    File dir = new File(path);
                    dir.mkdir();
                    modeloOts ots = new modeloOts();
                    modeloOcs ocs = new modeloOcs();
                    Object[][] data = ots.listarReporteHistoricoOt(fecIn, fecFin);
                    Object[][] data2 = ocs.listarReporteHistoricoOc(fecIn, fecFin);

                    int numOts = data.length;
                    String file = path + "/Reporte historico ot-oc - " + per + ".xls";
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
                    j++;
                    rowhead = sheet.createRow(j + 6);
                    rowhead.createCell(0).setCellValue("CÓDIGO OC");
                    rowhead.createCell(1).setCellValue("RUT CLIENTE");
                    rowhead.createCell(2).setCellValue("RAZÓN SOCIAL");
                    rowhead.createCell(3).setCellValue("GIRO");
                    rowhead.createCell(4).setCellValue("DIRECCIÓN");
                    rowhead.createCell(5).setCellValue("CIUDAD");
                    rowhead.createCell(6).setCellValue("COMUNA");
                    rowhead.createCell(7).setCellValue("FECHA OC");
                    rowhead.createCell(8).setCellValue("TOTAL NETO");
                    rowhead.createCell(9).setCellValue("IVA");
                    rowhead.createCell(10).setCellValue("TOTAL BRUTO");
                    rowhead.createCell(11).setCellValue("ESTADO");
                    j++;
                    for (Object[] oc : data2) {
                        rowhead = sheet.createRow(j + 6);
                        rowhead.createCell(0).setCellValue(oc[0].toString());
                        rowhead.createCell(1).setCellValue(oc[1].toString());
                        rowhead.createCell(2).setCellValue(oc[2].toString());
                        rowhead.createCell(3).setCellValue(oc[3].toString());
                        rowhead.createCell(4).setCellValue(oc[4].toString());
                        rowhead.createCell(5).setCellValue(oc[5].toString());
                        rowhead.createCell(6).setCellValue(oc[6].toString());
                        rowhead.createCell(7).setCellValue(oc[7].toString());
                        rowhead.createCell(8).setCellValue(oc[8].toString());
                        rowhead.createCell(9).setCellValue(oc[9].toString());
                        rowhead.createCell(10).setCellValue(oc[10].toString());
                        rowhead.createCell(11).setCellValue(oc[11].toString());
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
                modeloFacturas rutas = new modeloFacturas();
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reportes historico facturas";
                    File dir = new File(path);
                    dir.mkdir();
                    modeloFacturas facturas = new modeloFacturas();
                    Object[][] data = facturas.listarReporteFacturadas(fecIn, fecFin);
                    int numFacturas = data.length;
                    String file = path + "/Reporte historico facturas - " + per + ".xls";
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
                        if (factura[0] == null) {
                            break;
                        }
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
                modeloFacturas rutas = new modeloFacturas();
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reportes historico compras";
                    File dir = new File(path);
                    dir.mkdir();
                    modeloFacturas facturas = new modeloFacturas();
                    Object[][] data = facturas.listarReporteCompras(fecIn, fecFin);
                    int numFacturas = data.length;
                    System.out.println(numFacturas);
                    String file = path + "/Reporte historico compras - " + per + ".xls";
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
                        if (factura[0] == null) {
                            break;
                        }
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
                JOptionPane.showMessageDialog(null, "Reporte histórico de compras generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        runnable.start();
    }

    public void generarReporteCobranza(final String est) {
        Thread runnable = new Thread() {
            public void run() {
                String per = perDate.format(new Date());
                String fec = numDate.format(new Date());
                NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
                DecimalFormatSymbols dfs = new DecimalFormatSymbols();
                try {
                    String path = "Reporte cobranza " + est + "-" + per;
                    File dir = new File(path);
                    dir.mkdir();
                    modeloFacturas facturas = new modeloFacturas();
                    Object[][] data = facturas.listarReporteCobranza(est);
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
                        if (factura[0] == null) {
                            break;
                        }
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
                JOptionPane.showMessageDialog(null, "Reporte histórico de cobranza generado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            }
        };
        runnable.start();
    }

    public void GenerarLibroRemuneraciones() {
        DateFormat date2 = new SimpleDateFormat("dd-MMMM-yyyy");
        NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        
        Thread runnable;
        runnable = new Thread(){
            public void run(){
                try{
                    DateFormat yearDate = new SimpleDateFormat("yyyy");
                    DateFormat monthDate = new SimpleDateFormat("M");
                    String month = monthDate.format(new Date());
                    String year = yearDate.format(new Date());
                    String per = perDate.format(new Date());
                    String fecha = formatDate.format(new Date());
                    controladores.controladorPrincipal miControlador = new controladorPrincipal();
                    modelos3.modeloRemuneraciones remu = new modeloRemuneraciones();
                    double uf = remu.obtenerUF();
                    double utm = remu.obtenerUTM();
                    String[][] imp2cat = remu.obtenerTablaImpuesto();
                    
                    //Listas para detalle afp y salud
                    HashMap<String, ArrayList<AfpInfo>> mapAfp = new HashMap<>();
                    HashMap<String, ArrayList<SaludInfo>> mapSalud = new HashMap<>();
                    List<AfpInfo> listaAfp = new ArrayList<>();
                    List<SaludInfo> listaSalud = new ArrayList<>();
                    
                    modelos.modeloEmpleados emp = new modeloEmpleados();
                    String[][] data = emp.obtenerRemuneraciones2(getMes(), getYear());
                    
                    String path = "Libro de remuneraciones - " + per + ".xls";
                    File file = new File(path);
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFCellStyle style = workbook.createCellStyle();
                    HSSFFont font = workbook.createFont();
                    font.setBold(true);
                    font.setFontName("Calibri");
                    font.setFontHeightInPoints((short) 11);
                    style.setFont(font);
                    HSSFSheet sheet_rem = workbook.createSheet("Libro_Remuneraciones");
                    HSSFSheet sheet_afp = workbook.createSheet("Detalle_AFP");
                    HSSFSheet sheet_salud = workbook.createSheet("Detalle_Salud");
                    HSSFRow rowhead;
                    //J para recorrer los datos de los trabajadores
                    int j = 0;
                    int numTrab = data.length;
                    for(int i = 0; i < 2; i++){
                        //Primera linea
                        rowhead = sheet_rem.createRow((short) 0+ i*34);
                        rowhead.createCell(0).setCellValue("parte:1");
                        rowhead.createCell(3).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                        rowhead.getCell(3).setCellStyle(style);
                        rowhead.createCell(11).setCellValue("pag:"+(i+1));
                        
                        rowhead.createCell(12).setCellValue("parte:2");
                        rowhead.createCell(15).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                        rowhead.getCell(15).setCellStyle(style);
                        rowhead.createCell(23).setCellValue("pag:"+(i+1));
                        
                        rowhead.createCell(24).setCellValue("parte:3");
                        rowhead.createCell(27).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                        rowhead.getCell(27).setCellStyle(style);
                        rowhead.createCell(35).setCellValue("pag:"+(i+1));
                        
                        //Segunda linea
                        rowhead = sheet_rem.createRow((short) 1 + (i*34));
                        rowhead.createCell(0).setCellValue("Rut_empresa:77.037.960-1");
                        rowhead.createCell(2).setCellValue("Mes: " + month);
                        rowhead.createCell(4).setCellValue("Año: " + year);
                        rowhead.createCell(6).setCellValue("UF: $" + uf);
                        rowhead.createCell(9).setCellValue("UTM: $" + utm);
                        
                        rowhead.createCell(12).setCellValue("Rut_empresa:77.037.960-1");
                        rowhead.createCell(14).setCellValue("Mes: " + month);
                        rowhead.createCell(16).setCellValue("Año: " + year);
                        rowhead.createCell(18).setCellValue("UF: $" + uf);
                        rowhead.createCell(21).setCellValue("UTM: $" + utm);
                        
                        rowhead.createCell(24).setCellValue("Rut_empresa:77.037.960-1");
                        rowhead.createCell(26).setCellValue("Mes: " + month);
                        rowhead.createCell(28).setCellValue("Año: " + year);
                        rowhead.createCell(30).setCellValue("UF: $" + uf);
                        rowhead.createCell(33).setCellValue("UTM: $" + utm);
                        
                        //Tercera linea
                        rowhead = sheet_rem.createRow((short) 4 + (i*34));
                        rowhead.createCell(0).setCellValue("rut_trabajador");
                        rowhead.createCell(1).setCellValue("nombre_trabajador");
                        rowhead.createCell(2).setCellValue("dias_trab");
                        rowhead.createCell(3).setCellValue("sueldo_base_p");
                        rowhead.createCell(4).setCellValue("gratificacion");
                        rowhead.createCell(5).setCellValue("bono_años");
                        rowhead.createCell(6).setCellValue("bono_horas");
                        rowhead.createCell(7).setCellValue("bono_asig_vol");
                        rowhead.createCell(8).setCellValue("bono_ad");
                        rowhead.createCell(9).setCellValue("otros_bonos");
                        rowhead.createCell(10).setCellValue("horas_extra");
                        rowhead.createCell(11).setCellValue("tot_h_imp");
                        
                        rowhead.createCell(12).setCellValue("rut_trabajador");
                        rowhead.createCell(13).setCellValue("nombre_trabajador");
                        rowhead.createCell(14).setCellValue("colacion");
                        rowhead.createCell(15).setCellValue("movilizacion");
                        rowhead.createCell(16).setCellValue("asig_familiar");
                        rowhead.createCell(17).setCellValue("tot_h_no_imp");
                        rowhead.createCell(18).setCellValue("tot_haberes");
                        rowhead.createCell(19).setCellValue("desc_afp");
                        rowhead.createCell(20).setCellValue("desc_salud");
                        rowhead.createCell(21).setCellValue("afc_trab");
                        rowhead.createCell(22).setCellValue("afc_empl");
                        rowhead.createCell(23).setCellValue("tot_desc_leg");
                        
                        rowhead.createCell(24).setCellValue("rut_trabajador");
                        rowhead.createCell(25).setCellValue("nombre_trabajador");
                        rowhead.createCell(26).setCellValue("imp_renta");
                        rowhead.createCell(27).setCellValue("caja_comp");
                        rowhead.createCell(28).setCellValue("anticipo");
                        rowhead.createCell(29).setCellValue("adelanto");
                        rowhead.createCell(30).setCellValue("prestamo");
                        rowhead.createCell(31).setCellValue("tot_desc_men");
                        rowhead.createCell(32).setCellValue("tot_desc");
                        rowhead.createCell(33).setCellValue("total_a_pago");
                        rowhead.createCell(34).setCellValue("sis");
                        
                        //Cuarta linea                        
                        for(int k = 0; k < 28; k++, j++){
                            if (j == numTrab) break;
                            int base = Integer.parseInt(data[j][2]) * Integer.parseInt(data[j][28]) / 30;
                            //GRATIFICACION
                            int grat = (int)(base * 0.25);
                            //BONO ANTIGUEDAD
                            int bonoAnt = miControlador.obtenerBonoAnt(data[j][5]);
                            //BONO 300
                            int totalBon300 = Integer.parseInt(data[j][27]);
                            //BONO ADICIONAL
                            int bonoAd = Integer.parseInt(data[j][11]);
                            //BONO RESPONSABILIDAD
                            int bonoResp = 0;
                            //BONO ADICIONAL
                            double bonoCol1 = Double.parseDouble(data[j][8]);
                            double bonoCol30 = Double.parseDouble(data[j][9]);
                            double bonoCol = bonoCol1 + bonoCol30/2;
                            int totalBonCol = (int)Math.round(((double) base * 0.0077777) * bonoCol);
                            //HORAS EXTRA
                            double horasExNor = Double.parseDouble(data[j][12]);
                            double horasExFes = Double.parseDouble(data[j][13]);
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
                            int descAFP = Integer.parseInt(data[j][21]);
                            int totalAFP = (int)(totImp * ((double)descAFP / 10000));
                            int sis = (int)(totImp * 0.0141);
                            //DESCUENTO SALUD
                            double descSalud = 0, totalSalud = 0;
                            String salud;
                            if(data[j][4].toLowerCase().compareTo("fonasa") == 0){
                                salud = "FONASA";
                                descSalud = Integer.parseInt(data[j][22]);
                                totalSalud = (int)(totImp * ((double)descSalud / 10000));
                            }else{
                                if(data[i][23].compareTo("") == 0){
                                    salud = data[j][4];
                                }else{
                                    salud = data[j][23];
                                }
                                descSalud = ((double)Integer.parseInt(data[j][24]) / 1000) * uf;
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
                            int caja = Integer.parseInt(data[j][15]);
                            //ASIGNACION FAMILIAR
                            int af = Integer.parseInt(data[j][16]);
                            //LIQ ALCANZADO
                            double liqAl = totAux - caja;
                            //COLACION 
                            int col = Integer.parseInt(data[j][6]);
                            //TRANSPORTE
                            int trans = Integer.parseInt(data[j][7]);
                            //TOTAL NO IMPONIBLE
                            int noImp = trans + col + af;
                            //ANTICIPO ADELANTO PRESTAMOS
                            int antic = Integer.parseInt(data[j][17]);
                            int adel = Integer.parseInt(data[j][18]);
                            int pres = Integer.parseInt(data[j][19]);
                            int cuo = Integer.parseInt(data[j][20]);
                            int cuoPres = 0;
                            int cuores = Math.max(0, Integer.parseInt(data[j][26]) - 1);
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
                            
                            //Agrego los datos de afp a listaAfp
                            String afp = data[j][3];
                            if(mapAfp.get(afp) == null){
                                mapAfp.put(afp, new ArrayList<AfpInfo>());
                            }
                            if(mapSalud.get(salud.toUpperCase()) == null){
                                mapSalud.put(salud.toUpperCase(), new ArrayList<SaludInfo>());
                            }
                            AfpInfo info = new AfpInfo(data[j][0], data[j][1], totImp, descAFP, sis, totalAFP);
                            mapAfp.get(afp).add(info);
                            SaludInfo sInfo = new SaludInfo(data[j][0], data[j][1], totImp, descSalud);
                            mapSalud.get(salud.toUpperCase()).add(sInfo);
                            rowhead = sheet_rem.createRow((short) 5 + k + (i*34));
                            rowhead.createCell(0).setCellValue(data[j][0]);
                            rowhead.createCell(1).setCellValue(data[j][1]);
                            rowhead.createCell(2).setCellValue(Integer.parseInt(data[j][28]));
                            rowhead.createCell(3).setCellValue(base);
                            rowhead.createCell(4).setCellValue(grat);
                            rowhead.createCell(5).setCellValue(bonoAnt);
                            rowhead.createCell(6).setCellValue(totalBon300);
                            rowhead.createCell(7).setCellValue(totalBonoAV);
                            rowhead.createCell(8).setCellValue(totalBonCol);
                            rowhead.createCell(9).setCellValue(bonoAd);
                            rowhead.createCell(10).setCellValue(valorHorEx);
                            rowhead.createCell(11).setCellValue(totImp);
                            
                            rowhead.createCell(12).setCellValue(data[j][0]);
                            rowhead.createCell(13).setCellValue(data[j][1]);
                            rowhead.createCell(14).setCellValue(col);
                            rowhead.createCell(15).setCellValue(trans);
                            rowhead.createCell(16).setCellValue(af);
                            rowhead.createCell(17).setCellValue(noImp);
                            rowhead.createCell(18).setCellValue(totalHaberes);
                            rowhead.createCell(19).setCellValue(totalAFP);
                            rowhead.createCell(20).setCellValue(totalSalud);
                            rowhead.createCell(21).setCellValue(ces);
                            rowhead.createCell(22).setCellValue(cesEmp);
                            rowhead.createCell(23).setCellValue(descLegales);

                            rowhead.createCell(24).setCellValue(data[j][0]);
                            rowhead.createCell(25).setCellValue(data[j][1]);
                            rowhead.createCell(26).setCellValue(descRenta);
                            rowhead.createCell(27).setCellValue(caja);
                            rowhead.createCell(28).setCellValue(antic);
                            rowhead.createCell(29).setCellValue(adel);
                            rowhead.createCell(30).setCellValue(cuoPres);
                            rowhead.createCell(31).setCellValue(descMensuales);
                            rowhead.createCell(32).setCellValue(totDesc);
                            rowhead.createCell(33).setCellValue(liq);
                            rowhead.createCell(34).setCellValue(sis);
                        }
                    }
                    
                    //Totales
                    rowhead = sheet_rem.createRow(66);
                    rowhead.createCell(2).setCellFormula("SUM(C6:C34,C40:C66)");
                    rowhead.createCell(3).setCellFormula("SUM(D6:D34,D40:D66)");
                    rowhead.createCell(4).setCellFormula("SUM(E6:E34,E40:E66)");
                    rowhead.createCell(5).setCellFormula("SUM(F6:F34,F40:F66)");
                    rowhead.createCell(6).setCellFormula("SUM(G6:G34,G40:G66)");
                    rowhead.createCell(7).setCellFormula("SUM(H6:H34,H40:H66)");
                    rowhead.createCell(8).setCellFormula("SUM(I6:I34,I40:I66)");
                    rowhead.createCell(9).setCellFormula("SUM(J6:J34,J40:J66)");
                    rowhead.createCell(10).setCellFormula("SUM(K6:K34,K40:K66)");
                    rowhead.createCell(11).setCellFormula("SUM(L6:L34,L40:L66)");
                    
                    rowhead.createCell(14).setCellFormula("SUM(O6:O34,O40:O66)");
                    rowhead.createCell(15).setCellFormula("SUM(P6:P34,P40:P66)");
                    rowhead.createCell(16).setCellFormula("SUM(Q6:Q34,Q40:Q66)");
                    rowhead.createCell(17).setCellFormula("SUM(R6:R34,R40:R66)");
                    rowhead.createCell(18).setCellFormula("SUM(S6:S34,S40:S66)");
                    rowhead.createCell(19).setCellFormula("SUM(T6:T34,T40:T66)");
                    rowhead.createCell(20).setCellFormula("SUM(U6:U34,U40:U66)");
                    rowhead.createCell(21).setCellFormula("SUM(V6:V34,V40:V66)");
                    rowhead.createCell(22).setCellFormula("SUM(W6:W34,W40:W66)");
                    rowhead.createCell(23).setCellFormula("SUM(X6:X34,X40:X66)");
                    
                    rowhead.createCell(26).setCellFormula("SUM(AA6:AA34,AA40:AA66)");
                    rowhead.createCell(27).setCellFormula("SUM(AB6:AB34,AB40:AB66)");
                    rowhead.createCell(28).setCellFormula("SUM(AC6:AC34,AC40:AC66)");
                    rowhead.createCell(29).setCellFormula("SUM(AD6:AD34,AD40:AD66)");
                    rowhead.createCell(30).setCellFormula("SUM(AE6:AE34,AE40:AE66)");
                    rowhead.createCell(31).setCellFormula("SUM(AF6:AF34,AF40:AF66)");
                    rowhead.createCell(32).setCellFormula("SUM(AG6:AG34,AG40:AG66)");
                    rowhead.createCell(33).setCellFormula("SUM(AH6:AH34,AH40:AH66)");
                    rowhead.createCell(34).setCellFormula("SUM(AI6:AI34,AI40:AI66)");
                    
                    
                    //Hoja detalle afp
                    j = 0;

                    //Primera linea
                    rowhead = sheet_afp.createRow((short) j++);
                    rowhead.createCell(3).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                    rowhead.getCell(3).setCellStyle(style);
                    rowhead.createCell(11).setCellValue("pag:1");

                    //Segunda linea
                    rowhead = sheet_afp.createRow((short) j++ );
                    rowhead.createCell(0).setCellValue("Rut_empresa:77.037.960-1");
                    rowhead.createCell(2).setCellValue("Mes: " + month);
                    rowhead.createCell(4).setCellValue("Año: " + year);
                    rowhead.createCell(6).setCellValue("UF: $" + uf);
                    rowhead.createCell(9).setCellValue("UTM: $" + utm);

                    j+=2;
                    for(String key : mapAfp.keySet()){
                        List<AfpInfo> aux = mapAfp.get(key);
                        rowhead = sheet_afp.createRow((short) j++);
                        rowhead.createCell(5).setCellValue("AFP " + key);
                        rowhead.getCell(5).setCellStyle(style);
                        rowhead = sheet_afp.createRow((short) j++);
                        rowhead.createCell(0).setCellValue("rut");
                        rowhead.createCell(1).setCellValue("nom_trab");
                        rowhead.createCell(2).setCellValue("tot_h_imp");
                        rowhead.createCell(3).setCellValue("desc_afp");
                        rowhead.createCell(4).setCellValue("sis");
                        rowhead.createCell(5).setCellValue("tot_afp");
                        rowhead.createCell(8).setCellValue("afiliados: " + aux.size());
                        for(AfpInfo usuario : aux){
                            rowhead = sheet_afp.createRow((short) j++);
                            rowhead.createCell(0).setCellValue(usuario.getRut());
                            rowhead.createCell(1).setCellValue(usuario.getNombre());
                            rowhead.createCell(2).setCellValue(usuario.getTotalImp());
                            rowhead.createCell(3).setCellValue(usuario.getDescAfp());
                            rowhead.createCell(4).setCellValue(usuario.getSis());
                            rowhead.createCell(5).setCellValue(usuario.getTotAfp());
                        }
                        rowhead = sheet_afp.createRow((short) j++);
                        int totalTrab = aux.size();
                        rowhead.createCell(2).setCellFormula("SUM(C" + (j-totalTrab) + ":C" + (j-1) + ")");
                        rowhead.createCell(3).setCellFormula("SUM(D" + (j-totalTrab) + ":D" + (j-1) + ")");
                        rowhead.createCell(4).setCellFormula("SUM(E" + (j-totalTrab) + ":E" + (j-1) + ")");
                        rowhead.createCell(5).setCellFormula("SUM(F" + (j-totalTrab) + ":F" + (j-1) + ")");
                        j++;
                    }
                    
                    //Hoja detalle salud
                    j = 0;

                    //Primera linea
                    rowhead = sheet_salud.createRow((short) j++);
                    rowhead.createCell(3).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                    rowhead.getCell(3).setCellStyle(style);
                    rowhead.createCell(11).setCellValue("pag:1");

                    //Segunda linea
                    rowhead = sheet_salud.createRow((short) j++ );
                    rowhead.createCell(0).setCellValue("Rut_empresa:77.037.960-1");
                    rowhead.createCell(2).setCellValue("Mes: " + month);
                    rowhead.createCell(4).setCellValue("Año: " + year);
                    rowhead.createCell(6).setCellValue("UF: $" + uf);
                    rowhead.createCell(9).setCellValue("UTM: $" + utm);

                    j+=2;
                    for(String key : mapSalud.keySet()){
                        List<SaludInfo> aux = mapSalud.get(key);
                        rowhead = sheet_salud.createRow((short) j++);
                        rowhead.createCell(5).setCellValue("Isapre " + key);
                        rowhead.getCell(5).setCellStyle(style);
                        rowhead = sheet_salud.createRow((short) j++);
                        rowhead.createCell(0).setCellValue("rut");
                        rowhead.createCell(1).setCellValue("nom_trab");
                        rowhead.createCell(2).setCellValue("tot_h_imp");
                        rowhead.createCell(3).setCellValue("desc_salud");
                        rowhead.createCell(8).setCellValue("afiliados: " + aux.size());
                        for(SaludInfo usuario : aux){
                            rowhead = sheet_salud.createRow((short) j++);
                            rowhead.createCell(0).setCellValue(usuario.getRut());
                            rowhead.createCell(1).setCellValue(usuario.getNombre());
                            rowhead.createCell(2).setCellValue(usuario.getTotImp());
                            rowhead.createCell(3).setCellValue(usuario.getDescSalud());
                        }
                        rowhead = sheet_salud.createRow((short) j++);
                        int totalTrab = aux.size();
                        rowhead.createCell(2).setCellFormula("SUM(C" + (j-totalTrab) + ":C" + (j-1) + ")");
                        rowhead.createCell(3).setCellFormula("SUM(D" + (j-totalTrab) + ":D" + (j-1) + ")");
                        j++;
                    }
                    
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(file);
                    workbook.write(fileOut);
                    fileOut.close();
                    JOptionPane.showMessageDialog(null, "Libro de remuneraciones generado con éxito", "Operación exitosa",
                            JOptionPane.INFORMATION_MESSAGE);
                }catch(IOException ie){
                    JOptionPane.showMessageDialog(null, "El archivo està siendo ocupado\nCierre el archivo y vuelva a intentarlo", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    ie.printStackTrace();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error al crear libro de remuneraciones", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    e.printStackTrace();
                }
            }
        };
        runnable.start();
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

    public void GenerarLibroRemuneracionesAtrasado(final String year, final String month) {
        DateFormat date2 = new SimpleDateFormat("dd-MMMM-yyyy");
        NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
        DecimalFormatSymbols dfs = new DecimalFormatSymbols();
        
        Thread runnable;
        runnable = new Thread(){
            public void run(){
                try{
                    DateFormat yearDate = new SimpleDateFormat("yyyy");
                    DateFormat monthDate = new SimpleDateFormat("M");
//                    String month2 = monthDate.format(new Date());
//                    String year2 = yearDate.format(new Date());
                    String per = perDate.format(new Date());
                    String fecha = formatDate.format(new Date());
                    controladores.controladorPrincipal miControlador = new controladorPrincipal();
                    modelos3.modeloRemuneraciones remu = new modeloRemuneraciones();
                    double uf = remu.obtenerUF();
                    double utm = remu.obtenerUTM();
                    String[][] imp2cat = remu.obtenerTablaImpuesto();
                    
                    //Listas para detalle afp y salud
                    HashMap<String, ArrayList<AfpInfo>> mapAfp = new HashMap<>();
                    HashMap<String, ArrayList<SaludInfo>> mapSalud = new HashMap<>();
                    List<AfpInfo> listaAfp = new ArrayList<>();
                    List<SaludInfo> listaSalud = new ArrayList<>();
                    
                    modelos.modeloEmpleados emp = new modeloEmpleados();
                    String[][] data = emp.obtenerRemuneraciones2(Integer.parseInt(month), Integer.parseInt(year));
                    
                    String path = "Libro de remuneraciones - " + month + "-" + year + ".xls";
                    File file = new File(path);
                    HSSFWorkbook workbook = new HSSFWorkbook();
                    HSSFCellStyle style = workbook.createCellStyle();
                    HSSFFont font = workbook.createFont();
                    font.setBold(true);
                    font.setFontName("Calibri");
                    font.setFontHeightInPoints((short) 11);
                    style.setFont(font);
                    HSSFSheet sheet_rem = workbook.createSheet("Libro_Remuneraciones");
                    HSSFSheet sheet_afp = workbook.createSheet("Detalle_AFP");
                    HSSFSheet sheet_salud = workbook.createSheet("Detalle_Salud");
                    HSSFRow rowhead;
                    //J para recorrer los datos de los trabajadores
                    int j = 0;
                    int numTrab = data.length;
                    for(int i = 0; i < 2; i++){
                        //Primera linea
                        rowhead = sheet_rem.createRow((short) 0+ i*34);
                        rowhead.createCell(0).setCellValue("parte:1");
                        rowhead.createCell(3).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                        rowhead.getCell(3).setCellStyle(style);
                        rowhead.createCell(11).setCellValue("pag:"+(i+1));
                        
                        rowhead.createCell(12).setCellValue("parte:2");
                        rowhead.createCell(15).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                        rowhead.getCell(15).setCellStyle(style);
                        rowhead.createCell(23).setCellValue("pag:"+(i+1));
                        
                        rowhead.createCell(24).setCellValue("parte:3");
                        rowhead.createCell(27).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                        rowhead.getCell(27).setCellStyle(style);
                        rowhead.createCell(35).setCellValue("pag:"+(i+1));
                        
                        //Segunda linea
                        rowhead = sheet_rem.createRow((short) 1 + (i*34));
                        rowhead.createCell(0).setCellValue("Rut_empresa:77.037.960-1");
                        rowhead.createCell(2).setCellValue("Mes: " + month);
                        rowhead.createCell(4).setCellValue("Año: " + year);
                        rowhead.createCell(6).setCellValue("UF: $" + uf);
                        rowhead.createCell(9).setCellValue("UTM: $" + utm);
                        
                        rowhead.createCell(12).setCellValue("Rut_empresa:77.037.960-1");
                        rowhead.createCell(14).setCellValue("Mes: " + month);
                        rowhead.createCell(16).setCellValue("Año: " + year);
                        rowhead.createCell(18).setCellValue("UF: $" + uf);
                        rowhead.createCell(21).setCellValue("UTM: $" + utm);
                        
                        rowhead.createCell(24).setCellValue("Rut_empresa:77.037.960-1");
                        rowhead.createCell(26).setCellValue("Mes: " + month);
                        rowhead.createCell(28).setCellValue("Año: " + year);
                        rowhead.createCell(30).setCellValue("UF: $" + uf);
                        rowhead.createCell(33).setCellValue("UTM: $" + utm);
                        
                        //Tercera linea
                        rowhead = sheet_rem.createRow((short) 4 + (i*34));
                        rowhead.createCell(0).setCellValue("rut_trabajador");
                        rowhead.createCell(1).setCellValue("nombre_trabajador");
                        rowhead.createCell(2).setCellValue("dias_trab");
                        rowhead.createCell(3).setCellValue("sueldo_base_p");
                        rowhead.createCell(4).setCellValue("gratificacion");
                        rowhead.createCell(5).setCellValue("bono_años");
                        rowhead.createCell(6).setCellValue("bono_horas");
                        rowhead.createCell(7).setCellValue("bono_asig_vol");
                        rowhead.createCell(8).setCellValue("bono_ad");
                        rowhead.createCell(9).setCellValue("otros_bonos");
                        rowhead.createCell(10).setCellValue("horas_extra");
                        rowhead.createCell(11).setCellValue("tot_h_imp");
                        
                        rowhead.createCell(12).setCellValue("rut_trabajador");
                        rowhead.createCell(13).setCellValue("nombre_trabajador");
                        rowhead.createCell(14).setCellValue("colacion");
                        rowhead.createCell(15).setCellValue("movilizacion");
                        rowhead.createCell(16).setCellValue("asig_familiar");
                        rowhead.createCell(17).setCellValue("tot_h_no_imp");
                        rowhead.createCell(18).setCellValue("tot_haberes");
                        rowhead.createCell(19).setCellValue("desc_afp");
                        rowhead.createCell(20).setCellValue("desc_salud");
                        rowhead.createCell(21).setCellValue("afc_trab");
                        rowhead.createCell(22).setCellValue("afc_empl");
                        rowhead.createCell(23).setCellValue("tot_desc_leg");
                        
                        rowhead.createCell(24).setCellValue("rut_trabajador");
                        rowhead.createCell(25).setCellValue("nombre_trabajador");
                        rowhead.createCell(26).setCellValue("imp_renta");
                        rowhead.createCell(27).setCellValue("caja_comp");
                        rowhead.createCell(28).setCellValue("anticipo");
                        rowhead.createCell(29).setCellValue("adelanto");
                        rowhead.createCell(30).setCellValue("prestamo");
                        rowhead.createCell(31).setCellValue("tot_desc_men");
                        rowhead.createCell(32).setCellValue("tot_desc");
                        rowhead.createCell(33).setCellValue("total_a_pago");
                        rowhead.createCell(34).setCellValue("sis");
                        
                        //Cuarta linea                        
                        for(int k = 0; k < 28; k++, j++){
                            if (j == numTrab) break;
                            int base = Integer.parseInt(data[j][2]) * Integer.parseInt(data[j][28]) / 30;
                            //GRATIFICACION
                            int grat = (int)(base * 0.25);
                            //BONO ANTIGUEDAD
                            int bonoAnt = miControlador.obtenerBonoAnt(data[j][5]);
                            //BONO 300
                            int totalBon300 = Integer.parseInt(data[j][27]);
                            //BONO ADICIONAL
                            int bonoAd = Integer.parseInt(data[j][11]);
                            //BONO RESPONSABILIDAD
                            int bonoResp = 0;
                            //BONO ADICIONAL
                            double bonoCol1 = Double.parseDouble(data[j][8]);
                            double bonoCol30 = Double.parseDouble(data[j][9]);
                            double bonoCol = bonoCol1 + bonoCol30/2;
                            int totalBonCol = (int)Math.round(((double) base * 0.0077777) * bonoCol);
                            //HORAS EXTRA
                            double horasExNor = Double.parseDouble(data[j][12]);
                            double horasExFes = Double.parseDouble(data[j][13]);
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
                            int descAFP = Integer.parseInt(data[j][21]);
                            int totalAFP = (int)(totImp * ((double)descAFP / 10000));
                            int sis = (int)(totImp * 0.0141);
                            //DESCUENTO SALUD
                            double descSalud = 0, totalSalud = 0;
                            String salud;
                            if(data[j][4].toLowerCase().compareTo("fonasa") == 0){
                                salud = "FONASA";
                                descSalud = Integer.parseInt(data[j][22]);
                                totalSalud = (int)(totImp * ((double)descSalud / 10000));
                            }else{
                                if(data[i][23].compareTo("") == 0){
                                    salud = data[j][4];
                                }else{
                                    salud = data[j][23];
                                }
                                descSalud = ((double)Integer.parseInt(data[j][24]) / 1000) * uf;
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
                            int caja = Integer.parseInt(data[j][15]);
                            //ASIGNACION FAMILIAR
                            int af = Integer.parseInt(data[j][16]);
                            //LIQ ALCANZADO
                            double liqAl = totAux - caja;
                            //COLACION 
                            int col = Integer.parseInt(data[j][6]);
                            //TRANSPORTE
                            int trans = Integer.parseInt(data[j][7]);
                            //TOTAL NO IMPONIBLE
                            int noImp = trans + col + af;
                            //ANTICIPO ADELANTO PRESTAMOS
                            int antic = Integer.parseInt(data[j][17]);
                            int adel = Integer.parseInt(data[j][18]);
                            int pres = Integer.parseInt(data[j][19]);
                            int cuo = Integer.parseInt(data[j][20]);
                            int cuoPres = 0;
                            int cuores = Math.max(0, Integer.parseInt(data[j][26]) - 1);
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
                            
                            //Agrego los datos de afp a listaAfp
                            String afp = data[j][3];
                            if(mapAfp.get(afp) == null){
                                mapAfp.put(afp, new ArrayList<AfpInfo>());
                            }
                            if(mapSalud.get(salud.toUpperCase()) == null){
                                mapSalud.put(salud.toUpperCase(), new ArrayList<SaludInfo>());
                            }
                            AfpInfo info = new AfpInfo(data[j][0], data[j][1], totImp, descAFP, sis, totalAFP);
                            mapAfp.get(afp).add(info);
                            SaludInfo sInfo = new SaludInfo(data[j][0], data[j][1], totImp, descSalud);
                            mapSalud.get(salud.toUpperCase()).add(sInfo);
                            rowhead = sheet_rem.createRow((short) 5 + k + (i*34));
                            rowhead.createCell(0).setCellValue(data[j][0]);
                            rowhead.createCell(1).setCellValue(data[j][1]);
                            rowhead.createCell(2).setCellValue(Integer.parseInt(data[j][28]));
                            rowhead.createCell(3).setCellValue(base);
                            rowhead.createCell(4).setCellValue(grat);
                            rowhead.createCell(5).setCellValue(bonoAnt);
                            rowhead.createCell(6).setCellValue(totalBon300);
                            rowhead.createCell(7).setCellValue(totalBonoAV);
                            rowhead.createCell(8).setCellValue(totalBonCol);
                            rowhead.createCell(9).setCellValue(bonoAd);
                            rowhead.createCell(10).setCellValue(valorHorEx);
                            rowhead.createCell(11).setCellValue(totImp);
                            
                            rowhead.createCell(12).setCellValue(data[j][0]);
                            rowhead.createCell(13).setCellValue(data[j][1]);
                            rowhead.createCell(14).setCellValue(col);
                            rowhead.createCell(15).setCellValue(trans);
                            rowhead.createCell(16).setCellValue(af);
                            rowhead.createCell(17).setCellValue(noImp);
                            rowhead.createCell(18).setCellValue(totalHaberes);
                            rowhead.createCell(19).setCellValue(totalAFP);
                            rowhead.createCell(20).setCellValue(totalSalud);
                            rowhead.createCell(21).setCellValue(ces);
                            rowhead.createCell(22).setCellValue(cesEmp);
                            rowhead.createCell(23).setCellValue(descLegales);

                            rowhead.createCell(24).setCellValue(data[j][0]);
                            rowhead.createCell(25).setCellValue(data[j][1]);
                            rowhead.createCell(26).setCellValue(descRenta);
                            rowhead.createCell(27).setCellValue(caja);
                            rowhead.createCell(28).setCellValue(antic);
                            rowhead.createCell(29).setCellValue(adel);
                            rowhead.createCell(30).setCellValue(cuoPres);
                            rowhead.createCell(31).setCellValue(descMensuales);
                            rowhead.createCell(32).setCellValue(totDesc);
                            rowhead.createCell(33).setCellValue(liq);
                            rowhead.createCell(34).setCellValue(sis);
                        }
                    }
                    
                    //Totales
                    rowhead = sheet_rem.createRow(66);
                    rowhead.createCell(2).setCellFormula("SUM(C6:C34,C40:C66)");
                    rowhead.createCell(3).setCellFormula("SUM(D6:D34,D40:D66)");
                    rowhead.createCell(4).setCellFormula("SUM(E6:E34,E40:E66)");
                    rowhead.createCell(5).setCellFormula("SUM(F6:F34,F40:F66)");
                    rowhead.createCell(6).setCellFormula("SUM(G6:G34,G40:G66)");
                    rowhead.createCell(7).setCellFormula("SUM(H6:H34,H40:H66)");
                    rowhead.createCell(8).setCellFormula("SUM(I6:I34,I40:I66)");
                    rowhead.createCell(9).setCellFormula("SUM(J6:J34,J40:J66)");
                    rowhead.createCell(10).setCellFormula("SUM(K6:K34,K40:K66)");
                    rowhead.createCell(11).setCellFormula("SUM(L6:L34,L40:L66)");
                    
                    rowhead.createCell(14).setCellFormula("SUM(O6:O34,O40:O66)");
                    rowhead.createCell(15).setCellFormula("SUM(P6:P34,P40:P66)");
                    rowhead.createCell(16).setCellFormula("SUM(Q6:Q34,Q40:Q66)");
                    rowhead.createCell(17).setCellFormula("SUM(R6:R34,R40:R66)");
                    rowhead.createCell(18).setCellFormula("SUM(S6:S34,S40:S66)");
                    rowhead.createCell(19).setCellFormula("SUM(T6:T34,T40:T66)");
                    rowhead.createCell(20).setCellFormula("SUM(U6:U34,U40:U66)");
                    rowhead.createCell(21).setCellFormula("SUM(V6:V34,V40:V66)");
                    rowhead.createCell(22).setCellFormula("SUM(W6:W34,W40:W66)");
                    rowhead.createCell(23).setCellFormula("SUM(X6:X34,X40:X66)");
                    
                    rowhead.createCell(26).setCellFormula("SUM(AA6:AA34,AA40:AA66)");
                    rowhead.createCell(27).setCellFormula("SUM(AB6:AB34,AB40:AB66)");
                    rowhead.createCell(28).setCellFormula("SUM(AC6:AC34,AC40:AC66)");
                    rowhead.createCell(29).setCellFormula("SUM(AD6:AD34,AD40:AD66)");
                    rowhead.createCell(30).setCellFormula("SUM(AE6:AE34,AE40:AE66)");
                    rowhead.createCell(31).setCellFormula("SUM(AF6:AF34,AF40:AF66)");
                    rowhead.createCell(32).setCellFormula("SUM(AG6:AG34,AG40:AG66)");
                    rowhead.createCell(33).setCellFormula("SUM(AH6:AH34,AH40:AH66)");
                    rowhead.createCell(34).setCellFormula("SUM(AI6:AI34,AI40:AI66)");
                    
                    
                    //Hoja detalle afp
                    j = 0;

                    //Primera linea
                    rowhead = sheet_afp.createRow((short) j++);
                    rowhead.createCell(3).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                    rowhead.getCell(3).setCellStyle(style);
                    rowhead.createCell(11).setCellValue("pag:1");

                    //Segunda linea
                    rowhead = sheet_afp.createRow((short) j++ );
                    rowhead.createCell(0).setCellValue("Rut_empresa:77.037.960-1");
                    rowhead.createCell(2).setCellValue("Mes: " + month);
                    rowhead.createCell(4).setCellValue("Año: " + year);
                    rowhead.createCell(6).setCellValue("UF: $" + uf);
                    rowhead.createCell(9).setCellValue("UTM: $" + utm);

                    j+=2;
                    for(String key : mapAfp.keySet()){
                        List<AfpInfo> aux = mapAfp.get(key);
                        rowhead = sheet_afp.createRow((short) j++);
                        rowhead.createCell(5).setCellValue("AFP " + key);
                        rowhead.getCell(5).setCellStyle(style);
                        rowhead = sheet_afp.createRow((short) j++);
                        rowhead.createCell(0).setCellValue("rut");
                        rowhead.createCell(1).setCellValue("nom_trab");
                        rowhead.createCell(2).setCellValue("tot_h_imp");
                        rowhead.createCell(3).setCellValue("desc_afp");
                        rowhead.createCell(4).setCellValue("sis");
                        rowhead.createCell(5).setCellValue("tot_afp");
                        rowhead.createCell(8).setCellValue("afiliados: " + aux.size());
                        for(AfpInfo usuario : aux){
                            rowhead = sheet_afp.createRow((short) j++);
                            rowhead.createCell(0).setCellValue(usuario.getRut());
                            rowhead.createCell(1).setCellValue(usuario.getNombre());
                            rowhead.createCell(2).setCellValue(usuario.getTotalImp());
                            rowhead.createCell(3).setCellValue(usuario.getDescAfp());
                            rowhead.createCell(4).setCellValue(usuario.getSis());
                            rowhead.createCell(5).setCellValue(usuario.getTotAfp());
                        }
                        rowhead = sheet_afp.createRow((short) j++);
                        int totalTrab = aux.size();
                        rowhead.createCell(2).setCellFormula("SUM(C" + (j-totalTrab) + ":C" + (j-1) + ")");
                        rowhead.createCell(3).setCellFormula("SUM(D" + (j-totalTrab) + ":D" + (j-1) + ")");
                        rowhead.createCell(4).setCellFormula("SUM(E" + (j-totalTrab) + ":E" + (j-1) + ")");
                        rowhead.createCell(5).setCellFormula("SUM(F" + (j-totalTrab) + ":F" + (j-1) + ")");
                        j++;
                    }
                    
                    //Hoja detalle salud
                    j = 0;

                    //Primera linea
                    rowhead = sheet_salud.createRow((short) j++);
                    rowhead.createCell(3).setCellValue("Libro de Remuneraciones - Gruas Santa Teresita FM Limitada");
                    rowhead.getCell(3).setCellStyle(style);
                    rowhead.createCell(11).setCellValue("pag:1");

                    //Segunda linea
                    rowhead = sheet_salud.createRow((short) j++ );
                    rowhead.createCell(0).setCellValue("Rut_empresa:77.037.960-1");
                    rowhead.createCell(2).setCellValue("Mes: " + month);
                    rowhead.createCell(4).setCellValue("Año: " + year);
                    rowhead.createCell(6).setCellValue("UF: $" + uf);
                    rowhead.createCell(9).setCellValue("UTM: $" + utm);

                    j+=2;
                    for(String key : mapSalud.keySet()){
                        List<SaludInfo> aux = mapSalud.get(key);
                        rowhead = sheet_salud.createRow((short) j++);
                        rowhead.createCell(5).setCellValue("Isapre " + key);
                        rowhead.getCell(5).setCellStyle(style);
                        rowhead = sheet_salud.createRow((short) j++);
                        rowhead.createCell(0).setCellValue("rut");
                        rowhead.createCell(1).setCellValue("nom_trab");
                        rowhead.createCell(2).setCellValue("tot_h_imp");
                        rowhead.createCell(3).setCellValue("desc_salud");
                        rowhead.createCell(8).setCellValue("afiliados: " + aux.size());
                        for(SaludInfo usuario : aux){
                            rowhead = sheet_salud.createRow((short) j++);
                            rowhead.createCell(0).setCellValue(usuario.getRut());
                            rowhead.createCell(1).setCellValue(usuario.getNombre());
                            rowhead.createCell(2).setCellValue(usuario.getTotImp());
                            rowhead.createCell(3).setCellValue(usuario.getDescSalud());
                        }
                        rowhead = sheet_salud.createRow((short) j++);
                        int totalTrab = aux.size();
                        rowhead.createCell(2).setCellFormula("SUM(C" + (j-totalTrab) + ":C" + (j-1) + ")");
                        rowhead.createCell(3).setCellFormula("SUM(D" + (j-totalTrab) + ":D" + (j-1) + ")");
                        j++;
                    }
                    
                    FileOutputStream fileOut;
                    fileOut = new FileOutputStream(file);
                    workbook.write(fileOut);
                    fileOut.close();
                    JOptionPane.showMessageDialog(null, "Libro de remuneraciones generado con éxito", "Operación exitosa",
                            JOptionPane.INFORMATION_MESSAGE);
                }catch(IOException ie){
                    JOptionPane.showMessageDialog(null, "El archivo està siendo ocupado\nCierre el archivo y vuelva a intentarlo", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    ie.printStackTrace();
                }catch(Exception e){
                    JOptionPane.showMessageDialog(null, "Error al crear libro de remuneraciones", "Error",
                            JOptionPane.INFORMATION_MESSAGE);
                    e.printStackTrace();
                }
            }
        };
        runnable.start();
    }
}
