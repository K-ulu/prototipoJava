<%-- 
    Document   : maestro-materias-bloques
    Created on : 8/12/2017, 03:49:29 PM
    Author     : Norma
--%>

<%@page session="true" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@ page import= "modelos.Bloque"%>
<%@ page import= "modelos.Materia"%>
<%@page import="modelos.Docente"%>

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
                  
                int idMateria = Integer.parseInt(request.getParameter("variable")); 
                Materia materia = null;
                materia = Materia.obtenerPorId(idMateria);
                String nombreMateria = materia.getNombre();
              %>
                </a></li>
          </ul>
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
                    <form action="crudBloques" method='post'>
                        <input type="text" name="idMateria" value="<%=idMateria %>" style="display:none">
                        <label>Agregue un nombre al Bloque </label>
                        <input class="input" type="text" name="nombre" placeholder="Nombre" />
                        <label>Introduzca una breve descripcion</label>
                        <input class="input" type="text" name="descripcion" placeholder="Descripcion" />
                        <label>Introduzca la unidad</label>
                        <input class="input" type="text" name="unidad" placeholder="Unidad">
                        <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar"/>
                        <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                        <br>
                    </form>
                </div>
            </div>
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
        <center> <h1 class="titulo"> <%= nombreMateria %> </h1></center>
        <h2 class="titulo">Bloques</h2>
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
                        <th>Nombre Bloque</th>
                        <th>Descripción</th>
                        <th>Unidad</th>
                        <th colspan="3">Acciones</th>
                    </tr>
                    <%
                        int id =0, unidad=0;
                        String nombreBloque="", descripcion="";
                        
                    List<Bloque> bloq = new ArrayList<>();
                    bloq = Bloque.obtenerTodosID(idMateria);  

                    for (int i=0;i<bloq.size();i++){
                       id = bloq.get(i).getIdMateria();
                       unidad = bloq.get(i).getUnidad();
                       nombreBloque = bloq.get(i).getNombreBloque();
                       descripcion = bloq.get(i).getDescripcion();
                       int idBloq = bloq.get(i).getIdBloque();
                       
                       //if(idMateria == id){
                            out.println("<tr>");
                                 out.println("<td>"+ nombreBloque +"</td>");
                                 out.println("<td> "+ descripcion +" </td>");
                                 out.println("<td> "+ unidad +" </td>");
                                 out.println("<td><button class='boton' id='myBtn2' onClick='getBloque("+idBloq+", "+ unidad  + ",\"" + nombreBloque +"\", \""+ descripcion +"\")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");
                                 out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                                    out.println("<input type=\"text\" name=\"idMateria\" value=\""+ idMateria+ "\" style=\"display:none\">");                                 
                                    out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                                    out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudBloques', " + idBloq +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
                                 out.println("</td></form>");
                             out.println("</tr>");
                         //}
                    }
                %>
            </table>
        </div>
    </div>
  <!-- The Modal aparece cuando se selecciona editar-->
    <div id="edit" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span><!--Como aparece tercero es el tercero en span-->
                <h2>Editar datos de una materia!</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudBloques" method='post'>
                        <input type="text" name="idBloque" value="" style="display:none" id="idBloq">
                        <input type="text" name="idMateria" value="<%=idMateria %>" style="display:none">
                        <label>Nombre del Bloque </label>
                        <input class="input" type="text" name="nombre" placeholder="Nombre" id="bloque"/>
                        <label>Descripcion</label>
                        <input class="input" type="text" name="descripcion" placeholder="Descripcion" id="descripcion"/>
                        <label>Unidad</label>
                        <input class="input" type="text" name="unidad" placeholder="Unidad" id="unidad">
                        <input class="modal-boton active-boton" type="submit" value="Editar" name="editar"/>
                        <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                        <br>
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


