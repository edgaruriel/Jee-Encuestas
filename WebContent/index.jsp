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
<form action="ControlLogin?tipo=LogIn" method="post">	
	<br>
		<label >Usuario:</label>
		<input type="text" name="usuario" size="20">
	<br>	
		<label>Contraseña:</label>
		<input type="password" name="contrasenia" size="10">		
	<br>
	<input type="submit" value="Aceptar">
</form>

<a href="crearUsuario.jsp">Crear Usuario</a>

<c:choose>
<c:when test="${requestScope.error != null}">
	<font color="red">Usuario o Contraseña incorrectas</font>
</c:when>
</c:choose>

</body>
</html>

