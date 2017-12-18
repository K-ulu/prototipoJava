/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Alumno;
import modelos.GruposMateria;
import modelos.alumnos_materia;

/**
 *
 * @author Norma
 */
public class crudGruposMaterias extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                
        try{  
            String idGrupo[] = null;
            Integer idMateria=0;
            if (request.getParameter("agregar") != null) {
                boolean ver = true, verGrupos=true;
                int idG =0;
                idMateria = Integer.parseInt(request.getParameter("variable2"));
                idGrupo = request.getParameterValues("datos");
                
                List<GruposMateria> grupos = new ArrayList<>(); //Aqui obtenemos todos los grupos disponibles
                grupos= GruposMateria.obtenerTodos();//Aqui mandamos a llamar la funcion
                
                for(int i=0; i < idGrupo.length; i++){//Este recorre el arreglo de mis opciones
                    idG = Integer.parseInt(idGrupo[i]);
                    verGrupos = true;
                    for (int g=0; g<grupos.size(); g++){//Este recorre todo el arreglo para ver si el grupo ya fue asociado con la materia
                        if (idG == grupos.get(g).getidGrupo()&& idMateria == grupos.get(g).getidMateria()){
                                out.println("\nRepite datos\n");
                                verGrupos = false;//Si ya fue asociado marca un false
                        }  
                    }
                    if (verGrupos == true){//Si la variable no se modifico se guarda el objeto
                        GruposMateria.guardarObjeto(1, idGrupo[i], idMateria);
                    //Declaramos dos listas
                        List<alumnos_materia> cons = new ArrayList<>();//Este es solo para los relacionados a ese grupo
                        List<alumnos_materia> cons2 = new ArrayList<>();//Esto se usa para controlar repetidos (jala todos los elementos)

                        cons = alumnos_materia.obtenerTodosIDG(idG); //obtenemos todos los registros con el id del grupo

                        cons2 = alumnos_materia.obtenerTodos();//obtenemos todos los registros

                        for (int j=0;j<cons.size();j++){//Ciclo que obtiene todos los registros con ese id de grupo
                            int idAlum = cons.get(j).getIdAlumno();//guardamos el id del alumno
                            int idMat = cons.get(j).getIdMateria();//Guardamos el id de la materia
                            ver = true;//Declaramos la variable como positiva
                            for (int k=0; k<cons2.size(); k++){
                                if (idAlum == cons2.get(k).getIdAlumno() && idMat == cons2.get(k).getIdMateria()){//comparamos para ver si el resgistro ya existe
                                    out.println("\nRepite datos\n");
                                    ver = false;//Si existe la variable cambia a falso
                                }                           
                            }
                            if (ver == true)//si la variable es igual a true se guarda el objeto
                                alumnos_materia.guardarObjeto(j, idAlum, idMat);
                        }
                    }
                }
                
            } 
            else if(request.getParameter("deshacer") != null) {
                String dato = request.getParameter("variable3");//Obtenemos el valor del id del grupo
                dato = dato.trim();//Se eliminan espacios
                int idGrupMateria = Integer.parseInt(dato);//Se castea a entero
                //out.println(idGrupMateria);//Imprimimos el valor
                int grupoID = GruposMateria.obtenerPorId(idGrupMateria).getidGrupo();//Guardamos el id del grupo
                int materia = GruposMateria.obtenerPorId(idGrupMateria).getidMateria();//Guardamos el id de la materia                
                try{
                    List<Alumno> alumno = new ArrayList<>();//Creamos nuestra lista de tipo alumno
                    alumnos_materia alum = null;//creamos nuestro objeto de tipo alumno
                    alumno = Alumno.obtenerPorIdGrupo(grupoID);//obtenemos los registros del alumno quue tenga el id del grupo
                    for (int g=0; g<alumno.size(); g++){//Recorremos los registros
                        int idAlumno = alumno.get(g).getIdAlumno();//Obtenemos el id del alumno
                        alum = alumnos_materia.obtenerPorId(idAlumno, materia);//obtenemos los datos del alumno que tenga el id de alumno especificado y el id de la materia especificada
                        int idAlumnos_materia = alum.getIdAlumnosMateria();//obtenemos la llave primaria del alumno en turno que se almaceno en el objeto alum
                        alumnos_materia.eliminarObjeto(idAlumnos_materia);//El id obtenido se manda a la funcion eliminar objeto para borrar el registro
                    }
                    GruposMateria.eliminarObjeto(idGrupMateria);//Eliminamos el registro que tenga por valor en la variable idGrupMAteria
                }
                catch(Exception e){
                    GruposMateria.eliminarObjeto(idGrupMateria);//En caso de que falle o no exista solo elimina el registro de grupo
                }
            }
            response.sendRedirect("maestro-materias.jsp");
        }
        catch (Exception e){
            out.println("Error "+ e.getCause()+ e.getMessage());
            response.sendRedirect("maestro-materias.jsp");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
