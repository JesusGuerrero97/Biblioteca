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
import java.text.SimpleDateFormat;
import java.text.ParseException;
//import javax.swing.JOptionPane;

import Vista.VistaInventario;
import Modelo.ModeloInventario;

import Vista.VistaLibro;
import Modelo.ModeloLibro;
import Controlador.ControladorLibro;
import Modelo.ModeloMenuPrincipal;
import Vista.MenuPrincipal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dania
 */
public class ControladorInventario implements ActionListener, MouseListener{
    private ModeloInventario modelo;
    private VistaInventario vista;
    
    String Id_libro="";
    String nombre="";
    String autor="";
    String editorial="";
    String edicion="";
    String genero="";
    String fecha_pub="";
    String numpag="";
    String nombre_suc="";
    String existencia="";
    Date fecha;
    int suc=0;
    public ControladorInventario(ModeloInventario modelo, VistaInventario vista){
        this.modelo=modelo;
        this.vista=vista;
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnBuscar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnActualizar.addActionListener(this);
        this.vista.jTable1.addMouseListener(this);
        
    }
    
    public void iniciarVista(){
        vista.setTitle("Ventana de inventario");
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
                vista.dispose();

        }
        if(vista.btnEliminar == e.getSource()) {
            ModeloLibro LibroMod = new ModeloLibro();
                VistaLibro LibroVis = new VistaLibro();
           
                ControladorLibro LibroCon = new ControladorLibro(LibroMod,LibroVis); 
            if(LibroMod.deleteLibro(Integer.parseInt(vista.txtNombre1.getText()))) {
                JOptionPane.showMessageDialog(vista, "Registro eliminado exitosamente");
                limpiar();
                vista.jTable1.setModel(modelo.inventarioConsultar());
            }
                limpiar();
        }
        if(vista.btnEditar == e.getSource())
        {
            ModeloLibro LibroMod = new ModeloLibro();
            VistaLibro LibroVis = new VistaLibro();
            LibroVis.txtIdLibro.setText(Id_libro);
            LibroVis.txtNombre.setText(nombre);
            LibroVis.txtAutor.setText(autor);
            LibroVis.txtEditorial.setText(editorial);
            LibroVis.txtEdicion.setText(edicion);
            LibroVis.txtGenero.setText(genero);
            
//            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
//            String fechaBD = df.format(LibroVis.jDateChooserAddLibro.getDate());
            
            fecha = LibroVis.jDateChooserAddLibro.getDate();
            
            LibroVis.jDateChooserAddLibro.setDate(fecha);
            LibroVis.txtNumPag.setText(numpag);
            LibroVis.txtIdSucursal.setText(nombre_suc);
            LibroVis.txtExistencia.setText(existencia);
            
            ControladorLibro LibroCon = new ControladorLibro(LibroMod,LibroVis);
            LibroCon.iniciarVista();
           vista.dispose();
        }
        if(vista.btnRegresar == e.getSource()){
            MenuPrincipal obj = new MenuPrincipal();
            ModeloMenuPrincipal modeloMenu = new ModeloMenuPrincipal();
            ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal(modeloMenu,obj);
            ControladorMenuPrincipal.iniciarVista();
            vista.dispose();
        }
        if(vista.btnBuscar == e.getSource()){ 
            vista.btnCancelar.setEnabled(true);
            int idSucursal = Integer.parseInt(vista.txtNombre1.getText());
            vista.jTable1.setModel(modelo.buscarDatos(idSucursal));          
           // JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
        }
        if(vista.btnCancelar == e.getSource()){
             limpiar();
            vista.jTable1.setModel(modelo.inventarioConsultar());  
               
        } 
        if(vista.btnActualizar == e.getSource()){
             limpiar();
            vista.jTable1.setModel(modelo.inventarioConsultar());  
               
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
                    Id_libro=String.valueOf(vista.jTable1.getValueAt(fila, 0));
                    nombre=String.valueOf(vista.jTable1.getValueAt(fila, 1));
                    autor=String.valueOf(vista.jTable1.getValueAt(fila, 2));
                    editorial=String.valueOf(vista.jTable1.getValueAt(fila, 3));
                    edicion=String.valueOf(vista.jTable1.getValueAt(fila, 6));
                    genero=String.valueOf(vista.jTable1.getValueAt(fila, 7));
                    fecha_pub=String.valueOf(vista.jTable1.getValueAt(fila, 4));
                    numpag=String.valueOf(vista.jTable1.getValueAt(fila, 5));
                    //suc=modelo.buscarDatos(String.valueOf(vista.jTable1.getValueAt(fila, 8)));
                    nombre_suc=String.valueOf(suc);
                    existencia=String.valueOf(vista.jTable1.getValueAt(fila, 9));
                //cboxCiudad.setSelectedItem(ciudad);
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
     public void limpiar(){
        vista.txtNombre1.setText("");
        
    }
}
