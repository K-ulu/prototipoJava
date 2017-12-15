/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
window.tiempoRestante = 1200 //20 minutos

 
function contador(){
     window.tiempoRestante--;//saco 1 segundo
     if(tiempoRestante==0){
          window.location.href="index.jsp"; //redirecciono
     }else{
         //console.log("hola");
          setTimeout(contador,1000);//recursion en 1 segundo
     }
    //console.log("hola");
    //setTimeout(contador, 1000);
}

setTimeout(contador, 1000);
 
function hayActividad(){
     window.tiempoRestante=1200;//reinicio a 20 minutos
}