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

<c:set var="categorias" scope="session" value="${sessionScope.categorias}"></c:set>
<c:set var="carpetas" scope="session" value="${sessionScope.carpetas}"></c:set>

	<h3>Crear nueva encuesta</h3>
	<form action="<%=request.getContextPath()%>/ControlEncuesta?tipo=Guardar" method="post">	
	<br>
		<label >Nombre de la encuesta:</label>
		<input type="text" name="nombre" size="20">
		<br>
		
	<label>Categoría: </label>
	 <c:choose>
		<c:when test="${fn:length(categorias) == 0}">
			<label>No hay nada en categorias</label>
		</c:when>
		<c:otherwise>
			<select name="categoria">
				<c:forEach var="categoria" items="${categorias}" varStatus="contador">
					<option value="${categoria.id}">${categoria.nombre}</option>
				</c:forEach>
			</select>
		</c:otherwise>
		</c:choose>
		
		<br>
		<label>Carpeta: </label>
		<c:choose>
		<c:when test="${fn:length(carpetas) == 0}">
			<label>No hay nada en carpetas</label>
		</c:when>
		<c:otherwise>
			<select name="carpeta">
				<c:forEach var="carpeta" items="${carpetas}" varStatus="contador">
					<option value="${carpeta.id}">${carpeta.nombre}</option>
				</c:forEach>
			</select>
		</c:otherwise>
		</c:choose> 
		<br>
		
		<label >Fecha Actual:</label>
		<input type="text" name="fechaActual" value="${sessionScope.fechaActual}" readonly>
		<br>	
		
		<input type="hidden" name="id" value="${sessionScope.USUARIO.id}">
		<br>
		
		<a href="crearPregunta.jsp">Crear Pregunta</a>
	<input type="submit" value="Crear Encuesta">
</form>

<c:choose>
<c:when test="${requestScope.errorEncuesta != null}">
	<font color="red">No puede crear una encuesta sin haber agregado preguntas</font>
</c:when>
</c:choose>
</body>
</html>