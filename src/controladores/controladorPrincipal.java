/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores;

import java.awt.Component;
import java.awt.Frame;
import java.text.ParseException;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import vistas.vistaLogin;
import modelos.modeloUsuarios;
import vistas.vistaPrincipal;

/**
 *
 * @author Diego
 */
public class controladorPrincipal {
    static String tipo;
    public static String user;
    static controladorClientes micontroladorClientes;
    static controladorGruas micontroladorGruas;
    static controladorEmpleados micontroladorEmpleados;
    static controladorOT micontroladorOT;
    static controladorUsuarios micontroladorUsuarios;
    static controladorDetalleClientes micontroladorDC;
    static vistaLogin miVistaL;
    
    public static void main(String[] args) {
        miVistaL = new vistaLogin();
    }
    
    public void autenticarUsuario(){
        String rut, pass;
        modeloUsuarios usuario = new modeloUsuarios();
        rut = miVistaL.getTextoUsuario();
        pass = miVistaL.getTextoContraseña();
        if((tipo = usuario.verificarLogin(rut, pass)).compareTo("incorrecto") != 0){
            user = rut;
            mostrarVentana();
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Nombre de usuario o contraseña incorrectos", "Error", JOptionPane.ERROR_MESSAGE);
            miVistaL.setTextoUsuario();
            miVistaL.setTextoContraseña();
        }
    }
    
    public void mostrarVentana(){
        controladorClientes miControladorC;
        controladorGruas miControladorG;
        miVistaL.setVisible(false);
        vistas.vistaPrincipal miVistaP = new vistaPrincipal();
        miVistaP.setVisible(true);
    }
    
    public void crearControladorClientes(){
        modelos.modeloClientes clientes;
        clientes = new modelos.modeloClientes();
        Object[][] data;
        data = clientes.listarClientes();
        micontroladorClientes = new controladorClientes();
        micontroladorClientes.mostrarVistaControlClientes(tipo, data);
    }
    
    public void crearControladorGruas(){
        modelos.modeloGruas gruas;
        gruas = new modelos.modeloGruas();
        Object[][] data;
        data = gruas.listarGruas();
        micontroladorGruas = new controladorGruas();
        micontroladorGruas.mostrarVistaControlGruas(tipo, data);
    }

    public void crearControladorEmpleados() {
        modelos.modeloEmpleados empleados;
        empleados = new modelos.modeloEmpleados();
        Object[][] data;
        data = empleados.listarEmpleados();
        micontroladorEmpleados = new controladorEmpleados();
        micontroladorEmpleados.mostrarVistaControlEmpleados(tipo, data);
    }
    
    public void crearControladorOT() {
        modelos.modeloEmpleados empleados;
        empleados = new modelos.modeloEmpleados();
        Object[][] data;
        data = empleados.obtenerNombresEmpleados();
        micontroladorOT = new controladorOT();
        micontroladorOT.mostrarVistaOT(tipo, data);
    }
    
    public void crearControladorUsuarios() {
        modelos.modeloUsuarios usuarios;
        usuarios = new modelos.modeloUsuarios();
        String data;
        data = usuarios.obtenerContraseña(user);
        micontroladorUsuarios = new controladorUsuarios();
        micontroladorUsuarios.mostrarVistaCambioClave(tipo, data);
    }
    
    public void crearControladorIngresarClientes() {
        controladorIngresarClientes micontroladorIC;
        micontroladorIC = new controladorIngresarClientes();
        micontroladorIC.mostrarVistaIngresarClientes();
    }
    
    public void crearControladorEliminarClientes(String rut){
        controladorEliminarClientes micontroladorEC;
        micontroladorEC = new controladorEliminarClientes();
        micontroladorEC.irVistaControlClientes(rut);
    }
    
    public void crearControladorModificarClientes(String rut, String nombres, String apP) {
        controladorModificarClientes micontroladorMC;
        micontroladorMC = new controladorModificarClientes();
        micontroladorMC.mostrarVistaModificarCliente(rut, nombres, apP);
    }
    
    public void crearControladorDetalleCliente(String rut) {
        controladorDetalleClientes micontroladorDC;
        micontroladorDC = new controladorDetalleClientes();
        micontroladorDC.mostrarVistaDetalleCliente(rut);
    }
    
    public void crearControladorIngresarGruas() {
        controladorIngresarGruas micontroladorIG;
        micontroladorIG = new controladorIngresarGruas();
        micontroladorIG.mostrarVistaIngresarGruas();
    }
    
