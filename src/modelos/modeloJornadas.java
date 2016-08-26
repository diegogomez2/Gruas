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
import java.sql.Types;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author diego
 */
public class modeloJornadas {
    
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
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
            System.out.println("Error al listar jornadas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][8];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_jor, fsal_jor, horsal_jor, raz_cli,"
                    + "coalesce(des_gru,'') as des_gru, coalesce(nom_emp,'') as nom_emp, coalesce(apP_emp,'') "
                    + "as apP_emp, freg_jor, obs_jor FROM Jornadas INNER JOIN"
                    + " clientes ON clientes.rut_cli = jornadas.rut_cli LEFT JOIN empleados ON empleados.rut_emp "
                    + "= jornadas.rut_emp LEFT JOIN gruas ON gruas.pat_gru = jornadas.pat_gru Where "
                    + "cod_ot = -1 ORDER BY id_jor");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("id_jor");
                String estfsal = res.getString("fsal_jor");
                String esthorsal = res.getString("horsal_jor");
                java.util.Date fecha = formatDate.parse(estfsal);
                estfsal = newFormat.format(fecha);
                String estcli = res.getString("raz_cli");
                String estgrua = res.getString("des_gru");
                String estop = res.getString("nom_emp") + " " + res.getString("apP_emp");
                String estobs = res.getString("obs_jor");
                data[i] = new String[]{estid, estgrua, estcli, estop, estfsal, esthorsal, estobs};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al listar jornadas");
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
            PreparedStatement pstm = conn.prepareStatement("insert into jornadas (fsal_jor, horsal_jor,"
                    + " pat_gru, rut_cli, rut_emp, freg_jor, horlleg_jor, obs_jor, diasal_jor, dialleg_jor, fhsal_jor,"
                    + "fhreg_jor) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            if(data[2].compareTo("") == 0){
                pstm.setNull(3, Types.VARCHAR);
            }else{
                pstm.setString(3, data[2]);
            }
            pstm.setInt(4, Integer.parseInt(data[3]));
            if(data[4].compareTo("") == 0){
                pstm.setNull(5, Types.INTEGER);
            }else{
                pstm.setInt(5, Integer.parseInt(data[4]));
            }
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar jornada");
            System.out.println(e);
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
                    + "coalesce(des_gru,'') as des_gru, raz_cli, coalesce(nom_emp,'') as nom_emp, "
                    + "coalesce(apP_emp,'') as apP_emp, coalesce(apM_emp,'') as apM_emp, freg_jor, obs_jor, clientes.rut_cli, clientes.dig_cli,"
                    + "gir_cli, dir_cli, tel_cli, ton_gru, ciu_cli, coalesce(obs_cli,'') as obs_cli FROM jornadas INNER JOIN clientes ON "
                    + "jornadas.rut_cli = clientes.rut_cli LEFT JOIN gruas ON gruas.pat_gru = jornadas.pat_gru "
                    + "LEFT JOIN empleados ON empleados.rut_emp = jornadas.rut_emp WHERE id_jor = ?");
            pstm.setString(1, id);
            System.out.println(pstm);
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
            String estciu = res.getString("ciu_cli");
            String estobscli = res.getString("obs_cli");
            data = new String[]{estfsal, esthorsal, estfreg, esthorlleg, estdes, estnom, estobs
                    , estrutcli, estdigcli, estraz, estgir, estdir, esttel, id, estton, estciu, estobscli};
        }catch(SQLException e){
            System.out.println("Error obtener jornada por id");
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
            System.out.println("Error eliminar jornada");
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
            if(data[2].compareTo("") == 0){
                pstm.setNull(3, Types.VARCHAR);
            }else{
                pstm.setString(3, data[2]);
            }
            pstm.setString(4, data[3]);
            if(data[4].compareTo("") == 0){
                pstm.setNull(5, Types.VARCHAR);
            }else{
                pstm.setString(5, data[4]);
                
            }
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, id);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error modificar jornada");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String[] obtenerClienteIdJornada(String id){
        String[] data = new String[5];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT clientes.rut_cli, dig_cli, raz_cli, gir_cli,"
                    + "dir_cli, ciu_cli, com_cli FROM jornadas INNER JOIN clientes"
                    + " ON clientes.rut_cli = jornadas.rut_cli where id_jor = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_cli");
            String estdig = res.getString("dig_cli");
            String estraz = res.getString("raz_cli");
            String estgir = res.getString("gir_cli");
            String estdir = res.getString("dir_cli");
            String estciu = res.getString("ciu_cli");
            String estcom = res.getString("com_cli");
            data = new String[]{estrut + "-" + estdig, estraz, estgir, estdir, estciu, estcom};           
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener factura por id");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
    
    public String obtenerGruaPorIdJornada(String id){
        String data = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT des_gru FROM jornadas INNER JOIN gruas"
                    + " ON gruas.pat_gru = jornadas.pat_gru where id_jor = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estgru = res.getString("des_gru");
            data = estgru;
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener factura por id");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
}
