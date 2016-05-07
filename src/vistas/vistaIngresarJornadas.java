/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.SpinnerDateModel;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author diego
 */
public class vistaIngresarJornadas extends javax.swing.JDialog {

    /**
     * Creates new form vistaIngresarJornadas
     */
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatClock = new SimpleDateFormat("HH:mm");
    DateFormat formatDia = new SimpleDateFormat("EEE");
    
    
    public vistaIngresarJornadas(java.awt.Frame parent, boolean modal, Object[] clientes,
            Object[] gruas, Object[] empleados) {
        super(parent, modal);
        initComponents();
        textoFechaSalida.setDate(new Date());
        controladores.controladorIngresarJornadas miControlador = new controladores.controladorIngresarJornadas();
        TextAutoCompleter listaClientes = new TextAutoCompleter(textoCliente);
        TextAutoCompleter listaGruas = new TextAutoCompleter(textoGrua);
        TextAutoCompleter listaEmpleados = new TextAutoCompleter(textoOperador);
        for(int i = 0; i < clientes.length; i++){
            listaClientes.addItem(clientes[i].toString());
        }
        for(int i = 0; i < gruas.length; i++){
            listaGruas.addItem(gruas[i].toString());
        }
        for(int i = 0; i < empleados.length; i++){
            listaEmpleados.addItem(empleados[i].toString());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelFechaSalida = new javax.swing.JLabel();
        textoFechaSalida = new org.jdesktop.swingx.JXDatePicker();
        labelHoraSalida = new javax.swing.JLabel();
        textoHoraSalida = new javax.swing.JSpinner(new SpinnerDateModel());
        labelGrua = new javax.swing.JLabel();
        labelCliente = new javax.swing.JLabel();
        labelOperador = new javax.swing.JLabel();
        labelFechaRegreso = new javax.swing.JLabel();
        textoFechaRegreso = new org.jdesktop.swingx.JXDatePicker();
        labelHoraRegreso = new javax.swing.JLabel();
        textoHoraRegreso = new javax.swing.JSpinner(new SpinnerDateModel());
        labelObs = new javax.swing.JLabel();
        scrollpan = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        textoCliente = new javax.swing.JTextField();
        textoGrua = new javax.swing.JTextField();
        textoOperador = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar Jornada de Trabajo");
        setResizable(false);

        labelFechaSalida.setText("Fecha de salida");

        labelHoraSalida.setText("Hora de salida");

        JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(textoHoraSalida, "HH:mm");
        textoHoraSalida.setEditor(timeEditor);
        textoHoraSalida.setValue(new Date());

        labelGrua.setText("Grúa");

        labelCliente.setText("Cliente");

        labelOperador.setText("Operador");

        labelFechaRegreso.setText("Fecha de regreso");

        labelHoraRegreso.setText("Hora de regreso");

        JSpinner.DateEditor timeEditor2 = new JSpinner.DateEditor(textoHoraRegreso, "HH:mm");
        textoHoraRegreso.setEditor(timeEditor2);
        textoHoraRegreso.setValue(new Date());

        labelObs.setText("Observaciones");

        textoObs.setColumns(20);
        textoObs.setRows(5);
        scrollpan.setViewportView(textoObs);

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

        textoCliente.setNextFocusableComponent(textoOperador);

        textoGrua.setNextFocusableComponent(textoCliente);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(321, 321, 321)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoHoraRegreso)
                            .addComponent(textoFechaRegreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrollpan, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelObs)
                                    .addComponent(labelFechaRegreso)
                                    .addComponent(labelHoraRegreso))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCancelar)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(labelHoraSalida)
                        .addComponent(labelGrua)
                        .addComponent(labelCliente)
                        .addComponent(labelOperador)
                        .addComponent(labelFechaSalida)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textoGrua)
                            .addComponent(textoHoraSalida)
                            .addComponent(textoFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoCliente)
                            .addComponent(textoOperador, javax.swing.GroupLayout.PREFERRED_SIZE, 295, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(313, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelFechaRegreso)
                .addGap(5, 5, 5)
                .addComponent(textoFechaRegreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelHoraRegreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoHoraRegreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollpan, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(labelFechaSalida)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textoFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(labelHoraSalida)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textoHoraSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(labelGrua)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textoGrua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(labelCliente)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textoCliente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(labelOperador)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(textoOperador, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(65, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        JFrame tabs = (JFrame)this.getParent();
        controladores.controladorIngresarJornadas miControladorIJ = new controladores.controladorIngresarJornadas();
        String respuesta = miControladorIJ.camposVacios();
        boolean esVacio = respuesta.length() == 0;
        if (!esVacio) {
            JOptionPane.showMessageDialog(this, respuesta, "Debe rellenar los siguientes campos", JOptionPane.INFORMATION_MESSAGE);
        } else {
                if (miControladorIJ.irVistaJornadasP()) {
                    setVisible(false);
                }else{
                    JOptionPane.showMessageDialog(this, "Se generó un error al ingresar la jornada\n"
                            + "Por favor compruebe que el cliente, grúa y empleados sean correctos", "Error" , JOptionPane.INFORMATION_MESSAGE);
                }
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

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
            java.util.logging.Logger.getLogger(vistaIngresarJornadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarJornadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarJornadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarJornadas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelFechaRegreso;
    private javax.swing.JLabel labelFechaSalida;
    private javax.swing.JLabel labelGrua;
    private javax.swing.JLabel labelHoraRegreso;
    private javax.swing.JLabel labelHoraSalida;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelOperador;
    private javax.swing.JScrollPane scrollpan;
    private javax.swing.JTextField textoCliente;
    private org.jdesktop.swingx.JXDatePicker textoFechaRegreso;
    private org.jdesktop.swingx.JXDatePicker textoFechaSalida;
    private javax.swing.JTextField textoGrua;
    private javax.swing.JSpinner textoHoraRegreso;
    private javax.swing.JSpinner textoHoraSalida;
    private javax.swing.JTextArea textoObs;
    private javax.swing.JTextField textoOperador;
    // End of variables declaration//GEN-END:variables

    public String getTextoFechaRegreso() {
        Date fecha = textoFechaRegreso.getDate();
        if(fecha == null) return "";
        String dateString = formatDate.format(textoFechaRegreso.getDate());
        return dateString;
    }
    
    public String getDiaSalida(){
        Date fecha = textoFechaSalida.getDate();
        if(fecha == null) return "";
        String dia = formatDia.format(textoFechaSalida.getDate());
        return dia;
    }
    
    public String getDiaRegreso(){
        Date fecha = textoFechaRegreso.getDate();
        if(fecha == null) return "";
        String dia = formatDia.format(textoFechaRegreso.getDate());
        return dia;
    }

    public String getTextoFechaSalida() {
        Date fecha = textoFechaSalida.getDate();
        if(fecha == null) return "";
        String dateString = formatDate.format(textoFechaSalida.getDate());
        return dateString;    
    }

    public String getTextoHoraRegreso() {
        String hora = formatClock.format(textoHoraRegreso.getValue());
        if(hora == null) return "";
        return hora;
    }

    public String getTextoHoraSalida() {
        String hora = formatClock.format(textoHoraSalida.getValue());
        if(hora == null) return "";
        return hora;
    }

    public String getTextoObs() {
        return textoObs.getText();
    }

    public String getTextoCliente() {
        return textoCliente.getText();
    }

    public String getTextoGrua() {
        return textoGrua.getText();
    }

    public String getTextoOperador() {
        return textoOperador.getText();
    }
}
