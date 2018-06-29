/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Conexion {
    
    public Connection abrirConexion() throws SQLException{
        Connection con;
        boolean vari=false;
        try {
            //Inicializar la conexión a la base de datos
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            con = DriverManager.getConnection("jdbc:mysql://192.168.0.15:3306/biblioteca", "root", "123456"); //Motor, dirección, puerto, nombre
            System.out.println("Conexión realizada");
            vari=true;
        }catch(SQLException e) {
            System.out.print("No se pudo realizar la conexión\n");
            con = null;
        }
        if(vari=false){
            try {
            //Inicializar la conexión a la base de datos
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            con = DriverManager.getConnection("jdbc:mysql://192.168.0.15:3306/biblioteca", "root", "123456"); //Motor, dirección, puerto, nombre
            System.out.println("Conexión realizada");
            vari=true;
            }catch(SQLException e) {
                System.out.print("No se pudo realizar la conexión\n");
                con = null;
            }
        }else if(vari=false){
            try {
            //Inicializar la conexión a la base de datos
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());

            con = DriverManager.getConnection("jdbc:mysql://192.168.0.15:3306/biblioteca", "root", "123456"); //Motor, dirección, puerto, nombre
            System.out.println("Conexión realizada");
            vari=true;
            }catch(SQLException e) {
                System.out.print("No se pudo realizar la conexión\n");
                con = null;
            }
        }
        return con;
        
        
    }
    
    public void cerrarConexion(Connection con) {
        try {
            if(!con.isClosed()) {
                con.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
