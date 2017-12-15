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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Christopher Paredes
 */
public class Grupos {
    private Integer idGrupo;
    private Integer idDocente;
    private String Grado;
    private String letra;
    private String Turno;
    private Integer totalAlumnos;
    
    public Grupos(){
        this.idGrupo = null;
        this.idDocente = null;
        this.Grado = null;
        this.letra = null;
        this.Turno = null;
        this.totalAlumnos = null;
    }
    
    public Grupos(Integer idGrupo, Integer idDocente, String Grado, String letra, String Turno, Integer totalAlumnos){
        this.idGrupo = idGrupo;
        this.idDocente = idDocente;
        this.Grado = Grado;
        this.letra = letra;
        this.Turno = Turno;
        this.totalAlumnos = totalAlumnos;
    }
    
    public int getIdGrupo() {
        return idGrupo;
    }
    
    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    
    public int getIdDocente() {
        return idDocente;
    }
    
    public void setIdDocente(int idDocente) {
        this.idDocente = idDocente;
    }
    
    public String getGrado() {
        return Grado;
    }
    
    public void setGrado(String Grado) {
        this.Grado = Grado;
    }
    
    public String getLetra() {
        return letra;
    }
    
    public void setLetra(String letra) {
        this.letra = letra;
    }
    
    public String getTurno() {
        return Turno;
    }
    
    public void setTurno(String Turno) {
        this.Turno = Turno;
    }
    
    public int getTotalAlumnos() {
        return totalAlumnos;
    }
    
    public void setTotalAlumnos(int totalAlumnos) {
        this.totalAlumnos = totalAlumnos;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */ 
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Integer idGrupo, Integer idDocente, String Grado, String letra, String Turno, int totalAlumnos) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into grupos (idGrupo, idDocente, Grado, letra, Turno, totalAlumnos) values(null,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(idDocente));
            pst.setString(2, Grado);
            pst.setString(3, letra);
            pst.setString(4, Turno);
            pst.setString(5, String.valueOf(totalAlumnos));                       
            
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
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Grupos grupos) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into grupos (idGrupo, idDocente, Grado, letra, Turno, totalAlumnos) values(null,?,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(grupos.getIdDocente()));
            pst.setString(2, grupos.getGrado());
            pst.setString(3, grupos.getLetra());
            pst.setString(4, grupos.getTurno());
            pst.setString(5, String.valueOf(grupos.getTotalAlumnos()));                      
            
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
    public static boolean actualizarObjeto(int idGrupo, String Grado, String letra, String Turno, int totalAlumnos) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from grupos where idGrupo = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGrupo));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update grupos set Grado = ?, letra = ?, Turno = ?, totalAlumnos = ? where idGrupo = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, Grado);
                pst.setString(2, letra);
                pst.setString(3, Turno);
                pst.setInt(4, totalAlumnos);
                pst.setInt(5, idGrupo);
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
    public static boolean actualizarObjeto(Grupos grupos) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from grupos where idGrupo = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(grupos.getIdGrupo()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update grupos set Grado = ?, letra = ?, Turno = ?, totalAlumnos = ? where idGrupo = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, grupos.getGrado());
                pst.setString(2, grupos.getLetra());
                pst.setString(3, grupos.getTurno());
                pst.setInt(4, grupos.getTotalAlumnos());
                pst.setInt(5, grupos.getIdGrupo());
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
    public static boolean eliminarObjeto(int idGrupo) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from grupos where idGrupo = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGrupo));
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
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(Grupos grupos) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from grupos where idGrupo = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(grupos.getIdGrupo()));
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
    
    public static Grupos obtenerPorId(int idGrupo){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Grupos grupos = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from grupos where idGrupo = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGrupo));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                grupos = new Grupos(resultado.getInt("idGrupo"), resultado.getInt("idDocente"), resultado.getString("Grado"), resultado.getString("letra"), resultado.getString("Turno"), resultado.getInt("totalAlumnos"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupos;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Grupos> obtenerTodos() throws SQLException {
        List<Grupos> grupos = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from grupos");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            grupos.add(new Grupos(resultado.getInt("idGrupo"), resultado.getInt("idDocente"), resultado.getString("Grado"), resultado.getString("letra"), resultado.getString("Turno"), resultado.getInt("totalAlumnos")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return grupos;
    }

    public static void insertar(String nombre, Integer idDocente, String grado, String turno){  
        try {
            Grupos grupo = new Grupos(1, idDocente, grado, nombre, turno, 10) ;
            Grupos.guardarObjeto(grupo);
        } catch (Exception ex) {
            Logger.getLogger(Cuenta.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
<<<<<<< HEAD
        
   // public static void main(String[] args){
//        Grupos g = new Grupos(1, 2, "3", "Z", "Vesp", 300);
//        System.out.println(Grupos.actualizarObjeto(g));
    //System.out.println(Grupos.actualizarObjeto(8, "9", "A", "Nocturno", 12));
    
//    Grupos h = new Grupos(1, 1, "5", "X", "Matu", 200);
//    System.out.println(Grupos.guardarObjeto(h));
//        System.out.println(Grupos.guardarObjeto(1, 1, "6", "a", "xxx", 0));

//    Grupos i = new Grupos(7, 5, "6", "d", "asd", 99);
//    System.out.println(Grupos.eliminarObjeto(i));


//    System.out.println(Grupos.eliminarObjeto(5));
    

  //  }
=======
    
    public static String totAlum(int idG){
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        String total = "";
        try {
            consulta = "select COUNT(alumnos.idAlumno) from alumnos where alumnos.idGrupo = ?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idG));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            
            while(resultado.next()){
                total = resultado.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
      return total;
    }
>>>>>>> development
}
