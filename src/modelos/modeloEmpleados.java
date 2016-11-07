/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author Diego
 */
public class modeloEmpleados {
    Connector conector = Connector.getInstance();
    String login = conector.getLogin();
    String password = conector.getPassword();
    String url = conector.getUrl();
    Connection conn = null;
    DateFormat formatDate = new SimpleDateFormat("yyyy-MM-dd");
    
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

    public Object[][] listarEmpleados(){
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Empleados");
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar empleados");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[][] data = new String[registros][4];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, dig_emp, nom_emp, apP_emp,"
                    + "car_emp, tel_emp FROM empleados ORDER BY rut_emp");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_emp");
                String estdig = res.getString("dig_emp");
                String estnom = res.getString("nom_emp");
                String estapP = res.getString("apP_emp");
                String estcar = res.getString("car_emp");
                String esttel = res.getString("tel_emp");
                data[i] = new String[]{estrut + "-" + estdig, estnom + " " + estapP, estcar, esttel};
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al listar empleados");
            System.out.println(e);
        }
        return data;
    }

    public Object[] obtenerNombresEmpleados() {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Empleados");
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
            PreparedStatement pstm = conn.prepareStatement("SELECT nom_emp, apP_emp, apM_emp FROM empleados ORDER BY apP_emp");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnombres = res.getString("nom_emp");
                String estapPaterno = res.getString("apP_emp");
                String estapMaterno = res.getString("apM_emp");
                data[i] = estnombres + " " + estapPaterno + " " + estapMaterno;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al obtener nombres empleados");
            System.out.println(e);
        }
        return data;
    }

    public String ingresarEmpleados(String[] data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Empleados (rut_emp, dig_emp, nom_emp,"
                    + "apP_emp, apM_emp, fnac_emp, tel_emp, cor_emp, car_emp, sueldo_emp, afp_emp, sal_emp,"
                    + "fin_emp, dir_emp, reg_emp, com_emp, isa_emp, val_isa_emp, colacion_emp, transporte_emp, bonoad_emp, valbonoad_emp, "
                    + "caja_emp, af_emp) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setDate(6, toSqlDate(data[5]));
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setInt(10, Integer.parseInt(data[9]));
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setDate(13, toSqlDate(data[12]));
            pstm.setString(14, data[13]);
            pstm.setString(15, data[14]);
            pstm.setString(16, data[15]);
            pstm.setString(17, data[16]);
            pstm.setString(18, data[17]);
            pstm.setString(19, data[18]);
            pstm.setString(20, data[19]);
            pstm.setString(21, data[20]);
            pstm.setString(22, data[21]);
            pstm.setString(23, data[22]);
            pstm.setString(24, data[23]);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al ingresar empleado");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }catch(NumberFormatException e){
            System.out.println("El sueldo debe ser un valor numérico");
            return "incorrecto";
        }
        return "correcto";
    }

    public String[] obtenerEmpleadoPorRut(String rut) {
        String data[] = new String[]{};
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT *, coalesce(fnac_emp,'') as fnac, "
                    + " coalesce(isa_emp, '') isa, coalesce(val_isa_emp, 0) val, coalesce(bonoad_emp, '') bonoad,"
                    + " coalesce(valbonoad_emp, 0) valbono, coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af "
                    + " FROM empleados WHERE rut_emp = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_emp");
            String estdig = res.getString("dig_emp");
            String estnom = res.getString("nom_emp");
            String estapP = res.getString("apP_emp");
            String estapM = res.getString("apM_emp");
            String estfnac = res.getString("fnac");
            String esttel = res.getString("tel_emp");
            String estcor = res.getString("cor_emp");
            String estcar = res.getString("car_emp");
            String estsueldo = res.getString("sueldo_emp");
            String estafp = res.getString("afp_emp");
            String estsal = res.getString("sal_emp");
            String estfin = res.getString("fin_emp");
            String estdir = res.getString("dir_emp");
            String estreg = res.getString("reg_emp");
            String estcom = res.getString("com_emp");
            String estcol = res.getString("colacion_emp");
            String esttra = res.getString("transporte_emp");
            String estisa = res.getString("isa");
            String estval = res.getString("val");
            String estbonoad = res.getString("bonoad");
            String estvalbon = res.getString("valbono");
            String estcaja = res.getString("caja");
            String estaf = res.getString("af");
            data = new String[]{estrut + "-" + estdig , estnom, estapP, estapM, estfnac
                    , esttel, estcor, estcar, estsueldo, estafp, estsal, estfin, estdir, estreg, estcom
                    , estcol, esttra, estisa, estval, estbonoad, estvalbon, estcaja, estaf};
        }catch(SQLException e){
            System.out.println("Error al obtener empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }

    public String eliminarEmpleado(String data) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("DELETE FROM empleados WHERE rut_emp = ?");
            pstm.setString(1, data);
            pstm.execute();
            pstm.close();
            return "correcto";
        }catch(SQLException e){
            System.out.println("Error al eliminar empleado");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
    }

    public String modificarEmpleado(String[] data, int rut) {
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("update empleados set rut_emp=?, dig_emp=?,"
                    + "nom_emp=?, apP_emp=?, apM_emp=?, fnac_emp=?, tel_emp=?, cor_emp=?, car_emp=?,"
                    + "sueldo_emp=?, afp_emp=?, sal_emp=?, fin_emp=?, dir_emp=?, reg_emp=?, com_emp=?"
                    + ", isa_emp=?, val_isa_emp=?, colacion_emp=?, transporte_emp=?, bonoad_emp=?, "
                    + "valbonoad_emp=?, caja_emp=?, af_emp=? WHERE rut_emp=?");
            pstm.setInt(1, Integer.parseInt(data[0]));
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[4]);
            pstm.setDate(6, toSqlDate(data[5]));
            pstm.setString(7, data[6]);
            pstm.setString(8, data[7]);
            pstm.setString(9, data[8]);
            pstm.setInt(10, Integer.parseInt(data[9]));
            pstm.setString(11, data[10]);
            pstm.setString(12, data[11]);
            pstm.setDate(13, toSqlDate(data[12]));
            pstm.setString(14, data[13]);
            pstm.setString(15, data[14]);
            pstm.setString(16, data[15]);
            pstm.setString(17, data[16]);
            pstm.setString(18, data[17]);
            pstm.setString(19, data[18]);
            pstm.setString(20, data[19]);
            pstm.setString(21, data[20]);
            pstm.setString(22, data[21]);
            pstm.setString(23, data[22]);
            pstm.setString(24, data[23]);
            pstm.setInt(25, rut);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al modificar empleado");
            System.out.println(e);
            return "incorrecto";
        }catch(ClassNotFoundException e){
            System.out.println(e);
            return "incorrecto";
        }
        return "correcto";
    }

    public String obtenerEmpleadoPorNombre(String textoOperador) {
        String data = null;
        String[] nom = (textoOperador.trim()).split(" +");
        int size = nom.length;
        if(size < 3) return null;
        String apM = nom[size-1];
        String apP = nom[size-2];
        String nombres = "";
        for(int i = 0; i < size - 2; i++){
            nombres += nom[i] + " ";
        }
        nombres = nombres.trim();
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp FROM empleados WHERE apP_emp = ? "
                    + "AND apM_emp = ? AND nom_emp = ?");
            pstm.setString(1, apP);
            pstm.setString(2, apM);
            pstm.setString(3, nombres);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_emp");
            data = estrut;
        }catch(SQLException e){
            System.out.println("Error al obtener empleado por nombre");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public Object[] obtenerNomEmpDisp(String fhsal, String fhreg) {
        //String fechSal, String horaSal, String fechReg, String horaReg
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Empleados where "
                    + "rut_emp not in (SELECT rut_emp from jornadas where "
                    + "( subtime(?, '01:00') <= fhreg_jor and addtime(?, '01:00') >= fhsal_jor and rut_emp is not null))");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al obtener rut emp disp count");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT coalesce(nom_emp,'') as nom_emp, "
                    + "coalesce(apP_emp,'') as apP_emp, coalesce(apM_emp,'') as apM_emp "
                    + "FROM Empleados where rut_emp not in (SELECT rut_emp from jornadas where "
                    + "( subtime(?, '01:00')  <= fhreg_jor and addtime(?, '01:00') >= fhsal_jor and rut_emp is not null))"
                    + " order by nom_emp");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnom = res.getString("nom_emp");
                String estapp = res.getString("apP_emp");
                String estapm = res.getString("apM_emp");
                data[i] = estnom + " " + " " + estapp + " " + estapm;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error obtener rut emp disp");
            System.out.println(e);
        }
        return data;  
    }
    
    public int checkEmpDisp(String fhsal, String fhreg, String rut) {
        //String fechSal, String horaSal, String fechReg, String horaReg
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(*) as total from jornadas where "
                    + "( subtime(?, '01:00') <= fhreg_jor and addtime(?, '01:00') >= fhsal_jor and rut_emp = ?)");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            pstm.setString(3, rut);
            System.out.println(pstm);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al obtener rut emp disp count");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return registros;
    }
    
    public int checkEmpDispId(String fhsal, String fhreg, String rut, String id) {
        //String fechSal, String horaSal, String fechReg, String horaReg
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT count(*) as total from jornadas where "
                    + "( subtime(?, '01:00') <= fhreg_jor and addtime(?, '01:00') >= fhsal_jor and rut_emp = ?"
                    + "and id_jor <> ?)");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            pstm.setString(3, rut);
            pstm.setString(4, id);
            System.out.println(pstm);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al obtener rut emp disp count");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return registros;
    }
    
    public String[] obtenerRemuneracionPorRut(String rut) {
        String[] data = new String[5];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, dig_emp, nom_emp, apP_emp, "
                    + "sueldo_emp, afp_emp, sal_emp, TIMESTAMPDIFf(YEAR, fin_emp, CURDATE()) ant, colacion_emp col, transporte_emp trans, "
                    + " horcol_emp, coalesce(hormes_emp,0) hormes, coalesce(valbonoad_emp, 0) valbono "
                    + ", coalesce(horexcalc_emp, 0) horex, coalesce(horex_emp, 0) canthor, coalesce(bonohorexcalc_emp, 0) bonohorex, "
                    + "coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af, coalesce(anticipo_emp, 0) antic, "
                    + "coalesce(adel_emp, 0) adel, coalesce(pres_emp, 0) pres, coalesce(cuo_emp, 0) cuo FROM empleados WHERE rut_emp = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_emp");
            String estfrut = "";
            int cont = 0;
            for(int i = estrut.length() - 1; i >= 0; i--){
                estfrut = estrut.substring(i , i+1) + estfrut;
                cont ++;
                if(cont == 3 && i != 0){
                    estfrut = "." + estfrut;
                    cont = 0;
                }
            }
            String estdig = res.getString("dig_emp");
            String estnom = res.getString("nom_emp");
            String estapP = res.getString("apP_emp");
            String estsueldo = res.getString("sueldo_emp");
            String estafp = res.getString("afp_emp");
            String estsal = res.getString("sal_emp");
            String estant = res.getString("ant");
            String estcol = res.getString("col");
            String esttrans = res.getString("trans");
            String esthorcol = res.getString("horcol_emp");
            String esthormes = res.getString("hormes");
            String estvalbono = res.getString("valbono");
            String esthorex = res.getString("horex");
            String estcanthor = res.getString("canthor");
            String estbonohorex = res.getString("bonohorex");
            String estcaja = res.getString("caja");
            String estaf = res.getString("af");
            String estantic = res.getString("antic");
            String estadel = res.getString("adel");
            String estpres = res.getString("pres");
            String estcuo = res.getString("cuo");
            data = new String[]{estfrut + "-" + estdig , estnom + " " + estapP, estsueldo, estafp, estsal, 
                estant, estcol, esttrans, esthorcol, esthormes, estvalbono, esthorex, estcanthor, estbonohorex, 
                estcaja, estaf, estantic, estadel, estpres, estcuo};
        }catch(SQLException e){
            System.out.println("Error al obtener rem empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerPresAdelantoEmpleadoPorRut(String rut) {
        String[] data = new String[5];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT coalesce(anticipo_emp, 0) ant, coalesce(adel_emp, 0) "
                    + "adel, coalesce(pres_emp, 0) pres, coalesce(cuo_emp, 0) cuo, coalesce(cuores_emp, 0) cuores"
                    + " FROM empleados WHERE rut_emp = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estant = res.getString("ant");
            String estadel = res.getString("adel");
            String estpres = res.getString("pres");
            String estcuo = res.getString("cuo");
            String estcuores = res.getString("cuores");
            data = new String[]{estant, estadel, estpres, estcuo, estcuores};
        }catch(SQLException e){
            System.out.println("Error al obtener pres adelanto empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public void agregarColacion(String rut, int horas){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Empleados set horcol_emp = horcol_emp + ? WHERE rut_emp = ?");
            pstm.setInt(1, horas);
            pstm.setString(2, rut);
            pstm.executeUpdate();
        }catch(SQLException e){
            System.out.println("Error al actualizar horas bono colacion");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    public void agregarHorasExtra(String rut, int horas, int horasCalc){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT horex_emp FROM Empleados Where rut_emp = ?");
            pstm.setString(1, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            int horasAct = res.getInt("horex_emp");
            pstm = conn.prepareStatement("SELECT horexcalc_emp FROM Empleados Where rut_emp = ?");
            pstm.setString(1, rut);
            res = pstm.executeQuery();
            res.next();
            int horasActCalc = res.getInt("horexcalc_emp");
            //Horas restantes para completar las 45 horas extras
            int diff = 45 - horasAct - horas;
            if(diff <= 0){
                pstm = conn.prepareStatement("UPDATE Empleados set horex_emp=? WHERE rut_emp = ?");
                pstm.setInt(1, 45);
                pstm.setString(2, rut);
                pstm = conn.prepareStatement("UPDATE Empleados set bonohorex_emp=bonohorex_emp+? WHERE rut_emp = ?");
                pstm.setInt(1, Math.abs(diff));
                pstm.setString(2, rut);
                pstm.executeUpdate();
            }else{
                pstm = conn.prepareStatement("UPDATE Empleados set horex_emp=horex_emp+? WHERE rut_emp = ?");
                pstm.setInt(1, horas);
                pstm.setInt(2, horasCalc);
                pstm.setString(3, rut);
                pstm.executeUpdate();
            }
            int diffCalc = 90 - horasActCalc - horasCalc;
            if(diffCalc <= 0){
                pstm = conn.prepareStatement("UPDATE Empleados set horexcalc_emp=? WHERE rut_emp = ?");
                pstm.setInt(1, 90);
                pstm.setString(2, rut);
                pstm = conn.prepareStatement("UPDATE Empleados set bonohorexcalc_emp=bonohorexcalc_emp? WHERE rut_emp = ?");
                pstm.setInt(1, Math.abs(diff));
                pstm.setString(2, rut);
                pstm.executeUpdate();
            }else{
                pstm = conn.prepareStatement("UPDATE Empleados set horex_emp=horex_emp+? WHERE rut_emp = ?");
                pstm.setInt(1, horas);
                pstm.setInt(2, horasCalc);
                pstm.setString(3, rut);
                pstm.executeUpdate();
            }
            pstm.close();
            res.close();
        }catch(SQLException e){
            System.out.println("Error al actualizar horas ex");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    public void actualizarHorasMes(String rut, int horas){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Empleados set hormes_emp = hormes_emp + ? WHERE rut_emp = ?");
            pstm.setInt(1, horas);
            pstm.setString(2, rut);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al actualizar horas mes empleados");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    public String ingresarPresAdelanto(String rut, String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Empleados set anticipo_emp=?, adel_emp=?, pres_emp=?,"
                    + " cuo_emp=? WHERE rut_emp=?");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, rut);
            pstm.executeUpdate();
            pstm.close();
            return "correcto";
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error al ingresar pres adelanto");
            System.out.println(e);
            return "incorrecto";
        }
    }
}
