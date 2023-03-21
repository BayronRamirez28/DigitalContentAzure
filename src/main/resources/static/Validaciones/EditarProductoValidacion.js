function si(){
	
	caja1 = document.getElementById("txtnombre").value;

	//Validacion Nombres

	if (caja1.length >= 35) {
		Swal.fire({
  			icon: 'error',
  			title: 'Error...',
 			text: 'Sólo se permiten menos de 35 letras para el nombre!',
  
})
		return false;
	}
	
	else if (/^\s+$/.test(caja1)) {
		document.getElementById("txtnombre").focus();
		document.getElementById("txtnombre").style.borderColor = "green";
		return true;
	}

	else if (/^([0-9])*$/.test(caja1)) {
		Swal.fire({
  			icon: 'info',
  			title: 'Oops...',
 			text: 'El campo Nombre es invalido!',
  
})
		document.getElementById("txtnombre").focus();
		document.getElementById("txtnombre").style.borderColor = "red";
		return false;
	}

	else if (caja1.length <= 3) {
		Swal.fire({
  			icon: 'error',
  			title: 'Error...',
 			text: 'Sólo se permiten mas de 3 letras para el nombre!',
		})
		return false;
	}
}