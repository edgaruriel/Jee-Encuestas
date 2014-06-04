<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu_admin.css" media="screen" />
<title>Insert title here</title>
</head>
<body>
<div id="superior">
<img id="logoImg" alt="logo" src="recursos/imagenes/logo.png">
<label id="lblogo" >Mi Encuesta</label>
</div>
	<div id="contenedor">
		<div id="menu">
			<c:set var="admin" scope="session" value="${sessionScope.ADMIN}"></c:set>
			<a id="logout" href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >[ Salir ]</a>
			<h1>
			Bienvenido <c:out value="${admin.nombre}"/> 
			</h1>
				
				<h2><a class="menuop" href="usuarios.jsp">Usuarios</a></h2>
				
				<h3><a class="menuop" href="categorias.jsp">Categorias</a> </h3>
				
			<h3><a class="menuop" href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${admin.id}">Mi Cuenta</a></h3>
		
		</div>
		<div id="cuerpo">
			<label for="buscar">Buscar: </label>
			<input id="buscar" name="buscar" value="">
			<br>
			<a id="nuevo" href="crearUsuario.jsp">Crear nuevo usuario</a>
			
			<c:set var="usuarios" scope="session" value="${admin.usuarios}"></c:set>
			<c:choose>
				<c:when test="${fn:length(usuarios) == 0}">
				<table border="1" style="width:410px">
					<tr>
						<td colspan="4">No existen usuarios en el sistema</td>
					</tr>
				</table>
				</c:when>
			<c:otherwise>
					<table  border="1" style="width:420px">
					<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Correo</th>
					<th>Operaciones</th>
					</tr>
					<c:forEach var="usuario" items="${usuarios}" varStatus="contador">
						<tr>
							<td>${usuario.id}</td>
							<td>${usuario.nombre} ${usuario.primerApellido} ${usuario.segundoApellido}</td>
							<td>${usuario.correo}</td>
							<td>
							<a href="<%=request.getContextPath()%>/ControlEmail?tipo=seleccionarUsuario&usuario=${usuario.nombreUsuario}">Enviar correo</a>
							<a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Eliminar&id=${usuario.id}">Eliminar Cuenta</a>
							</td>
						</tr>
					</c:forEach>
					</table>
				
			</c:otherwise>	
			</c:choose>
		
		
		</div>
	</div>	
</body>
</html>