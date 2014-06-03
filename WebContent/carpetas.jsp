<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Mis Encuestas</title>
</head>
<body bgcolor="#FE2E2E">
	<div id="contenedor">
		<div id="menu">
		<c:set var="user" scope="session" value="${sessionScope.USUARIO}"></c:set>	
			<a href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >Salir</a>
			<br>
			<h3><a href="<%=request.getContextPath()%>/ControlCarpeta?tipo=Cargar">Mis Encuestas</a> </h3>
			<br>
			<h3><a href="contactos.jsp">Mis contactos</a> </h3>
			<br>
			<h3><a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${user.id}">Mi Cuenta</a></h3>
		</div>
		<div id="cuerpo">
			<br>
			<h1>Mis Encuestas. <c:out value="${user.nombre}"/> </h1>
			<br>
	
	<a href="crearCarpeta.jsp">Crear nuevo carpeta personal</a>
	
		<c:set var="carpetas" scope="session" value="${sessionScope.CARPETAS}"></c:set>
		<table  border="1" style="width:300px">
			<c:choose>
			<c:when test="${fn:length(carpetas) == 0}">
				<tr>
					<td colspan="4">No existe ninguna carpeta personal, Agregue una carpeta.</td>
				</tr>
			</c:when>
			<c:otherwise>
				<c:set var="multiplo" scope="page" value="4"></c:set>
				<c:set var="indiceFila" scope="page" value="1"></c:set>				
				<c:forEach var="carpeta" items="${carpetas}" varStatus="contador">
					<c:if test="${contador.count == indiceFila}">	
						<c:set var="indiceFila" scope="page" value="${indiceFila+4}"></c:set>	
						<tr>
					</c:if>
					
						<td>
							<table>
								<tr><td><a href="<%=request.getContextPath()%>/ControlEncuesta?tipo=Enlistar&carpeta=${carpeta.id}">${carpeta.nombre}</a></td></tr>
								<tr><td>Encuestas ${fn:length(carpeta.encuestas)}</td></tr>
							</table>
						</td>
						
						<c:set var="indice" scope="page" value="${contador.count}"></c:set>
						
						<c:choose>
						<c:when test="${ indice == multiplo }">
						<c:set var="multiplo" scope="page" value="${multiplo*2}"></c:set>
							</tr>
						</c:when>
						<c:otherwise>
							<c:if test="${contador.count == fn:length(carpetas)}">								
								</tr>
							</c:if>
						</c:otherwise>
						</c:choose>
					
				</c:forEach>			
			</c:otherwise>
			</c:choose>		
		</table>
			
		</div>
	</div>

	
	
	
	
	
</body>

</html>