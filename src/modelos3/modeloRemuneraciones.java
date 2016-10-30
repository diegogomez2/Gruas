/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import modelos.Connector;

/**
 *
 * @author Diego
 */
public class modeloRemuneraciones {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
        
    public int obtenerSueldoMin(){
        int sueldo_min = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT val_sue val FROM Sueldos WHERE nom_sue = 'minimo'");
            ResultSet res = pstm.executeQuery();
            res.next();
            sueldo_min = res.getInt("val");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener sueldo minimo");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return sueldo_min;
    }
    
    public int obtenerSueldoBase(){
        int sueldo_base = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT val_sue val FROM Sueldos WHERE nom_sue = 'base'");
            ResultSet res = pstm.executeQuery();
            res.next();
            sueldo_base = res.getInt("val");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener sueldo base");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return sueldo_base;
    }
    
    public int editarSueldos(String min, String base){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Sueldos set val_sue=? WHERE nom_sue = 'minimo'");
            pstm.setString(1, min);
            pstm.executeUpdate();
            pstm = conn.prepareStatement("UPDATE Sueldos set val_sue=? WHERE nom_sue = 'base'");
            pstm.setString(1, base);
            pstm.executeUpdate();
            pstm.close();
            return 1;
       }catch(SQLException e){
            System.out.println("Error editar sueldos");
            System.out.println(e);
            return 0;
       }catch(ClassNotFoundException e){
            System.out.println(e);
            return 0;
       }
    }
    
    public int obtenerBonoAnt(String ant){
        int bono = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT MAX(val_bon) val FROM bonos where tie_bon <= ?");
            pstm.setString(1, ant);
            ResultSet res = pstm.executeQuery();
            res.next();
            bono = res.getInt("val");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener bono ant");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return bono;
    }
    
    public int obtenerDescAFP(String afp){
        int desc = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT des_afp des FROM Afps WHERE nom_afp = ?");
            pstm.setString(1, afp);
            ResultSet res = pstm.executeQuery();
            res.next();
            desc = res.getInt("des");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener desc afp");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return desc;
    }
    
    public int obtenerDescSalud(String salud){
        int desc = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT des_sal des FROM Salud WHERE nom_sal = ?");
            pstm.setString(1, salud);
            ResultSet res = pstm.executeQuery();
            res.next();
            desc = res.getInt("des");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener desc afp");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return desc;
    }
}
