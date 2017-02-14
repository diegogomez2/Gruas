/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controladores3;

import java.text.DateFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author diego
 */
public class controladorReporteTrabajador {
    DateFormat perDate = new SimpleDateFormat("MMMM-yyyy");
    String per = perDate.format(new Date());
    NumberFormat FORMAT = NumberFormat.getCurrencyInstance();
    DecimalFormatSymbols dfs = new DecimalFormatSymbols();
    
    public void generarReporteTrabajador(){
        Thread runnable = new Thread(){
            public void run(){
                try{
                    
                }catch(Exception e){
                    
                }
            }
        };
    }
}
