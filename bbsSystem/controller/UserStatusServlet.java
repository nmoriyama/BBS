package bbsSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbsSystem.beans.Comment;
import bbsSystem.beans.Management;
import bbsSystem.service.CommentService;

public class UserStatusServlet extends HttpServlet{
	private static final long sirialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> postings = new ArrayList<String>();
		if (isValid(request, postings) == true) {
			Management user = (Management) session.getAttribute("loginUser");
			Comment comment = new Comment();
			comment.setBody(request.getParameter("comment"));
			comment.setUserId((user.getId()));
			comment.setPostingId(2);
			
			new CommentService().register(comment);
			response.sendRedirect("./management");
		} else {
			session.setAttribute("errorMessage", postings);
			response.sendRedirect("./management");
		}
	}
}
