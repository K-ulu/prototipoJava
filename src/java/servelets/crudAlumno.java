/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import com.sun.java.swing.plaf.windows.resources.windows;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelos.Alumno;

/**
 *
 * @author Norma
 */
public class crudAlumno extends HttpServlet {

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
            Integer idAlumno=0;
            if (request.getParameter("agregar") != null) {
                String nombreA = request.getParameter("nombre");
                String apPaternoA = request.getParameter("apellidoP");
                String apMaternoA = request.getParameter("apellidoM");
                String generoA = request.getParameter("generoA");
                String anio= request.getParameter("anio");
                String mes= request.getParameter("mes");
                String dia= request.getParameter("dia");
                String fechaNacimientoA = anio + "-" + mes + "-"+dia;
                String CURP = request.getParameter("curp");
                Integer idGrupo =Integer.parseInt(request.getParameter("idGrupo"));
                out.println(apPaternoA);
                Alumno.guardarObjeto(nombreA, apPaternoA, apMaternoA, generoA, fechaNacimientoA, CURP, idGrupo, 3) ; 
            } 
            /*else if (request.getParameter("eliminar") != null) {
                idAlumno =Integer.parseInt(request.getParameter("idAlumno"));
                Alumno.eliminarObjeto(idAlumno);
            }*/
            else if (request.getParameter("editar") != null) {
                idAlumno =Integer.parseInt(request.getParameter("idAlum"));
                String nombreA = request.getParameter("nombre");
                String apPaternoA = request.getParameter("apellidoP");
                String apMaternoA = request.getParameter("apellidoM");
                String generoA = request.getParameter("genero");
                String fechaNacimientoA = request.getParameter("fechaNac");
                String CURP = request.getParameter("curp");
                Integer idGrupo =Integer.parseInt(request.getParameter("idGrupo"));
                Alumno.actualizarObjeto(idAlumno,nombreA, apPaternoA, apMaternoA, generoA, fechaNacimientoA, CURP, idGrupo,3);
            }
            else if(request.getParameter("cancelar") != null){
                response.sendRedirect("maestro-Alumnos.jsp");
            }
            else{
                idAlumno = Integer.parseInt(request.getParameter("variable1"));
                Alumno.eliminarObjeto(idAlumno);
            }
            //response.sendRedirect("maestro-Alumnos.jsp?op=op&idAlumno="+idAlumno);
            response.sendRedirect("maestro-Alumnos.jsp");
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
