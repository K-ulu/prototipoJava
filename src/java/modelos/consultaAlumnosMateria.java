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
public class consultaAlumnosMateria {
    private Integer AlumnosidAlumno;
    private Integer MateriaidMateria;
    private String Nombre;

    public consultaAlumnosMateria(Integer AlumnosidAlumno, Integer MateriaidMateria, String Nombre) {
        this.AlumnosidAlumno = AlumnosidAlumno;
        this.MateriaidMateria = MateriaidMateria;
        this.Nombre = Nombre;
    }

    public consultaAlumnosMateria() {
        this.AlumnosidAlumno = null;
        this.MateriaidMateria = null;
        this.Nombre = null;
    }

    public Integer getAlumnosidAlumno() {
        return AlumnosidAlumno;
    }

    public void setAlumnosidAlumno(Integer AlumnosidAlumno) {
        this.AlumnosidAlumno = AlumnosidAlumno;
    }

    public Integer getMateriaidMateria() {
        return MateriaidMateria;
    }

    public void setMateriaidMateria(Integer MateriaidMateria) {
        this.MateriaidMateria = MateriaidMateria;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }
    
    public static List<consultaAlumnosMateria> obtenerPorId(int idAlumno) throws SQLException {
        List<consultaAlumnosMateria> cons = new ArrayList<>();
        PreparedStatement pst = null;
        ResultSet resultado;
        String consulta;
        try{
         //PreparedStatement consulta = Conexion.getConexion().prepareStatement("select Grupos.letra grupos_materia.idGruposMateria from grupos_materia, Grupos where grupos_materia.idMateria=? AND Grupos.idGrupo = grupos_materia.idGrupo");
            consulta = "SELECT * from alumnos_materia, materia WHERE alumnos_materia.AlumnosidAlumno =? and alumnos_materia.MateriaidMateria= materia.idMateria";
            pst = Conexion.getConexion().prepareStatement(consulta);
            //asignamos valores
            pst.setInt(1, idAlumno);
            //ejecutamos la consulta y guardamos resultados
            resultado = pst.executeQuery();
         while(resultado.next()){
            cons.add(new consultaAlumnosMateria(resultado.getInt("AlumnosidAlumno"),resultado.getInt("MateriaidMateria"),resultado.getString("Nombre")));
         }
      }catch(SQLException ex){
         throw new SQLException(ex);
      }
      return cons;
    }
          
    /*public static void main(String[] args){
        
        try {
            List<consultaAlumnosMateria> cons = new ArrayList<>();
            cons = consultaAlumnosMateria.obtenerPorId(22);
            
            for (int i=0;i<cons.size();i++)
            {
                System.out.println(cons.get(i).getNombre());
                System.out.println(cons.get(i).getMateriaidMateria());
            }
            
            //System.out.println(cons);
        } catch (SQLException ex) {
            Logger.getLogger(consultaAlumnosMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    */
}
