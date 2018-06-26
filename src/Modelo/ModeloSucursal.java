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
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
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
    
    
}
