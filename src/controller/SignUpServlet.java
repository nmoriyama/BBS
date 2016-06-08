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

import org.apache.commons.lang.StringUtils;

import beans.Branches;
import beans.Positions;
import beans.Users;
import service.UserService;

@WebServlet(urlPatterns = { "/signup" })
public class SignUpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {
		List<Positions> positions = new UserService().position();
		List<Branches> branches = new UserService().branch();
		HttpSession session = request.getSession();
		session.setAttribute("positons", positions);
		session.setAttribute("branches", branches);
		request.getRequestDispatcher("signup.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {

		List<String> messages = new ArrayList<String>();

		HttpSession session = request.getSession();
		if (isValid(request, messages) == true) {
			Users insertUser = new Users();
			insertUser.setLoginId(request.getParameter("loginId"));
			insertUser.setPassword(request.getParameter("password"));
			
			insertUser.setAccount(request.getParameter("account"));
			insertUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
			insertUser.setPositionId(Integer.parseInt(request.getParameter("positionId")));

			String message = new UserService().register(insertUser);
			
			if (!message.isEmpty()){
				messages.add(message);
				session.setAttribute("loginId", request.getParameter("loginId"));
				session.setAttribute("account", request.getParameter("account"));
				session.setAttribute("branchId", Integer.parseInt(request.getParameter("branchId")));
				session.setAttribute("positionId", Integer.parseInt(request.getParameter("positionId")));
				
				session.setAttribute("messages", messages);
				response.sendRedirect("signup");
			} else {
				message = "ユーザーを登録しました";
				messages.add(message);
				session.setAttribute("messages", messages);
				response.sendRedirect("./management");
			}
		} else {
			session.setAttribute("loginId", request.getParameter("loginId"));
			session.setAttribute("account", request.getParameter("account"));
			session.setAttribute("branchId", Integer.parseInt(request.getParameter("branchId")));
			session.setAttribute("positionId", Integer.parseInt(request.getParameter("positionId")));
			
			session.setAttribute("messages", messages);
			response.sendRedirect("signup");
		}
	}

	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int positionId = Integer.parseInt(request.getParameter("positionId"));
		
		
		if (StringUtils.isEmpty(loginId) == true) {
			messages.add("ログインIDを入力してください");
		} else if (loginId.length() < 6 || loginId.length() > 20) {
			messages.add("ログインIDは6文字以上20文字以下にして下さい");
		}
		
		if (StringUtils.isEmpty(password) == true) {
			messages.add("パスワードを入力してください");
		} else if (password.length() < 6 || password.length() > 255) {
			messages.add("パスワードは6文字以上255文字以下にして下さい");
		}
		
		if (!password.equals(checkPassword)) {
			messages.add("パスワードが一致しません");
		}
		
		if (StringUtils.isEmpty(account) == true) {
			messages.add("アカウント名を入力してください");
		} else if (account.length() > 10) {
			messages.add("アカウント名は10文字以下にして下さい");
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

