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
<%@ page import = "modelos.Grupos"%>
<%@ page import ="modelos.Consulta2Tablas"%>
<%@ page import= "modelos.GruposMateria"%>

<!DOCTYPE html>
<html>
    <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>K'ulu' - Materias Maestro</title>
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
                  String nombreDoc = Docente.obtenerPorIdUsuario(idU).getNombreD();
                  String ApPaterno = Docente.obtenerPorIdUsuario(idU).getApPaternoD(); 
                  int idDocente = Docente.obtenerPorIdUsuario(idU).getIdDocente();
                  out.println(nombreDoc +"!");
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
                    <form action="crudMaterias" method='post'>
                        <label>Agregue un nombre a la materia </label>
                        <input class="input" type="text" name="nombre" placeholder="Nombre" />
                        <label>Seleccione el grado</label>
                        <select class="input" name="grado">
                            <option value="5">5to</option>
                            <option value="6">6to</option>
                        </select>
                        <label>Docente:</label>
                        <input class="input" type="text" name="nombre" value="<%=nombreDoc + ApPaterno%>" disabled="">
                        <input class="input" type="text" name="idDocente" value="<%=idDocente%>" style="display:none">
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
                        int id =0;
                        String nombreMat="";
                        String grado="", idGrupMateria="";
                        
                    List<Materia> mat = new ArrayList<>();
                    mat = Materia.obtenerTodos();  

                    for (int i=0;i<mat.size();i++)
                    {
                       String nombreMateria="";
                       idGrupMateria="";
                       id = mat.get(i).getIdMateria();
                       nombreMat = mat.get(i).getNombre();
                       grado = mat.get(i).getGrado();
                       
                       List<Consulta2Tablas> cons = new ArrayList<>();
                       cons = Consulta2Tablas.obtenerPorId(id);
                        for (int j=0;j<cons.size();j++){
                            nombreMateria += cons.get(j).getletra() + " ";
                            idGrupMateria += cons.get(j).getidGMat() + " "; //En este arreglo se guarda tanto el id como el nombre
                            idGrupMateria += cons.get(j).getletra();//Y este es el que se le manda al metodo quitar para que muestre en la tabla el id y nombre
                        }   
                       out.println("<tr>");
                            out.println("<td><a href=\"maestro-materias-bloques.jsp?variable="+id+"\"> "+ nombreMat +"</a></td>");
                            out.println("<td>"+ grado +"</td>");
                            out.println("<td> "+ nombreMateria +" </td>");
                            out.println("<td><button class='boton' id='myBtn2' onClick='getMateria("+ id + ", \""+ nombreMat  + "\",\"" + grado +"\", "+ idDocente +")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");
                            
                            out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                                out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                                out.println("<input type =\"button\" class=\"boton\" onclick=\"javascript:eliminar('crudMaterias', " + id +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
                            out.println("</td></form>");
                            out.println("<td><button class=\"boton\" id=\"myBtn3\" onClick='agregar("+ id +")' name=\"agregarGrupo\"><i class=\"fa fa-plus\" aria-hidden=\"true\"></i> Asociar Grupo</button>");
                            out.println("<button class=\"boton\" id=\"myBtn3\" onClick='quitar(\"" +idGrupMateria+ "\")' name=\"quitarGrupo\"><i class=\"fa fa-trash\" aria-hidden=\"true\"></i> Desasociar Grupo</button></td>");
                        out.println("</tr>");
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
                    <form action="crudMaterias" method='post'>
                        <label>ID de la materia </label>
                        <input class="input" id='MyId' name='idMateria' value= '' disabled/>
                        <input class="input" id='MyId2' name='idMat' value= '' type="hidden" /> 
                        <label>Nombre de la materia </label>
                        <input class="input" id='nomb' name="nombre" value = ""/>
                        <label>Seleccione el grado</label>
                        <select class="input" name="grado" id='grad'>
                            <option value="5">5to</option>
                            <option value="6">6to</option>
                        </select>
                        
                        <label>ID del Docente</label>
                        <input class="input" id='idDoc' name='idDocente' value= '' disabled/>
                        <input class="modal-boton active-boton" type="submit" value="Editar" name="editar"/>
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
          <li class="active"><a href="quienes_somos.jsp">¿Quiénes somos? </a></li>
          <li><a href="kulu_business.jsp">K'ulu' for business</a></li>
          <li><a href="contacto.jsp">Contacto</a></li>
          <li><a href="soporte.jsp">Soporte</a></li>
        </ul>
      </nav>
    </div>
  </footer>
    <!-- The Modal aparece cuando se selecciona el boton asociar grupo-->
    <div id="Agregargrupo" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span><!--Como aparece primero es el primero en span-->
                <h2>Agregar un grupo a materia!</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body">
                    <br>
                    <%
                        out.println("<form action=\"crudGruposMaterias\" method='post' name=\"f1\"> ");
                        out.println("<input class=\"input\" id=\'var2\' name='variable2' value= '' type=\"hidden\" />");
                        int idG =0;
                        String nombreG="";

                        List<Grupos> grup = new ArrayList<>();
                        grup = Grupos.obtenerTodos();  

                        for (int i=0;i<grup.size();i++)
                        {
                            idG = grup.get(i).getIdGrupo();
                            nombreG = grup.get(i).getLetra();
                            out.println("<div class=\"contenedor\">");
                            out.println("<label> Grupo "+ nombreG +"</label>");
                            out.println("<input type=\"checkbox\" name='datos' value="+ idG +">");
                            out.println("<span class=\"checkmark\"></span>");
                            out.println("</div>");
                        }
                        out.println("<input class=\"modal-boton active-boton\" type=\"submit\" value=\"Agregar Grupo\" name=\"agregar\"/>");
                        out.println("<input class=\"modal-boton\" type=\"submit\" value=\"Cancelar\" name=\"cancelar\">");
                        out.println("<input type =\"button\" class=\"boton\" onclick='limpiar()' value=\"Limpiar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
                        out.println("<div class=\"clear\"></div><br>");
                        out.println("</form>");
                    %>
                </div>
            </div>
        </div>
    </div>
                <!--Este modal sirve para cuando se seleccione desasociar grupo-->
    <div id="des-asociar" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span><!--Como aparece primero es el primero en span-->
                <center><h2>Quitar un grupo a materia!</h2>
                <h4>Seleccione un elemento de la lista a quitar</h4></center>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <br>
                    <form action="crudGruposMaterias" method='post' name="f1" id="formulario">
                        <table id="miTabla" border="1">
                        </table>
                        
                        <label>Introduzca el id del grupo que desee eliminar</label>
                        <input class="input" id='var3' name='variable3' value= '' type="text">  
                        <br>
                        <input class="modal-boton active-boton" type="submit" value="Quitar Grupo" name="deshacer" id="btn"/>
                        <input class="modal-boton" type= "submit" value="Cancelar" name="cancelar"/>
                        <div class="clear"></div><br>                        
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="js/modal.js"></script>
    <script src="js/funciones.js"></script>
</body>

</html>

