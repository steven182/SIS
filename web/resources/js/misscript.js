const name = /^[a-zA-ZñÑ]{3,}$/;   
const identificacion = /^\d{8,10}$/;
const mail = /^(?:[^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*|"[^\n"]+")@(?:[^<>()[\].,;:\s@"]+\.)+[^<>()[\]\.,;:\s@"]{2,63}$/i;
const phone = /^\d{7,10}$/;
const address = /^.*(?=.*[0-9])(?=.*[a-zA-ZñÑ\s]).*$/;
const pattern = /^[a-zA-ZñÑ\d0-9]/;
const valor1 = /^\d{5,10}$/;
const cantidad = /^\d{1,3}$/;
const calificar = /^[1-5]$/;
const Atext = /^[a-zA-ZñÑ]/;
const cts = /^[aAsS]\d{3}$/;

function validarRegistro(){ //registro
let contra = document.getElementById('contrasena').value;
let contra1 = document.getElementById('contrasena1').value;
let espacios = false;
let cont = 0;

  if (!name.test(document.valida1.nombre.value)){
    alert("Nombre invalido, solo se permiten letras");
    document.valida1.nombre.focus('red');
    return 0;
  }
  if (!name.test(document.valida1.apellido.value)){
    alert("Apellido invalido, solo se permiten letras");
    document.valida1.apellido.focus('red');
    return 0;
  }
  if(!identificacion.test(document.valida1.identi.value)) {
       alert('Identificacion no válido, minimo 8 numeros');
       document.valida1.identi.focus('red');
       return 0;    
    }
  if (!mail.test(document.valida1.correo.value)) {
    alert('Correo electronico invalido');
      document.valida1.correo.focus('red');
      return 0;
  }  
   if (!phone.test(document.valida1.telefono.value)) {
    alert('Numero de telefono invalido, minimo 7 numeros');
    document.valida1.telefono.focus('red');
    return 0;
   } 
    if (!address.test(document.valida1.direccion.value)) {
      alert('Direccion invalida');
      document.valida1.direccion.focus('red');
      return 0;
    } 
    while (!espacios && (cont < contra.length)) {
       if (contra.charAt(cont) == " ")
        espacios = true;
        cont++;
}
 
    if (espacios) {
      alert ("La contraseña no puede contener espacios en blanco");
      return false;
}
      if (contra.length == 0 || contra1.length == 0) {
      alert("Los campos de la password no pueden quedar vacios");
      return false;
    }

      if (contra != contra1) {
      alert("Las passwords deben de coincidir");
      return false;
    } else {
      alert("Todo esta correcto");
      return true; 
    }

}
function validarFormulario(){ //gestion servicio
  if (!name.test(document.valida2.nombreTec.value)){
    alert("Nombre del tecnico invalido, solo se permiten letras");
    document.valida2.nombreTec.focus('red');
    return 0;
  }
   if(!identificacion.test(document.valida2.idTec.value)) {
       alert('Identificacion no válido, minimo 8 números, maximo 10 números');
       document.valida2.idTec.focus('red');
       return 0;    
    }
    if (!phone.test(document.valida2.telefono2.value)) {
    alert('Número de telefono del tecnico invalido, minimo 7 números');
    document.valida2.telefono2.focus('red');
    return 0;
   }
    if (valida2.tipoServicio.selectedIndex < 0) {
      alert('Escoga uno o varios servicios por favor');
      return false;
    }
    if (!name.test(document.valida2.nombreCliente.value)){
    alert("Nombre del cliente invalido, solo se permiten letras");
    document.valida2.nombreCliente.focus('red');
    return 0;
  }
  if (!name.test(document.valida2.nombreComp.value)){
    alert("Nombre de la compañia invalido, solo se permiten letras");
    document.valida2.nombreComp.focus('red');
    return 0;
  }
   if (!address.test(document.valida2.direc.value)) {
      alert('Direccion invalida');
      document.valida2.direc.focus('red');
      return 0;
    } 
    if (!phone.test(document.valida2.telefonoClien.value)) {
    alert('Numero de telefono del cliente invalido, minimo 7 numeros');
    document.valida2.telefonoClien.focus('red');
    return 0;
   }
   if (!mail.test(document.valida2.correoClien.value)) {
    alert('Correo electronico invalido');
    document.valida2.correoClien.focus('red');
    return 0;
  }
    if (valida2.grupo.selectedIndex <= 0) {
    alert('Escoga un grupo por favor');
    return false;
    }
    if (valida2.marca.selectedIndex <= 0) {
    alert('Escoga una marca por favor');
    return false;
    }
    if (!pattern.test(document.valida2.modelo.value)) {
      alert('modelo invalido');
      document.valida2.modelo.focus('red');
      return 0;
    }
    if (!pattern.test(document.valida2.serial.value)) {
      alert('Serial invalido');
      document.valida2.seial.focus('red');
      return false;
    }
}

function formularioCotizacion(){//formulario Cotizaciones
  if (!name.test(document.valida3.nombreCliente2.value)){
    alert("Nombre del cliente invalido, solo se permiten letras");
    document.valida3.nombreCliente2.focus('red');
    return 0;
  }
   if (!name.test(document.valida3.nombreCompañia.value)){
    alert("Nombre de la compañia invalido, solo se permiten letras");
    document.valida3.nombreCompañia.focus('red');
    return 0;
  }
  if (!address.test(document.valida3.direccion2.value)) {
      alert('Direccion invalida');
      document.valida3.direccion2.focus('red');
      return 0;
    }
  if (!phone.test(document.valida3.telefono1.value)) {
    alert('Numero de telefono del cliente invalido, minimo 7 numeros');
    document.valida3.telefono1.focus('red');
    return 0;
   }
  if (!mail.test(document.valida3.correo1.value)) {
    alert('Correo electronico invalido');
    document.valida3.correo1.focus('red');
    return 0;
  }
  if (document.valida3.grupo2.selectedIndex < 0) {
      alert('Escoga un grupo por favor');
      document.valida3.grupo2.focus('red');
      return 0;
    }

    if (valida3.subGrupo.selectedIndex < 0) {
      alert('Escoga un subgrupo por favor');
      document.valida3.subGrupo.focus('red');
      return 0;
    }
    if (valida3.marca1.selectedIndex <= 0) {
      alert('Escoga una marca por favor');
      document.valida3.marca1.focus('red');
      return 0;
    }  
  if (!pattern.test(document.valida3.modelo1.value)) {
      alert('Modelo invalido');
      document.valida3.modelo1.focus('red');
      return 0;
    }
  if (!cantidad.test(document.valida3.cantidad1.value)) {
      alert('Cantidad invalida');
      document.valida3.cantidad1.focus('red');
      return 0;
    }
   if (!valor1.test(document.valida3.total.value)) {
      alert('valor invalido');
      document.valida3.total.focus('red');
      return false;
    } 
}

function formularioConsumibles(){ //gestion consumibles
    if (valida4.tipo3.selectedIndex <= 0) {
      alert('Escoga un tipo de consumible por favor');
      document.valida4.tipo3.focus('red');
      return 0;
    }
    if (valida4.tipoMaquina.selectedIndex <= 0) {
      alert('Escoga un tipo de maquina por favor');
      document.valida4.tipoMaquina.focus('red');
      return 0;
    }
    if (valida4.grupoCons.selectedIndex <= 0) {
      alert('Escoga un grupo por favor');
      document.valida4.grupoCons.focus('red');
      return 0;
    }
    if (!cantidad.test(document.valida4.cantidad5.value)) {
      alert('Cantidad invalida');
      document.valida4.cantidad5.focus('red');
      return 0;
    }
    if (!Atext.test(document.valida4.areaTexto.value)) {
      alert('El campo de texto no puede ir vacio');
      document.valida4.areaTexto.focus('red');
      return false;
    }
} 

function formularioUsuarios(){// gestionar Usuarios
  let contrase = document.getElementById('contraseñaUsuario').value;
  let contrase1 = document.getElementById('contraseñaUsuario1').value;
  let espacios1 = false;
  let cont1 = 0;
  if (!name.test(document.valida5.nombreUsuario.value)) {
      alert('Nombre del cliente invalido, solo se permiten letras');
      document.valida5.nombreUsuario.focus('red');
      return 0;
    }
    if (!name.test(document.valida5.apellidoUsuario.value)) {
      alert('Apellido del cliente invalido, solo se permiten letras');
      document.valida5.apellidoUsuario.focus('red');
      return 0;
    }
    if(!identificacion.test(document.valida5.cedula.value)) {
       alert('Identificacion no válido, minimo 8 números, maximo 10 números');
       document.valida2.cedula.focus('red');
       return 0;    
    }
    if (!name.test(document.valida5.ciudad.value)) {
      alert('Nombre de la ciudad invalido, solo se permiten letras');
      document.valida5.ciudad.focus('red');
      return 0;
    }
     if (!name.test(document.valida5.nomCompañia.value)){
    alert("Nombre de la compañia invalido, solo se permiten letras");
    document.valida5.nomCompañia.focus('red');
    return 0;
  }
  if (!address.test(document.valida5.direccionCliente.value)) {
      alert('Direccion invalida');
      document.valida5.direccionCliente.focus('red');
      return 0;
    }
    if (!phone.test(document.valida5.telefonoCliente.value)) {
    alert('Numero de telefono del cliente invalido, minimo 7 numeros');
    document.valida5.telefonoCliente.focus('red');
    return 0;
    }
    if (!mail.test(document.valida5.correoCliente.value)) {
    alert('Correo electronico invalido');
    document.valida5.correoCliente.focus('red');
    return 0;
  }
  while (!espacios1 && (cont1 < contrase.length)) {
       if (contrase.charAt(cont1) == " ")
        espacios1 = true;
        cont1++;
}
 
    if (espacios1) {
      alert ("La contraseña no puede contener espacios en blanco");
      return false;
}
      if (contrase.length == 0 || contrase1.length == 0) {
      alert("Los campos de la password no pueden quedar vacios");
      return false;
    }

      if (contrase != contrase1) {
      alert("Las passwords deben de coincidir");
      return false;
    } else {
      alert("Todo esta correcto");
      return true; 
    }

}

function buscarHistarial(){ //buscar el historial
    if (!name.test(document.valida6.buscaNombre.value)){
      alert("Nombre del cliente invalido, solo se permiten letras");
      document.valida6.buscaNombre.focus('red');
      return 0;
  }
   if (!name.test(document.valida6.buscaApellido.value)){
      alert("Apellido del cliente invalido, solo se permiten letras");
      document.valida6.buscaApellido.focus('red');
      return 0;
  }
  if (!name.test(document.valida6.buscaEmpresa.value)){
      alert("Nombre de la compañia invalido, solo se permiten letras");
      document.valida6.buscaEmpresa.focus('red');
      return 0;
  }
  if (valida6.buscaServicio.selectedIndex < 0) {
      alert('Escoga un servicio por favor');
      document.valida6.buscaServicio.focus('red');
      return 0;
    }
    if (!pattern.test(document.valida6.buscaSerial.value)) {
      alert('Serial invalido');
      document.valida6.buscaSerial.focus('red');
      return 0;
    }
    if (!identificacion.test(document.valida6.buscaIdTecnico.value)) {
      alert('Identificacion del tecnico invalido');
      document.valida6.buscaIdTecnico.focus('red');
      return 0;
    }
    if (!identificacion.test(document.valida6.buscaIdCliente.value)) {
      alert('Identificacion del Cliente invalido');
      document.valida6.buscaIdCliente.focus('red');
      return false;
    }
}

function generaHistorial(){// historial generado depues de la busqueda
  if (!name.test(document.valida7.nombreHistorial.value)) {
    alert('Nombre del cliente invalido');
    document.valida7.nombreHistorial.focus('red');
    return 0;
  }
  if (!name.test(document.valida7.empresaHistorial.value)) {
    alert('Nombre de la empresa invalido');
    document.valida7.empresaHistorial.focus('red');
    return 0;
  }
  if (!address.test(document.valida7.direccionHistorial.value)) {
    alert('Direccion de residencia invalida');
    document.valida7.direccionHistorial.focus('red');
    return 0;
  }
  if (!phone.test(document.valida7.telefonoHistorial.value)) {
    alert('Telefono del Cliente Invalido');
    document.valida7.telefonoHistorial.focus('red');
    return 0;
  }
  if (!mail.test(document.valida7.correoHistorial.value)) {
    alert('Correo electronico invalido');
    document.valida7.correoHistorial.focus('red');
    return 0;
  }
  if (valida7.grupoHistorial.selectedIndex < 0) {
    alert('Seleccione un grupo por favor');
    document.valida7.grupoHistorial.focus('red');
    return 0;
  }
  if (valida7.subGrupoHistorial.selectedIndex < 0) {
    alert('Seleccione un sub-grupo por favor');
    document.valida7.subGrupoHistorial.focus('red');
    return 0;
  }
  if (valida7.marcaHistorial.selectedIndex <= 0) {
    alert('Seleccione una marca por favor');
    document.valida7.marcaHistorial.focus('red');
    return 0;
  }
  if (!pattern.test(document.valida7.modeloHistorial.value)) {
    alert('Modelo invalido');
    document.valida7.modeloHistorial.focus('red');
    return 0;
  }
  if (!pattern.test(document.valida7.serialHistorial.value)) {
    alert('Serial invalido');
    document.valida7.serialHistorial.focus('red');
    return 0;
  }
  if (!calificar.test(document.valida7.calificarHistorial.value)) {
    alert('Calificacion invalida, califique de 1 a 5 por favor');
    document.valida7.calificarHistorial.focus('red');
    return 0;
  }
  if (valida7.consumiblesHistorial.selectedIndex < 0) {
    alert('Seleccione uno o mas repuestos');
    document.valida7.consumiblesHistorial.focus('red');
    return 0;
  }
  if (!valor1.test(document.valida7.contadorHistorial.value)) {
    alert('Contador invalido, minimo 5 números maximo 10');
    document.valida7.contadorHistorial.focus('red');
    return 0;
  }
  if (!Atext.test(document.valida7.descripcionInicio.value)) {
    alert('la descripción inicial no puede ir vacia');
    document.valida7.descripcionInicio.focus('red');
    return 0;
  }
   if (!Atext.test(document.valida7.descripcionFinal.value)) {
    alert('la descripción final no puede ir vacia');
    document.valida7.descripcionFinal.focus('red');
    return false;
  }
}

function contratos(){// gestion de contratos
  if (!cts.test(document.valida8.contrato1.value)) {
    alert('Contrato Invalido, se requiere las letras A o S seguidas por tres números');
    document.valida8.contrato1.focus('red');
    return 0;
  }
  if (valida8.tipoContrato.selectedIndex <= 0) {
    alert('Seleccione el tipo de contrato por favor');
    document.valida8.tipoContrato.focus('red');
    return 0;
  }
  if (!name.test(document.valida8.nombreTitular.value)) {
    alert('Nombre del titular invalido');
    document.valida8.nombreTitular.focus('red');
    return 0;
  }
  if (!identificacion.test(document.valida8.identificacionContrato.value)) {
    alert('Identificación invalida, minimo 8 números maximo 10');
    document.valida8.identificacionContrato.focus('red');
    return 0;
  }
  if (!address.test(document.valida8.direccionContrato.value)) {
    alert('Dirección invalida');
    document.valida8.direccionContrato.focus('red');
    return 0;
  }
  if (!phone.test(document.valida8.telefonoContrato.value)) {
    alert('Telefono invalido, minimo 7 números maximo 10');
    document.valida8.telefonoContrato.focus('red');
    return 0;
  }
  if (!mail.test(document.valida8.correoContratos.value)) {
    alert('Correo electronico invalido');
    document.valida8.correoContratos.focus('red');
    return 0;
  }
  if (valida8.consumiblesContratos.selectedIndex <= 0) {
    alert('Seleccione una opción');
    document.valida8.consumiblesContratos.focus('red');
    return 0;
  }
  if (!cantidad.test(document.valida8.cantidadCopias.value)) {
    alert('Cantidad de copias invalido, minimo 1 número maximo 3');
    document.valida8.cantidadCopias.focus('red');
    return false;
  }

}

function radioButton(){ //calificacion servicio
 opciones = document.getElementsByName("group1");
 var seleccionado = false;
for(var i=0; i<opciones.length; i++) {    
  if(opciones[i].checked) {
    seleccionado = true;
    break;
  }
}
 
if(!seleccionado) {
  alert('Seleccione alguna opción por favor');
  return false;
}
 opciones2 = document.getElementsByName("group2");
 var seleccionado2 = false;
for(var i=0; i<opciones2.length; i++) {    
  if(opciones2[i].checked) {
    seleccionado2 = true;
    break;
  }
}
 
if(!seleccionado2) {
  alert('Seleccione alguna opción por favor');
  return false;
}
 opciones3 = document.getElementsByName("group3");
 var seleccionado3 = false;
for(var i=0; i<opciones3.length; i++) {    
  if(opciones3[i].checked) {
    seleccionado3 = true;
    break;
  }
}
 
if(!seleccionado3) {
  alert('Seleccione alguna opción por favor');
  return false;
}

 opciones4 = document.getElementsByName("group4");
 var seleccionado4 = false;
for(var i=0; i<opciones4.length; i++) {    
  if(opciones4[i].checked) {
    seleccionado4 = true;
    break;
  }
}
 
if(!seleccionado4) {
  alert('Seleccione alguna opción por favor');
  return false;
}

  
}




  $(document).ready(function(){
     $(".button-collapse").sideNav();
     $('.chips').material_chip();
     $('.tooltipped').tooltip({delay: 50});   
     $('.datepicker').pickadate({
    selectMonths: true, // Creates a dropdown to control month
    selectYears: 1, // Creates a dropdown of 15 years to control year,
    today: 'Hoy',
    clear: 'Limpiar',
    close: 'Ok',
    closeOnSelect: true, // Close upon selecting a date,
    format: 'yyyy/mm/dd',
    min: new Date(),
    monthsFull: [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre' ],
    monthsShort: [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
    weekdaysFull: [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado' ],
    weekdaysShort: [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
    weekdaysLetter: [ 'D', 'L', 'M', 'Mi', 'J', 'V', 'S' ],
    firstDay: true,
    max: 31
  });
    $('.desde').pickadate({
      selectMonths: true, // Creates a dropdown to control month
    selectYears: 1, // Creates a dropdown of 15 years to control year,
    today: 'Hoy',
    clear: 'Limpiar',
    close: 'Ok',
    closeOnSelect: true, // Close upon selecting a date,
    format: 'yyyy/mm/dd',
    min: new Date(),
    monthsFull: [ 'Enero', 'Febrero', 'Marzo', 'Abril', 'Mayo', 'Junio', 'Julio', 'Agosto', 'Septiembre', 'Octubre', 'Noviembre', 'Diciembre' ],
    monthsShort: [ 'Ene', 'Feb', 'Mar', 'Abr', 'May', 'Jun', 'Jul', 'Ago', 'Sep', 'Oct', 'Nov', 'Dic' ],
    weekdaysFull: [ 'Domingo', 'Lunes', 'Martes', 'Miércoles', 'Jueves', 'Viernes', 'Sábado' ],
    weekdaysShort: [ 'Dom', 'Lun', 'Mar', 'Mie', 'Jue', 'Vie', 'Sab' ],
    weekdaysLetter: [ 'D', 'L', 'M', 'Mi', 'J', 'V', 'S' ],
    firstDay: true,
    max: 365
    });
    $('.slider').slider();
    $('.modal').modal();
    $('.parallax').parallax();
    $('select').material_select();
    $('.dropdown-button').dropdown({
      alignment: 'right', // Displays dropdown with edge aligned to the left of button
      constrainWidth: false
    }

  );
    
    $('#ingreso').click(function(){
    var user = $('#usuario').val();
    var pass = $('#password').val();
  

    if(user == "admin" && pass == "admin" ){
    alert('Bienvenido administrador');
    location.href = 'Admin/Inicio.xhtml';
    
    
  }

  else if(user == "user" && pass == "user" ){
    alert('Bienvenido usuario');
    location.href = "html/Cliente/inicio.html";
  }

  else if(user == "tech" && pass == "tech" ){
    alert('Bienvenido Tecnico');
    location.href = 'html/Tecnico/inicio.html';
  }
  else{
    alert('Error Verifique el usuario o la contraseña por favor');
  }


    });
    $('#buscar').click(function(){
      swal({
        title: "¿Que esta buscando?",
        text: "Por favor escriba para iniciar una busqueda:",
      type: "input",
        showCancelButton: true,
        closeOnConfirm: false,
        animation: "slide-from-top",
      inputPlaceholder: "Cotizaciones"
},
function(inputValue){
  if (inputValue === false) return false;
  
  if (inputValue === "") {
    swal.showInputError("Escriba algo por favor");
    return false
  }
  
  swal("Nice!", "You wrote: " + inputValue, "success");
});
    });

});

