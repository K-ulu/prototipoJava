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
    <title>K'ulu' - Tareas Maestro</title>
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
          <a href="index.html"><img src="img/kulu_logo_160.png"></a>
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
                <h2>Agregar una nueva tarea</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudTareas_asignadas" method='post' name="formu">
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
                        
                        <label>Seleccione el bloque</label>
                        <select class="input" name="bloque" id="bloque">
                            <%
                                List<Bloque> bloq = new ArrayList<>();
                                bloq = Bloque.obtenerTodos();  
                                int bloqID = 0;
                                for (int i=0;i<bloq.size();i++) {   
                                    bloqID = bloq.get(i).getIdBloque();
                                    out.println("<option value=\"" + bloqID+ "\">"+bloq.get(i).getNombreBloque()+"</option>");
                                }   
                            %>
                        </select>
                        
                        <label>Docente</label> 
                        <input class="input" type="text" name="docente" value=" <%= NombreDocente %> " disabled/>
                        <input class="input" type="text" name="docenteID" value=" <%= idSesionD %> " style="display:none"/>
                        
                        <label>Fecha de entrega</label>
                        <div class="input">
                            <input class="input2" type="text" name="anio" placeholder="año" size="5"/>                        
                            <select name="mes" class="input2">
                                <option value="01">Enero</option>
                                <option value="02">Febrero</option>
                                <option value="03">Marzo</option>
                                <option value="04">Abril</option>
                                <option value="05">Mayo</option>
                                <option value="06">Junio</option>
                                <option value="07">Julio</option>
                                <option value="08">Agosto</option>
                                <option value="09">Septiembre</option>
                                <option value="10">Octubre</option>
                                <option value="11">Noviembre</option>
                                <option value="12">Diciembre</option>                        
                            </select>

                            <input class="input2" type="text" name="dia" placeholder="dia" size="5"/>                        
                        </div>
                        
                        <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar"/>
                        <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
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
      <center><h2 class="titulo">TAREAS ASIGNADAS</h2></center>
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
                      <th>Bloque</th>
                      <th>Fecha Entrega</th>
                      <th colspan="3">Acciones</th>
                    </tr>
                    <tr>
                      <%
                        int id =0, idBloque=0, idMat=0;
                        String nombreTarea="", fech="";
                        String descripcionT="";
                        String nombreM="", nombreB="";
                        List<TareaAsignada> tarea = new ArrayList<>();
                        tarea = TareaAsignada.obtenerTodos();  
                
                        for (int i=0;i<tarea.size();i++) {
                            id = tarea.get(i).getIdTareaAsignada();
                            nombreTarea = tarea.get(i).getNombreTarea();
                            descripcionT = tarea.get(i).getDescripcionT();
                            idBloque = tarea.get(i).getIdBloque();
                            fech = tarea.get(i).getFechaEntrega();
                            if (idBloque!=0)
                                nombreB = Bloque.obtenerPorId(idBloque).getNombreBloque();
                           idMat = tarea.get(i).getIdMateria();
                           nombreM = Materia.obtenerPorId(idMat).getNombre();

                            out.println("<tr>");
                            out.println("<td>"+nombreM+"</td>");
                            out.println("<td><a href=\"tareas_entregadas.jsp?variable="+id+"\">"+ nombreTarea +"</a></td>");
                            out.println("<td>"+descripcionT+"</td>");
                            if (idBloque!=0)
                                out.println("<td>"+nombreB+"</td>");
                            else
                               out.println("<td></td>"); 
                            out.println("<td>"+fech+"</td>");
                            out.println("<td><button class='boton' id='myBtn2' onClick='getTareas_asignadas("+ id + ",\"" + nombreTarea +"\", \""+ descripcionT +"\", "+ idMat +", "+ idBloque + ", " +idSesionD+", \""+fech+"\")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");
                            out.println("<form name=\"formulario"+i+"\" action=\"servletDondeIr\" method=\"Post\">");
                            out.println("<td><input type=\"text\" name=\"variable1\" placeholder=\"numero3\" hidden= \"\" id=\"var\"/>"); 
                            out.println("<input type =\"button\" onclick=\"javascript:eliminar('crudTareas_asignadas', " + id +");\" value=\"Eliminar\" style=\"border-radius: 5px; font-size: 15px; padding: 10px;margin: 5px;\"/>");
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
    <!-- The Modal aparece cuando se selecciona editar-->
    <div id="edit" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span><!--Como aparece tercero es el tercero en span-->
                <h2>Editar datos de una tarea!</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudTareas_asignadas" method='post'>
                        <label>ID de la tarea </label>
                        <input class="input" id='MyId' name='idTarea' value= '' disabled/>
                        <input class="input" id='MyId2' name='idTar' value= '' type="hidden" /> 
                        <label>Nombre de la tarea </label>
                        <input class="input" id='nomb' name="nombre" value = ""/>
                        <label>Descripción de la tarea</label>
                        <input class="input" id='descripcion' name="descripcion" value = ""/>
                        <label>id de la materia</label>
                        <select class="input" name="materia" id="materia2">
                            <%
                                for (int i=0;i<mat.size();i++) {   
                                    matID = mat.get(i).getIdMateria();
                                    out.println("<option value=\"" + matID+ "\">"+mat.get(i).getNombre()+"</option>");
                                }
                            %>
                        </select>
                        <!--label>id del bloque</label>
                        <input class="input" id='IDbloque' name='IDbloque' value= ''/-->
                        <label>Bloque</label>
                        <select class="input" name="IDbloque" id="IDbloque">
                            <%
                                bloq = Bloque.obtenerTodos();  
                                for (int i=0;i<bloq.size();i++) {   
                                    bloqID = bloq.get(i).getIdBloque();
                                    out.println("<option value=\"" + bloqID+ "\">"+bloq.get(i).getNombreBloque()+"</option>");
                                }   
                            %>
                        </select>
                        <label>Docente</label> 
                        <input class="input" type="text" name="docente" value=" <%= NombreDocente %> " disabled/>
                        <input class="input" type="text" name="docenteID" value="" id="docentID" style="display:none"/>
                        
                        <label>Fecha de entrega</label>
                        <input class="input" id='fecha' name="fechaE" value= ''/>
                        
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
        <script src="js/modal.js"></script>
        <script src="js/funciones.js"></script>
</body>

</html>

