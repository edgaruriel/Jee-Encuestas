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
	<form action="<%=request.getContextPath()%>/ControlUsuario?tipo=Actualizar&id=${sessionScope.USUARIO.id}" method="post">	
	<br>
		<label >Nombre(s):</label>
		<input type="text" name="nombre" size="20" value="${sessionScope.USUARIO.nombre}">
		<br>
		<label >Primer apellido::</label>
		<input type="text" name="pApellido" size="20" value="${sessionScope.USUARIO.primerApellido}">
		<br>
		<label >Segundo apellido::</label>
		<input type="text" name="sApellido" size="20" value="${sessionScope.USUARIO.segundoApellido}">
		<br>
		<label >Nombre de usuario:</label>
		<input type="text" name="nombreUsuario" size="20" value="${sessionScope.USUARIO.nombreUsuario}">
		<br>	
		<label>Contrase&ntilde;a:</label>
		<input type="password" name="contrasenia" size="10" value="${sessionScope.USUARIO.contrasena}">		
		<br>
		<label >Correo electr&oacute;nico:</label>
		<input type="text" name="correo" size="20" value="${sessionScope.USUARIO.correo}">
		<br>
		<c:out value="${sessionScope.USUARIO.rol.nombre}"></c:out>
		<input type="hidden" name="tipoUsuario" value="1">
		<br>
	<input type="submit" value="Actualizar Datos">
</form>
</body>
</html>