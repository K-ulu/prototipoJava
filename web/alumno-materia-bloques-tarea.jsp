<%-- 
    Document   : alumno-materia-bloques-tarea
    Created on : 15/12/2017, 02:25:30 PM
    Author     : Norma
--%>

<%@page import="modelos.TareaAsignada"%>
<%@page import="modelos.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>K'ulu' - Alumno Tareas</title>
    <link rel="stylesheet" href="css/font-awesome.min.css">
<!--    <link rel="stylesheet" href="css/alumno-materia-bloques-tarea.css">-->
<link rel="stylesheet" href="css/alumnoDashboard.css">
    <!-- <link rel="stylesheet" href="css/nav.css"> -->
    <!-- <link href="https://fonts.googleapis.com/css?family=Acme" rel="stylesheet" -->
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
                    <li><a class="active" href="alumno-dashboard.jsp"> ¡Hola
                    <% 
                      //recuperamos los datos de la sesion
                      HttpSession sesionStatus = request.getSession();
                      //out.println("id verificacion "+sesionStatus.getId());
                      int id = (int)sesionStatus.getAttribute("idUsuario");
                      String tipo = (String)sesionStatus.getAttribute("tipoUsuario");
                      //out.println("Sesion obtenida id:"+id+" tipo: "+tipo);
                      out.println(Alumno.obtenerPorIdUsuario(id).getNombreA()+"!");
                      int alumno = Alumno.obtenerPorIdUsuario(id).getIdAlumno();
                      int idTareaAsignada = -1, idMateria = -1;  
                        String nombreTarea="", descripcion="", fechaEntrega="";
                    try{
                        idTareaAsignada = Integer.parseInt(request.getParameter("variable")); 
                        TareaAsignada tarea = null;
                        tarea = TareaAsignada.obtenerPorId(idTareaAsignada);
                        idMateria = tarea.getIdMateria();
                        nombreTarea = tarea.getNombreTarea();
                        descripcion = tarea.getDescripcionT();
                        fechaEntrega = tarea.getFechaEntrega();
                    }catch (Exception e){
                        idMateria = 0;
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
                <li><a href="alumno-dashboard.jsp">Dashboard</a></li>
                <li><a href="alumno-materias-bloques.jsp?variable=<%=idMateria%>">Bloques</a></li>
                <li><a href="alumno-materia-bloques-tarea.jsp?variable=<%=idTareaAsignada%>">Tarea</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <div class="container">
    <div class="tarea">
        <%
            if (idTareaAsignada != -1){
                out.println("<h2>"+nombreTarea+"</h2>");
                out.println("<h3> Fecha de Entrega: "+fechaEntrega+"</h3>");
                out.println("<p>"+descripcion+"</p>");
                out.println("<form action=\"cargarTarea\" enctype=\"multipart/form-data\" method=\"POST\">");
                out.println("<input class=\"input\" type=\"hidden\" name=\"idAlumno\" value=\"" +alumno+ "\">");
                out.println("<input type=\"text\" name=\"idTarea\" value=\""+ idTareaAsignada+ "\" style=\"display:none\">");                                 
                out.println("<label for=\"archivo\" id=\"bArch\">Seleccione archivo</label>");
                out.println("<input class=\"inputfile\"  type=\"file\" name=\"archivo\" id=\"archivo\">");
                out.println("<input class=\"boton\" type=\"submit\" value=\"Agregar\" name=\"agregar\">");
                out.println("<div class=\"clear\"></div>");
                out.println("</form>");
                out.println("<a href=\"alumno-materias-bloques.jsp?variable="+idMateria+"\"><button class=\"boton\">Volver</button></a>");
            }
        %>
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
                    <li><a href="">Afiliados</a></li>
                    <li><a href="">Contactos</a></li>
                    <li><a href="">Soporte</a></li>
                </ul>
                </div>
            </nav>
        </div>
    </footer>
    <script src="js/file_select.js"></script>
</body>

</html>

