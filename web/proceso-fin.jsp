<%-- 
    Document   : proceso-fin
    Created on : 2/12/2017, 07:34:32 PM
    Author     : gerar
--%>
<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cerrando</title>
    </head>
    <body>
        <%
            HttpSession sesionOut = request.getSession();
            sesionOut.invalidate();
            response.sendRedirect("login.jsp");
            %>
    </body>
</html>
