/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.table.DefaultTableModel;
import java.util.Date;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
/**
 *
 * @author Dania
 */
public class ModeloRenta {
    
     private Conexion conexion = new Conexion();
     
     public boolean insertarRenta(int id_renta, int id_libro, int id_cliente, String fecha_renta, String fecha_entrega, int id_emp){
     try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            s.executeUpdate("Update cliente set libros_adq=libros_adq+1 WHERE id_cliente="+id_cliente+";");
            //System.out.println("insert into renta_libro(id_renta, id_libro, id_cliente, fecha_renta, fecha_entrega, id_emp) values("+id_renta+","+id_libro+", "+id_cliente+", '"+fecha_renta+"', '"+fecha_entrega+"', '"+id_emp+"');");
            s.executeUpdate("insert into renta_libros(id_renta, id_libro, id_cliente, fecha_renta, fecha_entrega, id_emp) values("+id_renta+","+id_libro+", "+id_cliente+", '"+fecha_renta+"', '"+fecha_entrega+"',"+id_emp+");");
            //INSERT INTO `biblioteca`.`libro` (`id_libro`, `nombre`, `autor`, `editorial`, `fecha_pub`, `numpag`, `edicion`, `genero`, `id_sucursal`, `existencia`) VALUES ('30', 'porpoe', 'dngf', 'dskygfs', '1998-02-22', '234', 'efds', 'edff', '3', '15');
            s.executeUpdate("update libro set existencia=existencia-1 where id_libro="+id_libro+";");
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
    public boolean modificarRenta(int id_renta, int id_libro, int id_cliente, String fecha_renta, String fecha_entrega, int id_emp){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement sel = con.createStatement();
            Statement s = con.createStatement();
            ResultSet rs=sel.executeQuery("SELECT id_libro FROM renta_libros where id_renta="+id_renta+"");
            int old_id_libro=0;
            while(rs.next())
            {
                old_id_libro=rs.getInt("id_libro");
            }
            if(old_id_libro!=id_libro)
            {
                s.executeUpdate("UPDATE libro SET existencia=existencia+1 WHERE id_libro="+old_id_libro+";");
                s.executeUpdate("UPDATE libro SET existencia=existencia-1 WHERE id_libro="+id_libro+";");
            }
                      
            System.out.println("update renta_libros set id_libro = "+id_libro+", id_cliente = "+id_cliente+", fecha_renta = '"+fecha_renta+"', fecha_entrega = '"+fecha_entrega+"', id_emp = "+id_emp+" where id_renta = "+id_renta+"");
            s.executeUpdate("update renta_libros set id_libro = "+id_libro+", id_cliente = "+id_cliente+", fecha_renta = '"+fecha_renta+"', fecha_entrega = '"+fecha_entrega+"', id_emp = "+id_emp+" where id_renta = "+id_renta+";");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
        public boolean deleteRenta(int id_renta)
        {
            try
            {
                Connection con = conexion .abrirConexion();
                Statement sel = con.createStatement();
                Statement s = con.createStatement();
                ResultSet rs=sel.executeQuery("SELECT id_libro,id_cliente FROM renta_libros where id_renta="+id_renta+"");
                int old_id_libro=0;
                int cliente=0;
                while(rs.next())
                {
                    old_id_libro=rs.getInt("id_libro");
                    cliente=rs.getInt("id_cliente");
                }
                sel.executeUpdate("UPDATE libro SET existencia=existencia+1 WHERE id_libro="+old_id_libro+";");
                s.executeUpdate("delete from renta_libros where id_renta = "+id_renta+";");
                s.executeUpdate("Update cliente set libros_adq=libros_adq-1 WHERE id_cliente="+cliente+";");
                conexion.cerrarConexion(con);
                return true;

            } catch (SQLException e) {
                return false;
            } 
        }
    public DefaultTableModel cargarDatos()
    {
       try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
        try
        {
          ResultSet rs = s.executeQuery("SELECT renta_libros.`id_renta`,libro.`nombre`,cliente.`nombre_cli`,renta_libros.`fecha_renta`,renta_libros.`fecha_entrega`,empleado.`nombre_emp`FROM  renta_libros INNER JOIN libro ON renta_libros.`id_libro`=libro.`id_libro` INNER JOIN cliente ON renta_libros.`id_cliente`=cliente.`id_cliente` INNER JOIN empleado ON renta_libros.`id_emp`=empleado.`id_emp`;");
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
          ResultSet rs = s.executeQuery("SELECT * FROM renta_libros WHERE id_renta = "+id+";");
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
    
    public void llenarComboClientes(JComboBox<ClienteComboBox> comboCliente)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT id_cliente,nombre_cli FROM cliente ORDER BY nombre_cli");
         while(rs.next())
         {
             comboCliente.addItem(new ClienteComboBox(rs.getInt("id_cliente"),rs.getString("nombre_cli")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class ClienteComboBox
    {
        private int id_cliente;
        private String nombre_cli;

        public ClienteComboBox(int id_cliente, String nombre_cli) {
            this.id_cliente = id_cliente;
            this.nombre_cli = nombre_cli;
        }

        /**
         * @return the idPaciente
         */
        public int getId_cliente() {
            return id_cliente;
        }

        /**
         * @param idPaciente the idPaciente to set
         */
        public void setId_cliente(int idPaciente) {
            this.id_cliente = idPaciente;
        }

        /**
         * @return the nombrePaciente
         */
        public String getNombre_cli() {
            return nombre_cli;
        }

        /**
         * @param nombrePaciente the nombrePaciente to set
         */
        public void setNombre_cli(String nombrePaciente) {
            this.nombre_cli = nombre_cli;
        }
        
        @Override
        public String toString()
        {
            return nombre_cli;
        }
    }
    
    public void llenarComboLibros(JComboBox<LibroComboBox> comboParto)
    {
        try
        {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         ResultSet rs=s.executeQuery("SELECT id_libro,nombre FROM libro where id_sucursal=1 and existencia!=0 ORDER BY nombre");
         while(rs.next())
         {
             comboParto.addItem(new LibroComboBox(rs.getInt("id_libro"),rs.getString("nombre")));
         }
         conexion.cerrarConexion(con);
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }

    public class LibroComboBox
    {
        private int id_libro;
        private String nombre;

        public LibroComboBox(int id_libro, String  nombre) {
            this.id_libro = id_libro;
            this.nombre = nombre;
        }

        public int getId_libro() {
            return id_libro;
        }

 
        public void setId_libro(int id_libro) {
            this.id_libro = id_libro;
        }

        
        public String getNombre() {
            return nombre;
        }

        
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }
        
        @Override
        public String toString()
        {
            return nombre;
        }
    }
    
    public void llenarComboEmpleado(JComboBox<EmpleadoComboBox> comboParto)
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
