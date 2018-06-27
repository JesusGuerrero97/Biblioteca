/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Modelo.ModSQLInicioSesion;
import Vista.Login;
import Controlador.ControladorLogin;
import Vista.VistaSucursal;
import Modelo.ModeloSucursal;
import Controlador.ControladorSucursal;
/**
 *
 * @author Jesus
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //ModSQLInicioSesion modelo = new ModSQLInicioSesion();
        /*Login vista = new Login();
        ControladorLogin control = new ControladorLogin(modelo, vista);
        control.iniciarVista();*/
        
        VistaSucursal vista = new VistaSucursal();
        ModeloSucursal modelo = new ModeloSucursal();
        ControladorSucursal control = new ControladorSucursal(modelo, vista);
        control.iniciarVista();
    }
    
}
