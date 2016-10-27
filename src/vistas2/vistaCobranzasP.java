/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas2;

import controladores2.controladorCobranzas;
import java.awt.Component;
import vistas.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author diego
 */
public class vistaCobranzasP extends javax.swing.JPanel {

    /**
     * Creates new form vistaFacturadasP
     */
    MyTableModel datos;
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    
    
    public vistaCobranzasP(String tipo, Object[][] data) {
        datos = new MyTableModel(data);
        initComponents();
        dfs.setCurrencySymbol("$");
        dfs.setGroupingSeparator('.');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) FORMAT).setDecimalFormatSymbols(dfs);
        
        tablaFacturadas.setModel(datos);
        TableColumnModel tcm = tablaFacturadas.getColumnModel();
        tcm.removeColumn(tcm.getColumn(14));
        tcm.removeColumn(tcm.getColumn(14));
        tablaFacturadas.getColumnModel().getColumn(5).setCellRenderer(new CurrencyTableCellRenderer());
        tablaFacturadas.getColumnModel().getColumn(6).setCellRenderer(new CurrencyTableCellRenderer());
        tablaFacturadas.getColumnModel().getColumn(7).setCellRenderer(new CurrencyTableCellRenderer());
        tablaFacturadas.getColumnModel().getColumn(11).setCellRenderer(new CurrencyTableCellRenderer());
        tablaFacturadas.setAutoCreateRowSorter(true);
        if(tablaFacturadas.getRowCount() > 0) tablaFacturadas.setRowSelectionInterval(0, 0);
        tablaFacturadas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaFacturadas.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                JTable table = (JTable)evt.getSource();
                Point p = evt.getPoint();
                int row = table.rowAtPoint(p);
                if(evt.getClickCount() == 2){
                    controladores.controladorFacturadas miControlador = new controladores.controladorFacturadas();
                    try {
                        miControlador.irVistaDetalleFacturadas(tablaFacturadas.getValueAt(row, 0).toString(),
                                tablaFacturadas.getModel().getValueAt(tablaFacturadas.convertColumnIndexToModel(row), 14).toString());
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
        tablaFacturadas = new javax.swing.JTable();
        textoFiltro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonActualizar = new javax.swing.JButton();
        botonCobranza = new javax.swing.JButton();
        botonPago = new javax.swing.JButton();
        botonVer = new javax.swing.JButton();

        tablaFacturadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaFacturadas.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaFacturadas);

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoFiltroKeyReleased(evt);
            }
        });

        jLabel1.setText("Filtro");

        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        botonCobranza.setText("Gestionar cobranza");
        botonCobranza.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCobranzaActionPerformed(evt);
            }
        });

        botonPago.setText("Gestionar pago");
        botonPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPagoActionPerformed(evt);
            }
        });

        botonVer.setText("Ver gestiones");
        botonVer.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVerActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonVer)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonPago)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCobranza)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonCobranza, botonPago, botonVer});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(botonActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 394, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCobranza)
                    .addComponent(botonPago)
                    .addComponent(botonVer))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void textoFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyReleased
        String query = textoFiltro.getText();
        filtrar(query);
    }//GEN-LAST:event_textoFiltroKeyReleased

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        controladores2.controladorCobranzas miControlador = new controladores2.controladorCobranzas();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void botonCobranzaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCobranzaActionPerformed
        controladorCobranzas miControlador = new controladorCobranzas();
        
        boolean selected = tablaFacturadas.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String id = getIdFila(row);
            String fac = getFacFila(row);
            String tipo = getTipoFila(row);
            try {
                miControlador.irVistaGestionCobranza(fac, tipo);
            } catch (ParseException ex) {
                Logger.getLogger(vistaCobranzasP.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una factura para ser gestionada");
        }
    }//GEN-LAST:event_botonCobranzaActionPerformed

    private void botonPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPagoActionPerformed
        controladorCobranzas miControlador = new controladorCobranzas();
        boolean selected = tablaFacturadas.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String id = getIdFila(row);
            String fol = getFacFila(row);
            String tipo = getTipoFila(row);
            try {
                miControlador.irVistaGestionPago(id, tipo);
            } catch (ParseException ex) {
                Logger.getLogger(vistaCobranzasP.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una factura para ser gestionada");
        }
    }//GEN-LAST:event_botonPagoActionPerformed

    private void botonVerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVerActionPerformed
        controladorCobranzas miControlador = new controladorCobranzas();
        boolean selected = tablaFacturadas.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String id = getIdFila(row);
            String tipo = getTipoFila(row);
            System.out.println(id);
            try {
                miControlador.irVistaVerGestion(id, tipo);
            } catch (ParseException ex) {
                Logger.getLogger(vistaCobranzasP.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar una factura para visualizar su gestión");
        }
    }//GEN-LAST:event_botonVerActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonCobranza;
    private javax.swing.JButton botonPago;
    private javax.swing.JButton botonVer;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaFacturadas;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
    public int getFilaSeleccionada() {
        return tablaFacturadas.getSelectedRow();
    }
    
    public String getIdFila(int row){
        return tablaFacturadas.getValueAt(row, 0).toString();
    }
    
    public String getTipoFila(int row){
        return tablaFacturadas.getModel().getValueAt(tablaFacturadas.convertColumnIndexToModel(row), 14).toString();
    }
    
    public String getFacFila(int row){
        return tablaFacturadas.getModel().getValueAt(tablaFacturadas.convertColumnIndexToModel(row), 15).toString();
    }
    
    public void filtrar(String query) {
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(datos);
        tablaFacturadas.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)" + query));
    }
    
    public class MyTableModel extends DefaultTableModel{
        public MyTableModel() {
          super(new String[]{"N°Folio", "Rut", "Razon", "Fecha", "Días emisión", "Neto", "IVA", 
             "Total", "Forma pago", "Medio pago", "Abono", "Monto abono", "Contacto", "Telefono", "Tipo", "Fac"}, 0);
        }
        public MyTableModel(Object[][] data){
            super(new String[]{"N°Folio", "Rut", "Razon", "Fecha", "Días emisión", "Neto", "IVA", 
             "Total", "Forma pago", "Medio pago", "Abono", "Monto abono", "Contacto", "Telefono", "Tipo", "Fac"}, 0);
            
            int i = 0;
            this.setRowCount(data.length);
            for(Object[] data1 : data){
                //int ot = Integer.parseInt(data1[0].toString());
                int fol = Integer.parseInt(data1[0].toString());
                int dias = Integer.parseInt(data1[4].toString());
                int neto = Integer.parseInt(data1[5].toString());
                int iva = Integer.parseInt(data1[6].toString());
                int tot = Integer.parseInt(data1[7].toString());
                int mon = Integer.parseInt(data1[11].toString());
                //this.setValueAt(ot, i, 0);
                this.setValueAt(fol, i, 0);
                this.setValueAt(data1[1], i, 1);
                this.setValueAt(data1[2], i, 2);
                this.setValueAt(data1[3], i, 3);
                this.setValueAt(dias, i, 4);
                this.setValueAt(neto, i, 5);
                this.setValueAt(iva, i, 6);
                this.setValueAt(tot, i, 7);
                this.setValueAt(data1[8], i, 8);
                this.setValueAt(data1[9], i, 9);
                this.setValueAt(data1[10], i, 10);
                this.setValueAt(mon, i, 11);
                this.setValueAt(data1[12], i, 12);
                this.setValueAt(data1[13], i, 13);
                this.setValueAt(data1[14], i, 14);
                this.setValueAt(data1[15], i, 15);
                i++;
            }
        }
        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                case 0:
                    return Integer.class;
                case 4:
                    return Integer.class;
                case 5:
                    return Integer.class;
                case 6:
                    return Integer.class;
                case 7:
                    return Integer.class;
                case 11:
                    return Integer.class;
                default:
                    return String.class;
          }
        }
        
        @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
    }
    
    public class CurrencyTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public final Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
                final Component result = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
                if (value instanceof Number) {
                    setHorizontalAlignment(JLabel.RIGHT);
                    setText(FORMAT.format(value));
                } else {
                    setText("");
                }
                return result;
            }
    }
}