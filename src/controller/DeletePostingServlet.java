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

import service.PostingService;

@WebServlet(urlPatterns = {"/deletePosting"})
public class DeletePostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<String> message = new ArrayList<String>();
		
		new PostingService().delete(request.getParameter("id"));
		message.add("投稿を削除しました");
		session.setAttribute("messages", message);

		response.sendRedirect("./home");
	}


}
