package com.example.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.dao.RequestDAO;
import com.example.dao.SoftwareDao;
import com.example.model.Request;

/**
 * Servlet implementation class ApprovalServlet
 */
@WebServlet("/ApprovalServlet")
public class ApprovalServlet extends HttpServlet {
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDAO requestDAO = new RequestDAO();
		SoftwareDao softwareDao = new SoftwareDao();
		List<Request> requests = requestDAO.getRequests();
		int id = Integer.parseInt(request.getParameter("id"));
		String adminAction = request.getParameter("adminAction");
		Request request1=null;
		for(Request r : requests) {
			if(r.getId()==id) {
				request1 = r;
				break;
			}
		}
		int softId = request1.getSoftwareId();
		if(adminAction.equals("approved")) {
			requestDAO.alterRequest(id, "Approved");
			softwareDao.alterSoftware(softId, request1.getAccessType());
		}
		else {
			requestDAO.alterRequest(id, "Rejected");
		}
		response.sendRedirect("RequestServlet");
		
	}

}
