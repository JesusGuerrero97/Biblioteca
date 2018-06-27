/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import Modelo.*;
import Controlador.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Dania
 */
public class ModeloSucursal {
    private int vSucId;
    private String vSucNom;
    private String vSucDir;
    private String vSucTel;
    private String vSucCor;
    
    private Conexion conexion = new Conexion();
    DefaultTableModel modelo;
    
    public DefaultTableModel cargarDatos(String[][] datos,String[]cabecera,int id_sucursal)
    {
       try
       {
         Connection con= conexion.abrirConexion();
         Statement s = con.createStatement();
        
        try
        {
          ResultSet rs = s.executeQuery("SELECT id_sucursal, nombre_suc, direccion, telefono, coreo FROM sucursal WHERE id_sucursal = "+id_sucursal+";");
          modelo = new DefaultTableModel(datos,cabecera);
          ResultSetMetaData rsMd = rs.getMetaData();
          int cantidadColumnas = rsMd.getColumnCount();
          while(rs.next())
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
           JOptionPane.showMessageDialog(null, e.getMessage());
       }
       return null;
    }
    
    public void agregarSucursal( int vSucId, String vSucNom, String vSucDir, String vSucTel,String vSucCor)
    {
        try
        {
            //Para abrir una conxion a la BD
            Connection con = conexion.abrirConexion();
            //Para Ejecutar la consulta
            //Statement s = con.createStatement();
            //JOptionPane.showMessageDialog(null, vConFecha+"---"+vConHora+"---"+vConTipo+"---"+vConNombre+"---"+vConPeso);
            String query  = "INSERT INTO sucursal( id_sucursal, nombre_suc, direccion, telefono, coreo) values (?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,vSucId);
            preparedStatement.setString(2,vSucNom);
            preparedStatement.setString(3,vSucDir);
            preparedStatement.setString(4,vSucTel);
            preparedStatement.setString(5,vSucCor);
            preparedStatement.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Registro agregado");
            conexion.cerrarConexion(con);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public void conActualizar( int vSucId, String vSucNom, String vSucDir, String vSucTel,String vSucCor)
    { 
        try
        {
            Connection con = conexion.abrirConexion();
            String query = "UPDATE sucursal SET nombre_suc = ?, direccion = ?, telefono = ?, coreo = ? WHERE id_sucursal = ?";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setString(1,vSucNom);
            preparedStatement.setString(2,vSucDir);
            preparedStatement.setString(3, vSucTel);
            preparedStatement.setString(4, vSucCor);
            int resultado = preparedStatement.executeUpdate();
            
            if(resultado > 0)
            {
                conexion.cerrarConexion(con);
                JOptionPane.showMessageDialog(null, "Registro modificado");
            }
            else
            {
                conexion.cerrarConexion(con);
                JOptionPane.showMessageDialog(null, "Error al modificar el registro");
                
            }
        } catch (SQLException e) 
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
