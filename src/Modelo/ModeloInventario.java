/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import javax.swing.table.DefaultTableModel;
import java.sql.Statement;
import java.sql.*;

/**
 *
 * @author Dania
 */
public class ModeloInventario {
    private Conexion conexion = new Conexion();
    
    public int buscarSucursal(String suc){
        int result = -1;
        try{
            Connection con = conexion.abrirConexion();
            if(con!=null){
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery("SELECT id_sucursal FROM sucursal WHERE sucursal.`nombre_suc`='"+suc+"';");
                if(rs.next()){
                    result = rs.getInt("id_sucursal");  
                }
            }
            conexion.cerrarConexion(con);
            return result;
            
        }catch(SQLException e){
            return 0;
        }
    }
    public DefaultTableModel inventarioConsultar(){
        try
       {
         Connection con = conexion.abrirConexion();
         Statement s = con.createStatement();
         DefaultTableModel modelo;
        
         try
        {
          ResultSet rs = s.executeQuery("SELECT libro.id_libro,libro.`nombre`,libro.`autor`,libro.`editorial`,libro.`fecha_pub`,libro.`numpag`,\n" +
          "libro.`edicion`,libro.`genero`,sucursal.`nombre_suc`,libro.`existencia`\n" +
          "FROM libro INNER JOIN sucursal ON libro.`id_sucursal`=sucursal.`id_sucursal`;");
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
