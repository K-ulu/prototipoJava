/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    
    public static void main(String[] args){
        try {
            System.out.println(Util.getId("idUsuario", "correo", "lori@test.com", "usuarios"));
        } catch (SQLException ex) {
            Logger.getLogger(Util.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
}
