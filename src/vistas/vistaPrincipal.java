/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas;

import controladores.controladorPrincipal;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.xml.transform.TransformerException;
import org.apache.fop.apps.FOPException;
import org.jdesktop.swingx.JXDatePicker;

/**
 *
 * @author Diego
 */
public class vistaPrincipal extends javax.swing.JFrame {
    //private JPanel vistaClientesP;

    /**
     * Creates new form vistaPrincipal
     */
    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    
    public vistaPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Clientes", miControlador.crearControladorClientesP());
        vistaPrincipal.addTab("Grúas", miControlador.crearControladorGruasP());
        vistaPrincipal.addTab("Trabajadores", miControlador.crearControladorEmpleadosP());
        vistaPrincipal.addTab("Jornada diaria", miControlador.crearControladorJornadaP());
        vistaPrincipal.addTab("OTs", miControlador.crearControladorOtsP());
        vistaPrincipal.addTab("A facturar", miControlador.crearControladorFacturasP());
        vistaPrincipal.addTab("Facturadas", miControlador.crearControladorFacturadasP());
        vistaPrincipal.addTab("Histórico", miControlador.crearControladorHistoricoP());
        //vistaPrincipal.addTab("Usuarios", miControlador.crearControladorUsuariosP());
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
        itemAgregarUsuario = new javax.swing.JMenuItem();
        itemCambiarContraseña = new javax.swing.JMenuItem();
        itemUsuarios = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        menuTarifas = new javax.swing.JMenuItem();
        menuAgregarTarifa = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        menuOperaciones = new javax.swing.JMenu();
        itemOperaciones = new javax.swing.JMenuItem();
        menuCobranza = new javax.swing.JMenu();
        itemCobranzas = new javax.swing.JMenuItem();
        itemGenerarReporte = new javax.swing.JMenuItem();
        menuCompras = new javax.swing.JMenu();
        itemCompras = new javax.swing.JMenuItem();
        menuLibros = new javax.swing.JMenu();
        itemLibroCompras = new javax.swing.JMenuItem();
        itemLibroVentas = new javax.swing.JMenuItem();
        itemLibroAtrasado = new javax.swing.JMenuItem();
        menuRemuneraciones = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        menuOpciones = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        menuOC = new javax.swing.JMenu();
        menuOc = new javax.swing.JMenuItem();
        menuExportar = new javax.swing.JMenu();
        menuExportarClientes = new javax.swing.JMenuItem();
        menuExportarGruas = new javax.swing.JMenuItem();
        menuExportarHistoricoOts = new javax.swing.JMenuItem();
        menuExportarFacturas = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Menu Principal");
        setAutoRequestFocus(false);
        setMinimumSize(new java.awt.Dimension(900, 700));

