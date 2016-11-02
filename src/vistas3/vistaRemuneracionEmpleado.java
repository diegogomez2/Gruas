/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas3;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import javax.swing.JLabel;

/**
 *
 * @author diego
 */
public class vistaRemuneracionEmpleado extends javax.swing.JDialog {

    /**
     * Creates new form vistaVerRemuneracion
     */
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    
    public vistaRemuneracionEmpleado(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dfs.setCurrencySymbol("$ ");
        dfs.setGroupingSeparator('.');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) FORMAT).setDecimalFormatSymbols(dfs);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        textoTotalImponible = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        textoAFP = new javax.swing.JTextField();
        textoTotalTrib = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        textoOtrosBonos = new javax.swing.JTextField();
        textoSueldoBase = new javax.swing.JTextField();
        textoSalud = new javax.swing.JTextField();
        textoRut = new javax.swing.JTextField();
        textoColacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textoBonoAntiguedad = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        textoAnticipo = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        textoCesantia = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        textoDescSalud = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        textoImpRenta = new javax.swing.JTextField();
        textoGratLegal = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        textoDescAFP = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoBono300 = new javax.swing.JTextField();
        textoNombre = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        textoTransporte = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        textoBonoColacion = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        textoSueldoLiquido = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle remuneración");

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jPanel1.setEnabled(false);

        jLabel1.setText("Rut");

        jLabel15.setText("Total tributable");

        textoTotalImponible.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoTotalImponible.setEnabled(false);

        jLabel7.setText("Otros bonos");

        jLabel14.setText("Total imponible");

        textoAFP.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoAFP.setEnabled(false);

        textoTotalTrib.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoTotalTrib.setEnabled(false);

        jLabel10.setText("Colación");

        textoOtrosBonos.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoOtrosBonos.setEnabled(false);

        textoSueldoBase.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoSueldoBase.setEnabled(false);

        textoSalud.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoSalud.setEnabled(false);

        textoRut.setForeground(new java.awt.Color(255, 51, 51));
        textoRut.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoRut.setEnabled(false);

        textoColacion.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoColacion.setEnabled(false);

        jLabel4.setText("Bono años trabajados");

        textoBonoAntiguedad.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoBonoAntiguedad.setEnabled(false);

        jLabel12.setText("Salud");

        jLabel3.setText("Sueldo base");

        jLabel8.setText("AFP");

        jLabel18.setText("Anticipo");

        textoAnticipo.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoAnticipo.setEnabled(false);

        jLabel19.setText("Cesantía");

        textoCesantia.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoCesantia.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel12)
                    .addComponent(jLabel10)
                    .addComponent(jLabel4)
                    .addComponent(jLabel15)
                    .addComponent(jLabel1)
                    .addComponent(jLabel3)
                    .addComponent(jLabel7)
                    .addComponent(jLabel14)
                    .addComponent(jLabel18)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(textoRut, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(textoSueldoBase, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(textoBonoAntiguedad, javax.swing.GroupLayout.DEFAULT_SIZE, 184, Short.MAX_VALUE)
                    .addComponent(textoOtrosBonos, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(textoTotalImponible, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(textoAFP)
                    .addComponent(textoSalud, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                    .addComponent(textoTotalTrib, javax.swing.GroupLayout.DEFAULT_SIZE, 179, Short.MAX_VALUE)
                    .addComponent(textoColacion)
                    .addComponent(textoAnticipo)
                    .addComponent(textoCesantia))
                .addContainerGap())
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textoAFP, textoBonoAntiguedad, textoCesantia, textoColacion, textoOtrosBonos, textoRut, textoSalud, textoSueldoBase, textoTotalImponible, textoTotalTrib});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(textoRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(textoSueldoBase, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(textoBonoAntiguedad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(textoOtrosBonos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel14)
                    .addComponent(textoTotalImponible, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(textoAFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(textoSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel19)
                    .addComponent(textoCesantia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel15)
                    .addComponent(textoTotalTrib, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(textoColacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(textoAnticipo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        textoDescSalud.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoDescSalud.setEnabled(false);

        jLabel16.setText("Impuesto renta");

        jLabel5.setText("Bono 300");

        textoImpRenta.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoImpRenta.setEnabled(false);

        textoGratLegal.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoGratLegal.setEnabled(false);

        jLabel2.setText("Trabajador");

        jLabel11.setText("Transporte");

        textoDescAFP.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoDescAFP.setEnabled(false);

        jLabel13.setText("Descuento salud");

        jLabel6.setText("Gratificación legal");

        textoBono300.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoBono300.setEnabled(false);

        textoNombre.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoNombre.setEnabled(false);

        jLabel9.setText("Descuento afp");

        textoTransporte.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoTransporte.setEnabled(false);

        jLabel20.setText("Bono colación");

        textoBonoColacion.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoBonoColacion.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoGratLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoDescAFP, javax.swing.GroupLayout.PREFERRED_SIZE, 182, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(textoDescSalud, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoImpRenta, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(textoTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoBono300, javax.swing.GroupLayout.DEFAULT_SIZE, 182, Short.MAX_VALUE)
                            .addComponent(textoBonoColacion))))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {textoBono300, textoDescAFP, textoGratLegal, textoImpRenta, textoNombre, textoTransporte});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(textoNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(textoGratLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(textoBono300, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(textoBonoColacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(textoDescAFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(textoDescSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoImpRenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel16))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(textoTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jLabel17.setText("Sueldo líquido");

        textoSueldoLiquido.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoSueldoLiquido.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel17)
                        .addGap(56, 56, 56)
                        .addComponent(textoSueldoLiquido, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 43, 43))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(textoSueldoLiquido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(37, 37, 37))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(vistaRemuneracionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaRemuneracionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaRemuneracionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaRemuneracionEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vistaRemuneracionEmpleado dialog = new vistaRemuneracionEmpleado(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField textoAFP;
    private javax.swing.JTextField textoAnticipo;
    private javax.swing.JTextField textoBono300;
    private javax.swing.JTextField textoBonoAntiguedad;
    private javax.swing.JTextField textoBonoColacion;
    private javax.swing.JTextField textoCesantia;
    private javax.swing.JTextField textoColacion;
    private javax.swing.JTextField textoDescAFP;
    private javax.swing.JTextField textoDescSalud;
    private javax.swing.JTextField textoGratLegal;
    private javax.swing.JTextField textoImpRenta;
    private javax.swing.JTextField textoNombre;
    private javax.swing.JTextField textoOtrosBonos;
    private javax.swing.JTextField textoRut;
    private javax.swing.JTextField textoSalud;
    private javax.swing.JTextField textoSueldoBase;
    private javax.swing.JTextField textoSueldoLiquido;
    private javax.swing.JTextField textoTotalImponible;
    private javax.swing.JTextField textoTotalTrib;
    private javax.swing.JTextField textoTransporte;
    // End of variables declaration//GEN-END:variables

    public void setTextoAFP(String textoAFP) {
        this.textoAFP.setText(textoAFP);
    }

    public void setTextoBono300(String textoBono300) {
        this.textoBono300.setHorizontalAlignment(JLabel.RIGHT);
        this.textoBono300.setText(FORMAT.format(Integer.parseInt(textoBono300)));
    }

    public void setTextoBonoAntiguedad(String textoBonoAntiguedad) {
        this.textoBonoAntiguedad.setHorizontalAlignment(JLabel.RIGHT);
        this.textoBonoAntiguedad.setText(FORMAT.format(Integer.parseInt(textoBonoAntiguedad)));
    }

    public void setTextoColacion(String textoColacion) {
        this.textoColacion.setHorizontalAlignment(JLabel.RIGHT);
        this.textoColacion.setText(FORMAT.format(Integer.parseInt(textoColacion)));
    }

    public void setTextoDescAFP(String textoDescAFP) {
        this.textoDescAFP.setHorizontalAlignment(JLabel.RIGHT);
        this.textoDescAFP.setText(FORMAT.format(Integer.parseInt(textoDescAFP)));
    }

    public void setTextoDescSalud(String textoDescSalud) {
        this.textoDescSalud.setHorizontalAlignment(JLabel.RIGHT);
        this.textoDescSalud.setText(FORMAT.format(Integer.parseInt(textoDescSalud)));
    }

    public void setTextoGratLegal(String textoGratLegal) {
        this.textoGratLegal.setHorizontalAlignment(JLabel.RIGHT);
        this.textoGratLegal.setText(FORMAT.format(Integer.parseInt(textoGratLegal)));
    }

    public void setTextoNombre(String textoNombre) {
        this.textoNombre.setText(textoNombre);
    }

    public void setTextoOtrosBonos(String textoOtrosBonos) {
        this.textoOtrosBonos.setHorizontalAlignment(JLabel.RIGHT);
        this.textoOtrosBonos.setText(FORMAT.format(Integer.parseInt(textoOtrosBonos)));
    }

    public void setTextoRut(String textoRut) {
        this.textoRut.setText(textoRut);
    }

    public void setTextoSalud(String textoSalud) {
        this.textoSalud.setText(textoSalud);
    }

    public void setTextoTransporte(String textoTransporte) {
        this.textoTransporte.setHorizontalAlignment(JLabel.RIGHT);
        this.textoTransporte.setText(FORMAT.format(Integer.parseInt(textoTransporte)));
    }

    public void setTextoSueldoBase(String textoSueldoBase) {
        this.textoSueldoBase.setHorizontalAlignment(JLabel.RIGHT);
        this.textoSueldoBase.setText(FORMAT.format(Integer.parseInt(textoSueldoBase)));
    }
    
    public void setTextoTotalImponible(String textoTotalImponible){
        this.textoTotalImponible.setHorizontalAlignment(JLabel.RIGHT);
        this.textoTotalImponible.setText(FORMAT.format(Integer.parseInt(textoTotalImponible)));
    }
    
    public void setTextoTotalTributable(String textoTotalTrib){
        this.textoTotalTrib.setHorizontalAlignment(JLabel.RIGHT);
        this.textoTotalTrib.setText(FORMAT.format(Integer.parseInt(textoTotalTrib)));
    }
    
    public void setTextoSueldoLiquido(String textoSueldoLiquido){
        this.textoSueldoLiquido.setHorizontalAlignment(JLabel.RIGHT);
        this.textoSueldoLiquido.setText(FORMAT.format(Integer.parseInt(textoSueldoLiquido)));
    }
    
    public void setTextoCesantia(String textoCesantia){
        this.textoCesantia.setHorizontalAlignment(JLabel.RIGHT);
        this.textoCesantia.setText(FORMAT.format(Integer.parseInt(textoCesantia)));
    }
    
    public void setTextoBonoCol(String textoBonoCol){
        this.textoBonoColacion.setHorizontalAlignment(JLabel.RIGHT);
        this.textoBonoColacion.setText(FORMAT.format(Integer.parseInt(textoBonoCol)));
    }
}
