
package Controlador;
import Modelo.ModeloLibro;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Vista.VistaCliente;
import Modelo.ModeloCliente;
import Modelo.ModeloMenuPrincipal;
import Vista.MenuPrincipal;
/**
 *
 * @author Dania
 */
public class ControladorCliente implements ActionListener, MouseListener {
    private ModeloCliente modelo;
    private VistaCliente vista;
    private int libros;
    
    public void transparenciaButton(){
        vista.btnAgregar.setOpaque(false);
        vista.btnAgregar.setContentAreaFilled(false);
        vista.btnAgregar.setBorderPainted(false);
        vista.btnRegresar.setOpaque(false);
        vista.btnRegresar.setContentAreaFilled(false);
        vista.btnRegresar.setBorderPainted(false);
    }
public ControladorCliente(ModeloCliente Modelo, VistaCliente Vista)
{       this.modelo = Modelo;
        this.vista = Vista;
        this.vista.clientes.addMouseListener(this);
        vista.btnAgregar.addActionListener(this); //Aqui
        vista.btnEditar1.addActionListener(this);//Aqui
        vista.btnCancelar.addActionListener(this);//Aqui
        vista.btnBuscar.addActionListener(this);
        vista.btnEliminar2.addActionListener(this);
        vista.btnRegresar.addActionListener(this);
}
    public void iniciarVista() 
{
    vista.setTitle("Cliente");//Aqui
    vista.pack();//Aqui
    vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Aqui
    vista.setLocationRelativeTo(null);//Aqui
    vista.setResizable(false);//Aqui
    vista.setVisible(true);//Aqui
    vista.clientes.setModel(modelo.cargarDatos());
    
}
        
    @Override
    public void actionPerformed(ActionEvent e) {
       if(vista.btnAgregar == e.getSource()) {
            
           if(modelo.agregarCliente(Integer.parseInt(vista.txtIdCliente.getText()), vista.txtNombre.getText(), vista.txtDireccion.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), libros)){
                limpiar();
                JOptionPane.showMessageDialog(vista, "Registro insertado exitosamente");
            limpiar();
            vista.clientes.setModel(modelo.cargarDatos());
            } 
                
            
        }
        if(vista.btnEditar1 == e.getSource()) {
            
            if(modelo.modificarCliente(Integer.parseInt(vista.txtIdCliente.getText()), vista.txtNombre.getText(), vista.txtDireccion.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), libros)){
                limpiar();
                JOptionPane.showMessageDialog(vista, "Registro editado exitosamente");
            limpiar();
            vista.clientes.setModel(modelo.cargarDatos());
                
            }
        }
        if(vista.btnCancelar == e.getSource()) {
//            if(modelo.deleteCliente(Integer.parseInt(vista.txtIdCliente.getText()))) {
//                
//            }
                limpiar();
        }
        if(vista.btnEliminar2 == e.getSource()) {
            if(modelo.deleteCliente(Integer.parseInt(vista.txtIdCliente.getText()))) {
                JOptionPane.showMessageDialog(vista, "Registro eliminado exitosamente");
                limpiar();
                vista.clientes.setModel(modelo.cargarDatos());
            }
                limpiar();
        }
        if(vista.btnBuscar == e.getSource()){ 
            vista.btnCancelar.setEnabled(true);
            int id_cliente = Integer.parseInt(vista.txtIdCliente.getText());
            vista.clientes.setModel(modelo.buscarDatos( id_cliente));          
            JOptionPane.showMessageDialog(null, "Registro consultado exitosamente");
        }
        if(vista.btnRegresar == e.getSource()){
            MenuPrincipal obj = new MenuPrincipal();
            ModeloMenuPrincipal modeloMenu = new ModeloMenuPrincipal();
            ControladorMenuPrincipal ControladorMenuPrincipal = new ControladorMenuPrincipal(modeloMenu,obj);
            ControladorMenuPrincipal.iniciarVista();
            vista.dispose();
        }
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
    

    @Override
    public void mouseClicked(MouseEvent e) {
        if(vista.clientes== e.getSource()){
            int fila=vista.clientes.rowAtPoint(e.getPoint());
            if(fila > -1)
            {
                vista.txtIdCliente.setText(String.valueOf(vista.clientes.getValueAt(fila, 0)));
                vista.txtNombre.setText(String.valueOf(vista.clientes.getValueAt(fila, 1)));
                vista.txtDireccion.setText(String.valueOf(vista.clientes.getValueAt(fila, 2)));
                vista.txtTelefono.setText(String.valueOf(vista.clientes.getValueAt(fila, 3)));
                vista.txtCorreo.setText(String.valueOf(vista.clientes.getValueAt(fila, 4)));
            }
        }  //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent ae) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent ae) {
        //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public 
        void mouseEntered(MouseEvent ae) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent ae) {
        //To change body of generated methods, choose Tools | Templates.
    }
    public void limpiar() {
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtDireccion.setText("");
        vista.txtIdCliente.setText("");
        vista.txtTelefono.setText("");
    }
}
