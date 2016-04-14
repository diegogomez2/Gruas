/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controladores;

import java.text.ParseException;
import vistas.vistaModificarGruas;

/**
 *
 * @author Diego
 */
public class controladorModificarGruas {

    static vistas.vistaModificarGruas vistaMG;
    
    void mostrarVistaModificarGrua(String patente, String descripcion) throws ParseException {
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerGruaPorPatente(patente);
        vistaMG = new vistaModificarGruas(new javax.swing.JFrame(), true);
        vistaMG.setTextoPatente(data[0]);
        vistaMG.setTextoDescripcion(data[1]);
        vistaMG.setTextoModelo(data[2]);
        vistaMG.setTextoPesoGrua(data[3]);
        vistaMG.setComboTipoNeumaticos(data[4]);
        vistaMG.setComboTipoNeumaticos2(data[5]);
        vistaMG.setTextoNChasis(data[6]);
        vistaMG.setComboTipoCombustible(data[7]);
        vistaMG.setTextoObs(data[8]);
        vistaMG.setTextoToneladas(data[9]);
        vistaMG.setTextoKMH(data[10]);
        vistaMG.setTextoFechaIngreso(data[11]);
        vistaMG.setTextoMarca(data[12]);
        vistaMG.setComboMastil(data[13]);
        vistaMG.setTextoAlturaMastil(data[14]);
        vistaMG.setTextoAncho(data[15]);
        vistaMG.setTextoLargo(data[16]);
        vistaMG.setTextoLargoUnas(data[17]);
        vistaMG.setTextoAlturaLevante(data[18]);
        vistaMG.setTextoNeumaticosDelanteros(data[19]);
        vistaMG.setTextoNeumaticosTraseros(data[20]);
        vistaMG.setTextoNumeroMotor(data[21]);
        vistaMG.setTextoNumeroSerie(data[22]);
        vistaMG.setTextoFechaRT(data[23]);
        vistaMG.setTextoFechaUM(data[24]);
        vistaMG.setTextoKMHUM(data[25]);
        vistaMG.setTextoHorasPM(data[26]);
        vistaMG.setTextoFechaBaja(data[27]);
        vistaMG.setPatente(data[0]);
        vistaMG.setLocationRelativeTo(null);
        vistaMG.setVisible(true);
    }
    
    public String camposVacios(){
        String respuesta = "";
        if(vistaMG.getTextoPatente().compareTo("") == 0) respuesta += "-Patente.\n";
        if(vistaMG.getTextoDescripcion().compareTo("") == 0) respuesta += "-Descripción.\n";
        if(vistaMG.getTextoModelo().compareTo("") == 0 ) respuesta += "-Modelo.\n";
        if(vistaMG.getTextoPesoGrua().compareTo("") == 0) respuesta += "-Peso Grúa.\n";
        if(vistaMG.getTextoNChasis().compareTo("") == 0) respuesta += "-Número de chasis.\n";
        if(vistaMG.getTextoToneladas().compareTo("") == 0) respuesta += "-Toneladas.\n";
        if(vistaMG.getTextoKMH().compareTo("") == 0) respuesta += "-Horómetro.\n";
        if(vistaMG.getTextoFechaIngreso().compareTo("") == 0) respuesta += "-Fecha ingreso.\n";
        if(vistaMG.getTextoMarca().compareTo("") == 0) respuesta += "-Marca.\n";
        return respuesta;
    }
    
    public boolean irVistaGruasP() {
        controladores.controladorPrincipal miControlador = new controladores.controladorPrincipal();
        String[] data = {vistaMG.getTextoPatente(), vistaMG.getTextoDescripcion(), vistaMG.getTextoModelo(),
            vistaMG.getTextoPesoGrua(), vistaMG.getComboTipoNeumaticos(), vistaMG.getComboTipoNeumaticos2(),
            vistaMG.getTextoNChasis(), vistaMG.getComboTipoCombustible(), vistaMG.getTextoObs(),
            vistaMG.getTextoToneladas(), vistaMG.getTextoKMH(), vistaMG.getTextoFechaIngreso(),
            vistaMG.getTextoMarca(), vistaMG.getComboMastil(), vistaMG.getTextoAlturaMastil(), 
            vistaMG.getTextoAncho(), vistaMG.getTextoLargo(), vistaMG.getTextoLargoUnas(), vistaMG.getTextoAlturaLevante(),
            vistaMG.getTextoNeumaticosDelanteros(), vistaMG.getTextoNeumaticosTraseros(), vistaMG.getTextoNumeroMotor(),
            vistaMG.getTextoNumeroSerie(), vistaMG.getTextoFechaRT(), vistaMG.getTextoFechaUM(), vistaMG.getTextoKMHUM(),
            vistaMG.getTextoHorasPM(), vistaMG.getTextoFechaBaja()};
        boolean flag = miControlador.modificarGrua(data, vistaMG.getPatente());
        return flag;
    }
}
