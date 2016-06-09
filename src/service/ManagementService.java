package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.ManagementUsers;
import beans.UserPostings;
import dao.ManagementDao;
import dao.PostingsDao;

public class ManagementService {
	public List<ManagementUsers> getUsers() {
		Connection connection = null;
		try {
			connection = getConnection();
			ManagementDao managementDao = new ManagementDao();
			List<ManagementUsers> ret = managementDao.select(connection);
			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	
	public List<UserPostings> getPosting() {
		Connection connection = null;
		try {
			connection = getConnection();
			PostingsDao PostingsDao = new PostingsDao();
			List<UserPostings> ret = PostingsDao.getPosting(connection);
			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
