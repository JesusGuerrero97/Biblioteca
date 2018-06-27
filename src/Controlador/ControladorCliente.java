
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
/**
 *
 * @author Dania
 */
public class ControladorCliente implements ActionListener, MouseListener{
    private ModeloCliente modelo;
    private VistaCliente vista;
    
public ControladorCliente(ModeloCliente Modelo, VistaCliente Vista)
{       this.modelo = Modelo;
        this.vista = Vista;
        vistaCliente.btnAgregar.addActionListener(this); //Aqui
        vistaCliente.btnEditar1.addActionListener(this);//Aqui
        vistaCliente.btnCancelar.addActionListener(this);//Aqui
}
    public void iniciarVista() 
{
    vistaCliente.setTitle("Cliente");//Aqui
    vistaCliente.pack();//Aqui
    vistaCliente.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//Aqui
    vistaCliente.setLocationRelativeTo(null);//Aqui
    vistaCliente.setResizable(false);//Aqui
    vistaCliente.setVisible(true);//Aqui
    
}
        
    @Override
    public void actionPerformed(ActionEvent ae) {
       if(vistaCliente.btnAgregar == ae.getSource())//entra aqui si le pico al boton de guardar
        
       // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
           }
    else if(vistaCliente.btnEditar1 == ae.getSource()))//Aqui
    {
        
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
    
}
