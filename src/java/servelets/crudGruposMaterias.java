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
                                verGrupos = false;//Si ya fue asociado 
                        }  
                    }
                    if (verGrupos == true){
                        GruposMateria.guardarObjeto(1, idGrupo[i], idMateria);
                    
                        List<alumnos_materia> cons = new ArrayList<>();
                        List<alumnos_materia> cons2 = new ArrayList<>();//Esto se usa para controlar repetidos

                        out.println("idGrupo"+idG);
                        cons = alumnos_materia.obtenerTodosIDG(idG);

                        cons2 = alumnos_materia.obtenerTodos();

                        for (int j=0;j<cons.size();j++){
                            int idAlum = cons.get(j).getIdAlumno();
                            int idMat = cons.get(j).getIdMateria();
                            //out.println("idAlumno"+idAlum);
                            //out.println("idMateria"+idMat);
                            ver = true;
                            for (int k=0; k<cons2.size(); k++){
                                out.println("\n"+cons2.get(k).getIdAlumno()+": "+ idAlum +"\n");
                                out.println("\n"+cons2.get(k).getIdMateria()+": "+ idMat +"\n");
                                                            out.println("**************************\n");
                                if (idAlum == cons2.get(k).getIdAlumno() && idMat == cons2.get(k).getIdMateria()){
                                    out.println("\nRepite datos\n");
                                    ver = false;
                                }                           
                            }
                            if (ver == true)
                                alumnos_materia.guardarObjeto(j, idAlum, idMat);

                        }
                    }
                }
                
            } 
            else if(request.getParameter("deshacer") != null) {
                idMateria = Integer.parseInt(request.getParameter("variable3")); //Obtengo el id del grupo              
                GruposMateria.eliminarObjeto(idMateria);
                List<alumnos_materia> cons = new ArrayList<>();
                cons = alumnos_materia.obtenerTodosIDG(idMateria);
                for (int j=0;j<cons.size();j++){
                    int id = cons.get(j).getIdAlumnosMateria();
                    alumnos_materia.eliminarObjeto(id);
                }
            }
            //response.sendRedirect("maestro-materias.jsp");
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
