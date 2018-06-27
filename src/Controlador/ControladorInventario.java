/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
//import javax.swing.JOptionPane;

import Vista.VistaInventario;
import Modelo.ModeloInventario;
/**
 *
 * @author Dania
 */
public class ControladorInventario implements ActionListener, MouseListener{
    private ModeloInventario modelo;
    private VistaInventario vista;
    
    public ControladorInventario(ModeloInventario modelo, VistaInventario vista){
        this.modelo=modelo;
        this.vista=vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.jTable1.addMouseListener(this);
    }
    
    public void iniciarVista(){
        vista.setTitle("Ventana de usuarios");
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setLocationRelativeTo(null);
        vista.jTable1.setModel(modelo.inventarioConsultar());
        vista.setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
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
