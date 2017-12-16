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
import modelos.Materia;

/**
 *
 * @author Norma
 */
public class crudMaterias extends HttpServlet {

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
            Integer idMateria=0;
            if (request.getParameter("agregar") != null) {
                String nombre = request.getParameter("nombre");
                String grado = request.getParameter("grado");
                Integer idDocente = Integer.parseInt(request.getParameter("idDocente"));
                if (!nombre.isEmpty() || ! nombre.equals(""))
                    Materia.guardarObjeto(nombre,grado,idDocente);
            }

            else if (request.getParameter("editar") != null) {  
                idMateria =Integer.parseInt(request.getParameter("idMat"));
                String nombre = request.getParameter("nombre");
                String grado = request.getParameter("grado");
                String grad = grado.substring((0),1);
                //Integer idDocente = Integer.parseInt(request.getParameter("idDocente"));
                Materia.actualizarObjeto(idMateria, nombre, grad);
            }
            else if(request.getParameter("cancelar") != null){
                response.sendRedirect("maestro-materias.jsp");
            }
            else{
                idMateria = Integer.parseInt(request.getParameter("variable1"));
                out.println("El numero es: "+idMateria);
                Materia.eliminarObjeto(idMateria);
            
            }
        response.sendRedirect("maestro-materias.jsp");
        }
        catch (Exception e){
            out.println("Error");
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
