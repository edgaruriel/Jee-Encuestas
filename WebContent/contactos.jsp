<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Lista de contactos</title>
</head>
<body bgcolor>
<div id="superior">
<img id="logoImg" alt="logo" src="recursos/imagenes/logo.png">
<label id="lblogo" >Mi Encuesta</label>
</div>
<div id="contenedor">
	<div id="menu">
		<a id="logout" href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >[ Salir ]</a>

		<h3><a class="menuop" href="<%=request.getContextPath()%>/ControlCarpeta?tipo=Cargar">Mis Encuestas</a> </h3>

		<h3><a class="menuop" href="contactos.jsp">Mis contactos</a> </h3>

		<h3><a class="menuop" href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${sessionScope.USUARIO.id}">Mi Cuenta</a></h3>
	</div>
	<div id="cuerpo">
		
		<a id="nuevo" href="crearContacto.jsp">Crear nuevo contacto</a>
		
		<c:set var="contactos" scope="session" value="${sessionScope.USUARIO.contactos}"></c:set>
		<c:choose>
		<c:when test="${fn:length(contactos) == 0}">
		<table border="1" style="width:300px">
			<tr>
				<td colspan="4">No tienes contactos</td>
			</tr>
		</table>
		</c:when>
		<c:otherwise>
			<table  border="1" style="width:300px">
			<tr>
			<th>ID</th>
			<th>Nombre</th>
			<th>Correo</th>
			<th>Operaciones</th>
			</tr>
			
			<c:forEach var="contacto" items="${contactos}" varStatus="contador">
				<tr>
					<td>${contacto.id}</td>
					<td>${contacto.nombre}</td>
					<td>${contacto.correo}</td>
					<td><a href="<%=request.getContextPath()%>/ControlContacto?tipo=Editar&id=${contacto.id}" >Editar</a> 
					<a href="<%=request.getContextPath()%>/ControlContacto?tipo=Eliminar&id=${contacto.id}"> Eliminar</a></td>
				</tr>
			</c:forEach>
			</table>
		</c:otherwise>
		</c:choose>
	</div>

</div>
</body>
</html>