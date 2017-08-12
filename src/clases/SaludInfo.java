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
public class SaludInfo {
    String rut;
    String nombre;
    double totImp;
    double descSalud;

    public SaludInfo(String rut, String nombre, double totImp, double descSalud) {
        this.rut = rut;
        this.nombre = nombre;
        this.totImp = totImp;
        this.descSalud = descSalud;
    }

    public String getRut() {
        return rut;
    }

    public String getNombre() {
        return nombre;
    }

    public double getTotImp() {
        return totImp;
    }

    public double getDescSalud() {
        return descSalud;
    }
}
