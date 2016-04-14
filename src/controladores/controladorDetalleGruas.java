/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import vistas.vistaDetalleGruas;

/**
 *
 * @author diego
 */
class controladorDetalleGruas {
    static vistas.vistaDetalleGruas vistaDG;
    
    void mostrarVistaDetalleCliente(String rut) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        String data[] = miControlador.obtenerGruaPorPatente(rut);
        vistaDG = new vistaDetalleGruas(new javax.swing.JFrame(), true);
        vistaDG.setTextoPatente(data[0]);
        vistaDG.setTextoDescripcion(data[1]);
        vistaDG.setTextoModelo(data[2]);
        vistaDG.setTextoPesoGrua(data[3]);
        vistaDG.setComboTipoNeumaticos(data[4]);
        vistaDG.setComboTipoNeumaticos2(data[5]);
        vistaDG.setTextoNChasis(data[6]);
        vistaDG.setComboTipoCombustible(data[7]);
        vistaDG.setTextoObs(data[8]);
        vistaDG.setTextoToneladas(data[9]);
        vistaDG.setTextoKMH(data[10]);
        vistaDG.setTextoFechaIngreso(data[11]);
        vistaDG.setTextoMarca(data[12]);
        vistaDG.setComboMastil(data[13]);
        vistaDG.setTextoAlturaMastil(data[14]);
        vistaDG.setTextoAncho(data[15]);
        vistaDG.setTextoLargo(data[16]);
        vistaDG.setTextoLargoUnas(data[17]);
        vistaDG.setTextoAlturaLevante(data[18]);
        vistaDG.setTextoNeumaticosDelanteros(data[19]);
        vistaDG.setTextoNeumaticosTraseros(data[20]);
        vistaDG.setTextoNumeroMotor(data[21]);
        vistaDG.setTextoNumeroSerie(data[22]);
        vistaDG.setTextoFechaRT(data[23]);
        vistaDG.setTextoFechaUM(data[24]);
        vistaDG.setTextoKMHUM(data[25]);
        vistaDG.setTextoHorasPM(data[26]);
        vistaDG.setTextoFechaBaja(data[27]);
        vistaDG.setLocationRelativeTo(null);
        vistaDG.setVisible(true);
    }
}
