<%-- 
    Document   : soporte
    Created on : Dec 15, 2017, 1:25:48 PM
    Author     : Christopher Paredes
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <!--Hoja de estilos del encabezado-->
        <link rel="stylesheet" href="css/nav-christ.css">
        <!--Hoja de estilos del slider-->
        <link rel="stylesheet" href="css/estilos.css">
        <!--Hoja de estilos de la página (El cuerpo y footer)-->
        <link href="css/mycss.css" rel="stylesheet" type="text/css" />
        <script src="js/jquery-3.1.0.min.js"></script>
        <script src="js/main.js"></script>
        <title>K'ulu' - Soporte</title>
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
                    <li><a href="login.jsp">Inicia sesión</a></li>
                    <li><a class="active" href="registro.jsp">Regístrate</a></li>
                </ul>
            </div>
        </div>
        <div id="contenido">
            <!--Parte para el slider-->
            <div class="slideshow">
                <ul class="slider">
                    <li><a href="soporte.jsp">
                        <img src="img/soporte.jpg" alt="">
                        <section class="caption">
                            <h1>Soporte</h1>
                            <p>¿Cómo te podemos ayudar?</p>
                        </section>
                    </a></li>
                    
                    <li><a href="quienes_somos.jsp">
                        <img src="img/itch_front.jpg" alt="">
                        <section class="caption">
                            <h1>¿Quiénes somos?</h1>
                            <p>Acerca de nosotros...</p>
                        </section>
                    </a></li>
                    
                    <li><a href="kulu_business.jsp">
                        <img src="img/kulu_business.jpg" alt="">
                        <section class="caption">
                            <h1>K'ulu' for business</h1>
                            <p>Incrementa tu productividad...</p>
                        </section>
                    </a></li>
                    
                    <li><a href="contacto.jsp">
                        <img src="img/contacto.jpg" alt="">
                        <section class="caption">
                            <h1>Contacto</h1>
                            <p>¡Acércate a nosotros!</p>
                        </section>
                    </a></li>
                    
                </ul>
                <ol class="pagination">
                </ol>
                <div class="left">
                    <span class="fa fa-chevron-left"></span>
                </div>
                <div class="right">
                    <span class="fa fa-chevron-right"></span>
                </div>
            </div>
        </div>
        <div id="contenido2">
            <div class="temas">
                <div class="estilo">
                    
                    <h2>¿Necesitas ayuda?</h2>
                   <br><p>Visita nuestro <a href='#'>centro de ayuda</a> para consultar tutoriales o <a href='#'>preguntas frecuentes</a>
                    <br><br><h2>Temas populares:</h2>
                    <br><ul>
    
                    <li><a href='#'>Dispositivos recomendados</a></li>
                    <li> <a href='#'>Configuracion de cuenta</a></li>
                     <li><a href='#'>Descarga de videos</a></li>
                     <li><a href='#'>Puntuaciones</a></li>
    
</ul>
</p>
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
    </body>
</html>