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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class modeloFacturas {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public String ingresarFacturada(String fec, int valorNeto, int valorIva, int valorTotal, String tipo, String folio){        
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into facturas (fec_fac, neto_fac,"
                    + "iva_fac, tot_fac, tipo_fac, fol_fac)"
                    + " values (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fec);
            pstm.setInt(2, valorNeto);
            pstm.setInt(3, valorIva);
            pstm.setInt(4, valorTotal);
            pstm.setString(5, tipo);
            pstm.setInt(6, Integer.parseInt(folio));
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            res.next();
            id = res.getString(1);
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar facturada");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id;
    }
    
    public String borrarFacturaDuplicada(String id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Facturas WHERE id_fac = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar factura duplicada");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }    
    
    public String borrarNDDuplicada(String id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Notadebito WHERE id_nd = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar nota debito duplicada");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
    
    public String borrarNCDuplicada(String id) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Notacredito WHERE id_nc = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar nota credito duplicada");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
//    public String ingresarFacturadav2(String fec, int valorNeto, int valorIva, int valorTotal, String tipo, String folio, String[] idOts){        
//        String id = "";
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("insert into facturas (fec_fac, neto_fac,"
//                    + "iva_fac, tot_fac, tipo_fac, fol_fac)"
//                    + " values (?, ?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
//            pstm.setString(1, fec);
//            pstm.setInt(2, valorNeto);
//            pstm.setInt(3, valorIva);
//            pstm.setInt(4, valorTotal);
//            pstm.setString(5, tipo);
//            pstm.setInt(6, Integer.parseInt(folio));
//            pstm.execute();
//            ResultSet res = pstm.getGeneratedKeys();
//            res.next();
//            id = res.getString(1);
//            String sql = "UPDATE Jornadas set fact_ot = 2, id_fact=? WHERE cod_ot"
//            pstm.close();
//        }catch(SQLException e){
//            System.out.println("Error ingresar facturada");
//            System.out.println(e);
//            return "incorrecto";
//        }catch(ClassNotFoundException e){
//            System.out.println(e);
//            return "incorrecto";
//        }
//        return id;
//    }
    
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
            pstm = conn.prepareStatement("SELECT COUNT(1) as total from  notacredito");
            res = pstm.executeQuery();
            res.next();
            registros += res.getInt("total");
            res.close();
            pstm = conn.prepareStatement("SELECT COUNT(1) as total from  notadebito");
            res = pstm.executeQuery();
            res.next();
            registros += res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar facturadas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        Object[][] data = new String[registros][11];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac fol, raz_cli, gir_cli, dir_cli, "
                    + "ciu_cli, com_cli, tot_fac tot, neto_fac neto, iva_fac iva,fec_fac fec, tipo_fac tipo "
                    + "FROM facturas f INNER JOIN("
                    + "SELECT id_fac, rut_cli FROM jornadas GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c ON c.rut_cli = b.rut_cli "
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_fac tot, "
                    + "neto_fac neto, iva_fac iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_fac, rut_cli FROM Jornadas GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac "
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd neto, iva_nd iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_nd, rut_cli FROM Jornadas GROUP BY id_nd) "
                    + "b ON b.id_nd = f.id_nd INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Notadebito ON Notadebito.id_nd = f.id_nd "
                    + "UNION "
                    + "SELECT fol_nd fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd as neto, iva_nd  iva, fec_nd as fec, tipo_nd  tipo FROM Notadebito f INNER JOIN( "
                    + "SELECT id_fac, rut_cli FROM Jornadas GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac order by fec DESC");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("fol");
                String estfec = res.getString("fec");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("tot");
                String estnet = res.getString("neto");
                String estiva = res.getString("iva");
                String esttip = res.getString("tipo");

                data[i] = new String[]{estid, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                    estiva, esttot, esttip };
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar facturadas");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public Object[][] listarFacturadasOC(){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total from facturas");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm = conn.prepareStatement("SELECT COUNT(1) as total from  notacredito");
            res = pstm.executeQuery();
            res.next();
            registros += res.getInt("total");
            res.close();
            pstm = conn.prepareStatement("SELECT COUNT(1) as total from notadebito");
            res = pstm.executeQuery();
            res.next();
            registros += res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar facturadas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        Object[][] data = new String[registros][11];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac fol, raz_cli, gir_cli, dir_cli, "
                    + "ciu_cli, com_cli, tot_fac tot, neto_fac neto, iva_fac iva,fec_fac fec, tipo_fac tipo "
                    + "FROM facturas f INNER JOIN("
                    + "SELECT id_fac, rut_cli FROM Jornadas_oc GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c ON c.rut_cli = b.rut_cli "
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_fac tot, "
                    + "neto_fac neto, iva_fac iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_fac, rut_cli FROM Jornadas_oc GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac "
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd neto, iva_nd iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_nd, rut_cli FROM Jornadas_oc GROUP BY id_nd) "
                    + "b ON b.id_nd = f.id_nd INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Notadebito ON Notadebito.id_nd = f.id_nd "
                    + "UNION "
                    + "SELECT fol_nd fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd as neto, iva_nd  iva, fec_nd as fec, tipo_nd  tipo FROM Notadebito f INNER JOIN( "
                    + "SELECT id_fac, rut_cli FROM Jornadas_oc GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac order by fec DESC");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("fol");
                String estfec = res.getString("fec");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("tot");
                String estnet = res.getString("neto");
                String estiva = res.getString("iva");
                String esttip = res.getString("tipo");

                data[i] = new String[]{estid, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                    estiva, esttot, esttip };
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar facturadas");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerFacturaPorId(String id) {
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT neto_fac, iva_fac, tot_fac "
                    + "FROM facturas WHERE id_fac = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estnet = res.getString("neto_fac");
            String estiva = res.getString("iva_fac");
            String esttot = res.getString("tot_fac");
            data = new String[]{estnet, estiva, esttot, res.getString(1)};
        }catch(SQLException e){
            System.out.println("Error obtener factura por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerFacturaPorIdNC(String id) {
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT cod_ot, neto_fac, iva_fac, tot_fac, facturas.id_fac "
                    + "FROM facturas WHERE id_fac = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estcod = res.getString("cod_ot");
            String estnet = res.getString("neto_fac");
            String estiva = res.getString("iva_fac");
            String esttot = res.getString("tot_fac");
            String estfac = res.getString("facturas.id_fac");
            data = new String[]{estcod, estnet, estiva, esttot, estfac, res.getString(1)};
        }catch(SQLException e){
            System.out.println("Error obtener factura por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerOtsPorIdNC(String id) {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM notacredito INNER"
                    + " JOIN facturas ON notacredito.id_fac = facturas.id_fac INNER JOIN jornadas ON "
                    + "jornadas.id_fac = facturas.id_fac WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener ot por id nc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        String[] data = new String[registros];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT cod_ot FROM "
                    + "jornadas INNER JOIN facturas ON jornadas.id_fac = facturas.id_fac INNER JOIN "
                    + "notacredito ON notacredito.id_fac = facturas.id_fac WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcod = res.getString("cod_ot");
                data[i] = estcod;
                i++;
            }
            
        }catch(SQLException e){
            System.out.println("Error obtener ots por id nc");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerOtsPorIdNDNC(String id) {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM notacredito nc INNER"
                    + " JOIN notadebito n ON nc.id_nd = n.id_nd INNER JOIN jornadas ON "
                    + "jornadas.id_nd = n.id_nd WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener ot por id nc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        String[] data = new String[registros];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT cod_ot FROM "
                    + "jornadas INNER JOIN Notadebito n ON jornadas.id_nd = n.id_nd INNER JOIN "
                    + "notacredito nc ON nc.id_nd = n.id_nd WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcod = res.getString("cod_ot");
                data[i] = estcod;
                i++;
            }
            
        }catch(SQLException e){
            System.out.println("Error obtener ots por id nc");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    //OBTIENE LOS CODIGOS POR LA ID DE LA FACTURA
    public String[] obtenerOtsPorIdFacturada(String id) {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM"
                    + " jornadas WHERE id_fac = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener ot por id fact");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        String[] data = new String[registros];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT cod_ot FROM "
                    + "jornadas WHERE id_fac = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcod = res.getString("cod_ot");
                data[i] = estcod;
                i++;
            }
            
        }catch(SQLException e){
            System.out.println("Error obtener ots por id fac");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerValoresNC(String id) {
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT facturas.id_fac, neto_fac, iva_fac, tot_fac FROM "
                    + "facturas INNER JOIN notacredito ON facturas.id_fac = notacredito.id_fac "
                    + "WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfac = res.getString("id_fac");
            String estnet = res.getString("neto_fac");
            String estiva = res.getString("iva_fac");
            String esttot = res.getString("tot_fac");
            data = new String[]{estfac, estnet, estiva, esttot};
        }catch(SQLException e){
            System.out.println("Error obtener valores ots por id nc");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerValoresNDNC(String id) {
        String[] data = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT n.id_nd, neto_nd, iva_nd, tot_nd FROM "
                    + "Notadebito n INNER JOIN Notacredito nc ON n.id_nd = nc.id_nd "
                    + "WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfac = res.getString("id_nd");
            String estnet = res.getString("neto_nd");
            String estiva = res.getString("iva_nd");
            String esttot = res.getString("tot_nd");
            data = new String[]{estfac, estnet, estiva, esttot};
        }catch(SQLException e){
            System.out.println("Error obtener valores ots por id nc");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarNotaCredito(String id, String razon, String folio, String tipo){    
        String fecha = formatDate.format(new Date());
        String id_nc;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm;
            if(tipo.compareTo("notadebito") == 0){
                pstm = conn.prepareStatement("SELECT id_nd id FROM Notadebito where fol_nd = ?"
                    + " AND tipo_nd = ?");
            }else{
                pstm = conn.prepareStatement("SELECT id_fac id FROM facturas where fol_fac = ?"
                    + " AND tipo_fac = ?");
            }
            pstm.setString(1, id);
            pstm.setString(2, tipo);
            ResultSet res = pstm.executeQuery();
            res.next();
            String id_fac = res.getString("id");
            pstm.close();
            res.close();
            if(tipo.compareTo("notadebito") == 0){
                pstm = conn.prepareStatement("SELECT COUNT(*) as total From notacredito where id_nd = ?");
                pstm.setString(1, id_fac);
                res = pstm.executeQuery();
                res.next();
                int resp = res.getInt("total");
                if(resp == 0){
                    pstm = conn.prepareStatement("insert into notacredito (fec_nc, raz_nc, id_nd, fol_nc, tipo_nc)"
                        + " values (?, ?, ?, ?, 'notacredito')", PreparedStatement.RETURN_GENERATED_KEYS);
                }else{
                    pstm.close();
                    res.close();
                    return "ncduplicada";
                }
            }else{
                pstm = conn.prepareStatement("SELECT COUNT(*) as total From notacredito where id_fac = ?");
                pstm.setString(1, id_fac);
                res = pstm.executeQuery();
                res.next();
                int resp = res.getInt("total");
                if(resp == 0){
                    pstm = conn.prepareStatement("insert into notacredito (fec_nc, raz_nc, id_fac, fol_nc, tipo_nc)"
                        + " values (?, ?, ?, ?, 'notacredito')", PreparedStatement.RETURN_GENERATED_KEYS);
                }else{
                    pstm.close();
                    res.close();
                    return "ncduplicada";
                }
            }
            pstm.setString(1, fecha);
            pstm.setString(2, razon);
            pstm.setInt(3, Integer.parseInt(id_fac));
            pstm.setInt(4, Integer.parseInt(folio));
            pstm.execute();
            res = pstm.getGeneratedKeys();
            res.next();
            id_nc = res.getString(1);
            pstm.close();
            res.close();
        }catch(SQLException e){
            System.out.println("Error ingresar nota credito");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id_nc;
    }
    
    public String ingresarND(String fec, int valorNeto, int valorIva, int valorTotal, String id_fac, String folio, String tiponc){        
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT id_fac FROM facturas where fol_fac = ?"
                    + " AND tipo_fac = ?");
            pstm.setString(1, id_fac);
            pstm.setString(2, tiponc);
            ResultSet res = pstm.executeQuery();
            res.next();
            String idFac = res.getString("id_fac");
            pstm.close();
            res.close();
            pstm = conn.prepareStatement("insert into notadebito (fec_nd, neto_nd,"
                    + "iva_nd, tot_nd, id_fac, fol_nd, tipo_nd)"
                    + " values (?, ?, ?, ?, ?, ?, 'notadebito')", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fec);
            pstm.setInt(2, valorNeto);
            pstm.setInt(3, valorIva);
            pstm.setInt(4, valorTotal);
            pstm.setString(5, idFac);
            pstm.setInt(6, Integer.parseInt(folio));
            pstm.execute();
            res = pstm.getGeneratedKeys();
            res.next();
            id = res.getString(1);
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error ingresar nota debito");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id;
    }
    
    public String folioFac(){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac from folios WHERE id_fol = 1 "
                    + "FOR UPDATE"); 
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_fac");
            fol = estfol;
            pstm = conn.prepareStatement("UPDATE folios SET fol_fac = fol_fac + 1 WHERE id_fol = 1");
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener folio fac");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String folioFacEx(){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_facex from folios WHERE id_fol = 1 "
                    + "FOR UPDATE");
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_facex");
            fol = estfol;
            pstm = conn.prepareStatement("UPDATE folios SET fol_facex = fol_facex + 1 WHERE id_fol = 1");
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener folio facex");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String folioBol(){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_bol from folios WHERE id_fol = 1 "
                    + "FOR UPDATE");
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_bol");
            fol = estfol;
            pstm = conn.prepareStatement("UPDATE folios SET fol_bol = fol_bol + 1 WHERE id_fol = 1");
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error folio bol");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String folioND(){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_nd from folios WHERE id_fol = 1 "
                    + "FOR UPDATE");
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_nd");
            fol = estfol;
            pstm = conn.prepareStatement("UPDATE folios SET fol_nd = fol_nd + 1 WHERE id_fol = 1");
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error folio nd");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String folioNC(){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_nc from folios WHERE id_fol = 1 "
                    + "FOR UPDATE");
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_nc");
            fol = estfol;
            pstm = conn.prepareStatement("UPDATE folios SET fol_nc = fol_nc + 1 WHERE id_fol = 1");
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error folio nc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String folioGuiaDesp(){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_gd from folios WHERE id_fol = 1 "
                    + "FOR UPDATE");
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_gd");
            fol = estfol;
            pstm = conn.prepareStatement("UPDATE folios SET fol_gd = fol_gd + 1 WHERE id_fol = 1");
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error folio fd");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String obtenerFolioFac(String id){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac from facturas WHERE id_fac = ? ");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_fac");
            fol = estfol;
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener folio fac");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String obtenerFolioNC(String id){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_nc from notacredito WHERE id_nc = ? ");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_nc");
            fol = estfol;
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener folio nc");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String obtenerFolioND(String id){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_nd from notadebito WHERE id_nd = ? ");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_nd");
            fol = estfol;
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener folio nd");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
    public String obtenerFechaFac(String id, String tipo){
        String fec = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fec_fac from facturas WHERE fol_fac = ? and"
                    + " tipo_fac = ?");
            pstm.setString(1, id);
            pstm.setString(2, tipo);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfec = res.getString("fec_fac");
            fec = estfec;
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener fecha fac");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fec;
    }
    
    public String obtenerFechaND(String id, String tipo){
        String fec = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fec_nd from Notadebito WHERE fol_nd = ? and"
                    + " tipo_nd = ?");
            pstm.setString(1, id);
            pstm.setString(2, tipo);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfec = res.getString("fec_nd");
            fec = estfec;
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener fecha nd");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fec;
    }
    
    public String[] obtenerResumenMes(String mes){
       int registros = 0;
       String[] datos = new String[4];
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM facturas where "
                    + " MONTH(fec_fac) = ? and tipo_fac = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "factura");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();
            datos[0] = String.valueOf(registros);
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(neto_fac), 0) neto FROM facturas where MONTH(fec_fac)"
                    + " = ? and tipo_fac = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "factura");
            res = pstm.executeQuery();
            res.next();
            datos[1] = res.getString("neto");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(iva_fac), 0) iva FROM facturas where MONTH(fec_fac)"
                    + " = ? and tipo_fac = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "factura");
            res = pstm.executeQuery();
            res.next();
            datos[2] = res.getString("iva");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(tot_fac), 0) tot FROM facturas where MONTH(fec_fac)"
                    + " = ? and tipo_fac = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "factura");
            res = pstm.executeQuery();
            res.next();
            datos[3] = res.getString("tot");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error al contar facturas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       return datos;
   }
    
    public String[] obtenerResumenNDMes(String mes){
       int registros = 0;
       String[] datos = new String[4];
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM notadebito where "
                    + " MONTH(fec_nd) = ? and tipo_nd = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "notadebito");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();
            datos[0] = String.valueOf(registros);
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(neto_nd), 0) neto FROM Notadebito WHERE MONTH(fec_nd)"
                    + " = ? and tipo_nd = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "notadebito");
            res = pstm.executeQuery();
            res.next();
            datos[1] = res.getString("neto");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(iva_nd), 0) iva FROM Notadebito where MONTH(fec_nd)"
                    + " = ? and tipo_nd = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "notadebito");
            res = pstm.executeQuery();
            res.next();
            datos[2] = res.getString("iva");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(tot_nd), 0) tot FROM Notadebito where MONTH(fec_nd)"
                    + " = ? and tipo_nd = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "notadebito");
            res = pstm.executeQuery();
            res.next();
            datos[3] = res.getString("tot");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error al contar notadebito");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       return datos;
   }
    
    public String[] obtenerResumenNCMes(String mes){
       int registros = 0;
       String[] datos = new String[4];
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM notacredito where "
                    + " MONTH(fec_nc) = ? and tipo_nc = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "notacredito");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();
            datos[0] = String.valueOf(registros);
            
            pstm = conn.prepareStatement("SELECT SUM(neto) netotot FROM (SELECT coalesce(sum(neto_fac), 0) AS neto "
                    + "FROM notacredito INNER JOIN facturas using(id_fac) where MONTH(fec_nc) = ?  "
                    + "UNION "
                    + "SELECT coalesce(sum(neto_nd), 0) AS neto FROM notacredito INNER JOIN notadebito "
                    + "USING(id_nd) WHERE MONTH(fec_nc) = ?) TOTAL");
            pstm.setString(1, mes);
            pstm.setString(2, mes);
            res = pstm.executeQuery();
            res.next();
            datos[1] = res.getString("netotot");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT SUM(iva) ivatot FROM (SELECT coalesce(sum(iva_fac), 0) AS iva "
                    + "FROM notacredito INNER JOIN facturas using(id_fac) where MONTH(fec_nc) = ?  "
                    + "UNION "
                    + "SELECT coalesce(sum(iva_nd), 0) AS iva FROM notacredito INNER JOIN notadebito "
                    + "USING(id_nd) WHERE MONTH(fec_nc) = ?) TOTAL");
            pstm.setString(1, mes);
            pstm.setString(2, mes);
            res = pstm.executeQuery();
            res.next();
            datos[2] = res.getString("ivatot");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT SUM(tot) tottot FROM (SELECT coalesce(sum(tot_fac), 0) AS tot "
                    + "FROM notacredito INNER JOIN facturas using(id_fac) where MONTH(fec_nc) = ?  "
                    + "UNION "
                    + "SELECT coalesce(sum(tot_nd), 0) AS tot FROM notacredito INNER JOIN notadebito "
                    + "USING(id_nd) WHERE MONTH(fec_nc) = ?) TOTAL");
            pstm.setString(1, mes);
            pstm.setString(2, mes);
            res = pstm.executeQuery();
            res.next();
            datos[3] = res.getString("tottot");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error al contar nc para libro");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       return datos;
   }
    
    public String[][] obtenerFacturasMes(String mes){
       int registros = 0;
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Facturas where "
                    + " MONTH(fec_fac) = ? and tipo_fac = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "factura");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();            
       }catch(SQLException e){
            System.out.println("Error al obtener ventas mes");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       
       String[][] datos = new String[registros][8];
       
       try{
           PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac, fec_fac, "
                   + "clientes.rut_cli, dig_cli, raz_cli, neto_fac, iva_fac, tot_fac FROM Facturas "
                   + "INNER JOIN Jornadas on Jornadas.id_fac = Facturas.id_fac INNER JOIN Clientes ON "
                   + "Clientes.rut_cli = Jornadas.rut_cli WHERE MONTH(fec_fac) = ? and tipo_fac = ? GROUP BY Facturas.id_fac "
                   + "ORDER BY fol_fac");
           pstm.setString(1, mes);
           pstm.setString(2, "factura");
           ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfol = res.getString("fol_fac");
                String estfec = res.getString("fec_fac");
                String estrut = res.getString("Clientes.rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estnet = res.getString("neto_fac");
                String estiva = res.getString("iva_fac");
                String esttot = res.getString("tot_fac");
                datos[i] = new String[]{estfol, estfec, estrut + "-" + estdig, estraz, estnet, 
                    estiva, esttot};
                i++;
            }
            res.close();
       }catch(Exception e){
           
       }
       return datos;
   }
    
    public String[][] obtenerNDMes(String mes){
       int registros = 0;
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Notadebito where "
                    + " MONTH(fec_nd) = ? and tipo_nd = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "notadebito");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();            
       }catch(SQLException e){
            System.out.println("Error al obtener ventas (nd) mes");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       
       String[][] datos = new String[registros][8];
       try{
           PreparedStatement pstm = conn.prepareStatement("SELECT fol_nd, fec_nd, "
                   + "clientes.rut_cli, dig_cli, raz_cli, neto_nd, iva_nd, tot_nd FROM Notadebito "
                   + "INNER JOIN Jornadas on Jornadas.id_nd = Notadebito.id_nd INNER JOIN Clientes ON "
                   + "Clientes.rut_cli = Jornadas.rut_cli WHERE MONTH(fec_nd) = ? and tipo_nd = ? GROUP BY fol_nd "
                   + "ORDER BY fol_nd");
           pstm.setString(1, mes);
           pstm.setString(2, "notadebito");
           ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfol = res.getString("fol_nd");
                String estfec = res.getString("fec_nd");
                String estrut = res.getString("Clientes.rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estnet = res.getString("neto_nd");
                String estiva = res.getString("iva_nd");
                String esttot = res.getString("tot_nd");
                datos[i] = new String[]{estfol, estfec, estrut + "-" + estdig, estraz, estnet, 
                    estiva, esttot};
                i++;
            }
            res.close();
       }catch(Exception e){
           
       }
       return datos;
   }
    
    public String[][] obtenerNCMes(String mes){
       int registros = 0;
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Notacredito where "
                    + " MONTH(fec_nc) = ? and tipo_nc = ?");
            pstm.setString(1, mes);
            pstm.setString(2, "notacredito");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();            
       }catch(SQLException e){
            System.out.println("Error al obtener ventas (nc) mes");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       
       String[][] datos = new String[registros][8];
       
       try{
           PreparedStatement pstm = conn.prepareStatement("SELECT fol_nc, fec_nc, rut_cli, dig_cli, raz_cli, "
                   + "neto_fac neto, iva_fac iva, tot_fac tot FROM Notacredito INNER JOIN Facturas USING(id_fac) "
                   + "INNER JOIN Jornadas USING(id_fac) INNER JOIN Clientes USING(rut_cli) WHERE MONTH(fec_nc) = ? "
                   + "AND tipo_nc = 'notacredito'"
                   + "UNION "
                   + "SELECT fol_nc, fec_nc, rut_cli, dig_cli, raz_cli, neto_nd neto, iva_nd iva, tot_nd tot "
                   + "FROM Notacredito INNER JOIN Notadebito USING(id_nd) INNER JOIN Jornadas USING(id_nd) INNER JOIN "
                   + "Clientes USING(rut_cli) WHERE MONTH(fec_nc) = ? AND tipo_nc = 'notacredito' ORDER BY fol_nc");
           pstm.setString(1, mes);
           pstm.setString(2, mes);
           ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfol = res.getString("fol_nc");
                String estfec = res.getString("fec_nc");
                String estrut = res.getString("rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estnet = res.getString("neto");
                String estiva = res.getString("iva");
                String esttot = res.getString("tot");
                datos[i] = new String[]{estfol, estfec, estrut + "-" + estdig, estraz, estnet, 
                    estiva, esttot};
                i++;
            }
            res.close();
       }catch(Exception e){
           
       }
       return datos;
   }
    
    public String obtenerRazonFactura(String id, String tipo){
        String razon = "";
       try{
           Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
           PreparedStatement pstm = conn.prepareStatement("SELECT raz_cli FROM Facturas INNER JOIN jornadas "
                   + "ON Facturas.id_fac = Jornadas.id_fac INNER JOIN Clientes on Clientes.rut_cli = Jornadas.rut_cli "
                   + "WHERE fol_fac=? and tipo_fac=? LIMIT 1");
           pstm.setString(1, id);
           pstm.setString(2, tipo);
           ResultSet res = pstm.executeQuery();
           res.next();
           razon = res.getString("raz_cli");
           pstm.close();
           res.close();
       }catch(Exception e){
           e.printStackTrace();
       }
       return razon;
   }
    
    public String obtenerRazonFacturaOC(String id, String tipo){
        String razon = "";
       try{
           Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
           PreparedStatement pstm = conn.prepareStatement("SELECT raz_cli FROM Facturas INNER JOIN Jornadas_oc "
                   + "ON Facturas.id_fac = Jornadas_oc.id_fac INNER JOIN Clientes on Clientes.rut_cli = Jornadas_oc.rut_cli "
                   + "WHERE fol_fac=? and tipo_fac=? LIMIT 1");
           pstm.setString(1, id);
           pstm.setString(2, tipo);
           ResultSet res = pstm.executeQuery();
           res.next();
           razon = res.getString("raz_cli");
           pstm.close();
           res.close();
       }catch(Exception e){
           e.printStackTrace();
       }
       return razon;
   }
    
    public boolean verificarExisteFactura(String id, String tipo){
       try{
           Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
           PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM Facturas WHERE fol_fac=?"
                   + " and tipo_fac=?");
           pstm.setString(1, id);
           pstm.setString(2, tipo);
           ResultSet res = pstm.executeQuery();
           res.next();
           int total = res.getInt("total");
           pstm.close();
           res.close();
           if(total > 0) return true;
           return false;
       }catch(Exception e){
           e.printStackTrace();
           return false;
       }
   }
    
    public int tipoNC(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Notacredito "
                    + "WHERE fol_nc = ? and id_fac is null");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            int total = res.getInt("total");
            return total;
       }catch(SQLException e){
            System.out.println("Error obtener forma pago por id");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return -1;
    }
    
    public String obtenerRuta(){
        String ruta = "";
       try{
           Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
           PreparedStatement pstm = conn.prepareStatement("SELECT car_ruta FROM rutas WHERE nom_ruta = 'carpeta'");
           ResultSet res = pstm.executeQuery();
           res.next();
           ruta = res.getString("car_ruta");
           pstm.close();
           res.close();
       }catch(Exception e){
           e.printStackTrace();
       }
       return ruta;
   }
    
    public int cambiarRuta(String ruta){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Rutas set car_ruta=? WHERE nom_ruta = 'carpeta'");
            pstm.setString(1, ruta);
            pstm.executeUpdate();
            return 1;
       }catch(SQLException e){
            System.out.println("Error cambiar ruta");
            System.out.println(e);
            return 0;
       }catch(ClassNotFoundException e){
            System.out.println(e);
            return 0;
       }
    }
    
    public void liberarNC(String[] ots){
        for(String ot: ots){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, login, password);
                PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas set nc_ot = 1 WHERE cod_ot = ?");
                pstm.setString(1, ot);
                pstm.executeUpdate();
                pstm.close();
            }catch(SQLException e){
                System.out.println("Error liberar nc");
                System.out.println(e);
            }catch(ClassNotFoundException e){
                System.out.println(e);
            }
            
        }
    }
    
    public void liberarNCOC(String[] ocs){
        for(String oc: ocs){
            try{
                Class.forName("com.mysql.jdbc.Driver");
                conn = DriverManager.getConnection(url, login, password);
                PreparedStatement pstm = conn.prepareStatement("UPDATE Jornadas_oc set nc_oc = 1 WHERE cod_oc = ?");
                pstm.setString(1, oc);
                pstm.executeUpdate();
                pstm.close();
            }catch(SQLException e){
                System.out.println("Error liberar nc oc");
                System.out.println(e);
            }catch(ClassNotFoundException e){
                System.out.println(e);
            }
            
        }
    }
    
    public Object[][] listarReporteFacturadas(String fecIn, String fecFin){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total from  facturas WHERE fec_fac >= ? AND fec_fac <= ?");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm = conn.prepareStatement("SELECT COUNT(1) as total from  notacredito WHERE fec_nc >= ? AND fec_nc <= ?");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            res = pstm.executeQuery();
            res.next();
            registros += res.getInt("total");
            res.close();
            pstm = conn.prepareStatement("SELECT COUNT(1) as total from  notadebito  WHERE fec_nd >= ? AND fec_nd <= ?");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            res = pstm.executeQuery();
            res.next();
            registros += res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar reporte facturadas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][11];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac fol, raz_cli, gir_cli, dir_cli, "
                    + "ciu_cli, com_cli, tot_fac tot, neto_fac neto, iva_fac iva,fec_fac fec, tipo_fac tipo "
                    + "FROM facturas f INNER JOIN("
                    + "SELECT id_fac, rut_cli FROM jornadas GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c ON c.rut_cli = b.rut_cli WHERE fec_fac >= ? AND fec_fac <= ?"
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_fac tot, "
                    + "neto_fac neto, iva_fac iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_fac, rut_cli FROM Jornadas GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac WHERE fec_nc >= ? AND fec_nc <= ?"
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd neto, iva_nd iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_nd, rut_cli FROM Jornadas GROUP BY id_nd) "
                    + "b ON b.id_nd = f.id_nd INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Notadebito ON Notadebito.id_nd = f.id_nd WHERE fec_nc >= ? AND fec_nc <= ?"
                    + "UNION "
                    + "SELECT fol_nd fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd as neto, iva_nd  iva, fec_nd as fec, tipo_nd  tipo FROM Notadebito f INNER JOIN( "
                    + "SELECT id_fac, rut_cli FROM Jornadas GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac WHERE fec_nd >= ? AND fec_nd <= ? "
                    + "UNION "
                    + "SELECT fol_fac fol, raz_cli, gir_cli, dir_cli, "
                    + "ciu_cli, com_cli, tot_fac tot, neto_fac neto, iva_fac iva,fec_fac fec, tipo_fac tipo "
                    + "FROM facturas f INNER JOIN ("
                    + "SELECT id_fac, rut_cli FROM jornadas_oc GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c ON c.rut_cli = b.rut_cli WHERE fec_fac >= ? AND fec_fac <= ?"
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_fac tot, "
                    + "neto_fac neto, iva_fac iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_fac, rut_cli FROM Jornadas_oc GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac WHERE fec_nc >= ? AND fec_nc <= ?"
                    + "UNION "
                    + "SELECT fol_nc fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd neto, iva_nd iva, fec_nc fec, tipo_nc tipo FROM Notacredito f INNER JOIN("
                    + "SELECT id_nd, rut_cli FROM Jornadas_oc GROUP BY id_nd) "
                    + "b ON b.id_nd = f.id_nd INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Notadebito ON Notadebito.id_nd = f.id_nd WHERE fec_nc >= ? AND fec_nc <= ?"
                    + "UNION "
                    + "SELECT fol_nd fol, raz_cli, gir_cli, dir_cli, ciu_cli, com_cli, tot_nd tot, "
                    + "neto_nd as neto, iva_nd  iva, fec_nd as fec, tipo_nd  tipo FROM Notadebito f INNER JOIN( "
                    + "SELECT id_fac, rut_cli FROM Jornadas_oc GROUP BY id_fac) "
                    + "b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN "
                    + "Facturas ON Facturas.id_fac = f.id_fac WHERE fec_nd >= ? AND fec_nd <= ? "
                    + "order by fec DESC");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            pstm.setString(3, fecIn);
            pstm.setString(4, fecFin);
            pstm.setString(5, fecIn);
            pstm.setString(6, fecFin);
            pstm.setString(7, fecIn);
            pstm.setString(8, fecFin);
            pstm.setString(9, fecIn);
            pstm.setString(10, fecFin);
            pstm.setString(11, fecIn);
            pstm.setString(12, fecFin);
            pstm.setString(13, fecIn);
            pstm.setString(14, fecFin);
            pstm.setString(15, fecIn);
            pstm.setString(16, fecFin);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("fol");
                String estfec = res.getString("fec");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String esttot = res.getString("tot");
                String estnet = res.getString("neto");
                String estiva = res.getString("iva");
                String esttip = res.getString("tipo");

                data[i] = new String[]{estid, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                    estiva, esttot, esttip };
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar reporte facturadas");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }

    public String[] obtenerOcsPorIdNDNC(String id) {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM notacredito nc INNER"
                    + " JOIN notadebito n ON nc.id_nd = n.id_nd INNER JOIN Jornadas_oc ON "
                    + "jornadas_oc.id_nd = n.id_nd WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener oc por id nc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        String[] data = new String[registros];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT cod_oc FROM "
                    + "Jornadas_oc INNER JOIN Notadebito n ON jornadas.id_nd = n.id_nd INNER JOIN "
                    + "notacredito nc ON nc.id_nd = n.id_nd WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcod = res.getString("cod_oc");
                data[i] = estcod;
                i++;
            }
            
        }catch(SQLException e){
            System.out.println("Error obtener ocs por id nc");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }

    public String[] obtenerOcsPorIdNC(String id) {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Notacredito INNER"
                    + " JOIN Facturas ON Notacredito.id_fac = facturas.id_fac INNER JOIN Jornadas_oc ON "
                    + "jornadas_oc.id_fac = facturas.id_fac WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error obtener oc por id nc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        String[] data = new String[registros];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT cod_oc FROM "
                    + "Jornadas_oc INNER JOIN Facturas ON jornadas_oc.id_fac = facturas.id_fac INNER JOIN "
                    + "Notacredito ON notacredito.id_fac = facturas.id_fac WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcod = res.getString("cod_oc");
                data[i] = estcod;
                i++;
            }
        }catch(SQLException e){
            System.out.println("Error obtener ocs por id nc");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }

    public Object[][] listarReporteCompras(String fecIn, String fecFin) {
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Compras WHERE fec_in_com >= ? AND fec_in_com <= ?");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar reporte historico compras");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][15];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_com, raz_pro, gir_pro, dir_pro, ciu_pro, com_pro, fec_in_com, neto_com, iva_com, tot_com, impes_com, "
                    + "impvar_com, tipo_com, clas_com, est_com FROM Compras_fac INNER JOIN Proveedores USING(rut_pro) WHERE fec_in_com >= ? AND fec_in_com <= ? ORDER BY fol_com");
            pstm.setString(1, fecIn);
            pstm.setString(2, fecFin);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfol = res.getString("fol_com");
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String estdir = res.getString("dir_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String estfec = res.getString("fec_in_com");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estnet = res.getString("neto_com");
                String estiva = res.getString("iva_com");
                String esttot = res.getString("tot_com");
                String estimpes = res.getString("impes_com");
                String estimpvar = res.getString("impvar_com");
                String esttip = res.getString("tipo_com");
                String estclas = res.getString("clas_com");
                String estest = res.getString("est_com");
                data[i] = new String[]{estfol, estraz, estgir, estdir, estciu, estcom, estfec, estnet, estiva, esttot, estimpes, estimpvar, esttip, estclas, estest};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar reporte historico compras");
            System.out.println(e);
        }catch(Exception e){
            System.out.println(e);
        }
        return data;
    }

