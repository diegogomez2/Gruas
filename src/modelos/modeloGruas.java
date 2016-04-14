/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Diego
 */
public class modeloGruas {
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
    
    public Object[][] listarGruas(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM gruas");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][5];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT pat_gru, des_gru, mar_gru, mod_gru, "
                    + "ton_gru FROM gruas");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estpat = res.getString("pat_gru");
                String estdes = res.getString("des_gru");
                String estmar = res.getString("mar_gru");
                String estmodel = res.getString("mod_gru");
                String estton = res.getString("ton_gru");
                data[i][0] = estpat;
                data[i][1] = estdes;
                data[i][2] = estmar;
                data[i][3] = estmodel;
                data[i][4] = estton;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarGrua(String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into gruas (pat_gru, des_gru, mod_gru,"
                    + "pes_gru, tneum_gru, tneum2_gru, ncha_gru, tcom_gru, obs_gru, ton_gru, kmh_gru,"
                    + "fin_gru, mar_gru, mas_gru, altmas_gru, anc_gru, lar_gru, larU_gru, altlev_gru,"
                    + "ndel_gru, ntra_gru, nmo_gru, nse_gru, frt_gru, fum_gru, kmhum_gru, hpm_gru, fba_gru)"
                    + " values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setString(1, data[0]); //patente
            pstm.setString(2, data[1]); //descripcion
            pstm.setString(3, data[2]); //modelo
            pstm.setString(4, data[3]); //Peso grua
            pstm.setString(5, data[4]); //tipo neum
            pstm.setString(6, data[5]); //tipo neum2
            pstm.setString(7, data[6]); //nchasis
            pstm.setString(8, data[7]); //tipo combs
            pstm.setString(9, data[8]); //obs
            pstm.setFloat(10, Float.parseFloat(data[9])); //ton
            pstm.setString(11, data[10]); //kmh
            pstm.setDate(12, toSqlDate(data[11])); //fechain
            pstm.setString(13, data[12]);   //marca
            pstm.setString(14, data[13]);   //mastil
            pstm.setInt(15, Integer.parseInt(data[14]));   //altmastil
            pstm.setInt(16, Integer.parseInt(data[15]));   //ancho
            pstm.setInt(17, Integer.parseInt(data[16]));   //largo
            pstm.setInt(18, Integer.parseInt(data[17])); //largo unas
            pstm.setInt(19, Integer.parseInt(data[18]));    //alt levante
            pstm.setString(20, data[19]); //neum del
            pstm.setString(21, data[20]);   //neum tras
            pstm.setString(22, data[21]);   //nmotor
            pstm.setString(23, data[22]);   //nserie
            pstm.setDate(24, toSqlDate(data[23])); //fecharev
            pstm.setDate(25,toSqlDate(data[24])); //fechaum
            pstm.setInt(26, Integer.parseInt(data[25])); //kmhum
            pstm.setInt(27, Integer.parseInt(data[26])); //horaspm
            pstm.setDate(28, toSqlDate(data[27])); //fechabaja
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }catch(Exception e){
            System.out.println(e);
        }
        return "correcto";
    }

    public String eliminarGrua(String data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM gruas WHERE pat_gru = ?");
            pstm.setString(1, data);
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

    public String[] obtenerGruaPorPatente(String patente) {
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM gruas WHERE pat_gru = ?");
            pstm.setString(1, patente);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estpat = res.getString("pat_gru");
            String estdesc = res.getString("des_gru");
            String estmod = res.getString("mod_gru");
            String estpeso = res.getString("pes_gru");
            String esttiponeum = res.getString("tneum_gru");
            String esttiponeum2 = res.getString("tneum2_gru");
            String estnchasis = res.getString("ncha_gru");
            String esttipocombs = res.getString("tcom_gru");
            String estobs = res.getString("obs_gru");
            String estton = res.getString("ton_gru");
            String estkmh = res.getString("kmh_gru");
            String estfechain = res.getString("fin_gru");
            String estmarca = res.getString("mar_gru");
            String estmastil = res.getString("mas_gru");
            String estaltmastil = res.getString("altmas_gru");
            String estancho = res.getString("anc_gru");
            String estlargo = res.getString("lar_gru"); 
            String estlargounas = res.getString("larU_gru");
            String estaltlevante = res.getString("altlev_gru");
            String estneumdel = res.getString("ndel_gru");
            String estneumtras = res.getString("ntra_gru");
            String estnmotor = res.getString("nmo_gru");
            String estnserie = res.getString("nse_gru");
            String estfechart = res.getString("frt_gru");
            String estfechaum = res.getString("fum_gru");
            String estfechakmhum = res.getString("kmhum_gru");
            String esthpm = res.getString("hpm_gru");
            String estfechabaja = res.getString("fba_gru");
            data = new String[]{estpat, estdesc , estmod, estpeso, esttiponeum, esttiponeum2, estnchasis,
                esttipocombs, estobs, estton, estkmh, estfechain, estmarca, estmastil, estaltmastil, estancho,
                estlargo, estlargounas, estaltlevante, estneumdel, estneumtras, estnmotor, estnserie, estfechart,
            estfechaum, estfechakmhum, esthpm, estfechabaja};
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;   
    }

    public String modificarGrua(String[] data, String patente) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update gruas set pat_gru=?, de_grus=?, mod_gru=?,"
                    + "pes_gru=?, tneum_gru=?, tneum2_gru=?, ncha_gru=?, tcom_gru=?, obs_gru=?, ton_gru=?,"
                    + "kmh_gru=?, fin_gru=?, mar_gru=?, mas_gru=?, altmas_gru=?, anc_gru=?, lar_gru=?, "
                    + "larU_gru=?, altlev_gru=?, ndel_gru=?, ntra_gru=?, nmo_gru=?, nse_gru=?, frt_gru=?, "
                    + "fum_gru=?, kmhum_gru=?, hpm_gru=?, fba_gru=? WHERE pat_gru=?");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setInt(4, Integer.parseInt(data[3]));
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setFloat(10, Float.parseFloat(data[9]));
            pstm.setString(11, data[10]);
            pstm.setDate(12, toSqlDate(data[11]));
            pstm.setString(13, data[12]);
            pstm.setString(14, data[13]);
            pstm.setInt(15, Integer.parseInt(data[14]));
            pstm.setInt(16, Integer.parseInt(data[15]));
            pstm.setInt(17, Integer.parseInt(data[16]));
            pstm.setInt(18, Integer.parseInt(data[17]));
            pstm.setInt(19, Integer.parseInt(data[18]));
            pstm.setInt(20, Integer.parseInt(data[19]));
            pstm.setInt(21, Integer.parseInt(data[20]));
            pstm.setString(22, data[21]);
            pstm.setString(23, data[22]);
            pstm.setDate(24, toSqlDate(data[23])); //fecharev
            pstm.setDate(25,toSqlDate(data[24])); //fechaum
            pstm.setInt(26, Integer.parseInt(data[25])); //kmhum
            pstm.setInt(27, Integer.parseInt(data[26])); //horaspm
            pstm.setDate(28, toSqlDate(data[27]));
            pstm.setString(29, patente);
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

    public Object[][] obtenerDescGruas() {
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Gruas");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][1];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT des_gru FROM Gruas");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estdes = res.getString("des_gru");
                data[i][0] = estdes;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;  
    }

    public String obtenerGruaPorDesc(String textoGrua) {
        String data = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM gruas WHERE des_gru = ?");
            pstm.setString(1, textoGrua);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estpat = res.getString("pat_gru");
            data = estpat;
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
}
