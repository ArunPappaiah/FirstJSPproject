package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.commonutil.ExceptionManager;
import com.chainsys.jspproject.commonutil.InvalidInputDataException;
import com.chainsys.jspproject.commonutil.Validator;
import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class EmployeeServlet
 */
@WebServlet("/EmployeeServlet")
public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeServlet() {
        super();
    }

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("submit").equals("ADD EMPLOYEE")) {
			String source="AddNewEmployee";
			String message="<h1>Error while "+source+"</h1>";
	        PrintWriter out = response.getWriter();
			Employee newemp = new Employee();

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
			String num = request.getParameter("Phone_no");
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
			String dateFormat = "dd/MM/yyyy";
			try {
				newemp.setHire_date(new SimpleDateFormat(dateFormat).parse(request.getParameter("Date")));
			} catch (ParseException e) {
				e.printStackTrace();
			} 
			
			// -----------------------------------------------------
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

				Validator.checkNumberForGreaterThanZero(sal);
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
			int result = EmployeeDao.insertEmployee(newemp);
			System.out.println(result);
	        
			out.println("<div> Added New Employee : " + result + "</div>");
		}else if(request.getParameter("submit").equals("UPDATE EMPLOYEE")) {
			doPut(request,response);
		}else if(request.getParameter("submit").equals("DELETE EMPLOYEE")) {
			doDelete(request,response);
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source="Update Employee";
		String message="<h1>Error while "+source+"</h1>";
		PrintWriter out = response.getWriter();
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
		
		out.println("<div>Employee  Updated " + result + "</div>");
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String source="Delete Employee";
		String message="<h1>Error while "+source+"</h1>";
		PrintWriter out = response.getWriter();
		Employee newemp = new Employee();
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
			out.print("<h1>Value Deleted</h1>");
	}  

}
