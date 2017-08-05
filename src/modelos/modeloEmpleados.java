/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.CallableStatement;
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

    /* Lista a los empleados por rut, nombre, cargo y telefono */
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

    /* Lista a los empleados por nombre */
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
            int valIsa = (int)(Float.parseFloat(data[17])*1000);
            pstm.setInt(18, valIsa);
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
                    + " coalesce(valbonoad_emp, 0) valbono, coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af, "
                    + " coalesce(diastrab_emp, 0) dias FROM empleados WHERE rut_emp = ?");
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
            String estdias = res.getString("dias");
            data = new String[]{estrut + "-" + estdig , estnom, estapP, estapM, estfnac
                    , esttel, estcor, estcar, estsueldo, estafp, estsal, estfin, estdir, estreg, estcom
                    , estcol, esttra, estisa, estval, estbonoad, estvalbon, estcaja, estaf, estdias};
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
                    + "valbonoad_emp=?, caja_emp=?, af_emp=?, diastrab_emp=? WHERE rut_emp=?");
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
            int valIsa = (int)(Float.parseFloat(data[17])*1000);
            pstm.setInt(18, valIsa);
            pstm.setString(19, data[18]);
            pstm.setString(20, data[19]);
            pstm.setString(21, data[20]);
            pstm.setString(22, data[21]);
            pstm.setString(23, data[22]);
            pstm.setString(24, data[23]);
            pstm.setString(25, data[24]);
            pstm.setInt(26, rut);
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

    /* Obtiene el rut del empleado según su nombre */
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
    
    /* Obtiene lista de empleados disponibles en periodo de tiempo*/ 
    public Object[] obtenerNomEmpDisp(String fhsal, String fhreg) {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Empleados where "
//                    + "rut_emp not in (SELECT rut_emp from jornadas where "
//                    + "( ? <= fhreg_jor and ? >= fhsal_jor and rut_emp is not null and nc_ot = 0))");
            PreparedStatement pstm = conn.prepareStatement("SELECT count(*) as total FROM Empleados where rut_emp not in( "
                    + "SELECT rut_emp from jornadas where ( ? <= fhreg_jor and ? >= fhsal_jor and rut_emp is not null and nc_ot = 0) "
                    + "union all "
                    + "SELECT rut_emp from detalle_oc_emp where  fhsal_det_emp is null and fhreg_det_emp is null and rut_emp is not null "
                    + "union all "
                    + "SELECT rut_emp from detalle_oc_emp where ? <= fhreg_det_emp and ? >= fhsal_det_emp  and rut_emp is not null)");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            pstm.setString(3, fhsal);
            pstm.setString(4, fhreg);
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
                    + "FROM Empleados where rut_emp not in (SELECT rut_emp from jornadas where ( ? <= fhreg_jor and ? >= fhsal_jor and rut_emp is not null and nc_ot = 0) "
                    + "union all "
                    + "SELECT rut_emp from detalle_oc_emp where  fhsal_det_emp is null and fhreg_det_emp is null and rut_emp is not null "
                    + "union all "
                    + "SELECT rut_emp from detalle_oc_emp where ? <= fhreg_det_emp and ? >= fhsal_det_emp  and rut_emp is not null)"
                    + " order by nom_emp");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            pstm.setString(3, fhsal);
            pstm.setString(4, fhreg);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estnom = res.getString("nom_emp");
                String estapp = res.getString("apP_emp");
                String estapm = res.getString("apM_emp");
                data[i] = estnom + " " + estapp + " " + estapm;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error obtener rut emp disp");
            System.out.println(e);
        }
        return data;  
    }
    
    /* Comprueba si el empleado esta disponible en el periodo indicado*/
    public int checkEmpDisp(String fhsal, String fhreg, String rut) {
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(*) as total from jornadas where "
//                    + "( ? <= fhreg_jor and ? >= fhsal_jor and rut_emp = ? and nc_ot = 0)");
            PreparedStatement pstm = conn.prepareStatement("SELECT SUM(total) total FROM("
                    + "SELECT count(*) as total FROM Jornadas WHERE ( ? <= fhreg_jor AND ? >= fhsal_jor AND rut_emp = ? AND nc_ot = 0) "
                    + "UNION ALL "
                    + "SELECT count(*) as total FROM Detalle_oc_emp WHERE rut_emp = ? AND fhsal_det_emp IS NULL AND fhreg_det_emp IS NULL "
                    + "UNION ALL "
                    + "SELECT count(*) as total FROM Detalle_oc_emp WHERE ? <= fhreg_det_emp AND ? >= fhsal_det_emp AND rut_emp = ?"
                    + ") a");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            pstm.setString(3, rut);
            pstm.setString(4, rut);
            pstm.setString(5, fhsal);
            pstm.setString(6, fhreg);
            pstm.setString(7, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al obtener rut emp disp count 1");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return registros;
    }
    
    /* Chequea la disponibilidad de un empleado en un periodo determinado, dejando fuera la id de la jornada (se usa en el modificar) */
    public int checkEmpDispId(String fhsal, String fhreg, String rut, String id) {
        //String fechSal, String horaSal, String fechReg, String horaReg
        int registros = 0;
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(*) as total from jornadas where "
//                    + "( ? <= fhreg_jor and ? >= fhsal_jor and rut_emp = ?"
//                    + "and id_jor <> ? and nc_ot = 0)");
            PreparedStatement pstm = conn.prepareStatement("SELECT SUM(total) total FROM("
                    + "SELECT count(*) as total FROM Jornadas WHERE ( ? <= fhreg_jor AND ? >= fhsal_jor AND rut_emp = ? AND id_jor <> ? AND nc_ot = 0) "
                    + "UNION ALL "
                    + "SELECT count(*) as total FROM Detalle_oc_emp WHERE rut_emp = ? AND fhsal_det_emp IS NULL AND fhreg_det_emp IS NULL "
                    + "UNION ALL "
                    + "SELECT count(*) as total FROM Detalle_oc_emp WHERE ? <= fhreg_det_emp AND ? >= fhsal_det_emp AND rut_emp = ?"
                    + ") a");
            pstm.setString(1, fhsal);
            pstm.setString(2, fhreg);
            pstm.setString(3, rut);
            pstm.setString(4, id);
            pstm.setString(5, rut);
            pstm.setString(6, fhsal);
            pstm.setString(7, fhreg);
            pstm.setString(8, rut);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al obtener rut emp disp count 2");
            System.out.println("hola");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        return registros;
    }
    
    //BACKUP CON EL CALCULO DE BONO 300 SETTEADO EN 300 POR DEFAULT
//    public String[] obtenerRemuneracionPorRut(String rut) {
//        String[] data = new String[20];
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, dig_emp, nom_emp, apP_emp, "
//                    + "sueldo_emp, afp_emp, sal_emp, TIMESTAMPDIFf(YEAR, fin_emp, CURDATE()) ant, colacion_emp col, transporte_emp trans, "
//                    + " horcol_emp, coalesce(hormes_emp,0) hormes, coalesce(valbonoad_emp, 0) valbono "
//                    + ", coalesce(horexcalc_emp, 0) horex, coalesce(horex_emp, 0) canthor, coalesce(bonohorexcalc_emp, 0) bonohorex, "
//                    + "coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af, coalesce(anticipo_emp, 0) antic, "
//                    + "coalesce(adel_emp, 0) adel, coalesce(pres_emp, 0) pres, coalesce(cuo_emp, 0) cuo, coalesce(cuores_emp,0) cuores"
//                    + " FROM empleados WHERE rut_emp = ?");
//            pstm.setString(1, rut);
//            ResultSet res = pstm.executeQuery();
//            res.next();
//            String estrut = res.getString("rut_emp");
//            String estfrut = "";
//            int cont = 0;
//            for(int i = estrut.length() - 1; i >= 0; i--){
//                estfrut = estrut.substring(i , i+1) + estfrut;
//                cont ++;
//                if(cont == 3 && i != 0){
//                    estfrut = "." + estfrut;
//                    cont = 0;
//                }
//            }
//            String estdig = res.getString("dig_emp");
//            String estnom = res.getString("nom_emp");
//            String estapP = res.getString("apP_emp");
//            String estsueldo = res.getString("sueldo_emp");
//            String estafp = res.getString("afp_emp");
//            String estsal = res.getString("sal_emp");
//            String estant = res.getString("ant");
//            String estcol = res.getString("col");
//            String esttrans = res.getString("trans");
//            String esthorcol = res.getString("horcol_emp");
//            String esthormes = res.getString("hormes");
//            String estvalbono = res.getString("valbono");
//            String esthorex = res.getString("horex");
//            String estcanthor = res.getString("canthor");
//            String estbonohorex = res.getString("bonohorex");
//            String estcaja = res.getString("caja");
//            String estaf = res.getString("af");
//            String estantic = res.getString("antic");
//            String estadel = res.getString("adel");
//            String estpres = res.getString("pres");
//            String estcuo = res.getString("cuo");
//            String estcuores = res.getString("cuores");
//            data = new String[]{estfrut + "-" + estdig , estnom + " " + estapP, estsueldo, estafp, estsal, 
//                estant, estcol, esttrans, esthorcol, esthormes, estvalbono, esthorex, estcanthor, estbonohorex, 
//                estcaja, estaf, estantic, estadel, estpres, estcuo, estcuores};
//        }catch(SQLException e){
//            System.out.println("Error al obtener rem empleado por rut");
//            System.out.println(e);
//        }catch(ClassNotFoundException e){
//            System.out.println(e);
//        }
//        return data;
//    }
    /*** BORRAR USAR EL 2***/
    public String[] obtenerRemuneracionPorRut(String rut) {
        String[] data = new String[20];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT empleados.rut_emp, dig_emp, "
                    + "concat(nom_emp, ' ', apP_emp) nom, sum(coalesce(hor_bon,0) * coalesce(val_ton,0)) bon300, "
                    + "sueldo_emp, afp_emp, sal_emp, TIMESTAMPDIFf(YEAR, fin_emp, CURDATE()) ant, colacion_emp col, "
                    + "transporte_emp trans, horcol_emp, coalesce(hormes_emp,0) hormes, coalesce(valbonoad_emp, 0) valbono, "
                    + "coalesce(horexcalc_emp, 0) horex, coalesce(horex_emp, 0) canthor, coalesce(bonohorexcalc_emp, 0) bonohorex, "
                    + "coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af, coalesce(anticipo_emp, 0) antic, "
                    + "coalesce(adel_emp, 0) adel, coalesce(pres_emp, 0) pres, coalesce(cuo_emp, 0) cuo, coalesce(cuores_emp, 0) "
                    + "cuores, "
                    + "coalesce(des_afp, 0) des, coalesce(des_sal, 0) dessal, coalesce(isa_emp, '') isa, "
                    + "coalesce(val_isa_emp, 0) valisa, coalesce(diastrab_emp, 0) dias FROM Empleados LEFT JOIN Afps ON afp_emp = nom_afp LEFT JOIN Salud "
                    + "ON sal_emp = nom_sal LEFT JOIN horas_bono_300 on (empleados.rut_emp = horas_bono_300.rut_emp)  LEFT JOIN tonelajes using (id_ton)"
                    + "WHERE empleados.rut_emp = ?");
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
            String estnom = res.getString("nom");
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
            String estcuores = res.getString("cuores");
            String est300 = res.getString("bon300");
            String estdias = res.getString("dias");
            data = new String[]{estfrut + "-" + estdig , estnom , estsueldo, estafp, estsal, 
                estant, estcol, esttrans, esthorcol, esthormes, estvalbono, esthorex, estcanthor, estbonohorex, 
                estcaja, estaf, estantic, estadel, estpres, estcuo, estcuores, est300, estdias};
        }catch(SQLException e){
            System.out.println("Error al obtener rem empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[] obtenerRemuneracionPorRut2(String rut, int mes, int year) {
        String[] data = new String[20];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT empleados.rut_emp, dig_emp, concat(nom_emp, ' ', apP_emp) "
                    + "nom, coalesce(sum(coalesce(hor_bon, 0) * coalesce(val_ton, 0)), 0) bon300, sueldo_emp, afp_emp, sal_emp, "
                    + "TIMESTAMPDIFf(YEAR, fin_emp, CURDATE()) ant, colacion_emp col, transporte_emp trans, "
                    + " coalesce(horcol1_suel, 0) horcol1, coalesce(horcol30_suel, 0) horcol30, coalesce(hormes_suel,0) hormes, coalesce(valbonoad_emp, 0) valbono "
                    + ", coalesce(horexnor_suel, 0) horexnor, coalesce(horexfes_suel, 0) horexfes, "
                    + "coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af, coalesce(anticipo_emp, 0) antic, "
                    + "coalesce(adel_emp, 0) adel, coalesce(pres_emp, 0) pres, coalesce(cuo_emp, 0) cuo, coalesce(cuores_emp, 0) "
                    + "cuores, coalesce(des_afp, 0) des, coalesce(des_sal, 0) dessal, coalesce(isa_emp, '') isa, coalesce(val_isa_emp, 0) valisa, "
                    + "coalesce(fin_emp, '') fin, coalesce(diastrab_emp, 30) dias FROM Empleados LEFT JOIN Afps ON afp_emp = nom_afp LEFT JOIN Salud ON sal_emp = nom_sal "
                    + "LEFT JOIN Horas_bono_300 on (Empleados.rut_emp = Horas_bono_300.rut_emp) LEFT JOIN Tonelajes using(id_ton) LEFT JOIN Mensualidades ON Empleados.rut_emp  "
                    + " = Mensualidades.rut_emp WHERE empleados.rut_emp = ? AND mes_bon = ? AND mes_suel = ? AND year_bon = ? AND year_suel = ?");
            pstm.setString(1, rut);
            pstm.setInt(2, mes);
            pstm.setInt(3, mes);
            pstm.setInt(4, year);
            pstm.setInt(5, year);
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
                String estnom = res.getString("nom");
                String estsueldo = res.getString("sueldo_emp");
                String estafp = res.getString("afp_emp");
                String estsal = res.getString("sal_emp");
                String estant = res.getString("ant");
                String estcol = res.getString("col");
                String esttrans = res.getString("trans");
                String esthorcol1 = res.getString("horcol1");
                String esthorcol30 = res.getString("horcol30");
                String esthormes = res.getString("hormes");
                String estvalbono = res.getString("valbono");
                String esthorexnor = res.getString("horexnor");
                String esthorexfes = res.getString("horexfes");
                String estbonohorex = "0";
                String estcaja = res.getString("caja");
                String estaf = res.getString("af");
                String estantic = res.getString("antic");
                String estadel = res.getString("adel");
                String estpres = res.getString("pres");
                String estcuo = res.getString("cuo");
                String estdes = res.getString("des");
                String estdessal = res.getString("dessal");
                String estisa = res.getString("isa");
                String estvalisa = res.getString("valisa");
                String estfin = res.getString("fin");
                String estcuores = res.getString("cuores");
                String estbon300 = res.getString("bon300");
                String estdias = res.getString("dias");
                data = new String[]{estfrut + "-" + estdig , estnom, estsueldo, estafp, estsal, 
                    estant, estcol, esttrans, esthorcol1, esthorcol30, esthormes, estvalbono, esthorexnor, esthorexfes, estbonohorex,
                    estcaja, estaf, estantic, estadel, estpres, estcuo, estdes, estdessal, estisa, estvalisa, estfin, 
                    estcuores, estbon300, estdias};
        }catch(SQLException e){
            System.out.println("Error al obtener rem empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    /*** BORRAR, USAR EL 2 ***/
    public String[][] obtenerRemuneraciones() {
        
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
             System.out.println("Error al remuneracionesempleados");
             System.out.println(e);
        }catch(ClassNotFoundException e){
             System.out.println(e);
        }
        
        String[][] data = new String[registros][24];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT empleados.rut_emp, dig_emp, concat(nom_emp, ' ', apP_emp) "
                    + "nom, sum(coalesce(hor_bon, 0) * coalesce(val_ton, 0)) bon300, sueldo_emp, afp_emp, sal_emp, "
                    + "TIMESTAMPDIFf(YEAR, fin_emp, CURDATE()) ant, colacion_emp col, transporte_emp trans, "
                    + " coalesce(horcol_suel, coalesce(hormes_emp,0) hormes, coalesce(valbonoad_emp, 0) valbono "
                    + ", coalesce(horexcalc_emp, 0) horex, coalesce(horex_emp, 0) canthor, coalesce(bonohorexcalc_emp, 0) bonohorex, "
                    + "coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af, coalesce(anticipo_emp, 0) antic, "
                    + "coalesce(adel_emp, 0) adel, coalesce(pres_emp, 0) pres, coalesce(cuo_emp, 0) cuo, coalesce(cuores_emp, 0) "
                    + "cuores, coalesce(des_afp, 0) des, coalesce(des_sal, 0) dessal, coalesce(isa_emp, '') isa, coalesce(val_isa_emp, 0) valisa, "
                    + "coalesce(fin_emp, '') fin, coalesce(diastrab_emp, 30) dias FROM Empleados LEFT JOIN Afps ON afp_emp = nom_afp LEFT JOIN Salud ON sal_emp = nom_sal "
                    + "LEFT JOIN Horas_bono_300 on (Empleados.rut_emp = Horas_bono_300.rut_emp) LEFT JOIN Tonelajes using(id_ton) "
                    + "GROUP BY rut_emp");
            ResultSet res = pstm.executeQuery();
            int j = 0;
            while(res.next()){
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
                String estnom = res.getString("nom");
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
                String estdes = res.getString("des");
                String estdessal = res.getString("dessal");
                String estisa = res.getString("isa");
                String estvalisa = res.getString("valisa");
                String estfin = res.getString("fin");
                String estcuores = res.getString("cuores");
                String estbon300 = res.getString("bon300");
                String estdias = res.getString("dias");
                data[j] = new String[]{estfrut + "-" + estdig , estnom, estsueldo, estafp, estsal, 
                    estant, estcol, esttrans, esthorcol, esthormes, estvalbono, esthorex, estcanthor, estbonohorex, 
                    estcaja, estaf, estantic, estadel, estpres, estcuo, estdes, estdessal, estisa, estvalisa, estfin, 
                    estcuores, estbon300, estdias};
                j++;
            }
        }catch(SQLException e){
            System.out.println("Error al obtener rem empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public String[][] obtenerRemuneraciones2(int mes, int year) {
        
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
             System.out.println("Error al obtener remuneraciones empleados");
             System.out.println(e);
        }catch(ClassNotFoundException e){
             System.out.println(e);
        }
        
        String[][] data = new String[registros][24];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT empleados.rut_emp, dig_emp, concat(nom_emp, ' ', apP_emp) "
                    + "nom, coalesce(sum(coalesce(hor_bon, 0) * coalesce(val_ton, 0)), 0) bon300, sueldo_emp, afp_emp, sal_emp, "
                    + "TIMESTAMPDIFf(YEAR, fin_emp, CURDATE()) ant, colacion_emp col, transporte_emp trans, "
                    + " coalesce(horcol1_suel, 0) horcol1, coalesce(horcol30_suel, 0) horcol30, coalesce(hormes_suel,0) hormes, coalesce(valbonoad_emp, 0) valbono "
                    + ", coalesce(horexnor_suel, 0) horexnor, coalesce(horexfes_suel, 0) horexfes, "
                    + "coalesce(caja_emp, 0) caja, coalesce(af_emp, 0) af, coalesce(anticipo_emp, 0) antic, "
                    + "coalesce(adel_emp, 0) adel, coalesce(pres_emp, 0) pres, coalesce(cuo_emp, 0) cuo, coalesce(cuores_emp, 0) "
                    + "cuores, coalesce(des_afp, 0) des, coalesce(des_sal, 0) dessal, coalesce(isa_emp, '') isa, coalesce(val_isa_emp, 0) valisa, "
                    + "coalesce(fin_emp, '') fin, coalesce(diastrab_emp, 30) dias FROM Empleados LEFT JOIN Afps ON afp_emp = nom_afp LEFT JOIN Salud ON sal_emp = nom_sal "
                    + "LEFT JOIN (SELECT * FROM Horas_bono_300 WHERE mes_bon = ? AND year_bon = ?) h on (Empleados.rut_emp = h.rut_emp) LEFT JOIN Tonelajes using(id_ton) LEFT JOIN "
                    + "(SELECT * FROM Mensualidades WHERE mes_suel = ? AND year_suel = ?) m ON Empleados.rut_emp = m.rut_emp GROUP BY rut_emp");
            pstm.setInt(1, mes);
            pstm.setInt(2, year);
            pstm.setInt(3, mes);
            pstm.setInt(4, year);
            ResultSet res = pstm.executeQuery();
            int j = 0;
            while(res.next()){
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
                String estnom = res.getString("nom");
                String estsueldo = res.getString("sueldo_emp");
                String estafp = res.getString("afp_emp");
                String estsal = res.getString("sal_emp");
                String estant = res.getString("ant");
                String estcol = res.getString("col");
                String esttrans = res.getString("trans");
                String esthorcol1 = res.getString("horcol1");
                String esthorcol30 = res.getString("horcol30");
                String esthormes = res.getString("hormes");
                String estvalbono = res.getString("valbono");
                String esthorexnor = res.getString("horexnor");
                String esthorexfes = res.getString("horexfes");
                String estbonohorex = "0";
                String estcaja = res.getString("caja");
                String estaf = res.getString("af");
                String estantic = res.getString("antic");
                String estadel = res.getString("adel");
                String estpres = res.getString("pres");
                String estcuo = res.getString("cuo");
                String estdes = res.getString("des");
                String estdessal = res.getString("dessal");
                String estisa = res.getString("isa");
                String estvalisa = res.getString("valisa");
                String estfin = res.getString("fin");
                String estcuores = res.getString("cuores");
                String estbon300 = res.getString("bon300");
                String estdias = res.getString("dias");
                data[j] = new String[]{estfrut + "-" + estdig , estnom, estsueldo, estafp, estsal, 
                    estant, estcol, esttrans, esthorcol1, esthorcol30, esthormes, estvalbono, esthorexnor, esthorexfes, estbonohorex,
                    estcaja, estaf, estantic, estadel, estpres, estcuo, estdes, estdessal, estisa, estvalisa, estfin, 
                    estcuores, estbon300, estdias};
                j++;
            }
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
    
    public void agregarHorasExtra(String rut, double horas, double horasCalc){
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
            double diff = 45 - horasAct - horas;
            if(diff <= 0){
                pstm = conn.prepareStatement("UPDATE Empleados set horex_emp=? WHERE rut_emp = ?");
                pstm.setInt(1, 45);
                pstm.setString(2, rut);
                pstm = conn.prepareStatement("UPDATE Empleados set bonohorex_emp=bonohorex_emp+? WHERE rut_emp = ?");
                pstm.setDouble(1, Math.abs(diff));
                pstm.setString(2, rut);
                pstm.executeUpdate();
            }else{
                pstm = conn.prepareStatement("UPDATE Empleados set horex_emp=horex_emp+? WHERE rut_emp = ?");
                pstm.setDouble(1, horas);
//                pstm.setInt(2, horasCalc);
                pstm.setString(2, rut);
                pstm.executeUpdate();
            }
            double diffCalc = 90 - horasActCalc - horasCalc;
            if(diffCalc <= 0){
                pstm = conn.prepareStatement("UPDATE Empleados set horexcalc_emp=? WHERE rut_emp = ?");
                pstm.setInt(1, 90);
                pstm.setString(2, rut);
                pstm = conn.prepareStatement("UPDATE Empleados set bonohorexcalc_emp=bonohorexcalc_emp? WHERE rut_emp = ?");
                pstm.setDouble(1, Math.abs(diff));
                pstm.setString(2, rut);
                pstm.executeUpdate();
            }else{
                pstm = conn.prepareStatement("UPDATE Empleados set horexcalc_emp=horexcalc_emp+? WHERE rut_emp = ?");
//                pstm.setInt(1, horas);
                pstm.setDouble(1, horasCalc);
                pstm.setString(2, rut);
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
    
//    public void actualizarHorasMes(String rut, int horas, int ton){
//        try{
//            Class.forName("com.mysql.jdbc.Driver");
//            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("UPDATE Empleados set hormes_emp = hormes_emp + ? WHERE rut_emp = ?");
//            pstm.setInt(1, horas);
//            pstm.setString(2, rut);
//            pstm.executeUpdate();
//            pstm.close();
//        }catch(SQLException e){
//            System.out.println("Error al actualizar horas mes empleados");
//            System.out.println(e);
//        }catch(ClassNotFoundException e){
//            System.out.println(e);
//        }
//    }
    
    /***BORRAR?***/
    public void actualizarHorasMes(String rut, int horas, String ton){ //agregar ton
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Empleados set hormes_emp = hormes_emp + ? WHERE rut_emp = ?");
            pstm.setInt(1, horas);
            pstm.setString(2, rut);
            pstm.executeUpdate();
            pstm = conn.prepareStatement("INSERT INTO horas_bono_300 (rut_emp, id_ton, hor_bon) "
                    + "values (?, (SELECT id_ton FROM tonelajes WHERE pes_ton = ?), ?) ON DUPLICATE KEY UPDATE "
                    + "hor_bon = hor_bon + ?");
            pstm.setString(1, rut);
            pstm.setString(2, ton);
            pstm.setInt(3, horas);
            pstm.setInt(4, horas);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al actualizar horas mes empleados");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    /*Agrega la mensualidad y las horas bono 300 al operador al asignar una OT o una OC*/
    public void agregarMensualidad(String rut, int mes, int year, double horas, double horasNormales, double horasFestivas, int horasColacion30, int horasColacion1, String ton){ 
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("INSERT INTO Mensualidades (rut_emp, mes_suel, year_suel, hormes_suel, horexnor_suel, horexfes_suel, horcol30_suel, "
                    + "horcol1_suel) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?) ON DUPLICATE KEY UPDATE hormes_suel=hormes_suel+?, horexnor_suel=horexnor_suel+?, horexfes_suel=horexfes_suel+?, "
                    + "horcol30_suel=horcol30_suel+?, horcol1_suel=horcol1_suel+?");
            pstm.setString(1, rut);
            pstm.setInt(2, mes);
            pstm.setInt(3, year);
            pstm.setDouble(4, horas);
            pstm.setDouble(5, horasNormales);
            pstm.setDouble(6, horasFestivas);
            pstm.setInt(7, horasColacion30);
            pstm.setInt(8, horasColacion1);
            pstm.setDouble(9, horas);
            pstm.setDouble(10, horasNormales);
            pstm.setDouble(11, horasFestivas);
            pstm.setInt(12, horasColacion30);
            pstm.setInt(13, horasColacion1);
            pstm.execute();
            pstm = conn.prepareStatement("INSERT INTO horas_bono_300 (rut_emp, id_ton, hor_bon, mes_bon, year_bon) "
                    + "values (?, (SELECT id_ton FROM tonelajes WHERE pes_ton = ?), ?, ?, ?) ON DUPLICATE KEY UPDATE "
                    + "hor_bon = hor_bon + ?");
            pstm.setString(1, rut);
            pstm.setString(2, ton);
            pstm.setDouble(3, horas);
            pstm.setInt(4, mes);
            pstm.setInt(5, year);
            pstm.setDouble(6, horas);
            pstm.execute();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al actualizar mensualidad de empleado");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    /*Agrega las horas al detalle del operador de una OC*/
    public void agregarHorasDetalleEmp(int id, double horasTotales, double horasNormales, double horasFestivas, double horasColacion30, double horasColacion1, String ton){ 
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Detalle_oc_emp SET hortot_det_emp = ?, horex_det_emp = ?, horex2_det_emp = ?, horcol1_det_emp = ?, "
                    + "horcol30_det_emp = ?, pes_det_emp = ? WHERE id_det_oc = ?");
            pstm.setDouble(1, horasTotales);
            pstm.setDouble(2, horasNormales);
            pstm.setDouble(3, horasFestivas);
            pstm.setDouble(4, horasColacion1);
            pstm.setDouble(5, horasColacion30);
            pstm.setString(6, ton);
            pstm.setInt(7, id);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al actualizar detalle horas de empleado oc");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
    }
    
    /*Obtiene las horas de la ot mas la id del tonelaje segun su id*/
    public String[] obtenerHorasOt(String idOt){ //agregar ton
        String[] data = new String[1];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, fec_ot, hortot_ot, horex_ot, horex2_ot, horcol30_ot, horcol1_ot, id_ton FROM Jornadas INNER JOIN "
                    + "Gruas USING(pat_gru) INNER JOIN Tonelajes ON ton_gru = pes_ton WHERE cod_ot = ?");
            pstm.setString(1, idOt);
            ResultSet res = pstm.executeQuery();
            res.next();
            String estrut = res.getString("rut_emp");
            String estfec = res.getString("fec_ot");
            String esthor = res.getString("hortot_ot");
            String esthorex = res.getString("horex_ot");
            String esthorex2 = res.getString("horex2_ot");
            String esthorcol30 = res.getString("horcol30_ot");
            String esthorcol1 = res.getString("horcol1_ot");
            String estidton = res.getString("id_ton");
            data = new String[]{estrut, estfec, esthor, esthorex, esthorex2, esthorcol30, esthorcol1, estidton};
            pstm.close();
        }catch(SQLException e){
            System.out.println("Error al obtener horas ot de empleado");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    /*Obtiene las horas de la oc mas la id del tonelaje segun su id*/
    public String[][] obtenerHorasOc(String idOc){ //agregar ton
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT COUNT(1) total FROM Detalle_oc_emp INNER JOIN Jornadas_oc USING(id_joroc) WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
       }catch(SQLException e){
            System.out.println("Error al listar horas oc");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        String[][] data = new String[registros][];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, fec_oc, hortot_det_emp, horex_det_emp, horex2_det_emp, horcol30_det_emp, horcol1_det_emp, id_ton FROM Jornadas_oc INNER JOIN "
                    + "Detalle_oc_emp USING(id_joroc) INNER JOIN Tonelajes ON pes_det_emp = pes_ton WHERE cod_oc = ?");
            pstm.setString(1, idOc);
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_emp");
                String estfec = res.getString("fec_oc");
                String esthor = res.getString("hortot_det_emp");
                String esthorex = res.getString("horex_det_emp");
                String esthorex2 = res.getString("horex2_det_emp");
                String esthorcol30 = res.getString("horcol30_det_emp");
                String esthorcol1 = res.getString("horcol1_det_emp");
                String estidton = res.getString("id_ton");
                data[i] = new String[]{estrut, estfec, esthor, esthorex, esthorex2, esthorcol30, esthorcol1, estidton};
                i++;
            }
            pstm.close();
            res.close();
        }catch(SQLException e){
            System.out.println("Error al obtener horas oc de empleado");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
    
    public void restarMensualidad(String rut, int mes, int year, String horas, String horex, String horex2, String horcol30, String horcol1, String ton){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Mensualidades SET hormes_suel=hormes_suel-?, horexnor_suel=horexnor_suel-?, horexfes_suel=horexfes_suel-?, "
                    + "horcol30_suel=horcol30_suel-?, horcol1_suel=horcol1_suel-? WHERE rut_emp = ? AND mes_suel = ? AND year_suel = ?");
            pstm.setString(1, horas);
            pstm.setString(2, horex);
            pstm.setString(3, horex2);
            pstm.setString(4, horcol30);
            pstm.setString(5, horcol1);
            pstm.setString(6, rut);
            pstm.setInt(7, mes);
            pstm.setInt(8, year);
            pstm.execute();
            pstm = conn.prepareStatement("UPDATE Horas_bono_300 SET hor_bon=hor_bon-? WHERE rut_emp = ? AND mes_bon = ? AND year_bon = ? AND id_ton = ?");
            pstm.setString(1, horas);
            pstm.setString(2, rut);
            pstm.setInt(3, mes);
            pstm.setInt(4, year);
            pstm.setString(5, ton);
            pstm.executeUpdate();
            pstm.close();
        }catch(SQLException|ClassNotFoundException e){
            System.out.println("error al restar mensualidad empleado");
            System.out.println(e);
        }
    }

    public String ingresarPresAdelanto(String rut, String[] data){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            PreparedStatement pstm = conn.prepareStatement("UPDATE Empleados set anticipo_emp=?, adel_emp=?, pres_emp=?,"
                    + " cuo_emp=?, cuores_emp=? WHERE rut_emp=?");
            pstm.setString(1, data[0]);
            pstm.setString(2, data[1]);
            pstm.setString(3, data[2]);
            pstm.setString(4, data[3]);
            pstm.setString(5, data[3]);
            pstm.setString(6, rut);
            pstm.executeUpdate();
            pstm.close();
            return "correcto";
        }catch(SQLException | ClassNotFoundException e){
            System.out.println("Error al ingresar pres adelanto");
            System.out.println(e);
            return "incorrecto";
        }
    }
    
    public String[] obtenerRutEmpleados(){
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
            System.out.println("Error al obtener rut empleados");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        String[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp FROM Empleados ORDER BY rut_emp");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_emp");
                data[i] = estrut;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al obtener rut empleados");
            System.out.println(e);
        }
        return data;
    }
    
    public void limpiarRemuneraciones(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
            CallableStatement cstmt = conn.prepareCall("{call resetear_remuneraciones_findemes()}");
            cstmt.execute();
       }catch(SQLException e){
            System.out.println("Error al resetear remus");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
    }
    
    public Object[] listarRutEmpleados(){
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
            System.out.println("Error al listar rut empleados");
            System.out.println(e);
       }catch(ClassNotFoundException e){
            System.out.println(e);
       }
        
        Object[] data = new String[registros];
        
        try{
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp FROM Empleados ORDER BY rut_emp");
            ResultSet res = pstm.executeQuery();
            int i = 0;
            while(res.next()){
                String estrut = res.getString("rut_emp");
                data[i] = estrut;
                i++;
            }
            res.close();
        }catch(SQLException e){
            System.out.println("Error al listar empleados");
            System.out.println(e);
        }
        return data;
    }
    
    public String[][] obtenerReporteEmpleados(String rut, String fecin, String fecfin) {
        
        int registros = 0;
        
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Jornadas WHERE rut_emp = ? AND MONTH(fec_ot) = MONTH(CURDATE())");
            PreparedStatement pstm = conn.prepareStatement("SELECT count(1) as total FROM Jornadas WHERE rut_emp = ? AND fec_ot >= ? and  fec_ot <= ?");
            pstm.setString(1, rut);
            pstm.setString(2, fecin);
            pstm.setString(3, fecfin);
            ResultSet res = pstm.executeQuery();
            res.next();
            registros = res.getInt("total");
            res.close();
        }catch(SQLException e){
             System.out.println("Error al reportes empleados");
             System.out.println(e);
        }catch(ClassNotFoundException e){
             System.out.println(e);
        }
        
        String[][] data = new String[registros][10];
        try{
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(url, login, password);
//            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, dig_emp, concat(apP_emp, ' ', nom_emp) nom,  cod_ot, fec_ot, raz_cli, horsal_jor horsal, horfin_ot horfin,"
//                    + " horlleg_jor horlleg, CONCAT('', timediff(fhreg_jor, fhsal_jor)) diff FROM Jornadas JOIN Empleados USING(rut_emp) JOIN Clientes USING(rut_cli) WHERE rut_emp = ? AND MONTH(fec_ot) = MONTH(CURDATE()) ");
            PreparedStatement pstm = conn.prepareStatement("SELECT rut_emp, dig_emp, concat(apP_emp, ' ', nom_emp) nom,  cod_ot, fec_ot, raz_cli, horsal_jor horsal, horfin_ot horfin,"
                    + " horlleg_jor horlleg, CONCAT('', timediff(fhreg_jor, fhsal_jor)) diff FROM Jornadas JOIN Empleados USING(rut_emp) JOIN Clientes USING(rut_cli) WHERE rut_emp = ? AND fec_ot >= ? and  fec_ot <= ?");
            pstm.setString(1, rut);
            pstm.setString(2, fecin);
            pstm.setString(3, fecfin);
            ResultSet res = pstm.executeQuery();
            int j = 0;
            while(res.next()){
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
                String estnom = res.getString("nom");
                String estcod = res.getString("cod_ot");
                String estfec = res.getString("fec_ot");
                String estraz = res.getString("raz_cli");
                String estsal = res.getString("horsal");
                String estfin = res.getString("horfin");
                String estlleg = res.getString("horlleg");
                String esthorex = "0";
                String estharr = res.getString("diff");
                data[j] = new String[]{estfrut + "-" + estdig , estnom, estcod, estfec, estraz, estsal, estfin, estlleg, esthorex, estharr};
                j++;
            }
        }catch(SQLException e){
            System.out.println("Error al obtener ots empleado por rut");
            System.out.println(e);
        }catch(ClassNotFoundException e){
            System.out.println(e);
        }
        return data;
    }
}
