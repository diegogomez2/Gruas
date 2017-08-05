/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import modelos.Connector;

/**
 *
 * @author diego
 */
public class modeloJornadasOC {


/**
 *
 * @author diego
 */
    
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
    
    public Object[][] listarJornadasOC() {
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) total FROM Jornadas_oc WHERE cod_oc = -1");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar jornadas oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][8];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_joroc, fsal_joroc, horsal_joroc, raz_cli, freg_joroc, obs_joroc, coalesce(countg, 0) countg, "
                    + "coalesce(counte, 0) counte, tras_oc FROM Jornadas_oc "
                    + "INNER JOIN clientes ON clientes.rut_cli = jornadas_oc.rut_cli LEFT JOIN (SELECT id_joroc, COUNT(*) countg FROM "
                    + "Detalle_oc_gru GROUP BY id_joroc) gruas USING(id_joroc) LEFT JOIN (SELECT id_joroc, COUNT(*) counte FROM Detalle_oc_emp GROUP BY id_joroc) emp USING(id_joroc) "
                    + "WHERE cod_oc = -1 ORDER BY id_joroc");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("id_joroc");
                String estfsal = res.getString("fsal_joroc");
                String esthorsal = res.getString("horsal_joroc");
                java.util.Date fecha = formatDate.parse(estfsal);
                estfsal = newFormat.format(fecha);
                String estcli = res.getString("raz_cli");
                String estobs = res.getString("obs_joroc");
                String estcgru = res.getString("countg");
                String estcemp = res.getString("counte");
                String esttras = res.getString("tras_oc");
                data[i] = new String[]{estid, estcli, estfsal, estobs, estcgru, estcemp, esttras};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al listar jornadas oc");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarJornadaOC(String[] data) {
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Jornadas_oc (fsal_joroc, horsal_joroc,"
                    + " rut_cli, freg_joroc, horlleg_joroc, obs_joroc) "
                    + "values (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setInt(3, Integer.parseInt(data[2]));
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            res.next();
            id = res.getString(1);
            pstm.close();
            res.close();
        }catch(SQLException e){
            System.out.println("Error ingresar jornada oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id;
    }

    public String ingresarDetalleGrua(String id, String pat, String fhsal, String fhreg) {
        if(fhsal.compareTo(" ") == 0) fhsal = null;
        if(fhreg.compareTo(" ") == 0) fhreg = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Detalle_oc_gru (id_joroc, pat_gru, fhsal_det_gru, fhreg_det_gru) "
                    + "values (?, ?, ?, ?)");
            pstm.setString(1, id);
            pstm.setString(2, pat);
            pstm.setString(3, fhsal);
            pstm.setString(4, fhreg);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar detalle grua oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String ingresarDetalleEmpleado(String id, String rut, String fhsal, String fhreg) {
        if(fhsal.compareTo(" ") == 0) fhsal = null;
        if(fhreg.compareTo(" ") == 0) fhreg = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Detalle_oc_emp (id_joroc, rut_emp, fhsal_det_emp, fhreg_det_emp) "
                    + "values (?, ?, ?, ?)");
            pstm.setString(1, id);
            pstm.setString(2, rut);
            pstm.setString(3, fhsal);
            pstm.setString(4, fhreg);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar detalle emp oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String ingresarHoras(String id, String ton, String horas, String turnos, String base) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Horas_oc (id_joroc, id_ton, horas_hora, base_hora, turnos_hora) "
                    + "values (?, (SELECT id_ton FROM Tonelajes WHERE pes_ton = ?), ?, ?, ?)");
            pstm.setString(1, id);
            pstm.setString(2, ton);
            pstm.setString(3, horas);
            pstm.setString(4, base);
            pstm.setString(5, turnos);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar horas oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String[] obtenerJornadaOCPorId(String id) {
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fsal_joroc, horsal_joroc, freg_joroc, horlleg_joroc, raz_cli, obs_joroc, clientes.rut_cli, clientes.dig_cli, "
                    + "gir_cli, dir_cli, tel_cli, ciu_cli, coalesce(obs_cli,'') obs_cli, coalesce(countg, 0) countg, coalesce(counte, 0) counte, tras_oc FROM Jornadas_oc INNER JOIN Clientes ON Jornadas_oc.rut_cli = Clientes.rut_cli "
                    + "LEFT JOIN (SELECT id_joroc, COUNT(*) countg FROM Detalle_oc_gru GROUP BY id_joroc) gruas USING(id_joroc) LEFT JOIN (SELECT id_joroc, COUNT(*) counte "
                    + "FROM Detalle_oc_emp GROUP BY id_joroc) emp USING(id_joroc) WHERE id_joroc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrutcli = res.getString("clientes.rut_cli");
            String estdigcli = res.getString("clientes.dig_cli");
            String estfsal = res.getString("fsal_joroc");
            String esthorsal = res.getString("horsal_joroc");
            String estraz = res.getString("raz_cli");
            String estfreg = res.getString("freg_joroc");
            String esthorlleg = res.getString("horlleg_joroc");
            String estobs = res.getString("obs_joroc");
            String estgir = res.getString("gir_cli");
            String estdir = res.getString("dir_cli");
            String esttel = res.getString("tel_cli");
            String estciu = res.getString("ciu_cli");
            String estobscli = res.getString("obs_cli");
            String estcgru = res.getString("countg");
            String estcemp = res.getString("counte");
            String esttras = res.getString("tras_oc");
            data = new String[]{estfsal, esthorsal, estfreg, esthorlleg, estobs, estrutcli, estdigcli, estraz, estgir, estdir, esttel, id, estciu, estobscli, estcgru, estcemp, 
            esttras};
        }catch(SQLException e){
            System.out.println("Error obtener jornada oc por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }

    public String eliminarJornadaOC(String id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Jornadas_oc WHERE id_joroc = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error eliminar jornada oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
//    
    public String modificarJornadaOC(String[] data, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas_oc SET fsal_joroc=?, horsal_joroc=?, "
                    + "rut_cli=?, freg_joroc = ?, horlleg_joroc=?, obs_joroc=?"
                    + " WHERE id_joroc=?");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, id);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error modificar jornada oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
//    
//    public String[] obtenerClienteIdJornada(String id){
//        String[] data = new String[5];
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT clientes.rut_cli, dig_cli, raz_cli, gir_cli,"
//                    + "dir_cli, ciu_cli, com_cli FROM jornadas INNER JOIN clientes"
//                    + " ON clientes.rut_cli = jornadas.rut_cli where id_jor = ?");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            String estrut = res.getString("rut_cli");
//            String estdig = res.getString("dig_cli");
//            String estraz = res.getString("raz_cli");
//            String estgir = res.getString("gir_cli");
//            String estdir = res.getString("dir_cli");
//            String estciu = res.getString("ciu_cli");
//            String estcom = res.getString("com_cli");
//            data = new String[]{estrut + "-" + estdig, estraz, estgir, estdir, estciu, estcom};           
//            res.close();
//       }catch(SQLException e){
//            System.out.println("Error obtener factura por id");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        return data;
//    }
//    
    public String[][] obtenerGruasPorIdJornadaOC(String id){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) total FROM Detalle_oc_gru WHERE id_joroc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar gruas detalle oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        String[][] data = new String[registros][7];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT des_gru, DATE_FORMAT(fhsal_det_gru, '%d-%m-%Y') fsal, DATE_FORMAT(fhsal_det_gru, '%H:%i') hsal, "
                    + "DATE_FORMAT(fhreg_det_gru, '%d-%m-%Y') freg, DATE_FORMAT(fhreg_det_gru, '%H:%i') hreg, id_det_gru id, coalesce(horas_det_gru, '0') h FROM Detalle_oc_gru INNER JOIN Gruas USING(pat_gru) WHERE id_joroc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estgru = res.getString("des_gru");
                String estfsal = res.getString("fsal");
                String esthsal = res.getString("hsal");
                String estfreg = res.getString("freg");
                String esthreg = res.getString("hreg");
                String estid = res.getString("id");
                String esthoras = res.getString("h");
                data[i] = new String[]{estgru, estfsal, esthsal, estfreg, esthreg, estid, esthoras};
                i++;
            }
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener gruas por id oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
    
    public String[][] obtenerEmpleadosPorIdJornadaOC(String id){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) total FROM Detalle_oc_emp WHERE id_joroc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar empleados detalle oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        String[][] data = new String[registros][12];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT nom_emp, apP_emp, apM_emp, DATE_FORMAT(fhsal_det_emp, '%d-%m-%Y') fsal, DATE_FORMAT(fhsal_det_emp, '%H:%i') hsal, "
                    + " DATE_FORMAT(fhreg_det_emp, '%d-%m-%Y') freg, DATE_FORMAT(fhreg_det_emp, '%H:%i') hreg, id_det_oc id, coalesce(hortot_det_emp, 0) hortot, "
                    + " coalesce(horex_det_emp, 0) horex, coalesce(horex2_det_emp, 0) horex2, coalesce(horcol1_det_emp, 0) horcol1, coalesce(horcol30_det_emp, 0) horcol30, "
                    + " coalesce(pes_det_emp, 0) pes FROM Detalle_oc_emp INNER JOIN Empleados USING(rut_emp) WHERE id_joroc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnom = res.getString("nom_emp");
                String estapp = res.getString("apP_emp");
                String estapm = res.getString("apM_emp");
                String estfsal = res.getString("fsal");
                String esthsal = res.getString("hsal");
                String estfreg = res.getString("freg");
                String esthreg = res.getString("hreg");
                String estid = res.getString("id");
                String esthortot = res.getString("hortot");
                String esthorex = res.getString("horex");
                String esthorex2 = res.getString("horex2");
                String esthorcol1 = res.getString("horcol1");
                String esthorcol30 = res.getString("horcol30");
                String estpes = res.getString("pes");
                data[i] = new String[]{estnom + " " + estapp + " " + estapm, estfsal, esthsal, estfreg, esthreg, estid, esthortot, esthorex, esthorex2, esthorcol1, esthorcol30, 
                estpes};
                i++;
            }
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener empleados por id oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
    
    public String[][] obtenerHorasPorIdJornadaOC(String id){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) total FROM Horas_oc WHERE id_joroc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar Horas detalle oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        String[][] data = new String[registros][8];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT pes_ton, horas_hora, turnos_hora, base_hora, turnosex_hora, horasex_hora, horasad_hora, valor_hora FROM Horas_oc "
                    + "INNER JOIN Tonelajes USING(id_ton) WHERE id_joroc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estpes = res.getString("pes_ton");
                String esthoras = res.getString("horas_hora");
                String estturnos = res.getString("turnos_hora");
                String estbase = res.getString("base_hora");
                String estturnosex = res.getString("turnosex_hora");
                String esthorasex = res.getString("horasex_hora");
                String esthorasad = res.getString("horasad_hora");
                String estvalorhora = res.getString("valor_hora");
                data[i] = new String[]{estpes, esthoras, estturnos, estbase, estvalorhora, esthorasad, esthorasex, estturnosex};
                i++;
            }
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener horas por id oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
    
    public String borrarDetalleGruas(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Detalle_oc_gru WHERE id_joroc = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar detalle gruas oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
    
    public String borrarDetalleEmpleados(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Detalle_oc_emp WHERE id_joroc = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar detalle empleados oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
    
    public String borrarHorasBase(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Horas_oc WHERE id_joroc = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar horas base oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
    
//    
//    public String obtenerDescuento(String id){
//        String data = "";
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT coalesce(desc_ot, 0) des FROM jornadas where cod_ot = ?");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            String estdes = res.getString("des");
//            data = estdes;
//            res.close();
//       }catch(SQLException e){
//            System.out.println("Error obtener descuento por id");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        return data;
//    }

    public String ingresarTraspaleta(String[] data) {
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Jornadas_oc (fsal_joroc, horsal_joroc,"
                    + " rut_cli, freg_joroc, horlleg_joroc, obs_joroc, tras_oc) "
                    + "values (?, ?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setInt(3, Integer.parseInt(data[2]));
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[10]);
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            res.next();
            id = res.getString(1);
            pstm.close();
            res.close();
        }catch(SQLException e){
            System.out.println("Error ingresar jornada oc con traspaleta");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id;
    }

    public String modificarTraspaleta(String[] data, String id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas_oc SET fsal_joroc=?, horsal_joroc=?,"
                    + " rut_cli=?, freg_joroc=?, horlleg_joroc=?, obs_joroc=?, tras_oc=? WHERE "
                    + "id_joroc=?", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[10]);
            pstm.setString(8, id);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error modificar jornada oc con traspaleta");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id;
    }
}

