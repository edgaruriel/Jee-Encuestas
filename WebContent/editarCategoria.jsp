<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="recursos/jquery/jquery-1.8.2.min.js"></script>
<script type="text/javascript" src="recursos/jqueryValidation/jquery-validation.js"></script>
<script type="text/javascript" src="recursos/js/categoria.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="recursos/css/menu.css" media="screen" />
<title>Insert title here</title>
</head>
<body  bgcolor="#0489B1">
	<div id="contenedor">
		<div id="menu">
			<c:set var="user" scope="session" value="${sessionScope.USUARIO}"></c:set>
			<a href="<%=request.getContextPath()%>/ControlLogin?tipo=LogOut" >Salir</a>
			<h1>
			Bienvenido <c:out value="${user.nombre}"/> 
			</h1>
				<br>
				<h2><a href="usuarios.jsp">Usuarios</a></h2>
				<br>
				<h3><a href="<%=request.getContextPath()%>/ControlCategoria?tipo=Cargar">Categorias</a> </h3>
				<br>
			<h3><a href="<%=request.getContextPath()%>/ControlUsuario?tipo=Editar&id=${sessionScope.USUARIO.id}">Mi Cuenta</a></h3>
		
		</div>
		<div id="cuerpo">
			<h3>Editar Categoria</h3>
			<a href="categorias.jsp">Atras</a>
			
			<c:set var="categoria" scope="session" value="${sessionScope.categoria}"></c:set>
			<form action="<%=request.getContextPath()%>/ControlCategoria?tipo=Actualizar" method="post">	
				<br>
					<label >Nombre:</label>
					<input type="text" name="nombre" id="nombre" size="20" value="${categoria.nombre}">
					<br>
					<input type="hidden" id="id" name="id" value="${categoria.id}" >
				<input type="submit" value="Agregar">
			</form>
			
		</div>
	</div>

</body>
</html>