package com.chainsys.jspproject;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
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
			Employee newemp = new Employee();
			request.setAttribute("addemp",newemp);
			RequestDispatcher rd = request.getRequestDispatcher("addemp.jsp");
			rd.forward(request, response);
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
		List<Employee> allEmployees = EmployeeDao.getAllEmployee();
		request.setAttribute("allemp", allEmployees);
		RequestDispatcher rd = request.getRequestDispatcher("getallemp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
	 */
	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Employee newemp = new Employee();
		request.setAttribute("updateemp", newemp);
		RequestDispatcher rd = request.getRequestDispatcher("updateemp.jsp");
		rd.forward(request, response);
	}

	/**
	 * @see HttpServlet#doDelete(HttpServletRequest, HttpServletResponse)
	 */
	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Employee newemp = new Employee();
		request.setAttribute("deleteemp", newemp);
		RequestDispatcher rd = request.getRequestDispatcher("deleteemp.jsp");
		rd.forward(request, response);
	}  

}
