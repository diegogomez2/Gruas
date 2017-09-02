package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Diego
 */
public class modeloClientes {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    
    public Object[][] listarClientes(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Clientes");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar clientes");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][5];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_cli, dig_cli, raz_cli, dir_cli,"
                    + " tel_cli, con_cli FROM Clientes ORDER BY rut_cli");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estdir = res.getString("dir_cli");
                String esttel = res.getString("tel_cli");
                String estcon = res.getString("con_cli");
                data[i] = new String[]{estrut + "-" + estdig, estraz, esttel, estdir, estcon};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarCliente(String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("insert into clientes (rut_cli, dig_cli,"
                    + "con_cli, raz_cli, gir_cli, cor_cli, tel_cli, cel_cli, dir_cli, reg_cli, ciu_cli,"
                    + "com_cli, obs_cli) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setString(13, data[12]);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar cliente");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String eliminarCliente(String data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM clientes WHERE rut_cli = ?");
            pstm.setString(1, data);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar cliente");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }
    
    public String[] obtenerClientePorRut(String rut){
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT * FROM clientes WHERE rut_cli = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_cli");
            String estdig = res.getString("dig_cli");
            String estcon = res.getString("con_cli");
            String estraz = res.getString("raz_cli");
            String estgir = res.getString("gir_cli");
            String estcor = res.getString("cor_cli");
            String esttel = res.getString("tel_cli");
            String estcel = res.getString("cel_cli");
            String estdir = res.getString("dir_cli");
            String estreg = res.getString("reg_cli");
            String estciu = res.getString("ciu_cli");
            String estcom = res.getString("com_cli");
            String estobs = res.getString("obs_cli");
            data = new String[]{estrut + "-" + estdig , estcon, estraz, estgir, estcor,
                esttel, estcel, estdir, estreg, estciu, estcom, estobs};
        }catch(SQLException e){
            System.out.println("Error al obtener cliente por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String modificarCliente(String[] data, int rut){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update clientes set rut_cli=?, dig_cli=?, "
                    + "con_cli = ?, raz_cli=?, gir_cli=?, cor_cli=?, tel_cli=?, cel_cli=?, dir_cli=?, reg_cli=?,"
                    + "ciu_cli=?, com_cli=?, obs_cli=? WHERE rut_cli=?");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setString(6, data[5]);
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setString(10, data[9]);
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setString(13, data[12]);
            pstm.setInt(14, rut);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al modificar cliente");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }

    public Object[] obtenerRazonClientes() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Clientes");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al obtener razon cliente");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT raz_cli FROM Clientes ORDER BY raz_cli");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrazon = res.getString("raz_cli");
                data[i] = estrazon;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
        }
        return data;  
    }

    /* Obtiene el rut del cliente según su razón */
    public String obtenerClientePorRazon(String razon) {
        String data = null;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_cli FROM clientes WHERE raz_cli = ?");
            pstm.setString(1, razon);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_cli");
            data = estrut;
        }catch(SQLException e){
            System.out.println("Error al obtener cliente por razon");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String obtenerObsPorRazon(String razon){
        String data = "";
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT obs_cli FROM clientes WHERE raz_cli = ?");
            pstm.setString(1, razon);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estobs = res.getString("obs_cli");
            data = estobs;
        }catch(SQLException e){
            System.out.println("Error al obtener obs por razon");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public Object[][] listarReporteClientes(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Clientes");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar reporte clientes");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][12];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_cli, dig_cli, raz_cli, gir_cli, tel_cli, cel_cli, cor_cli, dir_cli, reg_cli, ciu_cli, com_cli, obs_cli, "
                    + "con_cli FROM Clientes ORDER BY rut_cli");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_cli");
                String estdig = res.getString("dig_cli");
                String estraz = res.getString("raz_cli");
                String estgir = res.getString("gir_cli");
                String esttel = res.getString("tel_cli");
                String estcel = res.getString("cel_cli");
                String estcor = res.getString("cor_cli");
                String estdir = res.getString("dir_cli");
                String estreg = res.getString("reg_cli");
                String estciu = res.getString("ciu_cli");
                String estcom = res.getString("com_cli");
                String estobs = res.getString("obs_cli");
                String estcon = res.getString("con_cli");
                data[i] = new String[]{estrut + "-" + estdig, estraz, estgir, esttel, estcel, estcor, estdir, estreg, estciu, estcom, estobs, estcon};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println(e);
            System.out.println("error listar reporte clientes");
        }
        return data;
    }
   
}
