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
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;

/**
 *
 * @author diego
 */
public class vistaIngresarOC extends javax.swing.JDialog {

    JComboBox comboGruas = new JComboBox();
    JComboBox comboOperadores = new JComboBox();
    JTextField textoClientes = new JTextField();
    JTextField textoOperadores = new JTextField();
    JTextField textoGruas = new JTextField();
    List<String> listClientes = new ArrayList<>();
    List<String> listGruas = new ArrayList<>();
    List<String> listEmpleados = new ArrayList<>();
    private boolean hide_flag = false;
//    MyTableModelHorasBase hb = new MyTableModelHorasBase();
    MyTableModelGruas tg = new MyTableModelGruas();
    /**
     * Creates new form vistaIngresarOC
     */
    public vistaIngresarOC(java.awt.Frame parent, boolean modal, Object[] clientes, Object[] tonelajes, Object[] gruas) {
        super(parent, modal);
        initComponents();
        
        textoClientes = (JTextField)comboClientes.getEditor().getEditorComponent();
        textoGruas = (JTextField)comboGruas.getEditor().getEditorComponent();
        textoOperadores = (JTextField)comboOperadores.getEditor().getEditorComponent();
//        tablaHorasBase.setModel(hb);
        tablaGruas.setModel(tg);
        tablaGruas.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboGruas));
//        hb.setRowCount(tonelajes.length);
        tg.setRowCount(3);
        int i = 0;
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
        autosuggestClientes(clientes);
        autosuggestGruas(gruas);
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
        comboClientes = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaGruas = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSpinner1 = new javax.swing.JSpinner();
        jSpinner2 = new javax.swing.JSpinner();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Ingresar OC");

        jLabel1.setText("Cliente");

        comboClientes.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton1.setText("Cancelar");

        jButton2.setText("Aceptar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

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
        jScrollPane2.setViewportView(tablaGruas);

        jButton3.setText("-");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("+");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel2.setText("Número de grúas");

        jLabel4.setText("Número de trabajadores");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(89, 89, 89)
                        .addComponent(comboClientes, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(40, 40, 40)
                                .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 913, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jButton4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3)))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(comboClientes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jSpinner1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3)
                    .addComponent(jButton4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jSpinner2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 484, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        for(int i = 0 ; i < tablaGruas.getModel().getRowCount(); i++){
        System.out.println(tablaGruas.getValueAt(i,0));
            
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        tg.setRowCount(tg.getRowCount() - 1);
        tablaGruas.setModel(tg);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        tg.setRowCount(tg.getRowCount() + 1);
        tablaGruas.setModel(tg);
    }//GEN-LAST:event_jButton4ActionPerformed

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
            java.util.logging.Logger.getLogger(vistaIngresarOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaIngresarOC.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboClientes;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JTable tablaGruas;
    // End of variables declaration//GEN-END:variables

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
    
    public String getComboCliente() {
        return comboClientes.getSelectedItem().toString();
    }
    
//    public class MyTableModelHorasBase extends DefaultTableModel{
//        public MyTableModelHorasBase() {
//          super(new String[]{"Tonelaje", "Número de horas", "Número de turnos", "Horas base", "Horas adicionales", "Horas extra", "Turnos extraordinarios"}, 0);
//        }
//
//        @Override
//        public Class getColumnClass(int column) {
//          switch (column) {
//            default:
//                return Integer.class;
//          }
//        }
//        
//        @Override
//        public Object getValueAt(int row, int col){
//            return super.getValueAt(row, col);
//        }
//        
//        
//        @Override 
//        public boolean isCellEditable(int row, int col){
//            if(col == 0) return false;
//            return true;
//        }
//    }
    
    public class MyTableModelGruas extends DefaultTableModel{
        public MyTableModelGruas() {
          super(new String[]{"Grua"}, 0);
        }

        @Override
        public Class getColumnClass(int column) {
          switch (column) {
            default:
                return String.class;
          }
        }
        
        @Override
        public Object getValueAt(int row, int col){
            return super.getValueAt(row, col);
        }
        

        
        
//        @Override 
//        public boolean isCellEditable(int row, int col){
//            if(col == 0) return false;
//            return true;
//        }
    }
    
    
}
