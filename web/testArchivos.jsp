<%-- 
    Document   : testArchivos
    Created on : 18/11/2017, 06:28:52 PM
    Author     : gerar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <form action="cargarArchivo.jsp" method="POST" enctype="multipart/form-data">
            <input type="file" name="file" />
            <input type="submit" value="Cargar archivo"/>
        </form>
        
        
    </body>
</html>