//    public Object[][] listarReporteCobranza(String est) {
//        int registros = 0;
//        
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cobranza WHERE fec_in_com >= ? AND fec_in_com <= ?");
//            pstm.setString(1, fecIn);
//            pstm.setString(2, fecFin);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//       }catch(SQLException e){
//            System.out.println("Error listar reporte historico compras");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        
//        Object[][] data = new String[registros][15];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT fol_com, raz_pro, gir_pro, dir_pro, ciu_pro, com_pro, fec_in_com, neto_com, iva_com, tot_com, impes_com, "
//                    + "impvar_com, tipo_com, clas_com, est_com FROM Compras_fac INNER JOIN Proveedores USING(rut_pro) WHERE fec_in_com >= ? AND fec_in_com <= ? ORDER BY fol_com");
//            pstm.setString(1, fecIn);
//            pstm.setString(2, fecFin);
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while(res.next()){
//                String estfol = res.getString("fol_com");
//                String estraz = res.getString("raz_cli");
//                String estgir = res.getString("gir_cli");
//                String estdir = res.getString("dir_cli");
//                String estciu = res.getString("ciu_cli");
//                String estcom = res.getString("com_cli");
//                String estfec = res.getString("fec_in_com");
//                java.util.Date fecha = formatDate.parse(estfec);
//                estfec = newFormat.format(fecha);
//                String estnet = res.getString("neto_com");
//                String estiva = res.getString("iva_com");
//                String esttot = res.getString("tot_com");
//                String estimpes = res.getString("impes_com");
//                String estimpvar = res.getString("impvar_com");
//                String esttip = res.getString("tipo_com");
//                String estclas = res.getString("clas_com");
//                String estest = res.getString("est_com");
//                data[i] = new String[]{estfol, estraz, estgir, estdir, estciu, estcom, estfec, estnet, estiva, esttot, estimpes, estimpvar, esttip, estclas, estest};
//                i++;
//            }
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error listar reporte historico compras");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return data;
//    }

    public Object[][] listarReporteCobranza(String est) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
