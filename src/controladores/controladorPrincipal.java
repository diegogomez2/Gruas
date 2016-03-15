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
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelos.modeloClientes;
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
    static controladorJornadas micontroladorJornadas;
    static controladorOT micontroladorOT;
    static controladorUsuarios micontroladorUsuarios;
    static controladorDetalleClientes micontroladorDC;
    static vistaLogin miVistaL;
    static vistaPrincipal mivistaP;
    static vistas.vistaJornadasP mivistaJP;
    
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
        //controladorClientes miControladorC; borrar??
        //controladorGruas miControladorG;
        miVistaL.setVisible(false);
        vistas.vistaPrincipal miVistaP = new vistaPrincipal();
        miVistaP.setVisible(true);
    }
    
    //Crear controladores
    public void crearControladorPrincipal(JTabbedPane tabs, int i){
        tabs.removeAll();
        tabs.addTab("Clientes", crearControladorClientesP());
        tabs.addTab("Grúas", crearControladorGruasP());
        tabs.addTab("Empleados", crearControladorEmpleadosP());
        tabs.addTab("Jornada Diaria", crearControladorJornadaP());
        tabs.setSelectedIndex(i);
    }
    
   /* public void crearControladorClientes(){
        modelos.modeloClientes clientes;
        clientes = new modelos.modeloClientes();
        Object[][] data;
        data = clientes.listarClientes();
        micontroladorClientes = new controladorClientes();
        micontroladorClientes.mostrarVistaControlClientes(tipo, data);
    }*/
    
    public JPanel crearControladorClientesP() {
        modelos.modeloClientes clientes;
        clientes = new modelos.modeloClientes();
        Object[][] data;
        data = clientes.listarClientes();
        micontroladorClientes = new controladorClientes();
        return micontroladorClientes.mostrarTabControlClientes(tipo, data);
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
    
    public void crearControladorModificarClientes(String rut, String nombres) {
        controladorModificarClientes micontroladorMC;
        micontroladorMC = new controladorModificarClientes();
        micontroladorMC.mostrarVistaModificarCliente(rut, nombres);
    }
    
    public void crearControladorDetalleCliente(String rut) {
        controladorDetalleClientes micontroladorDC;
        micontroladorDC = new controladorDetalleClientes();
        micontroladorDC.mostrarVistaDetalleCliente(rut);
    }
    
    public JPanel crearControladorGruasP() {
        modelos.modeloGruas gruas;
        gruas = new modelos.modeloGruas();
        Object[][] data;
        data = gruas.listarGruas();
        micontroladorGruas = new controladorGruas();
        return micontroladorGruas.mostrarTabControlGruas(tipo, data);    
    }
    /*
    public void crearControladorGruas(){
        modelos.modeloGruas gruas;
        gruas = new modelos.modeloGruas();
        Object[][] data;
        data = gruas.listarGruas();
        micontroladorGruas = new controladorGruas();
        micontroladorGruas.mostrarVistaControlGruas(tipo, data);
    }
    */
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
    
    public void crearControladorModificarGruas(String patente, String descripcion) throws ParseException {
        controladorModificarGruas micontroladorMG;
        micontroladorMG = new controladorModificarGruas();
        micontroladorMG.mostrarVistaModificarGrua(patente, descripcion);
    }
    
    void crearControladorDetalleGrua(String patente) throws ParseException {
        controladorDetalleGruas micontroladorDG;
        micontroladorDG = new controladorDetalleGruas();
        micontroladorDG.mostrarVistaDetalleCliente(patente);
    }

    public JPanel crearControladorEmpleadosP() {
        modelos.modeloEmpleados empleados;
        empleados = new modelos.modeloEmpleados();
        Object[][] data;
        data = empleados.listarEmpleados();
        micontroladorEmpleados = new controladorEmpleados();
        return micontroladorEmpleados.mostrarTabControlEmpleados(tipo, data);    
    }
    /*
    public JPanel crearcontroladorEmpleadosP() {
        modelos.modeloEmpleados empleados;
        empleados = new modelos.modeloEmpleados();
        Object[][] data;
        data = empleados.listarEmpleados();
        micontroladorEmpleados = new controladorEmpleados();
        return micontroladorEmpleados.mostrarTabControlEmpleados(tipo, data);
    }
    */
    void crearControladorIngresarEmpleados() {
        controladores.controladorIngresarEmpleados micontroladorIE;
        micontroladorIE = new controladorIngresarEmpleados();
        micontroladorIE.mostrarVistaIngresarEmpleados();
    }
    
    public void crearControladorEliminarEmpleados(String rut) {
        controladorEliminarEmpleados micontroladorEE;
        micontroladorEE = new controladorEliminarEmpleados();
        micontroladorEE.irVistaEmpleadosP(rut);
    }
    
    void crearControladorModificarEmpleados(String rut, String nombres) throws ParseException {
        controladorModificarEmpleados micontroladorME;
        micontroladorME = new controladorModificarEmpleados();
        micontroladorME.mostrarVistaModificarEmpleados(rut, nombres);
    }
    
    void crearControladorDetalleEmpleado(String rut) throws ParseException {
        controladorDetalleEmpleados micontroladorDE;
        micontroladorDE = new controladorDetalleEmpleados();
        micontroladorDE.mostrarVistaDetalleEmpleado(rut);
    }
    
    /*
    public void crearControladorEmpleados() {
        modelos.modeloEmpleados empleados;
        empleados = new modelos.modeloEmpleados();
        Object[][] data;
        data = empleados.listarEmpleados();
        micontroladorEmpleados = new controladorEmpleados();
        micontroladorEmpleados.mostrarVistaControlEmpleados(tipo, data);
    }*/
    public JPanel crearControladorJornadaP() {
        modelos.modeloJornadas jornadas;
        jornadas = new modelos.modeloJornadas();
        Object[][] data;
        data = jornadas.listarJornadas();
        micontroladorJornadas = new controladorJornadas();
        return micontroladorJornadas.mostrarTabControlJornadas(tipo, data);    
    }
    
    void crearControladorIngresarJornadas() {
        modelos.modeloClientes clientes;
        modelos.modeloGruas gruas;
        modelos.modeloEmpleados empleados;
        clientes = new modeloClientes();
        gruas = new modelos.modeloGruas();
        empleados = new modelos.modeloEmpleados();
        Object[][] dataClientes, dataGruas, dataEmpleados;
        dataClientes = clientes.obtenerRazonClientes();
        dataGruas = gruas.obtenerDescGruas();
        dataEmpleados = empleados.obtenerNombresEmpleados();
        controladores.controladorIngresarJornadas micontroladorIJ;
        micontroladorIJ = new controladorIngresarJornadas();
        micontroladorIJ.mostrarVistaIngresarJornadas(dataClientes, dataGruas, dataEmpleados);
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
    
    //Funciones
    
    public boolean ingresarCliente(String[] data){
        modelos.modeloClientes cliente = new modelos.modeloClientes();
        if(cliente.ingresarCliente(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Cliente ingresado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
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
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar la grúa seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    boolean modificarGrua(String[] data, String patente) {
        modelos.modeloGruas grua = new modelos.modeloGruas();
        if(grua.modificarGrua(data, patente).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Grúa modificada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar la grúa selecionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    String[] obtenerGruaPorPatente(String patente) {
        modelos.modeloGruas grua = new modelos.modeloGruas();
        String[] data = grua.obtenerClientePorRut(patente);
        return data;    
    }
    
    boolean ingresarEmpleado(String[] data) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        if(empleado.ingresarEmpleados(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Empleado ingresado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos.\n Compruebe que el rut sea correcto",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    boolean eliminarEmpleados(String data) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        if(empleado.eliminarEmpleado(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Empleado eliminado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar el empleado seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    boolean modificarEmpleado(String[] data, int rut) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        if(empleado.modificarEmpleado(data, rut).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Empleado modificado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar el empleado selecionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    String[] obtenerEmpleadoPorRut(String rut) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        String[] data = empleado.obtenerEmpleadoPorRut(rut);
        return data;
    }
    
    
    
    public void crearControladorIngresarOT() {
        controladorIngresarOT micontroladorOT;
        micontroladorOT = new controladorIngresarOT();
        micontroladorOT.mostrarVistaIngresarOT();    
    }
    
    public void cambiarClaveUsuario(String pwNueva) {
        controladorCambioClave miControlador = new controladorCambioClave();
        miControlador.cambiarClave(pwNueva);
    }
    
    /*
    public void updateTable(String[] data){
        JTable tjornadas = mivistaJP.getTablaJornadas();
        DefaultTableModel model = (DefaultTableModel) tjornadas.getModel();
        model.addRow(new Object[]{data[2], data[3], data[4], data[0], "", data[7], data[1] });
    }*/
   
    boolean ingresarJornada(String[] data) {
        modelos.modeloJornadas jornada = new modelos.modeloJornadas();
        if(jornada.ingresarJornada(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Jornada ingresada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos.\n", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    
}
