package com.project.servlets;

import java.io.IOException;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import com.project.db.UserDBUtil;
import com.project.models.User;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Register() {
		super();
		// TODO Auto-generated constructor stub

	}

	@Resource(name = "jdbc/project")
	private DataSource datasource;
	private UserDBUtil userdb;

	@Override
	public void init() throws ServletException {
		// TODO Auto-generated method stub
		super.init();
		userdb = new UserDBUtil(datasource);

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		System.out.println("do get in resgiet");
		String name = request.getParameter("uname");
		String pass = request.getParameter("psw");
		String email = request.getParameter("ename");

		User tmpUser = new User(email, name, pass);
		tmpUser.register(userdb);
		
		//response.sendRedirect("Login.jsp");
		

		System.out.println(userdb);

		System.out.println(name);
		System.out.println(pass);
		System.out.println("hello");

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
