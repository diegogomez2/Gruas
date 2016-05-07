/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.controladorPrincipal;
import java.awt.PopupMenu;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author Diego
 */
public class vistaPrincipal extends javax.swing.JFrame {
    //private JPanel vistaClientesP;

    /**
     * Creates new form vistaPrincipal
     */
    public vistaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        //vistaClientesP tabClientes = new vistaClientesP();
        //vistaPrincipal.addTab("Clientes", tabClientes);
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Clientes", miControlador.crearControladorClientesP());
        vistaPrincipal.addTab("Grúas", miControlador.crearControladorGruasP());
        vistaPrincipal.addTab("Empleados", miControlador.crearControladorEmpleadosP());
        vistaPrincipal.addTab("Jornada Diaria", miControlador.crearControladorJornadaP());
        vistaPrincipal.addTab("OTs", miControlador.crearControladorOtsP());
        vistaPrincipal.addTab("A Facturar", miControlador.crearControladorFacturasP());
        vistaPrincipal.addTab("Facturadas", miControlador.crearControladorFacturadasP());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        vistaPrincipal = new javax.swing.JTabbedPane();
        MenuPrincipal = new javax.swing.JMenuBar();
        MenuArchivo = new javax.swing.JMenu();
        MenuUsuarios = new javax.swing.JMenu();
        MenuCambioClave = new javax.swing.JMenuItem();
        MenuControlUsuarios = new javax.swing.JMenuItem();
        MenuAgenda = new javax.swing.JMenu();
        MenuJornadaDiaria = new javax.swing.JMenuItem();
        MenuGestionAgendas = new javax.swing.JMenuItem();
        MenuCaja = new javax.swing.JMenu();
        MenuCobranza = new javax.swing.JMenu();
        MenuFacturacion = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        MenuMantenciones = new javax.swing.JMenu();
        MenuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        MenuArchivo.setText("Archivo");

        MenuUsuarios.setText("Usuarios");

        MenuCambioClave.setText("Cambio de clave");
        MenuCambioClave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCambioClaveActionPerformed(evt);
            }
        });
        MenuUsuarios.add(MenuCambioClave);

        MenuControlUsuarios.setText("Control de usuarios");
        MenuUsuarios.add(MenuControlUsuarios);

        MenuArchivo.add(MenuUsuarios);

        MenuAgenda.setText("Agenda");

        MenuJornadaDiaria.setText("Jornada diaria");
        MenuAgenda.add(MenuJornadaDiaria);

        MenuGestionAgendas.setText("Gestión de agendas");
        MenuAgenda.add(MenuGestionAgendas);

        MenuArchivo.add(MenuAgenda);

        MenuPrincipal.add(MenuArchivo);

        MenuCaja.setText("Caja");
        MenuPrincipal.add(MenuCaja);

        MenuCobranza.setText("Cobranza");
        MenuPrincipal.add(MenuCobranza);

        MenuFacturacion.setText("Facturación");

        jMenuItem1.setText("OT");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        MenuFacturacion.add(jMenuItem1);

        MenuPrincipal.add(MenuFacturacion);

        MenuMantenciones.setText("Mantenciones");
        MenuPrincipal.add(MenuMantenciones);

        MenuAyuda.setText("Ayuda");
        MenuPrincipal.add(MenuAyuda);

        setJMenuBar(MenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 548, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 264, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        this.setVisible(false);
        controladores.controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorOtsP();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void MenuCambioClaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCambioClaveActionPerformed
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        miControlador.crearControladorUsuarios();
    }//GEN-LAST:event_MenuCambioClaveActionPerformed

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
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(vistaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new vistaPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenu MenuAgenda;
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JMenu MenuAyuda;
    private javax.swing.JMenu MenuCaja;
    private javax.swing.JMenuItem MenuCambioClave;
    private javax.swing.JMenu MenuCobranza;
    private javax.swing.JMenuItem MenuControlUsuarios;
    private javax.swing.JMenu MenuFacturacion;
    private javax.swing.JMenuItem MenuGestionAgendas;
    private javax.swing.JMenuItem MenuJornadaDiaria;
    private javax.swing.JMenu MenuMantenciones;
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JMenu MenuUsuarios;
    private javax.swing.JMenuItem jMenuItem1;
    public javax.swing.JTabbedPane vistaPrincipal;
    // End of variables declaration//GEN-END:variables

    public JTabbedPane getVistaPrincipal() {
        return vistaPrincipal;
    }
    
    public void agregartab(String nom){
        vistaPrincipal.addTab(nom, new JPanel());
    }
    
}

