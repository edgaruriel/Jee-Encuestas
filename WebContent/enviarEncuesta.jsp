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
			<form action="<%=request.getContextPath()%>/ControlEmail" method="POST">
			<table>
			    <tr>
			    <td>To Email-id :<input type="text" name="email" /></td> <!--enter the email whom to send mail --> 
			    <td><input type="submit" value="send"></input></td>
			    </tr>
			</table>
			</form>
				
			</div>
	</div>
	
	

</body>
</html>