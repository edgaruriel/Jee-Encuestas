$(document).ready(function() {	
	$('#form').validate({
		rules:{
			tipopregunta: "required"
		},
		
		messages:{
			tipopregunta: "Seleccione un tipo de pregunta"
		},
		
		submitHandler: function(form) {
			form.submit();
	    }
	});
});

function tipoPregunta(sel){
	$("#opciones").html("");
	if(sel.value==1){

		var div = document.getElementById("contenido");
	    div.innerHTML = 'Pregunta abierta: <input type="text" name="pregunta" id="pregunta"/>';
	     
	}else if(sel.value==2){

		var div = document.getElementById('contenido');
	    div.innerHTML = '<br>Número de opciones: <input type="text" onblur="opciones(this.value)"/><br>Pregunta de múltiple: <input type="text" name="pregunta" id="pregunta"/>';
	     
	}else{

		var div = document.getElementById("contenido");
	    div.innerHTML = 'Pregunta de calificación: <input type="text" name="pregunta" id="pregunta"/>';
	     
	}
}

function opciones(value){
	$("#opciones").html("");
	var div = document.getElementById("opciones");
	
	for (var int = 1; int <= value; int++) {
		//div.insertAdjacentHTML('afterend', '<br>Opción '+int+'<input type="text" id="opcion'+int+'" name="opcion'+int+'"/>');
		div.innerHTML += '<br>Opción '+int+'<input type="text" id="opcion'+int+'" name="opcion'+int+'"/>';
	}
	//div.insertAdjacentHTML('afterend', '<input type="text" name="numopciones" id="numopciones" value="'+value+'" readonly/>');
	div.innerHTML+='<input type="hidden" name="numopciones" id="numopciones" value="'+value+'" readonly/>';
}