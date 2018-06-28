/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import Modelo.*;
import Vista.*;

import Modelo.ModeloInventario;
import Vista.VistaInventario;
import Controlador.ControladorInventario;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import javax.swing.JFrame;

/**
 *
 * @author Dania
 */
public class ControladorMenuPrincipal implements ActionListener {
    ModeloMenuPrincipal modelo = new ModeloMenuPrincipal();
    MenuPrincipal menu = new MenuPrincipal();
    //int idEmp;
    
    //ControladorInventario InventarioCon;
    
    public ControladorMenuPrincipal(ModeloMenuPrincipal modelo, MenuPrincipal menu)
    {
        this.modelo = modelo;
        this.menu = menu;
    }

    ControladorMenuPrincipal(){
    
    }
    
    public void iniciarVista()
    {
        //menu.setTitle("Biblioteca");
        
        menu.pack();
        
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.setLocationRelativeTo(null);
        menu.setVisible(true);
        //AdmMenu.nombreEmpleado.setText("ID: "+this.idEmp);
        this.menu.btnCliente.addActionListener(this);
        this.menu.btnEmpleado.addActionListener(this);
        this.menu.btnInventario.addActionListener(this);
        this.menu.btnRenta1.addActionListener(this);
        this.menu.btnSucursal.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(menu.btnInventario == e.getSource())
        {   
            ModeloInventario InventarioMod = new ModeloInventario();
            VistaInventario InventarioVis = new VistaInventario();
            ControladorInventario InventarioCon = new ControladorInventario(InventarioMod,InventarioVis); 
            InventarioCon.iniciarVista();
        }
        if(menu.btnSucursal == e.getSource())
        {
            VistaSucursal vista = new VistaSucursal();
        ModeloSucursal modelo = new ModeloSucursal();
        ControladorSucursal control = new ControladorSucursal(modelo, vista);
        control.iniciarVista();
        }
        if(menu.btnCliente == e.getSource())
        {
            VistaCliente vista = new VistaCliente();
        ModeloCliente modelo = new ModeloCliente();
        ControladorCliente control = new ControladorCliente(modelo, vista);
        control.iniciarVista();
        }
        if(menu.btnEmpleado == e.getSource())
        {
            VistaEmpleado vista = new VistaEmpleado();
        ModeloEmpleado modelo = new ModeloEmpleado();
        ControladorEmpleado control = new ControladorEmpleado(modelo, vista);
        control.iniciarVista();
        }
        if(menu.btnRenta1 == e.getSource())
        {
            VistaRenta vista = new VistaRenta();
        ModeloRenta modelo = new ModeloRenta();
        ControladorRenta control = new ControladorRenta(modelo, vista);
        control.iniciarVista();
        }
        if(menu.btnCerrarSesion == e.getSource())
        {
            menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        }
    }
}
