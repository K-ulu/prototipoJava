/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelos.Docente;

/**
 *
 * @author gerar
 */
public class Cuenta {
    
    //metodo que regresa true si el usuario tiene cuenta (iniciar sesion)
    public boolean autenticacion(String correo, String contrasenia){
        PreparedStatement pst = null;
        ResultSet rs = null;
        
        try {   
            //preparacion de la consulta
            String consulta = "select * from usuarios where correo = ? and  contrasenia = ?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, correo);
            pst.setString(2, contrasenia);
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno regresamos true
            if(rs.absolute(1)){
                return true;
            }            
            
        } catch(Exception e){
            System.err.println("Error"+e);
        } finally {
            try {//cerramos las conexiones
                if(Conexion.getConexion() != null) Conexion.cerrarConexion();
                if(pst != null) pst.close();
                if(rs != null) rs.close();
            } catch(Exception e){
                System.err.println("Error"+e);
            }
        }
        return false;
    }
    
    //metodos para registrar cuentas para docente 
    public boolean registrar(String correo, String contrasenia, String nombre, String apPaterno, String apMaterno, String genero, String fechaNacimiento){
        
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
        
        try {
            //verificamos si ya está registrado con el correo proporcionado
            //preparacion de la consulta
            consulta = "select * from usuarios where correo = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, correo);
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno regresamos false (indica que ya se encuentra registrado ese correo)
            if(rs.absolute(1)){
                return false;
            }
            
            //si no está registrado continuamos con el proceso normal            
            consulta = "insert into usuarios (idUsuario, correo, contrasenia, fechaCreacion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, correo);
            pst.setString(2, contrasenia);
            pst.setString(3, Util.getFecha());
            
            //si afecto a algun registro (se inserto)
            if(pst.executeUpdate() == 1){
                int idUsuario;
                consulta = "SELECT MAX(idUsuario) as idUsuario FROM usuarios";
                Statement sentencia = Conexion.getConexion().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
                rs = sentencia.executeQuery(consulta);
                if(rs.absolute(1)){
                    idUsuario = rs.getInt("idUsuario");
                    
                    if(Docente.guardarObjeto(nombre, apPaterno, apMaterno, genero, fechaNacimiento, idUsuario)){
                        return true;
                    }
                    return false;
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
        
        try {
            //System.out.println(Docente.actualizarObjeto("Maria","Perez", "Chavez", "femenino", "1996-08-31", 2));
            Docente docente = new Docente();
            docente = Docente.obtenerPorId(1);
            if(docente != null){
                System.out.println(docente.getNombreD());
            }
            List<Docente> docentes = new ArrayList<>();
            docentes = Docente.obtenerTodos();
            
            Iterator<Docente> it = docentes.iterator();
            while(it.hasNext()){
                System.out.println(it.next().getNombreD());
            }
                
        } catch (Exception ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
    
}
