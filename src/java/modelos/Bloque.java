/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import db.Conexion;
<<<<<<< HEAD
=======
import db.Cuenta;
>>>>>>> development
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
<<<<<<< HEAD
 * @author Christopher Paredes
=======
 * @author caro
>>>>>>> development
 */
public class Bloque {
    private Integer idBloque;
    private Integer idMateria;
    private String nombreBloque;
    private String descripcion;
<<<<<<< HEAD
=======
    private Integer unidad;
>>>>>>> development
    
    public Bloque(){
        this.idBloque = null;
        this.idMateria = null;
        this.nombreBloque = null;
        this.descripcion = null;
<<<<<<< HEAD
    }
    
    public Bloque(Integer idBloque, Integer idMateria, String nombreBloque, String descripcion){
=======
        this.unidad = null;
    }
    
    public Bloque(Integer idBloque, Integer idMateria, String nombreBloque, String descripcion, Integer unidad){
>>>>>>> development
        this.idBloque = idBloque;
        this.idMateria = idMateria;
        this.nombreBloque = nombreBloque;
        this.descripcion = descripcion;
<<<<<<< HEAD
=======
        this.unidad = unidad;
>>>>>>> development
    }
    
    public int getIdBloque(){
        return idBloque;
    }
    
    public void setIdBloque(int idBloque){
        this.idBloque = idBloque;
<<<<<<< HEAD
    }
=======
    } 
>>>>>>> development
    
    public int getIdMateria(){
        return idMateria;
    }
    
    public void setIdMateria(int idMateria){
        this.idMateria = idMateria;
<<<<<<< HEAD
    }
=======
    } 
>>>>>>> development
    
    public String getNombreBloque(){
        return nombreBloque;
    }
    
<<<<<<< HEAD
    public void setNombreBloque(String nombreBloque){
        this.nombreBloque= nombreBloque;
    }
=======
    public void setIdMateria(String nombreBloque){
        this.nombreBloque = nombreBloque;
    } 
