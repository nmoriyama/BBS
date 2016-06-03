package bbsSystem.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bbsSystem.beans.User;

<<<<<<< HEAD
@WebFilter(urlPatterns = {"/top", "/management", "/setting", "/signup", "/posting"})
=======
@WebFilter(urlPatterns = {"/top", "/management", "/setting", "/signup"})
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
public class LoginFilter implements Filter {
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpSession session = ((HttpServletRequest) request).getSession();
		List<String> messages = new ArrayList<String>();

			User user = (User) ((HttpServletRequest) request).getSession().getAttribute("loginUser");

<<<<<<< HEAD
		if (user == null || Integer.parseInt(user.getStatus()) == 1) {
=======
		if (user == null ) {
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
			
			messages.add("ログインしていません。");
			session.setAttribute("errorMessages", messages);
			((HttpServletResponse) response).sendRedirect("login");
		}else{
		chain.doFilter(request, response);
		}
	}
		
	public void init(FilterConfig config) {

	}
		
	public void destroy() {
	}
}

