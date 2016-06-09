package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Comments;
import beans.Users;
import service.CommentService;
@WebServlet(urlPatterns = {"/comment"})
public class CommentServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request, 
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		if (isValid(request, messages) == true) {
			Users user = (Users) session.getAttribute("loginUser");
			Comments comment = new Comments();
			String text = request.getParameter("comment");
			
			text = text.replaceAll("\n","<br>");
			
			session.setAttribute("messages", text);
			comment.setBody(text);
			comment.setUserId((user.getId()));
			comment.setPostingId(Integer.parseInt(request.getParameter("postingId")));
			
			new CommentService().register(comment);
			messages.add("コメントに成功しました");
			session.setAttribute("messages", messages);
			response.sendRedirect("./home");
		} else {
			session.setAttribute("messages", messages);
			response.sendRedirect("./home");
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
