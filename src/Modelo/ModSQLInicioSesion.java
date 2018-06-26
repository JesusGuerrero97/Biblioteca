/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Jesus
 */
public class ModSQLInicioSesion {
    String User;
    String Pass;
    
    private final Conexion conexion = new Conexion();
    
    public int consultarInicio(String user, String pass){
        int id = -1;
        try{
            Connection con = conexion.abrirConexion();
            if(con!=null){
                Statement s = con.createStatement();
                ResultSet rs = s.executeQuery("SELECT empleado_IdEmpleado FROM login where BINARY Usuario='"+user+"' and BINARY Contraseña='"+pass+"'");
                while(rs.next()){
                    id = rs.getInt("empleado_IdEmpleado");  
                }
            }
            conexion.cerrarConexion(con);
            return id;
            
        }catch(SQLException e){
            return -1;
        }
    }
}
