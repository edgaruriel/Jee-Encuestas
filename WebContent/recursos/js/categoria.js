$(document).ready(function(){	

	$("#form").validate({
		rules:{
			nombre: "required"
		},
		
		messages:{
			nombre: "Agregue un nombre"
		},
		
		submitHandler: function(form) {
			form.submit();
	    }
	});
});

function cancelar(){
	window.location="carpetas.jsp";
}