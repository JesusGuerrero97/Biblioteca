
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

public class ModeloSucursal {
    private int vSucId;
    private String vSucNom;
    private String vSucDir;
    private String vSucTel;
    private String vSucCor;
    
    private Conexion conexion = new Conexion();
    //DefaultTableModel modelo;
    
    public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT id_sucursal, nombre_suc, direccion, telefono, coreo FROM sucursal where status!=0;");
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
    
    public DefaultTableModel buscarDatos(int idSucursal){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT id_sucursal, nombre_suc, direccion, telefono, coreo FROM sucursal WHERE id_sucursal = "+idSucursal+" AND status!=0;");
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
    
    public void agregarSucursal( int vSucId, String vSucNom, String vSucDir, String vSucTel,String vSucCor)
    {
        try
        {
            //Para abrir una conxion a la BD
            Connection con = conexion.abrirConexion();
            //Para Ejecutar la consulta
            //Statement s = con.createStatement();
            //JOptionPane.showMessageDialog(null, vConFecha+"---"+vConHora+"---"+vConTipo+"---"+vConNombre+"---"+vConPeso);
            String query  = "INSERT INTO sucursal( id_sucursal, nombre_suc, direccion, telefono, coreo,status) values (?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,vSucId);
            preparedStatement.setString(2,vSucNom);
            preparedStatement.setString(3,vSucDir);
            preparedStatement.setString(4,vSucTel);
            preparedStatement.setString(5,vSucCor);
            preparedStatement.setInt(6,1);
            preparedStatement.executeUpdate();
            //JOptionPane.showMessageDialog(null, "Registro agregado");
            conexion.cerrarConexion(con);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public boolean conActualizar( int vSucId, String vSucNom, String vSucDir, String vSucTel,String vSucCor)
    {
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            System.out.println("UPDATE sucursal SET nombre_suc ='"+vSucNom+"', direccion = '"+vSucDir+"', telefono = '"+vSucTel+"', coreo = '"+vSucCor+"' WHERE id_sucursal = "+vSucId+";");
            s.executeUpdate("UPDATE sucursal SET nombre_suc ='"+vSucNom+"', direccion = '"+vSucDir+"', telefono = '"+vSucTel+"', coreo = '"+vSucCor+"' WHERE id_sucursal = "+vSucId+";");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
    public boolean conEliminar( int idSucursal)
    { 
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("delete from sucursal where id_sucursal="+idSucursal+";") ;
                conexion.cerrarConexion(con);
                return true;
                    
        } catch (SQLException e) 
        {
            return false;
        }
        
    }
}
