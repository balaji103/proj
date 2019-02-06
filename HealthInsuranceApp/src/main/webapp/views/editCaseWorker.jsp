<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>

<script type="text/javascript">
	$(function() {
		//This function check Email Uniqueness 
		$("#email").blur(function() {
			var enteredEmail = $("#email").val();
			var userId = $("#uid").val();
			$.ajax({
				url : "/checkEmail",
				data : "email=" + enteredEmail,
				success : function(result) {
					if (result == 'DUPLICATE') {
						$("#emailMsg").html("Email already Exist!!");
						$("#email").focus();
					} else {
						$("#emailMsg").html(" ");
					}
				}
			});

		});

		//This function is for get Datepicker
		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			maxDate : new Date()
		});
	

	//This function validate form elements
	 $('form[id="userRegForm"]').validate({
		rules : {
			firstName : 'required',
			lastName : 'required',
			userPhno : 'required',
			userDob : 'required',
			userRole : 'required',
			userEmail : {
				required : true,
				email : true,
			},
			
		},
		messages : {
			firstName : 'This field is required',
			lastName : 'This field is required',
			userPhno : 'This field is required',
			userDob : 'This field is required',
			userRole : 'This field is required',
			userEmail : 'Enter a valid email',
			
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
});
</script>

<title>Edit Form</title>
</head>
<body>
	
	<%@ include file="header-inner.jsp" %>
	
	<h2 style="color: gray; text-align: center">EDIT CASE WORKER PROFILE</h2>
	
	<form:form action="editCaseWorker" method="post" modelAttribute="formModel">
		<div align="center">
			<table>
				<tr>
					<td>User Id :</td>
					<td><form:input path="userId" id="userId" readonly="true"/></td>
				</tr>
				
				<tr>
					<td>First Name :</td>
					<td><form:input path="firstName" id="firstName"/></td>
				</tr>

				<tr>
					<td>Last Name :</td>
					<td><form:input path="lastName" /></td>
				</tr>

				<tr>
					<td>Email :</td>
					<td><form:input path="userEmail" id="email"/>
					<font color="red"><span id="emailMsg"></span></font>
					</td>
				</tr>

				
				<tr>
					<td>Password :</td>
					<td><form:input path="userPwd" readonly="true"/></td>
					
					<form:hidden path="activeSw"/>
					<form:hidden path="createDate"/>
					<form:hidden path="createdBy"/>
				</tr> 
				

				<tr>
					<td>Phone :</td>
					<td><form:input path="userPhno" /></td>
				</tr>

				<tr>
					<td>DOB :</td>
					<td><form:input path="userDob" id="datepicker"/></td>
				</tr>

				<tr>
					<td>User Role :</td>
					<td><form:select path="userRole" items="${userRole}" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Update" /></td>
				</tr>
			</table>
		</div>
	</form:form>
</body>
</html>