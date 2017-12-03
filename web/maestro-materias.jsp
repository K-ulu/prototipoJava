<%-- 
    Document   : maestro-materias
    Created on : 27/11/2017, 02:48:24 PM
    Author     : Norma
--%>
<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@ page import = "modelos.Materia"%> 
<%@ page import = "modelos.Docente"%> 

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Maestro Materias</title>
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
          <a href="index.jsp.html"><img src="img/kulu_logo_160.png"></a>
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
      <li><a href="maestro-tareas.html">Admin tareas</a></li>
      <li><a href="maestro-mis-documentos.html">Mis documentos</a></li>
      <li><a href="maestro-contenido-multimedia.html">Admin contenido Mult.</a></li>
    </ul>
  </nav>
 <hr class="style13">
  <!--Empieza a escrbir aquí -->
    <div class="container">
        <h2 class="titulo">Mis Materias</h2>
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
                <tbody>
                    <tr>
                        <th>Nombre</th>
                        <th>Grado</th>
                        <th>Grupo</th>
                        <th colspan="3">Acciones</th>
                    </tr>
                    <%
                        int id =0, idDoc=0;
                        String nombreMat="";
                        String grado="";
                    
                    List<Materia> mat = new ArrayList<>();
                    mat = Materia.obtenerTodos();  

                    for (int i=0;i<mat.size();i++)
                    {
                       id = mat.get(i).getIdMateria();
                       nombreMat = mat.get(i).getNombre();
                       grado = mat.get(i).getGrado();
                       idDoc = mat.get(i).getIdDocente();
                       out.println("<tr>");
                            out.println("<td> "+ nombreMat +"</td>");
                            out.println("<td>"+ grado +"</td>");
                            out.println("<td> 001 </td>");
                            out.println("<td><button class='boton' id='myBtn2' onClick='getMateria("+ id + ", \""+ nombreMat  + "\",\"" + grado +"\", "+ idDoc +")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");
                            
                            out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                                out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                                out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudMaterias', " + id +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
                            out.println("</td></form>");
                            out.println("<td><button class=\"boton\" id=\"myBtn4\" name=\"compartir\"><i class=\"fa fa-share-alt\" aria-hidden=\"true\"></i>Compartir </button></td>");
                        out.println("</tr>");
                    }
                %>
                </table>
        </div>
    </div
    <!-- The Modal -->
        <div id="myModal" class="modal">
            <!-- Modal content -->
            <div class="modal-content">
                <div class="modal-header">
                    <span class="close">&times;</span>
                    <h2>Agregar un grupo nuevo</h2>
                </div>
                <div class="modal-body">
                    <form action="crudMaterias" method='post'>
                        <label>Agregue un nombre a la materia </label>
                        <input class="input" type="text" name="nombre" placeholder="Nombre" />
                        <label>Seleccione el grado</label>
                        <select class="input" name="grado">
                            <option value="5">5to</option>
                            <option value="6">6to</option>
                        </select>
                        <label>Seleccione el Docente</label>
                    <!--input class="input" type="text" name="idGrupo" placeholder="Grupo" /-->
                    <select name="idDocente" class="input2">
                        <%
                            int idD =0;
                            String nombrD="";
                            String apD = "";
                            String nombreCom="";
                        List<Docente> doc = new ArrayList<>();
                        doc = Docente.obtenerTodos();  
                
                    for (int i=0;i<doc.size();i++)
                    {
                       idD = doc.get(i).getIdDocente();
                       nombrD = doc.get(i).getApPaternoD();
                       apD = doc.get(i).getNombreD();
                       nombreCom = nombrD + " " + apD;
                       out.println("<option value="+ idD +"> "+ nombreCom +"</option>");
                    }            
                %>
                        <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar"/>
                        <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                    </form>
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
                <h2>Editar un Alumno!</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body">
                    <form action="crudMaterias" method='post'>
                        <label>ID de la materia </label>
                        <input class="input" id='MyId' name='idMateria' value= '' disabled/>
                        <input class="input" id='MyId2' name='idMat' value= '' type="hidden" /> 
                        <label>Nombre de la materia </label>
                        <input class="input" id='nomb' name="nombre" value = ""/>
                        <label>Grado</label>
                        <input class="input" id='grad' name="grado" value = ""/>
                        <label>ID del Docente</label>
                        <input class="input" id='idDoc' name='idDocente' value= '' disabled/>
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

