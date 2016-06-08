package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Management;
import beans.Users;
import service.ManagementService;
import service.UserService;


@WebServlet(urlPatterns = {"/management"})
public class ManagementServlet extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		List<Management> users = new ManagementService().getUsers();
		request.setAttribute("users", users);
		request.getRequestDispatcher("management.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();

		Users user = new Users();
		user.setId(Integer.parseInt(request.getParameter("id")));
		user.setStatus(request.getParameter("status"));
		new UserService().status(user);
		String messages = new String();
		if (Integer.parseInt(request.getParameter("status")) == 1) {
			messages = "ユーザーを停止中にしました";
		} else {
			messages = "ユーザーを利用可能にしました";
		}
		
		session.setAttribute("messages", messages);
		response.sendRedirect("./management");
	}
		
}


