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

import bbsSystem.beans.Posting;
import bbsSystem.beans.User;
import bbsSystem.service.MessageService;

@WebServlet(urlPatterns = {"/posting"})
public class PostingServlet extends HttpServlet {
	private static final long serialVersion = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("posting.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> postings = new ArrayList<String>();

		if (isValid(request, postings) == true) {
			User user = (User) session.getAttribute("loginUser");
			Posting posting = new Posting();
			//件名、本文、カテゴリー、登録日時、登録者
			posting.setSubject(request.getParameter("subject"));
			posting.setBody(request.getParameter("body"));
			posting.setCategory(request.getParameter("category"));
			posting.setUserId(Integer.parseInt(request.getParameter("id")));
			session.setAttribute("successMessages", "投稿に成功しました");
			new MessageService().register(posting);
			response.sendRedirect("./home");
		} else {
			session.setAttribute("body", request.getParameter("body"));
			session.setAttribute("subject", request.getParameter("subject"));
			session.setAttribute("category", request.getParameter("category"));
			session.setAttribute("errorMessages", postings);
			response.sendRedirect("./posting");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> postings) {
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		String category = request.getParameter("category");
		if (subject.length() == 0) {
			postings.add("件名を入力してください");
		}
		
		if (category.length() == 0) {
			postings.add("カテゴリーを入力してください");
		}
		if (body.length() == 0) {
			postings.add("本文を入力してください");
		}
		if (50 < subject.length()) {
			postings.add("件名は50文字以下で入力してください");
		}
		if (1000 < body.length()) {
			postings.add("本文は1000文字以下で入力してください");
		}
		if (10 < category.length()) {
			postings.add("カテゴリーは10文字以下で入力してください");
		}
		if (postings.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
