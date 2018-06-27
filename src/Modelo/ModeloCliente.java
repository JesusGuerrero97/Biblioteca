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
            s.executeUpdate("delete from cliente where id_cliente = "+id+"");
            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
        public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT * FROM cliente;");
          modelo = new DefaultTableModel();
          ResultSetMetaData rsMd = rs.getMetaData();
          int cantidadColumnas = rsMd.getColumnCount();
          for(int i = 1; i <= cantidadColumnas; i++)
          {
            modelo.addColumn(rsMd.getColumnLabel(i));
          }while(rs.next())
          {
              Object[] fila = new Object[cantidadColumnas];
              for(int i = 0; i < cantidadColumnas; i++)
              {
                  fila[i] = rs.getObject(i+1);
              }
              modelo.addRow(fila);
          }return modelo;
        }finally
         {
             conexion.cerrarConexion(con);
         }
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
       return null;
    }
    
    public DefaultTableModel buscarDatos(int id){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT * FROM cliente WHERE id_cliente = "+id+";");
          modelo = new DefaultTableModel();
          ResultSetMetaData rsMd = rs.getMetaData();
          int cantidadColumnas = rsMd.getColumnCount();
          for(int i = 1; i <= cantidadColumnas; i++)
          {
            modelo.addColumn(rsMd.getColumnLabel(i));
          }while(rs.next())
          {
              Object[] fila = new Object[cantidadColumnas];
              for(int i = 0; i < cantidadColumnas; i++)
              {
                  fila[i] = rs.getObject(i+1);
              }
              modelo.addRow(fila);
          }return modelo;
        }finally
         {
             conexion.cerrarConexion(con);
         }
       }catch(SQLException e)
       {
           e.printStackTrace();
       }
       return null;
    }
}
