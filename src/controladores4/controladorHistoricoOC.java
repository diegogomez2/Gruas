/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores4;

import controladores.controladorPrincipal;
import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author diego
 */
public class controladorHistoricoOC {
    static vistas4.vistaHistoricoOC vistaHistoricoOCP;
    
    public JPanel mostrarTabControlHistoricoOC(String tipo, Object[][] data) {
        vistaHistoricoOCP = new vistas4.vistaHistoricoOC(tipo, data);  
        return vistaHistoricoOCP;
    }
    
//    public void irVistaDetalleHistorico(String id, String tipo) throws ParseException{
//        controladorPrincipal miControlador = new controladorPrincipal();
//        miControlador.crearControladorDetalleFacturadas(id, tipo);
//    }
//    
//
//    
//    public String[] obtenerOtsPorIdNC(String id){
//        modelos.modeloFacturas nc = new modelos.modeloFacturas();
//        String[] data;
//        data = nc.obtenerOtsPorIdNC(id);
//        return data;
//    }
//    
//    public String[] obtenerValoresNC(String id){
//        modelos.modeloFacturas nc = new modelos.modeloFacturas();
//        String valores[];
//        valores = nc.obtenerValoresNC(id);
//        return valores;
//    }
//    
//    public String[] obtenerOtsPorIdFacturada(String id){
//        modelos.modeloFacturas nc = new modelos.modeloFacturas();
//        String[] data;
//        data = nc.obtenerOtsPorIdFacturada(id);
//        return data;
//    }
    
    public void crearControladorPrincipal(JTabbedPane tabs){
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(4);
        tabs.insertTab("Histórico", null, miControlador.crearControladorHistoricoOCP(), null, 4);
        tabs.setSelectedIndex(4);
    }
}
