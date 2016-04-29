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
    Connection conn = null;
    
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat formatDateTime = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
    DateFormat formatClock = new SimpleDateFormat("HH:mm:ss");

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
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM jornadas where cod_ot = -1");
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
            PreparedStatement pstm = conn.prepareStatement("SELECT id_jor, fsal_jor, horsal_jor, raz_cli,"
                    + "pat_gru, nom_emp, apP_emp, freg_jor, obs_jor FROM Jornadas INNER JOIN"
                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
                    + "= jornadas.rut_emp Where cod_ot = -1 ORDER BY id_jor");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("id_jor");
                String estfsal = res.getString("fsal_jor");
                String esthorsal = res.getString("horsal_jor");
                java.util.Date fecha = formatDate.parse(estfsal);
                estfsal = newFormat.format(fecha);
                String estcli = res.getString("raz_cli");
                String estgrua = res.getString("pat_gru");
                String estop = res.getString("nom_emp") + " " + res.getString("apP_emp");
                String estfreg = res.getString("freg_jor");
                String estobs = res.getString("obs_jor");
                data[i][0] = estid;
                data[i][1] = estgrua;
                data[i][2] = estcli;
                data[i][3] = estop;
                data[i][4] = estfsal;
                data[i][5] = esthorsal;
                data[i][6] = estobs;
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

    public String obtenerIdOt(String id){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM ots where id_jor = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        if(registros == 0) return "null";
        
        String data = "null";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT id_ot From ots Where id_jor = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estidot = res.getString("id_ot");
            data = estidot;
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarJornada(String[] data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into jornadas (fsal_jor, horsal_jor,"
                    + " pat_gru, rut_cli, rut_emp, freg_jor, horlleg_jor, obs_jor, diasal_jor, dialleg_jor) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setInt(4, Integer.parseInt(data[3]));
            pstm.setInt(5, Integer.parseInt(data[4]));
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
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
            PreparedStatement pstm = conn.prepareStatement("SELECT fsal_jor, horsal_jor, freg_jor, horlleg_jor,"
                    + "des_gru, raz_cli, nom_emp, apP_emp, apM_emp, freg_jor, obs_jor, clientes.rut_cli, clientes.dig_cli,"
                    + "gir_cli, dir_cli, tel_cli, ton_gru FROM jornadas INNER JOIN clientes ON "
                    + "jornadas.rut_cli = clientes.rut_cli INNER JOIN gruas ON gruas.pat_gru = jornadas.pat_gru "
                    + "INNER JOIN empleados ON empleados.rut_emp = jornadas.rut_emp WHERE id_jor = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrutcli = res.getString("clientes.rut_cli");
            String estdigcli = res.getString("clientes.dig_cli");
            String estfsal = res.getString("fsal_jor");
            String esthorsal = res.getString("horsal_jor");
            String estdes = res.getString("des_gru");
            String estraz = res.getString("raz_cli");
            String estnom = res.getString("nom_emp") + " " + res.getString("apP_emp") + " " + res.getString("apM_emp");
            String estfreg = res.getString("freg_jor");
            String esthorlleg = res.getString("horlleg_jor");
            String estobs = res.getString("obs_jor");
            String estgir = res.getString("gir_cli");
            String estdir = res.getString("dir_cli");
            String esttel = res.getString("tel_cli");
            String estton = res.getString("ton_gru");
            data = new String[]{estfsal, esthorsal, estfreg, esthorlleg, estdes, estnom, estobs
                    , estrutcli, estdigcli, estraz, estgir, estdir, esttel, id, estton};
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
    
    public String modificarJornada(String[] data, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update jornadas set fsal_jor=?, horsal_jor=?, "
                    + "pat_gru=?, rut_cli=?, rut_emp=?, freg_jor = ?, horlleg_jor=?, obs_jor=?"
                    + " WHERE id_jor=?");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, id);
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
    
}
