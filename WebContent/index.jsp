<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/index.css" media="screen" />
<title>Insert title here</title>
</head>
<body>
<div id="superior">
<img id="logoImg" alt="logo" src="recursos/imagenes/logo.png">
<label id="lblogo" >Mi Encuesta</label>
</div>
<div id="centro">
<form action="ControlLogin?tipo=LogIn" method="post">	
	<br>
	
		<label class="lblusuario" >Usuario:</label>
		<input type="text" name="usuario" size="20">
	<br>	
		<label class="lblusuario" >Contraseña:</label>
		<input type="password" name="contrasenia" size="10">		
	<br>
	
	<input class="boton" type="submit" value="Aceptar">
	
</form>
<a href="crearUsuario.jsp">Crear Usuario</a>
</div>


<c:choose>
<c:when test="${requestScope.error != null}">
	<label class="error" >Usuario o Contraseña incorrectas</label>
</c:when>
</c:choose>

</body>
</html>

