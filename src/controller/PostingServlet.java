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

import beans.Postings;
import service.PostingService;

@WebServlet(urlPatterns = {"/posting"})
public class PostingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		request.getRequestDispatcher("posting.jsp").forward(request, response);
	}
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		HttpSession session = request.getSession();
		List<String> messages = new ArrayList<String>();
		if (isValid(request, messages) == true) {
			Postings posting = new Postings();
			//件名、本文、カテゴリー、登録日時、登録者
			posting.setSubject(request.getParameter("subject"));
			String text = request.getParameter("body");
			
			text = text.replaceAll("\n","<br>");//改行時
			posting.setBody(text);
			posting.setCategory(request.getParameter("category"));
			posting.setUserId(Integer.parseInt(request.getParameter("id")));
			messages.add("投稿に成功しました");
			session.setAttribute("messages", messages);
			new PostingService().register(posting);
			response.sendRedirect("./home");
		} else {
			session.setAttribute("body", request.getParameter("body"));
			session.setAttribute("subject", request.getParameter("subject"));
			session.setAttribute("category", request.getParameter("category"));
			session.setAttribute("messages", messages);
			response.sendRedirect("./posting");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String subject = request.getParameter("subject");
		String body = request.getParameter("body");
		String category = request.getParameter("category");
		if (subject.length() == 0) {
			messages.add("件名を入力してください");
		}
		
		if (50 < subject.length()) {
			messages.add("件名は50文字以下で入力してください");
		}
		
		if (category.length() == 0) {
			messages.add("カテゴリーを入力してください");
		}
		
		if (10 < category.length()) {
			messages.add("カテゴリーは10文字以下で入力してください");
		}
		
		if (body.length() == 0) {
			messages.add("本文を入力してください");
		}
		
		if (1000 < body.length()) {
			messages.add("本文は1000文字以下で入力してください");
		}
		
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}
