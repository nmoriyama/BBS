package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Users;
import dao.UsersDao;
import utils.CipherUtil;

public class LoginService {
	public Users login(String loginId, String password) {
		Connection connection = null;
		try {
			connection = getConnection();
			UsersDao userDao = new UsersDao();
			String encPassword = CipherUtil.encrypt(password);
			Users user = userDao.getUser(connection, loginId, encPassword);

			commit(connection);
			
			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
