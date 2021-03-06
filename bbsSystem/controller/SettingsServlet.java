package bbsSystem.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;

import bbsSystem.beans.UpdateUser;
import bbsSystem.beans.User;
import bbsSystem.service.UserService;

@WebServlet(urlPatterns = {"/setting"})
@MultipartConfig(maxFileSize = 100000)
public class SettingsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.getRequestDispatcher("setting.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		List<String> messages = new ArrayList<String>();
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		UpdateUser UpdateUser = getUpdateUser(request);
		
		if (isValid(request, messages) == true) { //仕様通りか
			int passwordCheck = 0;
			if (StringUtils.isEmpty(request.getParameter("password")) == true) { //パスワードを入力しているか
				passwordCheck = 1;
			}
			new UserService().update(UpdateUser, passwordCheck);

			response.sendRedirect("./top");
		} else {
			session.setAttribute("errorMessages", messages);
			response.sendRedirect("setting");
		}
	}

	private UpdateUser getUpdateUser(HttpServletRequest request)
			throws IOException, ServletException {


		UpdateUser UpdateUser = new UpdateUser();
		UpdateUser.setLoginId(request.getParameter("loginId"));
		UpdateUser.setPassword(request.getParameter("password"));
		UpdateUser.setCheckPassword(request.getParameter("checkPassword"));
		UpdateUser.setAccount(request.getParameter("account"));
		
		UpdateUser.setBranchId(Integer.parseInt(request.getParameter("branchId")));
		UpdateUser.setPositionId(Integer.parseInt(request.getParameter("positionId")));
		UpdateUser.setStatus(request.getParameter("status"));

		return UpdateUser;
	}
	
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		
		if (StringUtils.isEmpty(account) ==true) {
			messages.add("アカウント名を入力してください");
		}

		if (!password.equals(checkPassword)) {
			messages.add("パスワードが一致しません");
		}
		
		if (messages.size() == 0) {
			return true;
		} else {
			return false;
		}
	}
}
