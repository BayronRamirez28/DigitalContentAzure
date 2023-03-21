
$(document).ready(function() {	
	numero = document.getElementsByClassName("card").length;
	for (let step = 0; step <= numero; step++) {
		cantidad = $(".cant:eq("+step+")").val();
		precio = $(".precio:eq("+step+")").val();

		total = precio * cantidad;
		$(".total:eq("+step+")").val("$"+total);
	}
});