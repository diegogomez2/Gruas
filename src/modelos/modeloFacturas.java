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
import java.util.Date;
import static modelos.modeloClientes.url;

public class modeloFacturas {
    static String login = "root";
    static String password = "205243";
    static String url = "jdbc:mysql://localhost:3306/fact_gruas";
    Connection conn = null;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    
    public String ingresarFacturada(String fec, int valorNeto, int valorIva, int valorTotal, String tipo){        
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into facturas (fec_fac, neto_fac,"
                    + "iva_fac, tot_fac, tipo_fac)"
                    + " values (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fec);
            pstm.setInt(2, valorNeto);
            pstm.setInt(3, valorIva);
            pstm.setInt(4, valorTotal);
            pstm.setString(5, tipo);
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
            System.out.println("Error listar facturadas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][11];
        
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

                data[i] = new String[]{estid, estraz, estgir, estdir, estciu, estcom, estfec, estnet,
                    estiva, esttot };
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
    
    public String[][] obtenerOtsPorIdNC(String id) {
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
        String[][] data = new String[registros][4];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT cod_ot, neto_ot, iva_ot, total_ot FROM "
                    + "jornadas INNER JOIN facturas ON jornadas.id_fac = facturas.id_fac INNER JOIN "
                    + "notacredito ON notacredito.id_fac = facturas.id_fac WHERE id_nc = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcod = res.getString("cod_ot");
                String estnet = res.getString("neto_ot");
                String estiva = res.getString("iva_ot");
                String esttot = res.getString("tot_ot");
                data[i] = new String[]{estcod, estnet, estiva, esttot};
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
    
    public String ingresarNotaCredito(String id, String razon){    
        String fecha = formatDate.format(new Date());
        String id_nc;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into notacredito (fec_nc, raz_nc, id_fac)"
                    + " values (?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fecha);
            pstm.setString(2, razon);
            pstm.setInt(3, Integer.parseInt(id));
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            id_nc = res.getString(1);
            res.next();
            pstm.close();
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
    
    public String ingresarND(String fec, int valorNeto, int valorIva, int valorTotal, String id_fac){        
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into notadebito (fec_nd, neto_nd,"
                    + "iva_nd, tot_nd, id_fac)"
                    + " values (?, ?, ?, ?, ?)", PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setString(1, fec);
            pstm.setInt(2, valorNeto);
            pstm.setInt(3, valorIva);
            pstm.setInt(4, valorTotal);
            pstm.setString(5, id_fac);
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
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
}
