<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Employee</title>
</head>
<body>
	<form action="EmployeeServlet" method="post">
		<center>
			<div>
				ID : <input type='text' name='Emp_Id'>
			</div>
			<div>
				<input type='submit' name='submit' value='DELETE EMPLOYEE'>
		    </div>
		</center>
	</form>
</body>
</html>