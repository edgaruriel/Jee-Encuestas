<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>TEST MAIL</h1>
<form action="<%=request.getContextPath()%>/ControlTestMail" method="POST">
<table>
    <tr>
    <td>To Email-id :<input type="text" name="email" /></td> <!--enter the email whom to send mail --> 
    <td><input type="submit" value="send"></input></td>
    </tr>
</table>
</form>

</body>
</html>