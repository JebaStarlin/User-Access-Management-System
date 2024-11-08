package com.example.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.SoftwareDao;
import com.example.model.Software;

/**
 * Servlet implementation class SoftwareServlet
 */
@WebServlet("/SoftwareServlet")
public class SoftwareServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SoftwareDao dao = new SoftwareDao();
		List<Software> softwares = dao.getSoftwares();
		request.setAttribute("softwares", softwares);
		RequestDispatcher rd = request.getRequestDispatcher("showSoftwares.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SoftwareDao dao = new SoftwareDao();
		Software s = new Software();
		s.setName(request.getParameter("name"));
		s.setDescription(request.getParameter("description"));
		s.setAccessLevels(request.getParameter("access"));
		dao.createSoftware(s);
		response.sendRedirect("SoftwareServlet");
	}

}
