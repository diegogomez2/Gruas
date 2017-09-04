/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos2;

import java.sql.CallableStatement;
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
 * @author Diego
 */
public class modeloCompras {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    DateFormat newFormat = new SimpleDateFormat("dd-MM-yyyy");
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    
    public Object[][] listarCompras() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Compras_fac");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar compras");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][7];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT id_com, Compras_fac.rut_pro, dig_pro, raz_pro, tipo_com,"
                    + " fol_com, fol_int_com, fec_pag_com, coalesce(est_com,'') as est_com FROM Compras_fac "
                    + "INNER JOIN Proveedores ON"
                    + " Compras_fac.rut_pro = Proveedores.rut_pro ORDER BY fec_pag_com");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("Compras_fac.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
                String esttip = res.getString("tipo_com");
                String estfol = res.getString("fol_com");
                String estfolin = res.getString("fol_int_com");
                String estfecpag = res.getString("fec_pag_com");
                java.util.Date fecha = formatDate.parse(estfecpag);
                estfecpag = newFormat.format(fecha);
                String estest = res.getString("est_com");
                String estid = res.getString("id_com");
                data[i] = new String[]{estid, esttip, estfol, estfolin, estrut + "-" + estdig, estraz, estfecpag, estest};
                i++;
            }
            res.close();
