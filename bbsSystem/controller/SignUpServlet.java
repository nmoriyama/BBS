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

import org.apache.commons.lang.StringUtils;

import bbsSystem.beans.InsertUser;
import bbsSystem.service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {
			InsertUser insertUser = new InsertUser();
			insertUser.setLoginId(request.getParameter("loginId"));
			insertUser.setPassword(request.getParameter("password"));
			insertUser.setCheckPassword(request.getParameter("checkPassword"));
			insertUser.setAccount(request.getParameter("account"));
			insertUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			insertUser.setPositionId(Integer.parseInt(request.getParameter("positionId")));
			

			new UserService().register(insertUser);

			response.sendRedirect("./management");
		} else {
			session.setAttribute("loginId", request.getParameter("loginId"));
			session.setAttribute("account", request.getParameter("account"));
			session.setAttribute("branchId", Integer.parseInt(request.getParameter("branchId")));
			session.setAttribute("positionId", Integer.parseInt(request.getParameter("positionId")));
			
			
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int positionId = Integer.parseInt(request.getParameter("positionId"));
		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		}
		
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		}

		if (!password.equals(checkPassword)) {
			messages.add("パスワードが一致しません");
		}
		
		if (branchId != 1 && positionId <= 2) {
			messages.add("支店の人は、店長もしくは社員としか登録できません");
		}
		
		if (branchId == 1 && positionId == 3) {
			messages.add("本社の人は、店長として登録できません");
		}
		
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}

}

