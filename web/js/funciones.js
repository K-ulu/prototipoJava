/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function eliminar(variable, tot){
    var mensaje = confirm('¿Realmente desea eliminar?');
    var inp = document.getElementById("var");
    inp.value = tot;
    var dato2 =document.getElementById("var").value;
    if (mensaje) { 
        document.formulario0.action=variable;
        document.formulario0.submit();
    }
}

