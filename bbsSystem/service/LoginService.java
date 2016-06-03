package bbsSystem.service;

import static bbsSystem.utils.CloseableUtil.*;
import static bbsSystem.utils.DBUtil.*;

import java.sql.Connection;

import bbsSystem.beans.User;
import bbsSystem.dao.UserDao;
import bbsSystem.utils.CipherUtil;

public class LoginService {
	public User login(String loginId, String password) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			String encPassword = CipherUtil.encrypt(password);
			User user = userDao.getUser(connection, loginId, encPassword);

			commit(connection);
			
			return user;
		} catch(RuntimeException e) {
			rollback(connection);
			throw e;
		} catch(Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
