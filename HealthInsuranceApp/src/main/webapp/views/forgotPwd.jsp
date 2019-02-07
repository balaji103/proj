<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Forgot Password form</title>
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

	<h3>Login Here</h3>

	${SUCCESS}
	${ERROR}

	<form action="forgotPwd" method="POST">
		<table>
			<tr>
				<td>Email:</td>
				<td><input type="text" name="userEmail" /></td>
			</tr>

			<tr>
				<td><input type="Submit" value="Reset" class="button button2" /></td>
			</tr>
		</table>
	</form>

</body>
</html>