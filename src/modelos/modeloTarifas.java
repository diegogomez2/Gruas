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
                    + "tonelajes.id_ton = tarifas.id_ton ORDER BY tonelajes.id_ton");
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
    
    public String agregarTarifa(String dia, String ton, String hin, String hfin, String prec){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT Count(*) as total from horarios where hin_hor = ?"
                    + "and hfin_hor = ?");
            pstm.setString(1, hin);
            pstm.setString(2, hfin);
            ResultSet res = pstm.executeQuery();
            res.next();
            int registros = res.getInt("total");
            pstm.close();
            res.close();
            //GET TON
            pstm = conn.prepareStatement("SELECT id_ton from tonelajes where pes_ton = ?");
            pstm.setString(1, ton);
            res = pstm.executeQuery();
            res.next();
            String pes = res.getString("id_ton");
            String id = "";
            pstm.close();
            res.close();
            
            if(registros > 0){
                //GET HOR
                pstm = conn.prepareStatement("SELECT id_hor from horarios where hin_hor = ? and hfin_hor = ?");
                pstm.setString(1, hin);
                pstm.setString(2, hfin);
                res = pstm.executeQuery();
                res.next();
                id = res.getString("id_hor");
                pstm.close();
                res.close();
                                
            }else{
                pstm = conn.prepareStatement("INSERT INTO horarios (hin_hor, hfin_hor) values (?, ?)", 
                        PreparedStatement.RETURN_GENERATED_KEYS);
                pstm.setString(1, hin);
                pstm.setString(2, hfin);
                pstm.execute();
                res = pstm.getGeneratedKeys();
                res.next();
                id = res.getString(1);
                pstm.close();
                res.close();
            }
            //CHECK
            pstm = conn.prepareStatement("SELECT COUNT(*) as total from tarifas where id_ton = ? and id_dia = ? "
                    + "AND id_hor = ? AND prec_tar = ?");
            pstm.setString(1, pes);
            pstm.setString(2, dia);
            pstm.setString(3, id);
            pstm.setString(4, prec);
            res = pstm.executeQuery();
            res.next();
            int flag = res.getInt("total");
            
            if(flag > 0){
                return "duplicado";
            }else{
                    //INSERT
                pstm = conn.prepareStatement("INSERT INTO tarifas (id_ton, id_dia, id_hor, prec_tar) values "
                         + "(?, ?, ?, ?)");
                pstm.setString(1, pes);
                pstm.setString(2, dia);
                pstm.setString(3, id);
                pstm.setString(4, prec);
                pstm.execute();
                pstm.close(); 
            }
            
        }catch(SQLException e){
            System.out.println("Error al ingresar tarifa");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
}
