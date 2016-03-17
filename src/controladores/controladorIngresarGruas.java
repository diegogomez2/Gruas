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
    
    void mostrarVistaIngresarGruas() {
        vistaIG = new vistaIngresarGruas(new javax.swing.JFrame(), true);
        vistaIG.setLocationRelativeTo(null);
        vistaIG.setVisible(true);
    }
    
    public boolean irVistaControlGruas() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] data = {vistaIG.getTextoPatente(), vistaIG.getTextoDescripcion(), vistaIG.getTextoModelo(),
            vistaIG.getTextoPesoGrua(), vistaIG.getComboTipoNeumaticos2(), vistaIG.getComboTipoNeumaticos(),
            vistaIG.getTextoNChasis(), vistaIG.getComboTipoCombustible(), vistaIG.getTextoObservaciones(), vistaIG.getTextoToneladas(),
            vistaIG.getTextoKMH(), vistaIG.getTextoFechaIngreso(), vistaIG.getTextoMarca(), vistaIG.getComboMastil(),
            vistaIG.getTextoAlturaMastil(), vistaIG.getTextoAncho(), vistaIG.getTextoLargo(), vistaIG.getTextoLargoUnas(),
            vistaIG.getTextoAlturaLevante(), vistaIG.getTextoNeumaticosDelanteros(), vistaIG.getTextoNeumaticosTraseros(),
            vistaIG.getTextoNumeroMotor(), vistaIG.getTextoNumeroSerie(), vistaIG.getTextoFechaRT(), vistaIG.getTextoFechaUM(),
            vistaIG.getTextoKMHUM(), vistaIG.getTextoHorasPM(), vistaIG.getTextoFechaBaja()};
        boolean flag = miControlador.ingresarGrua(data);
        return flag;
    }
    
    public String camposVacios() {
        String respuesta = "";
        if (vistaIG.getTextoPatente().compareTo("") == 0) respuesta += "-Patente.\n";
        if (vistaIG.getTextoDescripcion().compareTo("") == 0) respuesta += "-Descripción.\n";
        if (vistaIG.getTextoModelo().compareTo("") == 0) respuesta += "-Modelo.\n";
        if (vistaIG.getTextoPesoGrua().compareTo("") == 0) respuesta += "-Peso Grúa.\n";
        if (vistaIG.getTextoNChasis().compareTo("") == 0) respuesta += "-Número de chasis .\n";
        if (vistaIG.getTextoObservaciones().compareTo("") == 0) respuesta += "-Observaciones.\n";
        if (vistaIG.getTextoToneladas().compareTo("") == 0) respuesta += "-Toneladas.\n";
        if (vistaIG.getTextoKMH().compareTo("") == 0) respuesta += "-Kilómetros/Horas.\n";
        if (vistaIG.getTextoMarca().compareTo("") == 0) respuesta += "-Marca.\n";
        if (vistaIG.getTextoAlturaMastil().compareTo("") == 0) respuesta += "-Altura mástil.\n";
        if (vistaIG.getTextoAncho().compareTo("") == 0) respuesta += "-Ancho.\n";
        if (vistaIG.getTextoLargo().compareTo("") == 0) respuesta += "-Largo.\n";
        if (vistaIG.getTextoLargoUnas().compareTo("") == 0) respuesta += "-Largo uñas.\n";
        if (vistaIG.getTextoAlturaLevante().compareTo("") == 0) respuesta += "-Altura levante.\n";
        if (vistaIG.getTextoNeumaticosDelanteros().compareTo("") == 0) respuesta += "-Neumáticos delanteros.\n";
        if (vistaIG.getTextoNeumaticosTraseros().compareTo("") == 0) respuesta += "-Neumáticos traseros.\n";
        if (vistaIG.getTextoNumeroMotor().compareTo("") == 0) respuesta += "-Número de motor.\n";
        if (vistaIG.getTextoNumeroSerie().compareTo("") == 0) respuesta += "-Número de serie.\n";
        if (vistaIG.getTextoKMHUM().compareTo("") == 0) respuesta += "-Kilómetros/Horas última mantención.\n";
        if (vistaIG.getTextoHorasPM().compareTo("") == 0) respuesta += "-Horas próxima mantención.\n";
        if (vistaIG.getTextoFechaIngreso().compareTo("") == 0) respuesta += "-Fecha de ingreso.\n";
        if (vistaIG.getTextoFechaRT().compareTo("") == 0) respuesta += "-Fecha de revisión técnica.\n";
        if (vistaIG.getTextoFechaUM().compareTo("") == 0) respuesta += "-Fecha última revisión.\n";
        if (vistaIG.getTextoFechaBaja().compareTo("") == 0) respuesta += "-Fecha de baja.\n";
        return respuesta;
    }
}
