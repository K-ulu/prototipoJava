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
public class Documento {
    private Integer idDocumento;
    private String nombreDocumento;
    private String tipoDocumento;
    private Blob documento;
    private Integer idDocente;
    
    public Documento(){
        this.idDocumento = null;
        this.nombreDocumento = null;
        this.tipoDocumento = null;
        this.documento = null;
        this.idDocente = null;
    }
    
    public Documento(Integer idDocumento, String nombreDocumento, String tipoDocumento, Blob documento, Integer idDocente){
        this.idDocumento = idDocumento;
        this.nombreDocumento = nombreDocumento;
        this.tipoDocumento = tipoDocumento;
        this.documento = documento;
        this.idDocente = idDocente;
    }

    public Integer getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(Integer idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getNombreDocumento() {
        return nombreDocumento;
    }

    public void setNombreDocumento(String nombreDocumento) {
        this.nombreDocumento = nombreDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public Blob getDocumento() {
        return documento;
    }

    public void setDocumento(Blob documento) {
        this.documento = documento;
    }

    public Integer getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(Integer idDocente) {
        this.idDocente = idDocente;
    }
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Integer idDocumento, String nombreDocumento, String tipoDocumento, InputStream documento, int tamano, Integer idDocente) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into documentos (idDocumento, nombreDocumento, tipoDocumento, documento, idDocente) values(null,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, nombreDocumento);
            pst.setString(2, tipoDocumento);
            pst.setBlob(3, documento, tamano);
            pst.setInt(4, idDocente);
            
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
    public static boolean actualizarObjeto(int idDocumento, String nombreDocumento, String tipoDocumento, InputStream documento, int tamano, int idDocente) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos)
            //preparacion de la consulta
            consulta = "select * from documentos where idDocumento = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idDocumento));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update documentos set nombreDocumento = ?, tipoDocumento = ?, documento = ? where idDocumento = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, nombreDocumento);
                pst.setString(2, tipoDocumento);
                pst.setBlob(3, documento, tamano);
                pst.setInt(4, idDocente);
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }              
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(int idDocumento) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from documentos where idDocumento = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idDocumento);
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
    
    public static Documento obtenerPorId(int idDocumento){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Documento documento = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from documentos where idDocumento = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idDocumento));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                documento = new Documento(resultado.getInt("idDocumento"), resultado.getString("nombreDocumento"), resultado.getString("tipoDocumento"), resultado.getBlob("documento"),  resultado.getInt("idDocente"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return documento;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Documento> obtenerTodos() throws SQLException {
        List<Documento> documentos = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from documentos");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            documentos.add(new Documento(resultado.getInt("idDocumento"), resultado.getString("nombreDocumento"), resultado.getString("tipoDocumento"), resultado.getBlob("documento"),  resultado.getInt("idDocente")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return documentos;
    }  
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Documento> obtenerTodosID(int idDocente) throws SQLException {
        List<Documento> documentos = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from documentos where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idDocente));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
         while(resultado.next()){
            documentos.add(new Documento(resultado.getInt("idDocumento"), resultado.getString("nombreDocumento"), resultado.getString("tipoDocumento"), resultado.getBlob("documento"),  resultado.getInt("idDocente")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return documentos;
    }
}