    public void crearControladorEliminarGruas(String patente) {
        controladorEliminarGruas micontroladorEG;
        micontroladorEG = new controladorEliminarGruas();
        micontroladorEG.irVistaControlGruas(patente);
    }
    
    void crearControladorDetalleGrua(String patente) throws ParseException {
        controladorDetalleGruas micontroladorDG;
        micontroladorDG = new controladorDetalleGruas();
        micontroladorDG.mostrarVistaDetalleCliente(patente);
    }
    
    public void crearControladorModificarClientes(String patente, String descripcion) throws ParseException {
        controladorModificarGruas micontroladorMG;
        micontroladorMG = new controladorModificarGruas();
        micontroladorMG.mostrarVistaModificarGrua(patente, descripcion);
    }
    
    public void crearControladorIngresarOT() {
        controladorIngresarOT micontroladorOT;
        micontroladorOT = new controladorIngresarOT();
        micontroladorOT.mostrarVistaIngresarOT();    }
    
    public boolean ingresarCliente(String[] data){
        modelos.modeloClientes cliente = new modelos.modeloClientes();
        if(cliente.ingresarCliente(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Cliente ingresado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            crearControladorClientes();
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos\n" 
                    + "verifique que el rut no exista previamente en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean eliminarClientes(String data){
        modelos.modeloClientes cliente = new modelos.modeloClientes();
        if(cliente.eliminarCliente(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Cliente eliminado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            crearControladorClientes();
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar el cliente seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean modificarCliente(String[] data, int rut){
        modelos.modeloClientes cliente = new modelos.modeloClientes();
        if(cliente.modificarCliente(data, rut).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Cliente modificado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            crearControladorClientes();
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar el cliente selecionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public String[] obtenerClientePorRut(String rut){
        modelos.modeloClientes cliente = new modelos.modeloClientes();
        String[] data = cliente.obtenerClientePorRut(rut);
        return data;
    }

    
    
    public boolean ingresarGrua(String[] data){
        modelos.modeloGruas grua = new modelos.modeloGruas();
        if(grua.ingresarGrua(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Grua ingresada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            crearControladorGruas();
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos\n" 
                    + "verifique que la patente no exista previamente en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    boolean eliminarGruas(String patente) {
        modelos.modeloGruas grua = new modelos.modeloGruas();
        if(grua.eliminarGrua(patente).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Grua eliminada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            crearControladorGruas();
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar la grúa seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    public void cambiarClaveUsuario(String pwNueva) {
        controladorCambioClave miControlador = new controladorCambioClave();
        miControlador.cambiarClave(pwNueva);
    }

    String[] obtenerGruaPorPatente(String patente) {
        modelos.modeloGruas grua = new modelos.modeloGruas();
        String[] data = grua.obtenerClientePorRut(patente);
        return data;    
    }

    public JPanel crearControladorClientesP() {
        modelos.modeloClientes clientes;
        clientes = new modelos.modeloClientes();
        Object[][] data;
        data = clientes.listarClientes();
        micontroladorClientes = new controladorClientes();
        return micontroladorClientes.mostrarTabControlClientes(tipo, data);
    }

    public JPanel crearcontroladorGruasP() {
        modelos.modeloGruas gruas;
        gruas = new modelos.modeloGruas();
        Object[][] data;
        data = gruas.listarGruas();
        micontroladorGruas = new controladorGruas();
        return micontroladorGruas.mostrarTabControlGruas(tipo, data);    
    }

    public Component crearcontroladorEmpleadosP() {
        modelos.modeloEmpleados empleados;
        empleados = new modelos.modeloEmpleados();
        Object[][] data;
        data = empleados.listarEmpleados();
        micontroladorEmpleados = new controladorEmpleados();
        return micontroladorEmpleados.mostrarTabControlEmpleados(tipo, data);
    }

    void crearControladorModificarGruas(String patente, String descripcion) throws ParseException {
        controladorModificarGruas micontroladorMG;
        micontroladorMG = new controladorModificarGruas();
        micontroladorMG.mostrarVistaModificarGrua(patente, descripcion);    }

    boolean modificarGrua(String[] data, String patente) {
        modelos.modeloGruas grua = new modelos.modeloGruas();
        if(grua.modificarGrua(data, patente).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Grúa modificada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            crearControladorGruas();
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar la grúa selecionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }


}
