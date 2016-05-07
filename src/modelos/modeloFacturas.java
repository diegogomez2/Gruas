/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author diego
 */

import java.beans.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import static modelos.modeloClientes.url;

public class modeloFacturas {
    static String login = "root";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    Connection conn = null;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public String ingresarFacturada(String fec, int valorNeto, int valorIva, int valorTotal){        
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into facturas (fec_fac, neto_fac,"
                    + "iva_fac, tot_fac)"
                    + " values (?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fec);
            pstm.setInt(2, valorNeto);
            pstm.setInt(3, valorIva);
            pstm.setInt(4, valorTotal);
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            res.next();
            id = res.getString(1);
            pstm.close();
        }catch(SQLException e){
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id;
    }
    
    public Object[][] listarFacturadas(){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total from  facturas");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][11];
        System.out.println(registros);
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT f.*, raz_cli, gir_cli, dir_cli, "
                    + "ciu_cli, com_cli FROM facturas f INNER JOIN(SELECT  id_fac, rut_cli " +
                    "FROM jornadas GROUP BY id_fac) b ON b.id_fac = f.id_fac " +
                    "INNER JOIN clientes c on c.rut_cli = b.rut_cli");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("id_fac");
                String estfec = res.getString("fec_fac");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("tot_fac");
                String estnet = res.getString("neto_fac");
                String estiva = res.getString("iva_fac");
                //String estcodot = res.getString("cod_ot");
                //String estfact = res.getString("fact_ot");
                //data[i] = new String[]{estid, estraz, estgir, estdir, estciu, estcom, estfec, estnet, estiva, esttot };
                data[i][0] = estid;
                data[i][1] = estraz;
                data[i][2] = estgir;
                data[i][3] = estdir;
                data[i][4] = estciu;
                data[i][5] = estcom;
                data[i][6] = estfec;
                data[i][7] = estnet;
                data[i][9] = esttot;
                data[i][8] = estiva;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println("AKA"+e);
        }
        return data;
    }
}
