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
import modelos.GruposMateria;

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
                idMateria = Integer.parseInt(request.getParameter("variable2"));
                idGrupo = request.getParameterValues("datos");
                for(int i=0; i < idGrupo.length; i++){
                    GruposMateria.guardarObjeto(1, idGrupo[i], idMateria);
                } 
            } 
            else if(request.getParameter("deshacer") != null) {
                //Integer num[] = null;
                String idMaterias = request.getParameter("variable3");
                //idMaterias = idMaterias.trim();
                
                char[] c_arr = idMaterias.toCharArray();                
                String val ="";                
                for (int i = 0; i < c_arr.length; i++) {
                    if (Character.isWhitespace(c_arr[i])){
                        int num = Integer.parseInt(val);
                        out.println(num);
                        val ="";
                    }
                    else{
                        val += c_arr[i];
                    }
                }
                /*char[] c_arr = idMaterias.toCharArray();
                
                char c = idMaterias.charAt(4);  // returns 'l'
                //out.println(idMaterias.substring(0, 1));
                int ini=0, fin=0;
                out.println("tamanio "+idMaterias.length());
                for (int j =0; j< idMaterias.length(); j++){
//                    fin++;
                    String var = idMaterias.substring(2,3);
                    if (var == " "){
                       out.println("Es espacio"); 
                    }
                    else{
                        out.println(var + "primera"); 
                    }
                }*/
                //out.println(c_arr.length);
            }
                            //response.sendRedirect("maestro-materias.jsp");
        }
        catch (Exception e){
            out.println("Error "+ e.getCause()+ e.getMessage());
            //response.sendRedirect("maestro-materias.jsp");
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