        MenuArchivo.setText("Archivo");
        MenuArchivo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuArchivoActionPerformed(evt);
            }
        });

        itemAgregarUsuario.setText("Agregar usuario");
        itemAgregarUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAgregarUsuarioActionPerformed(evt);
            }
        });
        MenuArchivo.add(itemAgregarUsuario);

        itemCambiarContraseña.setText("Cambiar contraseña");
        itemCambiarContraseña.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCambiarContraseñaActionPerformed(evt);
            }
        });
        MenuArchivo.add(itemCambiarContraseña);

        itemUsuarios.setText("Mostrar usuarios");
        itemUsuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemUsuariosActionPerformed(evt);
            }
        });
        MenuArchivo.add(itemUsuarios);

        MenuPrincipal.add(MenuArchivo);

        jMenu1.setText("Tarifas");

        menuTarifas.setText("Ver tarifas");
        menuTarifas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuTarifasActionPerformed(evt);
            }
        });
        jMenu1.add(menuTarifas);

        menuAgregarTarifa.setText("Agregar Tarifa");
        menuAgregarTarifa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuAgregarTarifaActionPerformed(evt);
            }
        });
        jMenu1.add(menuAgregarTarifa);

        jMenuItem4.setText("Tonelajes/Bono300");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem4);

        MenuPrincipal.add(jMenu1);

        menuOperaciones.setText("Operaciones y Servicios");
        menuOperaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOperacionesActionPerformed(evt);
            }
        });

        itemOperaciones.setText("Mostrar operaciones y servicios");
        itemOperaciones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemOperacionesActionPerformed(evt);
            }
        });
        menuOperaciones.add(itemOperaciones);

        MenuPrincipal.add(menuOperaciones);

        menuCobranza.setText("Cobranzas");

        itemCobranzas.setText("Mostrar cobranzas");
        itemCobranzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCobranzasActionPerformed(evt);
            }
        });
        menuCobranza.add(itemCobranzas);

        itemGenerarReporte.setText("Generar reporte");
        itemGenerarReporte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemGenerarReporteActionPerformed(evt);
            }
        });
        menuCobranza.add(itemGenerarReporte);

        MenuPrincipal.add(menuCobranza);

        menuCompras.setText("Compras");

        itemCompras.setText("Mostrar compras");
        itemCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemComprasActionPerformed(evt);
            }
        });
        menuCompras.add(itemCompras);

        MenuPrincipal.add(menuCompras);

        menuLibros.setText("Libros");

        itemLibroCompras.setText("Generar libro de compras");
        itemLibroCompras.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLibroComprasActionPerformed(evt);
            }
        });
        menuLibros.add(itemLibroCompras);

        itemLibroVentas.setText("Generar libro de ventas");
        itemLibroVentas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLibroVentasActionPerformed(evt);
            }
        });
        menuLibros.add(itemLibroVentas);

        itemLibroAtrasado.setText("Generar libro atrasado");
        itemLibroAtrasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemLibroAtrasadoActionPerformed(evt);
            }
        });
        menuLibros.add(itemLibroAtrasado);

        MenuPrincipal.add(menuLibros);

        menuRemuneraciones.setText("Remuneraciones");

        jMenuItem1.setText("Editar sueldos");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        menuRemuneraciones.add(jMenuItem1);

        jMenuItem2.setText("Generar liquidaciones de sueldo");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        menuRemuneraciones.add(jMenuItem2);

        jMenuItem3.setText("Cambiar valor UTM");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        menuRemuneraciones.add(jMenuItem3);

        jMenuItem7.setText("Cambiar valor UF");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        menuRemuneraciones.add(jMenuItem7);

        jMenuItem5.setText("Generar reportes trabajadores");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        menuRemuneraciones.add(jMenuItem5);

        MenuPrincipal.add(menuRemuneraciones);

        menuOpciones.setText("Opciones");

        jMenuItem6.setText("Cambiar ruta");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        menuOpciones.add(jMenuItem6);

        MenuPrincipal.add(menuOpciones);

        menuOC.setText("OC");

        menuOc.setText("Mostrar clientes por OC");
        menuOc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuOcActionPerformed(evt);
            }
        });
        menuOC.add(menuOc);

        MenuPrincipal.add(menuOC);

        menuExportar.setText("Exportar");

        menuExportarClientes.setText("Generar reporte clientes");
        menuExportarClientes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExportarClientesActionPerformed(evt);
            }
        });
        menuExportar.add(menuExportarClientes);

        menuExportarGruas.setText("Generar reporte grúas");
        menuExportarGruas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExportarGruasActionPerformed(evt);
            }
        });
        menuExportar.add(menuExportarGruas);

        menuExportarHistoricoOts.setText("Generar reporte histórico OT");
        menuExportarHistoricoOts.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExportarHistoricoOtsActionPerformed(evt);
            }
        });
        menuExportar.add(menuExportarHistoricoOts);

        menuExportarFacturas.setText("Generar reporte histórico facturas");
        menuExportarFacturas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuExportarFacturasActionPerformed(evt);
            }
        });
        menuExportar.add(menuExportarFacturas);

        jMenuItem8.setText("Generar reporte compras");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        menuExportar.add(jMenuItem8);

        jMenuItem9.setText("Generar reporte cobranza");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        menuExportar.add(jMenuItem9);

        MenuPrincipal.add(menuExportar);

        setJMenuBar(MenuPrincipal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(vistaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 874, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(vistaPrincipal, javax.swing.GroupLayout.DEFAULT_SIZE, 512, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void itemCambiarContraseñaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCambiarContraseñaActionPerformed
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        miControlador.crearControladorUsuarios();
    }//GEN-LAST:event_itemCambiarContraseñaActionPerformed

    private void menuTarifasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuTarifasActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Usuarios", miControlador.crearControladorUsuariosP());
        vistaPrincipal.addTab("Tarifas", miControlador.crearControladorTarifasP());
        vistaPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_menuTarifasActionPerformed

    private void menuAgregarTarifaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuAgregarTarifaActionPerformed
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        miControlador.crearControladorAgregarTarifa();
    }//GEN-LAST:event_menuAgregarTarifaActionPerformed

    private void itemAgregarUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAgregarUsuarioActionPerformed
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        miControlador.crearControladorAgregarUsuario2();
    }//GEN-LAST:event_itemAgregarUsuarioActionPerformed

    private void MenuArchivoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuArchivoActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Usuarios", miControlador.crearControladorUsuariosP());
        //vistaPrincipal.addTab("Grúas", miControlador.crearControladorGruasP());
    }//GEN-LAST:event_MenuArchivoActionPerformed

    private void itemUsuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemUsuariosActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Usuarios", miControlador.crearControladorUsuariosP());
        vistaPrincipal.addTab("Tarifas", miControlador.crearControladorTarifasP());
    }//GEN-LAST:event_itemUsuariosActionPerformed

    private void menuOperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOperacionesActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Usuarios", miControlador.crearControladorUsuariosP());
        //vistaPrincipal.addTab("Grúas", miControlador.crearControladorGruasP());
    }//GEN-LAST:event_menuOperacionesActionPerformed

    private void itemOperacionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemOperacionesActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Clientes", miControlador.crearControladorClientesP());
        vistaPrincipal.addTab("Grúas", miControlador.crearControladorGruasP());
        vistaPrincipal.addTab("Trabajadores", miControlador.crearControladorEmpleadosP());
        vistaPrincipal.addTab("Jornada diaria", miControlador.crearControladorJornadaP());
        vistaPrincipal.addTab("OTs", miControlador.crearControladorOtsP());
        vistaPrincipal.addTab("A facturar", miControlador.crearControladorFacturasP());
        vistaPrincipal.addTab("Facturadas", miControlador.crearControladorFacturadasP());
        vistaPrincipal.addTab("Histórico", miControlador.crearControladorHistoricoP());
        //vistaPrincipal.addTab("Usuarios", miControlador.crearControladorUsuariosP());
    }//GEN-LAST:event_itemOperacionesActionPerformed

    private void itemComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemComprasActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Proveedores", miControlador.crearControladorProveedoresP());
        vistaPrincipal.addTab("Compras", miControlador.crearControladorComprasP());
        vistaPrincipal.add("Agenda de pagos", miControlador.crearControladorAgendaDePagosP());
        vistaPrincipal.add("Agenda de otros pagos", miControlador.crearControladorAgendaDeOtrosPagosP());
        vistaPrincipal.add("Global de pagos", miControlador.crearControladorGlobalPagosP());
        vistaPrincipal.add("Global otros pagos", miControlador.crearControladorGlobalOtrosPagosP());
    }//GEN-LAST:event_itemComprasActionPerformed

    private void itemCobranzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCobranzasActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Cobranza", miControlador.crearControladorCobranzasP());
    }//GEN-LAST:event_itemCobranzasActionPerformed

    private void itemGenerarReporteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemGenerarReporteActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.generarReporte();
    }//GEN-LAST:event_itemGenerarReporteActionPerformed

    private void itemLibroComprasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLibroComprasActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.generarLibroCompras();
    }//GEN-LAST:event_itemLibroComprasActionPerformed

    private void itemLibroVentasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLibroVentasActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.generarLibroVentas();
    }//GEN-LAST:event_itemLibroVentasActionPerformed

    private void itemLibroAtrasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemLibroAtrasadoActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorGenerarLibroAtrasado();
    }//GEN-LAST:event_itemLibroAtrasadoActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorEditarSueldos();
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        controladorPrincipal miconPrincipal = new controladorPrincipal();
        try {
            int dialogResult = JOptionPane.showOptionDialog(null, "Esta seguro que desea generar las liquidaciones de "
                    + "sueldo", "Confirmar", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, null, 0);
            if(dialogResult == JOptionPane.YES_OPTION){
                miconPrincipal.crearControladorGenerarLiquidaciones(); 
            }
        } catch (TransformerException ex) {
            Logger.getLogger(vistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(vistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        } catch (FOPException ex) {
            Logger.getLogger(vistaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorCambiarUTM();
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorTonelajeBono300();
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.generarReporteTrabajador();
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorCambiarRutas();
    }//GEN-LAST:event_jMenuItem6ActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorCambiarUF();
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void menuOcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuOcActionPerformed
        vistaPrincipal.removeAll();
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        vistaPrincipal.addTab("Jornadas OC", miControlador.crearControladorJornadasOCP());
        vistaPrincipal.addTab("OCs", miControlador.crearControladorOcsP());
        vistaPrincipal.addTab("A facturar", miControlador.crearControladorFacturasOCP());
        vistaPrincipal.addTab("Facturadas", miControlador.crearControladorFacturadasOCP());
        vistaPrincipal.addTab("Histórico", miControlador.crearControladorHistoricoOCP());
    }//GEN-LAST:event_menuOcActionPerformed

    private void menuExportarClientesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExportarClientesActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.generarReporteClientes();
    }//GEN-LAST:event_menuExportarClientesActionPerformed

    private void menuExportarGruasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExportarGruasActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.generarReporteGruas();
    }//GEN-LAST:event_menuExportarGruasActionPerformed

    private void menuExportarHistoricoOtsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExportarHistoricoOtsActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        JXDatePicker fechaIn = new JXDatePicker(new Date());
        JXDatePicker fechaFin = new JXDatePicker(new Date());
        Object[] message = {
          "Fecha de inicio: ", fechaIn,
            "Fecha de término; ", fechaFin,
        };
        int resp = JOptionPane.showConfirmDialog(null, message, "Ingresar fechas.", JOptionPane.OK_CANCEL_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            String fecIn = formatDate.format(fechaIn.getDate());
            String fecFin = formatDate.format(fechaFin.getDate());
            miControlador.generarReporteHistoricoOT(fecIn, fecFin);
        }
    }//GEN-LAST:event_menuExportarHistoricoOtsActionPerformed

    private void menuExportarFacturasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuExportarFacturasActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        JXDatePicker fechaIn = new JXDatePicker(new Date());
        JXDatePicker fechaFin = new JXDatePicker(new Date());
        Object[] message = {
          "Fecha de inicio: ", fechaIn,
            "Fecha de término; ", fechaFin,
        };
        int resp = JOptionPane.showConfirmDialog(null, message, "Ingresar fechas.", JOptionPane.OK_CANCEL_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            String fecIn = formatDate.format(fechaIn.getDate());
            String fecFin = formatDate.format(fechaFin.getDate());
            miControlador.generarReporteHistoricoFacturas(fecIn, fecFin);
        }
    }//GEN-LAST:event_menuExportarFacturasActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        controladorPrincipal miControlador = new controladorPrincipal();
        JXDatePicker fechaIn = new JXDatePicker(new Date());
        JXDatePicker fechaFin = new JXDatePicker(new Date());
        Object[] message = {
          "Fecha de inicio: ", fechaIn,
            "Fecha de término; ", fechaFin,
        };
        int resp = JOptionPane.showConfirmDialog(null, message, "Ingresar fechas.", JOptionPane.OK_CANCEL_OPTION);
        if(resp == JOptionPane.YES_OPTION){
            String fecIn = formatDate.format(fechaIn.getDate());
            String fecFin = formatDate.format(fechaFin.getDate());
            miControlador.generarReporteCompras(fecIn, fecFin);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
//        controladorPrincipal miControlador = new controladorPrincipal();
//        JComboBox estado = new JComboBox(new String[]{"Pagado", "No pagado"});
//        Object[] message = {
//            "Estado: ", estado
//        };
//        int resp = JOptionPane.showConfirmDialog(null, message, "Ingresar estado de cobranza.", JOptionPane.OK_CANCEL_OPTION);
//        if(resp == JOptionPane.YES_OPTION){
//            String est = estado.getSelectedItem().toString();
//            miControlador.generarReporteCobranza(est);
//        }
    }//GEN-LAST:event_jMenuItem9ActionPerformed

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
    private javax.swing.JMenuBar MenuPrincipal;
    private javax.swing.JMenuItem itemAgregarUsuario;
    private javax.swing.JMenuItem itemCambiarContraseña;
    private javax.swing.JMenuItem itemCobranzas;
    private javax.swing.JMenuItem itemCompras;
    private javax.swing.JMenuItem itemGenerarReporte;
    private javax.swing.JMenuItem itemLibroAtrasado;
    private javax.swing.JMenuItem itemLibroCompras;
    private javax.swing.JMenuItem itemLibroVentas;
    private javax.swing.JMenuItem itemOperaciones;
    private javax.swing.JMenuItem itemUsuarios;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JMenuItem menuAgregarTarifa;
    private javax.swing.JMenu menuCobranza;
    private javax.swing.JMenu menuCompras;
    private javax.swing.JMenu menuExportar;
    private javax.swing.JMenuItem menuExportarClientes;
    private javax.swing.JMenuItem menuExportarFacturas;
    private javax.swing.JMenuItem menuExportarGruas;
    private javax.swing.JMenuItem menuExportarHistoricoOts;
    private javax.swing.JMenu menuLibros;
    private javax.swing.JMenu menuOC;
    private javax.swing.JMenuItem menuOc;
    private javax.swing.JMenu menuOpciones;
    private javax.swing.JMenu menuOperaciones;
    private javax.swing.JMenu menuRemuneraciones;
    private javax.swing.JMenuItem menuTarifas;
    public javax.swing.JTabbedPane vistaPrincipal;
    // End of variables declaration//GEN-END:variables

    public JTabbedPane getVistaPrincipal() {
        return vistaPrincipal;
    }
    
    public void agregartab(String nom){
        vistaPrincipal.addTab(nom, new JPanel());
    }
    
}

