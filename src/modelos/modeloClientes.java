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
public class modeloClientes {
    static String login = "root";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    Connection conn = null;
    
    public Object[][] listarClientes(){
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
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][4];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_cli, dig_cli, raz_cli, dir_cli,"
                    + " tel_cli FROM Clientes ORDER BY rut_cli");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_cli");
                String estdigito = res.getString("dig_cli");
                //String estnombres = res.getString("nombres");
//                String estapPaterno = res.getString("apPaterno");
                String estrazon = res.getString("raz_cli");
                String estdireccion = res.getString("dir_cli");
                String esttelefono = res.getString("tel_cli");
                data[i][0] = estrut + "-" + estdigito;
                //data[i][1] = estnombres + " " + estapPaterno;
                data[i][1] = estrazon;
                data[i][2] = esttelefono;
                data[i][3] = estdireccion;
//                data[i][4] = estrazon;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarCliente(String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into clientes (rut_cli, dig_cli,"
                    + "raz_cli, gir_cli, cor_cli, tel_cli, fax_cli, cel_cli, dir_cli, reg_cli, com_cli,"
                    + "obs_cli) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[5]);
            pstm.setString(4, data[6]);
            pstm.setString(5, data[7]);
            pstm.setString(6, data[8]);
            pstm.setString(7, data[9]);
            pstm.setString(8, data[10]);
            pstm.setString(9, data[11]);
            pstm.setString(10, data[12]);
            pstm.setString(11, data[13]);
            pstm.setString(12, data[14]);
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
    
    public String eliminarCliente(String data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM clientes WHERE rut_cli = ?");
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
    
    public String[] obtenerClientePorRut(String rut){
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM clientes WHERE rut_cli = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_cli");
            String estdigito = res.getString("dig_cli");
//            String estnombres = res.getString("nombres");
//            String estapPaterno = res.getString("apPaterno");
//            String estapMaterno = res.getString("apMaterno");
            String estrazon = res.getString("raz_cli");
            String estgiro = res.getString("gir_cli");
            String estcorreo = res.getString("cor_cli");
            String esttelefono = res.getString("tel_cli");
            String estfax = res.getString("fax_cli");
            String estcel = res.getString("cel_cli");
            String estdireccion = res.getString("dir_cli");
            String estregion = res.getString("reg_cli");
            String estcomuna = res.getString("com_cli");
            String estobs = res.getString("obs_cli");
            data = new String[]{estrut + "-" + estdigito , null, null, null, estrazon, estgiro, estcorreo, esttelefono, estfax, estcel, estdireccion, estregion, estcomuna, estobs};
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String modificarCliente(String[] data, int rut){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update clientes set rut_cli=?, dig_cli=?,"
                    + "raz_cli=?, gir_cli=?, cor_cli=?, tel_cli=?, fax_cli=?, cel_cli=?, dir_cli=?, reg_cli=?,"
                    + "com_cli=?, obs_cli=? WHERE rut_cli=?");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[5]);
            pstm.setString(4, data[6]);
            pstm.setString(5, data[7]);
            pstm.setString(6, data[8]);
            pstm.setString(7, data[9]);
            pstm.setString(8, data[10]);
            pstm.setString(9, data[11]);
            pstm.setString(10, data[12]);
            pstm.setString(11, data[13]);
            pstm.setString(12, data[14]);
//            pstm.setString(13, data[15]);
//            pstm.setString(14, data[16]);
//            pstm.setString(15, data[17]);
            pstm.setInt(13, rut);
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

    public Object[][] obtenerRazonClientes() {
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
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][1];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT raz_cli FROM Clientes ORDER BY raz_cli");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrazon = res.getString("raz_cli");
                data[i][0] = estrazon;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;  
    }
   
}
