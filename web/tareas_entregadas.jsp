<%-- 
    Document   : tareas_entregadas
    Created on : 13/12/2017, 05:40:39 PM
    Author     : Norma
--%>

<%@page import="modelos.Alumno"%>
<%@page import="modelos.TareaEntregada"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Docente"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="java.sql.Blob"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>K'ulu' - Maestro Tareas Entregadas</title>
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!--Css para la pagina (El cuerpo y footer)-->
    <link rel="stylesheet" href="css/maestro-mis-documentos.css">
    <!-- <link href="https://
    fonts.googleapis.com/css?family=Acme" rel="stylesheet"> -->
    <!--CSS para el header-->
    <link rel="stylesheet" href="css/nav-christ.css">
    <script language="javascript" src="js/jquery-1.2.6.min.js"></script>
</head>

<body>
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
                    int idSesionD = Docente.obtenerPorIdUsuario(idU).getIdDocente();
                    String NombreDocente = Docente.obtenerPorIdUsuario(idU).getNombreD();
                    NombreDocente += " "+ Docente.obtenerPorIdUsuario(idU).getApPaternoD();
                    out.println(Docente.obtenerPorIdUsuario(idU).getNombreD() +"!");
                    int idTareaAsignada = -1;
                    try{
                        idTareaAsignada = Integer.parseInt(request.getParameter("variable"));
                    }catch (Exception e){
                        idTareaAsignada = 0;
                    }
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
      <center><h2 class="titulo">Tareas Entregadas</h2></center>
 
        <div class="tabla">
            <table>
                <tbody>
                    <tr>
                      <th>Nombre Archivo</th>
                      <th>Alumno</th>
                      <th>Calificacion</th>
                      <th>Fecha Entregada</th>
                      <th colspan="3">Acciones</th>
                    </tr>
                    <tr>
                      <%
                        int id =0, idTareaA=0, idAlumno=0, calificacion=0;
                        String nombreArchivo="", nombreCA ="", fecha="";
                        Blob documento;
                        //formateador
                        DecimalFormat formatter = new DecimalFormat("#.##");
                        
                        List<TareaEntregada> tarea = new ArrayList<>();
                        tarea = TareaEntregada.obtenerTodosID(idTareaAsignada);  
                        
                        for (int i=0;i<tarea.size();i++) {
                            id = tarea.get(i).getIdTareaEntregada();
                            idTareaA = tarea.get(i).getIdTareaAsignada();
                            nombreArchivo = tarea.get(i).getNombreArchivo();
                            idAlumno = tarea.get(i).getIdAlumno();
                            calificacion = tarea.get(i).getCalificacion();
                            documento = tarea.get(i).getTarea();
                            fecha = tarea.get(i).getFechaEntrega();
                            
                            Alumno alum = Alumno.obtenerPorId(idAlumno);
                            nombreCA = alum.getNombreA() + " ";
                            nombreCA += alum.getApPaternoA();
                            out.println("<tr>");
                            out.println("<td>"+nombreArchivo+"</td>");
                            out.println("<td>"+ nombreCA +"</td>");
                            if (calificacion==0)
                                out.println("<td></td>");
                            else
                                out.println("<td>"+calificacion+"</td>");
                            out.println("<td>"+fecha+"</td>");
                            
                            out.println("<td><button class='boton' id='miBoton' name='editar' onClick='getTareas_entregadas(" +id+ ")'><i class='fa fa-pencil' aria-hidden='true'></i>Calificar </button></td>");
                            out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                            out.println("<input class=\"input\" id='idTareaA' name='idTAsignada' value= '" +idTareaAsignada+ "' type=\"hidden\"/>");
                            out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                            out.println("<input type =\"button\" onclick=\"javascript:eliminar('crudTareas_entregadas', " + id +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
                        out.println("</td></form>");
                        out.println("<td><button class=\"boton\" onclick=\"location.href = 'DescargarTarea.jsp?id="+id+"'\" value=\"Descargar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"><i class=\"fa fa-download\" aria-hidden=\"true\"></i> Descargar </button></td>");
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
    <!-- The Modal aparece cuando se selecciona editar-->
    <div id="tareas" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span>
                <center><h2>Asignar Calificación!</h2></center>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudTareas_entregadas" method='post'>
                        <input class="input" id='idTareaA' name='idTAsignada' value= '<%=idTareaAsignada%>' type="hidden"/>
                        <label>ID de la tarea </label>
                        <input class="input" id='MyId' name='idTarea' value= '' disabled/>
                        <input class="input" id='MyId2' name='idTar' value= '' type="hidden" /> 
                        <label>Introduzca la calificacion </label>
                        <input class="input" id='calificacion' name="calificacion" value = ""/>
                        <input class="modal-boton active-boton" type="submit" value="Calificar" name="editar"/>
                        <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                    </form>
                </div>
            </div>
        </div>
    </div> 
    <script src="js/funciones.js"></script>
    <script src="js/tareasEntregadas.js"></script>
</body>

</html>


