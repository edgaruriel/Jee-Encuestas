<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar contacto</title>
</head>
<body>
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
	

</body>
</html>