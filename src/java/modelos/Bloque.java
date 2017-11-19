/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import db.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christopher Paredes
 */
public class Bloque {
    private Integer idBloque;
    private Integer idMateria;
    private String nombreBloque;
    private String descripcion;
    
    public Bloque(){
        this.idBloque = null;
        this.idMateria = null;
        this.nombreBloque = null;
        this.descripcion = null;
    }
    
    public Bloque(Integer idBloque, Integer idMateria, String nombreBloque, String descripcion){
        this.idBloque = idBloque;
        this.idMateria = idMateria;
        this.nombreBloque = nombreBloque;
        this.descripcion = descripcion;
    }
    
    public int getIdBloque(){
        return idBloque;
    }
    
    public void setIdBloque(int idBloque){
        this.idBloque = idBloque;
    }
    
    public int getIdMateria(){
        return idMateria;
    }
    
    public void setIdMateria(int idMateria){
        this.idMateria = idMateria;
    }
    
    public String getNombreBloque(){
        return nombreBloque;
    }
    
    public void setNombreBloque(String nombreBloque){
        this.nombreBloque= nombreBloque;
    }
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion= descripcion;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */ 
    
    //devuelve TRUE cuando es guardado exitosamente, FALSE cuando paso un 
    public static boolean guardarObjeto(int idMateria, String nombreBloque, String descripcion) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se inserta uno  
            consulta = "insert into bloques (idBloque, idMateria, nombreBloque, descripcion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(idMateria));
            pst.setString(2, nombreBloque);
            pst.setString(3, descripcion);                       
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve TRUE cuando es guardado exitosamente, FALSE cuando paso un 
    public static boolean guardarObjeto(Bloque bloques) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into bloques (idMateria, nombreBloque, descripcion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(bloques.getIdMateria()));
            pst.setString(2, bloques.getNombreBloque());
            pst.setString(3, bloques.getDescripcion());                     
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(int idBloque, String nombreBloque, String descripcion) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idBloque));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update bloques set nombreBloque = ?, descripcion = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                
                pst.setString(1, nombreBloque);
                pst.setString(2, descripcion);
                pst.setInt(3, idBloque);
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }              
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(Bloque bloques) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(bloques.getIdBloque()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update bloques set nombreBloque = ?, descripcion = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, bloques.getNombreBloque());
                pst.setString(2, bloques.getDescripcion());
                pst.setInt(3, bloques.getIdBloque());
                
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    
    
    public static void main(String[] args){
        //System.out.println(Bloque.guardarObjeto(1, "Bloque 1", "Descripcion del bloque 1"));
        System.out.println(Bloque.actualizarObjeto(1, "Bloque #1", "Sin descripcion"));
        
    }
}
