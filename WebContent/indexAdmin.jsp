<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Admin</title>
</head>
<body bgcolor="#0489B1">
	<div id="contenedor">
		<div id="menu">
			<c:set var="admin" scope="session" value="${sessionScope.ADMIN}"></c:set>
			<a href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >Salir</a>
			<h1>
			Bienvenido <c:out value="${user.nombre}"/> 
			</h1>
				<br>
				<h2><a href="usuarios.jsp">Usuarios</a></h2>
				<br>
				<h3><a href="<%=request.getContextPath()%>/ControlCategoria?tipo=Cargar">Categorias</a> </h3>
				<br>
			<h3><a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${admin.id}">Mi Cuenta</a></h3>
		
		</div>
		<div id="cuerpo">
		
		</div>
	
	</div>
</body>
</html>