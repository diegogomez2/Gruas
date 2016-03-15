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
    static String url = "jdbc:mysql://localhost:3306/factgruas";
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
            PreparedStatement pstm = conn.prepareStatement("SELECT pat, des, mar, model, ton FROM gruas");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estpat = res.getString("pat");
                String estdes = res.getString("des");
                String estmar = res.getString("mar");
                String estmodel = res.getString("model");
                String estton = res.getString("ton");
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
            PreparedStatement pstm = conn.prepareStatement("insert into gruas (pat, des, model, peso,"
                    + "tiponeum, tiponeum2, nchasis, tipocombs, obs, ton, kmh, fechain, mar, mastil, altmastil, ancho, largo,"
                    + "largounas, alturalev, neumdel, neumtras, nmotor, nserie, fecharev, fechaum, kmhum, hpm, fechabaja)"
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
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM gruas WHERE pat = ?");
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

    public String[] obtenerClientePorRut(String patente) {
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM gruas WHERE pat = ?");
            pstm.setString(1, patente);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estpat = res.getString("pat");
            String estdesc = res.getString("des");
            String estmod = res.getString("model");
            String estpeso = res.getString("peso");
            String esttiponeum = res.getString("tiponeum");
            String esttiponeum2 = res.getString("tiponeum2");
            String estnchasis = res.getString("nchasis");
            String esttipocombs = res.getString("tipocombs");
            String estobs = res.getString("obs");
            String estton = res.getString("ton");
            String estkmh = res.getString("kmh");
            String estfechain = res.getString("fechain");
            String estmarca = res.getString("mar");
            String estmastil = res.getString("mastil");
            String estaltmastil = res.getString("altmastil");
            String estancho = res.getString("ancho");
            String estlargo = res.getString("largo"); 
            String estlargounas = res.getString("largounas");
            String estaltlevante = res.getString("alturalev");
            String estneumdel = res.getString("neumdel");
            String estneumtras = res.getString("neumtras");
            String estnmotor = res.getString("nmotor");
            String estnserie = res.getString("nserie");
            String estfechart = res.getString("fecharev");
            String estfechaum = res.getString("fechaum");
            String estfechakmhum = res.getString("kmhum");
            String esthpm = res.getString("hpm");
            String estfechabaja = res.getString("fechabaja");
            data = new String[]{estpat, estdesc , estmod, estpeso, esttiponeum, esttiponeum2, estnchasis,
                esttipocombs, estobs, estton, estkmh, estfechain, estmarca, estmastil, estaltmastil, estancho,
                estlargo, estlargounas, estaltlevante, estneumdel, estneumtras, estnmotor, estnserie, estfechart,
            estfechaum, estfechakmhum, esthpm, estfechabaja};
        }catch(SQLException e){
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;    }

    public String modificarGrua(String[] data, String patente) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update gruas set pat=?, des=?, model=?, peso=?,"
                    + "tiponeum=?, tiponeum2=?, nchasis=?, tipocombs=?, obs=?, ton=?, kmh=?, fechain=?, mar=?,"
                    + "mastil=?, altmastil=?, ancho=?, largo=?, largounas=?, alturalev=?, neumdel=?, neumtras=?,"
                    + "nmotor=?, nserie=?, fecharev=?, fechaum=?, kmhum=?, hpm=?, fechabaja=?"
                    + "WHERE pat=?");
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
            PreparedStatement pstm = conn.prepareStatement("SELECT des FROM Gruas");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estdes = res.getString("des");
                data[i][0] = estdes;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;  
    }
}
