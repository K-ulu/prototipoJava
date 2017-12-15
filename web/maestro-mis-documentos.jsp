<%-- 
    Document   : maestro-mis-documentos
    Created on : 7/12/2017, 10:03:59 PM
    Author     : gerar
--%>

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
                        int idU = (int)sesionStatus.getAttribute("idUsuario");
                        String tipo = (String)sesionStatus.getAttribute("tipoUsuario");
                        //out.println("Sesion obtenida id:"+id+" tipo: "+tipo);
                        out.println(Docente.obtenerPorIdUsuario(idU).getNombreD()+"!");                        
                    %>
                    </a></li>
                </ul>
            </div>
        </div>
        <hr class="style13">
        <nav class="nav-extras">
            <ul>
                <li><a href="maestro-Grupos.jsp">Mis grupos</a></li>
                <li><a href="maestro-Alumnos.jsp">Mis alumnos</a></li>
                <li><a href="maestro-materias.jsp">Mis materias</a></li>
                <li><a href="maestro-tareas.html">Admin tareas</a></li>
                <li><a href="maestro-mis-documentos.jsp">Mis documentos</a></li>
                <li><a href="maestro-contenido-multimedia.jsp">Admin contenido Mult.</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <!--Empieza a escrbir aquí -->
        <div class="container">
            <h2 class="titulo">Mis Documentos</h2>
            <div class="herramientas">
                <div class="add">
                    <button class="boton" id="myBtn"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</button>
                </div>
                <div class="form">
                    <form action="">
                        <input class="busqueda" type="search" name="buscar" placeholder="Buscar...">
                        <button class="boton">Buscar</button>
                        <button class="boton">Limpiar</button>
                    </form>
                </div>
            </div>
            <div class="tabla">
                <table>
                    <tr>
                        <th>Nombre</th>
                        <th>Tipo</th>
                        <th>Tamaño</th>
                        <th colspan="3">Acciones</th>
                    </tr>
                    <%
                        
                        List<Documento> documentos = new ArrayList<>();
                        documentos = Documento.obtenerTodos();
                        int idDocumento, idDocente;
                        String nombreDocumento, tipoDocumento;
                        Blob documento;
                        
                        //formateador
                        DecimalFormat formatter = new DecimalFormat("#.##");
                    
                        for (int i=0;i<documentos.size();i++){
                            idDocumento = documentos.get(i).getIdDocumento();
                            nombreDocumento = documentos.get(i).getNombreDocumento();
                            tipoDocumento = documentos.get(i).getTipoDocumento();
                            documento = documentos.get(i).getDocumento();
                            idDocente = documentos.get(i).getIdDocente();
                            
                            out.println("<tr>");
                            out.println("<td>"+nombreDocumento+"</td>");
                            if(tipoDocumento.length() > 15)
                                out.println("<td>"+tipoDocumento.substring(0, 15)+".. </td>");
                            else 
                                out.println("<td>"+tipoDocumento+"</td>");
                                                       
                            out.println("<td>"+formatter.format(((documento.length() / 1024.0)/1024.0))+" Mb </td>");
                            //out.println("<td><a href=''><i class='fa fa-download' aria-hidden='true'></i></a></td>");
                            //out.println("<td><a href=''><i class='fa fa-pencil' aria-hidden='true'></i></a></td>");
                            //out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudDocumentos', " + idDocumento +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
                            //out.println("<td><a href='test.jsp?id="+idDocumento+"'>Descargar</a></td>");
                            out.println("<td><button class=\"boton\" onclick=\"location.href = 'descargarArchivo.jsp?id="+idDocumento+"'\" value=\"Descargar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"><i class=\"fa fa-download\" aria-hidden=\"true\"></i> Descargar </button></td>");
                            
                            out.println("<form name=\"formulario"+i+"\" action=\"crudDocumentos\" method=\"Post\">");
                            out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                            //out.println("<button class=\"boton\" onclick=\"javascript:eliminar('crudDocumentos', " + idDocumento +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i> Eliminar</button>");                            
                            out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudDocumentos', " + idDocumento +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");

                            out.println("</td></form>");
                            //out.println("<td><input type=\"button\" class=\"boton\" id=\"myBtn4\" onClick='descargar("+idDocumento+")' name=\"descargar\"><i class=\"fa fa-download\" aria-hidden=\"true\"></i>Descargar </button></td>");                            
                            out.println("</tr>");
                            
                        }
                           
                            /*out.println("<tr>");
                                out.println("<td> Grupo "+letraG+"</td>");
                                out.println("<td>"+total+"</td>");
                                out.println("<td>"+turno +"</td>");
                                out.println("<td><button class='boton' id='myBtn2' onClick='getGrupo("+ id + ","+ idDoc  + ",\"" + grado +"\", \""+ letraG +"\", \""+ turno +"\")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");*/
                                /*
                                out.println("<form action=\"crudGrupos\" method='post'>");
                                    out.println("<input class=\"input\" type=\"hidden\" name=\"idGrupo\" id='"+i+"'value=\""+id+"\"/>");
                                    out.println("<td><button class=\"boton\" id=\"myBtn3\" name=\"eliminar\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i> Eliminar </button></td>");
                                    out.println("<td><button class=\"boton\" id=\"myBtn4\" name=\"compartir\"><i class=\"fa fa-share-alt\" aria-hidden=\"true\"></i>Compartir </button></td>");
                                out.println("</form>");
                            out.println("</tr>");*/

                                /*out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                                    out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                                    out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudGrupos', " + id +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
                                out.println("</td></form>");
                                out.println("<td><button class=\"boton\" id=\"myBtn4\" name=\"compartir\"><i class=\"fa fa-share-alt\" aria-hidden=\"true\"></i>Compartir </button></td>");
                            out.println("</tr>");
                        }*/
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
        <!-- Trigger/Open The Modal -->
        <!--<button id="myBtn">Open Modal</button>-->
        <!-- The Modal -->
        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Nuevo Documento</h2>
                </div>
                <div class="modal-body">
                    <form action="cargarArchivo" enctype="multipart/form-data" method="POST">
                        <!--<label for="">Nombre:</label>
                        <input class="input" type="text" name="nombre">
                        <label for="">Descripción:</label>
                        <input class="input" type="text" name="descripcion">
                        <label for="">Materia:</label>
                        <select class="input" class="" name="materia">
                            <option value="seleccione">Seleccione</option>
                            <option value="espaniol">Español</option>
                            <option value="matematicas">Matemáticas</option>
                            <option value="historia">Historia</option>
                            <option value="geografia">Geografía</option>
                        </select> -->
                        <label for="">Seleccione archivo:</label>
                        <input class="input" type="file" name="archivo">
                        <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar">
                        <input class="modal-boton" type="reset" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                    </form>
                </div>
            </div>
        </div>
        <footer>
            <div class="foot">
                <nav class="nav-extras nav-extras-fondo">
                    <div class="footer">
                        <ul>
                            <li class="active"><a href="">¿Quienes Somos?</a></li>
                            <li><a href="">Kulu for bussines</a></li>
                            <li><a href="">Contacto</a></li>
                            <li><a href="">Soporte</a></li>
                        </ul>
                    </div>
                </nav>
            </div>
        </footer>
        <script src="js/modal.js"></script>
        <script src="js/funciones.js"></script>
    </body>
</html>
