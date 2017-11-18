// Get the modal
  var modal = document.getElementById('myModal');
  var edit = document.getElementById('edit');
  

  // Get the button that opens the modal
  var btn = document.getElementById("myBtn");
  
  // Get the button that opens the modal
  var btn2 = document.getElementById("myBtn2");
  
  // Get the <span> element that closes the modal
  var span = document.getElementsByClassName("close")[0];
  
  var span2 = document.getElementsByClassName("close")[1];

  // When the user clicks the button, open the modal
  btn.onclick = function() {
      modal.style.display = "block";
  }
  
  // When the user clicks on <span> (x), close the modal
  span.onclick = function() {
      modal.style.display = "none";
  }

    // When the user clicks on <span> (x), close the modal
  span2.onclick = function() {
      edit.style.display = "none";
  }
  
  // When the user clicks anywhere outside of the modal, close it
  window.onclick = function(event) {
      if (event.target == modal) {
          modal.style.display = "none";
      }
  }
  
  function getId(id,apPat,am,nomb,gen,fech,miCurp,grupo){
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
      
      var fechita = document.getElementById("fecha");
      fechita.value = fech;
      
      var curp = document.getElementById("curpito");
      curp.value = miCurp;
      
      var miGrup = document.getElementById("idGroup");
      miGrup.value = grupo;
  }