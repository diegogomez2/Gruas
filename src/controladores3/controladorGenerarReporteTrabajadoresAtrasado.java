/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import vistas3.vistaGenerarReporteTrabajadoresAtrasado;

/**
 *
 * @author Diego
 */
public class controladorGenerarReporteTrabajadoresAtrasado {

   static vistas3.vistaGenerarReporteTrabajadoresAtrasado vistaGenerarReporteTrabajadoresAtrasado;

    public void mostrarVistaGenerarReporteTrabajadoresAtrasado() {
        if(vistaGenerarReporteTrabajadoresAtrasado != null) vistaGenerarReporteTrabajadoresAtrasado.setVisible(false);
        vistaGenerarReporteTrabajadoresAtrasado = new vistaGenerarReporteTrabajadoresAtrasado();
        vistaGenerarReporteTrabajadoresAtrasado.setVisible(true);
        vistaGenerarReporteTrabajadoresAtrasado.setLocationRelativeTo(null);
    }
    
}
