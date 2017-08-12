/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clases;

/**
 *
 * @author diego
 */
public class AfpInfo {
    String rut;
    String nombre; 
    double totalImp;
    int descAfp;
    int sis;
    int totAfp;

    public AfpInfo(String rut, String nombre, double totalImp, int descAfp, int sis, int totAfp) {
        this.rut = rut;
        this.nombre = nombre;
        this.totalImp = totalImp;
        this.descAfp = descAfp;
        this.sis = sis;
        this.totAfp = totAfp;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTotalImp() {
        return totalImp;
    }

    public int getDescAfp() {
        return descAfp;
    }

    public int getSis() {
        return sis;
    }

    public int getTotAfp() {
        return totAfp;
    }    
}
