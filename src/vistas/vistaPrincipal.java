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
        vistaPrincipal.addTab("Trabajadores", miControlador.crearControladorEmpleadosP());
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
        MenuCobranza = new javax.swing.JMenu();
        MenuFacturacion = new javax.swing.JMenu();
        MenuMantenciones = new javax.swing.JMenu();
        MenuAyuda = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setMinimumSize(new java.awt.Dimension(800, 600));
        setPreferredSize(new java.awt.Dimension(800, 600));

        MenuArchivo.setText("Archivo");
        MenuPrincipal.add(MenuArchivo);

        MenuCobranza.setText("Cobranza");
        MenuPrincipal.add(MenuCobranza);

        MenuFacturacion.setText("Facturación");
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
    private javax.swing.JMenu MenuArchivo;
    private javax.swing.JMenu MenuAyuda;
    private javax.swing.JMenu MenuCobranza;
    private javax.swing.JMenu MenuFacturacion;
    private javax.swing.JMenu MenuMantenciones;
    private javax.swing.JMenuBar MenuPrincipal;
    public javax.swing.JTabbedPane vistaPrincipal;
    // End of variables declaration//GEN-END:variables

    public JTabbedPane getVistaPrincipal() {
        return vistaPrincipal;
    }
    
    public void agregartab(String nom){
        vistaPrincipal.addTab(nom, new JPanel());
    }
    
}

