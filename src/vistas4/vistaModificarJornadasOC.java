/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas4;

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
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.text.MaskFormatter;
import modelos.modeloClientes;
import org.jdesktop.swingx.table.DatePickerCellEditor;

/**
 *
 * @author diego
 */
 public class vistaModificarJornadasOC extends javax.swing.JDialog {
    String id;

    modelos.modeloClientes cliente = new modeloClientes();
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat formatDate2 = new SimpleDateFormat("dd-MM-yyyy");
    SimpleDateFormat formatClock = new SimpleDateFormat("HH:mm");
    DateFormat formatDia = new SimpleDateFormat("EEE");
    JComboBox comboGruas = new JComboBox();
    JComboBox comboOperadores = new JComboBox();
    JTextField textoClientes = new JTextField();
    JTextField textoOperadores = new JTextField();
    JTextField textoGruas = new JTextField();
    List<String> listClientes = new ArrayList<>();
    List<String> listGruas = new ArrayList<>();
    List<String> listEmpleados = new ArrayList<>();
    private boolean hide_flag = false;
    MyTableModelGruas tg = new MyTableModelGruas();
    MyTableModelEmpleados te = new MyTableModelEmpleados();
    MyTableModelHoras th = new MyTableModelHoras();
    
    /**
     * Creates new form vistaModificarJornadasOC
     */
    public vistaModificarJornadasOC(java.awt.Frame parent, boolean modal, Object[] clientes, Object[] gruas, Object[] empleados) throws ParseException {
        super(parent, modal);
        initComponents();
        MaskFormatter mask = new MaskFormatter("##:##");
        JFormattedTextField hora = new JFormattedTextField(mask);
        
        textoClientes = (JTextField)comboClientes.getEditor().getEditorComponent();
        textoGruas = (JTextField)comboGruas.getEditor().getEditorComponent();
        textoOperadores = (JTextField)comboOperadores.getEditor().getEditorComponent();
        tablaGruas.setModel(tg);
        tablaGruas.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboGruas));
        tablaGruas.getColumnModel().getColumn(1).setCellEditor(new DatePickerCellEditor(formatDate));
        tablaGruas.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(hora));
        tablaGruas.getColumnModel().getColumn(3).setCellEditor(new DatePickerCellEditor(formatDate));
        tablaGruas.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(hora));
        tablaEmpleados.setModel(te);
        tablaEmpleados.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboOperadores));
        tablaEmpleados.getColumnModel().getColumn(1).setCellEditor(new DatePickerCellEditor(formatDate));
        tablaEmpleados.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(hora));
        tablaEmpleados.getColumnModel().getColumn(3).setCellEditor(new DatePickerCellEditor(formatDate));
        tablaEmpleados.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(hora));
//        hb.setRowCount(tonelajes.length);
        tg.setRowCount(0);
        te.setRowCount(0);
        tablaHoras.setModel(th);
        
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
        
//        textoFechaSalida.getEditor().addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent fe) {
//            }
//
//            @Override
//            public void focusLost(FocusEvent fe) {
//                 if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
//                    modelos.modeloGruas grua = new modelos.modeloGruas();
//                    Object[] gruas = grua.obtenerDescGruasDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
//                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
//                    autosuggestGruas(gruas); 
//                    modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
//                    Object[] empleados = empleado.obtenerNomEmpDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
//                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
//                    autosuggestOperadores(empleados);
//                 }
//            }
//        });
        
//        textoFechaRegreso.getEditor().addFocusListener(new FocusListener() {
//            @Override
//            public void focusGained(FocusEvent fe) {
//                
//            }
//
//            @Override
//            public void focusLost(FocusEvent fe) {
//                 if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
//                    modelos.modeloGruas grua = new modelos.modeloGruas();
//                    Object[] gruas = grua.obtenerDescGruasDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
//                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
//                    autosuggestGruas(gruas); 
//                    modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
//                    Object[] empleados = empleado.obtenerNomEmpDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
//                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
//                    autosuggestOperadores(empleados);
//                 }
//            }
//        });
        
        
//        for(Object data : tonelajes){
//            tablaHorasBase.setValueAt(data, i, 0);
//            i++;
//        }
        textoGruas.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
