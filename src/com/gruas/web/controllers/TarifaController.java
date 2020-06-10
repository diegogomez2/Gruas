/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gruas.web.controllers;

import vistas.vistaAgregarTarifa;

/**
 *
 * @author Diego
 */
public class TarifaController {
    static vistas.vistaAgregarTarifa vistaAgregarTarifa;

    void mostrarVistaAgregarTarifa(Object[] data) {
        if(vistaAgregarTarifa != null) vistaAgregarTarifa.setVisible(false);
        vistaAgregarTarifa = new vistaAgregarTarifa(data);
        vistaAgregarTarifa.setVisible(true);
        vistaAgregarTarifa.setLocationRelativeTo(null);
    }
}
