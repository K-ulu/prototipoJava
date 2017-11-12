/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Docente;

/**
 *
 * @author gerar
 */

//Clase destinada a hacer operaciones frecuentes como calcular fecha, etc
public class Util {
    
    //metodo que regresa un string con la fecha actual ano-mes-dia
    public static String getFecha(){
        Calendar c = new GregorianCalendar();
        String dia, mes, annio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        return (annio+"-"+mes+"-"+dia);
    }
    
    //metodo que te regresa el id basado en un campo dado y el nombre de la tabla
    public static String getId(String campoClave, String campoBusqueda, String valorCampoBusqueda, String tabla) throws SQLException {
        String consulta;
        PreparedStatement pst = null;
        ResultSet rs = null;
       
        String id = null;
        
        try {
            int idUsuario;
            consulta = "SELECT "+campoClave+" FROM "+tabla+" WHERE "+campoBusqueda+" = '"+valorCampoBusqueda+"'";
            pst = Conexion.getConexion().prepareStatement(consulta);
            
            rs = pst.executeQuery();
            while(rs.next()){
                return rs.getString(1);
                
            }
        } catch(SQLException ex){
            
        }
        return id;       
    }
    
    public static int getUltimoId(String campoClave, String tabla){
        int id = -1;
        String consulta;
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {
            consulta = "SELECT MAX("+campoClave+") as "+campoClave+" FROM "+tabla+"";
            Statement sentencia = Conexion.getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            rs = sentencia.executeQuery(consulta);
            if(rs.absolute(1)){
                id = rs.getInt(campoClave);
                return id;                
            }          
        
        } catch(Exception e){
            
        }
        return id;        
    }
    
    public static boolean existe(String campoBusqueda, String valorCampoBusqueda, String tabla){
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
        
        try {
            //verificamos si ya está registrado con el correo proporcionado
            //preparacion de la consulta
            consulta = "select * from "+tabla+" where "+campoBusqueda+" = '"+valorCampoBusqueda+"' ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            
            //verificamos los resultados, si existe alguno regresamos true (indica que hay un registro con esos datos)
            if(rs.absolute(1)){
                return true;
            }
            
        } catch(Exception e){
            System.err.println("Error"+e);
        } finally {
            try {//cerramos las conexiones
                if(Conexion.getConexion() != null) Conexion.cerrarConexion();
                if(pst != null) pst.close();                
            } catch(Exception e){
                System.err.println("Error"+e);
            }
        }   
        
        
        return false;
    }
    
    public static void main(String[] args){
        System.out.println(Util.existe("correo", "lao@test.com", "usuarios"));
        /*try {
            
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
    
    
    
}
