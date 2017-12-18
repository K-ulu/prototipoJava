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
 * @author Norma
 */
public class alumnos_materia {
    private Integer idAlumnosMateria;
    private Integer idMateria;
    private Integer idAlumno;
    
    public Integer getIdAlumnosMateria() {
        return idAlumnosMateria;
    }

    public void setIdAlumnosMateria(Integer idAlumnosMateria) {
        this.idAlumnosMateria = idAlumnosMateria;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }
    
    public alumnos_materia(Integer idMateria, Integer idAlumno, Integer idAlumnosMateria) {
        this.idAlumnosMateria = idAlumnosMateria;
        this.idMateria = idMateria;
        this.idAlumno = idAlumno;
    }
    
    public alumnos_materia(Integer idAlumno, Integer idMateria) {
       this.idAlumno = idAlumno;
        this.idMateria = idMateria;
    }
    
    public alumnos_materia() {
        this.idAlumnosMateria = null;
        this.idMateria = null;
        this.idAlumno = null;
    }
        
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Integer idAlumnosMateria, Integer idAlumno, Integer idMateria) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into alumnos_materia (idAlumnosMateria, AlumnosidAlumno, MateriaidMateria) values(null,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(idAlumno));
            pst.setString(2, String.valueOf(idMateria));                      
            
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
    public static boolean actualizarObjeto(Integer idAlumnosMateria, Integer idAlumno, Integer idMateria) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from alumnos_materia where idAlumnosMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idAlumnosMateria));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update grupos_materia set AlumnosidAlumno = ?, MateriaidMateria = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, idAlumno);
                pst.setInt(2, idMateria);
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
    public static boolean eliminarObjeto(int idAlumnosMateria) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from alumnos_materia where idAlumnosMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idAlumnosMateria));
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
    
    public static alumnos_materia obtenerPorId(int idAlumnosMateria){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        alumnos_materia alumMateria = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from alumnos_materia where idAlumnosMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idAlumnosMateria));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                alumMateria = new alumnos_materia(resultado.getInt("MateriaidMateria"), resultado.getInt("AlumnosidAlumno"), resultado.getInt("idAlumnosMateria"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumMateria;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<alumnos_materia> obtenerTodos() throws SQLException {
        List<alumnos_materia> alumMateria = new ArrayList<>();
        try{
            PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from alumnos_materia");
            ResultSet resultado = consulta.executeQuery();
            while(resultado.next()){
                alumMateria.add(new alumnos_materia(resultado.getInt("MateriaidMateria"), resultado.getInt("AlumnosidAlumno"), resultado.getInt("idAlumnosMateria")));
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
      return alumMateria;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<alumnos_materia> obtenerTodosIDG(Integer idGrupo) throws SQLException {
        List<alumnos_materia> alumMateria = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        try{
            consulta = "SELECT alumnos.idAlumno, grupos_materia.idMateria from alumnos, grupos_materia where grupos_materia.idGrupo = alumnos.idGrupo and grupos_materia.idGrupo = ?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGrupo));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                alumMateria.add(new alumnos_materia(resultado.getInt("idAlumno"), resultado.getInt("idMateria")));
            }
        }catch(SQLException ex){
            throw new SQLException(ex);
        }
      return alumMateria;
    }
    
    public static alumnos_materia obtenerPorId (int idAlumno, int idMateria) throws SQLException {
        alumnos_materia alumnoMateria=null;
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        try{
         //PreparedStatement consulta = Conexion.getConexion().prepareStatement("select Grupos.letra grupos_materia.idGruposMateria from grupos_materia, Grupos where grupos_materia.idMateria=? AND Grupos.idGrupo = grupos_materia.idGrupo");
            consulta = "SELECT * from alumnos_materia, materia WHERE alumnos_materia.AlumnosidAlumno =? and alumnos_materia.MateriaidMateria= ?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idAlumno);
            pst.setInt(2, idMateria);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                alumnoMateria = new alumnos_materia(resultado.getInt("MateriaidMateria"), resultado.getInt("AlumnosidAlumno"), resultado.getInt("idAlumnosMateria"));
             }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return alumnoMateria;
    }
    
    /*public static void main(String[] args){
        
        try {
            List<alumnos_materia> cons = new ArrayList<>();
            cons = alumnos_materia.obtenerTodosIDG(27);
            
            for (int i=0;i<cons.size();i++)
            {
                System.out.println(cons.get(i).getIdAlumno());
                System.out.println(cons.get(i).getIdMateria());
            }
            
            //System.out.println(cons);
        } catch (SQLException ex) {
            Logger.getLogger(GruposMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
