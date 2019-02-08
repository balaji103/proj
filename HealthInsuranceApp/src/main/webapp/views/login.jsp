<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<style>
form label {
	display: inline-block;
	width: 100px;
}

form div {
	margin-bottom: 10px;
}

.error {
	color: red;
	margin-left: 5px;
}

label.error {
	display: inline;
}
</style>
</head>
<body>

<div align="center">
	<h3>Login Here</h3>

	<div style="color:red; text-align: center">${ERROR}</div>

	<form:form action="login" method="POST" modelAttribute="formModel"
		name="loginForm">
		<table>
			<tr>
				<td>Email:</td>
				<td><form:input path="userEmail" /></td>
			</tr>

			<tr>
				<td>Password</td>
				<td><form:password path="userPwd" /></td>
			</tr>
			<tr>
				<td><input type="Submit" value="Sign In" class="button button2" /></td>
			</tr>
		</table>
		
		<a href="forgotPwd">Forgot Pwd ?</a>
	</form:form>
</div>
</body>
</html>