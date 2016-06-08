package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Branches;
import beans.Positions;
import beans.Users;
import dao.BranchesDao;
import dao.PositionsDao;
import dao.UserDao;
import utils.CipherUtil;

public class UserService {

	public String register (Users insertUser) {
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
		} finally {
			close(connection);
		}
		return message;
	}

	public String update (Users user, int passwordCheck) {
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
		} finally {
			close(connection);
		}
		return message;
	}
	
	public Users getUser(String loginId, String password) {

		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			Users user = userDao.getUser(connection, loginId, password);

			commit(connection);

			return user;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public void status(Users user) {
		Connection connection = null;
		try {
			connection = getConnection();

			UserDao userDao = new UserDao();
			userDao.status(connection, user);
			commit(connection);
		} catch (RuntimeException e) {
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
		} finally {
			close(connection);
		}
	}
	
	public Users updateUser(String loginId) {
		Connection connection = null;
		try {
			connection = getConnection();
			UserDao userDao = new UserDao();
			Users user = userDao.getUpdateUser(connection, loginId);

			commit(connection);
			
			return user;
		} catch(RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Positions> position() {
		Connection connection = null;
		try {
			connection = getConnection();
			PositionsDao positionsDao = new PositionsDao();
			List<Positions> position = positionsDao.getPosition(connection);

			commit(connection);
			return position;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

	public List<Branches> branch() {
		Connection connection = null;
		try {
			connection = getConnection();
			BranchesDao branchesDao = new BranchesDao();
			List<Branches> branch = branchesDao.getBranch(connection);

			commit(connection);
			return branch;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}

}