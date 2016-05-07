/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import static controladores.controladorIngresarGruas.vistaIG;
import vistas.vistaIngresarGruas;

/**
 *
 * @author Diego
 */
public class controladorIngresarGruas {

    static vistas.vistaIngresarGruas vistaIG;
    
    void mostrarVistaIngresarGruas(Object[] dataTonelajes) {
        vistaIG = new vistaIngresarGruas(new javax.swing.JFrame(), true, dataTonelajes);
        vistaIG.setLocationRelativeTo(null);
        vistaIG.setVisible(true);
    }
    
    public boolean irVistaGruasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        
        String[] data = {vistaIG.getTextoPatente(), vistaIG.getTextoDescripcion(), vistaIG.getTextoModelo(),
            vistaIG.getTextoPesoGrua(), vistaIG.getComboTipoNeumaticos2(), vistaIG.getComboTipoNeumaticos(),
            vistaIG.getTextoNChasis(), vistaIG.getComboTipoCombustible(), vistaIG.getTextoObservaciones(), vistaIG.getComboToneladas(),
            vistaIG.getTextoKMH(), vistaIG.getTextoFechaIngreso(), vistaIG.getTextoMarca(), vistaIG.getComboMastil(),
            vistaIG.getTextoAlturaMastil(), vistaIG.getTextoAncho(), vistaIG.getTextoLargo(), vistaIG.getTextoLargoUnas(),
            vistaIG.getTextoAlturaLevante(), vistaIG.getTextoNeumaticosDelanteros(), vistaIG.getTextoNeumaticosTraseros(),
            vistaIG.getTextoNumeroMotor(), vistaIG.getTextoNumeroSerie(), vistaIG.getTextoFechaRT(), vistaIG.getTextoFechaUM(),
            vistaIG.getTextoKMHUM()};
        boolean flag = miControlador.ingresarGrua(data);
        return flag;
    }
    
    public String camposVacios() {
        String respuesta = "";
        if (vistaIG.getTextoPatente().compareTo("") == 0) respuesta += "-Patente.\n";
        if (vistaIG.getTextoDescripcion().compareTo("") == 0) respuesta += "-Descripción.\n";
        if (vistaIG.getTextoModelo().compareTo("") == 0) respuesta += "-Modelo.\n";
        if (vistaIG.getComboToneladas().compareTo("") == 0) respuesta += "-Toneladas.\n";
        if (vistaIG.getTextoMarca().compareTo("") == 0) respuesta += "-Marca.\n";
        if (vistaIG.getTextoFechaIngreso().compareTo("") == 0) respuesta += "-Fecha de ingreso.\n";
        return respuesta;
    }
}
