package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.InsertUser;
import beans.UpdateUser;
import beans.User;
import dao.UserDao;
import utils.CipherUtil;

public class UserService {

	public String register(InsertUser insertUser) {
		String message = new String();
		Connection connection = null;
		try {
			connection = getConnection();
			String encPassword = CipherUtil.encrypt(insertUser.getPassword());
			insertUser.setPassword(encPassword);

			UserDao userDao = new UserDao();
			message = userDao.insert(connection, insertUser);
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
		return message;
	}



	public String update(UpdateUser user, int passwordCheck) {
		String message = new String();
		Connection connection = null;
		try {
			connection = getConnection();

			String encPassword = CipherUtil.encrypt(user.getPassword());
			user.setPassword(encPassword);

			UserDao userDao = new UserDao();
			message = userDao.update(connection, user, passwordCheck);

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
		return message;
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
	
	public void delete(String id) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.delete(connection, id);
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
	
	public UpdateUser updateUser(String loginId) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			UpdateUser user = userDao.getUpdateUser(connection, loginId);

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