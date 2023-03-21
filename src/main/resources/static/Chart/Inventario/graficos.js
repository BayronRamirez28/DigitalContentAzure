var num1;
var nomP = [];
var stock = [];
let myChart;
function traerData(tipo) {
	nomP = [];
	stock = [];
	url = "http://localhost:8090/admin/invt"
	response = {
		"data": [{
			"idInventario": num1,
			"id_Producto_fk": {
				"nombre": nomP
			},
			"stock": stock
		}]
	}
	$.ajax({
		type: "GET",
		url: url,
		success: function(response) {
			console.log(response)
			for (let i = 0; i < response.data.length; i++) {
				nomP.push(response.data[i].id_Producto_fk.nombre)
				stock.push(response.data[i].stock)
			}
			si(nomP, stock, tipo);

		}
	});
}

const getDataColor = opacity =>{
	const colors = ['#7448c2', '#21c0d7', '#cd3a81']
	return colors.map(color => opacity ? `${color + opacity}`: color)
}

function si(nomP, stock, tipo) {
	const ctx = document.getElementById('myChart');

	if (myChart) {
		myChart.destroy();
	}
	myChart = new Chart(ctx, {
		type: tipo,
		data: {
			labels: nomP,
			datasets: [{
				label: 'Cantidad en inventario',
				data: stock,
				borderWidth: 1,
				borderColor: getDataColor(),
				backgroundColor: getDataColor(40)
			}]
		},
		options: {
			scales: {
				y: {
					beginAtZero: true
				}
			}
		}
	});
}