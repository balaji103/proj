<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>All User List</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<script>
	$(function() {
		$("#tabs").tabs();
	});
	
 </script>

<style>
table, th, td {
	border: 1px solid black;
	border-collapse: collapse;
}

th {
	border: 1px solid black;
	border-collapse: collapse;
	background-color: 0abfcd;
}

th, td {
	padding: 10px;
	text-align: left;
}
</style>

</head>
<body>

	<div id="tabs">	
		<button><a href="viewUsers?cpn=1&userStatus=Y">Active</a></button>
		<button><a href="inActiveUsers?cpn=1&userStatus=N">In-Active</a></button>
	
			<div align="center">
			
				<%@include file="header-inner.jsp"%>

				<h3>Active User Profiles</h3>
				<div style="color: green">${SUCCESS}</div>
				<div style="color: red">${ERROR}</div>

				<table>
					<thead>
						<tr>
							<th>S.No</th>
							<th>Reg No</th>
							<th>HOH Name</th>
							<th>Created Date</th>
							<th>User Status</th>
						</tr>
					</thead>
					<tbody>
						<c:if test="${userModelList eq null }">
							<tr>
								<td colspan="5">No Records Found</td>
							</tr>
						</c:if>
						<c:forEach items="${userModelList}" var="um" varStatus="index">
							<tr>
								<td><c:out value="${index.count}" /></td>
								<td><c:out value="${um.regNo }" /></td>
								<td><c:out value="${um.firstName }" /></td>
								<td><c:out value="${um.createdDate }" /></td>

								<td><c:if test="${um.userStatus=='Y'}">
										<a href="deActivateUser?uid=${um.regNo}&cpn=${cpn}"> <img
											src="images/delete.png" width="20" height="20"
											onclick="return confirmDelete()" title="Deactivate" />
										</a>
									</c:if>
								</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
				<table>
					<tr>
						<c:if test="${cpn ne 1 }">
							<td><a href="viewUsers?cpn=${cpn-1}">Previous</a></td>
						</c:if>
						<c:forEach begin="1" end="${totalPages}" var="pageNo">
							<c:choose>
								<c:when test="${cpn==pageNo}">
									<td>${cpn}</td>
								</c:when>
								<c:otherwise>
									<td><a href="viewUsers?cpn=${pageNo}">${pageNo}</a></td>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<c:if test="${cpn ne totalPages }">
							<td><a href="viewUsers?cpn=${cpn+1}">Next</a></td>
						</c:if>
					</tr>
				</table>
			</div>
		
 </div>
</body>
</html>