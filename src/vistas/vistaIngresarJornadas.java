/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import java.awt.EventQueue;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.JTextComponent;
import modelos.modeloClientes;

/**
 *
 * @author diego
 */
public class vistaIngresarJornadas extends javax.swing.JDialog {
    modelos.modeloClientes cliente = new modeloClientes();
    List<String> listClientes = new ArrayList<>();
    List<String> listGruas = new ArrayList<>();
    List<String> listEmpleados = new ArrayList<>();
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    SimpleDateFormat formatClock = new SimpleDateFormat("HH:mm");
    DateFormat formatDia = new SimpleDateFormat("EEE");
    JTextField textoClientes = new JTextField();
    JTextField textoOperadores = new JTextField();
    JTextField textoGruas = new JTextField();
    private boolean hide_flag = false;


    public vistaIngresarJornadas(java.awt.Frame parent, boolean modal, Object[] clientes,
            Object[] gruas, Object[] empleados) {
        super(parent, modal);
        initComponents();
        textoClientes = (JTextField)comboClientes.getEditor().getEditorComponent();
        textoGruas = (JTextField)comboGruas.getEditor().getEditorComponent();
        textoOperadores = (JTextField)comboOperadores.getEditor().getEditorComponent();
        textoFechaSalida.setDate(new Date());
        
        final JTextComponent tcc = (JTextComponent) comboClientes.getEditor().getEditorComponent();
        tcc.getDocument().addDocumentListener(new DocumentListener() { 
            @Override
            public void insertUpdate(DocumentEvent de) {
                String obs = cliente.obtenerObsPorRazon(comboClientes.getSelectedItem().toString());
                setTextoObsCliente(obs);            
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            }
        });
        
        textoFechaSalida.getEditor().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
            }

