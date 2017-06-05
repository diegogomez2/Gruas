/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.io.File;
import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import modelos.modeloFacturas;
import modelos.modeloOts;

/**
 *
 * @author diego
 */
public class controladorFacturasOC {
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    static vistas4.vistaFacturasOCP vistaFacturasOCP;
     
    public JPanel mostrarTabControlFacturasOC(String tipo, Object[][] data) {
        vistaFacturasOCP = new vistas4.vistaFacturasOCP(tipo, data);  
        return vistaFacturasOCP;
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(1);
        tabs.remove(1);
        tabs.insertTab("Ocs", null, miControlador.crearControladorOcsP(), null, 1);
        tabs.insertTab("A facturar", null, miControlador.crearControladorFacturasOCP(), null, 2);
        tabs.setSelectedIndex(2);
    }
    
    public String archivarFacturas(String[] idOcs, int neto, int iva, int total, String tipo, String id_fac, String tiponc){
        File file = new File("test.log");
        PrintStream ps = null;
        try{
            ps = new PrintStream(file);
            modelos.modeloFacturas factura = new modeloFacturas();
            String id;
            modelos4.modeloOcs oc = new modelos4.modeloOcs();
            if(tipo.compareTo("nota debito") == 0){
                String folio = factura.folioND();
                id = factura.ingresarND(formatDate.format(new Date()), neto, iva, total, id_fac, folio, tiponc);
                String resp = "";
                if(id.compareTo("incorrecto") == 0){
                    JOptionPane.showMessageDialog(null, "No se encontró la factura con el folio indicado");
                }else{
                    for (String idOc : idOcs) {
                        resp = oc.archivarFacturaNDOC(idOc, id);
                        if(resp.compareTo("yafacturada") == 0){
                            factura.borrarNDDuplicada(id);
                            return "-1";
                        }
                    }  
                }
            }else{
                String folio = "";
                if(tipo.compareTo("factura") == 0){
                    folio = factura.folioFac();
                }else if(tipo.compareTo("boleta") == 0){
                    folio = factura.folioBol();
                }else{
                    folio = factura.folioFacEx();
                }
                id = factura.ingresarFacturada(formatDate.format(new Date()), neto, iva, total, tipo, folio);
                String resp = "";
                for (String idOc : idOcs) {
                    resp = oc.archivarFacturaOC(idOc, id);
                    if(resp.compareTo("yafacturada") == 0){
                        factura.borrarFacturaDuplicada(id);
                        return "-1";
                    }
                }
            }
            return id;
        }catch(Exception e){
            e.printStackTrace();
//            e.printStackTrace(ps);
            return "-1";
        }
    }
}
