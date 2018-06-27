
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
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import Vista.VistaSucursal;
import Modelo.ModeloSucursal;

import Vista.MenuPrincipal;
import Modelo.ModeloMenuPrincipal;
import java.util.Calendar;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;

public class ControladorSucursal implements ActionListener, PropertyChangeListener, ChangeListener, MouseListener{
    private ModeloSucursal modelo;
    private VistaSucursal vista;
    
    String datos[][]={};
    String[] camposCliente={"idSuc","nombre","direc", "tel","correo"};
    
    public ControladorSucursal(ModeloSucursal modelo, VistaSucursal vista){
        this.modelo=modelo;
        this.vista=vista;
        this.vista.tablaSuc.addMouseListener(this);
        this.vista.btnEditar.addActionListener(this);
        this.vista.btnEliminar.addActionListener(this);
        this.vista.btnAgregar.addActionListener(this);
        this.vista.btnCancelar.addActionListener(this);
        this.vista.btnRegresar.addActionListener(this);
        this.vista.btnBuscar1.addActionListener(this);
    }
    
    public void iniciarVista(){
        vista.pack();
        vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        vista.setAlwaysOnTop( true );
        vista.setLocationRelativeTo(null);
        vista.setAlwaysOnTop( false );
        vista.setVisible(true);
        vista.setResizable(true);
        vista.setTitle("Sucursal");
        transparenciaButton();
        
        vista.setAlwaysOnTop( false );
        vista.setVisible(true);
        deshabilitarElementos();
    }
    public void deshabilitarElementos()
    {
        vista.btnCancelar.setEnabled(false);
        vista.btnEditar.setEnabled(false);
        /*vista.txtCorreo.setEnabled(false);
        vista.txtNombre.setEnabled(false);
        vista.txtDireccion.setEnabled(false);
        vista.txtTelefono.setEnabled(false);*/
    }
    
    public void habilitarElementos()
    {
        
        vista.txtCorreo.setEnabled(true);
        vista.txtNombre.setEnabled(true);
        vista.txtDireccion.setEnabled(true);
        vista.txtTelefono.setEnabled(true);
    }
    public void deshabilitarElementosCancelar()
    {
        vista.tablaSuc.setEnabled(false);
        vista.btnEditar.setEnabled(false);
        vista.btnBuscar1.setEnabled(true);
        vista.tablaSuc.setModel(modelo.cargarDatos(datos, camposCliente, 0));
        vista.txtCorreo.setText("");
        vista.txtNombre.setText("");
        vista.txtDireccion.setText("");
        vista.txtTelefono.setText("");
        vista.btnCancelar.setEnabled(false);
        vista.btnAgregar.setEnabled(false);
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
        vista.btnEliminar.setOpaque(false);
        vista.btnEliminar.setContentAreaFilled(false);
        vista.btnEliminar.setBorderPainted(false);
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
                    modelo.agregarSucursal(Integer.parseInt(vista.txtIdSucursal.getText()), vista.txtNombre.getText(),vista.txtDireccion.getText(),
                    vista.txtTelefono.getText(),vista.txtCorreo.getText());
                
                    JOptionPane.showMessageDialog(vista, "Registro insertado exitosamente");
                    limpiarVista();
        }                 
        else if(vista.btnCancelar == evento.getSource()){
                limpiarVista();
            
        }   
        else if(vista.btnRegresar == evento.getSource()){
            MenuPrincipal obj = new MenuPrincipal();
            ModeloMenuPrincipal modeloMenu = new ModeloMenuPrincipal();
            //ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal(modeloMenu,obj);
            //ControladorMenuPrincipal.iniciarVista();
            vista.dispose();
        }
        else if(vista.btnBuscar1 == evento.getSource()){ 
            vista.btnCancelar.setEnabled(true);
            int id_sucursal = vista.tablaSuc.setModel(modelo.cargarDatos(datos, camposCliente, id_sucursal));         
            JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
        }
        else if(vista.btnEditar == evento.getSource()){
            
            int idServicio = Integer.parseInt(vista.txtIdSucursal.getText());
            dia = vista.jDateChooserFechaParto.getCalendar().get(Calendar.DAY_OF_MONTH);//sacamos el dia del calandario
            mes = vista.jDateChooserFechaParto.getCalendar().get(Calendar.MONTH) + 1;//sacamos el mes del calendario
            anio = vista.jDateChooserFechaParto.getCalendar().get(Calendar.YEAR);//sacamos el año del calendario
            fechaCompleta = anio+"-"+mes+"-"+dia;//concatenamos año, mes y dia para darle el formato para guardarlo en la base de datos
            horaCompleta = vista.spinnerHora.getValue()+":"+vista.spinnerMin.getValue();

            if(fechaTemporal.equals(fechaCompleta) && vista.spinnerHora.getValue().equals(horaTemporal) && vista.spinnerMin.getValue().equals(minutosTemporal) 
                    && tipoPartoTemporal.equals(vista.txtTipoParto.getText().trim()) && nombreBebeTemporal.equals(vista.txtNombre.getText().trim())
                    && pesoBebeTemporal == Double.parseDouble(vista.txtPeso.getText().trim()))//entra aqui si no ha elejido una fecha
            {
                JOptionPane.showMessageDialog(vista, "Registro modificado exitosamente");
            }
            else if(!modelo.validarParto(fechaCompleta, horaCompleta))//entra aqui si existe una cita el mismo dia, hora y minutos
            {
                modelo.conActualizar(idServicio,fechaCompleta, 
                horaCompleta,vista.txtTipoParto.getText(),vista.txtNombre.getText(),
                Double.parseDouble(vista.txtPeso.getText()));
                
                int idPaciente = vista.comboBoxPaciente.getItemAt(vista.comboBoxPaciente.getSelectedIndex()).getIdPaciente();
                vista.tableParto.setModel(modelo.cargarDatos(datos, camposCliente, idPaciente));
            
            }
            else//si no entra a ninguno se agrega la cita
            {
                 JOptionPane.showMessageDialog(vista, "Ya existe un registro con esa fecha, ponga otra por favor");   
            }
                
            }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
        

}
