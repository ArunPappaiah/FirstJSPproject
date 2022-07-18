<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Add Employee</title>
</head>
<body>
	<form action="EmployeeServlet" method="post">
		<!--need to mention method = post  -->
		<center>
			<div>
				ID : <input type='text' name='Emp_Id' placeholder="Employee Id">
			</div>
			<div>
				First Name : <input type='text' name='fname' placeholder="First name">
			</div>
			<div>
				Last Name : <input type='text' name='lname' placeholder="Last name">
			</div>
			<div>
				E-mail : <input type='text' name='mail' placeholder="example@gmail.com">
			</div>
			<div>
				Phone-No : <input type='text' name='Phone_no' placeholder="phone">
			</div>
			<div>
				Hire Date : <input type='text' name='Date' placeholder="dd/MM/yyyy">
			</div>
			<div>
				Job ID : <input type='text' name='Job_Id' placeholder="Job Id">
			</div>
			<div>
				Salary : <input type='text' name='Salary' placeholder="Salary">
			</div>
			<div>
				<input type='submit' name='submit' value='ADD EMPLOYEE'>
			</div>
		</center>
	</form>
</body>
</html>