<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	import="java.util.List,com.chainsys.jspproject.pojo.Employee,java.util.ArrayList,java.util.Iterator"%>
	 <%@ page import= "com.chainsys.jspproject.dao.EmployeeDao"%>
	 <%@ page import="java.io.PrintWriter" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>All Employees</title>
</head>
<body>
	<%
	PrintWriter out1 = response.getWriter();
	List<Employee> allEmployees = EmployeeDao.getAllEmployee();
	Iterator<Employee> empIterator = allEmployees.iterator();
	out1.println("<html><body bgcolor=gray><center><h1>All Employees List</h1></center>");
	out1.println("</tr></table>");
		out1.println("<hr><br>");
	out1.println("<table border=0>");
    out1.println("<tr><td width=137>&nbsp;</td><td>");
    out1.println("<table border=1>");
    out1.println("<tr><th bgcolor=silver>Emp_Id</th><th bgcolor=silver>First Name</th><th bgcolor=silver>Last Name</th><th bgcolor=silver>Email</th><th bgcolor=silver>Hire Date</th><th bgcolor=silver>Job Id</th><th bgcolor=silver>Salary</th>");
    out1.println("</tr>");
	while (empIterator.hasNext()) {
		Employee emp = empIterator.next();
		out1.println("<hr/>");
		out1.println("<tr><td bgcolor=lightblue>");
		out1.println(emp.getEmp_id() + "</td><td bgcolor=lightblue>" + emp.getFirst_name() + "</td><td width=70 bgcolor=lightblue> " + emp.getLast_name() + ",</td><td width=157 bgcolor=lightblue> " + emp.getEmail()
				+"</td><td bgcolor=lightblue> "+ emp.getHire_date() + "</td><td bgcolor=lightblue> " + emp.getJob_id() + "</td><td bgcolor=lightblue> " + emp.getSalary() + "</p>");
	}
	out1.println("</table>");		
	out1.println("</body></html>");
	%>
</body>
</html>