>>>>>>> development
    
    public String getDescripcion(){
        return descripcion;
    }
    
    public void setDescripcion(String descripcion){
<<<<<<< HEAD
        this.descripcion= descripcion;
    }
    
    /*
    
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    
    */ 
    
    //devuelve TRUE cuando es guardado exitosamente, FALSE cuando paso un 
    public static boolean guardarObjeto(int idMateria, String nombreBloque, String descripcion) {
=======
        this.descripcion = descripcion;
    } 
    
    public int getUnidad(){
        return unidad;
    }
    
    public void setDescripcion(int unidad){
        this.unidad = unidad;
    } 
    
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Integer idBloque, Integer idMateria, String nombreBloque, String descripcion, Integer unidad) {
>>>>>>> development
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
<<<<<<< HEAD
            //caso cuando no existe el grupo, se inserta uno  
            consulta = "insert into bloques (idBloque, idMateria, nombreBloque, descripcion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(idMateria));
            pst.setString(2, nombreBloque);
            pst.setString(3, descripcion);                       
=======
            //caso cuando no existe el grupo, se iserta uno  
            consulta = "insert into bloques (idBloque, idMateria, nombreBloque, descripcion, unidad) values(null,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(idMateria));
            pst.setString(2, nombreBloque);
            pst.setString(3, descripcion);
            pst.setString(4, String.valueOf(unidad));                       
>>>>>>> development
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
<<<<<<< HEAD
    //devuelve TRUE cuando es guardado exitosamente, FALSE cuando paso un 
    public static boolean guardarObjeto(Bloque bloques) {
=======
    //devuelve true cuando es guardado exitosamente, false cuando paso un 
    public static boolean guardarObjeto(Bloque bloque) {
>>>>>>> development
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {           
            //caso cuando no existe el grupo, se iserta uno  
<<<<<<< HEAD
            consulta = "insert into bloques (idMateria, nombreBloque, descripcion) values(null,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(bloques.getIdMateria()));
            pst.setString(2, bloques.getNombreBloque());
            pst.setString(3, bloques.getDescripcion());                     
=======
            consulta = "insert into bloques (idBloque, idMateria, nombreBloque, descripcion, unidad) values(null,?,?,?,?)";
            pst = Conexion.getConexion().prepareStatement(consulta);
            pst.setString(1, String.valueOf(bloque.getIdMateria()));
            pst.setString(2, bloque.getNombreBloque());
            pst.setString(3, bloque.getDescripcion());
            pst.setString(4, String.valueOf(bloque.getUnidad()));                      
>>>>>>> development
            
            //si afecto a algun registro (se inserto correctamente)
            if(pst.executeUpdate() == 1){
                return true; //el true indica que se inserto exitosamente
            }
            
            return false;
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
<<<<<<< HEAD
    public static boolean actualizarObjeto(int idBloque, String nombreBloque, String descripcion) {
=======
    public static boolean actualizarObjeto(int idBloque, int idMateria, String nombreBloque, String descripcion, int unidad) {
>>>>>>> development
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idBloque));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
<<<<<<< HEAD
                consulta = "update bloques set nombreBloque = ?, descripcion = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                
                pst.setString(1, nombreBloque);
                pst.setString(2, descripcion);
                pst.setInt(3, idBloque);
=======
                consulta = "update bloques set idMateria = ?, nombreBloque = ?, descripcion = ?, unidad = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, idMateria);
                pst.setString(2, nombreBloque);
                pst.setString(3, descripcion);
                pst.setInt(4, unidad);
                pst.setInt(5, idBloque);
>>>>>>> development
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }              
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
<<<<<<< HEAD
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(Bloque bloques) {
=======
//devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean actualizarObjeto(Bloque bloque) {
>>>>>>> development
        //variables a usar
        PreparedStatement pst = null;
        ResultSet rs = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
<<<<<<< HEAD
            consulta = "select * from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(bloques.getIdBloque()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update bloques set nombreBloque = ?, descripcion = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setString(1, bloques.getNombreBloque());
                pst.setString(2, bloques.getDescripcion());
                pst.setInt(3, bloques.getIdBloque());
                
=======
            consulta = "select * from bloques where idBloque = ?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(bloque.getIdBloque()));
            //ejecutamos la consulta y guardamos resultados
            rs = pst.executeQuery();
            
            //verificamos los resultados, si existe alguno lo actualizamos con los datos            
            if(rs.absolute(1)){
                //caso cuando no existe el docente, se iserta uno  
                consulta = "update bloques set idMateria = ?, nombreBloque = ?, descripcion = ?, unidad = ? where idBloque = ?";
                pst = Conexion.getConexion().prepareStatement(consulta);
                pst.setInt(1, bloque.getIdMateria());
                pst.setString(2, bloque.getNombreBloque());
                pst.setString(3, bloque.getDescripcion());
                pst.setInt(4, bloque.getUnidad());
                pst.setInt(5, bloque.getIdBloque());
>>>>>>> development
                //si afecto a algun registro (se actualizo correctamente)
                if(pst.executeUpdate() == 1){
                    return true;
                }
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
<<<<<<< HEAD
    
    
    public static void main(String[] args){
        //System.out.println(Bloque.guardarObjeto(1, "Bloque 1", "Descripcion del bloque 1"));
        System.out.println(Bloque.actualizarObjeto(1, "Bloque #1", "Sin descripcion"));
        
=======
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(int idBloque) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idBloque));
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //devuelve true si es actualizado correctamente, false cuando pasa un error al actualizar    
    public static boolean eliminarObjeto(Bloque bloque) {
        //variables a usar
        PreparedStatement pst = null;
        String consulta;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "delete from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(bloque.getIdBloque()));
            //ejecutamos la consulta y guardamos resultados
            //si afecto a algun registro (se elimino correctamente)
            if(pst.executeUpdate() == 1){
                return true;
            }     
            return false; //no se encontró ningun registro para actualizar con esos datos
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public static Bloque obtenerPorId(int idBloque){
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        Bloque bloque = null;
            
        try {   
            //verificamos si ya existe el registro (en caso que exista lo actualizamos, de lo contrario insertamos)
            //preparacion de la consulta
            consulta = "select * from bloques where idBloque = ? ";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idBloque));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();

            while(resultado.next()){
                bloque = new Bloque(resultado.getInt("idBloque"), resultado.getInt("idMateria"), resultado.getString("nombreBloque"), resultado.getString("descripcion"), resultado.getInt("unidad"));
             }
            
        } catch (SQLException ex) {
            Logger.getLogger(Bloque.class.getName()).log(Level.SEVERE, null, ex);
        }
        return bloque;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Bloque> obtenerTodos() throws SQLException {
        List<Bloque> bloque = new ArrayList<>();
        try{
         PreparedStatement consulta = Conexion.getConexion().prepareStatement("select * from bloques");
         ResultSet resultado = consulta.executeQuery();
         while(resultado.next()){
            bloque.add(new Bloque(resultado.getInt("idBloque"), resultado.getInt("idMateria"), resultado.getString("nombreBloque"), resultado.getString("descripcion"), resultado.getInt("unidad")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return bloque;
    }
    
    //nos devuelve una lista con todos los objetos de la tabla
    public static List<Bloque> obtenerTodosID(int idMateria) throws SQLException {
        //variables a usar
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
            
        List<Bloque> bloque = new ArrayList<>();
        try{
            consulta = "select * from bloques where idMateria=?";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idMateria));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
            
         while(resultado.next()){
            bloque.add(new Bloque(resultado.getInt("idBloque"), resultado.getInt("idMateria"), resultado.getString("nombreBloque"), resultado.getString("descripcion"), resultado.getInt("unidad")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return bloque;
>>>>>>> development
    }
}
