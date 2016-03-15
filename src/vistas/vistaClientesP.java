/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package vistas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Diego
 */
public class vistaClientesP extends javax.swing.JPanel {

    /**
     * Creates new form vistaClientesP
     */
    Object[][] data;
    
    public vistaClientesP(String tipo, Object[][] data) {
        initComponents();
        final int rows = 5;
        String[] columNames = {"Rut", "Nombre", "Teléfono", "Dirección", "Razón social"};
        DefaultTableModel datos = new DefaultTableModel(data, columNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tablaClientes.setModel(datos);
        if(tablaClientes.getRowCount() > 0) tablaClientes.setRowSelectionInterval(0, 0);
        tablaClientes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaClientes.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                JTable table = (JTable)evt.getSource();
                Point p = evt.getPoint();
                int row = table.rowAtPoint(p);
                if(evt.getClickCount() == 2){
                    controladores.controladorClientes miControlador = new controladores.controladorClientes();
                    miControlador.irVistaDetalleCliente(tablaClientes.getValueAt(row, 0).toString());
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
        tablaClientes = new javax.swing.JTable();
        botonAgregarCliente = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonModificar = new javax.swing.JButton();

        tablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"", null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Rut", "Nombre", "Teléfono", "Dirección"
            }
        ));
        tablaClientes.setToolTipText("");
        jScrollPane1.setViewportView(tablaClientes);

        botonAgregarCliente.setText("Agregar Cliente");
        botonAgregarCliente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarClienteActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar Cliente");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonModificar.setText("Modificar Cliente");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAgregarCliente)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonEliminar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonModificar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 529, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonEliminar)
                    .addComponent(botonAgregarCliente)
                    .addComponent(botonModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(37, 37, 37))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarClienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarClienteActionPerformed
        controladores.controladorClientes miControlador = new controladores.controladorClientes();
        miControlador.irVistaIngresarClientes();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonAgregarClienteActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        controladores.controladorClientes miControlador = new controladores.controladorClientes();
        String rut, nombres, apP;
        boolean selected = tablaClientes.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            rut = getRutFila(row);
            nombres = getNombresFila(row);
            apP = getApPaternoFila(row);
            int dialogResult = JOptionPane.showOptionDialog(tablaClientes, "Esta seguro que desea eliminar el cliente: \n "
            + rut + " " + nombres + " " + apP, "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
            if(dialogResult == JOptionPane.YES_OPTION)  miControlador.eliminarClientes(rut);
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(tablaClientes, "Debe seleccionar un cliente para ser eliminado");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        controladores.controladorClientes miControlador = new controladores.controladorClientes();
        boolean selected = tablaClientes.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String rut = getRutFila(row);
            String nombres = getNombresFila(row);
            String apP = getApPaternoFila(row);
            String[] rut_dv = rut.split("-");
            miControlador.irVistaModificarClientes(rut_dv[0], nombres, apP);
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(tablaClientes, "Debe seleccionar un cliente para ser modificado");
        }
    }//GEN-LAST:event_botonModificarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAgregarCliente;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaClientes;
    // End of variables declaration//GEN-END:variables
    
    public int getFilaSeleccionada() {
        return tablaClientes.getSelectedRow();
    }

    public String getRutFila(int row){
        return tablaClientes.getValueAt(row, 0).toString();
    }
    
    public String getNombresFila(int row){
        return tablaClientes.getValueAt(row,1).toString();
    }
    
    public String getApPaternoFila(int row){
        return tablaClientes.getValueAt(row, 2).toString();
    }
}
