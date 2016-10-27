/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelos.Connector;

/**
 *
 * @author Diego
 */
public class modeloCobranzas {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    
//    public Object[][] listarFacturadas(){
//        int registros = 0;
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM facturas f LEFT JOIN "
//                    + "notacredito n on f.id_fac = n.id_fac where n.id_fac is null");
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            registros = res.getInt("total");
//            res.close();
//            pstm = conn.prepareStatement("SELECT COUNT(1) as total from  notadebito nd LEFT JOIN notacredito n on "
//                    + "nd.id_fac ");
//            res = pstm.executeQuery();
//            res.next();
//            registros += res.getInt("total");
//            res.close();
//       }catch(SQLException e){
//            System.out.println("Error listar facturadas");
//            System.out.println(e);
//       }catch(ClassNotFoundException e){
//            System.out.println(e);
//       }
//        
//        Object[][] data = new String[registros][11];
//        
//        try{
//            PreparedStatement pstm = conn.prepareStatement("SELECT f.*, c.rut_cli, c.dig_cli, c.raz_cli, "
//                    + "c.con_cli, c.tel_cli, j.cod_ot, j.pag_ot, j.cond_ot FROM facturas f LEFT JOIN notacredito n on f.id_fac = n.id_fac INNER JOIN "
//                    + "jornadas j on j.id_fac = f.id_fac INNER JOIN clientes c on c.rut_cli = j.rut_cli"
//                    + " WHERE n.id_fac is null");
//            ResultSet res = pstm.executeQuery();
//            int i = 0;
//            while(res.next()){
//                String estot = res.getString("cod_ot");
//                String estid = res.getString("fol_fac");
//                String estrut = res.getString("rut_cli");
//                String estdig = res.getString("dig_cli");
//                String estraz = res.getString("raz_cli");
//                String estfec = res.getString("fec_fac");
//                java.util.Date fecha = formatDate.parse(estfec);
//                estfec = newFormat.format(fecha);
//                Date cur = new Date();
//                long dias = Math.round((cur.getTime() - fecha.getTime()) / (double) 86400000);
//                long dias = ((cur.getTime() - fecha.getTime()) / (long) 86400000);
//                String estdia = String.valueOf(dias);
//                String estnet = res.getString("neto_fac");
//                String esttot = res.getString("tot_fac");
//                String estiva = res.getString("iva_fac");
//                String estfor = res.getString("pag_ot");
//                String estmed = res.getString("cond_ot");
//                String estabo = "No";
//                String estmabo = res.getString("abo_fac");
//                if(Integer.parseInt(estmabo) > 0) estabo = "Si";
//                String estcon = res.getString("con_cli");
//                String esttel = res.getString("tel_cli");
//                data[i] = new String[]{estot, estid, estrut + "-" + estdig, estraz, estfec, estdia, estnet,
//                    estiva, esttot, estfor, estmed, estabo, estmabo, estcon, esttel };
//                i++;
//            }
//            res.close();
//        }catch(SQLException e){
//            System.out.println("Error listar facturadas");
//            System.out.println(e);
//        }catch(Exception e){
//            System.out.println(e);
//        }
//        return data;
//    }
    
