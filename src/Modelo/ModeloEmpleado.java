
package Modelo;
import Modelo.*;
import Controlador.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ModeloEmpleado {
    private int vId;
    private String vNom;
    private String vDir;
    private String vTel;
    private int vIdSuc;
    
    private Conexion conexion = new Conexion();
    
    public DefaultTableModel cargarDatos(){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT id_emp, nombre_emp, dirrecion, telefono, correo, id_sucursal FROM empleado where status!=0;");
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
    
    public DefaultTableModel buscarDatos(int idEmpleado){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT id_emp, nombre_emp, dirrecion, telefono, correo, id_sucursal FROM empleado WHERE id_emp = "+idEmpleado+" AND status!=0;");
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
    
    public void agregarSucursal( int vId, String vNom, String vDir, String vTel, String correo, int vIdSuc, int status)
    {
        try
        {
            Connection con = conexion.abrirConexion();
            String query  = "INSERT INTO empleado( id_emp, nombre_emp, dirrecion, telefono, correo, id_sucursal,status) values (?,?,?,?,?,?,?)";
            PreparedStatement preparedStatement = con.prepareStatement(query);
            preparedStatement.setInt(1,vId);
            preparedStatement.setString(2,vNom);
            preparedStatement.setString(3,vDir);
            preparedStatement.setString(4,vTel);
            preparedStatement.setString(5,correo);
            preparedStatement.setInt(6,vIdSuc);
            preparedStatement.setInt(7,1);
            preparedStatement.executeUpdate();
            conexion.cerrarConexion(con);
        }
        catch(SQLException e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public boolean conActualizar( int vId, String vNom, String vDir, String vTel, String correo, int vIdSuc)
    { 
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            System.out.println("UPDATE empleado SET nombre_emp ='"+vNom+"', dirrecion = '"+vDir+"', telefono = '"+vTel+"', correo = '"+correo+ "', id_sucursal = "+vIdSuc+" WHERE id_emp = "+vId+";");
            s.executeUpdate("UPDATE empleado SET nombre_emp ='"+vNom+"', dirrecion = '"+vDir+"', telefono = '"+vTel+"', correo = '"+correo+ "', id_sucursal = "+vIdSuc+" WHERE id_emp = "+vId+";");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
    public boolean conEliminar( int idEmpleado)
    { 
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("delete from empleado where id_emp="+idEmpleado+"") ;
                conexion.cerrarConexion(con);
                return true;
            
        } catch (SQLException e) 
        {
            return false;
        }
        
    }
    
    public void llenarComboClientes(JComboBox<EmpleadoComboBox> comboParto)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT id_emp,nombre_emp FROM empleado ORDER BY nombre_emp");
         while(rs.next())
         {
             comboParto.addItem(new EmpleadoComboBox(rs.getInt("id_emp"),rs.getString("nombre_emp")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class EmpleadoComboBox
    {
        private int id_emp;
        private String nombre_emp;

        public EmpleadoComboBox(int id_emp, String nombre_emp) {
            this.id_emp = id_emp;
            this.nombre_emp = nombre_emp;
        }

        public int getId_emp() {
            return id_emp;
        }

 
        public void setId_emp(int id_emp) {
            this.id_emp = id_emp;
        }

        
        public String getNombre_emp() {
            return nombre_emp;
        }

        
        public void setNombre_emp(String nombre_emp) {
            this.nombre_emp = nombre_emp;
        }
        
        @Override
        public String toString()
        {
            return nombre_emp;
        }
    }

 
}
