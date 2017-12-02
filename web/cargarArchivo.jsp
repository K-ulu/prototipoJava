<%-- 
    Document   : cargarArchivo
    Created on : 18/11/2017, 06:36:50 PM
    Author     : gerar
--%>

<%@page import="org.apache.commons.fileupload.FileItem"%>
<%@page import="java.util.List"%>
<%@page import="org.apache.commons.fileupload.servlet.ServletFileUpload"%>
<%@page import="java.io.File"%>
<%@page import="org.apache.commons.fileupload.disk.DiskFileItemFactory"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            
            String archivourl = "D:\\Documentos\\NetBeansProjects\\Kulu-prototipo\\web\\archivos";
            
            DiskFileItemFactory factory = new DiskFileItemFactory();
            factory.setSizeThreshold(4096);
            factory.setRepository(new File(archivourl));
            ServletFileUpload upload = new ServletFileUpload(factory);
            
            try {
                List<FileItem> partes = upload.parseRequest(request);
                
                for(FileItem items: partes){
                    File file = new File(archivourl, items.getName());
                    items.write(file);
                }
                
                out.println("<h1>Archivo subido exitosamente</h1><br><a href='testArchivos.jsp'>Volver</a>");
            } catch(Exception e){
                out.println("Exception: "+e.getMessage());
            }
            

        %>
    </body>
</html>
