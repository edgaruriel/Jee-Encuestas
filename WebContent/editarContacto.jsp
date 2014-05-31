<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Editar contacto</title>
</head>
<body>
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
				<p>Editar contacto</p>
			<a href="contactos.jsp">Atras</a>
			
			<c:set var="contacto" scope="session" value="${sessionScope.contacto}"></c:set>
			
			<form action="ControlContacto" method="post">	
			<br>
				<label >Nombre(s):</label>
				<input type="text" name="nombre" id="nombre" size="20" value="${contacto.nombre}">
				<br>	
				<label >Correo electr&oacute;nico:</label>
				<input type="text" name="correo" id="correo" size="20" value="${contacto.correo}">
				<br>		
			<input type="hidden" id="id" name="id" value="${contacto.id}" >	
			<input type="submit" id="tipo" name="tipo" value="Actualizar">
		</form>
		</div>
	</div>
</body>
</html>