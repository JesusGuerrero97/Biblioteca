/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.ModSQLInicioSesion;
import Vista.Login;

import Modelo.ModeloMenuPrincipal;
import Vista.MenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
/**
 *
 * @author Dania
 */
public class ControladorLogin implements ActionListener, MouseListener{
    
    ModSQLInicioSesion MInicio = new ModSQLInicioSesion();
    Login Log = new Login();
    
    public ControladorLogin(ModSQLInicioSesion MI, Login log){
        this.MInicio=MI;
        this.Log=log;
    }

    public void verificarInicio()
    {
        int idEmpleado = -1;
        idEmpleado = MInicio.consultarInicio(Log.txtUsuario.getText(), Log.txtPassw.getText()); //checa si el inicio es correcto y asigna el id a IdEmpleado si lo encuentra, de lo contrario, asigna un -1
        if(idEmpleado != -1){   //si se encontró una coincidencia en el inicio de sesion    
            Log.dispose(); 
            ModeloMenuPrincipal modMenu = new ModeloMenuPrincipal();
            MenuPrincipal visMenu = new MenuPrincipal();
            ControladorMenuPrincipal conMenu = new ControladorMenuPrincipal(modMenu, visMenu);
            conMenu.iniciarVista();
        }
    }
    
    public void iniciarVista()
    {
        Log.setTitle("Inicio de sesión");
        Log.pack();
        Log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       // visInicio.setLocationRelativeTo(null);
        Log.setVisible(true);
        //Log.btnIniciarSesion.addMouseListener((MouseListener) this);
       //visInicio.btnSalir.addMouseListener((MouseListener) this);
        //visInicio.txtContra.addKeyListener(this);
        Log.btnIniciarSesion.addActionListener(this);
        //visInicio.btnSalir.addKeyListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Log.btnIniciarSesion == e.getSource()) {
            verificarInicio();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(Log.btnIniciarSesion == e.getSource()) {
            verificarInicio();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
