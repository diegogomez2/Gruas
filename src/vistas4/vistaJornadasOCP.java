/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas4;

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
import javax.swing.table.DefaultTableModel;
import vistas.vistaJornadasP;

/**
 *
 * @author diego
 */
public class vistaJornadasOCP extends javax.swing.JPanel {

    /**
     * Creates new form vistaOCP
     */
    DefaultTableModel datos;
    
    public vistaJornadasOCP(String tipo, Object[][] data) {
        initComponents();
        String[] columNames = {"Código", "Cliente", "Fecha de salida", "Observaciones", "N° de grúas", "N° de operadores", "N° de transpaletas"};
        datos = new DefaultTableModel(data, columNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
        tablaJornadasOC.setModel(datos);
        tablaJornadasOC.setAutoCreateRowSorter(true);
        if (tablaJornadasOC.getRowCount() > 0) {
            tablaJornadasOC.setRowSelectionInterval(0, 0);
        }
        tablaJornadasOC.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaJornadasOC.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent evt) {
                JTable table = (JTable) evt.getSource();
                Point p = evt.getPoint();
                int row = table.rowAtPoint(p);
                if (evt.getClickCount() == 2) {
                    controladores4.controladorJornadasOC miControlador = new controladores4.controladorJornadasOC();
                    try {
                        miControlador.irVistaDetalleJornadaOC(tablaJornadasOC.getValueAt(row, 0).toString());
                    } catch (ParseException ex) {
                        Logger.getLogger(vistaJornadasOCP.class.getName()).log(Level.SEVERE, null, ex);
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
        tablaJornadasOC = new javax.swing.JTable(){

        };
        botonModificar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        textoFiltro = new javax.swing.JTextField();
        botonActualizar = new javax.swing.JButton();
        botonEliminar = new javax.swing.JButton();
        botonIngresar = new javax.swing.JButton();
        botonAsignar = new javax.swing.JButton();

        tablaJornadasOC.getTableHeader().setReorderingAllowed(false);
        tablaJornadasOC.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        tablaJornadasOC.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaJornadasOC.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(tablaJornadasOC);

        botonModificar.setText("Modificar");
        botonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonModificarActionPerformed(evt);
            }
        });

        jLabel1.setText("Filtro");

        textoFiltro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                textoFiltroKeyReleased(evt);
            }
        });

        botonActualizar.setText("Actualizar");
        botonActualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarActionPerformed(evt);
            }
        });

        botonEliminar.setText("Eliminar");
        botonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonEliminarActionPerformed(evt);
            }
        });

        botonIngresar.setText("Ingresar Jornada OC");
        botonIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonIngresarActionPerformed(evt);
            }
        });

        botonAsignar.setText("Asignar OC");
        botonAsignar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAsignarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonAsignar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 404, Short.MAX_VALUE)
                        .addComponent(botonIngresar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonEliminar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonModificar))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(botonActualizar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addComponent(jScrollPane1)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonActualizar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 396, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonModificar)
                    .addComponent(botonEliminar)
                    .addComponent(botonIngresar)
                    .addComponent(botonAsignar))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonModificarActionPerformed
        controladores4.controladorJornadasOC miControlador = new controladores4.controladorJornadasOC();
        boolean selected = tablaJornadasOC.getSelectedRowCount() > 0;
        if (selected) {
            int row = getFilaSeleccionada();
            String id = getIdFila(row);
            try {
                miControlador.irVistaModificarJornadasOC(id);
            } catch (ParseException ex) {
                Logger.getLogger(vistaJornadasOCP.class.getName()).log(Level.SEVERE, null, ex);
            }
            JTabbedPane tabs = (JTabbedPane) this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        } else {
            JOptionPane.showMessageDialog(tablaJornadasOC, "Debe seleccionar una jornada para ser modificada");
        }
    }//GEN-LAST:event_botonModificarActionPerformed

    private void textoFiltroKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_textoFiltroKeyReleased

    }//GEN-LAST:event_textoFiltroKeyReleased

    private void botonActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarActionPerformed
        controladores4.controladorJornadasOC miControlador = new controladores4.controladorJornadasOC();
        JTabbedPane tabs = (JTabbedPane)this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonActualizarActionPerformed

    private void botonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonEliminarActionPerformed
        controladores4.controladorJornadasOC miControlador = new controladores4.controladorJornadasOC();
        String id;
        boolean selected = tablaJornadasOC.getSelectedRowCount() > 0;
        if (selected) {
            int row = getFilaSeleccionada();
            id = getIdFila(row);
            int dialogResult = JOptionPane.showOptionDialog(null, "Esta seguro que desea eliminar la jornada: \n "
                    + "Código: " + id, "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
            if (dialogResult == JOptionPane.YES_OPTION) {
                miControlador.eliminarJornadasOC(id);
            }
            JTabbedPane tabs = (JTabbedPane) this.getParent();
            miControlador.crearControladorPrincipal(tabs);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una jornada para ser eliminada");
        }
    }//GEN-LAST:event_botonEliminarActionPerformed

    private void botonIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonIngresarActionPerformed
        controladores4.controladorJornadasOC miControlador = new controladores4.controladorJornadasOC();
        try {
            miControlador.irVistaIngresarOC();
        } catch (ParseException ex) {
            Logger.getLogger(vistaJornadasOCP.class.getName()).log(Level.SEVERE, null, ex);
        }
        JTabbedPane tabs = (JTabbedPane) this.getParent();
        miControlador.crearControladorPrincipal(tabs);
    }//GEN-LAST:event_botonIngresarActionPerformed

    private void botonAsignarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAsignarActionPerformed
        String id;
        controladores4.controladorJornadasOC miControlador = new controladores4.controladorJornadasOC();
        controladores4.controladorOcs miControladorOc = new controladores4.controladorOcs();
        boolean selected = tablaJornadasOC.getSelectedRowCount() > 0;
        if (selected) {
            int row = getFilaSeleccionada();
            int tras = Integer.parseInt(getNumTraspaletas(row));
            id = getIdFila(row);
            if(tras > 0){
                try{
                    miControlador.irVistaIngresarTraspaletaOcs(id);  
                }catch(Exception ex){
                    Logger.getLogger(vistaJornadasP.class.getName()).log(Level.SEVERE, null, ex);
                }
                JTabbedPane tabs = (JTabbedPane) this.getParent();
                miControladorOc.crearControladorPrincipal(tabs);
                miControlador.crearControladorPrincipal(tabs);
            }else{
                int grua = Integer.parseInt(getNumGruas(row));
                int trab = Integer.parseInt(getNumEmpleados(row));
                if (trab > 0 && grua > 0) {
                    try {
                        miControlador.irVistaIngresarOcs(id);
                    } catch (ParseException ex) {
                        Logger.getLogger(vistaJornadasP.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    JTabbedPane tabs = (JTabbedPane) this.getParent();
                    miControladorOc.crearControladorPrincipal(tabs);
                    miControlador.crearControladorPrincipal(tabs);
                } else {
                    JOptionPane.showMessageDialog(null, "Debe llenar los datos faltantes para asignar una OC a esta jornada");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar una jornada para ser asignada");
        }
    }//GEN-LAST:event_botonAsignarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonActualizar;
    private javax.swing.JButton botonAsignar;
    private javax.swing.JButton botonEliminar;
    private javax.swing.JButton botonIngresar;
    private javax.swing.JButton botonModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaJornadasOC;
    private javax.swing.JTextField textoFiltro;
    // End of variables declaration//GEN-END:variables

    public int getFilaSeleccionada() {
        return tablaJornadasOC.getSelectedRow();
    }
    
    public String getIdFila(int row) {
        return tablaJornadasOC.getValueAt(row, 0).toString();
    }
    
    public String getNumGruas(int row){
        return tablaJornadasOC.getValueAt(row, 4).toString();                
    }
    
    public String getNumEmpleados(int row){
        return tablaJornadasOC.getValueAt(row, 5).toString();                
    }
    
    public String getNumTraspaletas(int row){
        return tablaJornadasOC.getValueAt(row, 6).toString();                
    }
}