////                if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
//                    modelos.modeloGruas grua = new modelos.modeloGruas();
//                    Object[] gruas = grua.obtenerDescGruas();
////                    Object[] gruas = grua.obtenerDescGruasDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
////                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
//                    autosuggestGruas(gruas);
//                }
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
        
        textoOperadores.addFocusListener(new FocusListener(){
            @Override
            public void focusGained(FocusEvent fe){
////                if(getTextoFechaRegreso().compareTo("") != 0 && getTextoFechaSalida().compareTo("") != 0){
//                    modelos.modeloGruas grua = new modelos.modeloGruas();
//                    Object[] gruas = grua.obtenerDescGruas();
////                    Object[] gruas = grua.obtenerDescGruasDisp(getTextoFechaSalida() + " " + getTextoHoraSalida(),
////                    getTextoFechaRegreso() + " " + getTextoHoraRegreso());
//                    autosuggestGruas(gruas);
//                }
            }
            
            @Override 
            public void focusLost(FocusEvent fl){}
        });

        textoOperadores.addKeyListener(new KeyAdapter(){
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
                            comboOperadores.hidePopup();
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
        comboOperadores.addItemListener(new ItemListener() {
                        @Override
                        public void itemStateChanged(ItemEvent ie) {
                            if(ie.getStateChange() == ItemEvent.SELECTED){
                                comboOperadores.getSelectedIndex();
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

        radioDoc = new javax.swing.ButtonGroup();
        botonCancelar = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        textoObsCliente = new javax.swing.JTextArea();
        textoFechaSalida = new org.jdesktop.swingx.JXDatePicker();
        spinnerGruas = new javax.swing.JSpinner();
        jScrollPane3 = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        labelTrabajadores = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        labelGruas = new javax.swing.JLabel();
        textoFechaRegreso = new org.jdesktop.swingx.JXDatePicker();
        spinnerEmpleados = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        scrollTrabajadores = new javax.swing.JScrollPane();
        tablaEmpleados = new javax.swing.JTable();
        scrollGruas = new javax.swing.JScrollPane();
        tablaGruas = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        comboClientes = new javax.swing.JComboBox<>();
        scrollHoras = new javax.swing.JScrollPane();
        tablaHoras = new javax.swing.JTable();
        labelNumTras = new javax.swing.JLabel();
        spinnerNumTras = new javax.swing.JSpinner();
        jLabel8 = new javax.swing.JLabel();
        radioOC = new javax.swing.JRadioButton();
        radioTras = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Modificar jornada OC");

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

        textoObsCliente.setEditable(false);
        textoObsCliente.setColumns(20);
        textoObsCliente.setRows(5);
        jScrollPane4.setViewportView(textoObsCliente);

        spinnerGruas.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerGruasStateChanged(evt);
            }
        });

        textoObs.setColumns(20);
        textoObs.setRows(5);
        jScrollPane3.setViewportView(textoObs);

        jLabel5.setText("Fecha de inicio");

        jLabel10.setText("Observaciones cliente");

        labelTrabajadores.setText("Número de trabajadores");

        jLabel7.setText("Observaciones");

        labelGruas.setText("Número de grúas");

        spinnerEmpleados.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerEmpleadosStateChanged(evt);
            }
        });

        jLabel1.setText("Cliente");

        tablaEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollTrabajadores.setViewportView(tablaEmpleados);

        tablaGruas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollGruas.setViewportView(tablaGruas);

        jLabel6.setText("Fecha de término");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaHoras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Tonelaje", "N° de horas", "N° de turnos", "Horas base"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class, java.lang.Double.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        scrollHoras.setViewportView(tablaHoras);

        labelNumTras.setText("Número de traspaletas");

        spinnerNumTras.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(comboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoFechaSalida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(textoFechaRegreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollGruas, javax.swing.GroupLayout.DEFAULT_SIZE, 694, Short.MAX_VALUE)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane4)
                    .addComponent(scrollTrabajadores, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel3)
                        .addGap(209, 209, 209))
                    .addComponent(scrollHoras)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelGruas)
                                .addGap(18, 18, 18)
                                .addComponent(spinnerGruas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel1)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel10)
                            .addComponent(jLabel7)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelTrabajadores)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinnerEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(labelNumTras)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(spinnerNumTras, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoFechaSalida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(textoFechaRegreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(spinnerGruas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(labelGruas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollGruas, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTrabajadores)
                    .addComponent(spinnerEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollTrabajadores, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addGap(18, 18, 18)
                .addComponent(scrollHoras, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNumTras)
                    .addComponent(spinnerNumTras, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel1);

        jLabel8.setText("Tipo de documento");

        radioDoc.add(radioOC);
        radioOC.setSelected(true);
        radioOC.setText("Jornada OC");
        radioOC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioOCActionPerformed(evt);
            }
        });

        radioDoc.add(radioTras);
        radioTras.setText("Trapaleta");
        radioTras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radioTrasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(botonAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(botonCancelar)
                .addContainerGap())
            .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 733, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(radioOC)
                        .addGap(18, 18, 18)
                        .addComponent(radioTras)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(radioOC)
                    .addComponent(radioTras))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 839, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 14, Short.MAX_VALUE)
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
        controladores4.controladorModificarJornadasOC miControladorMJOC = new controladores4.controladorModificarJornadasOC();
        if(radioOC.isSelected()){
            String respuesta = miControladorMJOC.camposVacios();
            boolean esVacio = respuesta.length() == 0;
            if (!esVacio) {
                JOptionPane.showMessageDialog(this, respuesta, "Debe rellenar los siguientes campos", JOptionPane.INFORMATION_MESSAGE);
            } else {
                try {
                    if (miControladorMJOC.irVistaJornadasOCP()) {
                        setVisible(false);
                    } else {
                        JOptionPane.showMessageDialog(this, "Se generó un error al ingresar la jornada\n"
                            + "Por favor compruebe que el cliente, grúa y empleados sean correctos", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(vistaModificarJornadasOC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        /* Desde aqui se ingresa como traspaleta */
        }else{
            String respuesta = miControladorMJOC.camposVaciosTras();
            boolean esVacio = respuesta.length() == 0;
            if(!esVacio){
                JOptionPane.showMessageDialog(this, respuesta, "Debe rellenar los siguientes campos", JOptionPane.INFORMATION_MESSAGE);
            }else {
                try {
                    if (miControladorMJOC.irVistaJornadasTrasOCP()) {
                        setVisible(false);
                    } else {
    //                    JOptionPane.showMessageDialog(this, "Se generó un error al ingresar la jornada\n"
    //                            + "Por favor compruebe que el cliente, grúa y empleados sean correctos", "Error", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (ParseException ex) {
                    Logger.getLogger(vistaIngresarJornadasOC.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void spinnerGruasStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerGruasStateChanged
        tg.setRowCount((Integer) spinnerGruas.getValue());
    }//GEN-LAST:event_spinnerGruasStateChanged

    private void spinnerEmpleadosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerEmpleadosStateChanged
        te.setRowCount((Integer) spinnerEmpleados.getValue());
    }//GEN-LAST:event_spinnerEmpleadosStateChanged

    private void radioOCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioOCActionPerformed
        if(radioOC.isSelected()){
            showOC();
            hideTras();
        }
    }//GEN-LAST:event_radioOCActionPerformed

    private void radioTrasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radioTrasActionPerformed
        if(radioTras.isSelected()){
            showTras();
            hideOC();
        }
    }//GEN-LAST:event_radioTrasActionPerformed

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
            java.util.logging.Logger.getLogger(vistaModificarJornadasOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaModificarJornadasOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaModificarJornadasOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaModificarJornadasOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JComboBox<String> comboClientes;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel labelGruas;
    private javax.swing.JLabel labelNumTras;
    private javax.swing.JLabel labelTrabajadores;
    private javax.swing.ButtonGroup radioDoc;
    private javax.swing.JRadioButton radioOC;
    private javax.swing.JRadioButton radioTras;
    private javax.swing.JScrollPane scrollGruas;
    private javax.swing.JScrollPane scrollHoras;
    private javax.swing.JScrollPane scrollTrabajadores;
    private javax.swing.JSpinner spinnerEmpleados;
    private javax.swing.JSpinner spinnerGruas;
    private javax.swing.JSpinner spinnerNumTras;
    private javax.swing.JTable tablaEmpleados;
    private javax.swing.JTable tablaGruas;
    private javax.swing.JTable tablaHoras;
    private org.jdesktop.swingx.JXDatePicker textoFechaRegreso;
    private org.jdesktop.swingx.JXDatePicker textoFechaSalida;
    private javax.swing.JTextArea textoObs;
    private javax.swing.JTextArea textoObsCliente;
    // End of variables declaration//GEN-END:variables

    public void setId(String id){
        this.id = id;
    }
    
    public String getId(){
        return this.id;
    }
    
    public void setComboCliente(String comboCliente) {
        this.comboClientes.setSelectedItem(comboCliente);
    }

    public void setTextoFechaRegreso(String textoFechaRegreso) throws ParseException {
        this.textoFechaRegreso.setDate(formatDate.parse(textoFechaRegreso));
    }

    public void setTextoFechaSalida(String textoFechaSalida) throws ParseException {
        this.textoFechaSalida.setDate(formatDate.parse(textoFechaSalida));
    }

    public void setTextoObs(String textoObs) {
        this.textoObs.setText(textoObs);
    }
    
    public void setTextoObsCliente(String textoObsCliente){
        this.textoObsCliente.setText(textoObsCliente);
    }
    
    public void setSpinnerGruas(String spinnerNumGruas){
        this.spinnerGruas.setValue(Integer.parseInt(spinnerNumGruas));
    }
    
    public void setSpinnerEmpleados(String spinnerNumEmpleados){
        this.spinnerEmpleados.setValue(Integer.parseInt(spinnerNumEmpleados));
    }
    
    public String getTextoFechaRegreso() {
        Date fecha = textoFechaRegreso.getDate();
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(textoFechaRegreso.getDate());
        return dateString;
    }
    
    public String getTextoFechaSalida() {
        Date fecha = textoFechaSalida.getDate();
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(textoFechaSalida.getDate());
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

    public String getTextoFechaSalidaGrua(int i) {
        Date fecha = (Date)tablaGruas.getValueAt(i, 1);
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(fecha);
        return dateString;
    }
    
    public String getTextoFechaSalidaEmp(int i) {
        Date fecha = (Date)tablaEmpleados.getValueAt(i, 1);
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(fecha);
        return dateString;
    }
    
    public String getTextoFechaRegresoGrua(int i) {
        Date fecha = (Date)tablaGruas.getValueAt(i, 3);
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(fecha);
        return dateString;
    }
    
    public String getTextoFechaRegresoEmp(int i) {
        Date fecha = (Date)tablaEmpleados.getValueAt(i, 3);
        if (fecha == null) {
            return "";
        }
        String dateString = formatDate.format(fecha);
        return dateString;
    }

    public String getTextoHoraSalidaGrua(int i) {
        if(tablaGruas.getValueAt(i, 2) == null) return "";
        String hora = tablaGruas.getValueAt(i, 2).toString();
        if(hora == null) return "";
        return hora;
    }
    
    public String getTextoHoraSalidaEmp(int i) {
        if(tablaEmpleados.getValueAt(i, 2) == null) return "";
        String hora = tablaEmpleados.getValueAt(i, 2).toString();
        return hora;
    }

    public String getTextoHoraRegresoGrua(int i) {
        if(tablaGruas.getValueAt(i, 4) == null) return "";
        String hora = tablaGruas.getValueAt(i, 4).toString();
        return hora;
    }
    
    public String getTextoHoraRegresoEmp(int i) {
        if(tablaEmpleados.getValueAt(i, 4) == null) return "";
        String hora = tablaEmpleados.getValueAt(i, 4).toString();
        return hora;
    }
    
    public String getTextoHoraSalida() {
        return "";
    }
    
    public String getTextoHoraRegreso(){
        return "";
    }
    
    public String getTextoObs() {
        return textoObs.getText();
    }

    public String getComboCliente() {
        return comboClientes.getSelectedItem().toString();
    }

    public String getTextoGrua() {
        if (comboGruas.getSelectedItem() == null) {
            return "";
        }
        return comboGruas.getSelectedItem().toString();
    }

    public String getTextoOperador() {
        if (comboOperadores.getSelectedItem() == null) {
            return "";
        }
        return comboOperadores.getSelectedItem().toString();
    }

    public String getTextoObsCliente() {
        return textoObsCliente.getText();
    }

    public JTable getTablaGruas() {
        return tablaGruas;
    }

    public JTable getTablaHoras() {
        return tablaHoras;
    }

    public String getGruasVacia() {
        for (int i = 0; i < tablaGruas.getRowCount(); i++) {
//            for(int j = 0; j < tablaGruas.getColumnCount(); j++){
                if (tablaGruas.getValueAt(i, 0) == null || tablaGruas.getValueAt(i, 0) == "") {
                    return "";
//                }
            }
        }
        return "novacio";
    }

    public String getEmpleadosVacia() {
        for (int i = 0; i < tablaEmpleados.getRowCount(); i++) {
//            for(int j = 0; j < tablaEmpleados.getColumnCount(); j++){
                if (tablaEmpleados.getValueAt(i, 0) == null) {
                    return "";
                }
//            }
        }
        return "novacio";
    }

    public JTable getTablaEmpleados() {
        return tablaEmpleados;
    }

    public int getSpinnerGruas() {
        return Integer.parseInt(spinnerGruas.getValue().toString());
    }

    public int getSpinnerEmpleados() {
        return Integer.parseInt(spinnerEmpleados.getValue().toString());
    }
    
    public void agregarGruas(String id) throws ParseException{
        int i = 0;
        modelos4.modeloJornadasOC detalle_gru = new modelos4.modeloJornadasOC();
        Object[][] data = detalle_gru.obtenerGruasPorIdJornadaOC(id);
        tg.setRowCount(data.length);
        for(Object[] data1 : data){
            tablaGruas.setValueAt(data1[0], i, 0);
            if(data1[1] != null) tablaGruas.setValueAt(formatDate2.parse(data1[1].toString()), i, 1);
            if(data1[2] != null) tablaGruas.setValueAt(data1[2].toString(), i, 2);
            if(data1[3] != null) tablaGruas.setValueAt(formatDate2.parse(data1[3].toString()), i, 3);
            if(data1[4] != null) tablaGruas.setValueAt(data1[4], i, 4);
            i++;
        }
    }
    
    public void agregarEmpleados(String id) throws ParseException{
        int i = 0;
        modelos4.modeloJornadasOC detalle_emp = new modelos4.modeloJornadasOC();
        Object[][] data = detalle_emp.obtenerEmpleadosPorIdJornadaOC(id);
        te.setRowCount(data.length);
        for(Object[] data1 : data){
            tablaEmpleados.setValueAt(data1[0], i, 0);
            if(data1[1] != null) tablaEmpleados.setValueAt(formatDate2.parse(data1[1].toString()), i, 1);
            if(data1[2] != null) tablaEmpleados.setValueAt(data1[2].toString(), i, 2);
            if(data1[3] != null) tablaEmpleados.setValueAt(formatDate2.parse(data1[3].toString()), i, 3);
            if(data1[4] != null) tablaEmpleados.setValueAt(data1[4].toString(), i, 4);
            i++;
        }
    }
    
    public void agregarHoras(String id) throws ParseException{
        int i = 0;
        modelos4.modeloJornadasOC detalle_horas = new modelos4.modeloJornadasOC();
        String[][] data = detalle_horas.obtenerHorasPorIdJornadaOC(id);
        th.setRowCount(data.length);
        for(String[] data1 : data){
            tablaHoras.setValueAt(Double.parseDouble(data1[0]), i, 0);
            tablaHoras.setValueAt(Double.parseDouble(data1[1]), i, 1);
            tablaHoras.setValueAt(Double.parseDouble(data1[2]), i, 2);
            tablaHoras.setValueAt(Double.parseDouble(data1[3]), i, 3);
            i++;
        }
    }
    
    public final void autosuggestClientes(Object[] data){
            comboClientes.removeAllItems();
            if(comboClientes.getItemCount() == 0){
                for (Object data1 : data) {
                    comboClientes.addItem(data1.toString());
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
                }
                comboOperadores.setSelectedIndex(((DefaultComboBoxModel)comboOperadores.getModel()).getIndexOf(item));
            }else{
                comboOperadores.addItem("Error");
            }
            
        comboOperadores.setEditable(true);
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
    
    public class MyTableModelGruas extends DefaultTableModel {

        public MyTableModelGruas() {
            super(new String[]{"Grua", "Fecha de salida", "Hora de salida", "Fecha de regreso", "Hora de regreso"}, 0);
        }
        
        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                case 1:
                    return Date.class;
                case 2:
                    return String.class;
                case 3:
                    return Date.class;
                case 4:
                    return String.class;
                default:
                    return String.class;
            }
        }

        @Override
        public Object getValueAt(int row, int col) {
            return super.getValueAt(row, col);
        }
    }

    public class MyTableModelEmpleados extends DefaultTableModel {

        public MyTableModelEmpleados() {
            super(new String[]{"Empleado", "Fecha de salida", "Hora de salida", "Fecha de llegada", "Hora de llegada"}, 0);
        }

        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                case 1:
                    return Date.class;
                case 2: 
                    return String.class;
                case 3:
                    return Date.class;
                case 4:
                    return String.class;
                default:
                    return String.class;
            }
        }

        @Override
        public Object getValueAt(int row, int col) {
            return super.getValueAt(row, col);
        }
    }

    public class MyTableModelHoras extends DefaultTableModel {

        public MyTableModelHoras() {
            super(new String[]{"Tonelajes", "N° de horas", "N° de Turnos", "Horas base"}, 0);
        }

        @Override
        public Class getColumnClass(int column) {
            switch (column) {
                default:
                    return Double.class;
            }
        }

        @Override
        public Object getValueAt(int row, int col) {
            if (super.getValueAt(row, col) == null) {
                return 0.0;
            }
            return super.getValueAt(row, col);
        }
        
        @Override
        public boolean isCellEditable(int row, int col){
            switch(col){
                case 0:
                    return false;
                case 3:
                    return false;
                default:
                    return true;
            }
        }
        
        @Override
        public void setValueAt(Object value, int row, int column){
            super.setValueAt(value, row, column);
            double nextValue = (Double)getValueAt(row,1) * (Double)getValueAt(row, 2) ;
            super.setValueAt(nextValue, row, 3);
            
        }
    }
    
    public void setTonTablaHoras(Object[] tonelajes) {
        int i = 0;
        for (Object ton : tonelajes) {
            tablaHoras.setValueAt(Double.parseDouble(ton.toString()), i, 0);
            i++;
        }
    }    
    
     /* Para traspaleta*/
    
    public int getSpinnerTras() {
        return Integer.parseInt(spinnerNumTras.getValue().toString());
    }
    
    public void hideTras(){
        labelNumTras.setVisible(false);
        spinnerNumTras.setVisible(false);
    }
    
    public void showTras(){
        labelNumTras.setVisible(true);
        spinnerNumTras.setVisible(true);
    }
    
    public void hideOC(){
        labelGruas.setVisible(false);
        spinnerGruas.setVisible(false);
        scrollGruas.setVisible(false);
        labelTrabajadores.setVisible(false);
        spinnerEmpleados.setVisible(false);
        scrollTrabajadores.setVisible(false);
        scrollHoras.setVisible(false);
    }
    
    public void showOC(){
        labelGruas.setVisible(true);
        spinnerGruas.setVisible(true);
        scrollGruas.setVisible(true);
        labelTrabajadores.setVisible(true);
        spinnerEmpleados.setVisible(true);
        scrollTrabajadores.setVisible(true);
        scrollHoras.setVisible(true);
    }
    
    public void setSpinnerTras(String spinnerNumTras){
        this.spinnerNumTras.setValue(Integer.parseInt(spinnerNumTras));
    }
    
    public void setRadioOC(){
        radioOC.setSelected(true);
    }
    
    public void setRadioTras(){
        radioTras.setSelected(true);
    }
}
