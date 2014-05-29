<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="recursos/js/crearpregunta.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:set var="tipopreguntas" scope="session" value="${sessionScope.tipopreguntas}"></c:set>

	<h3>Crear nueva pregunta</h3>
	<form action="<%=request.getContextPath()%>/ControlEncuesta?tipo=GuardarPregunta" method="post">	
		
	<label>Tipo pregunta: </label>
	 <c:choose>
		<c:when test="${fn:length(tipopreguntas) == 0}">
			<label>No hay nada en tipo de preguntas</label>
		</c:when>
		<c:otherwise>
			<select name="tipopregunta" onchange="tipoPregunta(this)">
				<c:forEach var="tipopregunta" items="${tipopreguntas}" varStatus="contador">
					<option value="${tipopregunta.id}">${tipopregunta.tipo}</option>
				</c:forEach>
			</select>
		</c:otherwise>
		</c:choose>
		
		<div id="agregarPregunta">
			<div id="contenido"></div>
		</div>
		
		<input type="hidden" name="id" value="${sessionScope.USUARIO.id}">
		<br>
		
	<input type="submit" value="Crear">
</form>
</body>
</html>