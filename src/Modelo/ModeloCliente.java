/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
/**
 *
 * @author Dania
 */
public class ModeloCliente {
    private Conexion conexion = new Conexion();
    
     public boolean agregarCliente(int id_cliente, String nombre, String direccion, String telefono, String correo, int librosadq){
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            System.out.println("insert into cliente(id_cliente, nombre_cli, direccion, telefono, correo, libros_adq) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+librosadq+");");
            s.executeUpdate("insert into cliente(id_cliente, nombre_cli, direccion, telefono, correo, libros_adq) values("+id_cliente+",'"+nombre+"', '"+direccion+"', '"+telefono+"', '"+correo+"', "+librosadq+");");
            //INSERT INTO `biblioteca`.`libro` (`id_libro`, `nombre`, `autor`, `editorial`, `fecha_pub`, `numpag`, `edicion`, `genero`, `id_sucursal`, `existencia`) VALUES ('30', 'porpoe', 'dngf', 'dskygfs', '1998-02-22', '234', 'efds', 'edff', '3', '15');
            
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
     public boolean modificarCliente(int id, String nombre, String direccion, String telefono, String correo, int librosadq){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            System.out.println("update libro set nombre_cli = '"+nombre+"', direccion = '"+direccion+"', telefono = '"+telefono+"', correo = '"+correo+"', libros_adq = '"+librosadq+ "' where id_cliente = "+id+"");
            s.executeUpdate("update biblioteca.cliente set nombre_cli = '"+nombre+"', direccion = '"+direccion+"', telefono = '"+telefono+"', correo = '"+correo+"', libros_adq = '"+librosadq+ "' where id_cliente = '"+id+"';");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
        public boolean deleteCliente(int id){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("delete from libro where id_libro = "+id+"");
            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
        public DefaultTableModel usuariosModel() {
        DefaultTableModel modelo = new DefaultTableModel();
        try {
            Connection connection = conexion.abrirConexion();
            try {
                Statement st = connection.createStatement();
                ResultSet rS = st.executeQuery("SELECT * FROM alumnos");
                ResultSetMetaData rsMd = rS.getMetaData();
                modelo.addColumn("Id");
                modelo.addColumn("Nombre");
                modelo.addColumn("Direccion");
                modelo.addColumn("Telefono");
                modelo.addColumn("Correo");
                modelo.addColumn("Libros Adquiridos");
                
                
                while(rS.next()) {
                    Object fila[] = new Object[rsMd.getColumnCount()];
                    for(int i=0; i<rsMd.getColumnCount(); i++) {
                        fila[i] = rS.getObject(rsMd.getColumnName(i + 1));
                    }
                    modelo.addRow(fila);
                }
            }finally {
                conexion.cerrarConexion(connection);
            }
        } catch (SQLException ex) {
            modelo = null;
        }
        return modelo;
    }
}
