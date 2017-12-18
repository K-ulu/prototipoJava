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
    <title>K'ulu' - Dashboard Maestro</title>
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <!--Css para la pagina (El cuerpo y footer)-->
    <link rel="stylesheet" href="css/maestro-mis-documentos.css">
   <!--  <link href="https://
        fonts.googleapis.com/css?family=Acme" rel="stylesheet"> -->
    <!--CSS para el header-->
    <link rel="stylesheet" href="css/nav-christ.css">
    <script src="js/sesion.js"></script>
    <link rel="stylesheet" href="css/jquery-ui.min.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-ui-web.min.js"></script>
</head>
<body onMouseOver="hayActividad()">
    <div class="navbar">
        <div class="nav-logo">
            <a href="index.jsp.html"><img src="img/kulu_logo_160.png"></a>
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
        <h2 class="titulo">Mis Alumnos</h2>
        <div class="herramientas">
            <div class="add">
                <button class="boton" id="myBtn"><i class="fa fa-plus" aria-hidden="true"></i> Agregar</button>
            </div>
            <!--div class="form">
                <form action="">
                    <input class="busqueda" type="search" name="buscar" placeholder="Buscar...">
                    <button class="boton">Buscar</button>
                    <button class="boton">Limpiar</button>
                </form>
            </div-->
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
                    alumnito = Alumno.obtenerPorIdDocente(idDoc);  
                
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
        <div class="modal-content" style="width: 380px;">
            <div class="modal-header">
                <span class="close">&times;</span>
                <center><h2>Agregar un Alumno nuevo!</h2></center>
            </div>
            <div class="modal-body">
                <div class="modal-body2">
                    <form action="crudAlumno" method='post'>
                        <input class="input" type="hidden" name="idDocente" value="<%=idDoc%>" />  
                        <input class="input" type="hidden" name="idAlumno" value="0"/>                      
                        <label>Nombre del alumno </label>
                        <input class="input" type="text" name="nombre" placeholder="Nombre" style="width: 300px;"/>
                        <label>Apellido Paterno del alumno</label>
                        <input class="input" type="text" name="apellidoP" placeholder="apellido" style="width: 300px;"/>
                        <label>Apellido Materno del alumno</label>
                        <input class="input" type="text" name="apellidoM" placeholder="apellido" style="width: 300px;"/>
                        
                        <label class="input2">Genero del alumno</label>
                        <div class="input">
                            <select class="input2" name="generoA">
                                <option value="femenino">Femenino</option>
                                <option value="masculino">Masculino</option>
                            </select>
                            <label class="input2" size="6" style="float: left; display: inline-block;">Curp</label>
                            <input class="input2" type="text" name="curp" placeholder="curp" style="width: 120px;"/>
                        </div>
                        <br><br><br>
                        <label>Fecha de nacimiento</label>
                        <input class="campo" type="text" name="fecha" id="fecha" readonly="readonly" placeholder="Fecha Nacimiento">
                        <br>
                        <div class="input">
                            <label class="campo" style="float: left; display: inline-block;">Seleccione el Grupo</label>
                            <select name="idGrupo" class="campo" style="width: 170px;">
                                <%
                                    int idG =0;
                                    String nombrG="";
                                List<Grupos> grupito = new ArrayList<>();
                                grupito = Grupos.obtenerTodosID(idDoc);  

                            for (int i=0;i<grupito.size();i++)
                            {
                               idG = grupito.get(i).getIdGrupo();
                               nombrG = grupito.get(i).getLetra();

                               out.println("<option value="+ idG +"> Grupo "+ nombrG +"</option>");
                            }            
                        %>
                            </select> 
                        </div>
                       <!--Apartir de aqui son los campos para llenar los datos del usuario-->    
                        <div class="input">
                            <label>Correo Electronico</label>
                            <input class="input2" type="text" name="correo" id="correo"placeholder="Correo Electrónico" style="float: left; display: inline-block; width: 137px;">
                            <input class="input2" type="text" name="correo2" id="correo2" placeholder="Confirmar correo" style="width: 137px;">
                        </div>
                        
                        <div class="input">
                            <label>Contrasenia</label>
                            <input class="input2" type="password" name="contrasena" id="contrasena"placeholder="Contraseña" style="float: left; display: inline-block; width: 137px;">
                            <input class="input2" type="password" name="contrasena2" id="contrasena2" placeholder="Confirmar Contraseña" style="width: 137px;">
                        </div>
                        <div class="input">    
                            <input class="modal-boton active-boton" type="submit" value="Agregar" name="agregar">
                            <input class="modal-boton" type="submit" value="Cancelar" name="cancelar">
                        </div>
                            <div class="clear"></div>
                    </form>
                </div>
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
    
    <!-- Aparece cuando se selecciona editar-->
    <div id="edit" class="modal">
        <div class="modal-content">
            <div class="modal-header">
                <span class="close editarclose">&times;</span>
                <center><h2>Editar un Alumno!</h2></center>
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
                        <!--input class="input" id='geni' name="genero" value= ''/-->
                        <select class="input" name="genero" id='geni'>
                            <option value="femenino">Femenino</option>
                            <option value="masculino">Masculino</option>
                        </select>
                        <label>Fecha de nacimiento del alumno</label>
                        <input class="campo" type="text" name="fechaNac" id="fechaNac"  placeholder="Fecha Nacimiento">
                        <label class="input">Curp</label>
                        <input class="input" id='curpito' name="curp" value= ''/>
                        <label>id del Grupo</label>
                        <select name="idGrupo" class="input2" id='idGroup'>
                        <%
                            grupito = new ArrayList<>();
                            grupito = Grupos.obtenerTodosID(idDoc);  

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
    <script>        
        jQuery(function($){
            $.datepicker.regional['es'] = {
                    closeText: 'Cerrar',
                    prevText: '&#x3c;Ant',
                    nextText: 'Sig&#x3e;',
                    currentText: 'Hoy',
                    monthNames: ['Enero','Febrero','Marzo','Abril','Mayo','Junio',
                    'Julio','Agosto','Septiembre','Octubre','Noviembre','Diciembre'],
                    monthNamesShort: ['Ene','Feb','Mar','Abr','May','Jun',
                    'Jul','Ago','Sep','Oct','Nov','Dic'],
                    dayNames: ['Domingo','Lunes','Martes','Mi&eacute;rcoles','Jueves','Viernes','S&aacute;bado'],
                    dayNamesShort: ['Dom','Lun','Mar','Mi&eacute;','Juv','Vie','S&aacute;b'],
                    dayNamesMin: ['Do','Lu','Ma','Mi','Ju','Vi','S&aacute;'],
                    weekHeader: 'Sm',
                    dateFormat: 'yy-mm-dd',
                    firstDay: 1,
                    isRTL: false,
                    showMonthAfterYear: false,
                    yearSuffix: '',
                    yearRange: "1950:2017"
                };
            $.datepicker.setDefaults($.datepicker.regional['es']);
        });
        $(document).ready(function() {
            $("#fecha").datepicker({
                changeMonth:true,
                changeYear:true
            });
         });
         $(document).ready(function() {
            $("#fechaNac").datepicker({
                changeMonth:true,
                changeYear:true
            });
         });


    </script>
</body>
</html>
