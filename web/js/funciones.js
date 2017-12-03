/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function eliminar(nombreServ, tot){/*Esta funcion se creo para que cuando se seleccione eliminar...*/
    var mensaje = confirm('¿Realmente desea eliminar?');
    var inp = document.getElementById("var");//Si se selecciona que se desea eliminar
    inp.value = tot;
    if (mensaje) { //manda a llamar al servlet ya sea el de alumno o grupo
        document.formulario0.action=nombreServ;
        document.formulario0.submit();
    }
}

function limpiar(){
   for (i=0;i<document.f1.elements.length;i++) 
      if(document.f1.elements[i].type == "checkbox")	
        document.f1.elements[i].checked=0; 
}

