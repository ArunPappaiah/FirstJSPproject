package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.chainsys.jspproject.dao.EmployeeDao;
import com.chainsys.jspproject.pojo.Employee;

/**
 * Servlet implementation class AddEmployee
 */
@WebServlet("/AddEmployee")
public class AddEmployee extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		    response.getWriter().print("<h1>Post called</h1>");
			PrintWriter out = response.getWriter();
			Employee newemp = new Employee();
			String id = request.getParameter("Emp_Id");
			int empId = Integer.parseInt(id);
			newemp.setEmp_id(empId);
			String fName = request.getParameter("fname");
			newemp.setFirst_name(fName);
			String lName = request.getParameter("lname");
			newemp.setLast_name(lName);
			String eMail = request.getParameter("mail");
			newemp.setEmail(eMail);
			String num = request.getParameter("Phone_no");
			newemp.setPhone_number(num);
			String dateFormat = "dd/MM/yyyy";
			try {
				newemp.setHire_date(new SimpleDateFormat(dateFormat).parse(request.getParameter("Date")));
			} catch (ParseException e) {
				e.printStackTrace();
			}
			String jobId = request.getParameter("Job_Id");
			newemp.setJob_id(jobId);
			String salary = request.getParameter("Salary");
			float sal = Float.parseFloat(salary);
			newemp.setSalary(sal);
			int result = EmployeeDao.insertEmployee(newemp);
			System.out.println(result);

			out.println("<div> Added New Employee : " + result + "</div>");
	}
}
