<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="recursos/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="recursos/jqueryValidation/jquery-validation.js"></script>
<script type="text/javascript" src="recursos/js/verEncuesta.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Insert title here</title>
</head>
<body>
<div id="superior">
<img id="logoImg" alt="logo" src="recursos/imagenes/logo.png">
<label id="lblogo" >Mi Encuesta</label>
</div>
<div id="contenedor">
	<c:set var="esCliente" scope="session" value="${sessionScope.ES_CLIENTE}"></c:set>

	<c:choose>
		<c:when test="${esCliente == true}">
				<div id="menu">
					<a id="logout" href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >[ Salir ]</a>

					<h3><a class="menuop" href="<%=request.getContextPath()%>/ControlCarpeta?tipo=Cargar">Mis Encuestas</a> </h3>
				
					<h3><a class="menuop" href="contactos.jsp">Mis contactos</a> </h3>
					
					<h3><a class="menuop" href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${sessionScope.USUARIO.id}">Mi Cuenta</a></h3>
				</div>
				
		</c:when>
		
	</c:choose>
	
	<div id="cuerpo">
				<h1>Bienvenido al sistema de generación de encuestas.</h1>
		<br>
	
		<c:set var="ABIERTA" scope="page" value="1"></c:set>
		<c:set var="MULTIPLE" scope="page" value="2"></c:set>
		<c:set var="CALIFICACION" scope="page" value="3"></c:set>
		
		<form action="<%=request.getContextPath()%>/ControlResponder?tipo=agregar" method="post">
			<br>
			<c:set var="encuesta" scope="session" value="${sessionScope.VERENCUESTA}"></c:set>
			<c:set var="solicitante" scope="session" value="${sessionScope.SOLICITANTE}"></c:set>
			<input id="correo" name="correo" type="hidden" value="${solicitante}">
			<table  border="1" style="width:300px">
				<c:choose>
				<c:when test="${encuesta == null}">
					<tr>
						<td colspan="2">No existe ninguna encuesta, posiblemente eliminada por el usuario.</td>
					</tr>
				</c:when>
				<c:otherwise>
				<tr>
				<th>#</th>
				<th>Pregunta</th>
				</tr>
				<c:forEach var="pregunta" items="${encuesta.preguntas}" varStatus="contador">
					<tr>
						<c:choose>
							<c:when test="${pregunta.tipoPregunta.id == ABIERTA}">
								<td><label>${contador.count}</label></td>
								<td>
									<table>
										<tr><td>${pregunta.pregunta}</td></tr>
										<tr>
										<td> 
											<c:choose>
												<c:when test="${esCliente == true}">
													<input type="text" id="pregunta${pregunta.id}" name="pregunta${pregunta.id}" readonly="readonly"> 
												</c:when>
												<c:otherwise>
													<input type="text" id="pregunta${pregunta.id}" name="pregunta${pregunta.id}"> 
												</c:otherwise>
											</c:choose>
										</td>
										</tr>
									</table>
								
								</td>
							</c:when>
							<c:when test="${pregunta.tipoPregunta.id == MULTIPLE}">
								<td><label>${contador.count}</label></td>
								<td>
									<table>
										<tr>
											<td colspan="${fn:length(pregunta.opciones)}">${pregunta.pregunta}</td>
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
							</c:when>
							<c:when test="${pregunta.tipoPregunta.id == CALIFICACION}">
								<td><label>${contador.count}</label></td>
								<td>
									<table>
										<tr>
											<td colspan="${fn:length(pregunta.opciones)}">${pregunta.pregunta}</td>
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
							</c:when>
						</c:choose>
					</tr>
				</c:forEach>
				</c:otherwise>
				</c:choose>
				
			</table>
			
			<br>
			<c:choose>
				<c:when test="${esCliente == true}">
					<input type="button" value="cancelar" onclick="cancelar();">
				</c:when>
				<c:otherwise>
					<input type="submit" value="Contestar">
				</c:otherwise>
			</c:choose>
			
		</form>
	</div>

	
</div>	

	<c:choose>
	<c:when test="${requestScope.error != null}">
		<font color="red">Error al contestar la encuesta, No puedes contestarla 2 veces.</font>
	</c:when>
	<c:when test="${requestScope.correcto != null}">
		<font color="blue">Encuesta contestada correctamente.</font>
	</c:when>
	</c:choose>

</body>
</html>