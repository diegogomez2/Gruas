/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos2;

import modelos2.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class modeloProveedores {
    static String login = "root";
//    static String password = "gruas_205243";
//    static String url = "jdbc:mysql://10.20.224.100:3306/fact_gruas";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    Connection conn = null;
    
    public Object[][] listarProveedores(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Proveedores");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar proveedores");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][5];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_pro, dig_pro, raz_pro, dir_pro,"
                    + " tel_pro, con_pro FROM Proveedores ORDER BY rut_pro");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
                String estdir = res.getString("dir_pro");
                String esttel = res.getString("tel_pro");
                String estcon = res.getString("con_pro");
                data[i] = new String[]{estrut + "-" + estdig, estraz, esttel, estdir, estcon};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarProveedor(String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT into Proveedores (rut_pro, dig_pro,"
                    + "con_pro, raz_pro, gir_pro, cor_pro, tel_pro, dir_pro, reg_pro, ciu_pro,"
                    + "com_pro, obs_pro, for_pro, med_pro) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setString(13, data[12]);
            pstm.setString(14, data[13]);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar proveedor");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String eliminarProveedor(String data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Proveedores WHERE rut_pro = ?");
            pstm.setString(1, data);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar proveedor");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
    
    public String[] obtenerProveedorPorRut(String rut){
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Proveedores WHERE rut_pro = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_pro");
            String estdig = res.getString("dig_pro");
            String estcon = res.getString("con_pro");
            String estraz = res.getString("raz_pro");
            String estgir = res.getString("gir_pro");
            String estcor = res.getString("cor_pro");
            String esttel = res.getString("tel_pro");
            String estdir = res.getString("dir_pro");
            String estreg = res.getString("reg_pro");
            String estciu = res.getString("ciu_pro");
            String estcom = res.getString("com_pro");
            String estobs = res.getString("obs_pro");
            String estfor = res.getString("for_pro");
            String estmed = res.getString("med_pro");
            data = new String[]{estrut + "-" + estdig , estcon, estraz, estgir, estcor,
                esttel, estdir, estreg, estciu, estcom, estobs, estfor, estmed};
        }catch(SQLException e){
            System.out.println("Error al obtener proveedor por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String modificarProveedor(String[] data, int rut){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Proveedores set rut_pro=?, dig_pro=?, "
                    + "con_pro = ?, raz_pro=?, gir_pro=?, cor_pro=?, tel_pro=?, dir_pro=?, reg_pro=?,"
                    + "ciu_pro=?, com_pro=?, obs_pro=?, for_pro=?, med_pro=? WHERE rut_pro=?");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setString(13, data[12]);
            pstm.setString(14, data[13]);
            pstm.setInt(15, rut);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al modificar proveedor");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }

    public Object[] obtenerRazonClientes() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Clientes");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al obtener razon cliente");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT raz_cli FROM Clientes ORDER BY raz_cli");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrazon = res.getString("raz_cli");
                data[i] = estrazon;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;  
    }

    public String obtenerClientePorRazon(String razon) {
        String data = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_cli FROM clientes WHERE raz_cli = ?");
            pstm.setString(1, razon);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_cli");
            data = estrut;
        }catch(SQLException e){
            System.out.println("Error al obtener cliente por razon");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String obtenerObsPorRazon(String razon){
        String data = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT obs_cli FROM clientes WHERE raz_cli = ?");
            pstm.setString(1, razon);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estobs = res.getString("obs_cli");
            data = estobs;
        }catch(SQLException e){
            System.out.println("Error al obtener obs por razon");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
   
}
