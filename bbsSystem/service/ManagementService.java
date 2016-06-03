package bbsSystem.service;

import static bbsSystem.utils.CloseableUtil.*;
import static bbsSystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbsSystem.beans.Management;
import bbsSystem.beans.UserMessage;
import bbsSystem.dao.ManagementDao;
import bbsSystem.dao.UserMessageDao;



public class ManagementService {
	public List<Management> getUsers() {

		Connection connection = null;
		try {
			connection = getConnection();
			ManagementDao managementDao = new ManagementDao();
			List<Management> ret = managementDao.select(connection);
			commit(connection);
			return ret;
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
	
	public List<UserMessage> getPosting() {

		Connection connection = null;
		try {
			connection = getConnection();
			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getPosting(connection);
			commit(connection);
			return ret;
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
