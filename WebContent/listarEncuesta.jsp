<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Insert title here</title>
</head>
<body bgcolor="#FE2E2E">
	<div id="contenedor">
		<div id="menu">
			<c:set var="user" scope="session" value="${sessionScope.USUARIO}"></c:set>
			
			<a href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >Salir</a>			
			<br>
			<h2>USUARIO <c:out value="${user.nombre}"/>. </h2>
			<br>
			<h3><a href="<%=request.getContextPath()%>/ControlCarpeta?tipo=Cargar">Mis Encuestas</a> </h3>
			<br>
			<h3><a href="contactos.jsp">Mis contactos</a> </h3>
			<br>
			<h3><a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${user.id}">Mi Cuenta</a></h3>
		</div>
		<div id="cuerpo">
			
			<c:set var="carpeta" scope="session" value="${sessionScope.CARPETA}"></c:set>
			
			<h2>CARPETA: <c:out value="${carpeta.nombre}"/>. </h2>
			
		<a href="<%=request.getContextPath()%>/ControlEncuesta?tipo=Agregar&id=${user.id}">Agregar Encuesta</a>
		<br>
	
		<c:set var="encuestas" scope="session" value="${carpeta.encuestas}"></c:set>
		
		<table border="1" style="width:300px">
			<c:choose>
			<c:when test="${fn:length(encuestas) == 0}">
				<tr>
					<td colspan="4">No existe ninguna encuesta, Agregue una encuesta a la carpeta personal ${carpeta.nombre}.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<tr>
					<td>ID</td>
					<td>Nombre Encuesta</td>
					<td>N&uacute;mero de preguntas</td>
					<td>Operaciones</td>
				</tr>
				
				<c:forEach var="encuesta" items="${encuestas}" varStatus="contador">
					<tr>
						<td><label>${encuesta.id}</label></td>
						<td>${encuesta.nombre}</td>
						<td>${fn:length(encuesta.preguntas)}</td>
						<td><a href="#">Ver</a> <a href="<%=request.getContextPath()%>/ControlEmail?tipo=seleccionarEncuesta&encuesta=${encuesta.id}">Enviar</a></td>
					</tr>
				</c:forEach>
				
			
			</c:otherwise>
			</c:choose>
			
		</table>
		
		</div>		
	</div>
</body>
</html>