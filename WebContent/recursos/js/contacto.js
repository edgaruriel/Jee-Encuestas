$(document).ready(function(){	

	$("#form").validate({
		rules:{
			nombre: "required",
			correo: {required: true, email: true}
		},
		
		messages:{
			nombre: "Agregue un nombre",
			correo: {required: "campo obligatorio", email:"formato: ejemplo@hotmail.com"}
		},
		
		submitHandler: function(form) {
			form.submit();
	    }
	});
});

function cancelar(){
	window.location="contactos.jsp";
}