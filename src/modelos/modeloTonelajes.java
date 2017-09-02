package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author diego
 */
public class modeloTonelajes {
    
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    
    public Object[] listarTonelajes(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Tonelajes");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT pes_ton FROM Tonelajes ORDER BY pes_ton");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estpes = res.getString("pes_ton");
                data[i] = estpes;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar tonelajes");
            System.out.println(e);
        }
        return data;
    }
    
    public Object[][] obtenerTonelajeBono300(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Tonelajes");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new Object[registros][2];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT pes_ton, val_ton FROM Tonelajes ORDER BY pes_ton");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estpes = res.getString("pes_ton");
                String estval = res.getString("val_ton");
                data[i][0] = estpes;
                data[i][1] = estval;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error listar tonelajes bono 300");
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerTonelajeBono300PorPeso(String pes){
        
        String[] data = new String[3];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT pes_ton, val_ton, id_ton FROM Tonelajes WHERE pes_ton = ?");
            pstm.setString(1, pes);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            res.next();
            String estpes = res.getString("pes_ton");
            String estval = res.getString("val_ton");
            String estid = res.getString("id_ton");
            data = new String[]{estpes, estval, estid};
            res.close();
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error obtener tonelaje bono 300");
            System.out.println(e);
        }
        return data;
    }
    
    public String ingresarTonelaje(String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Tonelajes (pes_ton, val_ton) values (?, ?)");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar tonelaje");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
    public String modificarTonelaje(String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Tonelajes SET pes_ton=?, val_ton=? WHERE id_ton = ?");
            pstm.setString(1, data[1]);
            pstm.setString(2, data[2]);
            pstm.setString(3, data[0]);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al modificar tonelaje");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }
    
}
