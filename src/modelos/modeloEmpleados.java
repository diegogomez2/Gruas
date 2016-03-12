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

/**
 *
 * @author Diego
 */
public class modeloEmpleados {
    static String login = "root";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/factgruas";
    Connection conn = null;

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
        
        Object[][] data = new String[registros][3]; //arreglar 15
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut, digito, nombres, apPaterno, cargo FROM empleados ORDER BY rut");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut");
                String estdigito = res.getString("digito");
                String estnombres = res.getString("nombres");
                String estapPaterno = res.getString("apPaterno");
                String estcargo = res.getString("cargo");
                data[i][0] = estrut + "-" + estdigito;
                data[i][1] = estnombres + " " + estapPaterno;
                data[i][2] = estcargo;
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
    
}
