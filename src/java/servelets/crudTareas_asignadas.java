/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.TareaAsignada;

/**
 *
 * @author Norma
 */
public class crudTareas_asignadas extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
                
        try{  
            //Integer idMateria= Integer.parseInt(request.getParameter("id"));
            if (request.getParameter("agregar") != null) {
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                int materia =Integer.parseInt(request.getParameter("materia"));
                String docenteID = request.getParameter("docenteID");
                docenteID = docenteID.trim();
                int doc = Integer.parseInt(docenteID); 
                int idBloq =Integer.parseInt(request.getParameter("bloque"));                
                String fechaEntrega= request.getParameter("fecha");
                TareaAsignada.guardarObjeto(nombre, descripcion, idBloq, doc, materia,fechaEntrega);
            }

            else if (request.getParameter("editar") != null) {
                out.println("EDITAR");
                int idTarea = Integer.parseInt(request.getParameter("idTar"));
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                int materia =Integer.parseInt(request.getParameter("materia"));
                int idBloc = Integer.parseInt(request.getParameter("IDbloque"));
                String fecha = request.getParameter("fechaE");
                TareaAsignada.actualizarObjeto(idTarea, nombre, descripcion, idBloc, materia, fecha);
            }
            else if(request.getParameter("cancelar") != null){                
                response.sendRedirect("tareas_asignadas.jsp");
            }
            else{
                Integer idTarea = Integer.parseInt(request.getParameter("variable1"));//Obtenemos el id del bloque
                TareaAsignada.eliminarObjeto(idTarea);
            }
            //out.println(idMateria);
            response.sendRedirect("tareas_asignadas.jsp");
        }
        catch (Exception e){
            out.println("Error"+ e.getCause()+ e.getMessage());
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
