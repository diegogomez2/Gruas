/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.Color;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author diego
 */
public class vistaDetalleEmpleados extends javax.swing.JDialog {

    /**
     * Creates new form vistaDetalleEmpleados
     */
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    
    public vistaDetalleEmpleados(java.awt.Frame parent, boolean modal) {
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

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        labelCargo = new javax.swing.JLabel();
        comboCargo = new javax.swing.JComboBox();
        labelSueldo = new javax.swing.JLabel();
        textoSueldo = new javax.swing.JTextField();
        labelAFP = new javax.swing.JLabel();
        comboAFP = new javax.swing.JComboBox();
        labelSalud = new javax.swing.JLabel();
        comboSalud = new javax.swing.JComboBox();
        labelFechaIn = new javax.swing.JLabel();
        textoFechaIn = new org.jdesktop.swingx.JXDatePicker();
        labelIsapre = new javax.swing.JLabel();
        textoIsapre = new javax.swing.JTextField();
        labelValorPlan = new javax.swing.JLabel();
        textoValorPlan = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        textoColacion = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        textoTransporte = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        textoBonoAd = new javax.swing.JTextField();
        textoValorBonoAd = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        textoCaja = new javax.swing.JTextField();
        textoAF = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        labelDir = new javax.swing.JLabel();
        textoDir = new javax.swing.JTextField();
        labelRegion = new javax.swing.JLabel();
        comboRegion = new javax.swing.JComboBox();
        labelComuna = new javax.swing.JLabel();
        comboComuna = new javax.swing.JComboBox();
        botonOK = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        textoTelefono = new javax.swing.JTextField();
        textoRut = new javax.swing.JTextField();
        textoApPaterno = new javax.swing.JTextField();
        labelFechaNac = new javax.swing.JLabel();
        labelTelefono = new javax.swing.JLabel();
        labelApMaterno = new javax.swing.JLabel();
        textoApMaterno = new javax.swing.JTextField();
        labelNombres = new javax.swing.JLabel();
        labelCorreo = new javax.swing.JLabel();
        labelApPaterno = new javax.swing.JLabel();
        textoCorreo = new javax.swing.JTextField();
        textoFechaNac = new org.jdesktop.swingx.JXDatePicker();
        labelRut = new javax.swing.JLabel();
        textoNombres = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Detalle trabajador");
        setLocationByPlatform(true);
        setResizable(false);

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Datos sueldos"));
        jPanel2.setToolTipText("");

        labelCargo.setText("Cargo");

        comboCargo.setEditable(true);
        comboCargo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Jefe(a) de administración y finanzas", "Jefe de operaciones", "Gerente general", "Gerente de administración", "Supervisor", "Administrativo", "Mecánico", "Ayud. mecánico", "Jefe de Mantenciones", "Operador Senior", "Operador" }));
        comboCargo.setEnabled(false);

        labelSueldo.setText("Sueldo");

        textoSueldo.setEditable(false);
        textoSueldo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelAFP.setText("AFP");

        comboAFP.setEditable(true);
        comboAFP.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "CUPRUM", "HABITAT", "CAPITAL", "PLANVITAL", "PROVIDA", "MODELO" }));
        comboAFP.setEnabled(false);

        labelSalud.setText("Salud");

        comboSalud.setEditable(true);
        comboSalud.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Fonasa", "Isapre" }));
        comboSalud.setEnabled(false);

        labelFechaIn.setText("Fecha de ingreso");

        textoFechaIn.setEnabled(false);

        labelIsapre.setText("Isapre");

        textoIsapre.setEditable(false);

        labelValorPlan.setText("Valor plan (UF)");

        textoValorPlan.setEditable(false);

        jLabel3.setText("Colación");

        textoColacion.setEditable(false);

        jLabel4.setText("Transporte");

        textoTransporte.setEditable(false);

        jLabel1.setText("Bono adicional");

        jLabel2.setText("Valor bono");

        textoBonoAd.setEditable(false);

        textoValorBonoAd.setEditable(false);

        jLabel5.setText("Caja de compensación");

        jLabel6.setText("Asignación familiar");

        textoCaja.setEditable(false);

        textoAF.setEditable(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(textoCaja)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(textoBonoAd)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(labelCargo)
                                    .addComponent(labelAFP)
                                    .addComponent(labelFechaIn)
                                    .addComponent(jLabel3)
                                    .addComponent(textoFechaIn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboAFP, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(comboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(textoColacion))
                                .addComponent(jLabel1)))
                        .addComponent(jLabel5)))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textoSueldo, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(comboSalud, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelIsapre)
                                .addGap(0, 62, Short.MAX_VALUE))
                            .addComponent(textoIsapre))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(labelValorPlan)
                                .addGap(12, 12, 12))
                            .addComponent(textoValorPlan)))
                    .addComponent(textoTransporte)
                    .addComponent(textoValorBonoAd)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(jLabel2)
                            .addComponent(labelSalud)
                            .addComponent(jLabel4)
                            .addComponent(labelSueldo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(textoAF))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelCargo)
                    .addComponent(labelSueldo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboCargo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoSueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAFP)
                    .addComponent(labelSalud))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboAFP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboSalud, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaIn)
                    .addComponent(labelIsapre)
                    .addComponent(labelValorPlan))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFechaIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoIsapre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoValorPlan, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoColacion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTransporte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoBonoAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoValorBonoAd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoCaja, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoAF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Domicilio"));

        labelDir.setText("Dirección");

        textoDir.setEditable(false);
        textoDir.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelRegion.setText("Región");

        comboRegion.setEditable(true);
        comboRegion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboRegion.setEnabled(false);

        labelComuna.setText("Comuna");

        comboComuna.setEditable(true);
        comboComuna.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        comboComuna.setEnabled(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.CENTER, jPanel3Layout.createSequentialGroup()
                        .addComponent(labelDir)
                        .addGap(18, 18, 18)
                        .addComponent(textoDir))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelComuna)
                            .addComponent(labelRegion))
                        .addGap(22, 22, 22)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboRegion, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboComuna, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelDir)
                    .addComponent(textoDir, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRegion)
                    .addComponent(comboRegion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelComuna)
                    .addComponent(comboComuna, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        botonOK.setText("OK");
        botonOK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonOKActionPerformed(evt);
            }
        });

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Información"));

        textoTelefono.setEditable(false);
        textoTelefono.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        textoRut.setEditable(false);
        textoRut.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        textoApPaterno.setEditable(false);
        textoApPaterno.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelFechaNac.setText("Fecha de nacimiento");

        labelTelefono.setText("Teléfono");

        labelApMaterno.setText("Apellido Materno");

        textoApMaterno.setEditable(false);
        textoApMaterno.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        labelNombres.setText("Nombres");

        labelCorreo.setText("Correo");

        labelApPaterno.setText("Apellido Paterno");

        textoCorreo.setEditable(false);
        textoCorreo.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        textoFechaNac.setEnabled(false);

        labelRut.setText("Rut");

        textoNombres.setEditable(false);
        textoNombres.setDisabledTextColor(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(labelCorreo)
                    .addComponent(labelTelefono)
                    .addComponent(labelFechaNac)
                    .addComponent(labelApMaterno)
                    .addComponent(labelApPaterno)
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
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelRut)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelNombres)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoNombres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelApPaterno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoApPaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelApMaterno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoApMaterno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelFechaNac)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoFechaNac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addComponent(labelCorreo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonOK, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonOK)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void botonOKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonOKActionPerformed
        dispose();
    }//GEN-LAST:event_botonOKActionPerformed

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
            java.util.logging.Logger.getLogger(vistaDetalleEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaDetalleEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaDetalleEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaDetalleEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                vistaDetalleEmpleados dialog = new vistaDetalleEmpleados(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton botonOK;
    private javax.swing.JComboBox comboAFP;
    private javax.swing.JComboBox comboCargo;
    private javax.swing.JComboBox comboComuna;
    private javax.swing.JComboBox comboRegion;
    private javax.swing.JComboBox comboSalud;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JLabel labelAFP;
    private javax.swing.JLabel labelApMaterno;
    private javax.swing.JLabel labelApPaterno;
    private javax.swing.JLabel labelCargo;
    private javax.swing.JLabel labelComuna;
    private javax.swing.JLabel labelCorreo;
    private javax.swing.JLabel labelDir;
    private javax.swing.JLabel labelFechaIn;
    private javax.swing.JLabel labelFechaNac;
    private javax.swing.JLabel labelIsapre;
    private javax.swing.JLabel labelNombres;
    private javax.swing.JLabel labelRegion;
    private javax.swing.JLabel labelRut;
    private javax.swing.JLabel labelSalud;
    private javax.swing.JLabel labelSueldo;
    private javax.swing.JLabel labelTelefono;
    private javax.swing.JLabel labelValorPlan;
    private javax.swing.JTextField textoAF;
    private javax.swing.JTextField textoApMaterno;
    private javax.swing.JTextField textoApPaterno;
    private javax.swing.JTextField textoBonoAd;
    private javax.swing.JTextField textoCaja;
    private javax.swing.JTextField textoColacion;
    private javax.swing.JTextField textoCorreo;
    private javax.swing.JTextField textoDir;
    private org.jdesktop.swingx.JXDatePicker textoFechaIn;
    private org.jdesktop.swingx.JXDatePicker textoFechaNac;
    private javax.swing.JTextField textoIsapre;
    private javax.swing.JTextField textoNombres;
    private javax.swing.JTextField textoRut;
    private javax.swing.JTextField textoSueldo;
    private javax.swing.JTextField textoTelefono;
    private javax.swing.JTextField textoTransporte;
    private javax.swing.JTextField textoValorBonoAd;
    private javax.swing.JTextField textoValorPlan;
    // End of variables declaration//GEN-END:variables

    public void setComboAFP(String comboAFP) {
        this.comboAFP.setSelectedItem(comboAFP);
        ((JTextField)this.comboAFP.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
    }

    public void setComboCargo(String comboCargo) {
        this.comboCargo.setSelectedItem(comboCargo);
        ((JTextField)this.comboCargo.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
    }

    public void setComboComuna(String comboComuna) {
        this.comboComuna.setSelectedItem(comboComuna);
        ((JTextField)this.comboComuna.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
    }

    public void setComboRegion(String comboRegion) {
        this.comboRegion.setSelectedItem(comboRegion);
        ((JTextField)this.comboRegion.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
    }

    public void setComboSalud(String comboSalud) {
        this.comboSalud.setSelectedItem(comboSalud);
        ((JTextField)this.comboSalud.getEditor().getEditorComponent()).setDisabledTextColor(Color.black);
    }

    public void setTextoApMaterno(String textoApMaterno) {
        this.textoApMaterno.setText(textoApMaterno);
    }

    public void setTextoApPaterno(String textoApPaterno) {
        this.textoApPaterno.setText(textoApPaterno);
    }

    public void setTextoCorreo(String textoCorreo) {
        this.textoCorreo.setText(textoCorreo);
    }

    public void setTextoDir(String textoDir) {
        this.textoDir.setText(textoDir);
    }

    public void setTextoFechaIn(String textoFechaIn) throws ParseException {
        this.textoFechaIn.setDate(formatDate.parse(textoFechaIn));
        this.textoFechaIn.getEditor().setDisabledTextColor(Color.BLACK);
    }

    public void setTextoFechaNac(String textoFechaNac) throws ParseException {
        if(textoFechaNac.compareTo("") != 0){
            this.textoFechaNac.setDate(formatDate.parse(textoFechaNac));
        }
        this.textoFechaNac.getEditor().setDisabledTextColor(Color.BLACK);
    }

    public void setTextoNombres(String textoNombres) {
        this.textoNombres.setText(textoNombres);
    }

    public void setTextoRut(String textoRut) {
        this.textoRut.setText(textoRut);
    }

    public void setTextoSueldo(String textoSueldo) {
        this.textoSueldo.setText("$ "+String.format("%,d",Integer.parseInt(textoSueldo)));
    }

    public void setTextoTelefono(String textoTelefono) {
        this.textoTelefono.setText(textoTelefono);
    }
    
    public void setTextoColacion(String col){
//        textoColacion.setText(String.valueOf(col));
        textoColacion.setHorizontalAlignment(JLabel.RIGHT);
        textoColacion.setText(FORMAT.format(Integer.parseInt(col)));
    }
    
    public void setTextoTransporte(String trans){
//        textoTransporte.setText(String.valueOf(trans));
        textoTransporte.setHorizontalAlignment(JLabel.RIGHT);
        textoTransporte.setText(FORMAT.format(Integer.parseInt(trans)));
    }
    
    public void setTextoValorPlan(String val){
//        textoValorPlan.setText(String.valueOf(val));
        textoValorPlan.setHorizontalAlignment(JLabel.RIGHT);
        //textoValorPlan.setText(FORMAT.format(Integer.parseInt(val)));
        textoValorPlan.setText(val);
    }
    
    public void setTextoIsapre(String isa){
        textoIsapre.setText(isa);
    }
    
    public void hideIsapre(){
        textoIsapre.setVisible(false);
        textoValorPlan.setVisible(false);
        labelIsapre.setVisible(false);
        labelValorPlan.setVisible(false);
    }
    
    public void setTextoBonoAd(String nom){
        textoBonoAd.setText(nom);
    }
    
    public void setTextoValorBonoAd(String val){
        textoValorBonoAd.setHorizontalAlignment(JLabel.RIGHT);
        textoValorBonoAd.setText(FORMAT.format(Integer.parseInt(val)));
    }
    
    public void setTextoCaja(String val){
        textoCaja.setHorizontalAlignment(JLabel.RIGHT);
        textoCaja.setText(FORMAT.format(Integer.parseInt(val)));
    }
    
    public void setTextoAF(String val){
        textoAF.setHorizontalAlignment(JLabel.RIGHT);
        textoAF.setText(FORMAT.format(Integer.parseInt(val)));
    }
}
