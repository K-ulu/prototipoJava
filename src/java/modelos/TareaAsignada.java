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
public class TareaAsignada {
    private Integer idTareaAsignada;
    private String nombreTarea;
    private String descripcionT;
    private Integer idBloque;
    private Integer idDocente;
    private Integer idMateria;
    
    public TareaAsignada(){
        this.idTareaAsignada = null;
        this.nombreTarea = null;
        this.descripcionT = null;
        this.idBloque = null;
        this.idMateria = null;
    }
    
    public TareaAsignada(Integer idTareaAsignada, String nombreTarea, String descripcionT, Integer idBloque, Integer idDocente, Integer idMateria){
        this.idTareaAsignada = idTareaAsignada;
        this.nombreTarea = nombreTarea;
        this.descripcionT = descripcionT;
        this.idBloque = idBloque;
        this.idDocente = idDocente;
        this.idMateria = idMateria;
    }

    public Integer getIdTareaAsignada() {
        return idTareaAsignada;
    }

    public void setIdTareaAsignada(Integer idTareaAsignada) {
        this.idTareaAsignada = idTareaAsignada;
    }

    public String getNombreTarea() {
        return nombreTarea;
    }

    public void setNombreTarea(String nombreTarea) {
        this.nombreTarea = nombreTarea;
    }

    public String getDescripcionT() {
        return descripcionT;
    }

    public void setDescripcionT(String descripcionT) {
        this.descripcionT = descripcionT;
    }

    public Integer getIdBloque() {
        return idBloque;
    }

    public void setIdBloque(Integer idBloque) {
        this.idBloque = idBloque;
    }    

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }
    
    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */
    
    //devuelve true cuando es guardado exitosamente, false cuando pasa un error
    public static boolean guardarObjeto(String nombreTarea, String descripcionT, Integer idBloque, Integer idDocente, Integer idMateria) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into tareas_asignadas (idTareaAsignada, nombreTarea, descripcionT, idBloque, idDocente, idMateria) values(null,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, nombreTarea);
            pst.setString(2, descripcionT);
            if (idBloque == null){
                pst.setString(3, null);
            }
            else{
                pst.setString(3, String.valueOf(String.valueOf(idBloque)));
            }
            pst.setInt(4, idDocente);
            pst.setInt(5, idMateria);
            
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
    public static boolean guardarObjeto(TareaAsignada tareaAsignada) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into tareas_asignadas (idTareaAsignada, nombreTarea, descripcionT, idBloque, idDocente, idMateria) values(null,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, tareaAsignada.getNombreTarea());
            pst.setString(2, tareaAsignada.getDescripcionT());
            pst.setInt(3, tareaAsignada.getIdBloque()); 
            pst.setInt(4, tareaAsignada.getIdDocente());
            pst.setInt(5, tareaAsignada.getIdMateria());
            
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
    public static boolean actualizarObjeto(int idTareaAsignada, String nombreTarea, String descripcionT, int idBloque, int idMateria) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from tareas_asignadas where idTareaAsignada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idTareaAsignada);
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update tareas_asignadas set nombreTarea = ?, descripcionT = ?, idBloque = ?, idMateria = ? where idTareaAsignada = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, nombreTarea);
                pst.setString(2, descripcionT);
                pst.setInt(3, idBloque);
                pst.setInt(4, idMateria);
                pst.setInt(5, idTareaAsignada);
                
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
    public static boolean actualizarObjeto(TareaAsignada tareaAsignada) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from tareas_asignadas where idTareaAsignada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, tareaAsignada.getIdTareaAsignada());
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update tareas_asignadas set nombreTarea = ?, descripcionT = ?, idBloque = ?, idMateria = ? where idTareaAsignada = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, tareaAsignada.getNombreTarea());
                pst.setString(2, tareaAsignada.getDescripcionT());
                pst.setInt(3, tareaAsignada.getIdBloque());
                pst.setInt(4, tareaAsignada.getIdMateria());
                pst.setInt(5, tareaAsignada.getIdTareaAsignada());
                
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
    public static boolean eliminarObjeto(int idTareaAsignada) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from tareas_asignadas where idTareaAsignada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idTareaAsignada);
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
    public static boolean eliminarObjeto(TareaAsignada tareaAsignada) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from tareas_asignadas where idTareaAsignada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, tareaAsignada.getIdTareaAsignada());
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
    
    public static TareaAsignada obtenerPorId(int idTareaAsignada){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        TareaAsignada tareaAsignada = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from tareas_asignadas where idTareaAsignada = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idTareaAsignada);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                tareaAsignada = new TareaAsignada(resultado.getInt("idTareaAsignada"), resultado.getString("nombreTarea"), resultado.getString("descripcionT"), resultado.getInt("idBloque"), resultado.getInt("idDocente"), resultado.getInt("idMateria"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return tareaAsignada;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<TareaAsignada> obtenerTodos() throws SQLException {
        List<TareaAsignada> tareasAsignadas = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from tareas_asignadas");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            tareasAsignadas.add(new TareaAsignada(resultado.getInt("idTareaAsignada"), resultado.getString("nombreTarea"), resultado.getString("descripcionT"), resultado.getInt("idBloque"), resultado.getInt("idDocente"), resultado.getInt("idMateria")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return tareasAsignadas;
    }       
}
