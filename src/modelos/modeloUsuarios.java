/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.controladorPrincipal;
import java.net.ConnectException;
import java.sql.*;
import javax.swing.JOptionPane;
import static modelos.modeloClientes.url;

/**
 *
 * @author Diego
 */
public class modeloUsuarios {
    static String login = "root";
//    static String password = "gruas_205243";
//    static String url = "jdbc:mysql://10.20.224.100:3306/fact_gruas";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    Connection conn = null;
    
    public String verificarLogin(String rut, String pass){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM usuarios WHERE user = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            
            while(res.next()){
                String contraseña = res.getString("pass");
                if(contraseña.compareTo(pass) == 0){
                    return "correcto";
                }
            }
            pstm.close();
        }
        catch(SQLException e){
            System.out.println("Error verificar login");
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "La conexión con la base de datos falló");
        }catch(ClassNotFoundException e){
            System.out.println(e);
            JOptionPane.showMessageDialog(null, "La conexión con la base de datos falló");
        }
        return "incorrecto";
    }

    public String obtenerContraseña(String user) {
        String contraseña = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT pass FROM usuarios WHERE user = ?");
            pstm.setString(1, user);
            ResultSet res = pstm.executeQuery();
            
            while(res.next()){
                contraseña = res.getString("pass");
            }
            pstm.close();
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }catch(ClassNotFoundException e){
            JOptionPane.showMessageDialog(null, e);
            System.out.println(e);
        }
        return contraseña;
    }

    public String cambiarClave(String pwNueva) {
        try{
            controladores.controladorPrincipal miControlador = new controladorPrincipal();
            String user = miControlador.user;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE usuarios set pass=? where user = ?");
            pstm.setString(1, pwNueva);
            pstm.setString(2, user);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error cambiar clave");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String agregarUsuario(String usuario, String pw){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into usuarios (user, pass) values (?, ?)");
            pstm.setString(1, usuario);
            pstm.setString(2, pw);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al agregar usuario");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public Object[][] listarUsuarios(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Usuarios");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar usuarios");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][1];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT user FROM Usuarios ORDER BY user");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estuser = res.getString("user");
                data[i] = new String[]{estuser};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String eliminarUsuario(String data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM usuarios WHERE user = ?");
            pstm.setString(1, data);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar usuario");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
}
