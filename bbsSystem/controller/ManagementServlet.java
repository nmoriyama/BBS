package bbsSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbsSystem.beans.Management;
import bbsSystem.beans.User;
import bbsSystem.service.ManagementService;
import bbsSystem.service.UserService;


@WebServlet(urlPatterns = {"/management"})
public class ManagementServlet extends HttpServlet{
	private static final long sirialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		
		User user = (User) request.getSession().getAttribute("loginUser");
<<<<<<< HEAD
=======
		boolean isShowMessageForm;
		if (user != null) {
			isShowMessageForm = true;
		} else {
			isShowMessageForm = false;
		}
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286

		List<Management> users = new ManagementService().getUsers();

		request.setAttribute("users", users);
<<<<<<< HEAD
=======
		request.setAttribute("isShowMessageForm", isShowMessageForm);
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
		
		request.getRequestDispatcher("management.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> postings = new ArrayList<String>();
		

		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		user.setStatus(request.getParameter("status"));
		new UserService().status(user);
<<<<<<< HEAD
		
		response.sendRedirect("./management");
=======
		System.out.println(request.getParameter("loginId")+",   "+request.getParameter("status"));
			response.sendRedirect("./management");
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286

	}
		
}


