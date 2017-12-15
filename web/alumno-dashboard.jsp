<%-- 
    Document   : alumno-dashboard
    Created on : 3/12/2017, 01:47:05 AM
    Author     : gerar
--%>
<%@page session="true" %>
<%@page import="modelos.Alumno"%>
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
                    %>                        
                    </a></li>
                </ul>
            </div>
        </div>
        <hr class="style13">
        <nav class="nav-extras">
            <ul>
                <li><a href="alumno-dashboard.html">Dashboard</a></li>
                <li><a href="alumno-materia-bloques.html">Bloques</a></li>
                <li><a href="alumno-materia-bloques-tarea.html">Tarea</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <div class="alumno">
            <img src="img/slider-alumno.png" alt="slider-alumno">
        </div>
        <!--Empieza a escrbir aquí -->
        <div class="materias">
            <h1>Mis Materias</h1>
            <div class="materia">
                <h2><a href="alumno-materia-bloques.html">Matematicas</a></h2>
                <h3>Profesor: Ulises Javier</h3>
            </div>
            <div class="materia">
                <h2><a href="alumno-materia-bloques.html">Ciencias Naturales</a></h2>
                <h3>Profesor: Martha Sanchez</h3>
            </div>
            <div class="materia">
                <h2><a href="alumno-materia-bloques.html">Geografía</a></h2>
                <h3>Profesor: Rosaura Gomez</h3>
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
    </body>
</html>
