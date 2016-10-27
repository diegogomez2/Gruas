/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas2;

import controladores2.controladorProveedores;
import vistas.*;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author Diego
 */
public class vistaProveedoresP extends javax.swing.JPanel {

    /**
     * Creates new form vistaClientesP
     */
    Object[][] data;
    DefaultTableModel datos;
    
    public vistaProveedoresP(String tipo, Object[][] data) {
        initComponents();
        String[] columNames = {"Rut", "Razón social", "Teléfono", "Dirección", "Contacto"};
        datos = new DefaultTableModel(data, columNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tablaProveedores.setModel(datos);
        tablaProveedores.setAutoCreateRowSorter(true);
        if(tablaProveedores.getRowCount() > 0) tablaProveedores.setRowSelectionInterval(0, 0);
        tablaProveedores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaProveedores.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                JTable table = (JTable)evt.getSource();
                Point p = evt.getPoint();
                int row = table.rowAtPoint(p);
                if(evt.getClickCount() == 2){
                    controladorProveedores miControlador = new controladorProveedores();
                    miControlador.irVistaDetalleProveedor(tablaProveedores.getValueAt(row, 0).toString());
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
        tablaProveedores = new javax.swing.JTable(){
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
        botonAgregarProveedor = new javax.swing.JButton();
        botonEliminarProveedor = new javax.swing.JButton();
        botonModificarProveedor = new javax.swing.JButton();
        textoFiltro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonActualizar = new javax.swing.JButton();

        tablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaProveedores.setToolTipText("");
        tablaProveedores.getTableHeader().setReorderingAllowed(false);
        tablaProveedores.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaProveedores);

        botonAgregarProveedor.setText("Agregar Proveedor");
        botonAgregarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarProveedorActionPerformed(evt);
            }
        });

        botonEliminarProveedor.setText("Eliminar Proveedor");
        botonEliminarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarProveedorActionPerformed(evt);
            }
        });

        botonModificarProveedor.setText("Modificar Proveedor");
        botonModificarProveedor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarProveedorActionPerformed(evt);
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 824, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(373, 415, Short.MAX_VALUE)
                        .addComponent(botonAgregarProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminarProveedor)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonModificarProveedor))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonAgregarProveedor, botonEliminarProveedor, botonModificarProveedor});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(botonActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 419, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAgregarProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonEliminarProveedor, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(botonModificarProveedor, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarProveedorActionPerformed
        controladores2.controladorProveedores miControlador = new controladorProveedores();
        miControlador.irVistaIngresarProveedores();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonAgregarProveedorActionPerformed

    private void botonEliminarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarProveedorActionPerformed
        controladores2.controladorProveedores miControlador = new controladorProveedores();
        String rut, nombres;
        boolean selected = tablaProveedores.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            rut = getRutFila(row);
            nombres = getNombresFila(row);
            int dialogResult = JOptionPane.showOptionDialog(null, "Esta seguro que desea eliminar el proveedor: \n " +
                    "Rut: " + rut + "\nRazón social: " + nombres, "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
            if(dialogResult == JOptionPane.YES_OPTION)  miControlador.eliminarProveedores(rut);
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor para ser eliminado");
        }
    }//GEN-LAST:event_botonEliminarProveedorActionPerformed

    private void botonModificarProveedorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarProveedorActionPerformed
        controladorProveedores miControlador = new controladorProveedores();
        boolean selected = tablaProveedores.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String rut = getRutFila(row);
            String nombres = getNombresFila(row);
            String[] rut_dv = rut.split("-");
            miControlador.irVistaModificarProveedores(rut_dv[0], nombres);
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un proveedor para ser modificado");
        }
    }//GEN-LAST:event_botonModificarProveedorActionPerformed

    private void textoFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyReleased
        String query = textoFiltro.getText();
        filtrar(query);
    }//GEN-LAST:event_textoFiltroKeyReleased

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        controladorProveedores miControlador = new controladorProveedores();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonActualizarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAgregarProveedor;
    private javax.swing.JButton botonEliminarProveedor;
    private javax.swing.JButton botonModificarProveedor;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaProveedores;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables
    
    public int getFilaSeleccionada() {
        return tablaProveedores.getSelectedRow();
    }

    public String getRutFila(int row){
        return tablaProveedores.getValueAt(row, 0).toString();
    }
    
    public String getNombresFila(int row){
        return tablaProveedores.getValueAt(row,1).toString();
    }
    
    public void filtrar(String query){
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(datos);
        tablaProveedores.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+query));
    }
}