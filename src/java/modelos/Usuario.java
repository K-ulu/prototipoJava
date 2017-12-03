/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import db.Conexion;
import db.Util;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerar
 */
public class Usuario {
    private Integer idUsuario;
    private String correo;
    private String contrasenia;
    private String fechaCreacion;
    
    public Usuario(){
        this.idUsuario = null;
        this.correo = null;
        this.contrasenia = null;
        this.fechaCreacion = null;
    }
    
    public Usuario(Integer idUsuario, String correo, String contrasenia, String fechaCreacion){
        this.idUsuario = idUsuario;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.fechaCreacion = fechaCreacion;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia() {
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(String fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(String correo, String contrasenia, String fechaCreacion) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into usuarios (idUsuario, correo, contrasenia, FechaCreacion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, correo);
            pst.setString(2, contrasenia);
            pst.setString(3, fechaCreacion);                      
            
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
    public static boolean guardarObjeto(Usuario usuario) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into usuarios (idUsuario, correo, contrasenia, FechaCreacion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, usuario.getCorreo());
            pst.setString(2, usuario.getContrasenia());
            pst.setString(3, usuario.getFechaCreacion());                      
            
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
    public static boolean actualizarObjeto(Integer idUsuario, String correo, String contrasenia) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos
            //preparacion de la consulta
            consulta = "select * from usuarios where idUsuario = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idUsuario));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update usuarios set correo = ?, contrasenia = ? where idUsuario = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, correo);
                pst.setString(2, contrasenia);
                pst.setString(3, String.valueOf(idUsuario));
                
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
    public static boolean actualizarObjeto(Usuario usuario) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos
            //preparacion de la consulta
            consulta = "select * from usuarios where idUsuario = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(usuario.getIdUsuario()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update usuarios set correo = ?, contrasenia = ? where idUsuario = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, usuario.getCorreo());
                pst.setString(2, usuario.getContrasenia());
                pst.setString(3, String.valueOf(usuario.getIdUsuario()));
                
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
    public static boolean eliminarObjeto(int idUsuario) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from usuarios where idUsuario = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idUsuario));
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
    public static boolean eliminarObjeto(Usuario usuario) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from usuarios where idUsuario = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(usuario.getIdUsuario()));
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
    
    public static Usuario obtenerPorId(int idUsuario){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Usuario usuario = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from usuarios where idUsuario = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idUsuario));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                usuario = new Usuario(resultado.getInt("idUsuario"), resultado.getString("correo"), resultado.getString("contrasenia"), resultado.getString("FechaCreacion"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public static Usuario obtenerPorCorreo(String correo){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Usuario usuario = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from usuarios where correo = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, correo);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                usuario = new Usuario(resultado.getInt("idUsuario"), resultado.getString("correo"), resultado.getString("contrasenia"), resultado.getString("FechaCreacion"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return usuario;
    }
    
    public static String obtenerTipoUsuario(int idUsuario){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Usuario usuario = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from alumnos where idUsuario = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idUsuario);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                return "alumno";
             }
            return "docente";
            
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Usuario> obtenerTodos() throws SQLException {
        List<Usuario> usuarios = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from usuarios");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            usuarios.add(new Usuario(resultado.getInt("idUsuario"), resultado.getString("correo"), resultado.getString("contrasenia"), resultado.getString("FechaCreacion")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return usuarios;
    }
    
    //metodo que se se encarga de crear un usuario y anexarlo a la tabla que corresponda según el tipo
    public static boolean crearUsuarioCompleto(Usuario usuario, Docente docente){
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
        
        try {
            //verificamos si ya está registrado con el correo proporcionado            
            //verificamos los resultados, si existe alguno regresamos false (indica que ya se encuentra registrado ese correo)            
            if(Util.existe("correo", usuario.getCorreo(), "usuarios")){
                return false;
            }
            
            //si no está registrado continuamos con el proceso normal            
            consulta = "insert into usuarios (idUsuario, correo, contrasenia, fechaCreacion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, usuario.getCorreo());
            pst.setString(2, usuario.getContrasenia());
            pst.setString(3, Util.getFecha());
            
            //si afecto a algun registro (se inserto)
            if(pst.executeUpdate() == 1){
                int idUsuario = Util.getUltimoId("idUsuario", "usuarios"); //recuperamos id del ultimo usuario (el que acabamos de insertar)                
                if(Docente.guardarObjeto(docente.getNombreD(), docente.getApPaternoD(), docente.getApMaternoD(), docente.getGeneroD(), docente.getFechaNacimientoD(), idUsuario)){
                    return true;
                }                
                return false;
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
    
    //metodo que se se encarga de crear un usuario y anexarlo a la tabla que corresponda según el tipo
    public static boolean crearUsuarioCompleto(Usuario usuario, Alumno alumno){
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
        
        try {
            //verificamos si ya está registrado con el correo proporcionado            
            //verificamos los resultados, si existe alguno regresamos false (indica que ya se encuentra registrado ese correo)            
            if(Util.existe("correo", usuario.getCorreo(), "usuarios")){
                return false;
            }
            
            //si no está registrado continuamos con el proceso normal            
            consulta = "insert into usuarios (idUsuario, correo, contrasenia, fechaCreacion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, usuario.getCorreo());
            pst.setString(2, usuario.getContrasenia());
            pst.setString(3, Util.getFecha());
            
            //si afecto a algun registro (se inserto)
            if(pst.executeUpdate() == 1){
                int idUsuario = Util.getUltimoId("idUsuario", "usuarios"); //recuperamos id del ultimo usuario (el que acabamos de insertar)                
                if(Alumno.guardarObjeto(alumno.getNombreA(), alumno.getApPaternoA(), alumno.getApMaternoA(), alumno.getGeneroA(), alumno.getFechaNacimientoA(), alumno.getCURP(), null, idUsuario)){
                    return true;
                }                
                return false;
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
        
    
    /*public static void main(String[] args){
        Usuario user = new Usuario(null, "carolina@test.com", "hola2", Util.getFecha());
        Alumno alumno = new Alumno(null, "Carolina", "Javier", "Gonzalez", "Femenino", "1996-04-04", "jsdf", null, null);
        System.out.println(Usuario.crearUsuarioCompleto(user, alumno));
        /*Docente d = new Docente(Util.getUltimoId("idDocente", "docente"), "Carolina", "Javier", "Gonzalez", "femenino", Util.getFecha(), Util.getUltimoId("idUsuario", "usuarios"));
        System.out.println(Usuario.crearUsuarioCompleto(user, d));*/
        //Usuario.guardarObjeto(user);*/
        
        
        
        /*Usuario us;
        us = Usuario.obtenerPorId(20);
        System.out.println(us.getCorreo());*/
        /*try {
            List<Usuario> usuarios = new ArrayList<>();
            usuarios = Usuario.obtenerTodos();
            
            Iterator<Usuario> it = usuarios.iterator();
            while(it.hasNext()){
                System.out.println(it.next().getCorreo());
            }
        } catch(Exception e){
            
        }*/
        
    //}
    
}