/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas2;

import controladores2.controladorGestionPago;
import controladores2.controladorModificarPago;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author diego
 */
public class vistaModificarPago extends javax.swing.JDialog {

    /**
     * Creates new form vistaModificarPago
     */
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    int saldo;
    int montoAnt;
    String id;
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    
    public vistaModificarPago(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        dfs.setCurrencySymbol("$");
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

        jLabel1 = new javax.swing.JLabel();
        comboOpcionPago = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        textoFecha = new org.jdesktop.swingx.JXDatePicker();
        jLabel4 = new javax.swing.JLabel();
        comboMedio = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoBanco = new javax.swing.JTextField();
        textoNumCuenta = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        textoSaldo = new javax.swing.JFormattedTextField();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        textoMonto = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar pago");

        jLabel1.setText("Opción de pago");

        comboOpcionPago.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pago total", "Abono", "Pago de cuota" }));
        comboOpcionPago.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboOpcionPagoActionPerformed(evt);
            }
        });

        jLabel2.setText("Monto");

        jLabel3.setText("Fecha de pago");

        jLabel4.setText("Medio de pago");

        comboMedio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Efectivo", "Transferencia", "Cheque", "Depósito" }));
        comboMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMedioActionPerformed(evt);
            }
        });

        jLabel5.setText("Banco");

        jLabel6.setText("N° de cuenta/cheque");

        jLabel7.setText("Saldo");

        textoSaldo.setEditable(false);

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        textoMonto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoMontoFocusLost(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textoSaldo)
                            .addComponent(textoBanco)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(textoFecha, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(comboOpcionPago, 0, 225, Short.MAX_VALUE))
                                .addComponent(jLabel3)
                                .addComponent(jLabel5)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMedio, 0, 245, Short.MAX_VALUE)
                            .addComponent(textoNumCuenta)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel6)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel2))
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(textoMonto)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCancelar)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboOpcionPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoMonto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNumCuenta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboOpcionPagoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboOpcionPagoActionPerformed
        int op = getComboOpcionPago();
        if(op == 0){
            setPagoTotal();
        }else if(op == 1){
            setAbono();
        }
    }//GEN-LAST:event_comboOpcionPagoActionPerformed

    private void comboMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMedioActionPerformed
        if(comboMedio.getSelectedIndex() == 0){
            hideMedioPago();
        }else{
            showMedioPago();
        }
    }//GEN-LAST:event_comboMedioActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        controladorModificarPago miControladorMP = new controladorModificarPago();
        if (miControladorMP.irVistaCobranzasP()) {
            JOptionPane.showMessageDialog(this, "Pago modificado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            setVisible(false);
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void textoMontoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoMontoFocusLost
        setTextoMonto(getTextoMonto());
    }//GEN-LAST:event_textoMontoFocusLost

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
            java.util.logging.Logger.getLogger(vistaModificarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaModificarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaModificarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaModificarPago.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JComboBox<String> comboMedio;
    private javax.swing.JComboBox<String> comboOpcionPago;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JTextField textoBanco;
    private org.jdesktop.swingx.JXDatePicker textoFecha;
    private javax.swing.JTextField textoMonto;
    private javax.swing.JTextField textoNumCuenta;
    private javax.swing.JFormattedTextField textoSaldo;
    // End of variables declaration//GEN-END:variables

    
    public String getId(){
        return id;
    }
    
    public void setId(String id){
        this.id = id;
    }
    
    public void setComboOpcionPago(String opcionPago){
        comboOpcionPago.setSelectedItem(opcionPago);
    }
    
    public void setTextoFechaPago(String fechaPago) throws ParseException{
        textoFecha.setDate(formatDate.parse(fechaPago));
    }
    
    public void setComboMedioPago(String medioPago){
        comboMedio.setSelectedItem(medioPago);
    }
    
    public void setTextoBanco(String banco){
        textoBanco.setText(banco);
    }
    
    public void setTextoNumeroCuentaCheque(String num){
        textoNumCuenta.setText(num);
    }

    public String getComboMedio() {
        return comboMedio.getSelectedItem().toString();
    }

    public int getComboOpcionPago() {
        return comboOpcionPago.getSelectedIndex();
        //return comboOpcionPago.getSelectedItem().toString();
    }
    
    public String getTextoOpcionPago(){
        return comboOpcionPago.getSelectedItem().toString();
    }

    public String getTextoBanco() {
        return textoBanco.getText();
    }
    
    public String getTextoNum(){
        return textoNumCuenta.getText();
    }

    public String getTextoMonto() {
        String monto = textoMonto.getText().replace("$", "");
        monto = monto.replace(".", "");
        return monto;
        
    }
    
    public void setTextoMonto(String monto){
        try{
            Object value = Integer.parseInt(monto);
            if (value instanceof Number) {
                if(((Number) value).intValue() > saldo) value = saldo;
                textoMonto.setHorizontalAlignment(JLabel.RIGHT);
                textoMonto.setText(FORMAT.format(value));
                textoSaldo.setText(FORMAT.format(saldo - (int)value));
            } else {
                textoMonto.setHorizontalAlignment(JLabel.RIGHT);
                textoMonto.setText("");
                textoSaldo.setText(FORMAT.format(saldo));
            }
        }catch(NumberFormatException e){
            textoMonto.setHorizontalAlignment(JLabel.RIGHT);
            textoMonto.setText("");
            textoMonto.setText(FORMAT.format(0));
            textoSaldo.setText(FORMAT.format(saldo));
        }
    }

    public String getTextoSaldo() {
        return textoSaldo.getValue().toString();
    }
    
    public void setPagoTotal(){
        textoMonto.setEnabled(false);
        textoMonto.setText(String.valueOf(saldo));
        try{
            Object value = Integer.parseInt(textoMonto.getText());
            if (value instanceof Number) {
                textoMonto.setHorizontalAlignment(JLabel.RIGHT);
                textoMonto.setText(FORMAT.format(value));
                textoSaldo.setText(FORMAT.format(saldo - (int)value));
            } else {
                textoMonto.setText("");
                textoSaldo.setText(FORMAT.format(saldo));
            }
        }catch(NumberFormatException e){
            textoMonto.setText("");
        }
    }
    
    public void setAbono(){
        textoMonto.setEnabled(true);
    }
    
    public void hideMedioPago(){
        textoBanco.setEnabled(false);
        textoNumCuenta.setEnabled(false);
    }
    
    public void showMedioPago(){
        textoBanco.setEnabled(true);
        textoNumCuenta.setEnabled(true);
    }
    
    public String getFecha(){
        Date fecha = textoFecha.getDate();
        if(fecha == null) return "";
        String dateString = formatDate.format(textoFecha.getDate());
        return dateString;
    }
    
    public void setSaldo(int saldo){
        this.saldo = saldo;
    }
    
    public void setMontoAnt(int montoAnt){
        this.montoAnt = montoAnt;
    }
    
    public int getMontAnt(){
        return this.montoAnt;
    }
}