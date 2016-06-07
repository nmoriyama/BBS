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
@WebServlet(urlPatterns = { "/search" })
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
	User user = (User) request.getSession().getAttribute("loginUser");
	Posting posting = new Posting();
	String fromDate = request.getParameter("fromYear")+"-"+request.getParameter("fromMonth")+"-"+request.getParameter("fromDays")+" 00:00:00";
	String toDate = request.getParameter("toYear")+"-"+request.getParameter("toMonth")+"-"+request.getParameter("toDays")+" 23:59:59";
	String category = request.getParameter("category");

	posting.setFromDate(fromDate);
	posting.setToDate(toDate);
	posting.setSurchCategory(category);
	List<UserMessage> postings = new MessageService().getPostingSurch(posting);
	request.setAttribute("postings", postings);
	request.getRequestDispatcher("top.jsp").forward(request, response);
}
}
