<%-- 
    Document   : alumnos-contenido-multimedia
    Created on : 17/12/2017, 07:08:45 PM
    Author     : Norma
--%>

<%@page import="modelos.Alumno"%>
<%@page import="modelos.ContenidoMultimedia"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.Blob"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="db.Conexion"%>
<%@page import="modelos.Documento"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Maestro Dashboard</title>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/maestro-mis-documentos.css">
        <link rel="stylesheet" href="css/nav-christ.css">
        <!-- <link href="https://fonts.googleapis.com/css?family=Acme" rel="stylesheet"> -->
        <script src="js/sesion.js"></script>
    </head>
    <body onMouseOver="hayActividad()">
        <!-- Empieza codigo para la barra de navegacion -->
        <div class="navbar">
            <div class="nav-logo">
                <a href="index.html"><img src="img/kulu_logo_160.png"></a>
            </div>
            <div class="nav-menu">
                <li class="dropDown"><a href="javascript:void(0)" class="dropButton"><i class="fa fa-bars"></i> Menu</a>
                    <div class="dropDown-content">
                        <a href="#" Noticias</a>
                        <a href="#">Recursos Pedagógicos</a>
                        <a href="#">null</a>
                        <a href="#">null</a>
                    </div>
                </li>
            </div>
            <div class="nav-buscador">
                <form class="buscarNav" action="">
                    <input type="search" name="search" class="buscarTerm" placeholder="¿Qué estás buscando?">
                    <button type="submit" class="searchButton"><i class="fa fa-search"></i></button>
                </form>
            </div>
            <div class="nav-enlaces">
                <ul>
                    <li><a href="cerrarSesion">Cerrar sesión</a></li>
                    <li><a class="active" href="maestro-Grupos.jsp">¡Hola
                    <% 
                      //recuperamos los datos de la sesion
                      HttpSession sesionStatus = request.getSession();
                      //out.println("id verificacion "+sesionStatus.getId());
                      int id = (int)sesionStatus.getAttribute("idUsuario");
                      String tipo = (String)sesionStatus.getAttribute("tipoUsuario");
                      //out.println("Sesion obtenida id:"+id+" tipo: "+tipo);
                      out.println(Alumno.obtenerPorIdUsuario(id).getNombreA()+"!");
                      int alumno = Alumno.obtenerPorIdUsuario(id).getIdAlumno();
                    %> 
                    </a></li>
                </ul>
            </div>
        </div>
        <hr class="style13">
        <nav class="nav-extras">
            <ul>
                <li><a href="alumno-dashboard.jsp">Dashboard</a></li>
                <li><a href="alumno-materias-bloques.jsp">Bloques</a></li>
                <li><a href="alumno-materia-bloques-tarea.jsp">Tarea</a></li>
                <li><a href="alumnos-contenido-multimedia.jsp">Contenido Multimedia</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <!--Empieza a escrbir aquí -->
        <div class="container">
            <center><h1 class="titulo">Contenido Multimedia</h1></center>
            <br>
            <div class="tabla">
                <table>
                    <tr>
                        <th>Nombre</th>
                        <th>Tipo</th>
                        <th>Tamaño</th>
                        <th>Fecha de creación</th>
                        <th colspan="3">Acciones</th>
                    </tr>
                    <%
                        
                        List<ContenidoMultimedia> contenidos = new ArrayList<>();
                        contenidos = ContenidoMultimedia.obtenerTodos();
                        Integer idContenido;
                        String nombreContenido, tipoContenido, fechaCreacionContenido;
                        Blob contenidoMultimedia;
                        
                        //formateador
                        DecimalFormat formatter = new DecimalFormat("#.##");
                    
                        for (int i=0;i<contenidos.size();i++){
                            idContenido = contenidos.get(i).getIdContenido();
                            nombreContenido = contenidos.get(i).getNombreContenido();
                            tipoContenido = contenidos.get(i).getTipoContenido();
                            fechaCreacionContenido = contenidos.get(i).getFechaCreacionContenido();
                            contenidoMultimedia = contenidos.get(i).getContenidoMultimedia();
                            
                            out.println("<tr>");
                            out.println("<td>"+nombreContenido+"</td>");
                            if(tipoContenido.length() > 15)
                                out.println("<td>"+tipoContenido.substring(0, 15)+".. </td>");
                            else 
                                out.println("<td>"+tipoContenido+"</td>");
                                                       
                            out.println("<td>"+formatter.format(((contenidoMultimedia.length() / 1024.0)/1024.0))+" Mb </td>");
                            out.println("<td>"+fechaCreacionContenido+"</td>");
                            out.println("<td><button class=\"boton\" onclick=\"location.href = 'descargarContenido.jsp?id="+idContenido+"'\" value=\"Descargar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"><i class=\"fa fa-download\" aria-hidden=\"true\"></i> Descargar </button></td>");
                            
                            out.println("<form name=\"formulario"+i+"\" action=\"crudContenidos\" method=\"Post\">");
                            out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                            out.println("</td></form>");                            
                            out.println("</tr>");
                            
                        }
                           
                    %>                
                </table>
            </div>
            <div class="pagination">
                <a href="#">&laquo;</a>
                <a href="#">1</a>
                <a class="active" href="#">2</a>
                <a href="#">3</a>
                <a href="#">4</a>
                <a href="#">5</a>
                <a href="#">6</a>
                <a href="#">&raquo;</a>
            </div>
        </div>
        <footer>
            <div class="foot">
                <nav class="nav-extras nav-extras-fondo">
                    <ul>
                        <li class="active"><a href="quienes_somos.jsp">¿Quiénes somos? </a></li>
                        <li><a href="kulu_business.jsp">K'ulu' for business</a></li>
                        <li><a href="contacto.jsp">Contacto</a></li>
                        <li><a href="soporte.jsp">Soporte</a></li>
                    </ul>
                </nav>
            </div>
        </footer>
    </body>
</html>

