<%-- 
    Document   : maestro-Grupos
    Created on : 15/11/2017, 11:04:41 PM
    Author     : Norma
--%>
<%@page import="modelos.Docente"%>
<%@page session="true" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@ page import = "modelos.Grupos"%> 
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Maestro Dashboard</title>
        <!--Hoja de estilos del encabezado-->
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <!--Hoja de estilos de la página (El cuerpo y footer)-->
        <link rel="stylesheet" href="css/maestro-mis-documentos.css">
        <!-- <link href="https://fonts.googleapis.com/css?family=Acme" rel="stylesheet"> -->
        <link rel="stylesheet" href="css/nav-christ.css">
    </head>

    <body>
        <div class="navbar">
            <div class="nav-logo">
                <a href="index.jsp"><img src="img/kulu_logo_160.png"></a>
            </div>
            <div class="nav-menu">
                <li class="dropDown"><a href="javascript:void(0)" class="dropButton"><i class="fa fa-bars"></i> Menu</a>
                    <div class="dropDown-content">
                        <a href="#">Noticias</a>
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
                <li><a href="tareas_asignadas.jsp">Admin tareas</a></li>
                <li><a href="maestro-mis-documentos.html">Mis documentos</a></li>
                <li><a href="maestro-contenido-multimedia.html">Admin contenido Mult.</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <!--Empieza a escrbir aquí -->
        <div class="container">
            <h2 class="titulo">Mis Grupos</h2>
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
                        <th>No Alumnos</th>
                        <!--th>Fecha de creación</th-->
                        <th>Turno</th>
                        <th colspan="3">Acciones</th>
                    </tr>
                    <%
                        int id =0;
                        int idDoc =0;
                        String grado="";
                        String letraG="";
                        String turno="";
                        int total=0;
                    
                    List<Grupos> grupito = new ArrayList<>();
                    grupito = Grupos.obtenerTodos();  

                    for (int i=0;i<grupito.size();i++)
                    {
                       id = grupito.get(i).getIdGrupo();
                       idDoc = grupito.get(i).getIdDocente();
                       grado = grupito.get(i).getGrado();
                       letraG = grupito.get(i).getLetra();
                       turno = grupito.get(i).getTurno();
                       total = grupito.get(i).getTotalAlumnos();
                        out.println("<tr>");
                            out.println("<td> Grupo "+letraG+"</td>");
                            out.println("<td>"+Grupos.totAlum(id)+"</td>");
                            out.println("<td>"+turno +"</td>");
                            out.println("<td><button class='boton' id='myBtn2' onClick='getGrupo("+ id + ","+ idDoc  + ",\"" + grado +"\", \""+ letraG +"\", \""+ turno +"\")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");                           
                            out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                                out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                                out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudGrupos', " + id +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
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
        <!-- The Modal -->
        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Agregar un grupo nuevo</h2>
                </div>
                <div class="modal-body">
                    <div class="modal-body2">
                        <form action="crudGrupos" method='post'>
                            <label>Agregue un nombre al grupo </label>
                            <input class="input" type="text" name="nombre" placeholder="Nombre" />
                            <label>ID del Docente</label>
                            <input class="input" type="text" value = "1" disabled/>
                            <input class="input" type="hidden" name="idDocente" value = "1"/>
                            <label>Seleccione el grado</label>
                            <select class="input" name="grado">
                                <option value="5">5to</option>
                                <option value="6">6to</option>
                            </select>
                            <label>Turno escolar</label>
                            <input class="input" type="text" name="turno" placeholder="turno" />
                            <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar"/>
                            <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                            <div class="clear"></div>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <footer>
            <div class="foot">
                <nav class="nav-extras nav-extras-fondo">
                    <ul>
                        <li class="active"><a href="">¿Quienes Somos?</a></li>
                        <li><a href="">Kulu for bussines</a></li>
                        <li><a href="">Contacto</a></li>
                        <li><a href="">Soporte</a></li>
                    </ul>
                </nav>
            </div>
        </footer>
        
        <!-- The Modal aparece cuando se selecciona editar-->
    <div id="edit" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span>
                <h2>Editar un Grupo!</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudGrupos" method='post'>
                        <label>ID del grupo </label>
                        <input class="input" id='MyId' name='idGrupo' value= '' disabled/>
                        <input class="input" id='MyId2' name='idGrup' value= '' type="hidden" /> 
                        <label>ID del Docente</label>
                        <input class="input" id='idDoc' name='idDocente' value= '' disabled/>
                        <input class="input" id='idDoc2' name='idDoc' value= '' type="hidden" /> 
                        <label>Grado</label>
                        <input class="input" id='grad' name="grado" value = ""/>
                        <label>Nombre de grupo </label>
                        <input class="input" id='nomb' name="nombre" value = ""/>
                        <label>Turno escolar </label>
                        <input class="input" id='turn' name="turno" value = ""/>
                        <input class="modal-boton active-boton" type="submit" value="Editar" name="editar"/>
                        <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                    </form>
                </div>
            </div>
        </div>
        <script src="js/modal.js"></script>
        <script src="js/funciones.js"></script>
    </body>
</html>
