<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Crear carpeta personal</title>
</head>
<body>
	
<h3>Crear nueva carpeta personal</h3>
	<form action="<%=request.getContextPath()%>/ControlCarpeta?tipo=Agregar" method="post">	
	<br>
		<label >Nombre(s):</label>
		<input type="text" name="nombre" id="nombre" size="20">
		<br>		
		<input type="hidden" name="usuarioId" value="${sessionScope.USUARIO.id}">
		<br>
	<input type="submit" value="Agregar">
</form>

</body>
</html>