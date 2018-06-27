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

import Vista.VistaLibro;
import Modelo.ModeloLibro;
import Controlador.ControladorLibro;

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
        if(vista.btnAgregar == e.getSource())
        {   
            ModeloLibro LibroMod = new ModeloLibro();
            VistaLibro LibroVis = new VistaLibro();
             ControladorLibro LibroCon = new ControladorLibro(LibroMod,LibroVis); 
            LibroCon.iniciarVista();
        }
        if(vista.btnEditar == e.getSource())
        {
            ModeloLibro LibroMod = new ModeloLibro();
            VistaLibro LibroVis = new VistaLibro();
            
            //LibroVis.txtIdLibro.setText(String.valueOf(vista.jTable1.getValueAt(fila, 0)));
            ControladorLibro LibroCon = new ControladorLibro(LibroMod,LibroVis);
            LibroCon.iniciarVista();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    
            if(vista.jTable1==e.getSource())
            {
                int fila=vista.jTable1.rowAtPoint(e.getPoint());
                if(fila > -1)
                {
                    vista.txtNombre1.setText(String.valueOf(vista.jTable1.getValueAt(fila, 0)));
                }
            
            }
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
