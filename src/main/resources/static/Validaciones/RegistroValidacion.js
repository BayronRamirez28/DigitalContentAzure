function si() {

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

	else if (caja1.length <= 2) {
		Swal.fire({
  			icon: 'error',
  			title: 'Error...',
 			text: 'Sólo se permiten mas de 2 letras para el nombre!',
		})
		return false;
	}

	//Validacion Apellidos


	caja2 = document.getElementById("txtapellido").value;

	if (caja2.length >= 50) {
		Swal.fire({
  			icon: 'error',
  			title: 'Error...',
 			text: 'Sólo se permiten menos de 50 letras para los Apellidos!',
  
})
		return false;
	}

	else if (/^\s+$/.test(caja2)) {
		document.getElementById("txtapellido").focus();
		document.getElementById("txtapellido").style.borderColor = "green";
		return true;
	}


	else if (/^([0-9])*$/.test(caja2)) {
		Swal.fire({
  			icon: 'info',
  			title: 'Oops...',
 			text: 'El campo Apellidos es invalido!',
  
})
		document.getElementById("txtapellido").focus();
		document.getElementById("txtapellido").style.borderColor = "red";
		return false;
	}

	else if (caja2.length <= 2) {
		Swal.fire({
  			icon: 'error',
  			title: 'Error...',
 			text: 'Sólo se permiten mas de 3 letras para los Apellidos!',
		})
		return false;
	}
	
		//Validacion Direccion
	
	caja3 = document.getElementById("txtdireccion").value;


	if (caja3.length >= 100) {
		Swal.fire({
  			icon: 'error',
  			title: 'Error...',
 			text: 'Sólo se permiten menos de 100 letras para la Direccion!',
  
})
		return false;
	}
	
	else if (/^\s+$/.test(caja3)) {
		document.getElementById("txtdireccion").focus();
		document.getElementById("txtdireccion").style.borderColor = "green";
		return true;
	}

	else if (/^([0-9])*$/.test(caja3)) {
			Swal.fire({
  			icon: 'info',
  			title: 'Oops...',
 			text: 'El campo Direccion es invalido!',
  
})
		document.getElementById("txtdireccion").focus();
		document.getElementById("txtdireccion").style.borderColor = "red";
		return false;
	}

	else if (caja3.length <= 7) {
		Swal.fire({
  			icon: 'error',
  			title: 'Error...',
 			text: 'Sólo se permiten mas de 7 letras para la Direccion!',
		})
		return false;
	}

}