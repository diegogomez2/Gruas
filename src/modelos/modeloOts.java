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
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Pattern;
import static modelos.modeloClientes.url;
import static modelos.modeloJornadas.url;

/**
 *
 * @author diego
 */
public class modeloOts {
    static String login = "root";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    Connection conn = null;
    
    public java.sql.Date toSqlDate(String data){
        java.util.Date parsed = null;
        try{
            parsed = formatDate.parse(data);
        }catch(ParseException e){
            System.out.println(e);
        }
        java.sql.Date sqlDate = new java.sql.Date(parsed.getTime());
        return sqlDate;
    }
    
    public String ingresarOt(String[] data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update jornadas set cont_ot = ?, fec_ot = ?, pag_ot = ?,"
                    + "cond_ot = ?, desp_ot = ?, cod_ot = ?, neto_ot = ?, iva_ot = ?, total_ot = ?, "
                    + "horfin_ot=? where"
                    + " id_jor = ?");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setInt(6, Integer.parseInt(data[6]));
            pstm.setInt(7, Integer.parseInt(data[7]));
            pstm.setInt(8, Integer.parseInt(data[8]));
            pstm.setInt(9, Integer.parseInt(data[9]));
            pstm.setString(10, data[10]);
            pstm.setInt(11, Integer.parseInt(data[5]));
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
    
    public Object[][] listarOts(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM jornadas where not cod_ot = -1");
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
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_jor, fec_ot, raz_cli, gir_cli, dir_cli,"
                    + "reg_cli, com_cli, pat_gru, nom_emp, apP_emp, obs_jor, cod_ot, total_ot, neto_ot, "
                    + "iva_ot, fact_ot FROM Jornadas INNER JOIN"
                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
                    + "= jornadas.rut_emp Where not cod_ot = -1 and fact_ot < 3 ORDER BY cod_ot, fact_ot");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfec = res.getString("fec_ot");
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estreg = res.getString("reg_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_ot");
                String estnet = res.getString("neto_ot");
                String estiva = res.getString("iva_ot");
                //String estgrua = res.getString("pat_gru");
                //String estop = res.getString("nom_emp") + " " + res.getString("apP_emp");
                //String estobs = res.getString("obs_jor");
                String estcodot = res.getString("cod_ot");
                String estfact = res.getString("fact_ot");
                data[i][0] = estcodot;
                data[i][1] = estraz;
                data[i][2] = estgir;
                data[i][3] = estdir;
                data[i][4] = estreg;
                data[i][5] = estcom;
                data[i][6] = estfec;
                data[i][7] = esttot;
                data[i][8] = estnet;
                data[i][9] = estiva;
                data[i][10] = estfact;
                //data[i][1] = estgrua;
                //data[i][3] = estop;
                //data[i][4] = date[0];
                //data[i][5] = null;
                //data[i][6] = estobs;
                //data[i][7] = time[0];
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
    