            @Override
            public void focusLost(FocusEvent fe) {
                 if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
                    modelos.modeloGruas grua = new modelos.modeloGruas();
                    Object[] gruas = grua.obtenerDescGruasDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
                    autosuggestGruas(gruas); 
                    modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
                    Object[] empleados = empleado.obtenerNomEmpDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
                    autosuggestOperadores(empleados);
                 }
            }
        });
        
        textoFechaRegreso.getEditor().addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent fe) {
                
            }

            @Override
            public void focusLost(FocusEvent fe) {
                 if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
                    modelos.modeloGruas grua = new modelos.modeloGruas();
                    Object[] gruas = grua.obtenerDescGruasDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
                    autosuggestGruas(gruas); 
                    modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
                    Object[] empleados = empleado.obtenerNomEmpDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
                    autosuggestOperadores(empleados);
                 }
            }
        });
        
        textoOperadores.addFocusListener(new FocusListener() {
            @Override 
            public void focusGained(FocusEvent fe){
                if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
                    modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
                    Object[] empleados = empleado.obtenerNomEmpDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
                    autosuggestOperadores(empleados);
                 }
            }
            
            @Override
            public void focusLost(FocusEvent fe){}
        });
        
        textoGruas.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
                if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
                    modelos.modeloGruas grua = new modelos.modeloGruas();
                    Object[] gruas = grua.obtenerDescGruasDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
                    autosuggestGruas(gruas);
                }
            }
            
            @Override 
            public void focusLost(FocusEvent fl){}
        });

        textoGruas.addKeyListener(new KeyAdapter(){
            public void keyTyped(KeyEvent e){
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = textoGruas.getText();
                        if(text.length() == 0){
                            comboGruas.hidePopup();
                            setModel(new DefaultComboBoxModel(listGruas.toArray()), "", comboGruas, textoGruas);
                        }else{
                            DefaultComboBoxModel m = getSuggestedModel(listGruas, text);
                            if(m.getSize() == 0){
                                comboGruas.hidePopup();
                            }else{
                                setModel(m, text, comboGruas, textoGruas);
                                comboGruas.showPopup();
                            }
                        }
                    }
                });
            }

            @Override
            public void keyPressed(KeyEvent e){
                String text = textoGruas.getText();
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_ENTER){
                    for (int i = 0; i < listGruas.size(); i++) {
                        String str = (String)listGruas.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoGruas.setText(str);
                            comboGruas.hidePopup();
                            return;
                        }
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
                    hide_flag = true;
                }else if(code == KeyEvent.VK_RIGHT){
                    for (int i = 0; i < listGruas.size(); i++) {
                        String str = (String)listGruas.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoGruas.setText(str);
                            return;
                        }
                    }
                }
            }
        });
        comboGruas.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent ie) {
                            if(ie.getStateChange() == ItemEvent.SELECTED){
                                comboGruas.getSelectedIndex();
                            }
                        }
                    });
        
        autosuggestClientes(clientes);
        autosuggestGruas(gruas);
        autosuggestOperadores(empleados);
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
        labelObsCliente = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textoObsCliente = new javax.swing.JTextArea();
        comboGruas = new javax.swing.JComboBox();
        comboClientes = new javax.swing.JComboBox();
        comboOperadores = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar jornada de trabajo");
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

        labelObsCliente.setText("Observaciones cliente");

        textoObsCliente.setColumns(20);
        textoObsCliente.setRows(5);
        jScrollPane1.setViewportView(textoObsCliente);

        comboClientes.setMaximumSize(new java.awt.Dimension(28, 20));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botonAceptar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonCancelar))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(comboGruas, javax.swing.GroupLayout.Alignment.LEADING, 0, 268, Short.MAX_VALUE)
                            .addComponent(labelObsCliente, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                            .addComponent(comboClientes, 0, 268, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(textoHoraRegreso, javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(textoFechaRegreso, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 311, Short.MAX_VALUE)
                                .addComponent(comboOperadores, javax.swing.GroupLayout.Alignment.TRAILING, 0, 28, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(scrollpan, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(labelFechaRegreso)
                                    .addComponent(labelHoraRegreso)
                                    .addComponent(labelOperador)
                                    .addComponent(labelObs))
                                .addGap(8, 8, 8)))))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(labelHoraSalida)
                        .addComponent(labelGrua)
                        .addComponent(labelCliente)
                        .addComponent(labelFechaSalida)
                        .addComponent(textoFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                        .addComponent(textoHoraSalida, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE))
                    .addContainerGap(348, Short.MAX_VALUE)))
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
                .addGap(12, 12, 12)
                .addComponent(labelOperador)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboGruas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboOperadores, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(labelObsCliente)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(scrollpan))
                .addGap(18, 18, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addContainerGap())
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
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
                    .addGap(37, 37, 37)
                    .addComponent(labelCliente)
                    .addContainerGap(232, Short.MAX_VALUE)))
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
        JFrame tabs = (JFrame) this.getParent();
        controladores.controladorIngresarJornadas miControladorIJ = new controladores.controladorIngresarJornadas();
        String respuesta = miControladorIJ.camposVacios();
        boolean esVacio = respuesta.length() == 0;
        if (!esVacio) {
            JOptionPane.showMessageDialog(this, respuesta, "Debe rellenar los siguientes campos", JOptionPane.INFORMATION_MESSAGE);
        } else {
            try {
                if (miControladorIJ.irVistaJornadasP()) {
                    setVisible(false);
                } else {
                    JOptionPane.showMessageDialog(this, "Se generó un error al ingresar la jornada\n"
                            + "Por favor compruebe que el cliente, grúa y empleados sean correctos", "Error", JOptionPane.INFORMATION_MESSAGE);
                }
            } catch (ParseException ex) {
                Logger.getLogger(vistaIngresarJornadas.class.getName()).log(Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox comboClientes;
    private javax.swing.JComboBox comboGruas;
    private javax.swing.JComboBox comboOperadores;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCliente;
    private javax.swing.JLabel labelFechaRegreso;
    private javax.swing.JLabel labelFechaSalida;
    private javax.swing.JLabel labelGrua;
    private javax.swing.JLabel labelHoraRegreso;
    private javax.swing.JLabel labelHoraSalida;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelObsCliente;
    private javax.swing.JLabel labelOperador;
    private javax.swing.JScrollPane scrollpan;
    private org.jdesktop.swingx.JXDatePicker textoFechaRegreso;
    private org.jdesktop.swingx.JXDatePicker textoFechaSalida;
    private javax.swing.JSpinner textoHoraRegreso;
    private javax.swing.JSpinner textoHoraSalida;
    private javax.swing.JTextArea textoObs;
    private javax.swing.JTextArea textoObsCliente;
    // End of variables declaration//GEN-END:variables

    public void focusGained(FocusEvent event) {
        System.out.println("HOLA");
    }

    public String getTextoFechaRegreso() {
        Date fecha = textoFechaRegreso.getDate();
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(textoFechaRegreso.getDate());
        return dateString;
    }

    public String getDiaSalida() {
        Date fecha = textoFechaSalida.getDate();
        if (fecha == null) {
            return "";
        }
        String dia = formatDia.format(textoFechaSalida.getDate());
        return dia;
    }

    public String getDiaRegreso() {
        Date fecha = textoFechaRegreso.getDate();
        if (fecha == null) {
            return "";
        }
        String dia = formatDia.format(textoFechaRegreso.getDate());
        return dia;
    }

    public String getTextoFechaSalida() {
        Date fecha = textoFechaSalida.getDate();
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(textoFechaSalida.getDate());
        return dateString;
    }

    public String getTextoHoraRegreso() {
        String hora = formatClock.format(textoHoraRegreso.getValue());
        if (hora == null) {
            return "";
        }
        return hora;
    }

    public String getTextoHoraSalida() {
        String hora = formatClock.format(textoHoraSalida.getValue());
        if (hora == null) {
            return "";
        }
        return hora;
    }

    public String getTextoObs() {
        return textoObs.getText();
    }

    public String getComboCliente() {
        return comboClientes.getSelectedItem().toString();
    }

    public String getTextoGrua() {
        if(comboGruas.getSelectedItem() == null){
            return "";
        }
        return comboGruas.getSelectedItem().toString();
    }

    public String getTextoOperador() {
        if(comboOperadores.getSelectedItem() == null){
            return "";
        }
        return comboOperadores.getSelectedItem().toString();
    }

    public String getTextoObsCliente() {
        return textoObsCliente.getText();
    }

    public void setTextoObsCliente(String textoObsCliente) {
        this.textoObsCliente.setText(textoObsCliente);
    }

    private void setModel(DefaultComboBoxModel mdl, String str, JComboBox combo, JTextField texto){
        combo.setModel(mdl);
        texto.setText(str);
    }

    private DefaultComboBoxModel getSuggestedModel(List<String> list, String tx){
        DefaultComboBoxModel m = new DefaultComboBoxModel();
        for (String s : list){
            if(s.toLowerCase().contains(tx.toLowerCase())){
                m.addElement(s);
            }
        }
        return m;
    }

    public final void autosuggestClientes(Object[] data){
            comboClientes.removeAllItems();
            if(comboClientes.getItemCount() == 0){
                for (Object data1 : data) {
                    comboClientes.addItem(data1);
                    listClientes.add((String) data1);
                    comboClientes.addItemListener(new ItemListener() {

                        @Override
                        public void itemStateChanged(ItemEvent ie) {
                            if(ie.getStateChange() == ItemEvent.SELECTED){
                                comboClientes.getSelectedIndex();
                            }
                        }
                    });
                }
            }else{
                comboClientes.addItem("Error");
            }

        comboClientes.setEditable(true);
        textoClientes.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = textoClientes.getText();
                        if(text.length() == 0){
                            comboClientes.hidePopup();
                            setModel(new DefaultComboBoxModel(listClientes.toArray()), "", comboClientes, textoClientes);
                        }else{
                            DefaultComboBoxModel m = getSuggestedModel(listClientes, text);
                            if(m.getSize() == 0){
                                comboClientes.hidePopup();
                            }else{
                                setModel(m, text, comboClientes, textoClientes);
                                comboClientes.showPopup();
                            }
                        }
                    }
                });
            }

            @Override
            public void keyPressed(KeyEvent e){
                String text = textoClientes.getText();
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_ENTER){
                    for (int i = 0; i < listClientes.size(); i++) {
                        String str = (String)listClientes.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoClientes.setText(str);
                            return;
                        }
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
                    hide_flag = true;
                }else if(code == KeyEvent.VK_RIGHT){
                    for (int i = 0; i < listClientes.size(); i++) {
                        String str = (String)listClientes.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoClientes.setText(str);
                            return;
                        }
                    }
                }
            }
        });
    }

    public final void autosuggestGruas(Object[] data){
            String item = "";
            if(comboGruas.getSelectedItem() != null){
                item = comboGruas.getSelectedItem().toString();
            }
            comboGruas.removeAllItems();
            listGruas.clear();
            if(comboGruas.getItemCount() == 0){
                    //comboGruas.setModel(new DefaultComboBoxModel(data));
                for (Object data1 : data) {
                    comboGruas.addItem(data1);
                    listGruas.add((String) data1);
                }
                comboGruas.setSelectedIndex(((DefaultComboBoxModel)comboGruas.getModel()).getIndexOf(item));
            }else{
                comboGruas.addItem("Error");
            }

        comboGruas.setEditable(true); 
    }

    public final void autosuggestOperadores(Object[] data){
            String item = "";
            if(comboOperadores.getSelectedItem() != null){
                item = comboOperadores.getSelectedItem().toString();
            }
            comboOperadores.removeAllItems();
            listEmpleados.clear();
            if(comboOperadores.getItemCount() == 0){
                //comboOperadores.setModel(new DefaultComboBoxModel(data));
                for (Object data1 : data) {
                    comboOperadores.addItem(data1);
                    listEmpleados.add((String) data1);
                    comboOperadores.addItemListener(new ItemListener() {

                        @Override
                        public void itemStateChanged(ItemEvent ie) {
                            if(ie.getStateChange() == ItemEvent.SELECTED){
                                comboOperadores.getSelectedIndex();
                            }
                        }
                    });
                }
                comboOperadores.setSelectedIndex(((DefaultComboBoxModel)comboOperadores.getModel()).getIndexOf(item));
            }else{
                comboOperadores.addItem("Error");
            }
            
        comboOperadores.setEditable(true);
        textoOperadores.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = textoOperadores.getText();
                        if(text.length() == 0){
                            comboOperadores.hidePopup();
                            setModel(new DefaultComboBoxModel(listEmpleados.toArray()), "", comboOperadores, textoOperadores);
                        }else{
                            DefaultComboBoxModel m = getSuggestedModel(listEmpleados, text);
                            if(m.getSize() == 0){
                                comboOperadores.hidePopup();
                            }else{
                                setModel(m, text, comboOperadores, textoOperadores);
                                comboOperadores.showPopup();
                            }
                        }
                    }
                });
            }

            @Override
            public void keyPressed(KeyEvent e){
                String text = textoOperadores.getText();
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_ENTER){
                    for (int i = 0; i < listEmpleados.size(); i++) {
                        String str = (String)listEmpleados.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoOperadores.setText(str);
                            return;
                        }
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
                    hide_flag = true;
                }else if(code == KeyEvent.VK_RIGHT){
                    for (int i = 0; i < listEmpleados.size(); i++) {
                        String str = (String)listEmpleados.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoOperadores.setText(str);
                            return;
                        }
                    }
                }
            }
        });
    }
}
