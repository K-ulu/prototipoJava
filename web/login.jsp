<%-- 
    Document   : login
    Created on : 2/12/2017, 04:22:27 PM
    Author     : gerar
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>K'ulu' &bull; Iniciar sesión</title>
        <meta charset="UTF-8">
        <link rel="icon" href="img/icon.png">
        <link rel="stylesheet" type="text/css" href="css/style2.css">
        <link rel="stylesheet" href="css/nav-christ.css">
        <!-- <link rel="stylesheet" href="css/estilos.css"> -->
        </head>
    <body>
        <div class="navbar">
            <div class="nav-logo">
                <a href="index.jsp"><img src="img/kulu_logo_160.png"></a>
            </div>
            <div class="nav-menu">
                <li class="dropDown"><a href="javascript:void(0)" class="dropButton"><i class="fa fa-bars"></i> Menu</a>
                    <div class="dropDown-content">
                        <a href="">¿Quienes Somos?</a>
                        <a href="">Kulu for bussines</a>
                        <a href="">Contacto</a>
                        <a href="">Soporte</a>
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
        <div class="container">
            <div class="kulu-head"><img src="img/kulu_head.png" width="">
                <div class="kulu-hand" id="pass1"></div>
                <div class="kulu-hand-r" id="pass2"></div>
            </div>
            <div class="bg-img"></div>
            <div class="login">
                <div class="title">
                    <h2>Ingresa tus datos</h2>
                    <!-- <img src="img/kulu_logo_320.png" width="350px"> -->
                </div>
                <br>
                <span>
                    <% 
                        if(request.getParameter("error") != null){
                            out.println("<p style='color: red; font-family: \"Raleway\"';>Correo y/o clave incorrectas.</p>");
                        } else {
                            out.println("");
                        }

                    %>
                </span>
                <form action="inicioSesion" method="POST">
                    <div class="control">
                        <label for="correo" class="fa fa-user"></label>
                        <input type="email" id="pass3" name="correo" placeholder="Correo">
                    </div>
                    <br>
                    <div class="control">
                        <label for="correo" class="fa fa-lock"></label>
                        <input type="password" id="pass" name="contrasena" placeholder="Contraseña">
                    </div>
                    <br>
                    <button type="submit" name="iniciar">Iniciar sesión</button>
                    <br>
                    <br>
                    <div class="footer1"> <a href="recuperar.html">¿Olvidaste tu contraseña?</a> ó <a href="registro.jsp">¿No eres miembro aún?</a></div>
                </form>
            </div>
        </div>
        <script>
        document.getElementById("pass").onfocus = function() { myFunction() };

        function myFunction() {

            document.getElementById("pass1").classList.add("contrasena");
            document.getElementById("pass2").classList.add("contrasena");
        }

        var x = document.getElementById("pass");
        x.addEventListener("blur", myBlurFunction, true);

        function myBlurFunction() {
            document.getElementById("pass1").classList.remove('contrasena');
            document.getElementById("pass2").classList.remove('contrasena');
        }

        // document.getElementById("pass").onfocusout = function() {myFunction2()};

        // function myFunction2() {

        // document.getElementById("pass2").classList.remove('contrasena');
        // document.getElementById("pass1").classList.remove('contrasena');

        //  if ( document.getElementById("pass1").classList.contains('contrasena') )

        //  document.getElementById("pass1").classList.toggle('contrasena');
        // }
        </script>
    </body>
</html>
