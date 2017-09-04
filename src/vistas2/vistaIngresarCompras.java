/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas2;

import controladores2.controladorIngresarCompras;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.JTextComponent;
import modelos2.modeloProveedores;
import org.jdesktop.swingx.JXDatePicker;
import org.jdesktop.swingx.renderer.DefaultTableRenderer;
import org.jdesktop.swingx.renderer.FormatStringValue;
import org.jdesktop.swingx.renderer.StringValue;
import org.jdesktop.swingx.table.DatePickerCellEditor;

/**
 *
 * @author diego
 */
public class vistaIngresarCompras extends javax.swing.JDialog {
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
    modelos2.modeloProveedores proveedor = new modeloProveedores();
    StringValue sv = new FormatStringValue(new SimpleDateFormat("dd-MMMM-yyyy"));
    TableCellRenderer r = new DefaultTableRenderer(sv);
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    List<String> listProveedores = new ArrayList<>();
    JTextField textoRut = new JTextField();
    MyTableModelCheques cheques = new MyTableModelCheques();
    MyTableModelTC tc = new MyTableModelTC();
    MyTableModel detalles = new MyTableModel();
    MyTableModelImpuestos impuestos = new MyTableModelImpuestos();
    
    private boolean hide_flag = false;
    
    public vistaIngresarCompras(java.awt.Frame parent, boolean modal, Object proveedores[]) {
        super(parent, modal);
        initComponents();
        ((AbstractDocument) textoFolio.getDocument()).setDocumentFilter(onlyNumbers);
        dfs.setCurrencySymbol("$");
        dfs.setGroupingSeparator('.');
        dfs.setMonetaryDecimalSeparator('.');
        ((DecimalFormat) FORMAT).setDecimalFormatSymbols(dfs);
        textoRut = (JTextField)comboRut.getEditor().getEditorComponent();
        final JTextComponent tcr = (JTextComponent) comboRut.getEditor().getEditorComponent();
        tcr.getDocument().addDocumentListener(new DocumentListener() { 
            @Override
            public void insertUpdate(DocumentEvent de) {
                String data[] = proveedor.obtenerProveedorPorRut(comboRut.getSelectedItem().toString());
                setTextoContacto(data[1]);
                setTextoRazon(data[2]);
                setTextoGiro(data[3]);
                setTextoDireccion(data[6]);
            }

            @Override
            public void removeUpdate(DocumentEvent de) {
            }

            @Override
            public void changedUpdate(DocumentEvent de) {
            }
        });
        textoFechaIngreso.setDate(new Date());
        hideOtrosPagos();
        hideMedioPago();
        tablaDetalle.setModel(detalles);
        tablaImpuestos.setModel(impuestos);
        tablaDetalle.getColumnModel().getColumn(3).setCellRenderer(new CurrencyTableCellRenderer());
        tablaImpuestos.getColumnModel().getColumn(1).setCellRenderer(new CurrencyTableCellRenderer());
        tablaImpuestos.setEnabled(false);
        botonMasImp.setEnabled(false);
        botonMenosImp.setEnabled(false);
        autosuggestProveedores(proveedores);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        labelTipoDTE = new javax.swing.JLabel();
        comboTipoDTE = new javax.swing.JComboBox();
        labelRut = new javax.swing.JLabel();
        comboRut = new javax.swing.JComboBox();
        labelRazon = new javax.swing.JLabel();
        textoRazon = new javax.swing.JTextField();
        labelForma = new javax.swing.JLabel();
        comboForma = new javax.swing.JComboBox();
        labelFechaIngreso = new javax.swing.JLabel();
        textoFechaIngreso = new org.jdesktop.swingx.JXDatePicker();
        labelFolio = new javax.swing.JLabel();
        textoFolio = new javax.swing.JTextField();
        labelOrden = new javax.swing.JLabel();
        textoOrden = new javax.swing.JTextField();
        labelFechaPago = new javax.swing.JLabel();
        textoFechaPago = new org.jdesktop.swingx.JXDatePicker();
        labelMedio = new javax.swing.JLabel();
        labelCantidad = new javax.swing.JLabel();
        comboMedio = new javax.swing.JComboBox();
        spinnerCantidad = new javax.swing.JSpinner();
        labelBanco = new javax.swing.JLabel();
        textoBanco = new javax.swing.JTextField();
        scrollDatos = new javax.swing.JScrollPane();
        tablaDatos = new javax.swing.JTable();
        labelNumTC = new javax.swing.JLabel();
        textoNumTC = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        botonMenos = new javax.swing.JButton();
        botonMas = new javax.swing.JButton();
        textoAsunto = new javax.swing.JTextField();
        labelAsunto = new javax.swing.JLabel();
        spinnerNumCuotas = new javax.swing.JSpinner();
        labelNumCuotas = new javax.swing.JLabel();
        labelGiro = new javax.swing.JLabel();
        textoGiro = new javax.swing.JTextField();
        labelDireccion = new javax.swing.JLabel();
        textoDireccion = new javax.swing.JTextField();
        labelContacto = new javax.swing.JLabel();
        textoContacto = new javax.swing.JTextField();
        labelObs = new javax.swing.JLabel();
        scrollObs = new javax.swing.JScrollPane();
        textoObs = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        checkEstado = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        textoNeto = new javax.swing.JFormattedTextField(FORMAT);
        jLabel4 = new javax.swing.JLabel();
        textoIva = new javax.swing.JFormattedTextField(FORMAT);
        jLabel5 = new javax.swing.JLabel();
        textoTot = new javax.swing.JFormattedTextField(FORMAT);
        botonActualizarMontos = new javax.swing.JButton();
        textoImpuestoEsp = new javax.swing.JTextField();
        textoImpuestoVar = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        checkImpuestos = new javax.swing.JCheckBox();
        panelImpuestos = new javax.swing.JPanel();
        scrollImpuestos = new javax.swing.JScrollPane();
        tablaImpuestos = new javax.swing.JTable();
        botonMenosImp = new javax.swing.JButton();
        botonMasImp = new javax.swing.JButton();
        botonAceptar = new javax.swing.JButton();
        botonCancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar compra");

        jScrollPane1.getVerticalScrollBar().setUnitIncrement(16);

        labelTipoDTE.setText("Tipo de DTE");

        comboTipoDTE.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Factura de ventas y servicios", "Factura de venta y servicios electrónica", "Factura exenta", "Factura exenta electrónica", "Nota de crédito", "Nota de crédito electrónica", "Nota de débito", "Nota de débito electrónica" }));

        labelRut.setText("Rut proveedor");

        labelRazon.setText("Razón social");

        textoRazon.setEditable(false);
        textoRazon.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textoRazon.setForeground(new java.awt.Color(255, 51, 51));

        textoRazon.setDisabledTextColor(new java.awt.Color(102, 102, 102));

        textoRazon.setEnabled(false);

        labelForma.setText("Forma de pago");

        comboForma.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Al día", "30 días", "60 días", "90 días", "Otros pagos" }));
        comboForma.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboFormaActionPerformed(evt);
            }
        });

        labelFechaIngreso.setText("Fecha de ingreso");

        labelFolio.setText("Folio documento");

        labelOrden.setText("Orden de compra");

        labelFechaPago.setText("Fecha de pago");

        labelMedio.setText("Medio de pago");

        labelCantidad.setText("Cantidad");

        comboMedio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Efectivo", "Transferencia", "Cheque", "Tarjeta de crédito" }));
        comboMedio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comboMedioActionPerformed(evt);
            }
        });

        spinnerCantidad.setModel(new javax.swing.SpinnerNumberModel(0, 0, null, 1));
        spinnerCantidad.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                spinnerCantidadStateChanged(evt);
            }
        });

        labelBanco.setText("Banco");

        tablaDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        scrollDatos.setViewportView(tablaDatos);

        labelNumTC.setText("Número de tarjeta de crédito");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Detalle"));
        jPanel3.setPreferredSize(new java.awt.Dimension(200, 176));

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane2.setViewportView(tablaDetalle);

        botonMenos.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        botonMenos.setText("-");
        botonMenos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMenosActionPerformed(evt);
            }
        });

        botonMas.setFont(new java.awt.Font("Tahoma", 1, 8)); // NOI18N
        botonMas.setText("+");
        botonMas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(botonMas)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonMenos)))
                .addContainerGap())
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonMas, botonMenos});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonMenos)
                    .addComponent(botonMas)))
        );

        labelAsunto.setText("Asunto");

        labelNumCuotas.setText("Número de cuotas");

        labelGiro.setText("Giro");

        textoGiro.setEditable(false);
        textoGiro.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textoGiro.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoGiro.setEnabled(false);

        labelDireccion.setText("Dirección");

        textoDireccion.setEditable(false);
        textoDireccion.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textoDireccion.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoDireccion.setEnabled(false);

        labelContacto.setText("Contacto");

        textoContacto.setEditable(false);
        textoContacto.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        textoContacto.setDisabledTextColor(new java.awt.Color(102, 102, 102));
        textoContacto.setEnabled(false);

        labelObs.setText("Observaciones de pago");

        textoObs.setColumns(20);
        textoObs.setRows(5);
        scrollObs.setViewportView(textoObs);

        jLabel1.setText("Estado");

        checkEstado.setText("Pagada");

        jLabel2.setText("Clasificación de compra");

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Activo fijo (maquinaria)", "Activo vehiculo", "Combustibles (petróleo/gas)", "Repuestos", "Neumáticos", "Aceite y lubricantes", "Mantenciones", "Gastos de oficina", "Gastos generales", "Arriendo leasing", "Salud y seguridad", "Molymet", "Otros" }));

        jLabel3.setText("Neto");

        textoNeto.setEditable(false);

        jLabel4.setText("Iva");

        textoIva.setEditable(false);

        jLabel5.setText("Total");

        textoTot.setEditable(false);

        botonActualizarMontos.setText("Actualizar montos");
        botonActualizarMontos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonActualizarMontosActionPerformed(evt);
            }
        });

        textoImpuestoEsp.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoImpuestoEspFocusLost(evt);
            }
        });

        textoImpuestoVar.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                textoImpuestoVarFocusLost(evt);
            }
        });

        jLabel6.setText("Impuesto específico");

        jLabel7.setText("Impuesto variable");

        checkImpuestos.setText("Otros impuestos");
        checkImpuestos.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                checkImpuestosStateChanged(evt);
            }
        });

        panelImpuestos.setBorder(javax.swing.BorderFactory.createTitledBorder("Impuestos"));

        tablaImpuestos.setModel(new javax.swing.table.DefaultTableModel(
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
        scrollImpuestos.setViewportView(tablaImpuestos);

        botonMenosImp.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        botonMenosImp.setText("-");
        botonMenosImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMenosImpActionPerformed(evt);
            }
        });

        botonMasImp.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        botonMasImp.setText("+");
        botonMasImp.setPreferredSize(new java.awt.Dimension(41, 19));
        botonMasImp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonMasImpActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout panelImpuestosLayout = new javax.swing.GroupLayout(panelImpuestos);
        panelImpuestos.setLayout(panelImpuestosLayout);
        panelImpuestosLayout.setHorizontalGroup(
            panelImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImpuestosLayout.createSequentialGroup()
                .addGroup(panelImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImpuestosLayout.createSequentialGroup()
                        .addContainerGap(621, Short.MAX_VALUE)
                        .addComponent(botonMasImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(botonMenosImp))
                    .addComponent(scrollImpuestos))
                .addContainerGap())
        );

        panelImpuestosLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonMasImp, botonMenosImp});

        panelImpuestosLayout.setVerticalGroup(
            panelImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelImpuestosLayout.createSequentialGroup()
                .addComponent(scrollImpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelImpuestosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonMasImp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(botonMenosImp))
                .addGap(2, 2, 2))
        );

        panelImpuestosLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {botonMasImp, botonMenosImp});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(panelImpuestos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(scrollObs)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(scrollDatos)
                            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, 734, Short.MAX_VALUE))
                        .addGap(7, 7, 7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoGiro)
                            .addComponent(labelMedio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboTipoDTE, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoContacto)
                            .addComponent(textoBanco, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textoOrden, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboRut, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(comboForma, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textoAsunto, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboMedio, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(labelTipoDTE, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelRut, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelBanco, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelContacto, javax.swing.GroupLayout.Alignment.LEADING))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(20, 20, 20)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(labelCantidad, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(textoFolio, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(textoNumTC)
                                    .addComponent(spinnerCantidad)
                                    .addComponent(labelRazon)
                                    .addComponent(labelNumTC)
                                    .addComponent(labelFolio)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addGap(2, 2, 2)
                                        .addComponent(textoDireccion))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textoRazon)
                                            .addComponent(textoFechaIngreso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(textoFechaPago, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(labelNumCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(labelFechaPago)
                                            .addComponent(labelFechaIngreso)
                                            .addComponent(labelDireccion)
                                            .addComponent(spinnerNumCuotas))
                                        .addGap(2, 2, 2))
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(checkEstado)
                                            .addComponent(jLabel1))
                                        .addGap(0, 0, Short.MAX_VALUE)))))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelGiro)
                            .addComponent(labelOrden)
                            .addComponent(labelForma)
                            .addComponent(labelAsunto)
                            .addComponent(labelObs)
                            .addComponent(jLabel2)
                            .addComponent(botonActualizarMontos)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel3)
                                            .addComponent(textoNeto, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel4)
                                            .addComponent(textoIva, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(textoImpuestoEsp, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(jLabel6))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(0, 0, Short.MAX_VALUE))
                                            .addComponent(textoImpuestoVar))))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textoTot, javax.swing.GroupLayout.PREFERRED_SIZE, 229, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel5))))
                        .addContainerGap(34, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(checkImpuestos)
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelTipoDTE)
                    .addComponent(labelFolio))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboTipoDTE, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoFolio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelRut)
                    .addComponent(labelRazon))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboRut, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoRazon, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelGiro)
                    .addComponent(labelDireccion))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoGiro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoDireccion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelFechaIngreso)
                    .addComponent(labelContacto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoFechaIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoContacto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(4, 4, 4)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelOrden)
                    .addComponent(labelFechaPago))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoOrden, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoFechaPago, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelForma)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboForma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(checkEstado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelAsunto)
                    .addComponent(labelNumCuotas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoAsunto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerNumCuotas, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(labelObs)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollObs, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelMedio)
                    .addComponent(labelCantidad))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(comboMedio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(spinnerCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelBanco)
                    .addComponent(labelNumTC))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoBanco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoNumTC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(scrollDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoImpuestoEsp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoImpuestoVar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(checkImpuestos)
                .addGap(18, 18, 18)
                .addComponent(panelImpuestos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(textoNeto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoIva, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(textoTot, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addComponent(botonActualizarMontos)
                .addGap(31, 31, 31))
        );

        jScrollPane1.setViewportView(jPanel1);

        botonAceptar.setText("Aceptar");
        botonAceptar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonAceptarActionPerformed(evt);
            }
        });

        botonCancelar.setText("Cancelar");
        botonCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                botonCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(589, Short.MAX_VALUE)
                .addComponent(botonAceptar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonCancelar)
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 759, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {botonAceptar, botonCancelar});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(508, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonCancelar)
                    .addComponent(botonAceptar))
                .addContainerGap())
            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel2Layout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 464, Short.MAX_VALUE)
                    .addGap(78, 78, 78)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void comboMedioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboMedioActionPerformed
        switch(comboMedio.getSelectedIndex()){
            case 1:
                showTransferencia();
                break;
            case 2:
                showCheques();
                break;
            case 3:
                showTC();
                break;
            default:
                hideMedioPago();
        }        
    }//GEN-LAST:event_comboMedioActionPerformed

    private void spinnerCantidadStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_spinnerCantidadStateChanged
        if(comboMedio.getSelectedIndex() == 2){
            cheques.setRowCount(Integer.parseInt(spinnerCantidad.getValue().toString()));
            for(int i = 0; i < Integer.parseInt(spinnerCantidad.getValue().toString()); i++){
                cheques.setValueAt(i+1, i, 0);
            }
            tablaDatos.setModel(cheques);
        }else{
            tc.setRowCount(Integer.parseInt(spinnerCantidad.getValue().toString()));
            for(int i = 0; i < Integer.parseInt(spinnerCantidad.getValue().toString()); i++){
                tc.setValueAt(i+1, i, 0);
            }
            tablaDatos.setModel(tc);
        }
    }//GEN-LAST:event_spinnerCantidadStateChanged

    private void comboFormaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comboFormaActionPerformed
        if(comboForma.getSelectedIndex() == 4){
            showOtrosPagos();
        }else{
            hideOtrosPagos();
        }
    }//GEN-LAST:event_comboFormaActionPerformed

    private void botonMasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMasActionPerformed
        detalles.setRowCount(detalles.getRowCount()+1);
        tablaDetalle.setModel(detalles);
    }//GEN-LAST:event_botonMasActionPerformed

    private void botonMenosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMenosActionPerformed
        if(detalles.getRowCount() > 0){
            detalles.removeRow(detalles.getRowCount()-1);
            //detalles.setRowCount(detalles.getRowCount()-1);
            tablaDetalle.setModel(detalles);
        }  
    }//GEN-LAST:event_botonMenosActionPerformed

    private void botonCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonCancelarActionPerformed
        dispose();
    }//GEN-LAST:event_botonCancelarActionPerformed

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
        controladorIngresarCompras miControladorIC = new controladorIngresarCompras();
        String respuesta = miControladorIC.camposVacios();
        boolean esVacio = respuesta.length() == 0;
        if (!esVacio) {
            JOptionPane.showMessageDialog(this, respuesta, "Debe rellenar los siguientes campos", JOptionPane.INFORMATION_MESSAGE);
        } else {            
            if (miControladorIC.irVistaComprasP()) {
                JOptionPane.showMessageDialog(this, "Compra ingresada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
                setVisible(false);
            }            
        }
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void botonActualizarMontosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonActualizarMontosActionPerformed
        calTotales();
    }//GEN-LAST:event_botonActualizarMontosActionPerformed

    private void textoImpuestoEspFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoImpuestoEspFocusLost
        setTextoImpuestoEsp(getTextoImpuestoEsp());
    }//GEN-LAST:event_textoImpuestoEspFocusLost

    private void textoImpuestoVarFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_textoImpuestoVarFocusLost
        setTextoImpuestoVar(getTextoImpuestoVar());
    }//GEN-LAST:event_textoImpuestoVarFocusLost

    private void checkImpuestosStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_checkImpuestosStateChanged
        if(checkImpuestos.isSelected()){
            tablaImpuestos.setEnabled(true);
            botonMasImp.setEnabled(true);
            botonMenosImp.setEnabled(true);
        }else{
            tablaImpuestos.setEnabled(false);
            botonMasImp.setEnabled(false);
            botonMenosImp.setEnabled(false);
        }
    }//GEN-LAST:event_checkImpuestosStateChanged

    private void botonMasImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMasImpActionPerformed
        impuestos.setRowCount(impuestos.getRowCount()+1);
        tablaImpuestos.setModel(impuestos);
    }//GEN-LAST:event_botonMasImpActionPerformed

    private void botonMenosImpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonMenosImpActionPerformed
        if(impuestos.getRowCount() > 0){
            impuestos.removeRow(impuestos.getRowCount()-1);
            //detalles.setRowCount(detalles.getRowCount()-1);
            tablaImpuestos.setModel(impuestos);
        }  
    }//GEN-LAST:event_botonMenosImpActionPerformed

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
            java.util.logging.Logger.getLogger(vistaIngresarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarCompras.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton botonAceptar;
    private javax.swing.JButton botonActualizarMontos;
    private javax.swing.JButton botonCancelar;
    private javax.swing.JButton botonMas;
    private javax.swing.JButton botonMasImp;
    private javax.swing.JButton botonMenos;
    private javax.swing.JButton botonMenosImp;
    private javax.swing.JCheckBox checkEstado;
    private javax.swing.JCheckBox checkImpuestos;
    private javax.swing.JComboBox comboForma;
    private javax.swing.JComboBox comboMedio;
    private javax.swing.JComboBox comboRut;
    private javax.swing.JComboBox comboTipoDTE;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel labelAsunto;
    private javax.swing.JLabel labelBanco;
    private javax.swing.JLabel labelCantidad;
    private javax.swing.JLabel labelContacto;
    private javax.swing.JLabel labelDireccion;
    private javax.swing.JLabel labelFechaIngreso;
    private javax.swing.JLabel labelFechaPago;
    private javax.swing.JLabel labelFolio;
    private javax.swing.JLabel labelForma;
    private javax.swing.JLabel labelGiro;
    private javax.swing.JLabel labelMedio;
    private javax.swing.JLabel labelNumCuotas;
    private javax.swing.JLabel labelNumTC;
    private javax.swing.JLabel labelObs;
    private javax.swing.JLabel labelOrden;
    private javax.swing.JLabel labelRazon;
    private javax.swing.JLabel labelRut;
    private javax.swing.JLabel labelTipoDTE;
    private javax.swing.JPanel panelImpuestos;
    private javax.swing.JScrollPane scrollDatos;
    private javax.swing.JScrollPane scrollImpuestos;
    private javax.swing.JScrollPane scrollObs;
    private javax.swing.JSpinner spinnerCantidad;
    private javax.swing.JSpinner spinnerNumCuotas;
    private javax.swing.JTable tablaDatos;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaImpuestos;
    private javax.swing.JTextField textoAsunto;
    private javax.swing.JTextField textoBanco;
    private javax.swing.JTextField textoContacto;
    private javax.swing.JTextField textoDireccion;
    private org.jdesktop.swingx.JXDatePicker textoFechaIngreso;
    private org.jdesktop.swingx.JXDatePicker textoFechaPago;
    private javax.swing.JTextField textoFolio;
    private javax.swing.JTextField textoGiro;
    private javax.swing.JTextField textoImpuestoEsp;
    private javax.swing.JTextField textoImpuestoVar;
    private javax.swing.JFormattedTextField textoIva;
    private javax.swing.JFormattedTextField textoNeto;
    private javax.swing.JTextField textoNumTC;
    private javax.swing.JTextArea textoObs;
    private javax.swing.JTextField textoOrden;
    private javax.swing.JTextField textoRazon;
    private javax.swing.JFormattedTextField textoTot;
    // End of variables declaration//GEN-END:variables

    
    
    final public void hideOtrosPagos(){
        labelAsunto.setVisible(false);
        labelNumCuotas.setVisible(false);
        labelObs.setVisible(false);
        textoAsunto.setVisible(false);
        spinnerNumCuotas.setVisible(false);
        scrollObs.setVisible(false);
    }

    final public void showOtrosPagos(){
        labelAsunto.setVisible(true);
        labelNumCuotas.setVisible(true);
        labelObs.setVisible(true);
        textoAsunto.setVisible(true);
        spinnerNumCuotas.setVisible(true);
        scrollObs.setVisible(true);
    }
    
    final public void hideMedioPago(){
        checkEstado.setEnabled(true);
        labelCantidad.setVisible(false);
        spinnerCantidad.setVisible(false);
        labelBanco.setVisible(false);
        textoBanco.setVisible(false);
        labelNumTC.setVisible(false);
        textoNumTC.setVisible(false);
        scrollDatos.setVisible(false);
    }
    
    final public void showTransferencia(){
        checkEstado.setEnabled(false);
        labelCantidad.setText("Número de cuotas");
        labelNumTC.setText("Número de cuenta");
        labelCantidad.setVisible(true);
        spinnerCantidad.setVisible(true);
        tc.setRowCount(Integer.parseInt(spinnerCantidad.getValue().toString()));
        for(int i = 0; i < Integer.parseInt(spinnerCantidad.getValue().toString()); i++){
            tc.setValueAt(i+1, i, 0);
        }
        
        tablaDatos.setModel(tc);
        TableColumn column = tablaDatos.getColumnModel().getColumn(1);
        column.setCellEditor(new DatePickerCellEditor());
        tablaDatos.getColumnModel().getColumn(2).setCellRenderer(new CurrencyTableCellRenderer());
        scrollDatos.setVisible(true);
        spinnerCantidad.setValue(spinnerNumCuotas.getValue());
        labelBanco.setVisible(true);
        textoBanco.setVisible(true);
        labelNumTC.setVisible(true);
        textoNumTC.setVisible(true);
    }
    
    final public void showCheques(){
        checkEstado.setEnabled(false);
        labelCantidad.setText("Cantidad de cheques");
        labelCantidad.setVisible(true);
        spinnerCantidad.setVisible(true);
        cheques.setRowCount(Integer.parseInt(spinnerCantidad.getValue().toString()));
        for(int i = 0; i < Integer.parseInt(spinnerCantidad.getValue().toString()); i++){
            cheques.setValueAt(i+1, i, 0);
        }
        tablaDatos.setModel(cheques);
        TableColumn column = tablaDatos.getColumnModel().getColumn(2);
        column.setCellEditor(new DatePickerCellEditor());
        tablaDatos.getColumnModel().getColumn(3).setCellRenderer(new CurrencyTableCellRenderer());
        scrollDatos.setVisible(true);
        spinnerCantidad.setValue(spinnerNumCuotas.getValue());
        labelBanco.setVisible(true);
        textoBanco.setVisible(true);
        labelNumTC.setVisible(false);
        textoNumTC.setVisible(false);
    }
    
    final public void showTC(){
        checkEstado.setEnabled(false);
        labelCantidad.setText("Número de cuotas");
        labelNumTC.setText("Número de tarjeta de crédito");
        labelCantidad.setVisible(true);
        spinnerCantidad.setVisible(true);
        tc.setRowCount(Integer.parseInt(spinnerCantidad.getValue().toString()));
        for(int i = 0; i < Integer.parseInt(spinnerCantidad.getValue().toString()); i++){
            tc.setValueAt(i+1, i, 0);
        }
        
        tablaDatos.setModel(tc);
        TableColumn column = tablaDatos.getColumnModel().getColumn(1);
        column.setCellEditor(new DatePickerCellEditor());
        tablaDatos.getColumnModel().getColumn(2).setCellRenderer(new CurrencyTableCellRenderer());
        scrollDatos.setVisible(true);
        spinnerCantidad.setValue(spinnerNumCuotas.getValue());
        labelBanco.setVisible(true);
        textoBanco.setVisible(true);
        labelNumTC.setVisible(true);
        textoNumTC.setVisible(true);
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
    
    public final void autosuggestProveedores(Object[] data){
            comboRut.removeAllItems();
            if(comboRut.getItemCount() == 0){
                for (Object data1 : data) {
                    comboRut.addItem(data1);
                    listProveedores.add((String) data1);
                    comboRut.addItemListener(new ItemListener() {

                        @Override
                        public void itemStateChanged(ItemEvent ie) {
                            if(ie.getStateChange() == ItemEvent.SELECTED){
                                comboRut.getSelectedIndex();
                            }
                        }
                    });
                }
            }else{
                comboRut.addItem("Error");
            }

        comboRut.setEditable(true);
        textoRut.addKeyListener(new KeyAdapter(){
            @Override
            public void keyTyped(KeyEvent e){
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        String text = textoRut.getText();
                        if(text.length() == 0){
                            comboRut.hidePopup();
                            setModel(new DefaultComboBoxModel(listProveedores.toArray()), "", comboRut, textoRut);
                        }else{
                            DefaultComboBoxModel m = getSuggestedModel(listProveedores, text);
                            if(m.getSize() == 0){
                                comboRut.hidePopup();
                            }else{
                                setModel(m, text, comboRut, textoRut);
                                comboRut.showPopup();
                            }
                        }
                    }
                });
            }

            @Override
            public void keyPressed(KeyEvent e){
                String text = textoRut.getText();
                int code = e.getKeyCode();
                if(code == KeyEvent.VK_ENTER){
                    for (int i = 0; i < listProveedores.size(); i++) {
                        String str = (String)listProveedores.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoRut.setText(str);
                            return;
                        }
                    }
                }else if(code == KeyEvent.VK_ESCAPE){
                    hide_flag = true;
                }else if(code == KeyEvent.VK_RIGHT){
                    for (int i = 0; i < listProveedores.size(); i++) {
                        String str = (String)listProveedores.get(i);
                        if(str.toLowerCase().contains(text.toLowerCase())){
                            textoRut.setText(str);
                            return;
                        }
                    }
                }
            }
        });
    }
    
    public class MyTableModel extends DefaultTableModel{
        public MyTableModel() {
          super(new String[]{"Código del producto", "Detalle", "Cantidad", "Precio Unitario", "Iva"}, 0);
        }

        @Override
        public Class getColumnClass(int column) {
          switch (column) {
            case 0:
                return String.class;
            case 1: 
                return String.class;
            case 2:
                return Double.class;
            case 3: 
                return Double.class;
            default:
                return Boolean.class;
          }
        }
        @Override
        public Object getValueAt(int row, int col){
            if(col == 4 && super.getValueAt(row, col) == null){
                return Boolean.FALSE;
            }
            return super.getValueAt(row, col);
        }
    }
    
    public class MyTableModelImpuestos extends DefaultTableModel{
        public MyTableModelImpuestos() {
          super(new String[]{"Nombre del impuestos", "Monto"}, 0);
        }

        @Override
        public Class getColumnClass(int column) {
          switch (column) {
            case 0:
                return String.class;
            default:
                return Integer.class;
          }
        }
    }

    public class MyTableModelCheques extends DefaultTableModel{
        public MyTableModelCheques() {
          super(new String[]{"Cheque", "Folio", "Fecha vencimiento", "Monto", "Estado"}, 0);
        }

        @Override
        public Class getColumnClass(int column) {
          switch (column) {
            case 0:
                return Integer.class;
            case 1: 
                return Integer.class;
            case 2:
                return Date.class;
            case 3: 
                return Integer.class;
            default:
                return Boolean.class;
          }
        }
        
        @Override
        public Object getValueAt(int row, int col){
            if(col == 4 && super.getValueAt(row, col) == null){
                return Boolean.FALSE;
            }
            return super.getValueAt(row, col);
        }
    }
    
    public class CurrencyTableCellRenderer extends DefaultTableCellRenderer {
        @Override
        public final Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
                final Component result = super.getTableCellRendererComponent(table, value,
                    isSelected, hasFocus, row, column);
                if (value instanceof Number) {
                    setHorizontalAlignment(JLabel.RIGHT);
                    setText(FORMAT.format(value));
                } else {
                    setText("");
                }
                return result;
            }
    }
    
    public class MyTableModelTC extends DefaultTableModel{
        public MyTableModelTC() {
          super(new String[]{"Cuota", "Fecha vencimiento", "Monto", "Estado"}, 0);
        }

        @Override
        public Class getColumnClass(int column) {
          switch (column) {
            case 0:
                return Integer.class;
            case 1: 
                return Date.class;
            case 2:
                return Integer.class;
            default:
                return Boolean.class;
          }
        }
        
        @Override
        public Object getValueAt(int row, int col){
            if(col == 3 && super.getValueAt(row, col) == null){
                return Boolean.FALSE;
            }
            return super.getValueAt(row, col);
        }
    }
    
    public void setTextoContacto(String textoContacto) {
        this.textoContacto.setText(textoContacto);
    }

    public void setTextoDireccion(String textoDireccion) {
        this.textoDireccion.setText(textoDireccion);
    }

    public void setTextoGiro(String textoGiro) {
        this.textoGiro.setText(textoGiro);
    }

    public void setTextoRazon(String textoRazon) {
        this.textoRazon.setText(textoRazon);
    }

    public String getComboForma() {
        return comboForma.getSelectedItem().toString();
    }

    public String getComboMedio() {
        return comboMedio.getSelectedItem().toString();
    }

    public String getComboRut() {
        return comboRut.getSelectedItem().toString();
    }

    public String getComboTipoDTE() {
        return comboTipoDTE.getSelectedItem().toString();
    }

    public String getTextoAsunto() {
        if(comboForma.getSelectedIndex() == 4){
            return textoAsunto.getText();
        }else{
            return "";
        }
    }

    public String getTextoBanco() {
        return textoBanco.getText();
    }
    
    public String getTextoNumTC(){
        return textoNumTC.getText();
    }

    public String getTextoFechaIngreso() {
        Date fecha = textoFechaIngreso.getDate();
        if (fecha == null) return "";
        String dateString = formatDate.format(textoFechaIngreso.getDate());
        return dateString;
    }

    public String getTextoFechaPago() {
        Date fecha = textoFechaPago.getDate();
        if (fecha == null)  return "";
        String dateString = formatDate.format(textoFechaPago.getDate());
        return dateString;
    }

    public String getTextoFolio() {
        return textoFolio.getText();
    }

    public String getTextoObs() {
        if(comboForma.getSelectedIndex() == 4){
            return textoObs.getText();
        }else{
            return "";
        }
    }
    
    public String getComboClas(){
        return jComboBox1.getSelectedItem().toString();
    }

    public String getTextoOrden() {
        return textoOrden.getText();
    }
    
    public int getSpinnerCant(){
        return Integer.parseInt(spinnerCantidad.getValue().toString());
    }
    
    public String getTextoTot(){
        return textoTot.getValue().toString();
    }
    
    public String getTextoIva(){
        return textoIva.getValue().toString();
    }
 
    public String getTextoNeto(){
        return textoNeto.getValue().toString();
    }
    
    public int getCuotas(){
        switch(comboMedio.getSelectedIndex()){
            case 1:
                return 3;
            case 2:
                return 1;
            case 3:
                return 2;
            default:
                return 0;
        }
    }
    
    public JTable getTablaCuotas(){
        return tablaDatos;
    }
    
    public JTable getTablaDetalle(){
        return tablaDetalle;
    }
    
    public JTable getTablaImpuestos(){
        return tablaImpuestos;
    }
    
    public String getEstadoTablaCheques(int row){
        if((Boolean)tablaDatos.getValueAt(row, 4) == false){
            return "No pagado";
        }else{
            return "Pagado";
        }
    }
    
    public String getEstadoTablaTC(int row){
        if((Boolean)tablaDatos.getValueAt(row, 3) == false){
            return "No pagado";
        }else{
            return "Pagado";
        }
    }
    
    public String getfechaTablaCheques(int row){
        Date fecha = (Date)tablaDatos.getValueAt(row, 2);
        if (fecha == null) return "";
        String dateString = formatDate.format((Date)tablaDatos.getValueAt(row, 2));
        return dateString;
    }
    
    public String getfechaTablaTC(int row){
        Date fecha = (Date)tablaDatos.getValueAt(row, 1);
        if (fecha == null) return "";
        String dateString = formatDate.format((Date)tablaDatos.getValueAt(row, 1));
        return dateString;
    }
    
    public String getIva(int row){
        if((Boolean)tablaDetalle.getValueAt(row, 4) == false){
            return "Sin";
        }else{
            return "Con";
        }
    }
    
    public void setTextoImpuestoEsp(String val){
        try{
            Object value = Integer.parseInt(val);
            if (value instanceof Number) {
                textoImpuestoEsp.setHorizontalAlignment(JLabel.RIGHT);
                textoImpuestoEsp.setText(FORMAT.format(value));
            } else {
                textoImpuestoEsp.setHorizontalAlignment(JLabel.RIGHT);
                textoImpuestoEsp.setText("");
                textoImpuestoEsp.setText(FORMAT.format(value));
            }
        }catch(NumberFormatException e){
            textoImpuestoEsp.setHorizontalAlignment(JLabel.RIGHT);
            textoImpuestoEsp.setText("");
        }
    }
    
    public String getTextoImpuestoEsp(){
        String imp = textoImpuestoEsp.getText().replace("$", "");
        imp = imp.replace(".", "");
        if(imp.compareTo("") == 0){
            return "0";
        }
        return imp;
    }
    
    public void setTextoImpuestoVar(String val){
        try{
            Object value = Integer.parseInt(val);
            if (value instanceof Number) {
                textoImpuestoVar.setHorizontalAlignment(JLabel.RIGHT);
                textoImpuestoVar.setText(FORMAT.format(value));
            } else {
                textoImpuestoVar.setHorizontalAlignment(JLabel.RIGHT);
                textoImpuestoVar.setText("");
            }
        }catch(NumberFormatException e){
            textoImpuestoVar.setHorizontalAlignment(JLabel.RIGHT);
            textoImpuestoVar.setText("");
        }
    }
    
    public String getTextoImpuestoVar(){
        String imp = textoImpuestoVar.getText().replace("$", "");
        imp = imp.replace(".", "");
        if(imp.compareTo("") == 0){
            return "0";
        }
        return imp;
    }
    
    public boolean checkTablaDatos(){
        int rows = tablaDatos.getRowCount(), cols = tablaDatos.getColumnCount() - 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(tablaDatos.getValueAt(i, j) == null) return false;
            }
        }
        return true;
    }
    
    public boolean checkTablaDetalles(){
        int rows = tablaDetalle.getRowCount(), cols = tablaDetalle.getColumnCount() - 1;
        for(int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){
                if(tablaDetalle.getValueAt(i, j) == null) return false;
            }
        }
        return true;
    }
    
    public String getCheckEstado(){
        if(!checkEstado.isEnabled()) return "No pagado";
        if(checkEstado.isSelected()) return "Pagado";
        return "No pagado";
    }
    
    public void calTotales(){
        int tot = 0;
        int net = 0;
        int iva = 0;
        int impEs = Integer.parseInt(getTextoImpuestoEsp());
        int impVar = Integer.parseInt(getTextoImpuestoVar());
        int impOtros = 0;
        try{
            for(int i = 0; i < tablaDetalle.getRowCount(); i++){
                if(tablaDetalle.getValueAt(i, 4) == Boolean.FALSE){
                    /* SE CAMBIO DOUBLE Y DOUBLE EN LA V4 Y SE CASTEO A INT*/
                    int totParcial = (int) (Double.valueOf(tablaDetalle.getValueAt(i, 2).toString()) * 
                            Double.valueOf(tablaDetalle.getValueAt(i, 3).toString()));
                    tot += totParcial;
                    net += totParcial;
                }else{
                    int ivaParcial = 0;
                    int totParcial = (int)(Double.valueOf(tablaDetalle.getValueAt(i, 2).toString()) * 
                            Double.valueOf(tablaDetalle.getValueAt(i, 3).toString()));
                    double ivaFloat = totParcial * 0.19;
                    ivaParcial = (int)Math.round(ivaFloat);
                    net += totParcial;
                    tot += totParcial + ivaParcial;
                    iva += ivaParcial;
                }
            }
            if(checkImpuestos.isSelected()){
                for(int i = 0; i < tablaImpuestos.getRowCount(); i++){
                    impOtros += Integer.valueOf(tablaImpuestos.getValueAt(i, 1).toString());
                }
            }
            net += impEs + impVar + impOtros;
        }catch(NullPointerException e){
            
        }
        textoNeto.setValue(net);
        textoIva.setValue(iva);
        textoTot.setValue(tot);
    }
    
    public JXDatePicker getFechaCompra(){
        return textoFechaIngreso;
    }
}
