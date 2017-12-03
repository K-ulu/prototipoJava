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
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author gerar
 */
public class Alumno {
    private Integer idAlumno;
    private String nombreA;
    private String apPaternoA;
    private String apMaternoA;
    private String generoA;
    private String fechaNacimientoA;
    private String CURP;
    private Integer idGrupo;
    private Integer idUsuario;
    
    public Alumno(){
        this.idAlumno = null;
        this.nombreA = null;
        this.apPaternoA = null;
        this.apMaternoA = null;
        this.generoA = null;
        this.fechaNacimientoA = null;
        this.CURP = null;
        this.idGrupo = null;
        this.idUsuario = null;
    }
    
    public Alumno(Integer idAlumno, String nombreA, String apPaternoA, String apMaternoA, String generoA, String fechaNacimientoA, String CURP, Integer idGrupo, Integer idUsuario){
        this.idAlumno = idAlumno;
        this.nombreA = nombreA;
        this.apPaternoA = apPaternoA;
        this.apMaternoA = apMaternoA;
        this.generoA = generoA;
        this.fechaNacimientoA = fechaNacimientoA;
        this.CURP = CURP;
        this.idGrupo = idGrupo;
        this.idUsuario = idUsuario;
    }

    public Integer getIdAlumno() {
        return idAlumno;
    }

    public void setIdAlumno(Integer idAlumno) {
        this.idAlumno = idAlumno;
    }

    public String getNombreA() {
        return nombreA;
    }

    public void setNombreA(String nombreA) {
        this.nombreA = nombreA;
    }

    public String getApPaternoA() {
        return apPaternoA;
    }

    public void setApPaternoA(String apPaternoA) {
        this.apPaternoA = apPaternoA;
    }

    public String getApMaternoA() {
        return apMaternoA;
    }

    public void setApMaternoA(String apMaternoA) {
        this.apMaternoA = apMaternoA;
    }

    public String getGeneroA() {
        return generoA;
    }

    public void setGeneroA(String generoA) {
        this.generoA = generoA;
    }

    public String getFechaNacimientoA() {
        return fechaNacimientoA;
    }

    public void setFechaNacimientoA(String fechaNacimientoA) {
        this.fechaNacimientoA = fechaNacimientoA;
    }

    public String getCURP() {
        return CURP;
    }

    public void setCURP(String CURP) {
        this.CURP = CURP;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.idGrupo = idGrupo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */  
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(String nombreA, String apPaternoA, String apMaternoA, String generoA, String fechaNacimientoA, String CURP, Integer idGrupo, Integer idUsuario) {
        System.out.println("Aqui entro");
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el alumno, se iserta uno  
            consulta = "insert into alumnos (idAlumno, nombreA, apPaternoA, apMaternoA, generoA, fechaNacimientoA, CURP, idGrupo, idUsuario) values(null,?,?,?,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, nombreA);
            pst.setString(2, apPaternoA);
            pst.setString(3, apMaternoA);
            pst.setString(4, generoA);
            pst.setString(5, fechaNacimientoA);
            pst.setString(6, CURP);
            if (idGrupo == null){
                pst.setString(7, null);
            }
            else{
                pst.setString(7, String.valueOf(String.valueOf(idGrupo)));
            }
            pst.setString(8, String.valueOf(idUsuario));                        
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Alumno alumno) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el docente, se iserta uno  
            consulta = "insert into alumnos (idAlumno, nombreA, apPaternoA, apMaternoA, generoA, fechaNacimientoA, CURP, idGrupo, idUsuario) values(null,?,?,?,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, alumno.getNombreA());
            pst.setString(2, alumno.getApPaternoA());
            pst.setString(3, alumno.getApMaternoA());
            pst.setString(4, alumno.getGeneroA());
            pst.setString(5, alumno.getFechaNacimientoA());
            pst.setString(6, alumno.getCURP());
            if (alumno.getIdGrupo() == null){
                pst.setString(7, null);
            }
            else{
                pst.setString(7, String.valueOf(alumno.getIdGrupo()));
            }
            pst.setString(8, String.valueOf(alumno.getIdUsuario()));                        
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(Integer idAlumno, String nombreA, String apPaternoA, String apMaternoA, String generoA, String fechaNacimientoA, String CURP, Integer idGrupo, Integer idUsuario) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from alumnos where idAlumno = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idAlumno));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update alumnos set nombreA = ?, apPaternoA = ?, apMaternoA = ?, generoA = ?, fechaNacimientoA = ?, CURP = ?, idGrupo = ? where idAlumno = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, nombreA);
                pst.setString(2, apPaternoA);
                pst.setString(3, apMaternoA);
                pst.setString(4, generoA);
                pst.setString(5, fechaNacimientoA);
                pst.setString(6, CURP);
                pst.setString(7, String.valueOf(idGrupo));
                pst.setString(8, String.valueOf(idAlumno)); 
                
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(Alumno alumno) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from alumnos where idAlumno = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(alumno.getIdAlumno()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update alumnos set nombreA = ?, apPaternoA = ?, apMaternoA = ?, generoA = ?, fechaNacimientoA = ?, CURP = ?, idGrupo = ? where idDocente = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, alumno.getNombreA());
                pst.setString(2, alumno.getApPaternoA());
                pst.setString(3, alumno.getApMaternoA());
                pst.setString(4, alumno.getGeneroA());
                pst.setString(5, alumno.getFechaNacimientoA());
                pst.setString(6, alumno.getCURP());
                pst.setString(7, String.valueOf(alumno.getIdGrupo()));
                pst.setString(8, String.valueOf(alumno.getIdUsuario())); 
                
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(int idAlumno) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from alumnos where idAlumno = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idAlumno));
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(Alumno alumno) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from alumnos where idAlumno = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(alumno.getIdAlumno()));
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Alumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Alumno obtenerPorId(int idAlumno){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Alumno alumno = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from alumnos where idAlumno = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idAlumno));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                alumno = new Alumno(resultado.getInt("idAlumno"), resultado.getString("nombreA"), resultado.getString("apPaternoA"), resultado.getString("apMaternoA"), resultado.getString("generoA"), resultado.getString("fechaNacimientoA"),resultado.getString("CURP"),resultado.getInt("idGrupo"), resultado.getInt("idUsuario"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
    }
    
    public static Alumno obtenerPorIdUsuario(int idUsuario){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Alumno alumno = null;
            
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
                alumno = new Alumno(resultado.getInt("idAlumno"), resultado.getString("nombreA"), resultado.getString("apPaternoA"), resultado.getString("apMaternoA"), resultado.getString("generoA"), resultado.getString("fechaNacimientoA"),resultado.getString("CURP"),resultado.getInt("idGrupo"), resultado.getInt("idUsuario"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Docente.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alumno;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Alumno> obtenerTodos() throws SQLException {
        List<Alumno> alumnos = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from Alumnos");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            alumnos.add(new Alumno(resultado.getInt("idAlumno"), resultado.getString("nombreA"), resultado.getString("apPaternoA"), resultado.getString("apMaternoA"), resultado.getString("generoA"), resultado.getString("fechaNacimientoA"),resultado.getString("CURP"),resultado.getInt("idGrupo"), resultado.getInt("idUsuario")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return alumnos;
    }
    
    public static String imo(){
        return "hola";
    }
}
