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
<body bgcolor>
<div id="superior">
<img id="logoImg" alt="logo" src="recursos/imagenes/logo.png">
<label id="lblogo" >Mi Encuesta</label>
</div>
	<div id="contenedor">
		<div id="menu">
			<c:set var="admin" scope="session" value="${sessionScope.ADMIN}"></c:set>
			<a id="logout" href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >[ Salir ]</a>
			<h1>
			Bienvenido <c:out value="${user.nombre}"/> 
			</h1>
				
				<h2><a class="menuop" href="usuarios.jsp">Usuarios</a></h2>
				
				<h3><a class="menuop" href="<%=request.getContextPath()%>/ControlCategoria?tipo=Cargar">Categorias</a> </h3>
				
			<h3><a class="menuop" href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${admin.id}">Mi Cuenta</a></h3>
		
		</div>
		<div id="cuerpo">
			
			<a id="nuevo" href="crearCategoria.jsp">Crear nueva categoria</a>
			
			<c:set var="categorias" scope="session" value="${sessionScope.CATEGORIAS}"></c:set>
			<c:choose>
			<c:when test="${fn:length(categorias) == 0}">
				<table border="1" style="width:300px">
					<tr>
						<td colspan="4">No existe ninguna categoria</td>
					</tr>
				</table>
			</c:when>
			<c:otherwise>
					<table  border="1" style="width:300px">
					<tr>
					<th>ID</th>
					<th>Nombre</th>
					<th>Operaciones</th>
					</tr>
					<c:forEach var="categoria" items="${categorias}" varStatus="contador">
						<tr>
							<td>${categoria.id}</td>
							<td>${categoria.nombre}</td>
							<td><a href="<%=request.getContextPath()%>/ControlCategoria?tipo=Editar&id=${categoria.id}" >Editar</a> 
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