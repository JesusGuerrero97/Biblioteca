/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;
import Modelo.*;
import Vista.*;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Set;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JSpinner.DefaultEditor;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author Dania
 */
public class ControladorRenta implements ActionListener, MouseListener{
    private ModeloRenta modelo;
    private VistaRenta vista;
    
    int id_libro,id_cliente,id_emp;
    public String fecha_renta,fecha_entrega;
    
    
     public ControladorRenta(ModeloRenta modelo, VistaRenta vista){
        this.modelo=modelo;
        this.vista=vista;
        this.vista.tablaRenta.addMouseListener(this);
        this.vista.btnEditar2.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnAgregar2.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnBuscar1.addActionListener(this);
        this.vista.tablaRenta.addMouseListener(this);
     }
    
    public void iniciarVista(){
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //vista.setAlwaysOnTop( true );
        vista.setLocationRelativeTo(null);
        //vista.setAlwaysOnTop( false );
        //vista.setVisible(true);
        vista.setResizable(false);
        vista.setTitle("Renta Libros");
            //transparenciaButton();
            //vista.setModel(modelo.cargarDatos());
        //vista.setAlwaysOnTop( false );
        vista.setVisible(true);
        modelo.llenarComboClientes(vista.cmbCliente);
        modelo.llenarComboEmpleado(vista.cmbEmpleado);
        modelo.llenarComboLibros(vista.cmbLibros);
        vista.tablaRenta.setModel(modelo.cargarDatos());
        //deshabilitarElementos();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
         id_libro = vista.cmbLibros.getItemAt(vista.cmbLibros.getSelectedIndex()).getId_libro();
        id_cliente = vista.cmbCliente.getItemAt(vista.cmbCliente.getSelectedIndex()).getId_cliente();
        id_emp= vista.cmbEmpleado.getItemAt(vista.cmbEmpleado.getSelectedIndex()).getId_emp();
        fecha_renta= df.format(vista.jDateChooserRenta.getDate());
        fecha_entrega= df.format(vista.jDateChooserEntrega.getDate());
        if(vista.btnAgregar2 == e.getSource())
        {   
            
           // String fechaBD = df.format(vista..getDate());
           
            if(modelo.insertarRenta(Integer.parseInt(vista.txtIdRenta.getText()), id_libro, id_cliente, fecha_renta, fecha_entrega, id_emp))
            {
                 JOptionPane.showMessageDialog(null, "Renta Registrada con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                 vista.tablaRenta.setModel(modelo.cargarDatos());
                 limpiarVista();
            }
        }
        if(vista.btnEditar2== e.getSource())
        {
            if(modelo.modificarRenta(Integer.parseInt(vista.txtIdRenta.getText()), id_libro, id_cliente, fecha_renta, fecha_entrega, id_emp))
            {
                JOptionPane.showMessageDialog(null, "Renta Modificada con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                vista.tablaRenta.setModel(modelo.cargarDatos());
                limpiarVista();
            }
        }
        if(vista.btnEliminar == e.getSource())
        {
            if(modelo.deleteRenta(Integer.parseInt(vista.txtIdRenta.getText())))
            {
                JOptionPane.showMessageDialog(null, "Renta Eliminada con exito", "Informacion", JOptionPane.INFORMATION_MESSAGE);
                vista.tablaRenta.setModel(modelo.cargarDatos());
            }
        }
        //NO FUNCIONA BIEN
        if(vista.btnBuscar1 == e.getSource())
        {
            int idEmp = Integer.parseInt(vista.txtIdRenta.getText());
            vista.tablaRenta.setModel(modelo.buscarDatos(idEmp));
        }
        
        if(vista.btnRegresar==e.getSource())
        {
            MenuPrincipal obj = new MenuPrincipal();
            ModeloMenuPrincipal modeloMenu = new ModeloMenuPrincipal();
            ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal(modeloMenu,obj);
            ControladorMenuPrincipal.iniciarVista();
            vista.dispose();
        }
        if(vista.btnCancelar== e.getSource())
        {
            limpiarVista();
        }
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
         if(vista.tablaRenta== e.getSource()){
            
            int fila=vista.tablaRenta.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdRenta.setText(String.valueOf(vista.tablaRenta.getValueAt(fila, 0)));
                //stem.out.println(vista.tablaRenta.getValueAt(fila, 1).toString());
                /*
                boolean exists = false;
                for (int index = 0; index < myComboBox.getItemCount() && !exists; index++) {
                  if (item.equals(myComboBox.getItemAt(index)) {
                    exists = true;
                  }
                }
                if (!exists) {
                  myComboBox.addItem(item);
                }
                int                */
                int i=0;
                for(i=0;i<vista.cmbLibros.getItemCount();i++)
                {
                    
                    if((vista.tablaRenta.getValueAt(fila, 1)).equals(vista.cmbLibros.getItemAt(i).toString()))
                    {
                        break;
                    }
                    
                }
                vista.cmbLibros.setSelectedIndex(i);
                i=0;
                for(i=0;i<vista.cmbCliente.getItemCount();i++)
                {
                    
                    if((vista.tablaRenta.getValueAt(fila, 2)).equals(vista.cmbCliente.getItemAt(i).toString()))
                    {
                        break;
                    }
                    
                }
                vista.cmbCliente.setSelectedIndex(i);
                i=0;
                for(i=0;i<vista.cmbEmpleado.getItemCount();i++)
                {
                    
                    if((vista.tablaRenta.getValueAt(fila, 5)).equals(vista.cmbEmpleado.getItemAt(i).toString()))
                    {
                        break;
                    }
                    
                }
                vista.cmbEmpleado.setSelectedIndex(i);
                String[] a = vista.tablaRenta.getValueAt(fila, 3).toString().split("-");
                //año mes día
                //8/06/2018 
                
                Date dia = new Date(Integer.parseInt(a[0]),Integer.parseInt(a[1]),Integer.parseInt(a[2]));
                dia.setYear(dia.getYear()-1900);
                dia.setMonth(dia.getMonth()-1);
              
               System.out.println(dia);
               vista.jDateChooserRenta.setDate(dia);
               
               String[] a2 = vista.tablaRenta.getValueAt(fila, 4).toString().split("-");
               
               Date dia2 = new Date(Integer.parseInt(a2[0]),Integer.parseInt(a2[1]),Integer.parseInt(a2[2]));
               dia2.setYear(dia2.getYear()-1900);
               dia2.setMonth(dia2.getMonth()-1);
               
               vista.jDateChooserEntrega.setDate(dia2);

              
               
               
                
                
                
                
               /* vista.txtDireccion.setText(String.valueOf(vista.empleado.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.empleado.getValueAt(fila, 3)));
                vista.txtCorreo.setText(String.valueOf(vista.empleado.getValueAt(fila, 4)));*/
            }
         }
    }
    public void limpiarVista(){
//        vista.jDateChooserEntrega.setDateFormatString("");
//        vista.jDateChooserRenta.setDateFormatString("");
        vista.jDateChooserEntrega.setDate(null);
        vista.jDateChooserRenta.setDate(null);
        vista.txtIdRenta.setText("");
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
