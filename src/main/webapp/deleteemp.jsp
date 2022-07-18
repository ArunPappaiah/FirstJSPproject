<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ page import="java.io.PrintWriter" %>
 <%@ page import= "com.chainsys.jspproject.dao.EmployeeDao"%>
 <%@ page import= "com.chainsys.jspproject.pojo.Employee"%>
 <%@ page import="java.io.IOException" %>
 <%@ page import= "java.text.ParseException"%>
 <%@ page import= "java.util.Date" %>
 <%@ page import= "java.text.SimpleDateFormat" %>
  <%@ page import= "com.chainsys.jspproject.commonutil.Validator"%>
 <%@ page import= "com.chainsys.jspproject.commonutil.ExceptionManager"%>
 <%@ page import="com.chainsys.jspproject.commonutil.InvalidInputDataException"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delete Employee</title>
</head>
<body>
	<%
	String source="Delete Employee";
	String message="<h1>Error while "+source+"</h1>";
	PrintWriter out1 = response.getWriter();
	Employee newemp = (Employee)request.getAttribute("deleteemp");
	String id = request.getParameter("Emp_Id");
	try {
			Validator.checkStringForParseInt(id);
		} catch (InvalidInputDataException err) {
			message +=" Error in Employee id input </p>";
			String errorPage=ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;
		}
		int empId = Integer.parseInt(id);
		try {
			Validator.checkNumberForGreaterThanZero(empId);
		} catch (InvalidInputDataException err) {
			message +=" Error in Employee id input </p>";
			String errorPage=ExceptionManager.handleException(err, source, message);
			out.print(errorPage);
			return;
		}
		Employee emp = EmployeeDao.getEmployeeById(empId);
		if (emp == null) {
			System.out.println("Employee Doesn't Exist For Id " + empId); //
			return;
		}
		newemp.setEmp_id(empId);
		int result = EmployeeDao.deleteEmployee(empId);
		System.out.println(result);
		out1.print("<h1>Value Deleted</h1>");

	%>
</body>
</html>