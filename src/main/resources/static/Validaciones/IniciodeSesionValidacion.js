function si() {
  if ( /^\w+([.-_+]?\w+)*@\w+([.-]?\w+)*(\.\w{2,10})+$/.test(username)){
   alert("La dirección de email " + username + " es correcta!.");
  } else {
   Swal.fire({
  			icon: 'info',
  			title: 'Oops...',
 			text: 'El campo Nombre es invalido!',
  
})
  }
}