/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import db.Cuenta;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;

/**
 *
 * @author gerar
 */
public class inicioSesion extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            //recuperamos los datos
            String correo = request.getParameter("correo");
            String contrasena = request.getParameter("contrasena");
            if(!correo.equals("") && !contrasena.equals("")){
                if(Cuenta.autenticacion(correo, contrasena)){
                    HttpSession sesion = request.getSession();
                    int idUsuario = Usuario.obtenerPorCorreo(correo).getIdUsuario();
                    String tipo = Usuario.obtenerTipoUsuario(idUsuario);
                    sesion.setAttribute("idUsuario", idUsuario);
                    sesion.setAttribute("tipoUsuario", tipo);
                    if(tipo.equals("alumno")){
                        response.sendRedirect("alumno-dashboard.jsp");
                    } else {
                        response.sendRedirect("maestro-Grupos.jsp");
                    }                    
                } else {
                    response.sendRedirect("login.jsp?error=Correo y/o clave incorrectas");
                }
            } else {
                response.sendRedirect("login.jsp?error=Correo y/o clave incorrectas");
            }
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
