/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.controladorIngresarEmpleados;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.plaf.basic.CalendarHeaderHandler;
import org.jdesktop.swingx.plaf.basic.SpinningCalendarHeaderHandler;

/**
 *
 * @author diego
 */
public class vistaIngresarEmpleados extends javax.swing.JDialog {

    DocumentFilter onlyNumbers = new DocumentFilter() {
            Pattern regEx = Pattern.compile("\\d+");

            @Override
            public void replace(DocumentFilter.FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
                Matcher matcher = regEx.matcher(text);
                if (!matcher.matches()) {
                    return;
                }
                super.replace(fb, offset, length, text, attrs);
            }
        };
    /**
     * Creates new form vistaIngresarEmpleados
     */
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    int sueldo_min;
    int sueldo_base;
    
    public vistaIngresarEmpleados(java.awt.Frame parent, boolean modal, final Object[][] regiones) {
        super(parent, modal);
        initComponents();
        dfs.setCurrencySymbol("$");
        dfs.setGroupingSeparator('.');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) FORMAT).setDecimalFormatSymbols(dfs);
        String[] listaRegiones = new String[regiones.length];
        for(int i = 0; i < regiones.length; i++){
            listaRegiones[i] = regiones[i][1].toString();
        }
        comboRegion.setModel(new DefaultComboBoxModel<String>(listaRegiones));
        cargarComunas(regiones);
        comboRegion.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                cargarComunas(regiones);
            }
        });
    }
    
    public void cargarComunas(Object[][] regiones){
        controladores.controladorIngresarEmpleados miControlador = new controladorIngresarEmpleados();
        int index = comboRegion.getSelectedIndex();
        int region = Integer.parseInt(regiones[index][0].toString());
        Object[] comunas = miControlador.cargarComunas(region);
        String[] listaComunas = new String[comunas.length];
        for(int i = 0; i < comunas.length; i++){
            listaComunas[i] = comunas[i].toString();
        }
        comboComuna.setModel(new DefaultComboBoxModel<String>(listaComunas));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jPanel1 = new javax.swing.JPanel();
        labelCargo = new javax.swing.JLabel();
        comboCargo = new javax.swing.JComboBox();
        labelSueldo = new javax.swing.JLabel();
        textoSueldo = new javax.swing.JTextField();
        labelAFP = new javax.swing.JLabel();
        comboAFP = new javax.swing.JComboBox();
        labelSalud = new javax.swing.JLabel();
        comboSalud = new javax.swing.JComboBox();
        labelFechaIngreso = new javax.swing.JLabel();
        textoFechaIn = new org.jdesktop.swingx.JXDatePicker();
        jButton1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        labelDir = new javax.swing.JLabel();
        textoDir = new javax.swing.JTextField();
        labelRegion = new javax.swing.JLabel();
        comboRegion = new javax.swing.JComboBox();
        labelComuna = new javax.swing.JLabel();
        comboComuna = new javax.swing.JComboBox();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        textoCorreo = new javax.swing.JTextField();
        textoTelefono = new javax.swing.JTextField();
        labelRut = new javax.swing.JLabel();
        labelCorreo = new javax.swing.JLabel();
        textoApMaterno = new javax.swing.JTextField();
        textoApPaterno = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        textoNombres = new javax.swing.JTextField();
        labelTelefono = new javax.swing.JLabel();
        labelNombres = new javax.swing.JLabel();
        textoFechaNac = new org.jdesktop.swingx.JXDatePicker();
        textoRut = new javax.swing.JTextField();
        labelFechaNac = new javax.swing.JLabel();
        labelApMaterno = new javax.swing.JLabel();

        jRadioButton1.setText("jRadioButton1");

        jRadioButton2.setText("jRadioButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar trabajador");
        setResizable(false);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos sueldos"));
        jPanel1.setToolTipText("");

        labelCargo.setText("Cargo");

        comboCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jefe(a) de administración y finanzas", "Jefe de operaciones", "Gerente general", "Gerente de administración", "Supervisor", "Administrativo", "Mecánico", "Ayud. mecánico", "Jefe de Mantenciones", "Operador Senior", "Operador" }));

        labelSueldo.setText("Sueldo");

        textoSueldo.setToolTipText("Sueldo contrato indefinido");
        textoSueldo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoSueldoFocusLost(evt);
            }
        });

        labelAFP.setText("AFP");

        comboAFP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CUPRUM", "HABITAT", "CAPITAL", "PLANVITAL", "PROVIDA", "MODELO" }));

        labelSalud.setText("Salud");

        comboSalud.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fonasa", "Isapre" }));

        labelFechaIngreso.setText("Fecha de ingreso");

        jButton1.setToolTipText("Sueldo contrato indefinido");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(textoFechaIn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelCargo, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboCargo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(labelAFP, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(comboAFP, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(labelFechaIngreso))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelSueldo)
                    .addComponent(labelSalud)
                    .addComponent(textoSueldo)
                    .addComponent(comboSalud, 0, 136, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCargo)
                    .addComponent(labelSueldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(comboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(textoSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAFP)
                    .addComponent(labelSalud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboAFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelFechaIngreso)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(textoFechaIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jButton1, textoSueldo});

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio"));

        labelDir.setText("Dirección");

        labelRegion.setText("Región");

        comboRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        labelComuna.setText("Comuna");

        comboComuna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDir)
                    .addComponent(labelRegion)
                    .addComponent(labelComuna))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoDir)
                    .addComponent(comboRegion, 0, 259, Short.MAX_VALUE)
                    .addComponent(comboComuna, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDir)
                    .addComponent(textoDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelRegion))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboComuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelComuna))
                .addGap(20, 20, 20))
        );

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

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Información"));
        jPanel3.setToolTipText("");

        labelRut.setText("Rut");

        labelCorreo.setText("Correo");

        jLabel1.setText("Apellido Paterno");

        labelTelefono.setText("Teléfono");

        labelNombres.setText("Nombres");

        labelFechaNac.setText("Fecha de nacimiento");

        labelApMaterno.setText("Apellido Materno");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCorreo)
                    .addComponent(labelTelefono)
                    .addComponent(labelFechaNac)
                    .addComponent(labelApMaterno)
                    .addComponent(jLabel1)
                    .addComponent(labelNombres)
                    .addComponent(labelRut)
                    .addComponent(textoRut)
                    .addComponent(textoNombres)
                    .addComponent(textoApPaterno)
                    .addComponent(textoApMaterno)
                    .addComponent(textoFechaNac, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoTelefono)
                    .addComponent(textoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 154, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelNombres)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelApMaterno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(labelFechaNac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonCancelar)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        controladores.controladorIngresarEmpleados miControladorIE = new controladores.controladorIngresarEmpleados();
        String respuesta = miControladorIE.camposVacios();
        boolean esVacio = respuesta.length() == 0;
        if(!esVacio){
            JOptionPane.showMessageDialog(this, respuesta, "Debe rellenar los siguientes campos", JOptionPane.INFORMATION_MESSAGE);
        }else{
            boolean rutValido = miControladorIE.verificarRut(getTextoRut());
            if(rutValido){
                if(miControladorIE.irVistaEmpleadosP()) setVisible(false);
            }
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void textoSueldoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoSueldoFocusLost
        try{
            Object value = Integer.parseInt(textoSueldo.getText());
            if (value instanceof Number) {
                if(((Number) value).intValue() < sueldo_min) value = sueldo_min;
                textoSueldo.setHorizontalAlignment(JLabel.RIGHT);
                textoSueldo.setText(FORMAT.format(value));
            } else {
                textoSueldo.setHorizontalAlignment(JLabel.RIGHT);
                textoSueldo.setText("");
                textoSueldo.setText(FORMAT.format(sueldo_min));
            }
        }catch(NumberFormatException e){
            textoSueldo.setHorizontalAlignment(JLabel.RIGHT);
            textoSueldo.setText("");
            textoSueldo.setText(FORMAT.format(sueldo_min));
        }
    }//GEN-LAST:event_textoSueldoFocusLost

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        textoSueldo.setText(FORMAT.format(sueldo_base));
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
            java.util.logging.Logger.getLogger(vistaIngresarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox comboAFP;
    private javax.swing.JComboBox comboCargo;
    private javax.swing.JComboBox comboComuna;
    private javax.swing.JComboBox comboRegion;
    private javax.swing.JComboBox comboSalud;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JLabel labelAFP;
    private javax.swing.JLabel labelApMaterno;
    private javax.swing.JLabel labelCargo;
    private javax.swing.JLabel labelComuna;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelDir;
    private javax.swing.JLabel labelFechaIngreso;
    private javax.swing.JLabel labelFechaNac;
    private javax.swing.JLabel labelNombres;
    private javax.swing.JLabel labelRegion;
    private javax.swing.JLabel labelRut;
    private javax.swing.JLabel labelSalud;
    private javax.swing.JLabel labelSueldo;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JTextField textoApMaterno;
    private javax.swing.JTextField textoApPaterno;
    private javax.swing.JTextField textoCorreo;
    private javax.swing.JTextField textoDir;
    private org.jdesktop.swingx.JXDatePicker textoFechaIn;
    private org.jdesktop.swingx.JXDatePicker textoFechaNac;
    private javax.swing.JTextField textoNombres;
    private javax.swing.JTextField textoRut;
    private javax.swing.JTextField textoSueldo;
    private javax.swing.JTextField textoTelefono;
    // End of variables declaration//GEN-END:variables

    public String getComboAFP() {
        return comboAFP.getSelectedItem().toString();
    }

    public String getComboCargo() {
        return comboCargo.getSelectedItem().toString().trim();
    }

    public String getComboComuna() {
        return comboComuna.getSelectedItem().toString();
    }

    public String getComboRegion() {
        return comboRegion.getSelectedItem().toString();
    }

    public String getComboSalud() {
        return comboSalud.getSelectedItem().toString();
    }

    public String getTextoApMaterno() {
        return textoApMaterno.getText().trim();
    }

    public String getTextoApPaterno() {
        return textoApPaterno.getText().trim();
    }

    public String getTextoCorreo() {
        return textoCorreo.getText();
    }

    public String getTextoDir() {
        return textoDir.getText();
    }

    public String  getTextoFechaIn() {
        Date fecha = textoFechaIn.getDate();
        if(fecha == null) return "";
        String dateString = formatDate.format(textoFechaIn.getDate());
        return dateString;
    }

    public String getTextoFechaNac() {
        Date fecha = textoFechaNac.getDate();
        if(fecha == null) return "";
        String dateString = formatDate.format(textoFechaNac.getDate());
        return dateString;
    }

    public String getTextoNombres() {
        String text = textoNombres.getText().trim();
        
        return text.replaceAll("\\s+", " ");
    }

    public String getTextoRut() {
        return textoRut.getText();
    }

    public String getTextoSueldo() {
        return textoSueldo.getText();
    }

    public String getTextoTelefono() {
        return textoTelefono.getText();
    }

    public void setSueldoMin(int sueldo){
        sueldo_min = sueldo;
    }

    public void setSueldoBase(int sueldo){
        sueldo_base = sueldo;
    }
    
    public int getSueldoMin(){
        return sueldo_min;
    }
    
    public int getSueldoBase(){
        return sueldo_base;
    }
}
