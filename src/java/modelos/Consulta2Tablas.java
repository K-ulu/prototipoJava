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
public class Consulta2Tablas {
    private Integer idGMat;
    private String letra;
    
    public Consulta2Tablas(){
        this.idGMat = null;
        this.letra = null;
    }
    
    public Consulta2Tablas(Integer idGMat, String letra){
        this.idGMat = idGMat;
        this.letra = letra;
    }
    
    public int getidGMat() {
        return idGMat;
    }
    
    public void setidGMat(int idGMat) {
        this.idGMat = idGMat;
    }
    
    public String getletra() {
        return letra;
    }
    
    public void setletra(String letra) {
        this.letra = letra;
    }
    
    /*
        MMETODOS PARA OPERACIONES SOBRE LA BD, SON METODOS ESTATICOS (NO ES NECESARIO INSTANCIAR UN OBJETO DE LA CLASE)
    */ 
           
    public static List<Consulta2Tablas> obtenerPorId(int idGruposMateria) throws SQLException {
        List<Consulta2Tablas> cons = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        try{
         //PreparedStatement consulta = Conexion.getConexion().prepareStatement("select Grupos.letra grupos_materia.idGruposMateria from grupos_materia, Grupos where grupos_materia.idMateria=? AND Grupos.idGrupo = grupos_materia.idGrupo");
            consulta = "select Grupos.letra, grupos_materia.idGruposMateria from grupos_materia, Grupos where grupos_materia.idMateria=? AND Grupos.idGrupo = grupos_materia.idGrupo";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setString(1, String.valueOf(idGruposMateria));
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
         while(resultado.next()){
            cons.add(new Consulta2Tablas(resultado.getInt("idGruposMateria"),resultado.getString("letra")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return cons;
    }
          
    /*public static void main(String[] args){
        
        try {
            List<Consulta2Tablas> cons = new ArrayList<>();
            cons = Consulta2Tablas.obtenerPorId(3);
            
            for (int i=0;i<cons.size();i++)
            {
                System.out.println(cons.get(i).getidGMat());
                System.out.println(cons.get(i).getletra());
            }
            
            //System.out.println(cons);
        } catch (SQLException ex) {
            Logger.getLogger(GruposMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/
}
