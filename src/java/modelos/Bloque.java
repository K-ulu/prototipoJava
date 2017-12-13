/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import db.Conexion;
import db.Cuenta;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caro
 */
public class Bloque {
    private Integer idBloque;
    private Integer idMateria;
    private String nombreBloque;
    private String descripcion;
    private Integer unidad;
    
    public Bloque(){
        this.idBloque = null;
        this.idMateria = null;
        this.nombreBloque = null;
        this.descripcion = null;
        this.unidad = null;
    }
    
    public Bloque(Integer idBloque, Integer idMateria, String nombreBloque, String descripcion, Integer unidad){
        this.idBloque = idBloque;
        this.idMateria = idMateria;
        this.nombreBloque = nombreBloque;
        this.descripcion = descripcion;
        this.unidad = unidad;
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
    
    public void setIdMateria(String nombreBloque){
        this.nombreBloque = nombreBloque;
    } 
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
        this.descripcion = descripcion;
    } 
    
    public int getUnidad(){
        return unidad;
    }
    
    public void setDescripcion(int unidad){
        this.unidad = unidad;
    } 
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Integer idBloque, Integer idMateria, String nombreBloque, String descripcion, Integer unidad) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into bloques (idBloque, idMateria, nombreBloque, descripcion, unidad) values(null,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(idMateria));
            pst.setString(2, nombreBloque);
            pst.setString(3, descripcion);
            pst.setString(4, String.valueOf(unidad));                       
            
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
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Bloque bloque) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into bloques (idBloque, idMateria, nombreBloque, descripcion, unidad) values(null,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(bloque.getIdMateria()));
            pst.setString(2, bloque.getNombreBloque());
            pst.setString(3, bloque.getDescripcion());
            pst.setString(4, String.valueOf(bloque.getUnidad()));                      
            
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
    public static boolean actualizarObjeto(int idBloque, int idMateria, String nombreBloque, String descripcion, int unidad) {
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
                consulta = "update bloques set idMateria = ?, nombreBloque = ?, descripcion = ?, unidad = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, idMateria);
                pst.setString(2, nombreBloque);
                pst.setString(3, descripcion);
                pst.setInt(4, unidad);
                pst.setInt(5, idBloque);
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
    public static boolean actualizarObjeto(Bloque bloque) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from bloques where idBloque = ?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(bloque.getIdBloque()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update bloques set idMateria = ?, nombreBloque = ?, descripcion = ?, unidad = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, bloque.getIdMateria());
                pst.setString(2, bloque.getNombreBloque());
                pst.setString(3, bloque.getDescripcion());
                pst.setInt(4, bloque.getUnidad());
                pst.setInt(5, bloque.getIdBloque());
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
    public static boolean eliminarObjeto(int idBloque) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idBloque));
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(Bloque bloque) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(bloque.getIdBloque()));
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Bloque obtenerPorId(int idBloque){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Bloque bloque = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idBloque));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();

            while(resultado.next()){
                bloque = new Bloque(resultado.getInt("idBloque"), resultado.getInt("idMateria"), resultado.getString("nombreBloque"), resultado.getString("descripcion"), resultado.getInt("unidad"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bloque;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Bloque> obtenerTodos() throws SQLException {
        List<Bloque> bloque = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from bloques");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            bloque.add(new Bloque(resultado.getInt("idBloque"), resultado.getInt("idMateria"), resultado.getString("nombreBloque"), resultado.getString("descripcion"), resultado.getInt("unidad")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return bloque;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Bloque> obtenerTodosID(int idMateria) throws SQLException {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
            
        List<Bloque> bloque = new ArrayList<>();
        try{
            consulta = "select * from bloques where idMateria=?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idMateria));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            
         while(resultado.next()){
            bloque.add(new Bloque(resultado.getInt("idBloque"), resultado.getInt("idMateria"), resultado.getString("nombreBloque"), resultado.getString("descripcion"), resultado.getInt("unidad")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return bloque;
    }
}
