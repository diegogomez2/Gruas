/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.text.ParseException;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

/**
 *
 * @author diego
 */
public class controladorHistorico {
    static vistas.vistaHistoricoOt vistaHistoricoP;
    
    JPanel mostrarTabControlHistorico(String tipo, Object[][] data) throws ParseException {
        vistaHistoricoP = new vistas.vistaHistoricoOt(tipo, data);  
        return vistaHistoricoP;
    }
    
    public void irVistaDetalleHistorico(String id, String tipo) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        miControlador.crearControladorDetalleFacturadas(id, tipo);
    }
    

    
    public String[] obtenerOtsPorIdNC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String[] data;
        data = nc.obtenerOtsPorIdNC(id);
        return data;
    }
    
    public String[] obtenerValoresNC(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String valores[];
        valores = nc.obtenerValoresNC(id);
        return valores;
    }
    
    public String[] obtenerOtsPorIdFacturada(String id){
        modelos.modeloFacturas nc = new modelos.modeloFacturas();
        String[] data;
        data = nc.obtenerOtsPorIdFacturada(id);
        return data;
    }
    
    public void crearControladorPrincipal(JTabbedPane tabs) throws ParseException{
        controladorPrincipal miControlador = new controladorPrincipal();
        tabs.remove(7);
        tabs.insertTab("Histórico", null, miControlador.crearControladorHistoricoP(), null, 7);
        tabs.setSelectedIndex(7);
    }
}
