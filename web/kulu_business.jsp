<%-- 
    Document   : kulu_business
    Created on : Dec 15, 2017, 1:16:39 PM
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
        <title>K'ulu' - K'ulu for business</title>
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
                        <img src="img/kulu_business.jpg" alt="">
                        <section class="caption">
                            <h1>K'ulu' for business</h1>
                            <p>Incrementa tu productividad...</p>
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
                <div class="">
                    
                    <h2>Organiza tu flujo de trabajo</h2>
                   <br><p>Per ne nibh iudicabit, per at illum platonem forensibus. Mucius voluptua appareat eu sed. Latine voluptaria interpretaris at sed, eam cu vitae ridens aperiam. Duo eu facete alienum, minim propriae et nam. Alia fugit in has, inani oratio impedit sed ei.

                    <br><br><h2>Aprende a usar la plataforma</h2>
                    <br>Lorem ipsum dolor sit amet, illum nostrum praesent ut sit, nisl dolore verterem mel cu. Aliquam definitionem in est, alia aperiam mea in. Pri ex brute partem verear, mea ad habemus pericula. Wisi graece pro te, cu pro meis persius iracundia. Tritani voluptatum ius ex. Est et iisque eleifend elaboraret, tale vocibus no usu.
                    <br><br><h2>Resultados notables</h2>
                    <br>Ad eos fastidii volutpat tractatos. Duo vidisse instructior in, mel eu tantas tibique, ea viris dolor vituperata qui. Ne possim convenire temporibus mel, ut alia molestiae eum. Vero modus soluta no cum, nostrud verterem liberavisse an sit. Id augue fabellas theophrastus per.
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
