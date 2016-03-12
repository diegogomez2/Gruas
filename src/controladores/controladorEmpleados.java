/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import vistas.vistaControlEmpleados;

/**
 *
 * @author Diego
 */
class controladorEmpleados {
    static vistas.vistaControlEmpleados vistaEmpleados;

    public void mostrarVistaControlEmpleados(String tipo, Object[][] data) {
        if(vistaEmpleados != null) vistaEmpleados.setVisible(false);
        vistaEmpleados = new vistaControlEmpleados(tipo, data);
        vistaEmpleados.setVisible(true);
    }
    
}
