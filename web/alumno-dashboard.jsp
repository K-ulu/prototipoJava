<%-- 
    Document   : alumno-dashboard
    Created on : 3/12/2017, 01:47:05 AM
    Author     : gerar
--%>
<%@page import="modelos.consultaAlumnosMateria"%>
<%@page import="modelos.alumnos_materia"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page session="true" %>
<%@page import="modelos.Alumno"%>
<%@page import="modelos.Materia"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <title>Maestro Dashboard</title>
        <link rel="stylesheet" href="css/font-awesome.min.css">
        <link rel="stylesheet" href="css/alumnoDashboard.css">
        <!-- <link rel="stylesheet" href="css/nav.css"> -->
        <!-- <link href="https://fonts.googleapis.com/css?family=Acme" rel="stylesheet"> -->
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
                    %>                        
                    </a></li>
                </ul>
            </div>
        </div>
        <hr class="style13">
        <nav class="nav-extras">
            <ul>
                <li><a href="alumno-dashboard.jsp">Dashboard</a></li>
                <li><a href="alumno-materias-bloques.jsp">Bloques</a></li>
                <li><a href="alumno-materia-bloques-tarea.jsp">Tarea</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <div class="alumno">
            <img src="img/slider-alumno.png" alt="slider-alumno">
        </div>
        <!--Empieza a escrbir aquí -->
        <div class="materias">
            <h1>Mis Materias</h1>
            <%
                int idMateria =0;
                String nombreMateria="";

                List<consultaAlumnosMateria> aluMateria = new ArrayList<>();
                aluMateria = consultaAlumnosMateria.obtenerPorId(alumno);  

                for (int i=0;i<aluMateria.size();i++) {
                    idMateria = aluMateria.get(i).getMateriaidMateria();
                    nombreMateria = aluMateria.get(i).getNombre();
                    out.println("<div class=\"materia\">");
                    out.println("<h2><a href=\"alumno-materias-bloques.jsp?variable="+idMateria+"\">"+ nombreMateria +"</a></h2>");
                    out.println("</div>");                               
                }      
            %>
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
    </body>
</html>
