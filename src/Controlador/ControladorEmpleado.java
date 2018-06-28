
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

import Vista.VistaEmpleado;
import Modelo.ModeloEmpleado;

import Vista.MenuPrincipal;
import Modelo.ModeloMenuPrincipal;
import Modelo.ModeloSucursal;
import Vista.VistaSucursal;
import javax.swing.event.ChangeEvent;

public class ControladorEmpleado implements ActionListener, MouseListener{
    private ModeloEmpleado modelo;
    private VistaEmpleado vista;
    
    public ControladorEmpleado(ModeloEmpleado Modelo, VistaEmpleado Vista)
{       this.modelo = Modelo;
        this.vista = Vista;
        this.vista.empleado.addMouseListener(this);
        vista.btnAgregar.addActionListener(this); //Aqui
        vista.btnEditar1.addActionListener(this);//Aqui
        vista.btnCancelar.addActionListener(this);//Aqui
        vista.btnBuscar1.addActionListener(this);
        vista.btnEliminar2.addActionListener(this);
}

<<<<<<< HEAD
    
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
=======
    @Override
    public void actionPerformed(ActionEvent evento) {
         if(vista.btnAgregar == evento.getSource()){
            modelo.agregarSucursal(Integer.parseInt(vista.txtIdSucursal.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(), vista.txtTelefono.getText(),Integer.parseInt(vista.txtIdSucursal.getText()));
                
            JOptionPane.showMessageDialog(vista, "Registro insertado exitosamente");
            limpiar();
            vista.empleado.setModel(modelo.cargarDatos());
        }                
        if(vista.btnCancelar == evento.getSource()){
                limpiar();
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
            int idSucursal = Integer.parseInt(vista.txtIdEmp.getText());
            vista.empleado.setModel(modelo.buscarDatos( idSucursal));          
           // JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
        }
        if(vista.btnEditar1 == evento.getSource()){
            modelo.conActualizar(Integer.parseInt(vista.txtIdEmp.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(),vista.txtTelefono.getText(),Integer.parseInt(vista.txtIdSucursal.getText()));
            vista.empleado.setModel(modelo.cargarDatos());
            limpiar();
        }
        if(vista.btnEliminar2 == evento.getSource()){
            if(modelo.conEliminar(Integer.parseInt(vista.txtIdEmp.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                vista.empleado.setModel(modelo.cargarDatos());
            }
            
            /*
            int idSucursal = Integer.parseInt(vista.txtIdSucursal.getText());
            
            modelo.conEliminar(idSucursal);
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");*/
        }
>>>>>>> 6cf44b717b8b71ece1637ff886b2da5b4309258b
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
                vista.tablaSuc.setModel(modelo.cargarDatos());
            }
            
            /*
            int idSucursal = Integer.parseInt(vista.txtIdSucursal.getText());
            
            modelo.conEliminar(idSucursal);
            JOptionPane.showMessageDialog(null, "Registro eliminado exitosamente");*/
        }
        }


    @Override
<<<<<<< HEAD
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
=======
    public void mouseClicked(MouseEvent e) {
         //To change body of generated methods, choose Tools | Templates.
          if(vista.empleado== e.getSource()){
            int fila=vista.empleado.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdSucursal.setText(String.valueOf(vista.empleado.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.empleado.getValueAt(fila, 1)));
                vista.txtDireccion.setText(String.valueOf(vista.empleado.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.empleado.getValueAt(fila, 3)));
                vista.txtIdSucursal.setText(String.valueOf(vista.empleado.getValueAt(fila, 4)));
>>>>>>> 6cf44b717b8b71ece1637ff886b2da5b4309258b
            }
        }  
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
        vista.txtCorreo.setText("");
        vista.txtDireccion.setText("");
        vista.txtIdEmp.setText("");
        vista.txtTelefono.setText("");
        vista.txtIdSucursal.setText("");
    }
}