//            CallableStatement cstmt = conn.prepareCall("{call update_pagos}");
//            cstmt.execute();
//            cstmt.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch (ParseException e){
            
        }
        return data;
    }
    
    public String ingresarCompra(String[] data){
        String id = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT into Compras_fac (rut_pro,"
                    + "tipo_com, fol_com, fol_int_com, fec_in_com, ord_com, fec_pag_com, form_com, asun_com, obs_com,"
                    + "med_com, ban_com, num_tc_com, est_com, clas_com, tot_com, iva_com, neto_com, impes_com, impvar_com) "
                    + "values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
                    PreparedStatement.RETURN_GENERATED_KEYS);
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3] + "/" + data[20] + "/" + data[21]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setString(13, data[12]);
            pstm.setString(14, data[13]);
            pstm.setString(15, data[14]);
            pstm.setInt(16, Integer.parseInt(data[15]));
            pstm.setInt(17, Integer.parseInt(data[16]));
            pstm.setInt(18, Integer.parseInt(data[17]));
            pstm.setString(19, data[18]);
            pstm.setString(20, data[19]);
            pstm.execute();
            ResultSet res = pstm.getGeneratedKeys();
            res.next();
            id = res.getString(1);
            pstm.close();
            res.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar compra");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return id;
    }

    public String eliminarCompra(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Compras_fac WHERE id_com = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar compra");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }

    public String modificarCompra(String[] data, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Compras_fac set rut_pro=?, tipo_com=?, fol_com=?, "
                    + "fol_int_com=?, fec_in_com=?, ord_com=?, fec_pag_com=?, form_com=?, asun_com=?, obs_com=?,"
                    + "med_com=?, ban_com=?, num_tc_com=?, est_com=?, clas_com=?, tot_com=?, iva_com=?, "
                    + "neto_com=?, impes_com=?, impvar_com=? WHERE id_com = ?");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setInt(4, Integer.parseInt(data[3]));
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setString(13, data[12]);
            pstm.setString(14, data[13]);
            pstm.setString(15, data[14]);
            pstm.setString(16, data[15]);
            pstm.setString(17, data[16]);
            pstm.setString(18, data[17]);
            pstm.setString(19, data[18]);
            pstm.setString(20, data[19]);
            pstm.setString(21, id);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar compra");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String folioFac(){
        String fol = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT fol_fac from folios WHERE id_fol = 2 "
                    + "FOR UPDATE"); 
            ResultSet res = pstm.executeQuery();
            res.next();
            String estfol = res.getString("fol_fac");
            fol = estfol;
            pstm = conn.prepareStatement("UPDATE folios SET fol_fac = fol_fac + 1 WHERE id_fol = 2");
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error obtener folio fac compra");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return fol;
    }
    
   public String ingresarCheques(String[][] data, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            for (String[] data1 : data) {
                PreparedStatement pstm = conn.prepareStatement("INSERT into Cuotas (id_com, num_cuo, fol_cuo,"
                        + "fec_cuo, mon_cuo, est_cuo) values (?, ?, ?, ?, ?, ?)");
                pstm.setInt(1, Integer.parseInt(id));
                pstm.setInt(2, Integer.parseInt(data1[0]));
                pstm.setInt(3, Integer.parseInt(data1[1]));
                pstm.setString(4, data1[2]);
                pstm.setInt(5, Integer.parseInt(data1[3]));
                pstm.setString(6, data1[4]);
                pstm.execute();
                pstm.close();
            }
            CallableStatement cstmt = conn.prepareCall("{call set_estado_compra(?)}");
            cstmt.setString(1, id);
            cstmt.execute();
        }catch(SQLException e){
            System.out.println("Error al ingresar cheque");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
   
   public String ingresarProductos(String[][] data, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            int iva;
            for (String[] data1 : data) {
                PreparedStatement pstm = conn.prepareStatement("INSERT into Productos (id_com, cod_prod, det_prod,"
                        + "cant_prod, prec_prod, iva_prod) values (?, ?, ?, ?, ?, ?)");
                pstm.setInt(1, Integer.parseInt(id));
                pstm.setString(2, data1[0]);
                pstm.setString(3, data1[1]);
                pstm.setDouble(4, Double.parseDouble(data1[2]));
                pstm.setDouble(5, Double.parseDouble(data1[3]));
                System.out.println("data "+data1[4]);
                iva = (data1[4].compareTo("Con") == 0) ? 1 : 0;
                pstm.setInt(6, iva);
                pstm.execute();
                pstm.close();
            }
        }catch(SQLException e){
            System.out.println("Error al ingresar producto");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
   
   public String ingresarImpuestos(String[][] data, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            int iva;
            for (String[] data1 : data) {
                PreparedStatement pstm = conn.prepareStatement("INSERT INTO Impuestos (id_com, nom_imp, mon_imp) "
                        + "values (?, ?, ?)");
                pstm.setInt(1, Integer.parseInt(id));
                pstm.setString(2, data1[0]);
                pstm.setInt(3, Integer.parseInt(data1[1]));
                pstm.execute();
                pstm.close();
            }
        }catch(SQLException e){
            System.out.println("Error al ingresar impuesto");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
   
   public String ingresarCuotas(String[][] data, String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            for (String[] data1 : data) {
                
                PreparedStatement pstm = conn.prepareStatement("INSERT into Cuotas (id_com, num_cuo,"
                        + "fec_cuo, mon_cuo, est_cuo) values (?, ?, ?, ?, ?)");
                pstm.setInt(1, Integer.parseInt(id));
                pstm.setInt(2, Integer.parseInt(data1[0]));
                pstm.setString(3, data1[1]);
                pstm.setInt(4, Integer.parseInt(data1[2]));
                pstm.setString(5, data1[3]);
                pstm.execute();
                pstm.close();
            }
            CallableStatement cstmt = conn.prepareCall("{call set_estado_compra(?)}");
            cstmt.setString(1, id);
            cstmt.execute();
        }catch(SQLException e){
            System.out.println("Error al ingresar cuota");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
   
   public String borrarCuotas(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Cuotas WHERE id_com = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar cuotas");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
   
   public String borrarProductos(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Productos WHERE id_com = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar productos");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
   
   public String borrarImpuestos(String id){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM Impuestos WHERE id_com = ?");
            pstm.setString(1, id);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar impuestos");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
   
   public String[] obtenerCompraPorId(String id){
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT *, coalesce(impes_com, 0) impes, coalesce(impvar_com, 0) "
                    + "impvar FROM Compras_fac INNER JOIN Proveedores "
                    + "ON Compras_fac.rut_pro = Proveedores.rut_pro WHERE id_com = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            String esttip = res.getString("tipo_com");
            String estfol = res.getString("fol_com");
            String estrut = res.getString("Compras_fac.rut_pro");
            String estdig = res.getString("dig_pro");
            String estraz = res.getString("raz_pro");
            String estgir = res.getString("gir_pro");
            String estdir = res.getString("dir_pro");
            String estcon = res.getString("con_pro");
            String estfecin = res.getString("fec_in_com");
            String estord = res.getString("ord_com");
            String estfecpag = res.getString("fec_pag_com");
            String estfor = res.getString("form_com");
            String estasu = res.getString("asun_com");
            String estobs = res.getString("obs_com");
            String estmed = res.getString("med_com");
            String estban = res.getString("ban_com");
            String estntc = res.getString("num_tc_com");
            String estest = res.getString("est_com");
            String estclas = res.getString("clas_com");
            String esttot = res.getString("tot_com");
            String estiva = res.getString("iva_com");
            String estnet = res.getString("neto_com");
            String estimpes = res.getString("impes");
            String estimpvar = res.getString("impvar");
            data = new String[]{esttip, estfol, estrut + "-" + estdig , estraz, estgir, estdir, estcon, 
                estfecin, estord, estfecpag, estfor, estasu, estobs, estmed, estban, estntc, estest, estclas, 
                estnet, estiva, esttot, estimpes, estimpvar};
        }catch(SQLException e){
            System.out.println("Error al obtener proveedor por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
   
   public Object[][] obtenerChequesPorId(String id){
       int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas WHERE id_com = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar cheques");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][5];
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Cuotas WHERE id_com = ? Order by num_cuo");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnum = res.getString("num_cuo");
                String estfol = res.getString("fol_cuo");
                String estfec = res.getString("fec_cuo");
                String estmon = res.getString("mon_cuo");
                String estest = res.getString("est_cuo");
                data[i] = new String[]{estnum, estfol, estfec, estmon, estest};
                i++;
            }
        }catch(SQLException e){
            System.out.println("Error al obtener cheques por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
   
   public String[] obtenerCuotaPorId(String id){
        String[] data = new String[5];
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Cuotas WHERE id_cuo = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
                String estnum = res.getString("num_cuo");
                String estfec = res.getString("fec_cuo");
                String estmon = res.getString("mon_cuo");
                String estest = res.getString("est_cuo");
                data = new String[]{estnum, estfec, estmon, estest};
        }catch(SQLException e){
            System.out.println("Error al obtener cuota por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
   
   public Object[][] obtenerCuotasPorId(String id){
       int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas WHERE id_com = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar cuotas");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][5];
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Cuotas WHERE id_com = ? Order by num_cuo");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnum = res.getString("num_cuo");
                String estfec = res.getString("fec_cuo");
                String estmon = res.getString("mon_cuo");
                String estest = res.getString("est_cuo");
                data[i] = new String[]{estnum, estfec, estmon, estest};
                i++;
            }
        }catch(SQLException e){
            System.out.println("Error al obtener cuotas por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
   
   public Object[][] obtenerProductosPorId(String id){
       int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Productos WHERE id_com = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar productos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][5];
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Productos WHERE id_com = ? Order by cod_prod");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estcod = res.getString("cod_prod");
                String estdet = res.getString("det_prod");
                String estcant = res.getString("cant_prod");
                String estprec = res.getString("prec_prod");
                String estiva = res.getString("iva_prod");
                data[i] = new String[]{estcod, estdet, estcant, estprec, estiva};
                i++;
            }
        }catch(SQLException e){
            System.out.println("Error al obtener prod por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
   
    public Object[][] obtenerImpuestosPorId(String id){
       int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Impuestos WHERE id_com = ?");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar impuestos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][2];
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM Impuestos WHERE id_com = ? Order by id_imp");
            pstm.setString(1, id);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnom = res.getString("nom_imp");
                String estmon = res.getString("mon_imp");
                data[i] = new String[]{estnom, estmon};
                i++;
            }
        }catch(SQLException e){
            System.out.println("Error al obtener imp por id");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
   
   public Object[][] listarAgendaDePagos() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas INNER JOIN Compras_fac"
                    + " ON Cuotas.id_com = Compras_fac.id_com WHERE MONTH(fec_cuo) = MONTH(curdate()) and  not form_com = "
                    + "'Otros pagos'");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar agenda de pagos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][7];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT med_com, Cuotas.id_com, Compras_fac.rut_pro, dig_pro,"
                    + " raz_pro, coalesce(fol_cuo, '') as fol_cuo, num_cuo, fec_cuo, est_cuo, id_cuo, mon_cuo, fol_com, fol_int_com FROM Cuotas INNER JOIN Compras_fac ON Cuotas.id_com"
                    + "= Compras_fac.id_com INNER JOIN Proveedores ON Compras_fac.rut_pro = Proveedores.rut_pro"
                    + " WHERE MONTH(fec_cuo) = MONTH(curdate()) and not form_com = 'Otros pagos' ORDER BY fec_cuo");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estmed = res.getString("med_com");
                String estrut = res.getString("Compras_fac.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
//                String esttip = res.getString("tipo_com");
                String estfol = res.getString("fol_cuo");
                String estfec = res.getString("fec_cuo");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estnum = res.getString("num_cuo");
                String estest = res.getString("est_cuo");
                String estid = res.getString("id_cuo");
                String estfac = res.getString("Cuotas.id_com");
                String estmon = res.getString("mon_cuo");
                String estfolcom = res.getString("fol_com");
                String estfolintcom = res.getString("fol_int_com");
                //String estid = res.getString("Cuotas.id_com");
                data[i] = new String[]{estmed, estrut + "-" + estdig, estraz, estfol, estnum, estmon, estfec, estest, estid, estfac, estfolcom, estfolintcom};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(ParseException e){
            
        }
        return data;
    }
   
   public Object[][] listarAgendaDeOtrosPagos() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas INNER JOIN Compras_fac"
                    + " ON Cuotas.id_com = Compras_fac.id_com WHERE MONTH(fec_cuo) = MONTH(curdate()) and form_com = "
                    + "'Otros pagos'");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar agenda de otros pagos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][7];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT med_com, Cuotas.id_com, Compras_fac.rut_pro, dig_pro,"
                    + " raz_pro, coalesce(fol_cuo,'') as fol_cuo, num_cuo, fec_cuo, est_cuo, id_cuo, "
                    + "coalesce(asun_com,'') as asun_com, coalesce(obs_com,'') as obs_com, mon_cuo, fol_com, fol_int_com FROM Cuotas "
                    + "INNER JOIN Compras_fac ON Cuotas.id_com"
                    + "= Compras_fac.id_com INNER JOIN Proveedores ON Compras_fac.rut_pro = Proveedores.rut_pro"
                    + " WHERE MONTH(fec_cuo) = MONTH(curdate()) and form_com = 'Otros pagos' ORDER BY fec_cuo");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estmed = res.getString("med_com");
                String estasu = res.getString("asun_com");
                String estrut = res.getString("Compras_fac.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
//                String esttip = res.getString("tipo_com");
                String estfol = res.getString("fol_cuo");
                String estobs = res.getString("obs_com");
                String estfec = res.getString("fec_cuo");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estnum = res.getString("num_cuo");
                String estmon = res.getString("mon_cuo");
                String estest = res.getString("est_cuo");
                String estid = res.getString("id_cuo");
                String estfac = res.getString("id_com");
                String estfolcom = res.getString("fol_com");
                String estfolintcom = res.getString("fol_int_com");
                //String estid = res.getString("Cuotas.id_com");
                data[i] = new String[]{estmed, estasu, estrut + "-" + estdig, estraz, estfol, estobs, estnum, estmon,
                    estfec, estest, estid, estfac, estfolcom, estfolintcom};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(ParseException e){
            
        }
        return data;
    }
   
   public String cambiarEstadoPago(String estado, String id, String fac){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Cuotas set est_cuo=? WHERE id_cuo = ?");
            pstm.setString(1, estado);
            pstm.setString(2, id);
            pstm.executeUpdate();
            pstm.close();
            CallableStatement cstmt = conn.prepareCall("{call set_estado_compra(?)}");
            cstmt.setString(1, fac);
            cstmt.execute();
        }catch(SQLException e){
            System.out.println("Error al cambiar estado");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
   
   public Object[][] listarGlobalNoPagados() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas INNER JOIN Compras_fac"
                    + " ON Cuotas.id_com = Compras_fac.id_com WHERE MONTH(fec_cuo) = MONTH(curdate()) and  not form_com = "
                    + "'Otros pagos' and not est_cuo = 'Pagado'");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar agenda de pagos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][7];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT med_com, Cuotas.id_com, Compras_fac.rut_pro, dig_pro,"
                    + " raz_pro, coalesce(fol_cuo, '') as fol_cuo, num_cuo, fec_cuo, est_cuo, id_cuo FROM Cuotas INNER JOIN Compras_fac ON Cuotas.id_com"
                    + "= Compras_fac.id_com INNER JOIN Proveedores ON Compras_fac.rut_pro = Proveedores.rut_pro"
                    + " WHERE MONTH(fec_cuo) = MONTH(curdate()) and not form_com = 'Otros pagos' and not est_cuo = 'Pagado' ORDER BY fec_cuo");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estmed = res.getString("med_com");
                String estrut = res.getString("Compras_fac.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
//                String esttip = res.getString("tipo_com");
                String estfol = res.getString("fol_cuo");
                String estfec = res.getString("fec_cuo");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estnum = res.getString("num_cuo");
                String estest = res.getString("est_cuo");
                String estid = res.getString("id_cuo");
                String estfac = res.getString("id_com");
                //String estid = res.getString("Cuotas.id_com");
                data[i] = new String[]{estmed, estrut + "-" + estdig, estraz, estfol, estnum, estfec, estest, estid, estfac};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(ParseException e){
            
        }
        return data;
    }
   
   public Object[][] listarGlobalPagados() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas INNER JOIN Compras_fac"
                    + " ON Cuotas.id_com = Compras_fac.id_com WHERE MONTH(fec_cuo) = MONTH(curdate()) and  not form_com = "
                    + "'Otros pagos' and est_cuo = 'Pagado'");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar agenda de pagos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][7];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT med_com, Cuotas.id_com, Compras_fac.rut_pro, dig_pro,"
                    + " raz_pro, coalesce(fol_cuo, '') as fol_cuo, num_cuo, fec_cuo, est_cuo, id_cuo FROM Cuotas INNER JOIN Compras_fac ON Cuotas.id_com"
                    + "= Compras_fac.id_com INNER JOIN Proveedores ON Compras_fac.rut_pro = Proveedores.rut_pro"
                    + " WHERE MONTH(fec_cuo) = MONTH(curdate()) and not form_com = 'Otros pagos' and est_cuo = 'Pagado' ORDER BY fec_cuo");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estmed = res.getString("med_com");
                String estrut = res.getString("Compras_fac.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
//                String esttip = res.getString("tipo_com");
                String estfol = res.getString("fol_cuo");
                String estfec = res.getString("fec_cuo");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estnum = res.getString("num_cuo");
                String estest = res.getString("est_cuo");
                String estid = res.getString("id_cuo");
                String estfac = res.getString("id_com");
                //String estid = res.getString("Cuotas.id_com");
                data[i] = new String[]{estmed, estrut + "-" + estdig, estraz, estfol, estnum, estfec, estest, estid, estfac};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(ParseException e){
            
        }
        return data;
    }
   
   public Object[][] listarGlobalOtrosNoPagados() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas INNER JOIN Compras_fac"
                    + " ON Cuotas.id_com = Compras_fac.id_com WHERE MONTH(fec_cuo) = MONTH(curdate()) and form_com = "
                    + "'Otros pagos' and not est_cuo = 'Pagado'");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar agenda de pagos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][7];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT med_com, Cuotas.id_com, Compras_fac.rut_pro, dig_pro,"
                    + " raz_pro, coalesce(fol_cuo, '') as fol_cuo, num_cuo, fec_cuo, est_cuo, id_cuo FROM Cuotas INNER JOIN Compras_fac ON Cuotas.id_com"
                    + "= Compras_fac.id_com INNER JOIN Proveedores ON Compras_fac.rut_pro = Proveedores.rut_pro"
                    + " WHERE MONTH(fec_cuo) = MONTH(curdate()) and form_com = 'Otros pagos' and not est_cuo = 'Pagado' ORDER BY fec_cuo");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estmed = res.getString("med_com");
                String estrut = res.getString("Compras_fac.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
//                String esttip = res.getString("tipo_com");
                String estfol = res.getString("fol_cuo");
                String estfec = res.getString("fec_cuo");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estnum = res.getString("num_cuo");
                String estest = res.getString("est_cuo");
                String estid = res.getString("id_cuo");
                String estfac = res.getString("id_com");
                //String estid = res.getString("Cuotas.id_com");
                data[i] = new String[]{estmed, estrut + "-" + estdig, estraz, estfol, estnum, estfec, estest, estid, estfac};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(ParseException e){
            
        }
        return data;
    }
   
   public Object[][] listarGlobalOtrosPagados() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Cuotas INNER JOIN Compras_fac"
                    + " ON Cuotas.id_com = Compras_fac.id_com WHERE MONTH(fec_cuo) = MONTH(curdate()) and form_com = "
                    + "'Otros pagos' and est_cuo = 'Pagado'");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar agenda de pagos");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][7];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT med_com, Cuotas.id_com, Compras_fac.rut_pro, dig_pro,"
                    + " raz_pro, coalesce(fol_cuo, '') as fol_cuo, num_cuo, fec_cuo, est_cuo, id_cuo FROM Cuotas INNER JOIN Compras_fac ON Cuotas.id_com"
                    + "= Compras_fac.id_com INNER JOIN Proveedores ON Compras_fac.rut_pro = Proveedores.rut_pro"
                    + " WHERE MONTH(fec_cuo) = MONTH(curdate()) and form_com = 'Otros pagos' and est_cuo = 'Pagado' ORDER BY fec_cuo");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estmed = res.getString("med_com");
                String estrut = res.getString("Compras_fac.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
//                String esttip = res.getString("tipo_com");
                String estfol = res.getString("fol_cuo");
                String estfec = res.getString("fec_cuo");
                java.util.Date fecha = formatDate.parse(estfec);
                estfec = newFormat.format(fecha);
                String estnum = res.getString("num_cuo");
                String estest = res.getString("est_cuo");
                String estid = res.getString("id_cuo");
                String estfac = res.getString("id_com");
                //String estid = res.getString("Cuotas.id_com");
                data[i] = new String[]{estmed, estrut + "-" + estdig, estraz, estfol, estnum, estfec, estest, estid, estfac};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }catch(ParseException e){
            
        }
        return data;
    }
   
   public int obtenerCantFacturasMes(String mes){
       int registros = 0;
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Compras_fac where "
                    + " MONTH(fec_in_com) = ?");
            pstm.setString(1, mes);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al contar compras");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       return registros;
   }
   
   public String[] obtenerResumenMes(String mes){
       int registros = 0;
       String[] datos = new String[4];
       try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Compras_fac where "
                    + " MONTH(fec_in_com) = ?");
            pstm.setString(1, mes);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();
            datos[0] = String.valueOf(registros);
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(neto_com), 0) neto FROM Compras_fac where MONTH(fec_in_com)"
                    + " = ?");
            pstm.setString(1, mes);
            res = pstm.executeQuery();
            res.next();
            datos[1] = res.getString("neto");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(iva_com), 0) iva FROM Compras_fac where MONTH(fec_in_com)"
                    + " = ?");
            pstm.setString(1, mes);
            res = pstm.executeQuery();
            res.next();
            datos[2] = res.getString("iva");
            res.close();
            pstm.close();
            
            pstm = conn.prepareStatement("SELECT coalesce(sum(tot_com), 0) tot FROM Compras_fac where MONTH(fec_in_com)"
                    + " = ?");
            pstm.setString(1, mes);
            res = pstm.executeQuery();
            res.next();
            datos[3] = res.getString("tot");
            res.close();
            pstm.close();
       }catch(SQLException e){
            System.out.println("Error al contar compras");
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
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Compras_fac where "
                    + " MONTH(fec_in_com) = ?");
            pstm.setString(1, mes);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
            pstm.close();            
       }catch(SQLException e){
            System.out.println("Error al obtener compras mes");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
       
       String[][] datos = new String[registros][8];
       
       try{
           PreparedStatement pstm = conn.prepareStatement("SELECT fol_com, fol_int_com, fec_in_com, "
                   + "proveedores.rut_pro, dig_pro, raz_pro, neto_com, iva_com, tot_com FROM compras_fac "
                   + "INNER JOIN proveedores on proveedores.rut_pro = compras_fac.rut_pro WHERE "
                   + "MONTH(fec_in_com) = ?");
           pstm.setString(1, mes);
           ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estfol = res.getString("fol_com");
                String estfolin = res.getString("fol_int_com");
                String estfec = res.getString("fec_in_com");
                String estrut = res.getString("proveedores.rut_pro");
                String estdig = res.getString("dig_pro");
                String estraz = res.getString("raz_pro");
                String estnet = res.getString("neto_com");
                String estiva = res.getString("iva_com");
                String esttot = res.getString("tot_com");
                datos[i] = new String[]{estfol, estfolin, estfec, estrut + "-" + estdig, estraz, estnet, 
                    estiva, esttot};
                i++;
            }
            res.close();
       }catch(Exception e){
           
       }
       return datos;
   }
}
