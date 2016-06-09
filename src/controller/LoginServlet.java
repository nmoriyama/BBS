package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Users;
import service.LoginService;

@WebServlet(urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet (HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("login.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost (HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		String loginId = request.getParameter("loginId");
		String password = request.getParameter("password");
		LoginService loginService = new LoginService();
		Users user = loginService.login(loginId, password);
		
		if (user == null) {
			session.setAttribute("loginId", request.getParameter("loginId"));
			System.out.println(request.getParameter("loginId"));
		} else if (Integer.parseInt(user.getStatus()) == 1) {
			session.setAttribute("loginId", request.getParameter("loginId"));
		}
		session.setAttribute("loginUser", user);
		response.sendRedirect("home");
	}
}
