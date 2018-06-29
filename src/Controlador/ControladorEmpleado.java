
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
import Modelo.ModeloEmpleado;
import Vista.VistaEmpleado;
import javax.swing.event.ChangeEvent;

public class ControladorEmpleado implements ActionListener, MouseListener{
    private ModeloEmpleado modelo;
    private VistaEmpleado vista;
   
    
    public ControladorEmpleado(ModeloEmpleado Modelo, VistaEmpleado Vista)
{       this.modelo = Modelo;
        this.vista = Vista;
        this.vista.empleado.addMouseListener(this);
        this.vista.btnAgregar.addActionListener(this); //Aqui
        this.vista.btnEditar1.addActionListener(this);//Aqui
        this.vista.btnCancelar.addActionListener(this);//Aqui
        this.vista.btnBuscar1.addActionListener(this);
        this.vista.btnEliminar2.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
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
        vista.empleado.setModel(modelo.cargarDatos());
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
        vista.btnEliminar2.setOpaque(false);
        vista.btnEliminar2.setContentAreaFilled(false);
        vista.btnEliminar2.setBorderPainted(false);
        vista.btnEditar1.setOpaque(false);
        vista.btnEditar1.setContentAreaFilled(false);
        vista.btnEditar1.setBorderPainted(false);
        vista.btnBuscar1.setOpaque(false);
        vista.btnBuscar1.setContentAreaFilled(false);
        vista.btnBuscar1.setBorderPainted(false);
    }
   
    
    public void actionPerformed(ActionEvent evento){
        if(vista.btnAgregar == evento.getSource()){
            int status=1;
            modelo.agregarSucursal(Integer.parseInt(vista.txtIdEmp.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), Integer.parseInt(vista.txtIdSucursal.getText()), status);
                
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
            int idEmp = Integer.parseInt(vista.txtIdEmp.getText());
            vista.empleado.setModel(modelo.buscarDatos( idEmp));          
           // JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
        }
        if(vista.btnEditar1 == evento.getSource()){
            modelo.conActualizar(Integer.parseInt(vista.txtIdEmp.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(),vista.txtTelefono.getText(),vista.txtCorreo.getText(), Integer.parseInt(vista.txtIdSucursal.getText()));
            JOptionPane.showMessageDialog(null, "Registro modificado");
            vista.empleado.setModel(modelo.cargarDatos());
            limpiar();
        }
        if(vista.btnEliminar2 == evento.getSource()){
            if(modelo.conEliminar(Integer.parseInt(vista.txtIdEmp.getText()))){
                JOptionPane.showMessageDialog(null, "Registro eliminado");
                limpiar();
                vista.empleado.setModel(modelo.cargarDatos());
            }
        }
        }


    @Override

    public void mouseClicked(MouseEvent e) { //To change body of generated methods, choose Tools | Templates.
         if(vista.empleado== e.getSource()){
            int fila=vista.empleado.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdEmp.setText(String.valueOf(vista.empleado.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.empleado.getValueAt(fila, 1)));
                vista.txtDireccion.setText(String.valueOf(vista.empleado.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.empleado.getValueAt(fila, 3)));
                vista.txtCorreo.setText(String.valueOf(vista.empleado.getValueAt(fila, 4)));
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
