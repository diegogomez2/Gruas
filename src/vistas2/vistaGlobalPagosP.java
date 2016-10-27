/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas2;

import controladores2.controladorGlobalPagos;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.RowFilter;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.plaf.basic.BasicListUI;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author diego
 */
public class vistaGlobalPagosP extends javax.swing.JPanel {

    /**
     * Creates new form vistaGlobalPagosP
     */
    DefaultTableModel datos, datos2;
    public vistaGlobalPagosP(String tipo, Object[][] noPagados, Object[][] pagados) {
        initComponents();
        //Object[][] data = new Object[0][0];
        String[] columNames = {"Medio de pago", "Rut proveedor", "Razón social", "Folio", 
            "N° de cheque/cuota", "Fecha de pago", "Estado", "Id", "Fac"};
        datos = new DefaultTableModel(noPagados, columNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        datos2 = new DefaultTableModel(pagados, columNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tablaNoPagos.setModel(datos);
        tablaPagos.setModel(datos2);
        TableColumnModel tcm = tablaNoPagos.getColumnModel();
        TableColumnModel tcm2 = tablaPagos.getColumnModel();
        tcm.removeColumn(tcm.getColumn(6));
        tcm.removeColumn(tcm.getColumn(6));
        tcm.removeColumn(tcm.getColumn(6));
        tcm2.removeColumn(tcm2.getColumn(6));
        tcm2.removeColumn(tcm2.getColumn(6));
        tcm2.removeColumn(tcm2.getColumn(6));
        tablaNoPagos.setAutoCreateRowSorter(true);
        tablaPagos.setAutoCreateRowSorter(true);
        tablaNoPagos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                tablaPagos.clearSelection();
            }
        });
        tablaPagos.getSelectionModel().addListSelectionListener(new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent e){
                tablaNoPagos.clearSelection();
            }
        });
        jSplitPane1.setDividerLocation(0.5);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        botonCambiarEstado = new javax.swing.JButton();
        textoFiltro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonActualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel(){
            private boolean painted;
            @Override
            public void paint(Graphics g) {
                super.paint(g);

                if (!painted) {
                    painted = true;
                    jSplitPane1.setDividerLocation(0.5);
                }
            }
        };
        jSplitPane1 = new javax.swing.JSplitPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaNoPagos = new javax.swing.JTable(){
            //Implement table cell tool tips.
            public String getToolTipText(MouseEvent e) {
                String tip = null;
                java.awt.Point p = e.getPoint();
                int rowIndex = rowAtPoint(p);
                int colIndex = columnAtPoint(p);

                try {
                    tip = getValueAt(rowIndex, colIndex).toString();
                } catch (RuntimeException e1) {
                    //catch null pointer exception if mouse is over an empty line
                }

                return tip;
            }
        };
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaPagos = new javax.swing.JTable();

        botonCambiarEstado.setText("Cambiar estado de pago");
        botonCambiarEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCambiarEstadoActionPerformed(evt);
            }
        });

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

        tablaNoPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaNoPagos.setToolTipText("");
        tablaNoPagos.getTableHeader().setReorderingAllowed(false);
        tablaNoPagos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaNoPagos);

        jSplitPane1.setLeftComponent(jScrollPane1);

        tablaPagos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaPagos);

        jSplitPane1.setRightComponent(jScrollPane2);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 931, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSplitPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonCambiarEstado))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 620, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(botonActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 552, Short.MAX_VALUE)
                .addComponent(botonCambiarEstado)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(43, 43, 43)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(43, 43, 43)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonCambiarEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCambiarEstadoActionPerformed
        controladorGlobalPagos miControlador = new controladorGlobalPagos();
        boolean selectedPagos = tablaPagos.getSelectedRowCount() > 0;
        boolean selectedNoPagos = tablaNoPagos.getSelectedRowCount() > 0;
        if(selectedNoPagos){
                int row = getFilaSeleccionada(0);
                String id = getIdFila(row, 0);
                String fac = getIdFac(row, 0);
                System.out.println(fac);
                miControlador.irVistaCambiarEstado(id, fac);
                JTabbedPane tabs = (JTabbedPane)this.getParent();
                miControlador.crearControladorPrincipal(tabs);
            }else if(selectedPagos){
                int row = getFilaSeleccionada(1);
                String id = getIdFila(row, 1);
                String fac = getIdFac(row, 1);
                System.out.println(fac);
                miControlador.irVistaCambiarEstado(id, fac);
                JTabbedPane tabs = (JTabbedPane)this.getParent();
                miControlador.crearControladorPrincipal(tabs);
            }
            else{
                JOptionPane.showMessageDialog(null, "Debe seleccionar una cuota para ser modificada");
            }
    }//GEN-LAST:event_botonCambiarEstadoActionPerformed

    private void textoFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyReleased
                String query = textoFiltro.getText();
                filtrar(query);
    }//GEN-LAST:event_textoFiltroKeyReleased

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
                controladores2.controladorGlobalPagos miControlador = new controladorGlobalPagos();
                JTabbedPane tabs = (JTabbedPane)this.getParent();
                miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonCambiarEstado;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSplitPane jSplitPane1;
    private javax.swing.JTable tablaNoPagos;
    private javax.swing.JTable tablaPagos;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
    
    public int getFilaSeleccionada(int tabla) {
        if(tabla == 0){
            return tablaNoPagos.getSelectedRow();
        }else{
            return tablaPagos.getSelectedRow();
        }
    }
    
    public String getIdFila(int row, int tabla){
        if(tabla == 0){
            return tablaNoPagos.getModel().getValueAt(tablaNoPagos.convertRowIndexToModel(row), 7).toString();
        }else{
            return tablaPagos.getModel().getValueAt(tablaPagos.convertRowIndexToModel(row), 7).toString();
        }
    }
    
    public String getIdFac(int row, int tabla){
        if(tabla == 0){
            return tablaNoPagos.getModel().getValueAt(tablaNoPagos.convertRowIndexToModel(row), 8).toString();
        }else{
            return tablaPagos.getModel().getValueAt(tablaPagos.convertRowIndexToModel(row), 8).toString();
        }
    }
    
    public void filtrar(String query){
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<>(datos);
        TableRowSorter<DefaultTableModel> sorter2 = new TableRowSorter<>(datos2);
        tablaNoPagos.setRowSorter(sorter);
        tablaPagos.setRowSorter(sorter2);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+query));
        sorter2.setRowFilter(RowFilter.regexFilter("(?i)"+query));
    }   
}