package com.example.web;

import java.io.IOException;
import java.util.ArrayList;
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
import com.example.model.Request;
import com.example.model.Software;
import com.example.model.User;

@WebServlet("/RequestServlet")
public class RequestServlet extends HttpServlet {
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDAO dao = new RequestDAO();
		List<Request> requests = dao.getRequests();
		System.out.println(requests);
		request.setAttribute("requests", requests);
		RequestDispatcher rd = request.getRequestDispatcher("pendingRequests.jsp");
		rd.forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDAO dao = new RequestDAO();
		SoftwareDao softwareDao = new SoftwareDao();
		List<Software> softwares = new ArrayList<Software>();
		softwares = softwareDao.getSoftwares();
		String softwareName = request.getParameter("softwareName");
		int sid=0;
		for(Software s : softwares) {
			if(s.getName().equals(softwareName) ) {
				sid=s.getId();
			}
		}
		Request r = new Request();
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		r.setUserId(user.getId());
		r.setSoftwareId(sid);
		r.setAccessType("Write");
		r.setReason(request.getParameter("reason"));
		r.setStatus("Pending");
		dao.addRequest(r);
		response.sendRedirect("SoftwareServlet");
	}

}
