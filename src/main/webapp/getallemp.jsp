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
	out.println("<html><body bgcolor=gray><center><h1>All Employees List</h1></center>");
	out.println("</tr></table>");
		out.println("<hr><br>");
	out.println("<table border=0>");
    out.println("<tr><td width=137>&nbsp;</td><td>");
    out.println("<table border=1>");
    out.println("<tr><th bgcolor=silver>Emp_Id</th><th bgcolor=silver>First Name</th><th bgcolor=silver>Last Name</th><th bgcolor=silver>Email</th><th bgcolor=silver>Hire Date</th><th bgcolor=silver>Job Id</th><th bgcolor=silver>Salary</th>");
    out.println("</tr>");
	while (empIterator.hasNext()) {
		Employee emp = empIterator.next();
		out.println("<hr/>");
		out.println("<tr><td bgcolor=lightblue>");
		out.println(emp.getEmp_id() + "</td><td bgcolor=lightblue>" + emp.getFirst_name() + "</td><td width=70 bgcolor=lightblue> " + emp.getLast_name() + ",</td><td width=157 bgcolor=lightblue> " + emp.getEmail()
				+"</td><td bgcolor=lightblue> "+ emp.getHire_date() + "</td><td bgcolor=lightblue> " + emp.getJob_id() + "</td><td bgcolor=lightblue> " + emp.getSalary() + "</p>");
	}
	out1.println("</table>");		
	out1.println("</body></html>");
	%>
</body>
</html>