/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import db.Conexion;
import java.io.InputStream;
import java.sql.Blob;
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
public class ContenidoMultimedia {
    private Integer idContenido;
    private String nombreContenido;
    private String tipoContenido;
    private String fechaCreacionContenido;
    private Blob contenidoMultimedia;
    private Integer idDocente;
    
    public ContenidoMultimedia(){
        this.idContenido = null;
        this.nombreContenido = null;
        this.tipoContenido = null;
        this.fechaCreacionContenido = null;
        this.contenidoMultimedia = null;
        this.idDocente = null;
    }
    
    public ContenidoMultimedia(Integer idContenido, String nombreContenido, String tipoContenido, String fechaCreacionContenido, Blob contenidoMultimedia, Integer idDocente){
        this.idContenido = idContenido;
        this.nombreContenido = nombreContenido;
        this.tipoContenido = tipoContenido;
        this.fechaCreacionContenido = fechaCreacionContenido;
        this.contenidoMultimedia = contenidoMultimedia;
        this.idDocente = idDocente;
    }

    public Integer getIdContenido() {
        return idContenido;
    }

    public void setIdContenido(Integer idContenido) {
        this.idContenido = idContenido;
    }

    public String getNombreContenido() {
        return nombreContenido;
    }

    public void setNombreContenido(String nombreContenido) {
        this.nombreContenido = nombreContenido;
    }

    public String getTipoContenido() {
        return tipoContenido;
    }

    public void setTipoContenido(String tipoContenido) {
        this.tipoContenido = tipoContenido;
    }

    public String getFechaCreacionContenido() {
        return fechaCreacionContenido;
    }

    public void setFechaCreacionContenido(String fechaCreacionContenido) {
        this.fechaCreacionContenido = fechaCreacionContenido;
    }

    public Blob getContenidoMultimedia() {
        return contenidoMultimedia;
    }

    public void setContenidoMultimedia(Blob contenidoMultimedia) {
        this.contenidoMultimedia = contenidoMultimedia;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Integer idContenido, String nombreContenido, String tipoContenido, String fechaCreacionContenido, InputStream contenidoMultimedia, int tamano, Integer idDocente) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into contenidomultimedia (idContenido, nombreContenido, tipoContenido, fechaCreacionContenido, contenidoMultimedia, idDocente) values(null,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, nombreContenido);
            pst.setString(2, tipoContenido);
            pst.setString(3, fechaCreacionContenido);
            pst.setBlob(4, contenidoMultimedia, tamano);
            pst.setInt(5, idDocente);
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(int idContenido) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from contenidomultimedia where idContenido = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idContenido);
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static ContenidoMultimedia obtenerPorId(int idContenido){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        ContenidoMultimedia contenidoMultimedia = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from contenidomultimedia where idContenido = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idContenido));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                contenidoMultimedia = new ContenidoMultimedia(resultado.getInt("idContenido"), resultado.getString("nombreContenido"), resultado.getString("tipoContenido"), resultado.getString("fechaCreacionContenido"), resultado.getBlob("contenidoMultimedia"),  resultado.getInt("idDocente"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return contenidoMultimedia;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<ContenidoMultimedia> obtenerTodos() throws SQLException {
        List<ContenidoMultimedia> contenidos = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from contenidomultimedia");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            contenidos.add(new ContenidoMultimedia(resultado.getInt("idContenido"), resultado.getString("nombreContenido"), resultado.getString("tipoContenido"), resultado.getString("fechaCreacionContenido"), resultado.getBlob("contenidoMultimedia"),  resultado.getInt("idDocente")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return contenidos;
    } 
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<ContenidoMultimedia> obtenerTodosID(int idDocente) throws SQLException {
        List<ContenidoMultimedia> contenidos = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from contenidomultimedia where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idDocente));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
         while(resultado.next()){
            contenidos.add(new ContenidoMultimedia(resultado.getInt("idContenido"), resultado.getString("nombreContenido"), resultado.getString("tipoContenido"), resultado.getString("fechaCreacionContenido"), resultado.getBlob("contenidoMultimedia"),  resultado.getInt("idDocente")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return contenidos;
    } 
}
