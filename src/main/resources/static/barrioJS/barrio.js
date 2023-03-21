$(document).ready(function() {
	$('#exampleS').DataTable({
		dom: 'Bfrtilp',
		buttons: [
			{
				extend: 'excelHtml5',
				text: '<i class="fa-solid fa-file-excel"></i>',
				titleAttr: 'Exportar a Excel',
				className: 'btn btn-success'
			},
			{
				extend: 'pdfHtml5',
				text: '<i class="fa-solid fa-file-pdf"></i>',
				titleAttr: 'Exportar a PDF',
				className: 'btn btn-danger'
			}
		]
	});
});


function eliminar() {

	var idBarrio = document.getElementById("txt_id").value;
	location.href = "/admin/barrio/eliminare/" + idBarrio;

}

function confirmar(idBarrio) {
	document.getElementById("txt_id").value = idBarrio;

}