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
    
    public int obtenerDescIsapre(String rut){
        int desc = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT val_isa_emp val FROM Empleados WHERE "
                    + "rut_emp = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            desc = res.getInt("val");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener val isapre empleado");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return desc;
    }
    
    public String obtenerIsapreEmpleado(String rut){
        String desc = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT isa_emp isa FROM Empleados WHERE "
                    + "rut_emp = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            desc = res.getString("isa");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener isapre empleado");
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
            System.out.println("Error obtener desc salud");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return desc;
    }
    
    public int obtenerBono300(){
        int desc = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT val_bon val FROM Bonos WHERE tie_bon = ?");
            pstm.setString(1, "300");
            ResultSet res = pstm.executeQuery();
            res.next();
            desc = res.getInt("val");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener bono 300");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return desc;
    }
    

    
    public String[][] obtenerTablaImpuesto(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM impuesto_renta");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener tabla impuesto");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        String[][] data = new String[registros][4];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT val_val * men_ren as men, val_val * may_ren as may, "
                    + "fac_ren fac, val_val * des_ren as des FROM impuesto_renta, valores");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estmen = res.getString("men");
                String estmay = res.getString("may");
                String estfac = res.getString("fac");
                String estdes = res.getString("des");
                data[i] = new String[]{estmen, estmay, estfac, estdes};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    
    public int obtenerUTM(){
        int utm = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT val_val val FROM valores WHERE nom_val = 'UTM'");
            ResultSet res = pstm.executeQuery();
            res.next();
            utm = res.getInt("val");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error obtener valor utm");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return utm;
    }
    
    public int cambiarUTM(String utm){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Valores set val_val=? WHERE nom_val = 'UTM'");
            pstm.setString(1, utm);
            pstm.executeUpdate();
            return 1;
       }catch(SQLException e){
            System.out.println("Error cambiar utm");
            System.out.println(e);
            return 0;
       }catch(ClassNotFoundException e){
            System.out.println(e);
            return 0;
       }
    }
}
