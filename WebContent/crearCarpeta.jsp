<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="recursos/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="recursos/jqueryValidation/jquery-validation.js"></script>
<script type="text/javascript" src="recursos/js/carpetaPersonal.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Crear carpeta personal</title>
</head>
<body bgcolor="#FE2E2E">
	<div id="contenedor">
		<div id="menu">
			<a href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >Salir</a>
			<br>
			<h3><a href="<%=request.getContextPath()%>/ControlCarpeta?tipo=Cargar">Mis Encuestas</a> </h3>
			<br>
			<h3><a href="contactos.jsp">Mis contactos</a> </h3>
			<br>
			<h3><a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${sessionScope.USUARIO.id}">Mi Cuenta</a></h3>
		</div>
		<div id="cuerpo">
			<h3>Crear nueva carpeta personal</h3>
			<form id="form" action="<%=request.getContextPath()%>/ControlCarpeta?tipo=Agregar" method="post">	
			<br>
				<label >Nombre:</label>
				<input type="text" name="nombre" id="nombre" size="20">
				<br>		
				<input type="hidden" name="usuarioId" value="${sessionScope.USUARIO.id}">
				<br>
			<input type="submit" value="Agregar">
			<input type="button" value="Cancelar" onclick="cancelar();">
		</form>
		</div>
		
	</div>


</body>
</html>