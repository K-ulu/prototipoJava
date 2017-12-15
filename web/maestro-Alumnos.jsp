<%-- 
    Document   : maestro-Alumnos
    Created on : 15/11/2017, 10:38:02 PM
    Author     : Norma
--%>

<%@page session="true" %>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.LinkedList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import = "modelos.Alumno"%> 
<%@ page import = "modelos.Grupos"%> 
<%@page import="modelos.Docente"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Maestro Dashboard</title>
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!--Css para la pagina (El cuerpo y footer)-->
    <link rel="stylesheet" href="css/maestro-mis-documentos.css">
   <!--  <link href="https://
        fonts.googleapis.com/css?family=Acme" rel="stylesheet"> -->
    <!--CSS para el header-->
    <link rel="stylesheet" href="css/nav-christ.css">
    <script src="js/sesion.js"></script>
</head>
<body onMouseOver="hayActividad()">
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
            <li><a href="tareas_asignadas.jsp">Admin tareas</a></li>
            <li><a href="maestro-mis-documentos.jsp">Mis documentos</a></li>
            <li><a href="maestro-contenido-multimedia.html">Admin contenido Mult.</a></li>
        </ul>
    </nav>
    <hr class="style13">
    <!--Empieza a escrbir aquí -->
    <div class="container">
        <h2 class="titulo">Mis Alumnos</h2>
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
                    <th>Nombre Alumno</th>
                    <th>Apellido Alumno</th>
                    <th>Genero</th>
                    <th>Curp</th>
                    <th>Grupo</th>
                    <th colspan="3">Acciones</th>
                </tr>
                <%
                    int id =0;
                    String ap="";
                    String am="";
                    String nombr="";
                    String gen="";
                    String fech="";
                    String miCurp="";
                    int grupo=0;
                    List<Alumno> alumnito = new ArrayList<>();
                    alumnito = Alumno.obtenerTodos();  
                
                    for (int i=0;i<alumnito.size();i++)
                    {
                       id = alumnito.get(i).getIdAlumno();
                       ap = alumnito.get(i).getApPaternoA();
                       am = alumnito.get(i).getApMaternoA();
                       nombr = alumnito.get(i).getNombreA();
                       gen = alumnito.get(i).getGeneroA();
                       fech = alumnito.get(i).getFechaNacimientoA();
                       miCurp = alumnito.get(i).getCURP();
                       grupo = alumnito.get(i).getIdGrupo();
                       
                       String nombreG = Grupos.obtenerPorId(grupo).getLetra();
                       
                        out.println("<tr>");
                        out.println("<td>"+nombr+"</td>");
                        out.println("<td>"+ ap +"</td>");
                        out.println("<td>"+gen+"</td>");
                        out.println("<td>"+miCurp+"</td>");
                        out.println("<td>"+nombreG+"</td>");
                        out.println("<td><button class='boton' id='myBtn2' onClick='getAlumno("+ id + ",\"" + ap +"\", \""+ am +"\", \""+ nombr +"\", \""+ gen + "\", \""+ fech + "\", \""+ miCurp + "\", \""+ grupo + "\")' name='editar'><i class='fa fa-pencil' aria-hidden='true'></i>Editar </button></td>");
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
    <!-- Modal content-->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close">&times;</span>
                <h2>Agregar un Alumno nuevo!</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudAlumno" method='post'>
                        <input class="input" type="hidden" name="idAlumno" value="0"/>                      
                        <label>Nombre del alumno </label>
                        <input class="input" type="text" name="nombre" placeholder="Nombre" />
                        <label>Apellido Paterno del alumno</label>
                        <input class="input" type="text" name="apellidoP" placeholder="apellido" />
                        <label>Apellido Materno del alumno</label>
                        <input class="input" type="text" name="apellidoM" placeholder="apellido" />
                        <label>Genero del alumno</label>
                        <select class="input" name="generoA">
                            <option value="femenino">Femenino</option>
                            <option value="masculino">Masculino</option>
                        </select>

                        <label>Fecha de nacimiento</label>
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

                        <label class="input">Curp</label>
                        <input class="input" type="text" name="curp" placeholder="curp" />
                        <label>id del Grupo</label>
                        <!--input class="input" type="text" name="idGrupo" placeholder="Grupo" /-->
                        <select name="idGrupo" class="input2">
                            <%
                                int idG =0;
                                String nombrG="";
                            List<Grupos> grupito = new ArrayList<>();
                            grupito = Grupos.obtenerTodos();  

                        for (int i=0;i<grupito.size();i++)
                        {
                           idG = grupito.get(i).getIdGrupo();
                           nombrG = grupito.get(i).getLetra();

                           out.println("<option value="+ idG +"> Grupo "+ nombrG +"</option>");
                        }            
                    %>
                        </select>
                        <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar">
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
    
    <!-- Aparece cuando se selecciona editar-->
    <div id="edit" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span>
                <h2>Editar un Alumno!</h2>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudAlumno" method='post'>
                        <label>ID del alumno </label>
                        <input class="input" id='MyId' name='idAlumno' value= '' disabled/>
                        <input class="input" id='MyId2' name='idAlum' value= '' type="hidden" />                    
                        <label>Apellido Paterno del alumno </label>
                        <input class='input' id='apd' name='apellidoP' value= ''/>
                        <label>Apellido Materno del alumno</label>
                        <input class="input" id='apm' name="apellidoM" value= ''/>
                        <label>Nombre del alumno </label>
                        <input class="input" id='nomb' name="nombre" value= ''/>
                        <label>Genero del alumno</label>
                        <input class="input" id='geni' name="genero" value= ''/>
                        <label>Fecha de nacimiento del alumno</label>
                        <input class="input" id='fecha' name="fechaNac" value= ''/>
                        <label class="input">Curp</label>
                        <input class="input" id='curpito' name="curp" value= ''/>
                        <label>id del Grupo</label>
                        <select name="idGrupo" class="input2" id='idGroup'>
                        <%
                            grupito = new ArrayList<>();
                            grupito = Grupos.obtenerTodos();  

                            for (int i=0;i<grupito.size();i++)
                            {
                               idG = grupito.get(i).getIdGrupo();
                               nombrG = grupito.get(i).getLetra();

                               out.println("<option value="+ idG +"> Grupo "+ nombrG +"</option>");
                            }            
                        %>
                        </select>

                        <input class="modal-boton active-boton" type="submit" value="Editar" name="editar">
                        <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        <div class="clear"></div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <script src="js/modal.js"></script>
    <script src="js/funciones.js"></script>
</body>
</html>
