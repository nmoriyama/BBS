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

import bbsSystem.beans.User;
import bbsSystem.service.UserService;

@WebServlet(urlPatterns = {"/deleteUser"})
public class DeleteUser extends HttpServlet{
	private static final long sirialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		User user = new User();
		user.setLoginId(request.getParameter("loginId"));
		new UserService().delete(user);
		response.sendRedirect("./management");

	}
		
}
