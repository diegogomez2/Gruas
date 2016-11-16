/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import controladores.controladorPrincipal;
import vistas3.vistaModificarTonelaje;

/**
 *
 * @author diego
 */
public class controladorModificarTonelaje {
    
    static vistas3.vistaModificarTonelaje vistaMT;
    
    public void mostrarVistaModificarTonelaje(String pes){
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerTonelajeBono300PorPeso(pes);
        vistaMT = new vistaModificarTonelaje(new javax.swing.JFrame(), true);
        vistaMT.setTextoTonelaje(data[0]);
        vistaMT.setTextoValor(data[1]);
        vistaMT.setId(data[2]);
        vistaMT.setLocationRelativeTo(null);
        vistaMT.setVisible(true);
    }
    
    public boolean irVistaTonelajesP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] data = {vistaMT.getId(), vistaMT.getTextoTonelaje(), vistaMT.getTextoValor()};
        boolean flag = miControlador.modificarTonelaje(data);
        return flag;
    }
    
    public String camposVacios(){
        String resp = "";
        if(vistaMT.getTextoTonelaje().compareTo("") == 0) resp += "-Tonelaje\n";
        if(vistaMT.getTextoValor().compareTo("") == 0) resp += "-Valor bono 300\n";
        return resp;
    }
}
