/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servelets;

import db.Util;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Docente;
import modelos.Usuario;

/**
 *
 * @author gerar
 */
public class registroMaestro extends HttpServlet {

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
            List<String> errores = new ArrayList<String>();
            
            //recogemos los valores 
            String nombre = request.getParameter("nombre");
            String apellidoP = request.getParameter("apellidoP");
            String apellidoM = request.getParameter("apellidoM");
            String correo = request.getParameter("correo");
            String correo2 = request.getParameter("correo2");
            String genero = request.getParameter("genero");
            String fechaNac = request.getParameter("fecha");
            String contrasena = request.getParameter("contrasena");
            String contrasena2 = request.getParameter("contrasena2");
            
            if(nombre.equals("")){
                errores.add("Proporcione un nombre");
            }
            if(apellidoP.equals("")){
                errores.add("Proporcione apellido paterno");
            }
            if(!correo.equals("") && !correo2.equals("")){
                if(!correo.equals(correo2)){
                    errores.add("Los correos deben coincidir");
                }                
            } else {
                errores.add("Complete los campos de correo");
            }
            if(genero.equals("null")){
                errores.add("Seleccione un genero");
            }
            if(fechaNac.equals("")){
                errores.add("Seleccione una fecha de nacimiento");
            }
            if(!contrasena.equals("") && !contrasena2.equals("")){
                if(!contrasena.equals(contrasena2)){
                    errores.add("Las contrasenas deben coincidir");
                }                
            } else {
                errores.add("Complete los campos de contrasena");
            }
            
            //si hay errores regresamos a la página de registro para que se corrijan
            if(errores.size() > 0){
                response.sendRedirect("registro.jsp?errors=true");
            } else { //procedemos a guardar la info en la bd
                Usuario usuario = new Usuario(null, correo, contrasena, Util.getFecha());
                Docente docente = new Docente(null, nombre, apellidoP, apellidoM, genero, fechaNac, null);                
                if(Usuario.guardarObjeto(usuario)){
                    docente.setIdUsuario(Util.getUltimoId("idUsuario", "usuarios"));
                    if(Docente.guardarObjeto(docente))
                        response.sendRedirect("registro.jsp?msg=true");
                } else {
                    response.sendRedirect("registro.jsp?errors=true");
                }
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
