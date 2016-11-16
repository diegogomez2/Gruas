/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import vistas3.vistaAgregarTonelaje;

/**
 *
 * @author diego
 */
public class controladorCrearTonelaje {
    static vistas3.vistaAgregarTonelaje vistaAT;

    public void mostrarVistaAgregarTonelaje() {
        vistaAT = new vistaAgregarTonelaje(new javax.swing.JFrame(), true);
        vistaAT.setLocationRelativeTo(null);
        vistaAT.setVisible(true);
    }
    
    public String camposVacios(){
        String resp = "";
        if(vistaAT.getTextoTonelaje().compareTo("") == 0) resp += "-Tonelaje\n";
        if(vistaAT.getTextoValor().compareTo("") == 0) resp += "-Valor bono 300\n";
        return resp;
    }
    
    public boolean irVistaTonelajesP(){
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] data = {vistaAT.getTextoTonelaje(), vistaAT.getTextoValor()};
        boolean flag = miControlador.ingresarTonelaje(data);
        return flag;
    }
}
