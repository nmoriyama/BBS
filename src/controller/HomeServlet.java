package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Postings;
import beans.UserMessage;
import beans.Users;
import service.MessageService;
@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {		
		Users user = (Users) request.getSession().getAttribute("loginUser");
		
		List<UserMessage> comments = new MessageService().getMessage();
		List<String> date = new MessageService().getDate();
		
		String fromDate;
		String toDate;
		Postings posting = new Postings();
		if (request.getParameter("toDate") != null) {
			fromDate = request.getParameter("fromDate") + " 00:00:00";
			toDate = request.getParameter("toDate") + " 23:59:59";
			String[] fromDate1 = request.getParameter("fromDate").split("-", 0);
			String[] toDate1 = request.getParameter("toDate").split("-", 0);
			
			request.setAttribute("fromYear", fromDate1[0]);
			request.setAttribute("fromMonth", fromDate1[1]);
			request.setAttribute("fromDay", fromDate1[2]);
			request.setAttribute("toYear", toDate1[0]);
			request.setAttribute("toMonth", toDate1[1]);
			request.setAttribute("toDay", toDate1[2]);
			
		} else {
			fromDate = date.get(0)+"-"+date.get(1)+"-"+date.get(2)+" 23:59:59";
			toDate = date.get(3)+"-"+date.get(4)+"-"+date.get(5)+" 00:00:00";
			request.setAttribute("fromYear", date.get(0));
			request.setAttribute("fromMonth", date.get(1));
			request.setAttribute("fromDay", date.get(2));
			request.setAttribute("toYear", date.get(3));
			request.setAttribute("toMonth", date.get(4));
			request.setAttribute("toDay", date.get(5));
		}
		String SearchCategory = request.getParameter("category");
		if (request.getParameter("category") == null) {
			SearchCategory = "";
		}
		List<String> category = new MessageService().getCategory();
		
		posting.setFromDate(fromDate);
		posting.setToDate(toDate);
		posting.setSurchCategory(SearchCategory);
		List<UserMessage> postings = new MessageService().getPostingSurch(posting);
		request.setAttribute("SearchCategory", SearchCategory);
		request.setAttribute("users", user);
		request.setAttribute("postings", postings);
		request.setAttribute("comments", comments);	
		request.setAttribute("category", category);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}

