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
import static modelos.modeloClientes.url;

/**
 *
 * @author diego
 */
public class modeloTarifas {
    static String login = "root";
//    static String password = "gruas_205243";
//    static String url = "jdbc:mysql://10.20.224.100:3306/fact_gruas";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    Connection conn = null;
    
    public Object[][] listarTarifas(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM tarifas");
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
            PreparedStatement pstm = conn.prepareStatement("SELECT pes_ton, tarifas.id_dia, hin_hor, "
                    + "hfin_hor, prec_tar  FROM tarifas INNER JOIN dias ON tarifas.id_dia = dias.id_dia "
                    + "INNER JOIN horarios ON horarios.id_hor = tarifas.id_hor inner join tonelajes ON "
                    + "tonelajes.id_ton = tarifas.id_ton ORDER BY id_tar");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estpes = res.getString("pes_ton");
                String estdia = res.getString("tarifas.id_dia");
                String esthin = res.getString("hin_hor");
                String esthfin = res.getString("hfin_hor");
                String estprec = res.getString("prec_tar");
                if(estdia.compareTo("1") == 0){
                    estdia = "Lunes - Viernes";
                }else if(estdia.compareTo("2") == 0){
                    estdia = "Sábado";
                }else{
                    estdia = "Domingo";
                }
                data[i] = new String[]{estpes, estdia, esthin+"-"+ esthfin, estprec};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar tarifas");
            System.out.println(e);
        }
        return data;
    }
    
    public String agregarTarifa(String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into tarifas (rut_cli, dig_cli,"
                    + "con_cli, raz_cli, gir_cli, cor_cli, tel_cli, cel_cli, dir_cli, reg_cli, ciu_cli,"
                    + "com_cli, obs_cli) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
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
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar cliente");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
}
