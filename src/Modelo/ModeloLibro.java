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

public class ModeloLibro {
    private Conexion conexion = new Conexion();
    
    public boolean insertarLibro(int id_libro, String nombre, String autor, String editorial, String fecha_pub, int numpag, String edicion, String genero, int id_sucursal, int existencia){
        try
        {
            Connection con = conexion.abrirConexion();
            Statement s = con.createStatement();
            System.out.println("insert into libro(id_libro, nombre, autor, editorial, fecha_pub, numpag,edicion, genero, id_sucursal, existencia) values("+id_libro+",'"+nombre+"', '"+autor+"', '"+editorial+"', '"+fecha_pub+"', "+numpag+", "+ "'"+edicion+"', '"+genero+"', "+id_sucursal+", "+existencia+");");
            s.executeUpdate("insert into libro(id_libro, nombre, autor, editorial, fecha_pub, numpag,edicion, genero, id_sucursal, existencia) values("+id_libro+",'"+nombre+"', '"+autor+"', '"+editorial+"', '"+fecha_pub+"', "+numpag+", "+ "'"+edicion+"', '"+genero+"', "+id_sucursal+", "+existencia+");");
            //INSERT INTO `biblioteca`.`libro` (`id_libro`, `nombre`, `autor`, `editorial`, `fecha_pub`, `numpag`, `edicion`, `genero`, `id_sucursal`, `existencia`) VALUES ('30', 'porpoe', 'dngf', 'dskygfs', '1998-02-22', '234', 'efds', 'edff', '3', '15');
            
            conexion.cerrarConexion(con);
            return true;
        } catch (SQLException e) {
            return false;
        }
        
    }
    public boolean modificarLibro(int id, String nombre, String autor, String editorial, String fecha_pub, int numpag, String edicion, String genero, int id_sucursal, int existencia){
        try
        {
            Connection con = conexion .abrirConexion();
            Statement s = con.createStatement();
            System.out.println("update libro set nombre = '"+nombre+"', autor = '"+autor+"', editorial = '"+editorial+"', fecha_pub = '"+fecha_pub+"', numpag = '"+numpag+"', edicion = '"+edicion+"', genero = '"+genero+"', id_sucursal  = '"+id_sucursal+"', existencia = '"+existencia+"' where id_libro = "+id+"");
            s.executeUpdate("update biblioteca.libro set nombre = '"+nombre+"', autor = '"+autor+"', editorial = '"+editorial+"', fecha_pub = '"+fecha_pub+"', numpag = '"+numpag+"', edicion = '"+edicion+"', genero = '"+genero+"', id_sucursal  = '"+id_sucursal+"', existencia = '"+existencia+ "' where id_libro = '"+id+"';");

            conexion.cerrarConexion(con);
            return true;
        
        } catch (SQLException e) {
            return false;
        } 
    }
        public boolean deleteLibro(int id){
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
}
