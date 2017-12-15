/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
var tareas = document.getElementById('tareas');//Sirve para desplegar la ventana de agregar algo nuevo
var tar = document.getElementsByClassName("close")[0];
// When the user clicks on <span> (x), close the modal agregar
tar.onclick = function() {
    tareas.style.display = "none";
}

function getTareas_entregadas(id){
    tareas.style.display = "block";
    var idT = document.getElementById("MyId");
    idT.value = id;
      
    var idT2 = document.getElementById("MyId2");
    idT2.value = id;
}

