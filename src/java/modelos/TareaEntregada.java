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
 * @author gerar
 */
public class TareaEntregada {
    
    private Integer idTareaEntregada;
    private Integer idTareaAsignada;
    private String nombreArchivo;
    private Integer idAlumno;
    private Integer calificacion;
    
    public TareaEntregada(){
        this.idTareaEntregada = null;
        this.idTareaAsignada = null;
        this.nombreArchivo = null;
        this.idAlumno = null;
        this.calificacion = null;
    }
    
    public TareaEntregada(Integer idTareaEntregada, int idTareaAsignada, String nombreArchivo, int idAlumno, int calificacion){
        this.idTareaEntregada = idTareaEntregada;
        this.idTareaAsignada = idTareaAsignada;
        this.nombreArchivo = nombreArchivo;
        this.idAlumno = idAlumno;
        this.calificacion = calificacion;
    }

    public Integer getIdTareaEntregada() {
        return idTareaEntregada;
    }

    public void setIdTareaEntregada(Integer idTareaEntregada) {
        this.idTareaEntregada = idTareaEntregada;
    }

    public Integer getIdTareaAsignada() {
        return idTareaAsignada;
    }

    public void setIdTareaAsignada(Integer idTareaAsignada) {
        this.idTareaAsignada = idTareaAsignada;
    }

    public String getNombreArchivo() {
        return nombreArchivo;
    }

    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public Integer getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */
    
    //devuelve true cuando es guardado exitosamente, false cuando pasa un error
    public static boolean guardarObjeto(int idTareaAsignada, String nombreArchivo, int idAlumno, Integer calificacion) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into tareas_entregadas (idTareaEntregada, idTareaAsignada, nombreArchivo, idAlumno, calificacion) values(null,?,?,?,null)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setInt(1, idTareaAsignada);
            pst.setString(2, nombreArchivo);
            pst.setInt(3, idAlumno); 
            
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
    public static boolean guardarObjeto(TareaEntregada tareaEntregada) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into tareas_entregadas (idTareaEntregada, idTareaAsignada, nombreArchivo, idAlumno, calificacion) values(null,?,?,?,null)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setInt(1, tareaEntregada.getIdTareaAsignada());
            pst.setString(2, tareaEntregada.getNombreArchivo());
            pst.setInt(3, tareaEntregada.getIdAlumno()); 
            
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
    public static boolean actualizarObjeto(int idTareaEntregada, int calificacion) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from tareas_entregadas where idTareaEntregada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idTareaEntregada);
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update tareas_entregadas set calificacion = ? where idTareaEntregada = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, calificacion);
                pst.setInt(2, idTareaEntregada);
                
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
    public static boolean actualizarObjeto(TareaEntregada tareaEntregada) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from tareas_entregadas where idTareaEntregada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, tareaEntregada.getIdTareaEntregada());
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update tareas_entregadas set calificacion = ? where idTareaEntregada = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, tareaEntregada.getCalificacion());
                pst.setInt(2, tareaEntregada.getIdTareaEntregada());
                
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
    public static boolean eliminarObjeto(int idTareaEntregada) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from tareas_entregadas where idTareaEntregada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idTareaEntregada);
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
    public static boolean eliminarObjeto(TareaEntregada tareaEntregada) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from tareas_entregadas where idTareaEntregada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, tareaEntregada.getIdTareaEntregada());
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
    
    public static TareaEntregada obtenerPorId(int idTareaEntregada){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        TareaEntregada tareaEntregada = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from tareas_entregadas where idTareaEntregada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idTareaEntregada);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                tareaEntregada = new TareaEntregada(resultado.getInt("idTareaEntregada"), resultado.getInt("idTareaAsignada"), resultado.getString("nombreArchivo"), resultado.getInt("idAlumno"), resultado.getInt("calificacion"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tareaEntregada;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<TareaEntregada> obtenerTodos() throws SQLException {
        List<TareaEntregada> tareasEntregadas = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from tareas_entregadas");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            tareasEntregadas.add(new TareaEntregada(resultado.getInt("idTareaEntregada"), resultado.getInt("idTareaAsignada"), resultado.getString("nombreArchivo"), resultado.getInt("idAlumno"), resultado.getInt("calificacion")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return tareasEntregadas;
    }     
    
}
