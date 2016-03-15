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
    static String url = "jdbc:mysql://localhost:3306/factgruas";
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
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][4]; //arreglar 15
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut, digito, nombres, apPaterno, cargo, telefono FROM empleados ORDER BY rut");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut");
                String estdigito = res.getString("digito");
                String estnombres = res.getString("nombres");
                String estapPaterno = res.getString("apPaterno");
                String estcargo = res.getString("cargo");
                String esttel = res.getString("telefono");
                data[i][0] = estrut + "-" + estdigito;
                data[i][1] = estnombres + " " + estapPaterno;
                data[i][2] = estcargo;
                data[i][3] = esttel;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }

    public Object[][] obtenerNombresEmpleados() {
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
        
        Object[][] data = new String[registros][1]; //arreglar 15
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT nombres, apPaterno FROM empleados ORDER BY nombres");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnombres = res.getString("nombres");
                String estapPaterno = res.getString("apPaterno");
                data[i][0] = estnombres + " " + estapPaterno;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }

    public String ingresarEmpleados(String[] data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into empleados (rut, digito, nombres,"
                    + "apPaterno, apMaterno, fechaNac, telefono, correo, cargo, sueldo, afp, salud, fechain, direccion,"
                    + "region, comuna) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }

    public String[] obtenerEmpleadoPorRut(String rut) {
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM empleados WHERE rut = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut");
            String estdigito = res.getString("digito");
            String estnombres = res.getString("nombres");
            String estapPaterno = res.getString("apPaterno");
            String estapMaterno = res.getString("apMaterno");
            String estfnac = res.getString("fechaNac");
            String esttel = res.getString("telefono");
            String estcorreo = res.getString("correo");
            String estcargo = res.getString("cargo");
            String estsueldo = res.getString("sueldo");
            String estafp = res.getString("afp");
            String estsalud = res.getString("salud");
            String estfin = res.getString("fechain");
            String estdir = res.getString("direccion");
            String estregion = res.getString("region");
            String estcom = res.getString("comuna");
            data = new String[]{estrut + "-" + estdigito , estnombres, estapPaterno, estapMaterno, estfnac
                    , esttel, estcorreo, estcargo, estsueldo, estafp, estsalud, estfin, estdir, estregion, estcom};
        }catch(SQLException e){
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
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM empleados WHERE rut = ?");
            pstm.setString(1, data);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
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
            PreparedStatement pstm = conn.prepareStatement("update empleados set rut=?, digito=?, nombres=?,"
                    + "apPaterno=?, apMaterno=?, fechanac=?, telefono=?, correo=?, cargo=?, sueldo=?, "
                    + "afp=?, salud=?, fechain=?, direccion=?, region=?, comuna=? WHERE rut=?");
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
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
}
