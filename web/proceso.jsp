<%-- 
    Document   : proceso
    Created on : 2/12/2017, 05:17:23 PM
    Author     : gerar
--%>
<%@page session="true" %>
<%@page import="db.Cuenta"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <% 
            try {
                String correo = request.getParameter("correo");
                String contrasena = request.getParameter("contrasena");
                if(!correo.equals("") && !contrasena.equals("")){
                    Cuenta login = new Cuenta();
                    if(login.autenticacion(correo, contrasena)){
                        HttpSession sesion = request.getSession();
                        sesion.setAttribute("invitado", login);
                        response.sendRedirect("pruebaSesion.jsp");
                    } else {
                        %>
                        <jsp:forward page="login.jsp">
                        <jsp:param name="error" value="Correo y/o contrasena incorrecta"/>
                        </jsp:forward>
                        <%
                    }

                } else {
                    %>
                    <jsp:forward page="login.jsp">
                        <jsp:param name="error" value="Correo y/o contrasena incorrecta"/>
                    </jsp:forward>
                    <%
                }
                
            } catch(Exception e){
                response.sendRedirect("login.jsp?error=Correo y/o clave incorrectas");
            }
            
        %>
    </body>
</html>
