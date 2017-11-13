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
public class Docente {
    private Integer idDocente;
    private String nombreD;
    private String apPaternoD;
    private String apMaternoD;
    private String generoD;
    private String fechaNacimientoD;
    private Integer idUsuario;
    
    public Docente(){
        this.idDocente = null;
        this.nombreD = null;
        this.apPaternoD = null;
        this.apMaternoD = null;
        this.generoD = null;
        this.fechaNacimientoD = null;
        this.idUsuario = null;
    }
    
    public Docente(Integer idDocente, String nombreD, String apPaternoD, String apMaternoD, String generoD, String fechaNacimientoD, Integer idUsuario){
        this.idDocente = idDocente;
        this.nombreD = nombreD;
        this.apPaternoD = apPaternoD;
        this.apMaternoD = apMaternoD;
        this.generoD = generoD;
        this.fechaNacimientoD = fechaNacimientoD;
        this.idUsuario = idUsuario;
    }

    public int getIdDocente() {
        return idDocente;
    }

    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }

    public String getNombreD() {
        return nombreD;
    }

    public void setNombreD(String nombreD) {
        this.nombreD = nombreD;
    }

    public String getApPaternoD() {
        return apPaternoD;
    }

    public void setApPaternoD(String apPaternoD) {
        this.apPaternoD = apPaternoD;
    }

    public String getApMaternoD() {
        return apMaternoD;
    }

    public void setApMaternoD(String apMaternoD) {
        this.apMaternoD = apMaternoD;
    }

    public String getGeneroD() {
        return generoD;
    }

    public void setGeneroD(String generoD) {
        this.generoD = generoD;
    }

    public String getFechaNacimientoD() {
        return fechaNacimientoD;
    }

    public void setFechaNacimienro(String fechaNacimienro) {
        this.fechaNacimientoD = fechaNacimienro;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }          



    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */  
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(String nombreD, String apPaternoD, String apMaternoD, String generoD, String fechaNacimientoD, int idUsuario) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into docente (idDocente, nombreD, apPaternoD, apMaternoD, generoD, fechaNacimientoD, idUsuario) values(null,?,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, nombreD);
            pst.setString(2, apPaternoD);
            pst.setString(3, apMaternoD);
            pst.setString(4, generoD);
            pst.setString(5, fechaNacimientoD);
            pst.setString(6, String.valueOf(idUsuario));                        
            
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
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Docente docente) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into docente (idDocente, nombreD, apPaternoD, apMaternoD, generoD, fechaNacimientoD, idUsuario) values(null,?,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, docente.getNombreD());
            pst.setString(2, docente.getApPaternoD());
            pst.setString(3, docente.getApMaternoD());
            pst.setString(4, docente.getGeneroD());
            pst.setString(5, docente.getFechaNacimientoD());
            pst.setString(6, String.valueOf(docente.getIdUsuario()));                        
            
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
    public static boolean actualizarObjeto(int idDocente, String nombreD, String apPaternoD, String apMaternoD, String generoD, String fechaNacimientoD) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from docente where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idDocente));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update docente set nombreD = ?, apPaternoD = ?, apMaternoD = ?, generoD = ?, fechaNacimientoD = ? where idDocente = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, nombreD);
                pst.setString(2, apPaternoD);
                pst.setString(3, apMaternoD);
                pst.setString(4, generoD);
                pst.setString(5, fechaNacimientoD);
                pst.setString(6, String.valueOf(idDocente)); 
                
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
    public static boolean actualizarObjeto(Docente docente) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from docente where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(docente.getIdDocente()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update docente set nombreD = ?, apPaternoD = ?, apMaternoD = ?, generoD = ?, fechaNacimientoD = ? where idDocente = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, docente.getNombreD());
                pst.setString(2, docente.getApPaternoD());
                pst.setString(3, docente.getApMaternoD());
                pst.setString(4, docente.getGeneroD());
                pst.setString(5, docente.getFechaNacimientoD());
                pst.setString(6, String.valueOf(docente.getIdUsuario())); 
                
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
    public static boolean eliminarObjeto(int idDocente) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from docente where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idDocente));
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
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(Docente docente) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from docente where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(docente.getIdDocente()));
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
    
    public static Docente obtenerPorId(int idDocente){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Docente docente = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from docente where idDocente = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idDocente));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                docente = new Docente(resultado.getInt("idDocente"), resultado.getString("nombreD"), resultado.getString("apPaternoD"), resultado.getString("apMaternoD"), resultado.getString("generoD"), resultado.getString("fechaNacimientoD"), resultado.getInt("idUsuario"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return docente;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Docente> obtenerTodos() throws SQLException {
        List<Docente> docentes = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from docente");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            docentes.add(new Docente(resultado.getInt("idDocente"), resultado.getString("nombreD"), resultado.getString("apPaternoD"), resultado.getString("apMaternoD"), resultado.getString("generoD"), resultado.getString("fechaNacimientoD"), resultado.getInt("idUsuario")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return docentes;
    }
    
}
