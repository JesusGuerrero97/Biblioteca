
package Controlador;

import Modelo.ModeloLibro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Vista.VistaSucursal;
import Modelo.ModeloSucursal;

import Vista.MenuPrincipal;
import Modelo.ModeloMenuPrincipal;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class ControladorSucursal implements ActionListener, PropertyChangeListener, ChangeListener, MouseListener{
    private ModeloSucursal modelo;
    private VistaSucursal vista;
    
    //String datos[][]={};
    //String[] camposCliente={"idSuc","nombre","direc", "tel","correo"};
    
    public ControladorSucursal(ModeloSucursal modelo, VistaSucursal vista){
        this.modelo=modelo;
        this.vista=vista;
        this.vista.tablaSuc.addMouseListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar1.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnBuscar1.addActionListener(this);
    }
    
    public void iniciarVista(){
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //vista.setAlwaysOnTop( true );
        vista.setLocationRelativeTo(null);
        //vista.setAlwaysOnTop( false );
        //vista.setVisible(true);
        vista.setResizable(false);
        vista.setTitle("Sucursal");
        transparenciaButton();
        vista.tablaSuc.setModel(modelo.cargarDatos());
        //vista.setAlwaysOnTop( false );
        vista.setVisible(true);
        //deshabilitarElementos();
    }
    
    
    public void transparenciaButton(){
        vista.btnRegresar.setOpaque(false);
        vista.btnRegresar.setContentAreaFilled(false);
        vista.btnRegresar.setBorderPainted(false);
        vista.btnAgregar.setOpaque(false);
        vista.btnAgregar.setContentAreaFilled(false);
        vista.btnAgregar.setBorderPainted(false);
        vista.btnCancelar.setOpaque(false);
        vista.btnCancelar.setContentAreaFilled(false);
        vista.btnCancelar.setBorderPainted(false);
        vista.btnEliminar1.setOpaque(false);
        vista.btnEliminar1.setContentAreaFilled(false);
        vista.btnEliminar1.setBorderPainted(false);
        vista.btnEditar.setOpaque(false);
        vista.btnEditar.setContentAreaFilled(false);
        vista.btnEditar.setBorderPainted(false);
        vista.btnBuscar1.setOpaque(false);
        vista.btnBuscar1.setContentAreaFilled(false);
        vista.btnBuscar1.setBorderPainted(false);
    }
    public void limpiarVista(){
        vista.txtIdSucursal.setText("");
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtDireccion.setText("");
        vista.txtTelefono.setText("");
    }

    
    public void actionPerformed(ActionEvent evento){
        if(vista.btnAgregar == evento.getSource()){
            modelo.agregarSucursal(Integer.parseInt(vista.txtIdSucursal.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(), vista.txtTelefono.getText(),vista.txtCorreo.getText());
                
            JOptionPane.showMessageDialog(vista, "Registro insertado exitosamente");
            limpiarVista();
            vista.tablaSuc.setModel(modelo.cargarDatos());
        }                
        if(vista.btnCancelar == evento.getSource()){
                limpiarVista();
        }   
        if(vista.btnRegresar == evento.getSource()){
            MenuPrincipal obj = new MenuPrincipal();
            ModeloMenuPrincipal modeloMenu = new ModeloMenuPrincipal();
            ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal(modeloMenu,obj);
            ControladorMenuPrincipal.iniciarVista();
            vista.dispose();
        }
        if(vista.btnBuscar1 == evento.getSource()){ 
            vista.btnCancelar.setEnabled(true);
            int idSucursal = Integer.parseInt(vista.txtIdSucursal.getText());
            vista.tablaSuc.setModel(modelo.buscarDatos( idSucursal));          
           // JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
        }
        if(vista.btnEditar == evento.getSource()){
            modelo.conActualizar(Integer.parseInt(vista.txtIdSucursal.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(),vista.txtTelefono.getText(),vista.txtCorreo.getText());
            vista.tablaSuc.setModel(modelo.cargarDatos());
            limpiarVista();
        }
        if(vista.btnEliminar1 == evento.getSource()){
            if(modelo.conEliminar(Integer.parseInt(vista.txtIdSucursal.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiarVista();
                vista.tablaSuc.setModel(modelo.cargarDatos());
            }
        }
        }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
    }

    public void stateChanged(ChangeEvent e) {
       
    }

    @Override
    public void mouseClicked(MouseEvent e) { //To change body of generated methods, choose Tools | Templates.
         if(vista.tablaSuc== e.getSource()){
            int fila=vista.tablaSuc.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdSucursal.setText(String.valueOf(vista.tablaSuc.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.tablaSuc.getValueAt(fila, 1)));
                vista.txtDireccion.setText(String.valueOf(vista.tablaSuc.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.tablaSuc.getValueAt(fila, 3)));
                vista.txtCorreo.setText(String.valueOf(vista.tablaSuc.getValueAt(fila, 4)));
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
