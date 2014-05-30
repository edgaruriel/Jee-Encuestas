function tipoPregunta(sel){
	if(sel.value==1){

		var div = document.getElementById("contenido");
	    div.innerHTML = 'Pregunta abierta: <input type="text" name="pregunta" />';
	     
	}else if(sel.value==2){

		var div = document.getElementById('contenido');
	    div.innerHTML = '<br>Número de opciones: <input type="text" onblur="opciones(this.value)"/><br>Pregunta de múltiple: <input type="text" name="pregunta" />';
	     
	}else{

		var div = document.getElementById("contenido");
	    div.innerHTML = 'Pregunta de calificación: <input type="text" name="pregunta" />';
	     
	}
}

function opciones(value){
	var div = document.getElementById("contenido");
	
	for (var int = 1; int <= value; int++) {
		div.insertAdjacentHTML('afterend', '<br>Opción '+int+'<input type="text" name="opcion'+int+'"/>');
		//div.innerHTML += '<br>Opción '+int+'<input type="text" name="opcion'+int+'"/>';
	}
	div.insertAdjacentHTML('afterend', '<input type="text" name="numopciones" id="numopciones" value="'+value+'" readonly/>');
	//div.innerHTML+='<input type="text" name="numopciones" id="numopciones" value="'+value+'" readonly/>';
}