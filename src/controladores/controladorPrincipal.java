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
import modelos.modeloEmpleados;
import modelos.modeloJornadas;
import modelos.modeloRegiones;
import modelos.modeloTonelajes;
import vistas.vistaLogin;
import modelos.modeloUsuarios;
import vistas.vistaPrincipal;
import controladores2.*;
import modelos2.modeloCompras;
import modelos2.modeloProveedores;

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
    static controladorOts micontroladorOts;
    static controladorFacturas micontroladorFacturas;
    static controladorFacturadas micontroladorFacturadas;
    static controladorHistorico micontroladorHistorico;
    static controladorUsuarios micontroladorUsuarios;
    static controladorDetalleClientes micontroladorDC;
    static controladorTarifas micontroladorTarifas;
    static controladorProveedores micontroladorProveedores;
    static controladorCompras micontroladorCompras;
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
        miVistaL.setVisible(false);
        vistas.vistaPrincipal miVistaP = new vistaPrincipal();
        miVistaP.setVisible(true);
    }
    
    //Crear controladores
    public void crearControladorPrincipal(JTabbedPane tabs, int i){
        tabs.removeAll();
        tabs.addTab("Clientes", crearControladorClientesP());
        tabs.addTab("Grúas", crearControladorGruasP());
        tabs.addTab("Trabajadores", crearControladorEmpleadosP());
        tabs.addTab("Jornada Diaria", crearControladorJornadaP());
        //tabs.addTab("Usuarios", crearControladorUsuariosP());
        tabs.setSelectedIndex(i);
    }
    
    //CLIENTES
    public JPanel crearControladorClientesP() {
        modelos.modeloClientes clientes;
        clientes = new modelos.modeloClientes();
        Object[][] data;
        data = clientes.listarClientes();
        micontroladorClientes = new controladorClientes();
        return micontroladorClientes.mostrarTabControlClientes(tipo, data);
    }
    
    public void crearControladorIngresarClientes() {
        modelos.modeloRegiones regiones = new modeloRegiones();
        Object[][] dataRegiones = regiones.listarRegiones();
        controladorIngresarClientes micontroladorIC;
        micontroladorIC = new controladorIngresarClientes();
        micontroladorIC.mostrarVistaIngresarClientes(dataRegiones);
    }
    
    public void crearControladorEliminarClientes(String rut){
        controladorEliminarClientes micontroladorEC;
        micontroladorEC = new controladorEliminarClientes();
        micontroladorEC.irVistaClientesP(rut);
    }
    
    public void crearControladorModificarClientes(String rut, String nombres) {
        modelos.modeloRegiones regiones = new modeloRegiones();
        Object[][] dataRegiones = regiones.listarRegiones();
        controladorModificarClientes micontroladorMC;
        micontroladorMC = new controladorModificarClientes();
        micontroladorMC.mostrarVistaModificarCliente(rut, nombres, dataRegiones);
    }
    
    public void crearControladorDetalleCliente(String rut) {
        controladorDetalleClientes micontroladorDC;
        micontroladorDC = new controladorDetalleClientes();
        micontroladorDC.mostrarVistaDetalleCliente(rut);
    }
    
    //GRUAS
    public JPanel crearControladorGruasP() {
        modelos.modeloGruas gruas;
        gruas = new modelos.modeloGruas();
        Object[][] data;
        data = gruas.listarGruas();
        micontroladorGruas = new controladorGruas();
        return micontroladorGruas.mostrarTabControlGruas(tipo, data);    
    }

    public void crearControladorIngresarGruas() {
        modelos.modeloTonelajes tonelajes = new modeloTonelajes();
        Object[] dataTonelajes = tonelajes.listarTonelajes();
        controladorIngresarGruas micontroladorIG;
        micontroladorIG = new controladorIngresarGruas();
        micontroladorIG.mostrarVistaIngresarGruas(dataTonelajes);
    }
    
    public void crearControladorEliminarGruas(String patente) {
        controladorEliminarGruas micontroladorEG;
        micontroladorEG = new controladorEliminarGruas();
        micontroladorEG.irVistaGruasP(patente);
    }
    
    public void crearControladorModificarGruas(String patente) throws ParseException {
        controladorModificarGruas micontroladorMG;
        micontroladorMG = new controladorModificarGruas();
        modelos.modeloTonelajes tonelajes = new modeloTonelajes();
        Object[] dataTonelajes = tonelajes.listarTonelajes();
        micontroladorMG.mostrarVistaModificarGrua(patente, dataTonelajes);
    }
    
    void crearControladorDetalleGrua(String patente) throws ParseException {
        controladorDetalleGruas micontroladorDG;
        micontroladorDG = new controladorDetalleGruas();
        micontroladorDG.mostrarVistaDetalleCliente(patente);
    }

    //EMPLEADOS
    public JPanel crearControladorEmpleadosP() {
        modelos.modeloEmpleados empleados;
        empleados = new modelos.modeloEmpleados();
        Object[][] data;
        data = empleados.listarEmpleados();
        micontroladorEmpleados = new controladorEmpleados();
        return micontroladorEmpleados.mostrarTabControlEmpleados(tipo, data);    
    }

    void crearControladorIngresarEmpleados() {
        modelos.modeloRegiones regiones = new modeloRegiones();
        Object[][] dataRegiones = regiones.listarRegiones();
        controladores.controladorIngresarEmpleados micontroladorIE;
        micontroladorIE = new controladorIngresarEmpleados();
        micontroladorIE.mostrarVistaIngresarEmpleados(dataRegiones);
    }
    
    public void crearControladorEliminarEmpleados(String rut) {
        controladorEliminarEmpleados micontroladorEE;
        micontroladorEE = new controladorEliminarEmpleados();
        micontroladorEE.irVistaEmpleadosP(rut);
    }
    
    void crearControladorModificarEmpleados(String rut, String nombres) throws ParseException {
        modelos.modeloRegiones regiones = new modeloRegiones();
        Object[][] dataRegiones = regiones.listarRegiones();
        controladorModificarEmpleados micontroladorME;
        micontroladorME = new controladorModificarEmpleados();
        micontroladorME.mostrarVistaModificarEmpleados(rut, nombres, dataRegiones);
    }
    
    void crearControladorDetalleEmpleado(String rut) throws ParseException {
        controladorDetalleEmpleados micontroladorDE;
        micontroladorDE = new controladorDetalleEmpleados();
        micontroladorDE.mostrarVistaDetalleEmpleado(rut);
    }

    //JORNADAS
    public JPanel crearControladorJornadaP() {
        modelos.modeloJornadas jornadas;
        jornadas = new modelos.modeloJornadas();
        Object[][] data;
        data = jornadas.listarJornadas();
        micontroladorJornadas = new controladorJornadas();
        return micontroladorJornadas.mostrarTabControlJornadas(tipo, data);    
    }
    
    public JPanel crearControladorUsuariosP() {
        modelos.modeloUsuarios usuarios;
        usuarios = new modelos.modeloUsuarios();
        Object[][] data;
        data = usuarios.listarUsuarios();
        micontroladorUsuarios = new controladorUsuarios();
        return micontroladorUsuarios.mostrarTabControlUsuarios(tipo, data);    
    }
    
    public JPanel crearControladorTarifasP() {
        modelos.modeloTarifas tarifas;
        tarifas = new modelos.modeloTarifas();
        Object[][] data;
        data = tarifas.listarTarifas();
        micontroladorTarifas = new controladorTarifas();
        return micontroladorTarifas.mostrarTabControlTarifas(tipo, data);    
    }
    
    void crearControladorIngresarJornadas() {
        modelos.modeloClientes clientes;
        modelos.modeloGruas gruas;
        modelos.modeloEmpleados empleados;
        clientes = new modeloClientes();
        gruas = new modelos.modeloGruas();
        empleados = new modelos.modeloEmpleados();
        Object[] dataClientes, dataGruas, dataEmpleados;
        dataClientes = clientes.obtenerRazonClientes();
        dataGruas = gruas.obtenerDescGruas();
        dataEmpleados = empleados.obtenerNombresEmpleados();
        controladores.controladorIngresarJornadas micontroladorIJ;
        micontroladorIJ = new controladorIngresarJornadas();
        micontroladorIJ.mostrarVistaIngresarJornadas(dataClientes, dataGruas, dataEmpleados);
    }
    
    public void crearControladorModificarJornadas(String id) throws ParseException {
        modelos.modeloClientes clientes;
        modelos.modeloGruas gruas;
        modelos.modeloEmpleados empleados;
        clientes = new modeloClientes();
        gruas = new modelos.modeloGruas();
        empleados = new modelos.modeloEmpleados();
        Object[] dataClientes, dataGruas, dataEmpleados;
        dataClientes = clientes.obtenerRazonClientes();
        dataGruas = gruas.obtenerDescGruas();
        dataEmpleados = empleados.obtenerNombresEmpleados();
        controladorModificarJornadas micontroladorMJ;
        micontroladorMJ = new controladorModificarJornadas();
        micontroladorMJ.mostrarVistaModificarJornadas(id, dataClientes, dataGruas, dataEmpleados);
    }
    
    //OTS
    public JPanel crearControladorOtsP() {
        modelos.modeloOts ots;
        ots = new modelos.modeloOts();
        Object[][] data;
        data = ots.listarOts();
        micontroladorOts = new controladorOts();
        return micontroladorOts.mostrarTabControlOts(tipo, data);    
    }
    
    void crearControladorDetalleOts(String id) throws ParseException {
        controladorDetalleOts micontroladorDO;
        micontroladorDO = new controladorDetalleOts();
        micontroladorDO.mostrarVistaDetalleOts(id);
    }
    
    public JPanel crearControladorFacturasP() {
        modelos.modeloOts ots;
        ots = new modelos.modeloOts();
        Object[][] data;
        data = ots.listarFacturas();
        micontroladorFacturas = new controladorFacturas();
        return micontroladorFacturas.mostrarTabControlFacturas(tipo, data);    
    }
    
    public JPanel crearControladorFacturadasP() {
        modelos.modeloFacturas facturas;
        facturas = new modelos.modeloFacturas();
        Object[][] data;
        data = facturas.listarFacturadas();
        micontroladorFacturadas = new controladorFacturadas();
        return micontroladorFacturadas.mostrarTabControlFacturadasP(tipo, data);    
    }
    
    public JPanel crearControladorHistoricoP() {
        modelos.modeloOts ots;
        ots = new modelos.modeloOts();
        Object[][] data;
        data = ots.listarHistoricos();
        micontroladorHistorico = new controladorHistorico();
        return micontroladorHistorico.mostrarTabControlHistorico(tipo, data);    
    }
    
    void crearControladorDetalleFacturas(String id) throws ParseException {
        controladorDetalleFacturas micontroladorDF;
        micontroladorDF = new controladorDetalleFacturas();
        micontroladorDF.mostrarVistaDetalleFacturas(id);
    }
    
    void crearControladorDetalleFacturadas(String id, String tipo) throws ParseException {
        controladorDetalleFacturadas micontroladorDF;
        micontroladorDF = new controladorDetalleFacturadas();
        micontroladorDF.mostrarVistaDetalleFacturadas(id, tipo);
    }
    
    //USUARIOS
    public void crearControladorUsuarios() {
        modelos.modeloUsuarios usuarios;
        usuarios = new modelos.modeloUsuarios();
        String data;
        data = usuarios.obtenerContraseña(user);
        micontroladorUsuarios = new controladorUsuarios();
        micontroladorUsuarios.mostrarVistaCambioClave(tipo, data);
    }
    
    public void crearControladorAgregarUsuario() {
        controladorAgregarUsuario micontrolador = new controladorAgregarUsuario();
        micontrolador.mostrarVistaAgregarUsuario();
    }
    
    public void crearControladorEliminarUsuario(String user){
        controladorEliminarUsuario micontroladorEU;
        micontroladorEU = new controladorEliminarUsuario();
        micontroladorEU.irVistaUsuariosP(user);
    }
    
    public boolean agregarUsuario(String usuario, String pw, String pw2){
        modelos.modeloUsuarios usuarios = new modelos.modeloUsuarios();
        String res = "pw";
        if(pw.compareTo(pw2) == 0){
            res = usuarios.agregarUsuario(usuario, pw);
        }
        
        if(res.compareTo("pw") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Las contraseñas deben ser las mismas", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else if(res.compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Usuario agregado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar al usuario", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    //TARIFAS
    public void crearControladorTarifas() {
        modelos.modeloTarifas tarifas;
        tarifas = new modelos.modeloTarifas();
        Object[][] data;
        data = tarifas.listarTarifas();
        micontroladorTarifas = new controladorTarifas();
        micontroladorTarifas.mostrarVistaTarifas(tipo, data);
    }
    
    public void crearControladorAgregarTarifa() {
        modelos.modeloTonelajes tonelajes;
        tonelajes = new modelos.modeloTonelajes();
        Object[] data;
        data = tonelajes.listarTonelajes();
        controladorAgregarTarifa micontrolador = new controladorAgregarTarifa();
        micontrolador.mostrarVistaAgregarTarifa(data);
    }
    
    public boolean agregarTarifa(String dia, String ton, String horaInicio, String horaFin, String tar){
        modelos.modeloTarifas tarifa = new modelos.modeloTarifas();
        String res = tarifa.agregarTarifa(dia, ton, horaInicio, horaFin, tar);
        if(res.compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Tarifa agregada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else if(res.compareTo("duplicado") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Esta tarifa ya está en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    //PROVEEDORES
    public JPanel crearControladorProveedoresP() {
        modelos2.modeloProveedores proveedores;
        proveedores = new modelos2.modeloProveedores();
        Object[][] data;
        data = proveedores.listarProveedores();
        micontroladorProveedores = new controladorProveedores();
        return micontroladorProveedores.mostrarTabControlProveedores(tipo, data);
    }
    
    public void crearControladorIngresarProveedores() {
        modelos.modeloRegiones regiones = new modeloRegiones();
        Object[][] dataRegiones = regiones.listarRegiones();
        controladorIngresarProveedores micontroladorIP;
        micontroladorIP = new controladorIngresarProveedores();
        micontroladorIP.mostrarVistaIngresarProveedores(dataRegiones);
    }
    
    public void crearControladorEliminarProveedores(String rut){
        controladorEliminarProveedores micontroladorEP;
        micontroladorEP = new controladorEliminarProveedores();
        micontroladorEP.irVistaProveedoresP(rut);
    }
    
    public void crearControladorModificarProveedores(String rut, String nombres) {
        modelos.modeloRegiones regiones = new modeloRegiones();
        Object[][] dataRegiones = regiones.listarRegiones();
        controladorModificarProveedores micontroladorMP;
        micontroladorMP = new controladorModificarProveedores();
        micontroladorMP.mostrarVistaModificarProveedor(rut, nombres, dataRegiones);
    }
    
    public void crearControladorDetalleProveedor(String rut) {
        controladorDetalleProveedores micontroladorDP;
        micontroladorDP = new controladorDetalleProveedores();
        micontroladorDP.mostrarVistaDetalleProveedor(rut);
    }
    
    //COMPRAS
    public JPanel crearControladorComprasP() {
        modelos2.modeloCompras compras;
        compras = new modelos2.modeloCompras();
        Object[][] data;
        data = compras.listarCompras();
        micontroladorCompras = new controladorCompras();
        return micontroladorCompras.mostrarTabControlCompras(tipo, data);
    }
    
    public void crearControladorIngresarCompras() {
        modelos.modeloRegiones regiones = new modeloRegiones();
        modelos2.modeloProveedores proveedores = new modeloProveedores();
        Object dataProveedores[] = proveedores.obtenerRutProveedores();
        Object[][] dataRegiones = regiones.listarRegiones();
        controladorIngresarCompras micontroladorIC;
        micontroladorIC = new controladorIngresarCompras();
        micontroladorIC.mostrarVistaIngresarCompras(dataProveedores);
    }
    
    public void crearControladorEliminarCompras(String id){
        controladorEliminarCompras micontroladorEC;
        micontroladorEC = new controladorEliminarCompras();
        micontroladorEC.irVistaComprasP(id);
    }
    
    public void crearControladorModificarCompras(String id) throws ParseException {
        modelos2.modeloProveedores proveedores = new modeloProveedores();
        Object dataProveedores[] = proveedores.obtenerRutProveedores();
        controladorModificarCompras micontroladorMC;
        micontroladorMC = new controladorModificarCompras();
        micontroladorMC.mostrarVistaModificarCompras(id, dataProveedores);
    }
    
    public void crearControladorDetalleCompra(String id) throws ParseException {
        controladorDetalleCompras micontroladorDC;
        micontroladorDC = new controladorDetalleCompras();
        micontroladorDC.mostrarVistaDetalleCompras(id);
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
        String respuesta = grua.ingresarGrua(data);
        if(respuesta.compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Grua ingresada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else if(respuesta.compareTo("incorrecto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos\n" 
                    + "Verifique que la patente no exista previamente en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos\n" 
                    + "Verifique que los valores corresponden a números", "Error", JOptionPane.ERROR_MESSAGE);
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
        String respuesta = grua.modificarGrua(data, patente);
        if(respuesta.compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Grúa modificada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else if(respuesta.compareTo("incorrecto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar la grúa selecionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos\n" 
                    + "Verifique que los valores corresponden a números", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    String[] obtenerGruaPorPatente(String patente) {
        modelos.modeloGruas grua = new modelos.modeloGruas();
        String[] data = grua.obtenerGruaPorPatente(patente);
        return data;    
    }
    
    boolean ingresarEmpleado(String[] data) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        String respuesta = empleado.ingresarEmpleados(data);
        if(respuesta.compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Trabajador ingresado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else if(respuesta.compareTo("rut incorrecto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos.\n Compruebe que el rut sea correcto",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos.\n El sueldo debe ser un número",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    boolean eliminarEmpleados(String data) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        if(empleado.eliminarEmpleado(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Trabajador eliminado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar el trabajador seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    boolean modificarEmpleado(String[] data, int rut) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        if(empleado.modificarEmpleado(data, rut).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Trabajador modificado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar el trabajador selecionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    String[] obtenerEmpleadoPorRut(String rut) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        String[] data = empleado.obtenerEmpleadoPorRut(rut);
        return data;
    }
    
    public void crearControladorIngresarOT(String[] data, Object[] ciudades) throws ParseException {
        controladorIngresarOts micontroladorOT;
        micontroladorOT = new controladorIngresarOts();
        micontroladorOT.mostrarVistaIngresarOts(data, ciudades);    
    }
    
    public int cambiarClaveUsuario(String pwNueva) {
        controladorCambioClave miControlador = new controladorCambioClave();
        return miControlador.cambiarClave(pwNueva);
    }
    
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
    
    public boolean modificarJornada(String[] data, String id){
        modelos.modeloJornadas jornada = new modelos.modeloJornadas();
        if(jornada.modificarJornada(data, id).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Jornada modificada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar la jornada selecionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public int checkGruaDisp(String pat, String fsal, String hsal, String freg, String hreg){
        modelos.modeloGruas grua = new modelos.modeloGruas();
        if(grua.checkGruaDisp(fsal+" "+hsal, freg+" "+hreg, pat) == 0){
            return 0;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Esta grúa no esta disponible en la fecha especificada", "Error", JOptionPane.ERROR_MESSAGE);
            return 1;
        }
    }
    
    public int checkGruaDispId(String pat, String fsal, String hsal, String freg, String hreg, String id){
        modelos.modeloGruas grua = new modelos.modeloGruas();
        if(grua.checkGruaDispId(fsal+" "+hsal, freg+" "+hreg, pat, id) == 0){
            return 0;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Esta grúa no esta disponible en la fecha especificada", "Error", JOptionPane.ERROR_MESSAGE);
            return 1;
        }
    }
    
    public int checkEmpDisp(String rut, String fsal, String hsal, String freg, String hreg){
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        if(empleado.checkEmpDisp(fsal+" "+hsal, freg+" "+hreg, rut) == 0){
            return 0;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Este empleado no esta disponible en la fecha especificada", "Error", JOptionPane.ERROR_MESSAGE);
            return 1;
        }
    }
    
    public int checkEmpDispId(String rut, String fsal, String hsal, String freg, String hreg, String id){
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        if(empleado.checkEmpDispId(fsal+" "+hsal, freg+" "+hreg, rut, id) == 0){
            return 0;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Este empleado no esta disponible en la fecha especificada", "Error", JOptionPane.ERROR_MESSAGE);
            return 1;
        }
    }
    
    public boolean ingresarOt(String[] data){
        modelos.modeloOts ot = new modelos.modeloOts();
        if(ot.obtenerCodigoOt(data[6]).compareTo("incorrecto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Código de ot duplicado ", "Error", JOptionPane.INFORMATION_MESSAGE);
            return false;
        }
        if(ot.ingresarOt(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Orden de trabajo ingresada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos\n" 
                    + "", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public String ingresarNotaCredito(String id, String razon, String tipo){
        modelos.modeloFacturas factura = new modelos.modeloFacturas();
        String folio = factura.folioNC();
        return factura.ingresarNotaCredito(id, razon, folio, tipo);
    }

    public Object[] cargarComunas(int region) {
        modelos.modeloRegiones comunas = new modelos.modeloRegiones();
        Object[] data = comunas.listaComunas(region);
        return data;
    }
    
    public Object[] cargarCiudades(int region) {
        modelos.modeloRegiones ciudades = new modelos.modeloRegiones();
        Object[] data = ciudades.listaCiudades(region);
        return data;
    }

    Object[] obtenerRazonClientes() {
        modelos.modeloClientes clientes = new modelos.modeloClientes();
        Object[] data = clientes.obtenerRazonClientes();
        return data;
    }

    String obtenerClientePorRazon(String textoCliente) {
        modelos.modeloClientes cliente = new modelos.modeloClientes();
        String rut = cliente.obtenerClientePorRazon(textoCliente);
        return rut;
    }

    String obtenerEmpleadoPorNombre(String textoOperador) {
        modelos.modeloEmpleados empleado = new modelos.modeloEmpleados();
        String rut = empleado.obtenerEmpleadoPorNombre(textoOperador);
        return rut;
    }

    String obtenerGruaPorDesc(String textoGrua) {
        modelos.modeloGruas grua = new modelos.modeloGruas();
        String pat = grua.obtenerGruaPorDesc(textoGrua);
        return pat;
    }

    void crearControladorDetalleJornadas(String id) throws ParseException {
        controladorDetalleJornadas micontroladorDJ;
        micontroladorDJ = new controladorDetalleJornadas();
        micontroladorDJ.mostrarVistaDetalleJornadas(id);
    }

    public String[] obtenerJornadaPorId(String id) {
        modelos.modeloJornadas jornada = new modelos.modeloJornadas();
        String[] datos = jornada.obtenerJornadaPorId(id);
        return datos;
    }

    void crearControladorEliminarJornadas(String id) {
        controladorEliminarJornadas micontroladorEJ;
        micontroladorEJ = new controladorEliminarJornadas();
        micontroladorEJ.irVistaJornadasP(id);
    }

    boolean eliminarJornadas(String id) {
        modelos.modeloJornadas jornada = new modelos.modeloJornadas();
        if(jornada.eliminarJornada(id).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Jornada eliminada con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar la jornada seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }

    void crearControladorIngresarOts(String id) throws ParseException {
        String[] data;
        Object[] ciudades;
        modelos.modeloJornadas jornada = new modeloJornadas();
        modelos.modeloRegiones ciudad = new modeloRegiones();
        data = jornada.obtenerJornadaPorId(id);
        ciudades = ciudad.listaCiudades2();
        controladores.controladorIngresarOts micontroladorIO;
        micontroladorIO = new controladorIngresarOts();
        micontroladorIO.mostrarVistaIngresarOts(data, ciudades);
    }
    
    public String[] obtenerOtPorId(String id) {
        modelos.modeloOts ot = new modelos.modeloOts();
        String[] datos = ot.obtenerOtPorId(id);
        return datos;
    }
    
    public Object[][] obtenerOtPorIdFacturada(String id, String tipo) {
        modelos.modeloOts ot = new modelos.modeloOts();
        Object[][] datos;
        switch(tipo){
            case("notadebito"):
                datos = ot.obtenerOtPorIdFacturadaND(id);
                break;
            case("notacredito"):
                datos = ot.obtenerOtPorIdFacturadaNC(id);
                break;
            default:
                datos = ot.obtenerOtPorIdFacturada(id, tipo);
                break;
        }
        
        return datos;
    }
    
    public boolean eliminarUsuario(String data){
        modelos.modeloUsuarios usuario = new modelos.modeloUsuarios();
        if(usuario.eliminarUsuario(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Usuario eliminado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar el usuario seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    //Funciones PROVEEDORES
    public boolean ingresarProveedor(String[] data){
        modeloProveedores proveedor = new modeloProveedores();
        if(proveedor.ingresarProveedor(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Proveedor ingresado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos\n" 
                    + "verifique que el rut no exista previamente en la base de datos", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean eliminarProveedores(String data){
        modeloProveedores proveedor = new modeloProveedores();
        if(proveedor.eliminarProveedor(data).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Proveedor eliminado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar el proveedor seleccionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean modificarProveedor(String[] data, int rut){
        modeloProveedores proveedor = new modeloProveedores();
        if(proveedor.modificarProveedor(data, rut).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Proveedor modificado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar el proveedor selecionado", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public String[] obtenerProveedorPorRut(String rut){
        modeloProveedores proveedor = new modeloProveedores();
        String[] data = proveedor.obtenerProveedorPorRut(rut);
        return data;
    }
    
    //Funciones COMPRAS

    public String ingresarCompra(String[] data){
        modeloCompras compra = new modeloCompras();
        String id = compra.ingresarCompra(data);
        if(id.compareTo("incorrecto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al ingresar los datos de la compra\n" 
                    , "Error", JOptionPane.ERROR_MESSAGE);
            return "incorrecto";
        }else{
            return id;
        }
    }
    
    public boolean modificarCompra(String[] data, String id){
        modeloCompras compra = new modeloCompras();
        if(compra.modificarCompra(data, id).compareTo("correcto") == 0){
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al modificar la compra selecionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean eliminarCompras(String id){
        modeloCompras compra = new modeloCompras();
        if(compra.eliminarCompra(id).compareTo("correcto") == 0){
            JOptionPane.showMessageDialog(miVistaL, "Compra eliminado con éxito", "Operación exitosa", JOptionPane.INFORMATION_MESSAGE);
            return true;
        }else{
            JOptionPane.showMessageDialog(miVistaL, "Ha ocurrido un error al eliminar la compra seleccionada", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
    }
    
    public boolean ingresarCheques(String[][] data, String id){
        modeloCompras compra = new modeloCompras();
        if(compra.ingresarCheques(data, id).compareTo("correcto") == 0){
            return true;
        }else{
            System.out.println("Error al ingresar cheques");
            return false;
        }
    }
    
    public boolean borrarCheques(String id){
        modeloCompras compra = new modeloCompras();
        if(compra.borrarCuotas(id).compareTo("correcto") == 0){
            return true;
        }else{
            System.out.println("Error al ingresar cheques");
            return false;
        }
    }
    
    public boolean borrarProductos(String id){
        modeloCompras compra = new modeloCompras();
        if(compra.borrarProductos(id).compareTo("correcto") == 0){
            return true;
        }else{
            System.out.println("Error al borrar productos");
            return false;
        }
    }
    
    public boolean ingresarCuotas(String[][] data, String id){
        modeloCompras compra = new modeloCompras();
        if(compra.ingresarCuotas(data, id).compareTo("correcto") == 0){
            return true;
        }else{
            System.out.println("Error al ingresar cuotas");
            return false;
        }
    }
    
    public boolean ingresarProductos(String[][] data, String id){
        modeloCompras compra = new modeloCompras();
        if(compra.ingresarProductos(data, id).compareTo("correcto") == 0){
            return true;
        }else{
            System.out.println("Error al ingresar productos");
            return false;
        }
    }
    
    public String[] obtenerCompraPorId(String id){
        modeloCompras compra = new modeloCompras();
        String[] data = compra.obtenerCompraPorId(id);
        return data;
    }
}
