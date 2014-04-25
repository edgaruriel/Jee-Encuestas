<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
		<h3>Editar Cuenta:</h3>
	<form action="actualizarDatos" method="post">	
	<br>
		<label >Nombre(s):</label>
		<input type="text" name="nombre" size="20" value="${sessionScope.nombre}">
		<br>
		<label >Primer apellido::</label>
		<input type="text" name="pApellido" size="20" value="${sessionScope.pApellido}">
		<br>
		<label >Segundo apellido::</label>
		<input type="text" name="sApellido" size="20" value="${sessionScope.sApellido}">
		<br>
		<label >Nombre de usuario:</label>
		<input type="text" name="nombreUsuario" size="20" value="${sessionScope.nombreUsuario}">
		<br>	
		<label>Contrase&ntilde;a:</label>
		<input type="password" name="contrasenia" size="10" value="${sessionScope.contrasena}">		
		<br>
		<label >Correo electr&oacute;nico:</label>
		<input type="text" name="correo" size="20" value="${sessionScope.correo}">
		<br>
		<c:out value="${sessionScope.rol}"></c:out>
		<input type="hidden" name="tipoUsuario" value="1">
		<br>
	<input type="submit" value="Actualizar Datos">
</form>
</body>
</html>