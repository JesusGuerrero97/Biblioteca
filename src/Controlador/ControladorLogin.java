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

import Controlador.ControladorMenuPrincipal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
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
        //this.Log.btnSalir.addActionListener(this);
    }

    public void verificarInicio()
    {
        int resultado = 0;
        if(!Log.txtUsuario.getText().equals("") && !String.valueOf(Log.jPassword.getPassword()).equals(""))
        {   
            resultado = MInicio.consultarInicio(Log.txtUsuario.getText(),String.valueOf(Log.jPassword.getPassword())); //checa si el inicio es correcto y asigna el id a IdEmpleado si lo encuentra, de lo contrario, asigna un -1
            //System.out.println(Log.jPassword.getPassword().toString());
            if(resultado == 1){   //si se encontró una coincidencia en el inicio de sesion    
                Log.dispose(); 
                JOptionPane.showMessageDialog(null, "Bienvenido al sistema", "Mensaje de bienvenida", JOptionPane.INFORMATION_MESSAGE);
                ControladorMenuPrincipal conMenu = new ControladorMenuPrincipal();
                conMenu.iniciarVista();
            }
            else{
                JOptionPane.showMessageDialog(null, "ERROR", "ERROR AL INICIAR AL SISTEMA", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        //else JOptionPane.showMessageDialog(null, "ERROR", "Complete los campos", JOptionPane.ERROR_MESSAGE);
    }
    
    public void iniciarVista()
    {
        //Log.setTitle("Inicio de sesión");
        Log.pack();
        Log.setLocationRelativeTo(null);
        Log.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Log.setVisible(true);
        Log.btnIniciaSesion.addActionListener(this);
        Log.btnSalir.addActionListener(this);
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if(Log.btnIniciaSesion == e.getSource()) {
            if(!Log.txtUsuario.getText().equals("") && !String.valueOf(Log.jPassword.getPassword()).equals(""))
            {   
                verificarInicio();
            }
            else if(Log.txtUsuario.getText()=="" || Log.txtUsuario.getText()==null)
            {
             JOptionPane.showMessageDialog(null, "ERROR", "Complete todos los campos", JOptionPane.INFORMATION_MESSAGE);
             System.exit(0);
            }
        }
        
        if(Log.btnSalir == e.getSource())
        {
            System.exit(0);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
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
