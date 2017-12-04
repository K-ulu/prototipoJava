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
 * @author Norma
 */
public class GruposMateria {
    private Integer idGruposMateria;
    private Integer idGrupo;
    private Integer idMateria;
    
    public GruposMateria(){
        this.idGruposMateria = null;
        this.idGrupo = null;
        this.idMateria = null;
    }
    
    public GruposMateria(Integer idGruposMateria, Integer idGrupo, Integer idMateria){
        this.idGruposMateria = idGruposMateria;
        this.idGrupo = idGrupo;
        this.idMateria = idMateria;
    }
    
    public int getidGruposMateria() {
        return idGruposMateria;
    }
    
    public void setidGruposMateria(int idGruposMateria) {
        this.idGruposMateria = idGruposMateria;
    }
    
    public int getidGrupo() {
        return idGrupo;
    }
    
    public void setidGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }
    
    public int getidMateria() {
        return idMateria;
    }
    
    public void setidMateria(int idMateria) {
        this.idMateria = idMateria;
    }
    /*
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    */ 
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Integer idGruposMateria, String idGrupo, Integer idMateria) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
        int idG = Integer.parseInt(idGrupo);
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into grupos_materia (idGruposMateria, idGrupo, idMateria) values(null,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(idG));
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
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(GruposMateria grupMat) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into grupos_materia (idGruposMateria, idGrupo, idMateria) values(null,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(grupMat.getidGrupo()));
            pst.setString(2, String.valueOf(grupMat.getidMateria()));                     
            
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
    public static boolean actualizarObjeto(int idGruposMateria, int idGrupo, int idMateria) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from grupos_materia where idGruposMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGruposMateria));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update grupos_materia set idGrupo = ?, idMateria = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, idGrupo);
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
    public static boolean actualizarObjeto(GruposMateria grupMat) {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from grupos_materia where idGruposMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(grupMat.getidGrupo()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update grupos_materia set idGrupo = ?, idMateria = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, grupMat.getidGrupo());
                pst.setInt(2, grupMat.getidMateria());
                
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
    public static boolean eliminarObjeto(int idGruposMateria) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from grupos_materias where idGruposMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGruposMateria));
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
    public static boolean eliminarObjeto(GruposMateria grup) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from grupos_materias where idGruposMateria = ?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(grup.getidGrupo()));
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
    
    public static GruposMateria obtenerPorId(int idGruposMateria){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        GruposMateria grupos = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from grupos_materia where idGruposMateria = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGruposMateria));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while(resultado.next()){
                grupos = new GruposMateria(resultado.getInt("idGruposMateria"), resultado.getInt("idGrupo"), resultado.getInt("idMateria"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Grupos.class.getName()).log(Level.SEVERE, null, ex);
        }
        return grupos;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<GruposMateria> obtenerTodos() throws SQLException {
        List<GruposMateria> grupos = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from grupos_materias");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            grupos.add(new GruposMateria(resultado.getInt("idGruposMateria"), resultado.getInt("idGrupo"), resultado.getInt("idMateria")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return grupos;
    }
    
    public static String obtenerGrupos(int idMateria, String consulta) throws SQLException {
        PreparedStatement pst = null;
        ResultSet resultado;
        String resul = "";
        
        try{
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idMateria));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            while (resultado.next()){
                resul +=  resultado.getString(1) + " ";
            }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return resul;
    }
    
    public static void main(String[] args){
        String consulta = "select Grupos.letra  from grupos_materia, Grupos where grupos_materia.idMateria=? AND Grupos.idGrupo = grupos_materia.idGrupo";
        String consulta2 = "select grupos_materia.idGruposMateria from grupos_materia, Grupos where grupos_materia.idMateria=? AND Grupos.idGrupo = grupos_materia.idGrupo";

        try {
            System.out.println(obtenerGrupos(3, consulta));
            System.out.println(obtenerGrupos(3, consulta2));
        } catch (SQLException ex) {
            Logger.getLogger(GruposMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}