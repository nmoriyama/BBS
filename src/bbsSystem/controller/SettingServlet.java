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
public class SettingServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserService UserService = new UserService();
		String id = request.getParameter("id");
		UpdateUser UpdateUser = UserService.updateUser(id);
		//session.setAttribute("UpdateLoginId", request.getParameter("loginId"));
		session.setAttribute("UpdateUser", UpdateUser);
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
			String OldId = request.getParameter("OldLoginId");
			
			new UserService().update(UpdateUser, passwordCheck, OldId);


			response.sendRedirect("./management");
		} else {
			session.setAttribute("loginId", request.getParameter("loginId"));
			session.setAttribute("account", request.getParameter("account"));
			session.setAttribute("branchId", Integer.parseInt(request.getParameter("branchId")));
			session.setAttribute("positionId", Integer.parseInt(request.getParameter("positionId")));
			
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

		return UpdateUser;
	}
	
	private boolean isValid(HttpServletRequest request, List<String> messages) {
		String loginId = request.getParameter("loginId");
		String account = request.getParameter("account");
		String password = request.getParameter("password");
		String checkPassword = request.getParameter("checkPassword");
		int branchId = Integer.parseInt(request.getParameter("branchId"));
		int positionId = Integer.parseInt(request.getParameter("positionId"));
		
		if (loginId.length() < 6 || loginId.length() > 20) {
			messages.add("ログインIDは6文字以上20文字以下にして下さい");
		}
		
		if (password.length() < 6 || password.length() > 255) {
			messages.add("パスワードは6文字以上255文字以下にして下さい");
		}
		
		if (StringUtils.isEmpty(account) ==true) {
			messages.add("アカウント名を入力してください");
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
