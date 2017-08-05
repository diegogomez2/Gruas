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
public class modeloOcs {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    
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
    
    public String ingresarOc(String[] data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas_oc SET cont_oc = ?, fec_oc = ?, pag_oc = ?,"
                    + "cond_oc = ?, desp_oc = ?, cod_oc = ?, neto_oc = ?, iva_oc = ?, total_oc = ?, checkdesp_oc = ?, vdesp_oc = ?, "
                    + "desc_oc=? WHERE id_joroc = ?");            
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[6]);
            pstm.setInt(7, Integer.parseInt(data[7]));
            pstm.setInt(8, Integer.parseInt(data[8]));
            pstm.setInt(9, Integer.parseInt(data[9]));
            pstm.setString(10, data[10]);
            if(data[10].compareTo("1") == 0){
                pstm.setInt(11, Integer.parseInt(data[11]));
            }else{
                pstm.setInt(11, 0);
            }
            pstm.setInt(12, Integer.parseInt(data[12]));
            pstm.setInt(13, Integer.parseInt(data[5]));
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public Object[][] listarOcs(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Jornadas_oc WHERE NOT cod_oc = -1 and fact_oc < 2");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][11];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_joroc, fec_oc, raz_cli, gir_cli, dir_cli,"
                    + "ciu_cli, com_cli, obs_joroc, cod_oc, total_oc, neto_oc, "
                    + "iva_oc, fact_oc, clientes.rut_cli, dig_cli FROM Jornadas_oc INNER JOIN"
                    + " Clientes ON Clientes.rut_cli = Jornadas_oc.rut_cli WHERE NOT cod_oc = -1 and fact_oc < 2 ORDER BY cod_oc, fact_oc");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfec = res.getString("fec_oc");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_oc");
                String estnet = res.getString("neto_oc");
                String estiva = res.getString("iva_oc");
                String estcodoc = res.getString("cod_oc");
                String estfact = res.getString("fact_oc");
                String estfact2;
                if(estfact.compareTo("0") == 0){
                    estfact2 = "Disponible";
                }else{
                    estfact2 = "Facturada";
                }
                String estrut = res.getString("rut_cli") + "-" + res.getString("dig_cli");
                data[i] = new String[]{estcodoc, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                estiva, esttot, estfact2, estrut};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar ocs");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public String actualizarHoras(String id, String[] hora) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Horas_oc SET valor_hora=?, horasad_hora=?, horasex_hora=?, turnosex_hora=? WHERE "
                    + "id_ton = (SELECT id_ton FROM Tonelajes WHERE pes_ton = ?) AND id_joroc= ?");
            pstm.setString(1, hora[0]);
            pstm.setString(2, hora[1]);
            pstm.setString(3, hora[2]);
            pstm.setString(4, hora[3]);
            pstm.setString(5, hora[4]);
            pstm.setString(6, id);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error actualizar horas oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public Object[][] listarHistoricosOC(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Jornadas_oc WHERE NOT cod_oc = -1 and fact_oc >= 2");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar oc historico");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][11];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_joroc, fec_oc, raz_cli, gir_cli, dir_cli,"
                    + "ciu_cli, com_cli, obs_joroc, cod_oc, total_oc, neto_oc, iva_oc, fact_oc, clientes.rut_cli, dig_cli FROM Jornadas_oc INNER JOIN "
                    + "Clientes ON clientes.rut_cli = jornadas_oc.rut_cli WHERE NOT cod_oc = -1 and fact_oc >= 2 ORDER BY cod_oc, fact_oc");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfec = res.getString("fec_oc");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_oc");
                String estnet = res.getString("neto_oc");
                String estiva = res.getString("iva_oc");
                String estcodot = res.getString("cod_oc");
                String estfact = res.getString("fact_oc");
                String estfact2;
                if(estfact.compareTo("0") == 0){
                    estfact2 = "Disponible";
                }else if(estfact.compareTo("4") == 0){
                    estfact2 = "Nula";
                }else{
                    estfact2 = "Facturada";
                }
                String estrut = res.getString("clientes.rut_cli") + "-" + res.getString("dig_cli");
                data[i] = new String[]{estcodot, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                estiva, esttot, estfact2, estrut};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar historico ocs");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerOcPorId(String id){
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fsal_joroc, "
                    + "raz_cli, ciu_cli, freg_joroc, obs_joroc, clientes.rut_cli, clientes.dig_cli,"
                    + "gir_cli, dir_cli, tel_cli, fec_oc, cod_oc, pag_oc, cond_oc, cont_oc, "
                    + "total_oc, neto_oc, iva_oc, desp_oc, horfin_oc, checkdesp_oc, vdesp_oc, desc_oc, id_joroc "
                    + " FROM Jornadas_oc INNER JOIN Clientes ON "
                    + "Jornadas_oc.rut_cli = clientes.rut_cli WHERE cod_oc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrutcli = res.getString("clientes.rut_cli");
            String estdigcli = res.getString("clientes.dig_cli");
            String estfsal = res.getString("fsal_joroc");
            String estraz = res.getString("raz_cli");
            String estciu = res.getString("ciu_cli");
            String estfreg = res.getString("freg_joroc");
            String estobs = res.getString("obs_joroc");
            String estgir = res.getString("gir_cli");
            String estdir = res.getString("dir_cli");
            String esttel = res.getString("tel_cli");
            String estfot = res.getString("fec_oc");
            String estcond = res.getString("cond_oc");
            String estpago = res.getString("pag_oc");
            String estcont = res.getString("cont_oc");
            String esttot = res.getString("total_oc");
            String estneto = res.getString("neto_oc");
            String estiva = res.getString("iva_oc");
            String estdesp = res.getString("desp_oc");
            String esthorfin = res.getString("horfin_oc");
            String estcdesp = res.getString("checkdesp_oc");
            String estvdesp = res.getString("vdesp_oc");
            String estdesc = res.getString("desc_oc");
            String estidoc = res.getString("id_joroc");
            data = new String[]{estfsal, estfreg, estobs, estrutcli, estdigcli, estraz, estgir, estdir, esttel, id, estfot, estcond
                    , estpago, estcont, esttot, estneto, estiva, estdesp, esthorfin, estcdesp, estvdesp, estciu, estdesc, estidoc};
        }catch(SQLException e){
            System.out.println("Error obtener oc por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public Object[][] listarFacturasOC(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Jornadas_oc WHERE fact_oc = 1");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar facturas oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][11];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_joroc, fec_oc, raz_cli, gir_cli, dir_cli,"
                    + "reg_cli, com_cli, obs_joroc, cod_oc, total_oc, neto_oc, "
                    + "iva_oc, fact_oc, desc_oc FROM Jornadas_oc INNER JOIN"
                    + " clientes ON clientes.rut_cli = jornadas_oc.rut_cli WHERE NOT cod_oc = -1 and fact_oc = 1 ORDER BY cod_oc, fact_oc");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfec = res.getString("fec_oc");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estreg = res.getString("reg_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_oc");
                String estnet = res.getString("neto_oc");
                String estiva = res.getString("iva_oc");
                String estcodoc = res.getString("cod_oc");
                String estdesc = res.getString("desc_oc");
                data[i] = new String[]{estcodoc, estraz, estgir, estdir, estreg, estcom, estfec, estnet,
                    estiva, esttot, estdesc};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar facturas oc");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public String getIdFacturaOC(String id){
        String idFact = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fact_oc FROM Jornadas_oc WHERE cod_oc = ?");
             pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            idFact = res.getString("fact_oc");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener id factura oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return idFact;
    }
    
    public String ingresarFacturaOC(String idOc){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas_oc SET fact_oc = 1 WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar factura oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String anularFactura(String idOc){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas_oc SET fact_oc = 4 WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error anular factura oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String eliminarOc(String idOc){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Jornadas_oc WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error eliminar oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String quitarFacturaOC(String idOc){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas_oc SET fact_oc = 0 WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error quitar factura oc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String[] obtenerFacturaPorIdOC(String idOc){
        String[] data = new String[13];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT clientes.rut_cli, dig_cli, raz_cli, gir_cli,"
                    + "dir_cli, ciu_cli, com_cli, total_oc, neto_oc, iva_oc, fact_oc, cod_oc, hortot_oc, tras_oc FROM Jornadas_oc INNER JOIN Clientes"
                    + " ON Clientes.rut_cli = jornadas_oc.rut_cli WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_oc");
                String estnet = res.getString("neto_oc");
                String estiva = res.getString("iva_oc");
                String estcodot = res.getString("cod_oc");
                String estfact = res.getString("fact_oc");
                String esthor = res.getString("hortot_oc");
                String esttras = res.getString("tras_oc");
                //Esta linea deberia borrarse
                //String estcheckhor = res.getString("checkhormin_ot");
                String estcheckhor = "";
                data = new String[]{estrut + "-" + estdig.toUpperCase(), estraz, estgir, estdir, estciu, estcom, esttot, estnet,
                estiva, estfact, estcodot, esthor, estcheckhor, esttras};
                i++;
            }
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener factura por id oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
    
//    public void actualizarHoras(int horas, String id){
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("update jornadas set hortot_ot = ? WHERE id_jor = ?");
//            pstm.setInt(1, horas);
//            pstm.setString(2, id);
//            pstm.executeUpdate();
//        }catch(SQLException e){
//            System.out.println("Error actualizar hroas");
//            System.out.println(e);
//        }catch(ClassNotFoundException e){
//            System.out.println(e);
//        }
//    }
    
    public String archivarFacturaOC(String idOc, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT coalesce(fact_oc, 0) fact_oc FROM Jornadas_oc WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            ResultSet res = pstm.executeQuery();
            res.next();
            String facoc = res.getString("fact_oc");
            System.out.println(facoc);
            res.close();
            if(facoc.compareTo("2") == 0){
                pstm.close();
                return "yafacturada";
            }
            pstm = conn.prepareStatement("UPDATE Jornadas_oc SET fact_oc = 2, id_fac = ? WHERE cod_oc = ?");
            pstm.setInt(1, Integer.parseInt(id));
            pstm.setInt(2, Integer.parseInt(idOc));
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error archivar facturas OC");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String archivarFacturaNDOC(String idOc, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT coalesce(fact_oc, 0) fact_oc FROM Jornadas_oc WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            ResultSet res = pstm.executeQuery();
            res.next();
            String facoc = res.getString("fact_oc");
            System.out.println(facoc);
            res.close();
            if(facoc.compareTo("2") == 0){
                pstm.close();
                return "yafacturada";
            }
            pstm = conn.prepareStatement("UPDATE Jornadas_oc SET fact_oc = 2, id_nd = ? WHERE cod_oc = ?");
            pstm.setInt(1, Integer.parseInt(id));
            pstm.setInt(2, Integer.parseInt(idOc));
            pstm.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error archivar facturas nd OC");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
//    public Object[][] listarFacturadas2(){
//        int registros = 0;
//        
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM jornadas where fact_ot = 2");
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//       }catch(SQLException e){
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        
//        Object[][] data = new String[registros][11];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT id_jor, fec_ot, raz_cli, gir_cli, dir_cli,"
//                    + "ciu_cli, com_cli, obs_jor, cod_ot, total_ot, neto_ot, "
//                    + "iva_ot, fact_ot FROM Jornadas INNER JOIN"
//                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
//                    + "= jornadas.rut_emp Where not cod_ot = -1 and fact_ot = 2 ORDER BY cod_ot, fact_ot");
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while(res.next()){
//                String estfec = res.getString("fec_ot");
//                java.util.Date fecha = formatDate.parse(estfec);
//                estfec = newFormat.format(fecha);
//                String estraz = res.getString("raz_cli");
//                String estgir = res.getString("gir_cli");
//                String estdir = res.getString("dir_cli");
//                String estciu = res.getString("ciu_cli");
//                String estcom = res.getString("com_cli");
//                String esttot = res.getString("total_ot");
//                String estnet = res.getString("neto_ot");
//                String estiva = res.getString("iva_ot");
//                String estcodot = res.getString("cod_ot");
//                data[i] = new String[]{estcodot, estraz, estgir, estdir, estciu, estcom, estfec, estnet, 
//                estiva, esttot};
//                i++;
//            }
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error listar facturadas 2");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return data;
//    }
//    
//    public String[] obtenerNetoTotal(String id_fac){
//        int registros = 0;
//        int neto = 0;
//        String[] datos;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM detallefactura where id_fac = ?");
//            pstm.setInt(1, Integer.parseInt(id_fac));
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error obtener neto total");
//            System.out.println(e);
//        }catch(ClassNotFoundException e){
//            System.out.println(e);
//        }
//        
//        datos = new String[registros];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT neto_ot FROM Jornadas INNER JOIN"
//                    + " detallefactura ON jornadas.id_jor = detallefactura.id_jor Where id_fac = ?");
//            pstm.setInt(1, Integer.parseInt(id_fac));
//            ResultSet res = pstm.executeQuery();
//            
//            while(res.next()){
//                neto += res.getInt("neto_ot");
//            }
//            res.close();
//            
//            pstm = conn.prepareStatement("Select raz_cli From jornadas INNER JOIN clientes ON "
//                    + "jornadas.rut_cli = clientes.rut_cli INNER JOIN detallefactura ON jornada.id_jor "
//                    + "= detallelfactura.id_jor where id_fac = ?");
//            pstm.setInt(1, Integer.parseInt(id_fac));
//            res = pstm.executeQuery();
//            
//            String estraz = res.getString("raz_cli");
//            datos = new String[]{estraz, Integer.toString(neto)};
//        }catch(SQLException e){
//            System.out.println("Error obtener neto total");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return datos;
//    }
//    
//    public String[] obtenerTotales(String id_jor){
//        String[] datos;
//        
//        datos = new String[3];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT neto_ot, , iva_ot, total_ot FROM Jornadas "
//                    + "Where id_jor = ?");
//            pstm.setInt(1, Integer.parseInt(id_jor));
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            String estnet = res.getString("neto_ot");
//            String estbru = res.getString("total_ot");
//            String estiva = res.getString("iva_ot");
//            datos = new String[]{estnet, estbru, estiva};
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error obtener totales");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return datos;
//    }
//    
    public Object[][] obtenerOcPorIdFacturada(String id, String tipo){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Jornadas_oc INNER JOIN facturas"
                    + " ON facturas.id_fac = jornadas_oc.id_fac WHERE fol_fac = ? and tipo_fac = ?");
            pstm.setString(1, id);
            pstm.setString(2, tipo);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener oc por id facturada");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][11];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT fec_oc, raz_cli, gir_cli, dir_cli,"
                    + "ciu_cli, com_cli, cod_oc, total_oc, neto_oc, iva_oc, fact_oc FROM Jornadas_oc INNER JOIN facturas ON facturas.id_fac = jornadas_oc.id_fac "
                    + "INNER JOIN Clientes ON clientes.rut_cli = jornadas_oc.rut_cli WHERE fol_fac = ? and tipo_fac = ? ORDER BY cod_oc");
            pstm.setString(1, id);
            pstm.setString(2, tipo);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfec = res.getString("fec_oc");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_oc");
                String estnet = res.getString("neto_oc");
                String estiva = res.getString("iva_oc");
                String estcodot = res.getString("cod_oc");
                String estfact = res.getString("fact_oc");
                data[i] = new String[]{estcodot, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                estiva, esttot, estfact};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error obtener oc por id facturada");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
//    
//    public Object[][] obtenerOtPorIdFacturadaNC(String id){
//        int registros = 0;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM Jornadas "
//                    + "INNER JOIN Notacredito n ON n.id_fac = Jornadas.id_fac WHERE fol_nc = ?");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//       }catch(SQLException e){
//            System.out.println("Error obtener ot por id facturada");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        
//        Object[][] data = new String[registros][11];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT fec_ot, raz_cli, gir_cli, dir_cli,"
//                    + "ciu_cli, com_cli, cod_ot, total_ot, neto_ot, "
//                    + "iva_ot, fact_ot FROM Jornadas INNER JOIN notacredito ON notacredito.id_fac = "
//                    + " jornadas.id_fac "
//                    + "INNER JOIN"
//                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
//                    + "= jornadas.rut_emp Where fol_nc = ? ORDER BY cod_ot");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while(res.next()){
//                String estfec = res.getString("fec_ot");
//                java.util.Date fecha = formatDate.parse(estfec);
//                estfec = newFormat.format(fecha);
//                String estraz = res.getString("raz_cli");
//                String estgir = res.getString("gir_cli");
//                String estdir = res.getString("dir_cli");
//                String estciu = res.getString("ciu_cli");
//                String estcom = res.getString("com_cli");
//                String esttot = res.getString("total_ot");
//                String estnet = res.getString("neto_ot");
//                String estiva = res.getString("iva_ot");
//                String estcodot = res.getString("cod_ot");
//                String estfact = res.getString("fact_ot");
//                data[i] = new String[]{estcodot, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
//                estiva, esttot, estfact};
//                i++;
//            }
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error obtener ot por id facturada");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return data;
//    }
//    
//    public Object[][] obtenerOtPorIdNDNC(String id){
//        int registros = 0;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM Jornadas "
//                    + "INNER JOIN Notacredito n ON n.id_nd = Jornadas.id_nd WHERE fol_nc = ?");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//       }catch(SQLException e){
//            System.out.println("Error obtener ot por id facturada");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        
//        Object[][] data = new String[registros][11];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT fec_ot, raz_cli, gir_cli, dir_cli,"
//                    + "ciu_cli, com_cli, cod_ot, total_ot, neto_ot, "
//                    + "iva_ot, fact_ot FROM Jornadas INNER JOIN notacredito ON notacredito.id_nd = "
//                    + " jornadas.id_nd "
//                    + "INNER JOIN"
//                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
//                    + "= jornadas.rut_emp Where fol_nc = ? ORDER BY cod_ot");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while(res.next()){
//                String estfec = res.getString("fec_ot");
//                java.util.Date fecha = formatDate.parse(estfec);
//                estfec = newFormat.format(fecha);
//                String estraz = res.getString("raz_cli");
//                String estgir = res.getString("gir_cli");
//                String estdir = res.getString("dir_cli");
//                String estciu = res.getString("ciu_cli");
//                String estcom = res.getString("com_cli");
//                String esttot = res.getString("total_ot");
//                String estnet = res.getString("neto_ot");
//                String estiva = res.getString("iva_ot");
//                String estcodot = res.getString("cod_ot");
//                String estfact = res.getString("fact_ot");
//                data[i] = new String[]{estcodot, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
//                estiva, esttot, estfact};
//                i++;
//            }
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error obtener ot por id facturada");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return data;
//    }
//    
//    public Object[][] obtenerOtPorIdFacturadaND(String id){
//        int registros = 0;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM notadebito where fol_nd = ?");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//       }catch(SQLException e){
//            System.out.println("Error obtener ot por id facturada");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        
//        Object[][] data = new String[registros][11];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT fec_ot, raz_cli, gir_cli, dir_cli,"
//                    + "ciu_cli, com_cli, cod_ot, tot_nd, neto_nd, "
//                    + "iva_nd, fact_ot FROM Jornadas INNER JOIN notadebito ON jornadas.id_nd = notadebito.id_nd "
//                    + "INNER JOIN"
//                    + " clientes ON clientes.rut_cli = jornadas.rut_cli INNER JOIN empleados ON empleados.rut_emp "
//                    + "= jornadas.rut_emp Where notadebito.fol_nd = ? ORDER BY cod_ot");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while(res.next()){
//                String estfec = res.getString("fec_ot");
//                java.util.Date fecha = formatDate.parse(estfec);
//                estfec = newFormat.format(fecha);
//                String estraz = res.getString("raz_cli");
//                String estgir = res.getString("gir_cli");
//                String estdir = res.getString("dir_cli");
//                String estciu = res.getString("ciu_cli");
//                String estcom = res.getString("com_cli");
//                String esttot = res.getString("tot_nd");
//                String estnet = res.getString("neto_nd");
//                String estiva = res.getString("iva_nd");
//                String estcodot = res.getString("cod_ot");
//                String estfact = res.getString("fact_ot");
//                data[i] = new String[]{estcodot, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
//                estiva, esttot, estfact};
//                i++;
//            }
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error obtener ot por id facturada");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return data;
//    }
//    
    public String obtenerCodigoOc(String cod){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT SUM(total) total FROM (SELECT count(1) as total FROM Jornadas WHERE cod_ot = ? UNION "
                    + "SELECT count(*) total FROM Jornadas_oc WHERE cod_oc = ?) x");
            pstm.setString(1, cod);
            pstm.setString(2, cod);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener cod oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        if(registros == 0){
            return "correcto";
        }else{
            return "incorrecto";
        }
    }
    
    public String[] obtenerDiasPorIdOc(String idOt){
        String[] data = new String[12];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fsal_jor, freg_jor, horsal_jor, horlleg_jor,"
                    + "ton_gru FROM jornadas INNER JOIN gruas ON gruas.pat_gru = jornadas.pat_gru "
                    + " WHERE cod_ot = ?");
            pstm.setString(1, idOt);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfsal = res.getString("fsal_jor");
                String estfreg = res.getString("freg_jor");
                String esthsal = res.getString("horsal_jor");
                String esthreg = res.getString("horlleg_jor");
                String estton = res.getString("ton_gru");
                data = new String[]{estfsal, estfreg, esthsal, esthreg, estton};
                i++;
            }
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener factura por id");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
    
    public String[] obtenerValorDespachoOc(String idOt){
        String[] data = new String[2];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT vdesp_ot, checkdesp_ot FROM jornadas_oc WHERE cod_oc = ?");
            pstm.setString(1, idOt);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcheck = res.getString("checkdesp_oc");
                String estvdesp = res.getString("vdesp_oc");
                data[0] = estcheck;
                data[1] = estvdesp;
                i++;
            }
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener factura por id oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return data;
    }
    
    public String obtenerFormaPagoOC(String idOc){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT pag_oc, cond_oc FROM Jornadas_oc WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            ResultSet res = pstm.executeQuery();
            res.next();
            String pag_oc = res.getString("pag_oc");
            String cond_oc = res.getString("cond_oc");
            res.close();
            if(pag_oc.compareTo("Al día") == 0 && cond_oc.compareTo("Cheque") != 0){
                return "1";
            }else{
                return "2";
            }
       }catch(SQLException e){
            System.out.println("Error obtener forma pago por id oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return "0";
    }
    
//    //Para saber si es nc de fac o nd
//    public int tipoNC(String id){
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Notacredito "
//                    + "WHERE fol_nc = ? and id_fac is null");
//            pstm.setString(1, id);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            int total = res.getInt("total");
//            return total;
//       }catch(SQLException e){
//            System.out.println("Error obtener forma pago por id");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        return -1;
//    }
//    
//    public int agregarHorasExtra(String id, double horex, double horex2){
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("update jornadas set horex_ot = ?, horex2_ot = ? WHERE id_jor = ?");
//            pstm.setDouble(1, horex);
//            pstm.setDouble(2, horex2);
//            pstm.setString(3, id);
//            System.out.println(id);
//            pstm.executeUpdate();
//        }catch(SQLException e){
//            System.out.println("Error actualizar horas en ot");
//            System.out.println(e);
//        }catch(ClassNotFoundException e){
//            System.out.println(e);
//        }
//        return 1;
//    }

    public Object[][] listarReporteHistoricoOc(String fecIn, String fecFin){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM jornadas_oc WHERE NOT cod_oc = -1 and fact_oc >= 2 AND fec_oc >= ? AND fec_oc <= ?");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar reporte historico oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][14];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_joroc, fec_oc, raz_cli, gir_cli, dir_cli,"
                    + "ciu_cli, com_cli, obs_joroc, cod_oc, total_oc, neto_oc, "
                    + "iva_oc, fact_oc, clientes.rut_cli, dig_cli FROM Jornadas_oc INNER JOIN"
                    + " Clientes ON Clientes.rut_cli = Jornadas_oc.rut_cli WHERE not cod_oc = -1 and fact_oc >= 2 AND fec_oc >= ? AND fec_oc <= ? ORDER BY cod_oc, fact_oc");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfec = res.getString("fec_oc");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("total_oc");
                String estnet = res.getString("neto_oc");
                String estiva = res.getString("iva_oc");
                String estcodot = res.getString("cod_oc");
                String estfact = res.getString("fact_oc");
                String estfact2;
                if(estfact.compareTo("0") == 0){
                    estfact2 = "Disponible";
                }else if(estfact.compareTo("4") == 0){
                    estfact2 = "Nula";
                }else{
                    estfact2 = "Facturada";
                }
                String estrut = res.getString("clientes.rut_cli") + "-" + res.getString("dig_cli");
                data[i] = new String[]{estcodot, estrut, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                estiva, esttot, estfact2};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar reporte historico ocs");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }


}
