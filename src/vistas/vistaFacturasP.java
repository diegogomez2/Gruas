/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import modelos.modeloFacturas;

/**
 *
 * @author diego
 */
public class vistaFacturasP extends javax.swing.JPanel {

    /**
     * Creates new form vistaFacturasP
     */
    public vistaFacturasP(String tipo, Object[][] data) {
        initComponents();
        String[] columNames = {"Código OT", "Razon", "Giro", "Dirección", "Región", "Comuna", "Fecha",
            "Neto", "IVA", "Total", "Desc"};
        DefaultTableModel datos = new DefaultTableModel(data, columNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        tablaFacturas.setModel(datos);
        TableColumnModel tcm = tablaFacturas.getColumnModel();
        tcm.removeColumn(tcm.getColumn(10));
        tablaFacturas.setAutoCreateRowSorter(true);
        if (tablaFacturas.getRowCount() > 0) {
            tablaFacturas.setRowSelectionInterval(0, 0);
        }
        tablaFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaFacturas.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent evt) {
                JTable table = (JTable) evt.getSource();
                Point p = evt.getPoint();
                int row = table.rowAtPoint(p);
                if (evt.getClickCount() == 2) {
                    controladores.controladorFacturas miControlador = new controladores.controladorFacturas();
                    try {
                        miControlador.irVistaDetalleFacturas(tablaFacturas.getValueAt(row, 0).toString());
                    } catch (ParseException ex) {
                        Logger.getLogger(vistaJornadasP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaFacturas = new javax.swing.JTable();
        botonFactura = new javax.swing.JButton();
        botonBoleta = new javax.swing.JButton();
        botonNotaDeb = new javax.swing.JButton();
        botonFacEx = new javax.swing.JButton();
        botonQuitar = new javax.swing.JButton();
        botonActualizar = new javax.swing.JButton();

        tablaFacturas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaFacturas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaFacturas);

        botonFactura.setText("Generar factura");
        botonFactura.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacturaActionPerformed(evt);
            }
        });

        botonBoleta.setText("Generar Boleta");
        botonBoleta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonBoletaActionPerformed(evt);
            }
        });

        botonNotaDeb.setText("Generar Nota débito");
        botonNotaDeb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonNotaDebActionPerformed(evt);
            }
        });

        botonFacEx.setText("Generar factura ex.");
        botonFacEx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacExActionPerformed(evt);
            }
        });

        botonQuitar.setText("Quitar");
        botonQuitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonQuitarActionPerformed(evt);
            }
        });

        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonQuitar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonFacEx)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonNotaDeb)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonBoleta)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonFactura))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonActualizar)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonBoleta, botonFacEx, botonFactura, botonNotaDeb});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(botonActualizar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonFactura)
                    .addComponent(botonBoleta)
                    .addComponent(botonNotaDeb)
                    .addComponent(botonFacEx)
                    .addComponent(botonQuitar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonFacturaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacturaActionPerformed
        File file = new File("test.log");
        PrintStream ps = null;
        try {
            ps = new PrintStream(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
        }
        try{
            controladores.controladorCrearFactura miControlador = new controladores.controladorCrearFactura();
            controladores.controladorOts micontroladorOts = new controladores.controladorOts();
            int filas = tablaFacturas.getRowCount();
            if(filas > 0){
                String flag = verificarRazon();
                if (flag.compareTo("correcto") == 0) {
                    String[] idOts = new String[filas];
                    int neto = 0, iva = 0, total = 0, desc = 0;
                    for (int i = 0; i < filas; i++) {
                        idOts[i] = getIdFact(i);
                        desc = getDescFact(i);
//                        neto += getNetoFact(i) - desc;
                        neto += getNetoFact(i);
                        iva += getIvaFact(i);
                        total += getTotalFact(i);
                    }
                    controladores.controladorFacturas micontroladorFacturas = new controladores.controladorFacturas();
                    String id = micontroladorFacturas.archivarFacturas(idOts, neto, iva, total, "factura", "0", "0");
                    if(id.compareTo("-1") == 0){
                        JOptionPane.showMessageDialog(null, "La factura ya había sido ingresada al sistema", "Error factura duplicada",
                                JOptionPane.INFORMATION_MESSAGE);
                        JTabbedPane tabs = (JTabbedPane) this.getParent();
                        micontroladorOts.crearControladorPrincipal(tabs);
                        miControlador.crearControladorPrincipal(tabs);
                    }else{
                        try {
                            //docRef va en el tag observaciones
                            String docRef = JOptionPane.showInputDialog("Ingrese documento de referencia: ");
                            if ((miControlador.crearFacXML(idOts, Integer.toString(neto), Integer.toString(iva),
                                    Integer.toString(total), id, docRef).compareTo("correcto") == 0)) {
                                JTabbedPane tabs = (JTabbedPane) this.getParent();
                                micontroladorOts.crearControladorPrincipal(tabs);
                                miControlador.crearControladorPrincipal(tabs);
                            }
                        } catch (ParseException ex) {
                            ex.printStackTrace();
//                            ex.printStackTrace(ps);
                            Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
                        } 
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "No se puede generar una factura para clientes distintos");
                }
            }else{
                JOptionPane.showMessageDialog(null, "No hay ots para generar una factura");
            }
        }catch(Exception e){
//            e.printStackTrace(ps);
            e.printStackTrace();
        }
    }//GEN-LAST:event_botonFacturaActionPerformed

    private void botonBoletaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonBoletaActionPerformed
        controladores.controladorCrearFactura miControlador = new controladores.controladorCrearFactura();
        controladores.controladorOts micontroladorOts = new controladores.controladorOts();
        int filas = tablaFacturas.getRowCount();
        if(filas > 0){
            String flag = verificarRazon();
            if(flag.compareTo("correcto") == 0){
                String[] idOts = new String[filas];
                int neto = 0, iva = 0, total = 0, desc = 0;
                for (int i = 0; i < filas; i++) {
                    idOts[i] = getIdFact(i);
                    desc = getDescFact(i);
//                    neto += getNetoFact(i) - desc;
                    neto += getNetoFact(i);
                    iva += getIvaFact(i);
                    total += getTotalFact(i);
                }
                controladores.controladorFacturas micontroladorFacturas = new controladores.controladorFacturas();
                String id = micontroladorFacturas.archivarFacturas(idOts, neto, iva, total, "boleta", "0", "0");
                if ((miControlador.crearBolXML(idOts, Integer.toString(neto), Integer.toString(iva),
                        Integer.toString(total), id).compareTo("correcto") == 0)) {
                    JTabbedPane tabs = (JTabbedPane) this.getParent();
                    try {
                        micontroladorOts.crearControladorPrincipal(tabs);
                    } catch (ParseException ex) {
                        Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    miControlador.crearControladorPrincipal(tabs);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se puede generar una boleta para clientes distintos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay ots para generar una boleta");
        }
    }//GEN-LAST:event_botonBoletaActionPerformed

    private void botonNotaDebActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonNotaDebActionPerformed
        controladores.controladorCrearFactura miControlador = new controladores.controladorCrearFactura();
        controladores.controladorOts micontroladorOts = new controladores.controladorOts();
        int filas = tablaFacturas.getRowCount();
        if(filas > 0){
            String flag = verificarRazon();
            JTextField idfac = new JTextField();
            JComboBox combo = new JComboBox(new String[]{"Factura", "Boleta", "Factura exenta"});
            Object[] message = {
                "Folio: ",idfac,
                "Tipo documento: ", combo
            };
            String id_fac, tiponc;
            if(flag.compareTo("correcto") == 0){
                int resp = JOptionPane.showConfirmDialog(null, message, "N° de folio de factura: ", JOptionPane.OK_CANCEL_OPTION);
                if(resp == JOptionPane.OK_OPTION){
                    id_fac = idfac.getText();
                    tiponc = combo.getSelectedItem().toString();
                    boolean existe = verificarExisteFactura(id_fac, tiponc);
                    if(existe){
                        String razonFac = verificarRazonFactura(id_fac, tiponc);
                        if(razonFac.compareTo(getRazonFila(0)) == 0){
                            String[] idOts = new String[filas];
                            int neto = 0, iva = 0, total = 0, desc = 0;
                            for (int i = 0; i < filas; i++) {
                                idOts[i] = getIdFact(i);
                                desc = getDescFact(i);
//                                neto += getNetoFact(i) - desc;
                                neto += getNetoFact(i);
                                iva += getIvaFact(i);
                                total += getTotalFact(i);
                            }
                            controladores.controladorFacturas micontroladorFacturas = new controladores.controladorFacturas();
                            String id = micontroladorFacturas.archivarFacturas(idOts, neto, iva, total, "nota debito", id_fac, tiponc);
                            if(id.compareTo("incorrecto") != 0){
                                if(id.compareTo("-1") == 0){
                                    JOptionPane.showMessageDialog(null, "La nota de débito ya había sido ingresada al sistema", "Error nota de débito duplicada",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    JTabbedPane tabs = (JTabbedPane) this.getParent();
                                    try {
                                        micontroladorOts.crearControladorPrincipal(tabs);
                                    } catch (ParseException ex) {
                                        Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                    miControlador.crearControladorPrincipal(tabs);
                                }else{
                                    try {
                                        if ((miControlador.crearNotaDebXML(idOts, Integer.toString(neto), Integer.toString(iva),
                                                Integer.toString(total), id, id_fac).compareTo("correcto") == 0)) {
                                            JTabbedPane tabs = (JTabbedPane) this.getParent();
                                            micontroladorOts.crearControladorPrincipal(tabs);
                                            miControlador.crearControladorPrincipal(tabs);
                                        }
                                    } catch (ParseException ex) {
                                        Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
                                    }  
                                }
                            }
                        }else{
                            JOptionPane.showMessageDialog(null, "No se puede generar una nota de débito para una factura con clientes distintos");
                        }  
                    }else{
                        JOptionPane.showMessageDialog(null, "La " + tiponc + " con folio " + id_fac + " no existe.");
                    }
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se puede generar una nota de débito para clientes distintos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay ots para generar una nota de débito");
        }
    }//GEN-LAST:event_botonNotaDebActionPerformed

    private void botonFacExActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacExActionPerformed
        controladores.controladorCrearFactura miControlador = new controladores.controladorCrearFactura();
        controladores.controladorOts micontroladorOts = new controladores.controladorOts();
        int filas = tablaFacturas.getRowCount();
        if(filas > 0){
            String flag = verificarRazon();
            if(flag.compareTo("correcto") == 0){
                String[] idOts = new String[filas];
                int neto = 0, iva = 0, total = 0, desc = 0;
                for (int i = 0; i < filas; i++) {
                    idOts[i] = getIdFact(i);
                    desc = getDescFact(i);
                    neto += getNetoFact(i) - desc;
                    iva += getIvaFact(i);
                    total += getTotalFact(i);
                }
                controladores.controladorFacturas micontroladorFacturas = new controladores.controladorFacturas();
                String id = micontroladorFacturas.archivarFacturas(idOts, neto, iva, total, "factura ex", "0", "0");
                String docRef = JOptionPane.showInputDialog("Ingrese documento de referencia: ");
                if ((miControlador.crearFacExXML(idOts, Integer.toString(neto), Integer.toString(iva),
                        Integer.toString(total), id, docRef).compareTo("correcto") == 0)) {
                    JTabbedPane tabs = (JTabbedPane) this.getParent();
                    try {
                        micontroladorOts.crearControladorPrincipal(tabs);
                    } catch (ParseException ex) {
                        Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    miControlador.crearControladorPrincipal(tabs);
                }
            }else{
                JOptionPane.showMessageDialog(null, "No se puede generar una factura exenta para clientes distintos");
            }
        }else{
            JOptionPane.showMessageDialog(null, "No hay ots para generar una factura exenta");
        }
    }//GEN-LAST:event_botonFacExActionPerformed

    private void botonQuitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonQuitarActionPerformed
        String id;
        String idOt;
        controladores.controladorOts miControlador = new controladores.controladorOts();
        controladores.controladorFacturas micontroladorFacturas = new controladores.controladorFacturas();
        boolean selected = tablaFacturas.getSelectedRowCount() > 0;
        if (selected) {
            int row = getFilaSeleccionada();
            id = getIdFila(row);
            idOt = getIdOt(row);
            miControlador.quitarFactura(idOt);
            JTabbedPane tabs = (JTabbedPane) this.getParent();
            try {
                micontroladorFacturas.crearControladorPrincipal(tabs);
                //JOptionPane.showMessageDialog(null, "Orden de trabajo facturada con éxito");
            } catch (ParseException ex) {
                Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una orden de trabajo quitar");
        }
    }//GEN-LAST:event_botonQuitarActionPerformed

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        controladores.controladorFacturas miControlador = new controladores.controladorFacturas();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        try {
            miControlador.crearControladorPrincipal(tabs);
        } catch (ParseException ex) {
            Logger.getLogger(vistaFacturasP.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_botonActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonBoleta;
    private javax.swing.JButton botonFacEx;
    private javax.swing.JButton botonFactura;
    private javax.swing.JButton botonNotaDeb;
    private javax.swing.JButton botonQuitar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaFacturas;
    // End of variables declaration//GEN-END:variables

    public String getIdFact(int row) {
        return tablaFacturas.getValueAt(row, 0).toString();
    }

    public int getNetoFact(int row) {
        return Integer.parseInt(tablaFacturas.getValueAt(row, 7).toString());
    }

    public int getDescFact(int row) {
        return Integer.parseInt(tablaFacturas.getModel().getValueAt(tablaFacturas.convertRowIndexToModel(row), 10).toString());
    }
    
    public int getIvaFact(int row) {
        return Integer.parseInt(tablaFacturas.getValueAt(row, 8).toString());
    }

    public int getTotalFact(int row) {
        return Integer.parseInt(tablaFacturas.getValueAt(row, 9).toString());
    }

    public String verificarRazon() {
        String razon = getRazonFila(0);
        for (int i = 1; i < tablaFacturas.getRowCount(); i++) {
            if (razon.compareTo(getRazonFila(i)) != 0) {
                return "incorrecto";
            }
        }
        return "correcto";
    }
    
    public String verificarRazonFactura(String fol, String tipo) {
        modelos.modeloFacturas factura = new modeloFacturas();
        return factura.obtenerRazonFactura(fol, tipo.toLowerCase());
    }
    
    public boolean verificarExisteFactura(String fol, String tipo){
        modelos.modeloFacturas factura = new modeloFacturas();
        return factura.verificarExisteFactura(fol, tipo);
    }

    public String getRazonFila(int row) {
        return tablaFacturas.getValueAt(row, 1).toString();
    }

    public int getFilaSeleccionada() {
        return tablaFacturas.getSelectedRow();
    }

    public String getIdFila(int row) {
        return tablaFacturas.getValueAt(row, 0).toString();
    }

    public String getIdOt(int row) {
        return tablaFacturas.getValueAt(row, 0).toString();
    }
}
