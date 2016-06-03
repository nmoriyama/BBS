package bbsSystem.service;

import static bbsSystem.utils.CloseableUtil.*;
import static bbsSystem.utils.DBUtil.*;

import java.sql.Connection;

import bbsSystem.beans.InsertUser;
<<<<<<< HEAD
import bbsSystem.beans.UpdateUser;
=======
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
import bbsSystem.beans.User;
import bbsSystem.dao.UserDao;
import bbsSystem.utils.CipherUtil;

public class UserService {

	public void register(InsertUser insertUser) {

		Connection connection = null;
		try {
			connection = getConnection();
			String encPassword = CipherUtil.encrypt(insertUser.getPassword());
			insertUser.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.insert(connection, insertUser);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}


<<<<<<< HEAD
	public void update(UpdateUser user, int passwordCheck) {
=======
	public void update(User user, int passwordCheck) {
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286

		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			userDao.update(connection, user, passwordCheck);

			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	
	public User getUser(String loginId, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			User user = userDao.getUser(connection, loginId, password);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void status(User user) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.status(connection, user);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	
	public void delete(User user) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.delete(connection, user);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} catch (Error e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}