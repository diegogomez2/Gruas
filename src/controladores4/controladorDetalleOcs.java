/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.text.ParseException;

/**
 *
 * @author diego
 */
public class controladorDetalleOcs {
    static vistas4.vistaDetalleOcs vistaDO;
    static vistas4.vistaDetalleTraspaletaOcs vistaDTO;
    
    public void mostrarVistaDetalleOcs(String[] data) throws ParseException{
//        controladorPrincipal miControlador = new controladorPrincipal();
        modelos4.modeloJornadasOC ocs = new modelos4.modeloJornadasOC();
//        String data[] = miControlador.obtenerOcPorId(id);
        String[][] horas = ocs.obtenerHorasPorIdJornadaOC(data[23]);
        String[][] gruas = ocs.obtenerGruasPorIdJornadaOC(data[23]);
        String[][] emps = ocs.obtenerEmpleadosPorIdJornadaOC(data[23]);
        vistaDO = new vistas4.vistaDetalleOcs(new javax.swing.JFrame(), true);
//        {estfsal, estfreg, estobs, estrutcli, estdigcli, estraz, estgir, estdir, esttel, id, estfot, estcond
//                    , estpago, estcont, esttot, estneto, estiva, estdesp, esthorfin, estcdesp, estvdesp, estciu, estdesc, estidoc};
//        vistaDO.setDiaInicio(data[0]);
//        vistaDO.setHoraInicio(data[1]);
//        vistaDO.setDiaFin(data[2]);
//        vistaDO.setHoraFin(data[3]);
        vistaDO.setTextoObs(data[2]);
        vistaDO.setTextoRutCliente(data[3] + "-" + data[4]);
        vistaDO.setTextoRazon(data[5]);
        vistaDO.setTextoRazon2(data[5]);
        vistaDO.setTextoGiro(data[6]);
        vistaDO.setTextoDireccion(data[7]);
        vistaDO.setTextoTelefono(data[8]);
        vistaDO.setTextoCodigo(data[9]);
//        vistaDO.setId(data[11]);
        vistaDO.setTextoFechaOc(data[10]);
        vistaDO.setTextoFechaInicio(data[0]);
        vistaDO.setTextoFechaTermino(data[1]);
        vistaDO.setComboCondPago(data[11]);
        vistaDO.setComboFormaPago(data[12]);
        vistaDO.setTextoContacto(data[13]);
        vistaDO.setTextoBruto(data[14]);
        int neto = Integer.parseInt(data[15]) - Integer.parseInt(data[20]) + Integer.parseInt(data[22]);
        vistaDO.setTextoNeto(String.valueOf(neto));
        vistaDO.setTextoIva(data[16]);
        vistaDO.setTextoDespachado(data[17]);
        vistaDO.setCheckDespacho(data[19]);
        vistaDO.setTextoDespacho(data[20]);
//        vistaDO.setNuevoNeto(0);
        vistaDO.setTextoCiudad(data[21]);
        vistaDO.setTextoDescuento(data[22]);
        vistaDO.setTextoNuevoNeto(data[15]);
        vistaDO.agregarGruas(gruas);
        vistaDO.agregarEmpleados(emps);
        vistaDO.agregarHoras(horas);
        vistaDO.setLocationRelativeTo(null);
        vistaDO.setVisible(true);
    }

    public void mostrarVistaDetalleTraspaletaOcs(String[] data) throws ParseException {
        modelos4.modeloJornadasOC ocs = new modelos4.modeloJornadasOC();
        vistaDTO = new vistas4.vistaDetalleTraspaletaOcs(new javax.swing.JFrame(), true);
        vistaDTO.setTextoObs(data[2]);
        vistaDTO.setTextoRutCliente(data[3] + "-" + data[4]);
        vistaDTO.setTextoRazon(data[5]);
        vistaDTO.setTextoRazon2(data[5]);
        vistaDTO.setTextoGiro(data[6]);
        vistaDTO.setTextoDireccion(data[7]);
        vistaDTO.setTextoTelefono(data[8]);
        vistaDTO.setTextoCodigo(data[9]);
        vistaDTO.setTextoFechaOc(data[10]);
        vistaDTO.setTextoFechaInicio(data[0]);
        vistaDTO.setTextoFechaTermino(data[1]);
        vistaDTO.setComboCondPago(data[11]);
        vistaDTO.setComboFormaPago(data[12]);
        vistaDTO.setTextoContacto(data[13]);
        vistaDTO.setTextoBruto(data[14]);
        int neto = Integer.parseInt(data[15]) - Integer.parseInt(data[20]) + Integer.parseInt(data[22]);
        vistaDTO.setTextoNeto(String.valueOf(neto));
        vistaDTO.setTextoIva(data[16]);
        vistaDTO.setTextoDespachado(data[17]);
        vistaDTO.setCheckDespacho(data[19]);
        vistaDTO.setTextoDespacho(data[20]);
        vistaDTO.setTextoCiudad(data[21]);
        vistaDTO.setTextoDescuento(data[22]);
        vistaDTO.setTextoNuevoNeto(data[15]);
        vistaDTO.setSpinnerNumTras(data[24]);
        vistaDTO.setLocationRelativeTo(null);
        vistaDTO.setVisible(true);
    }
}
