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
import javax.swing.JOptionPane;
import modelos.Grupos;

/**
 *
 * @author Norma
 */
public class crudGrupos extends HttpServlet {

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
            Integer idGrupo=0;
            if (request.getParameter("agregar") != null) {
                String nombre = request.getParameter("nombre");
                int tam = nombre.length();
                String nomb = nombre.substring((tam-1),tam);
                Integer idDocente = Integer.parseInt(request.getParameter("idDocente"));
                String grado = request.getParameter("grado");
                String turno = request.getParameter("turno");
                Grupos.insertar(nomb, idDocente,grado,turno);
            }

            else if (request.getParameter("editar") != null) {  
                idGrupo =Integer.parseInt(request.getParameter("idGrup"));
                String nombre = request.getParameter("nombre");
                int tam = nombre.length();
                String nomb = nombre.substring((tam-1),tam);
                //Integer idDocente = Integer.parseInt(request.getParameter("idDocente"));
                String grado = request.getParameter("grado");
                String turno = request.getParameter("turno");
                Grupos.actualizarObjeto(idGrupo, grado, nomb, turno, 0);
            }
            /*
            else if (request.getParameter("eliminar") != null) {
                idGrupo =Integer.parseInt(request.getParameter("idGrupo"));
                Grupos.eliminarObjeto(idGrupo);
            }
            */
            else if(request.getParameter("cancelar") != null){
                out.println("hola");
                response.sendRedirect("maestro-Grupos.jsp");
            }
            else{
                idGrupo = Integer.parseInt(request.getParameter("variable1"));
                out.println("El numero es: "+idGrupo);
                Grupos.eliminarObjeto(idGrupo);
            
            }
        response.sendRedirect("maestro-Grupos.jsp");
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
