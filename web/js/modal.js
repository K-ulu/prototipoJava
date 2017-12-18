     // Get the modal
    var Agregargrupo = document.getElementById('Agregargrupo');//Sirve para agregar un grupo
    var modal = document.getElementById('myModal');//Sirve para desplegar la ventana de agregar algo nuevo
    var edit = document.getElementById('edit');//Sirve para editar cualquier objeto
    var desasociar = document.getElementById('des-asociar'); //Sirve para desasociar un grupo
    //
    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];

    var span2 = document.getElementsByClassName("close")[1];
    
    var span3 = document.getElementsByClassName("close")[2];
    
    var span4 = document.getElementsByClassName("close")[3];
       
    // When the user clicks the button, open the modal
    btn.onclick = function() {
        modal.style.display = "block";
    }
  
    // When the user clicks on <span> (x), close the modal agregar
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks on <span> (x), close the modal editar 
    span2.onclick = function() {
        edit.style.display = "none";
    }
    
    // When the user clicks on <span> (x), close the modal asociar grupo
    span3.onclick = function() {
        Agregargrupo.style.display = "none";
    }
  
    // When the user clicks on <span> (x), close the modal desasociar grupo
    span4.onclick = function() {
        desasociar.style.display = "none";
    }
    
    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
  
    function agregar(id){
        Agregargrupo.style.display = "block";
        var inp = document.getElementById("var2");
        inp.value = id;
    }
       
    function quitar(idGM){        
        desasociar.style.display = "block";
        var padre = document.getElementById("formulario");
        var tabla = document.getElementById("miTabla"); //Recuperamos la tabla
        var btn = document.getElementById("btn"); //Recuperamos el boton
        var var1 = document.getElementById("var3");    
        
        padre.removeChild(tabla);//Si existe una tabla se elimina
        var tablaReemplazo = document.createElement("table"); //Creamos la tabla a reemplazar
        tablaReemplazo.border = 1; //agregamos el border
        tablaReemplazo.id = "miTabla"; //agregamos el id con el mismo id que se maneja
        padre.appendChild(tablaReemplazo); //agregamos la tabla nueva a nuestro padre
                
        if(idGM.length>0){
           var1.disabled = false;
           //Esta seccion es para el encabezado
            var fila = document.createElement("tr");
            var valor = ("ID GRUPO");//Introducimos valor que es igual a la fila y columna
            var columna = document.createElement("th");//creamos la columna
            var texto = document.createTextNode(valor);//creamos elemento texto y le introducimos el valor
            columna.appendChild(texto);//agregamos el texto a la columna
            fila.appendChild(columna);//agregamos la columna a la fila

            var valor = ("Nombre del grupo");//Introducimos valor que es igual a la fila y columna
            var columna = document.createElement("th");//creamos la columna
            var texto = document.createTextNode(valor);//creamos elemento texto y le introducimos el valor
            columna.appendChild(texto);//agregamos el texto a la columna
            fila.appendChild(columna);//agregamos la columna a la fila
            tablaReemplazo.appendChild(fila);//agregamos la fila a la tabla de reemplazo
            //Termina el encabezado y comenzamos aguardar valores
           var dato ="";
            var i=0;
            while (i < idGM.length){
                var aux = idGM[i];
                if (aux == " "){
                    //creamos la fila
                    fila = document.createElement("tr");
                    valor = (dato);//Introducimos valor que es igual a la fila y columna
                    columna = document.createElement("td");//creamos la columna
                    texto = document.createTextNode(valor);//creamos elemento texto y le introducimos el valor
                    columna.appendChild(texto);//agregamos el texto a la columna
                    fila.appendChild(columna);//agregamos la columna a la fila

                    i++;
                    valor = (idGM[i]);//Introducimos valor que es igual a la fila y columna
                    columna = document.createElement("td");//creamos la columna
                    texto = document.createTextNode(valor);//creamos elemento texto y le introducimos el valor
                    columna.appendChild(texto);//agregamos el texto a la columna
                    fila.appendChild(columna);//agregamos la columna a la fila
                    tablaReemplazo.appendChild(fila);//agregamos la fila a la tabla de reemplazo
                    dato="";
                    i++;
                }
                else{
                    dato+=aux;
                    i++;
                }
            }
            padre.insertBefore(tablaReemplazo, btn);  
        }
        else{
            var1.disabled = true;
        }     
    }
    
  function getAlumno(id,apPat,am,nomb,gen,fech,miCurp,grupo){
      edit.style.display = "block";
      var inp = document.getElementById("MyId");
      inp.value = id;
      
      var inp2 = document.getElementById("MyId2");
      inp2.value = id;
      
      var apt = document.getElementById("apd");
      apt.value = apPat;
      
      var apm = document.getElementById("apm");
      apm.value = am;
      
      var nombre = document.getElementById("nomb");
      nombre.value = nomb;
      
      var gener = document.getElementById("geni");
      gener.value = gen;
      
      var fechita = document.getElementById("fechaNac");
      fechita.value = fech;
      
      var curp = document.getElementById("curpito");
      curp.value = miCurp;
            
      var miGrup = document.getElementById("idGroup");
      for(var j=0; j<miGrup.length; j++){ 
          if(miGrup[j].value==grupo){
             miGrup[j].selected = true;  
          }
      }
  }
  
  function getGrupo(id,idDoc,grado,letra,turno){
      edit.style.display = "block";
      var inp = document.getElementById("MyId");
      inp.value = id;
      
      var inp2 = document.getElementById("MyId2");
      inp2.value = id;
      
      var idD = document.getElementById("idDoc");
      idD.value = idDoc;
      
      var grad = document.getElementById("grad");
      grad.value = grado;
      
      var nomb = document.getElementById("nomb");
      nomb.value = 'Grupo '+letra;
      
      var turn = document.getElementById("turn");
      turn.value = turno;
    }
    
    function getMateria(id,nombre,grado,idDoc){
      edit.style.display = "block";
      var inp = document.getElementById("MyId");
      inp.value = id;
      
      var inp2 = document.getElementById("MyId2");
      inp2.value = id;
      
      var nomb = document.getElementById("nomb");
      nomb.value = nombre;
           
      var grad = document.getElementById("grad");
      for(var j=0; j<grad.length; j++){    
          if(grad[j].value==grado){
             grad[j].selected = true;  
          }
      }
      
      var idD = document.getElementById("idDoc");
      idD.value = idDoc;
    }
    
    function getBloque(idBloq, unidad,bloque,descripcion){
      edit.style.display = "block";
      
      var idB = document.getElementById("idBloq");
      idB.value = idBloq;

      var inp = document.getElementById("bloque");
      inp.value = bloque;
            
      var nomb = document.getElementById("descripcion");
      nomb.value = descripcion;
      
      var grad = document.getElementById("unidad");
      grad.value = "Unidad "+ unidad;
    }
    
    function getTareas_asignadas(idTa, nombreTarea,descripcion,idMat,idBloque,idSesionD, fech){
      edit.style.display = "block";
      
      var idT = document.getElementById("MyId");
      idT.value = idTa;
      
      var idT2 = document.getElementById("MyId2");
      idT2.value = idTa;

      var nomb = document.getElementById("nomb");
      nomb.value = nombreTarea;
            
      var desc = document.getElementById("descripcion");
      desc.value = descripcion;
                
      var idM2 = document.getElementById("materia2");
      for(var j=0; j<idM2.length; j++){    
          if(idM2[j].value==idMat){
             idM2[j].selected = true;  
          }
      }
           
      var idB = document.getElementById("IDbloque");
      for(var j=0; j<idM2.length; j++){   
          if(idB[j].value==idBloque){
             idB[j].selected = true;  
          }
      }
      
      var idSD = document.getElementById("docentID");
      idSD.value = idSesionD;
      
      var fechita = document.getElementById("fechaE");
      fechita.value = fech;
}