<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<h3>Crear nuevo usuario</h3>
	<form action="<%=request.getContextPath()%>/ControlUsuario?tipo=Agregar" method="post">	
	<br>
		<label >Nombre(s):</label>
		<input type="text" name="nombre" size="20">
		<br>
		<label >Primer apellido::</label>
		<input type="text" name="pApellido" size="20">
		<br>
		<label >Segundo apellido::</label>
		<input type="text" name="sApellido" size="20">
		<br>
		<label >Nombre de usuario:</label>
		<input type="text" name="nombreUsuario" size="20">
		<br>	
		<label>Contrase&ntilde;a:</label>
		<input type="password" name="contrasenia" size="10">		
		<br>
		<label >Correo electr&oacute;nico:</label>
		<input type="text" name="correo" size="20">
		<br>
		<input type="hidden" name="tipoUsuario" value="1">
		<br>
	<input type="submit" value="Crear">
</form>
<c:choose>
<c:when test="${requestScope.errorUsuario != null}">
	<font color="red">Ya existe un registro con ese nombre de usuario</font>
</c:when>
</c:choose>
</body>
</html>