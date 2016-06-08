package controller;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.DeleteDao;

@WebServlet(urlPatterns = {"/deleteComment"})
public class deleteCommentServlet  extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Connection connection = null;
		List<String> message = new ArrayList<String>();
		try {
			connection = getConnection();
			DeleteDao.deleteComment(connection,request.getParameter("id"));
			message.add("コメントを削除しました");
			session.setAttribute("messages", message);
			commit(connection);
		} catch(RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
		response.sendRedirect("./home");
	}
}
