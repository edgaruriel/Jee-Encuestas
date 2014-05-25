<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lista de contactos</title>
</head>
<body>
<a href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >Salir</a>
<br>
<br>
<label for="buscar">Buscar: </label>
<input id="buscar" name="buscar" value="">
<br>
<a href="crearContacto.jsp">Crear nuevo contacto</a>

<c:set var="contactos" scope="session" value="${sessionScope.USUARIO.contactos}"></c:set>

<table  border="1" style="width:300px">
<tr>
<th>ID</th>
<th>Nombre</th>
<th>Correo</th>
<th>Operaciones</th>
</tr>

<c:choose>
<c:when test="${fn:length(contactos) == 0}">
	<tr>
		<td colspan="4">No tienes contactos</td>
	</tr>
</c:when>
<c:otherwise>
	
	<c:forEach var="contacto" items="${contactos}" varStatus="contador">
		<tr>
			<td>${contacto.id}</td>
			<td>${contacto.nombre}</td>
			<td>${contacto.correo}</td>
			<td><a href="<%=request.getContextPath()%>/ControlContacto?tipo=Editar&id=${contacto.id}" >Editar</a> 
			<a href="<%=request.getContextPath()%>/ControlContacto?tipo=Eliminar&id=${contacto.id}"> Eliminar</a></td>
		</tr>
	</c:forEach>

</c:otherwise>
</c:choose>


</table>


</body>
</html>