    public String[] obtenerOtPorId(String id){
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fsal_jor, horsal_jor, freg_jor, horlleg_jor,"
                    + "des_gru, raz_cli, nom_emp, apP_emp, apM_emp, freg_jor, obs_jor, clientes.rut_cli, clientes.dig_cli,"
                    + "gir_cli, dir_cli, tel_cli, ton_gru, fec_ot, cod_ot, pag_ot, cond_ot, cont_ot, "
                    + "total_ot, neto_ot, iva_ot, desp_ot, horfin_ot  FROM jornadas INNER JOIN clientes ON "
                    + "jornadas.rut_cli = clientes.rut_cli INNER JOIN gruas ON gruas.pat_gru = jornadas.pat_gru "
                    + "INNER JOIN empleados ON empleados.rut_emp = jornadas.rut_emp WHERE cod_ot = ?");
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
            String estfot = res.getString("fec_ot");
            String estcond = res.getString("cod_ot");
            String estpago = res.getString("pag_ot");
            String estcont = res.getString("cont_ot");
            String esttot = res.getString("total_ot");
            String estneto = res.getString("neto_ot");
            String estiva = res.getString("iva_ot");
            String estdesp = res.getString("desp_ot");
            String esthorfin = res.getString("horfin_ot");
            data = new String[]{estfsal, esthorsal, estfreg, esthorlleg, estdes, estnom, estobs
                    , estrutcli, estdigcli, estraz, estgir, estdir, esttel, id, estton, estfot, estcond
                    , estpago, estcont, esttot, estneto, estiva, estdesp, esthorfin};
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] getTarifa(String diaInicio, String horaInicio, String day, String ton){
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT prec_tar, horarios.hfin_hor FROM tarifas INNER JOIN"
                    + " tonelajes ON tonelajes.id_ton = tarifas.id_ton INNER JOIN horarios ON"
                    + " horarios.id_hor = tarifas.id_hor INNER JOIN dias ON dias.id_dia = tarifas.id_dia"
                    + " WHERE pes_ton = ? AND hin_hor <= ? And hfin_hor > ? AND dias.id_dia = ?");
            pstm.setString(1, ton);
            pstm.setString(2, horaInicio);
            pstm.setString(3, horaInicio);
            pstm.setString(4, day);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estprec = res.getString("prec_tar");
            String esthfin = res.getString("horarios.hfin_hor");
            data = new String[]{estprec, esthfin};
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public Object[][] listarFacturas(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM jornadas where fact_ot = 1");
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
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_jor, fec_ot, raz_cli, gir_cli, dir_cli,"
                    + "reg_cli, com_cli, obs_jor, cod_ot, total_ot, neto_ot, "
                    + "iva_ot, fact_ot FROM Jornadas INNER JOIN"
                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
                    + "= jornadas.rut_emp Where not cod_ot = -1 and fact_ot = 1 ORDER BY cod_ot, fact_ot");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfec = res.getString("fec_ot");
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estreg = res.getString("reg_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_ot");
                String estnet = res.getString("neto_ot");
                String estiva = res.getString("iva_ot");
                String estcodot = res.getString("cod_ot");
                String estfact = res.getString("fact_ot");
                data[i][0] = estcodot;
                data[i][1] = estraz;
                data[i][2] = estgir;
                data[i][3] = estdir;
                data[i][4] = estreg;
                data[i][5] = estcom;
                data[i][6] = estfec;
                data[i][7] = esttot;
                data[i][8] = estnet;
                data[i][9] = estiva;
                data[i][10] = estfact;
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
    
    public String getIdFactura(String id){
        String idFact = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fact_ot FROM jornadas where cod_ot = ?");
             pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            idFact = res.getString("fact_ot");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return idFact;
    }
    
    public String ingresarFactura(String idOt){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update jornadas set fact_ot = 1 where cod_ot = ?");
            pstm.setString(1, idOt);
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
    
    public String[] obtenerFacturaPorId(String idOt){
        String[] data = new String[10];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT clientes.rut_cli, raz_cli, gir_cli,"
                    + "dir_cli, reg_cli, com_cli, total_ot, neto_ot, iva_ot, fact_ot FROM jornadas INNER JOIN clientes"
                    + " ON clientes.rut_cli = jornadas.rut_cli where cod_ot = ?");
            pstm.setString(1, idOt);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_cli");
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estreg = res.getString("reg_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_ot");
                String estnet = res.getString("neto_ot");
                String estiva = res.getString("iva_ot");
                //String estcodot = res.getString("cod_ot");
                String estfact = res.getString("fact_ot");
                data[0] = estrut;
                data[1] = estraz;
                data[2] = estgir;
                data[3] = estdir;
                data[4] = estreg;
                data[5] = estcom;
                data[6] = esttot;
                data[7] = estnet;
                data[8] = estiva;
                data[9] = estfact;
                i++;
            }
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
}
