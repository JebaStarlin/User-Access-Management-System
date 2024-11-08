package com.example.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.example.dao.RequestDAO;
import com.example.dao.SoftwareDao;
import com.example.dao.UserDAO;
import com.example.model.Request;
import com.example.model.Software;
import com.example.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		UserDAO dao = new UserDAO();
		User user = dao.verify(username, password);
		if(user!=null) {
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("role", user.getRole());
		
			if(user.getRole().equals("Employee")) {
				SoftwareDao softwareDao = new SoftwareDao();
				List<Software> softwares = softwareDao.getSoftwares();
				request.setAttribute("softwares", softwares);
				RequestDispatcher rd = request.getRequestDispatcher("requestAccess.jsp");
				rd.forward(request, response);
			}else if(user.getRole().equals("Manager")) {
				response.sendRedirect("RequestServlet");
			}else {
				RequestDispatcher rd = request.getRequestDispatcher("createSoftware.jsp");
				rd.forward(request, response);
			}
		}
		else {
			PrintWriter out =response.getWriter();
			out.print("User not found");
		}
	}

}
