
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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
/**
 *
 * @author Dania
 */
public class ControladorCliente implements ActionListener, MouseListener{
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
        vista.btnAgregar.addActionListener(this); //Aqui
        vista.btnEditar1.addActionListener(this);//Aqui
        vista.btnCancelar.addActionListener(this);//Aqui
}
    public void iniciarVista() 
{
    vista.setTitle("Cliente");//Aqui
    vista.pack();//Aqui
    vista.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Aqui
    vista.setLocationRelativeTo(null);//Aqui
    vista.setResizable(false);//Aqui
    vista.setVisible(true);//Aqui
    
}
        
    @Override
    public void actionPerformed(ActionEvent e) {
       if(vista.btnAgregar == e.getSource()) {
            
           if(modelo.agregarCliente(Integer.parseInt(vista.txtIdCliente.getText()), vista.txtNombre.getText(), vista.txtDireccion.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), libros)){
                limpiar();
            } 
                
            
        }
        if(vista.btnEditar1 == e.getSource()) {
            
            if(modelo.modificarCliente(Integer.parseInt(vista.txtIdCliente.getText()), vista.txtNombre.getText(), vista.txtDireccion.getText(), vista.txtTelefono.getText(), vista.txtCorreo.getText(), libros)){
                limpiar();
            }
        }
        if(vista.btnCancelar == e.getSource()) {
//            if(modelo.deleteCliente(Integer.parseInt(vista.txtIdCliente.getText()))) {
//                
//            }
                limpiar();
        }
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
    

    @Override
    public void mouseClicked(MouseEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent ae) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    public void limpiar() {
        vista.txtNombre.setText("");
        vista.txtCorreo.setText("");
        vista.txtDireccion.setText("");
        vista.txtIdCliente.setText("");
        vista.txtTelefono.setText("");
    }
}
