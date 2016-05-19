/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Diego
 */
public class modeloEmpleados {
    static String login = "root";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    Connection conn = null;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    
    public java.sql.Date toSqlDate(String data){
        java.util.Date parsed = null;
        try{
            parsed = formatDate.parse(data);
        }catch(ParseException e){
            System.out.println(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        return sqlDate;
    }

    public Object[][] listarEmpleados(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar empleados");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][4];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, dig_emp, nom_emp, apP_emp,"
                    + "car_emp, tel_emp FROM empleados ORDER BY rut_emp");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_emp");
                String estdig = res.getString("dig_emp");
                String estnom = res.getString("nom_emp");
                String estapP = res.getString("apP_emp");
                String estcar = res.getString("car_emp");
                String esttel = res.getString("tel_emp");
                data[i] = new String[]{estrut + "-" + estdig, estnom + " " + estapP, estcar, esttel};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al listar empleados");
            System.out.println(e);
        }
        return data;
    }

    public Object[] obtenerNombresEmpleados() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT nom_emp, apP_emp, apM_emp FROM empleados ORDER BY apP_emp");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnombres = res.getString("nom_emp");
                String estapPaterno = res.getString("apP_emp");
                String estapMaterno = res.getString("apM_emp");
                data[i] = estnombres + " " + estapPaterno + " " + estapMaterno;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al obtener nombres empleados");
            System.out.println(e);
        }
        return data;
    }

    public String ingresarEmpleados(String[] data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into empleados (rut_emp, dig_emp, nom_emp,"
                    + "apP_emp, apM_emp, fnac_emp, tel_emp, cor_emp, car_emp, sueldo_emp, afp_emp, sal_emp,"
                    + "fin_emp, dir_emp, reg_emp, com_emp) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setDate(6, toSqlDate(data[5]));
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setInt(10, Integer.parseInt(data[9]));
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setDate(13, toSqlDate(data[12]));
            pstm.setString(14, data[13]);
            pstm.setString(15, data[14]);
            pstm.setString(16, data[15]);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar empleado");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }catch(NumberFormatException e){
            System.out.println("El sueldo debe ser un valor numérico");
            return "incorrecto";
        }
        return "correcto";
    }

    public String[] obtenerEmpleadoPorRut(String rut) {
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM empleados WHERE rut_emp = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_emp");
            String estdig = res.getString("dig_emp");
            String estnom = res.getString("nom_emp");
            String estapP = res.getString("apP_emp");
            String estapM = res.getString("apM_emp");
            String estfnac = res.getString("fnac_emp");
            String esttel = res.getString("tel_emp");
            String estcor = res.getString("cor_emp");
            String estcar = res.getString("car_emp");
            String estsueldo = res.getString("sueldo_emp");
            String estafp = res.getString("afp_emp");
            String estsal = res.getString("sal_emp");
            String estfin = res.getString("fin_emp");
            String estdir = res.getString("dir_emp");
            String estreg = res.getString("reg_emp");
            String estcom = res.getString("com_emp");
            data = new String[]{estrut + "-" + estdig , estnom, estapP, estapM, estfnac
                    , esttel, estcor, estcar, estsueldo, estafp, estsal, estfin, estdir, estreg, estcom};
        }catch(SQLException e){
            System.out.println("Error al obtener empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }

    public String eliminarEmpleado(String data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM empleados WHERE rut_emp = ?");
            pstm.setString(1, data);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar empleado");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }

    public String modificarEmpleado(String[] data, int rut) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update empleados set rut_emp=?, dig_emp=?,"
                    + "nom_emp=?, apP_emp=?, apM_emp=?, fnac_emp=?, tel_emp=?, cor_emp=?, car_emp=?,"
                    + "sueldo_emp=?, afp_emp=?, sal_emp=?, fin_emp=?, dir_emp=?, reg_emp=?, com_emp=? WHERE rut_emp=?");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setDate(6, toSqlDate(data[5]));
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setInt(10, Integer.parseInt(data[9]));
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setDate(13, toSqlDate(data[12]));
            pstm.setString(14, data[13]);
            pstm.setString(15, data[14]);
            pstm.setString(16, data[15]);
            pstm.setInt(17, rut);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al modificar empleado");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }

    public String obtenerEmpleadoPorNombre(String textoOperador) {
        String data = null;
        String[] nom = (textoOperador.trim()).split(" +");
        int size = nom.length;
        if(size < 3) return null;
        String apM = nom[size-1];
        String apP = nom[size-2];
        String nombres = "";
        for(int i = 0; i < size - 2; i++){
            nombres += nom[i] + " ";
        }
        nombres = nombres.trim();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp FROM empleados WHERE apP_emp = ? "
                    + "AND apM_emp = ? AND nom_emp = ?");
            pstm.setString(1, apP);
            pstm.setString(2, apM);
            pstm.setString(3, nombres);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_emp");
            data = estrut;
        }catch(SQLException e){
            System.out.println("Error al obtener empleado por nombre");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
}
