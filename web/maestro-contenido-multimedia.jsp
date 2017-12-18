<%-- 
    Document   : maestro-mis-documentos
    Created on : 7/12/2017, 10:03:59 PM
    Author     : gerar
--%>

<%@page import="modelos.ContenidoMultimedia"%>
<%@page import="modelos.Docente"%>
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
        <title>K'ulu' - Maestro Contenido Multimedia</title>
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
                <a href="index.jsp"><img src="img/kulu_logo_160.png"></a>
            </div>
            <div class="nav-menu">
                <li class="dropDown"><a href="javascript:void(0)" class="dropButton"><i class="fa fa-bars"></i> Menu</a>
                    <div class="dropDown-content">
                        <a href="quienes_somos.jsp">¿Quiénes somos?</a>
                        <a href="kulu_business.jsp">K'ulu' for business</a>
                        <a href="contacto.jsp">Contacto</a>
                        <a href="soporte.jsp">Soporte</a>
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
                        int idU = (int)sesionStatus.getAttribute("idUsuario");
                        String tipo = (String)sesionStatus.getAttribute("tipoUsuario");
                        //out.println("Sesion obtenida id:"+id+" tipo: "+tipo);
                        out.println(Docente.obtenerPorIdUsuario(idU).getNombreD()+"!");  
                        int idDoc = Docente.obtenerPorIdUsuario(idU).getIdDocente();
                    %>
                    </a></li>
                </ul>
            </div>
        </div>
        <hr class="style13">
        <div class="pattern">
        <nav class="nav-extras">
            <ul>
                <li><a href="maestro-Grupos.jsp">Mis grupos</a></li>
                <li><a href="maestro-Alumnos.jsp">Mis alumnos</a></li>
                <li><a href="maestro-materias.jsp">Mis materias</a></li>
                <li><a href="tareas_asignadas.jsp">Admin tareas</a></li>
                <li><a href="maestro-mis-documentos.jsp">Mis documentos</a></li>
                <li><a href="maestro-contenido-multimedia.jsp">Admin contenido Mult.</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <!--Empieza a escrbir aquí -->
        <div class="container">
            <h2 class="titulo">Contenido Multimedia</h2>
            <div class="herramientas">
                <div class="add">
                    <button class="boton" id="myBtn"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</button>
                </div>
                <!--<div class="form">
                    <form action="">
                        <input class="busqueda" type="search" name="buscar" placeholder="Buscar...">
                        <button class="boton">Buscar</button>
                        <button class="boton">Limpiar</button>
                    </form>
                </div>-->
            </div>
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
                        contenidos = ContenidoMultimedia.obtenerTodosID(idDoc);
                        Integer idContenido, idDocente;
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
                            idDocente = idDoc;
                            
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
                            //out.println("<button class=\"boton\" onclick=\"javascript:eliminar('crudDocumentos', " + idDocumento +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i> Eliminar</button>");                            
                            out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudContenidos', " + idContenido +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");

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
        
        <!-- Trigger/Open The Modal -->
        <!--<button id="myBtn">Open Modal</button>-->
        <!-- The Modal -->
        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Nuevo Contenido</h2>
                </div>
                <div class="modal-body">
                    <form action="cargarContenido" enctype="multipart/form-data" method="POST">
                        <input class="input" type="hidden" name="idDocente" value="<%=idDoc%>"> 
                        <label for="">Seleccione archivo:</label>
                        <input class="input" type="file" name="archivo">
                        <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar">
                        <input class="modal-boton" type="reset" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                    </form>
                </div>
                <br>
            </div>
        </div>
        </div>
                        </div>
        <footer>
    <div class="foot">
      <nav class="nav-extras nav-extras-fondo">
          <div class="footer">
        <ul>
          <li class="active"><a href="quienes_somos.jsp">¿Quiénes somos? </a></li>
          <li><a href="kulu_business.jsp">K'ulu' for business</a></li>
          <li><a href="contacto.jsp">Contacto</a></li>
          <li><a href="soporte.jsp">Soporte</a></li>
        </ul>
              </div>
      </nav>
    </div>
  </footer>
        <script src="js/modal.js"></script>
        <script src="js/funciones.js"></script>
    </body>
</html>
