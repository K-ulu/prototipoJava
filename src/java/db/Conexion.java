/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author gerar
 */
public class Conexion {
    //Configuracion de los datos de la BD
    private static String usuario = "root";
    private static String pass = "";
    private static String host = "localhost";
    private static String nombre_BD = "kulu";
    
    private static Connection con = null;
    
    
    
    public static Connection getConexion(){
        try{
            Class.forName("com.mysql.jdbc.Driver").newInstance( );
            String servidor = "jdbc:mysql://"+host+"/"+nombre_BD;
            con = DriverManager.getConnection(servidor,usuario,pass);
            return con;
        }catch(Exception e){
            e.printStackTrace();
        }
        return con;
    }
    
    public static void cerrarConexion(){
        try {
            con.close();
        } catch (SQLException e){
            System.err.println("Error "+e);
        }
        
    }
    
}
