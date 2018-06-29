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
import javax.swing.JOptionPane;

import Vista.VistaInventario;
import Modelo.ModeloInventario;
import Controlador.ControladorInventario;
import Vista.VistaLibro;
import Modelo.ModeloLibro;
import Modelo.ModeloMenuPrincipal;
import Vista.MenuPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 *
 * @author Dania
 */
public class ControladorLibro implements ActionListener, MouseListener{
    private ModeloLibro modelo;
    private VistaLibro vista;
    
    //private Date fecha_ini;
    
    public void transparenciaButton(){
        vista.btnAgregar.setOpaque(false);
        vista.btnAgregar.setContentAreaFilled(false);
        vista.btnAgregar.setBorderPainted(false);
        vista.btnRegresar.setOpaque(false);
        vista.btnRegresar.setContentAreaFilled(false);
        vista.btnRegresar.setBorderPainted(false);
    }
    
    public ControladorLibro(ModeloLibro modelo, VistaLibro vista) {
        this.modelo = modelo;
        this.vista = vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnCancelar1.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
          if(vista.btnAgregar == e.getSource()) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fechaBD = df.format(vista.jDateChooserAddLibro.getDate());
            //this.fecha_ini = vista.jDateChooserAddLibro.getDate();
            modelo.insertarLibro(Integer.parseInt(vista.txtIdLibro.getText()), vista.txtNombre.getText(), vista.txtAutor.getText(), vista.txtEditorial.getText(),fechaBD, Integer.parseInt(vista.txtNumPag.getText()), vista.txtEdicion.getText(), vista.txtGenero.getText(), Integer.parseInt(vista.txtIdSucursal.getText()), Integer.parseInt(vista.txtExistencia.getText()));
                
                limpiar();
            
        }
         if(vista.btnEditar == e.getSource()) {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            String fechaBD = df.format(vista.jDateChooserAddLibro.getDate());
            if(modelo.modificarLibro(Integer.parseInt(vista.txtIdLibro.getText()), vista.txtNombre.getText(), vista.txtAutor.getText(), vista.txtEditorial.getText(),fechaBD, Integer.parseInt(vista.txtNumPag.getText()), vista.txtEdicion.getText(), vista.txtGenero.getText(), Integer.parseInt(vista.txtIdSucursal.getText()), Integer.parseInt(vista.txtExistencia.getText()))) {
                limpiar();
                
             VistaInventario obj = new VistaInventario();
            ModeloInventario modelo = new ModeloInventario();
            ControladorInventario ControladorInventario = new ControladorInventario(modelo,obj);
                ControladorInventario.iniciarVista();
                vista.dispose();
            }
        }
        if(vista.btnCancelar1 == e.getSource()) {
            if(modelo.deleteLibro(Integer.parseInt(vista.txtIdLibro.getText()))) {
                
            }
        }
        if(vista.btnRegresar == e.getSource()){
            VistaInventario obj = new VistaInventario();
            ModeloInventario modelo = new ModeloInventario();
            ControladorInventario ControladorInventario = new ControladorInventario(modelo,obj);
            ControladorInventario.iniciarVista();
            vista.dispose();
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
    }
    public void limpiar() {
        vista.txtNombre.setText("");
        vista.txtAutor.setText("");
        vista.txtEditorial.setText("");
        vista.txtGenero.setText("");
        vista.txtNumPag.setText("");
        vista.txtIdLibro.setText("");
        vista.txtEdicion.setText("");
        vista.txtIdSucursal.setText("");
        vista.txtExistencia.setText("");
    }
    public void iniciarVista() {
        vista.setTitle("Libros");
        vista.setVisible(true);
  //      vista.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
       
    }
}
