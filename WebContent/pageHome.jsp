
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Inicio de sesión</title>
</head>
<body bgcolor="#CEECF5">
	<div id="contenedor">
		<div id="menu">
			<c:set var="user" scope="session" value="${sessionScope.USUARIO}"></c:set>
			<a href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >Salir</a>
			<h1>
			Bienvenido <c:out value="${user.nombre}"/> 
			</h1>
			<br>
			<h3><a href="<%=request.getContextPath()%>/ControlCarpeta?tipo=Cargar">Mis Encuestas</a> </h3>	
				<br>
				<h2><a href="<%=request.getContextPath()%>/ControlEncuesta?tipo=Agregar&id=${sessionScope.USUARIO.id}">Crear Encuesta</a></h2>
				<br>
			<h3><a href="contactos.jsp">Mis contactos</a> </h3>
				<br>
			<h3><a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${sessionScope.USUARIO.id}">Mi Cuenta</a></h3>
			<br>
			<a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Eliminar&id=${sessionScope.USUARIO.id}">Eliminar Cuenta</a>
		</div>
		
		<div id="cuerpo"></div>
	
	</div>

	
</body>
</html>

<%!%>