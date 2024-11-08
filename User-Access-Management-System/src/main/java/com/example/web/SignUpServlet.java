package com.example.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.UserDAO;
import com.example.model.Role;
import com.example.model.User;

/**
 * Servlet implementation class SignUpServlet
 */
@WebServlet("/SignUp")
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User u = new User();
		u.setUsername(request.getParameter("username"));
		u.setPassword(request.getParameter("password"));
		u.setRole(request.getParameter("role"));
		UserDAO dao = new UserDAO();
		dao.addUser(u);
		response.sendRedirect("login.jsp");
	}

}
