<%-- 
    Document   : pruebaSesion
    Created on : 2/12/2017, 07:25:47 PM
    Author     : gerar
--%>
<%@page import="db.Cuenta"%>
<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuario</title>
    </head>
    <body>
        <%
            HttpSession sesionStatus = request.getSession();
            out.println("id verificacion "+sesionStatus.getId());
            
            int id = (int)sesionStatus.getAttribute("idUsuario");
            String tipo = (String)sesionStatus.getAttribute("tipoUsuario");
            out.println("Sesion obtenida id:"+id+" tipo: "+tipo);
        %>
        <br>
        <a href="proceso-fin.jsp">cerrar sesion</a>
    </body>
</html>
