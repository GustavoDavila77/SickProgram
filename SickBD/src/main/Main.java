/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import controlador.Controlador;

/**
 *
 * @author Gustavo Davila
 */
public class Main {
    
    public static void main(String[] args) {

        //JFrame view = new ViewLogin();
        //Modelo modelo = new Modelo();
        Controlador controlador = new Controlador();
        controlador.iniciar();

    } 
}
