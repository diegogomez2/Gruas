/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import javax.swing.JButton;

/**
 *
 * @author diego
 */
public class modeloJornadas {
    
    static String login = "root";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat formatDateTime = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    DateFormat formatClock = new SimpleDateFormat("HH:mm:ss");
    Connection conn = null;

    public String toSqlDateTime(String data){
        String datetime = null;
        java.util.Date parsed = null;
        java.util.Date formated = new java.util.Date();
        try{
            parsed = formatDateTime.parse(data);
            String currentTime = formatDateTime.format(parsed);
        }catch(ParseException e){
            System.out.println(e);
        }
        return datetime;
    }
    
    public Object[][] listarJornadas() {
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM jornadas");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][8];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_jor, fsal_jor, raz_cli,"
                    + "pat_gru, nom_emp, apP_emp, freg_jor, obs_jor, id_ot FROM Jornadas INNER JOIN"
                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
                    + "= jornadas.rut_emp ORDER BY id_jor");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("id_jor");
                String estfsal = res.getString("fsal_jor");
                String estcli = res.getString("raz_cli");
                String estgrua = res.getString("pat_gru");
                String estop = res.getString("nom_emp") + " " + res.getString("apP_emp");
                String estfreg = res.getString("freg_jor");
                String estobs = res.getString("obs_jor");
                String estidot = res.getString("id_ot");
                String[] date = estfsal.split(" ");
                String[] time = date[1].split(Pattern.quote("."));
                data[i][0] = estid;
                data[i][1] = estgrua;
                data[i][2] = estcli;
                data[i][3] = estop;
                data[i][4] = date[0];
                data[i][5] = estidot;
                data[i][6] = estobs;
                data[i][7] = time[0];
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }

    public String ingresarJornada(String[] data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into jornadas (fsal_jor, pat_gru, "
                    + "rut_cli, rut_emp, freg_jor, obs_jor) values (?, ?, ?, ?, ?, ?)");
            pstm.setString(1, data[0] + " " + data[1]);
            pstm.setString(2, data[2]);
            pstm.setInt(3, Integer.parseInt(data[3]));
            pstm.setInt(4, Integer.parseInt(data[4]));
            pstm.setString(5, data[5] + " " + data[6]);
            pstm.setString(6, data[7]);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }

    public String[] obtenerJornadaPorId(String id) {
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fsal_jor, des_gru, raz_cli,"
                    + "nom_emp, apP_emp, apM_emp, freg_jor, obs_jor, clientes.rut_cli, clientes.dig_cli,"
                    + "gir_cli FROM jornadas INNER JOIN clientes ON "
                    + "jornadas.rut_cli = clientes.rut_cli INNER JOIN gruas ON gruas.pat_gru = jornadas.pat_gru "
                    + "INNER JOIN empleados ON empleados.rut_emp = jornadas.rut_emp WHERE id_jor = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrutcli = res.getString("clientes.rut_cli");
            String estdigcli = res.getString("clientes.dig_cli");
            String estfsal = res.getString("fsal_jor");
            String estdes = res.getString("des_gru");
            String estraz = res.getString("raz_cli");
            String estnom = res.getString("nom_emp") + " " + res.getString("apP_emp") + " " + res.getString("apM_emp");
            String estfreg = res.getString("freg_jor");
            String estobs = res.getString("obs_jor");
            String estgir = res.getString("gir_cli");
            data = new String[]{estfsal, estdes, estraz, estnom, estfreg, estobs, estrutcli, estdigcli, estraz, estgir};
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }

    public String eliminarJornada(String id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM jornadas WHERE id_jor = ?");
            pstm.setString(1, id);
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
    
}
