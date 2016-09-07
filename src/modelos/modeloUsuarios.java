/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import controladores.controladorPrincipal;
import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Diego
 */
public class modeloUsuarios {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    
    public int verificarLogin(String rut, String pass){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT pass FROM usuarios WHERE user = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            while(res.next()){
                String contraseña = res.getString("pass");
                if(contraseña.compareTo(pass) == 0){
                    return 1;
                }
            }
            res.close();
            pstm.close();
            conn.close();
        }
        catch(SQLException | ClassNotFoundException e){
            System.out.println("Error verificar login");
            System.out.println(e);
            return 2;
        }
        return 0;
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
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error verificar login");
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
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error cambiar clave");
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
    
    public int comprobarUsuario(String user){
        try{
            int registros = 0;
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Usuarios WHERE "
                    + "user = ?");
            pstm.setString(1, user);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();  
            return registros;
        }catch(SQLException e){
            System.out.println("Error al comprobar usuario");
            System.out.println(e);
            return 1;
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return 1;
        }
    }
    
}
