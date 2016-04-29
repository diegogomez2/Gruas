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
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

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
        //final int rows = 5;
        String[] columNames = {"Código OT", "Razon", "Giro", "Dirección", "Región", "Comuna", "Fecha",
             "Neto", "IVA", "Total"};
        DefaultTableModel datos = new DefaultTableModel(data, columNames){
            @Override
            public boolean isCellEditable(int row, int column){
                return false;
            }
        };
        tablaFacturas.setModel(datos);
        if(tablaFacturas.getRowCount() > 0) tablaFacturas.setRowSelectionInterval(0, 0);
        tablaFacturas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tablaFacturas.addMouseListener(new MouseAdapter(){
            public void mousePressed(MouseEvent evt){
                JTable table = (JTable)evt.getSource();
                Point p = evt.getPoint();
                int row = table.rowAtPoint(p);
                if(evt.getClickCount() == 2){
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
        botonFacturar = new javax.swing.JButton();

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
        jScrollPane1.setViewportView(tablaFacturas);

        botonFacturar.setText("Generar XML");
        botonFacturar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonFacturarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonFacturar)
                .addGap(18, 18, 18))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 438, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonFacturar)
                .addGap(0, 9, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void botonFacturarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonFacturarActionPerformed
        controladores.controladorCrearFactura miControlador = new controladores.controladorCrearFactura();
        int filas = tablaFacturas.getRowCount();
        String[] idOts = new String[filas];
        int neto = 0, iva = 0, total = 0;
        for(int i = 0; i < filas; i++){
            idOts[i] = getIdFact(i);
            neto += getNetoFact(i);
            iva += getIvaFact(i);
            total += getTotalFact(i);
        }
        String respuesta = miControlador.crearDocXML(idOts, Integer.toString(neto), Integer.toString(iva),
                Integer.toString(total));
        if(respuesta.compareTo("correcto") == 0){
            controladores.controladorFacturas micontroladorFacturas = new controladores.controladorFacturas();
            if((micontroladorFacturas.archivarFacturas(idOts).compareTo("correcto") == 0)){
                
            }
        }
    }//GEN-LAST:event_botonFacturarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonFacturar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tablaFacturas;
    // End of variables declaration//GEN-END:variables
    
    public String getIdFact(int row){
        return tablaFacturas.getValueAt(row, 0).toString();
    }
    
    public int getNetoFact(int row){
        return Integer.parseInt(tablaFacturas.getValueAt(row, 7).toString());
    }
    
    public int getIvaFact(int row){
        return Integer.parseInt(tablaFacturas.getValueAt(row, 8).toString());
    }
    
    public int getTotalFact(int row){
        return Integer.parseInt(tablaFacturas.getValueAt(row, 9).toString());
    }
}


