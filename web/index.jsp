<%-- 
    Document   : index
    Created on : 2/12/2017, 03:18:56 PM
    Author     : gerar
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
        <title>K'ulu' - Bienvenidos</title>
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
                    <li>
                        <img src="img/news8.jpg" alt="">
                        <section class="caption">
                            <h1>Aprende y diviértete</h1>
                            <p>No hay mejor forma de aprender que jugando
                                <br> ¡Te esperamos!</p>
                        </section>
                    </li>
                    <li>
                        <img src="img/ninia.jpg" alt="">
                        <section class="caption">
                            <h1>Bienvenido a 'Kulu'!</h1>
                            <p>La forma más entretenida de aprender juntos.</p>
                        </section>
                    </li>
                    <li>
                        <img src="img/aplicaciones-ortografia.jpg" alt="">
                        <section class="caption">
                            <h1>Únete a k'ulu'!</h1>
                            <p>Crea una cuenta y empieza a disfrutar de las sorpresas
                                <br> que tenemos para ti.</p>
                        </section>
                    </li>
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
        <div id="contenido">
            <div class="temas">
                <div class="item">
                    <img src="img/crayons-1445053_1920.jpg" alt="colores">
                    <h2>Lorem ipsum</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consectetur fringilla urna sit amet aliquet.Lorem ipsum dolor sit amet, consectetur adipiscing elit. </p>
                </div>
                <div class="item">
                    <!--<img src="img/aplicaciones-ortografia.jpg" alt="orto">-->
                    <iframe allowfullscreen="" frameborder="0" height="265" src="https://www.youtube.com/embed/GCW1LzzdFsU" width="480"></iframe>
                    <h2>Lorem ipsum</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consectetur fringilla urna sit amet aliquet.Lorem ipsum dolor sit amet, consectetur adipiscing elit. </p>
                </div>
                <div class="item">
                    <img src="img/timothy-muza-572.jpg" alt="Our work">
                    <h2>Lorem ipsum</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Morbi consectetur fringilla urna sit amet aliquet.Lorem ipsum dolor sit amet, consectetur adipiscing elit. </p>
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
