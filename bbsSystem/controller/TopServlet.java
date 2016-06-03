package bbsSystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbsSystem.beans.User;
import bbsSystem.beans.UserMessage;
import bbsSystem.service.MessageService;
@WebServlet(urlPatterns = { "/top" })
public class TopServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		User user = (User) request.getSession().getAttribute("loginUser");
		boolean isShowMessageForm;
		if (user != null) {
			isShowMessageForm = true;
		} else {
			isShowMessageForm = false;
		}
		
		List<UserMessage> postings = new MessageService().getPosting();
		List<UserMessage> comments = new MessageService().getMessage();
		request.setAttribute("users", user);
		request.setAttribute("postings", postings);
		request.setAttribute("comments", comments);
		request.setAttribute("isShowMessageForm", isShowMessageForm);
		
		request.getRequestDispatcher("top.jsp").forward(request, response);
	}
}

