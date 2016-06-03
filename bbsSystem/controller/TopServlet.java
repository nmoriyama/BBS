package bbsSystem.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import bbsSystem.beans.Posting;
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
		
		List<UserMessage> comments = new MessageService().getMessage();
		List<String> date = new MessageService().getDate();
		
		String fromDate;
		String toDate;
		Posting posting = new Posting();
		if (request.getParameter("fromYear") != null) {
			fromDate = request.getParameter("fromYear")+"-"+request.getParameter("fromMonth")+"-"+request.getParameter("fromDay")+" 23:59:59";
			toDate = request.getParameter("toYear")+"-"+request.getParameter("toMonth")+"-"+request.getParameter("toDay")+" 00:00:00";
		} else {
			
			fromDate = date.get(0)+"-"+date.get(1)+"-"+date.get(2)+" 23:59:59";
			toDate = date.get(3)+"-"+date.get(4)+"-"+date.get(5)+" 00:00:00";

		}
		
		
		String category = request.getParameter("category");
		
		posting.setFromDate(fromDate);
		posting.setToDate(toDate);
		posting.setSurchCategory(category);
		List<UserMessage> postings = new MessageService().getPostingSurch(posting);
		request.setAttribute("users", user);
		request.setAttribute("postings", postings);
		request.setAttribute("comments", comments);
		request.setAttribute("firstYear", date.get(0));
		request.setAttribute("firstMonth", date.get(1));
		request.setAttribute("firstDay", date.get(2));
		request.setAttribute("lastYear", date.get(3));
		request.setAttribute("lastMonth", date.get(4));
		request.setAttribute("lastDay", date.get(5));
		request.getRequestDispatcher("top.jsp").forward(request, response);
	}
}

