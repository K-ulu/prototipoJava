/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Bloque;

/**
 *
 * @author Norma
 */
public class crudBloques extends HttpServlet {

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
            Integer idMateria= Integer.parseInt(request.getParameter("idMateria"));
            if (request.getParameter("agregar") != null) {
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String unidad = request.getParameter("unidad");
                unidad = unidad.substring((unidad.length()-1),unidad.length());
                int uni = Integer.parseInt(unidad);
                Bloque.guardarObjeto(1, idMateria,nombre,descripcion, uni);
            }

            else if (request.getParameter("editar") != null) {
                Integer idBloq = Integer.parseInt(request.getParameter("idBloque"));
                String nombre = request.getParameter("nombre");
                String descripcion = request.getParameter("descripcion");
                String unidad = request.getParameter("unidad");
                unidad = unidad.substring((unidad.length()-1),unidad.length());
                int uni = Integer.parseInt(unidad);
                Bloque.actualizarObjeto(idBloq,idMateria, nombre, descripcion, uni);
                
            }
            else if(request.getParameter("cancelar") != null){                
                response.sendRedirect("maestro-materias-bloques.jsp?variable="+idMateria);
            }
            else{
                Integer idBloq = Integer.parseInt(request.getParameter("variable1"));//Obtenemos el id del bloque
                Bloque.eliminarObjeto(idBloq);
            }
            out.println(idMateria);
        response.sendRedirect("maestro-materias-bloques.jsp?variable="+idMateria);
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
