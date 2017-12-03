<%-- 
    Document   : registro
    Created on : 2/12/2017, 03:39:44 PM
    Author     : gerar
--%>

<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Registro - K'ulu'</title>
        <meta charset="utf-8">
        <link rel="icon" href="img/icon.png">
        <link rel="stylesheet" type="text/css" href="css/styleRegistro.css">
        <link rel="stylesheet" href="css/nav-christ.css">  
        <link rel="stylesheet" href="css/jquery-ui.min.css">
        <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="//ajax.googleapis.com/ajax/libs/jqueryui/1.11.2/jquery-ui.min.js"></script>       
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
            <div class="kulu-head"><img src="" width="">
                <div class="kulu-hand" id="pass1"></div>
                <div class="kulu-hand-r" id="pass2"></div>
            </div>
            <div class="bg-img"></div>
            <div class="login">
                <div class="title">
                <h2>Ingresa tus datos</h2>
                </div>
                <br>
                <span>
                    <% 
                        if(request.getParameter("errors") != null){
                            out.println("<p style='color: red; font-family: \"Raleway\"';>Error al registrarse. Verifique los campos</p>");
                        } else {
                            out.println("");
                        }
                        if(request.getParameter("msg") != null){
                            out.println("<p style='color: green; font-family: \"Raleway\"';>Cuenta creada exitosamente!</p>");
                        } else {
                            out.println("");
                        }
                        
                    %>
                </span>
                <form action="registroMaestro" method="POST">
                    <input class="campo" type="text" name="nombre" id="nombre" placeholder="Nombre">
                    <input class="campo" type="text" name="apellidoP" id="apellidoP" placeholder="Apellido Paterno">
                    <input class="campo" type="text" name="apellidoM" id="apellidoM" placeholder="Apellido Materno">
                    <input class="campo" type="text" name="correo" id="correo"placeholder="Correo Electrónico">
                    <input class="campo" type="text" name="correo2" id="correo2" placeholder="Confirmar correo">
                    <select class="campo" name="genero" id="genero">
                        <option value="null" selected>Seleccione</option>
                        <option value="Masculino">Masculino</option>
                        <option value="Femenino">Femenino</option>
                    </select>
                    <input class="campo" type="text" name="fecha" id="fecha" readonly="readonly" placeholder="Fecha Nacimiento">
                    <input class="campo" type="text" name="contrasena" id="contrasena" placeholder="Contraseña">
                    <input class="campo" type="text" name="contrasena2" id="contrasena2" placeholder="Confirmar contraseña">
                    <button type="submit" name="registro">Registrar cuenta</button>
                    <div class="footer1"><a href="login.jsp">¿Ya eres miembro?</a></div>
                </form>
            </div>
        </div>
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
             
             
        </script>
    </body>
</html>
