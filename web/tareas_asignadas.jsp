<%-- 
    Document   : tareas_asignadas
    Created on : 11/12/2017, 11:17:56 PM
    Author     : Norma
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="modelos.Docente"%>
<%@page import="modelos.TareaAsignada"%>
<%@page import="modelos.Materia"%>
<%@page import="modelos.Bloque"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Maestro Tareas</title>
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!--Css para la pagina (El cuerpo y footer)-->
    <link rel="stylesheet" href="css/maestro-mis-documentos.css">
    <!-- <link href="https://
    fonts.googleapis.com/css?family=Acme" rel="stylesheet"> -->
    <!--CSS para el header-->
    <link rel="stylesheet" href="css/nav-christ.css">
</head>

<body>
    <div class="navbar">
      <div class="nav-logo">
          <a href="index.html"><img src="img/kulu_logo_160.png"></a>
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
                    int idSesionD = Docente.obtenerPorIdUsuario(idU).getIdDocente();
                    String NombreDocente = Docente.obtenerPorIdUsuario(idU).getNombreD();
                    NombreDocente += " "+ Docente.obtenerPorIdUsuario(idU).getApPaternoD();
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
    <h2 class="titulo">Mis Tareas</h2>
    <div class="herramientas">
      <div class="add"><button class="boton" id="myBtn"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</button></div>
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
                <tbody>
                    <tr>
                      <th>Materia</th>
                      <th>Nombre Tarea</th>
                      <th>Descripción</th>
                      <th>ID Bloque</th>
                      <th colspan="3">Acciones</th>
                    </tr>
                    <tr>
                      <%
                        int id =0, idBloque=0, idMat=0;
                        String nombreTarea="";
                        String descripcionT="";
                        String nombreM="";
                        List<TareaAsignada> tarea = new ArrayList<>();
                        tarea = TareaAsignada.obtenerTodos();  
                
                        for (int i=0;i<tarea.size();i++) {
                           id = tarea.get(i).getIdTareaAsignada();
                           nombreTarea = tarea.get(i).getNombreTarea();
                           descripcionT = tarea.get(i).getDescripcionT();
                           idBloque = tarea.get(i).getIdBloque();
                           idMat = tarea.get(i).getIdMateria();
                           nombreM = Materia.obtenerPorId(idMat).getNombre();

                            out.println("<tr>");
                            out.println("<td>"+nombreM+"</td>");
                            out.println("<td>"+ nombreTarea +"</td>");
                            out.println("<td>"+descripcionT+"</td>");
                            out.println("<td>"+idBloque+"</td>");
                            out.println("<td><button class='boton' id='myBtn2' onClick='getAlumno("+ id + ",\"" + nombreTarea +"\", \""+ descripcionT +"\", \""+ idBloque +"\", \""+ idSesionD + "\")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");
                            out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                            out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                            out.println("<input type =\"button\" onclick=\"javascript:eliminar('crudAlumno', " + id +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
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
                    <h2>Agregar una nueva tarea</h2>
                </div>
                <div class="modal-body">
                    <div class="modal-body2">
                        <form action="crudTareas_asignadas" method='post'>
                            <label>Agregue un nombre a la tarea</label>
                            <input class="input" type="text" name="nombre" placeholder="Nombre" />
                            <label>Deje una breve descripcion</label>
                            <input class="input" name="descripcion" placeholder = "Descripción"/>
                            <label>Seleccione la materia</label>
                            <select class="input" name="materia" id="materia">
                                <%
                                    List<Materia> mat = new ArrayList<>();
                                    mat = Materia.obtenerTodos();  
                                    int matID = 0;
                                    for (int i=0;i<mat.size();i++) {   
                                        matID = mat.get(i).getIdMateria();
                                        out.println("<option value=\"" + matID+ "\">"+mat.get(i).getNombre()+"</option>");
                                    }
                                %>
                            </select>
                            
                            <label>Docente</label> 
                            <input class="input" type="text" name="docente" value=" <%= NombreDocente %> " disabled/>
                            <input class="input" type="text" name="docenteID" value=" <%= idSesionD %> " hidden=""/>
                            
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
        <script src="js/modal.js"></script>
        <script src="js/funciones.js"></script>
</body>

</html>

