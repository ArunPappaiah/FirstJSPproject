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
<title>Update Employee</title>
</head>
<body>
  <%
  String source="Update Employee";
	String message="<h1>Error while "+source+"</h1>";
	PrintWriter out1 = response.getWriter();
	Employee newemp = new Employee();
	//System.out.println("Enter Employee Id :");
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
		System.out.println("Employee Doesn't Exist For Id " + empId);
		return;
	}
	newemp.setEmp_id(empId);
//	System.out.println("Enter First_Name to Modify:");
	String fName = request.getParameter("fname");
	try {
		Validator.checkCharLessThanTwenty(fName);
	} catch (InvalidInputDataException err) {
		message +=" Error in First Name input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	try {
		Validator.checkDataOnlyString(fName);
	} catch (InvalidInputDataException err) {
		message +=" Error in First Name input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	newemp.setFirst_name(fName);
//	System.out.println("Enter Last_Name to Modify:");
	String lName = request.getParameter("lname");
	try {
		Validator.checkCharLessThanTwenty(lName);
	} catch (InvalidInputDataException err) {
		message +=" Error in Last Name input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	try {
		Validator.checkDataOnlyString(lName);
	} catch (InvalidInputDataException err) {
		message +=" Error in Last Name input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	newemp.setLast_name(lName);
//	System.out.println("Enter Email to Modify:");
	String eMail = request.getParameter("mail");
	try {
		Validator.checkMailContainsAtsymbol(eMail);
	} catch (InvalidInputDataException err) {
		message +=" Error in email input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	newemp.setEmail(eMail);
//	System.out.println("Enter Phone Number :");
	String num =request.getParameter("phone");
	try {
		Validator.checkStringForParseInt(num);
	} catch (InvalidInputDataException err) {
		message +=" Error in phone_no input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	try {
		Validator.checkPhoneNumberLessThanTenDigits(num);
	} catch (InvalidInputDataException err) {
		message +=" Error in phone_no input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	newemp.setPhone_number(num);
//------------------------------------------------
	 String dateFormat = "dd/MM/yyyy";
	    try {
			newemp.setHire_date(new SimpleDateFormat(dateFormat).parse(request.getParameter("date")));
		} catch (ParseException e) {
			e.printStackTrace();
		}
//	System.out.println("Enter Job_id to Modify:");
	String jobId = request.getParameter("Job_Id");
	try {
		Validator.checkCharLessThanTwenty(jobId);
	} catch (InvalidInputDataException err) {
		message +=" Error in Job Id input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	newemp.setJob_id(jobId);
	System.out.println("Enter Salary to Modify:");
	String salary = request.getParameter("Salary");
	float sal = Float.parseFloat(salary);
	try {
		Validator.checkSalaryLessThanTenDigit(sal);
	} catch (InvalidInputDataException err) {
		message +=" Error in Salary input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	try {
		Validator.checkNumberForGreaterThanZero((int) sal);
	} catch (InvalidInputDataException err) {
		message +=" Error in Salary input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	try {
		Validator.checkSalaryOnlyNumbers((int) sal);
	} catch (InvalidInputDataException err) {
		message +=" Error in Salary input </p>";
		String errorPage=ExceptionManager.handleException(err, source, message);
		out.print(errorPage);
		return;
	}
	newemp.setSalary(sal);
	int result = EmployeeDao.updateEmployee(newemp);
	System.out.println(result);
	
	out1.println("<div>Employee  Updated " + result + "</div>");
  %>
</body>
</html>