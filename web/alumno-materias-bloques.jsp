<%-- 
    Document   : alumno-materias-bloques
    Created on : 15/12/2017, 03:19:00 AM
    Author     : Norma
--%>

<%@page import="modelos.TareaAsignada"%>
<%@page import="modelos.consultaAlumnosMateria"%>
<%@page import="modelos.Bloque"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Materia"%>
<%@page import="modelos.Alumno"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>K'ulu' - Alumno Bloques</title>
    <link rel="stylesheet" href="css/font-awesome.min.css">
<!--    <link rel="stylesheet" href="css/alumno-materia-bloques.css">-->
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
                      int idMateria = -1;  
                  String nombreMateria="";
                    try{
                        idMateria = Integer.parseInt(request.getParameter("variable")); 
                        Materia materia = null;
                        materia = Materia.obtenerPorId(idMateria);
                        nombreMateria = materia.getNombre();
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
                <li><a href="alumno-materia-bloques-tarea.jsp">Tarea</a></li>
            </ul>
        </nav>
        <hr class="style13">
        <div class="container">
    <div class="bloques">
        <center><h1 class=""><%=nombreMateria%></h1></center>
        <%
            int idBloque =0;
            String nombreBloque="", descripcion="";
            
            List<Bloque> bloquito = new ArrayList<>();
            bloquito = Bloque.obtenerTodosID(idMateria);  

                for (int i=0;i<bloquito.size();i++) {
                    idBloque = bloquito.get(i).getIdBloque();
                    nombreMateria = bloquito.get(i).getNombreBloque();
                    descripcion = bloquito.get(i).getDescripcion();
                    
                    out.println("<div class=\"bloque\">");
                        out.println("<h2 class=\"\">"+ (i+1) + "._ "+ nombreMateria + "</h2>");
                        out.println("<p>" + descripcion+ "</p>");
                        List<TareaAsignada> tarAsignada = new ArrayList<>();
                        tarAsignada = TareaAsignada.obtenerTodosIDM(idBloque); 
                        for (int j=0;j<tarAsignada.size();j++) {
                            int idTarea = tarAsignada.get(j).getIdTareaAsignada();
                            out.println("<a href=\"alumno-materia-bloques-tarea.jsp?variable="+idTarea+"\">"+tarAsignada.get(j).getNombreTarea()+ "</a>");
                        }
                    out.println("</div>");                             
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
</body>

</html>

