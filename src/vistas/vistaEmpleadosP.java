/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.RowFilter;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author diego
 */
public class vistaEmpleadosP extends javax.swing.JPanel {

    DefaultTableModel datos;
    
    public vistaEmpleadosP(String tipo, Object[][] data) {
        initComponents();
        String[] columNames = {"Rut", "Nombre", "Cargo", "Teléfono"};
        datos = new DefaultTableModel(data, columNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tablaEmpleados.setModel(datos);
        tablaEmpleados.setAutoCreateRowSorter(true);
        if(tablaEmpleados.getRowCount() > 0) tablaEmpleados.setRowSelectionInterval(0, 0);
        tablaEmpleados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaEmpleados.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                JTable table = (JTable)evt.getSource();
                Point p = evt.getPoint();
                int row = table.rowAtPoint(p);
                if(evt.getClickCount() == 2){
                    controladores.controladorEmpleados miControlador = new controladores.controladorEmpleados();
                    try {
                        miControlador.irVistaDetalleEmpleado(tablaEmpleados.getValueAt(row, 0).toString());
                    } catch (ParseException ex) {
                        Logger.getLogger(vistaEmpleadosP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        });    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable(){
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
        botonModificar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonAgregar = new javax.swing.JButton();
        textoFiltro = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        botonActualizar = new javax.swing.JButton();
        botonVistaRemuneracion = new javax.swing.JButton();
        botonPresAdelanto = new javax.swing.JButton();

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaEmpleados.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaEmpleados);

        botonModificar.setText("Modificar trabajador");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar trabajador");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonAgregar.setText("Agregar trabajador");
        botonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAgregarActionPerformed(evt);
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

        botonVistaRemuneracion.setText("Ver remuneración");
        botonVistaRemuneracion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonVistaRemuneracionActionPerformed(evt);
            }
        });

        botonPresAdelanto.setText("Préstamo/Adelanto");
        botonPresAdelanto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonPresAdelantoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonPresAdelanto)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonVistaRemuneracion)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonAgregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonModificar)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonAgregar, botonEliminar, botonModificar, botonVistaRemuneracion});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(botonActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 410, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonModificar)
                    .addComponent(botonEliminar)
                    .addComponent(botonAgregar)
                    .addComponent(botonVistaRemuneracion)
                    .addComponent(botonPresAdelanto))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAgregarActionPerformed
        controladores.controladorEmpleados miControlador = new controladores.controladorEmpleados();
        miControlador.irVistaIngresarEmpleados();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonAgregarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        controladores.controladorEmpleados miControlador = new controladores.controladorEmpleados();
        String rut, nombres;
        boolean selected = tablaEmpleados.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            nombres = getNombresFila(row);
            rut = getRutFila(row);
            int dialogResult = JOptionPane.showOptionDialog(null, "Esta seguro que desea eliminar al empleado: \nRut: "
            + rut + "\nNombre: " + nombres, "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
            if(dialogResult == JOptionPane.YES_OPTION)  miControlador.eliminarEmpleados(rut);
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado para ser eliminado");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        controladores.controladorEmpleados miControlador = new controladores.controladorEmpleados();
        boolean selected = tablaEmpleados.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String rut = getRutFila(row);
            String nombres = getNombresFila(row);
            String[] rut_dv = rut.split("-");
            try {
                miControlador.irVistaModificarEmpleados(rut_dv[0], nombres);
            } catch (ParseException ex) {
                Logger.getLogger(vistaEmpleadosP.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTabbedPane tabs = (JTabbedPane)this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado para ser modificado");
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void textoFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyReleased
        String query = textoFiltro.getText();
        filtrar(query);
    }//GEN-LAST:event_textoFiltroKeyReleased

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        controladores.controladorEmpleados miControlador = new controladores.controladorEmpleados();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void botonVistaRemuneracionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonVistaRemuneracionActionPerformed
        controladores.controladorEmpleados miControlador = new controladores.controladorEmpleados();
        boolean selected = tablaEmpleados.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String rut = getRutFila(row);
            String[] rut_dv = rut.split("-");
            miControlador.irVistaRemuneraciones(rut_dv[0]);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado para ver su remuneración");
        }
    }//GEN-LAST:event_botonVistaRemuneracionActionPerformed

    private void botonPresAdelantoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonPresAdelantoActionPerformed
        controladores.controladorEmpleados miControlador = new controladores.controladorEmpleados();
        boolean selected = tablaEmpleados.getSelectedRowCount() > 0;
        if(selected){
            int row = getFilaSeleccionada();
            String rut = getRutFila(row);
            String[] rut_dv = rut.split("-");
            miControlador.irVistaPresAdelanto(rut_dv[0]);
        }else{
            JOptionPane.showMessageDialog(null, "Debe seleccionar un empleado para generar un préstamo o anticipo");
        }
    }//GEN-LAST:event_botonPresAdelantoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAgregar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JButton botonPresAdelanto;
    private javax.swing.JButton botonVistaRemuneracion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables

    public int getFilaSeleccionada() {
        return tablaEmpleados.getSelectedRow();
    }

    public String getRutFila(int row){
        return tablaEmpleados.getValueAt(row, 0).toString();
    }
    
    public String getNombresFila(int row){
        return tablaEmpleados.getValueAt(row,1).toString();
    }
    
    public void filtrar(String query){
        TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(datos);
        tablaEmpleados.setRowSorter(sorter);
        sorter.setRowFilter(RowFilter.regexFilter("(?i)"+query));
    }
}