    public Object[][] listarFacturadas(){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM Facturas f LEFT JOIN "
                    + "Notacredito n ON f.id_fac = n.id_fac WHERE n.id_fac is null");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM Notadebito nd LEFT JOIN Notacredito n ON "
                    + " nd.id_fac = n.id_fac WHERE n.id_fac = null");
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
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac as fol, c.rut_cli, dig_cli, raz_cli, "
                    + "ges_fac as ges, con_cli, tel_cli, tot_fac as tot, neto_fac as neto, iva_fac as iva, "
                    + "fec_fac as fec, abo_fac as abo, pag_ot, cond_ot, tipo_fac as tipo, f.id_fac as id FROM Facturas f LEFT JOIN Notacredito n ON "
                    + "f.id_fac = n.id_fac INNER JOIN( SELECT id_fac, rut_cli, pag_ot, cond_ot FROM Jornadas GROUP BY "
                    + "id_fac ) b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli WHERE n.id_fac is null "
                    + "UNION "
                    + "SELECT fol_nd as fol, c.rut_cli, dig_cli, raz_cli, ges_nd as ges, con_cli, tel_cli, "
                    + "tot_nd as tot, neto_nd as neto, iva_nd as iva, fec_nd as fec, abo_nd as abo, pag_ot, "
                    + "cond_ot, tipo_nd as tipo, f.id_fac as id FROM "
                    + "Notadebito f LEFT JOIN Notacredito n ON n.id_fac = f.id_fac INNER JOIN( "
                    + "SELECT id_fac, rut_cli, pag_ot, cond_ot FROM Jornadas GROUP BY id_fac ) b ON b.id_fac = f.id_fac "
                    + "INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN Facturas ON "
                    + "facturas.id_fac = f.id_fac WHERE n.id_fac is null ORDER BY fec DESC");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("fol");
                String estrut = res.getString("rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estfec = res.getString("fec");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                Date cur = new Date();
//                long dias = Math.round((cur.getTime() - fecha.getTime()) / (double) 86400000);
                long dias = ((cur.getTime() - fecha.getTime()) / (long) 86400000);
                String estdia = String.valueOf(dias);
                String estnet = res.getString("neto");
                String esttot = res.getString("tot");
                String estiva = res.getString("iva");
                String estfor = res.getString("pag_ot");
                String estmed = res.getString("cond_ot");
                String estabo = "No";
                String estmabo = res.getString("abo");
                if(Integer.parseInt(estmabo) > 0) estabo = "Si";
                String estcon = res.getString("con_cli");
                String esttel = res.getString("tel_cli");
                String esttipo = res.getString("tipo");
                String estfac = res.getString("id");
                data[i] = new String[]{estid, estrut + "-" + estdig, estraz, estfec, estdia, estnet,
                    estiva, esttot, estfor, estmed, estabo, estmabo, estcon, esttel, esttipo, estfac};
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
    
    public Object[][] listarFacturadasGestion(){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM Facturas f LEFT JOIN "
                    + "Notacredito n ON f.id_fac = n.id_fac WHERE n.id_fac is null");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM Notadebito nd LEFT JOIN Notacredito n ON "
                    + " nd.id_fac = n.id_fac WHERE n.id_fac = null");
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
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac as fol, c.rut_cli, dig_cli, raz_cli, "
                    + "ges_fac as ges, con_cli, tel_cli, tot_fac as tot, neto_fac as neto, iva_fac as iva, "
                    + "fec_fac as fec, abo_fac as abo, pag_ot, cond_ot, tipo_fac as tipo, f.id_fac as id FROM Facturas f LEFT JOIN Notacredito n ON "
                    + "f.id_fac = n.id_fac INNER JOIN( SELECT id_fac, rut_cli, pag_ot, cond_ot FROM Jornadas GROUP BY "
                    + "id_fac ) b ON b.id_fac = f.id_fac INNER JOIN Clientes c on c.rut_cli = b.rut_cli WHERE n.id_fac is null "
                    + "UNION "
                    + "SELECT fol_nd as fol, c.rut_cli, dig_cli, raz_cli, ges_nd as ges, con_cli, tel_cli, "
                    + "tot_nd as tot, neto_nd as neto, iva_nd as iva, fec_nd as fec, abo_nd as abo, pag_ot, "
                    + "cond_ot, tipo_nd as tipo, f.id_fac as id FROM "
                    + "Notadebito f LEFT JOIN Notacredito n ON n.id_fac = f.id_fac INNER JOIN( "
                    + "SELECT id_fac, rut_cli, pag_ot, cond_ot FROM Jornadas GROUP BY id_fac ) b ON b.id_fac = f.id_fac "
                    + "INNER JOIN Clientes c on c.rut_cli = b.rut_cli INNER JOIN Facturas ON "
                    + "facturas.id_fac = f.id_fac WHERE n.id_fac is null ORDER BY fec DESC");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estid = res.getString("fol");
                String estrut = res.getString("rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estfec = res.getString("fec");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                Date cur = new Date();
//                long dias = Math.round((cur.getTime() - fecha.getTime()) / (double) 86400000);
                long dias = ((cur.getTime() - fecha.getTime()) / (long) 86400000);
                String estdia = String.valueOf(dias);
                String estnet = res.getString("neto");
                String esttot = res.getString("tot");
                String estiva = res.getString("iva");
                String estfor = res.getString("pag_ot");
                String estmed = res.getString("cond_ot");
                String estabo = "No";
                String estmabo = res.getString("abo");
                if(Integer.parseInt(estmabo) > 0) estabo = "Si";
                String estcon = res.getString("con_cli");
                String esttel = res.getString("tel_cli");
                String esttipo = res.getString("tipo");
                String estfac = res.getString("id");
                String cant = String.valueOf(this.obtenerCantGestion(estfac, esttipo));
                String[] gestion = this.obtenerUltGestion(estfac, esttipo);
                System.out.println(cant);
                System.out.println(gestion[0] + gestion[1] + gestion[2]+gestion[3]);
                data[i] = new String[]{estid, estrut + "-" + estdig, estraz, estfec, estdia, estnet,
                    estiva, esttot, estfor, estmed, estabo, estmabo, estcon, esttel, cant, gestion[0], 
                gestion[1], gestion[2], gestion[3]};
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
    
    public String gestionCobranza(String id, String tipo, String ges, String res, String fec, String obs){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm;
            System.out.println(tipo);
            if(tipo.compareTo("notadebito") == 0){
                pstm = conn.prepareStatement("INSERT INTO Gestion_cob (id_nd, ges_ges, res_ges, fec_res_ges, "
                        + "obs_ges) values (?, ?, ?, ?, ?)");
            }else{
                pstm = conn.prepareStatement("INSERT INTO Gestion_cob (id_fac, ges_ges, res_ges, fec_res_ges, "
                        + "obs_ges) values (?, ?, ?, ?, ?)");
            }
            pstm.setString(1, id);
            pstm.setString(2, ges);
            pstm.setString(3, res);
            pstm.setString(4, fec);
            pstm.setString(5, obs);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar gestion");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String gestionPago(String id, String monto){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE facturas SET abo_fac=?+abo_fac WHERE fol_fac=?");
            pstm.setString(1, monto);
            pstm.setString(2, id);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar pago");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public int getSaldo(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT tot_fac, abo_fac FROM facturas WHERE "
                    + "fol_fac=?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            int saldo = Integer.parseInt(res.getString("tot_fac")) - Integer.parseInt(res.getString("abo_fac"));
            pstm.close();
            res.close();
            return saldo;
        }catch(SQLException e){
            System.out.println("Error al ingresar gestion");
            System.out.println(e);
            return -1;
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return -1;
        }
    }
    
    public Object[][] obtenerGestion(String id, String tipo){
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm;
            if(tipo.compareTo("notadebito") == 0){
                pstm = conn.prepareStatement("SELECT COUNT(1) as total from Gestion_cob WHERE id_nd = ?");
            }else{
                pstm = conn.prepareStatement("SELECT COUNT(1) as total from Gestion_cob WHERE id_fac = ?");
            }
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            pstm.close();
            res.close();
       }catch(SQLException e){
            System.out.println("Error listar gestiones");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][4];
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm;
            if(tipo.compareTo("notadebito") == 0){
                pstm = conn.prepareStatement("SELECT ges_ges, res_ges, fec_res_ges, obs_ges"
                    + " FROM Gestion_cob WHERE id_nd=?");
            }else{
                pstm = conn.prepareStatement("SELECT ges_ges, res_ges, fec_res_ges, obs_ges"
                    + " FROM Gestion_cob WHERE id_fac=?");
            }
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estges = res.getString("ges_ges");
                String estres = res.getString("res_ges");
                String estfec = res.getString("fec_res_ges");
                String estobs = res.getString("obs_ges");
                data[i] = new String[]{estges, estres, estfec, estobs};
                i++;
            }
            pstm.close();
            res.close();
            return data;
        }catch(SQLException e){
            System.out.println("Error al ingresar gestion");
            System.out.println(e);
            return data;
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return data;
        }
    }
    
    public int obtenerCantGestion(String id, String tipo){
        int cant = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm;
            if(tipo.compareTo("notadebito") == 0){
                pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM gestion_cob WHERE id_nd=?");
            }else{
                pstm = conn.prepareStatement("SELECT COUNT(1) as total FROM gestion_cob WHERE id_fac=?");
            }
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            cant = res.getInt("total");
            pstm.close();
            res.close();
            return cant;
        }catch(SQLException e){
            System.out.println("Error al ingresar gestion");
            System.out.println(e);
            return cant;
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return cant;
        }
    }
    
    public String[] obtenerUltGestion(String id, String tipo){
        
        String[] data = new String[4];
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm;
            if(tipo.compareTo("notadebito") == 0){
                pstm = conn.prepareStatement("SELECT ges_ges, res_ges, fec_res_ges, obs_ges"
                    + " FROM Gestion_cob WHERE id_nd=? and id_ges = ( SELECT MAX(id_ges) FROM Gestion_cob "
                        + "WHERE id_nd=? )");
            }else{
                pstm = conn.prepareStatement("SELECT ges_ges, res_ges, fec_res_ges, obs_ges"
                    + " FROM Gestion_cob WHERE id_fac=? and id_ges = ( SELECT MAX(id_ges) FROM Gestion_cob "
                        + "WHERE id_fac=? )");
            }
            pstm.setString(1, id);
            pstm.setString(2, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estges = res.getString("ges_ges");
            String estres = res.getString("res_ges");
            String estfec = res.getString("fec_res_ges");
            String estobs = res.getString("obs_ges");
            data = new String[]{estges, estres, estfec, estobs};
            pstm.close();
            res.close();
            return data;
        }catch(SQLException e){
            System.out.println("Error al ingresar gestion");
            System.out.println(e);
            return data;
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return data;
        }
    }
}
