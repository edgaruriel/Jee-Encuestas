<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="recursos/js/crearpregunta.js"></script>
<script type="text/javascript" src="recursos/jquery/jquery-1.8.2.min.js"></script>
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
			<select id="tipopregunta" name="tipopregunta" onchange="tipoPregunta(this)">
				<c:forEach var="tipopregunta" items="${tipopreguntas}" varStatus="contador">
					<option value="${tipopregunta.id}">${tipopregunta.tipo}</option>
				</c:forEach>
			</select>
		</c:otherwise>
		</c:choose>
		
		<div id="agregarPregunta">
			<div id="contenido"></div>
			<div id="opciones"></div>
		</div>
		
		<input type="hidden" name="id" value="${sessionScope.USUARIO.id}">
		<br>
		
	<input type="submit" value="Añadir pregunta">
</form>

	<c:set var="ABIERTA" scope="page" value="1"></c:set>
	<c:set var="MULTIPLE" scope="page" value="2"></c:set>
	<c:set var="CALIFICACION" scope="page" value="3"></c:set>
<c:set var="preguntas" scope="session" value="${sessionScope.preguntas}"></c:set>
		<table  border="1" style="width:300px">
			<tr>
			<th>#</th>
			<th>Pregunta</th>
			<th>Opciones</th>
			</tr>
			<c:forEach var="pregunta" items="${preguntas}" varStatus="contador">
				<tr>
					<c:choose>
						<c:when test="${fn:length(preguntas) == 0}">
							<label>No hay ninguna pregunta creada</label>
						</c:when>
						<c:when test="${pregunta.tipoPregunta.id == ABIERTA}">
							<td><label>${contador.count}</label></td>
							<td>
								<table>
									<tr><td>${pregunta.pregunta}</td></tr>
								</table>
							
							</td>
							<td><a href="<%=request.getContextPath()%>/ControlEncuesta?tipo=Eliminar&&id=${pregunta.id}">Eliminar</a></td>
						</c:when>
						<c:when test="${pregunta.tipoPregunta.id == MULTIPLE}">
							<td><label>${contador.count}</label></td>
							<td>
								<table>
									<tr>
										<td>${pregunta.pregunta}</td>
									</tr>
									<tr>
										<c:forEach  var="opcion" items="${pregunta.opciones}" varStatus="indice">
											<td>
												<input type="radio" id="opcion${pregunta.id}" name="opcion${pregunta.id}" value="${opcion.id}"> <b> ${opcion.opcion} </b>
											</td>
										</c:forEach>
									</tr>
								</table>
							
							</td>
							<td><a href="<%=request.getContextPath()%>/ControlEncuesta?tipo=Eliminar&&id=${pregunta.id}">Eliminar</a></td>
						</c:when>
						<c:when test="${pregunta.tipoPregunta.id == CALIFICACION}">
							<td><label>${contador.count}</label></td>
							<td>
								<table>
									<tr>
										<td>${pregunta.pregunta}</td>
									</tr>
								</table>
							
							</td>
							<td><a href="<%=request.getContextPath()%>/ControlEncuesta?tipo=Eliminar&&id=${pregunta.id}">Eliminar</a></td>
						</c:when>
					</c:choose>
				</tr>
			</c:forEach>
		</table>
</body>
</html>