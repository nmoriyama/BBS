package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Postings;
import beans.UserComments;
import beans.UserPostings;
import beans.Users;
import service.HomeService;
@WebServlet(urlPatterns = { "/home" })
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {		
		Users user = (Users) request.getSession().getAttribute("loginUser");
		
		List<UserComments> comments = new HomeService().getComment();
		List<String> date = new HomeService().getDate();
		
		String fromDate = new String();
		String toDate = new String();
		Postings posting = new Postings();
		if (request.getParameter("fromYear") != null) {
			fromDate = request.getParameter("fromYear")+"-"+request.getParameter("fromMonth")+"-"+request.getParameter("fromDay")+" 23:59:59";
			toDate = request.getParameter("toYear")+"-"+request.getParameter("toMonth")+"-"+request.getParameter("toDay")+" 00:00:00";
			
			request.setAttribute("firstYear", request.getParameter("fromYear"));
			request.setAttribute("firstMonth", request.getParameter("fromMonth"));
			request.setAttribute("firstDay", request.getParameter("fromDay"));
			request.setAttribute("lastYear", request.getParameter("toYear"));
			request.setAttribute("lastMonth", request.getParameter("toYear"));
			request.setAttribute("lastDay", request.getParameter("toDay"));

		} else {
			fromDate = date.get(0)+"-"+date.get(1)+"-"+date.get(2)+" 00:00:00";
			toDate = date.get(3)+"-"+date.get(4)+"-"+date.get(5)+" 23:59:59";
			request.setAttribute("firstYear", date.get(0));
			request.setAttribute("firstMonth", date.get(1));
			request.setAttribute("firstDay", date.get(2));
			request.setAttribute("lastYear", date.get(3));
			request.setAttribute("lastMonth", date.get(4));
			request.setAttribute("lastDay", date.get(5));
		}
		String SearchCategory = request.getParameter("category");
		if (request.getParameter("category") == null) {
			SearchCategory = "";
		}
		List<String> category = new HomeService().getCategory();
		
		posting.setFromDate(fromDate);
		posting.setToDate(toDate);
		posting.setSurchCategory(SearchCategory);
		List<UserPostings> postings = new HomeService().getPostingSurch(posting);
		request.setAttribute("SearchCategory", SearchCategory);
		request.setAttribute("users", user);
		request.setAttribute("postings", postings);
		request.setAttribute("comments", comments);	
		request.setAttribute("category", category);
		request.getRequestDispatcher("home.jsp").forward(request, response);
	}
}

