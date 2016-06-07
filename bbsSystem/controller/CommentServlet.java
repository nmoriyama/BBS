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

import bbsSystem.beans.Comment;
import bbsSystem.beans.User;
import bbsSystem.service.CommentService;

@WebServlet(urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet{
	private static final long sirialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> postings = new ArrayList<String>();
		if (isValid(request, postings) == true) {
			User user = (User) session.getAttribute("loginUser");
			Comment comment = new Comment();
			comment.setBody(request.getParameter("comment"));
			comment.setUserId((user.getId()));
			comment.setPostingId(Integer.parseInt(request.getParameter("postingId")));
			
			new CommentService().register(comment);
			response.sendRedirect("./top");
		} else {
	
<<<<<<< HEAD
			session.setAttribute("errorMessages", postings);
=======
			session.setAttribute("errorMessage", postings);
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
			response.sendRedirect("./top");
		}
	}
		
	private boolean isValid(HttpServletRequest request, List<String> postings) {
		//入力の確認
		String comment = request.getParameter("comment");
		if (comment.length() == 0) {
			postings.add("メッセージを入力してください");
		}
		if (500 < comment.length()) {
			postings.add("コメントは500文字以下で入力してください");
		}
		if (postings.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
