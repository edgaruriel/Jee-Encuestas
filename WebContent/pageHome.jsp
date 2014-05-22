
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inicio de sesión</title>
</head>
<body bgcolor="#CEECF5">
	<h1>
	Bienvenido.
	</h1>
	<a href="contactos.jsp">Contactos</a>
	<br>
	<a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${sessionScope.USUARIO.id}">Editar Cuenta</a>
	<br>
	<a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Eliminar&id=${sessionScope.USUARIO.id}">Eliminar Cuenta</a>
	<br>
	<a href="secondPage.jsp" >Salir</a>
</body>
</html>

<%!%>