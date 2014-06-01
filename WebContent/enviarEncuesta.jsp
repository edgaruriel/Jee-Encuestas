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
<body>
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
				<h3><a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${sessionScope.USUARIO.id}">Mi Cuenta</a></h3>
			</div>
			<div id="cuerpo">
				<h2>Enviar encuesta: </h2>
			<c:set var="contactos" scope="session" value="${sessionScope.USUARIO.contactos}"></c:set>
			
			<form action="<%=request.getContextPath()%>/ControlEmail?tipo=enviar" method="POST">
			<table  border="1" style="width:300px">
			    <tr>
			    <td>Para :<input type="text" id="email" name="email" />
			    <br> 
			     Ejemplo: ejeplo1@gmail.com,ejemplo2@hotmail.com,...
			    </td> <!--enter the email whom to send mail -->			    
			    </tr>
			    <tr>
			    <td>Seleccione contactos de su lista:</td>
			    </tr>
			    <tr>
					<c:choose>
						<c:when test="${fn:length(contactos) == 0}">
							
								<td colspan="3">No tienes contactos, agregue contactos para enviar sus encuestas</td>
							
						</c:when>
						<c:otherwise>
							<table border="1" style="width:300px">
							<tr>
							<th>Nombre</th>
							<th>Correo</th>
							<th>Seleccionar</th>
							</tr>
							
							<c:forEach var="contacto" items="${contactos}" varStatus="contador">
								<tr>
									<td>${contacto.nombre}</td>
									<td>${contacto.correo}</td>
									<td> <input id="contacto${contacto.id}" name="contacto${contacto.id}" type="checkbox" value="${contacto.correo}"/> </td>
								</tr>
							</c:forEach>
							</table>
						</c:otherwise>
				</c:choose>
			    </tr>
			    <tr>
			    <td><input type="submit" value="send"></input></td>
			    </tr>
			</table>
			</form>
				
			</div>
	</div>
	
	

</body>
</html>