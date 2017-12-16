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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerar
 */
public class Materia {
    private Integer idMateria;
    private String nombre;
    private String grado;
    private Integer idDocente;
    
    public Materia(){
        this.idMateria = null;
        this.nombre = null;
        this.grado = null;
        this.idDocente = null;
    }
    
    public Materia(Integer idMateria, String nombre, String grado, Integer idDocente){
        this.idMateria = idMateria;
        this.nombre = nombre;
        this.grado = grado;
        this.idDocente = idDocente;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */ 
    
    //devuelve true cuando es guardado exitosamente, false cuando pasa un error
    public static boolean guardarObjeto(String nombre, String grado, int idDocente) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into materia (idMateria, Nombre, Grado, idDocente) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, nombre);
            pst.setString(2, grado);
            pst.setInt(3, idDocente);                      
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true cuando es guardado exitosamente, false cuando pasa un error
    public static boolean guardarObjeto(Materia materia) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into materia (idMateria, Nombre, Grado, idDocente) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, materia.getNombre());
            pst.setString(2, materia.getGrado());
            pst.setInt(3, materia.getIdDocente());                      
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(int idMateria, String nombre, String grado) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from materia where idMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idMateria);
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update materia set Nombre = ?, Grado = ? where idMateria = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, nombre);
                pst.setString(2, grado);
                pst.setInt(3, idMateria);
                
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(Materia materia) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from materia where idMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, materia.getIdMateria());
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update materia set Nombre = ?, Grado = ? where idMateria = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, materia.getNombre());
                pst.setString(2, materia.getGrado());
                pst.setInt(3, materia.getIdMateria());
                
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es eliminado correctamente, false cuando pasa un error al eliminar    
    public static boolean eliminarObjeto(int idMateria) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from materia where idMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idMateria);
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es eliminado correctamente, false cuando pasa un error al eliminar    
    public static boolean eliminarObjeto(Materia materia) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from materia where idMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, materia.getIdMateria());
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Materia obtenerPorId(int idMateria){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Materia materia = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from materia where idMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idMateria);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                materia = new Materia(resultado.getInt("idMateria"), resultado.getString("Nombre"), resultado.getString("Grado"), resultado.getInt("idDocente"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return materia;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Materia> obtenerTodos() throws SQLException {
        List<Materia> materias = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from materia");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            materias.add(new Materia(resultado.getInt("idMateria"), resultado.getString("Nombre"), resultado.getString("Grado"), resultado.getInt("idDocente")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return materias;
    }  
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Materia> obtenerTodosID(int idDocente) throws SQLException {
        List<Materia> materias = new ArrayList<>();
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from materia where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idDocente);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
         while(resultado.next()){
            materias.add(new Materia(resultado.getInt("idMateria"), resultado.getString("Nombre"), resultado.getString("Grado"), resultado.getInt("idDocente")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return materias;
    }  
}
