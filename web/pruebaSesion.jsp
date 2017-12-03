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
            
            Cuenta cuenta = (Cuenta)sesionStatus.getAttribute("invitado");
            out.println("Sesion obtenida"+cuenta.getSaludo());
        %>
        <br>
        <a href="proceso-fin.jsp">cerrar sesion</a>
    </body>
</html>
