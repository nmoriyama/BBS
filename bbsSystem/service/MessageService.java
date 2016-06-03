package bbsSystem.service;

import static bbsSystem.utils.CloseableUtil.*;
import static bbsSystem.utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import bbsSystem.beans.Posting;
import bbsSystem.beans.UserMessage;
import bbsSystem.dao.PostingDao;
import bbsSystem.dao.UserMessageDao;

public class MessageService {

	public void register(Posting posting) {

		Connection connection = null;
		try {
			connection = getConnection();

			PostingDao messageDao = new PostingDao();
			messageDao.insert(connection, posting);

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


	public List<UserMessage> getMessage() {

		Connection connection = null;
		try {
			connection = getConnection();
			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getUserMessages(connection);
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
	 
	public List<UserMessage> getPostingSurch(Posting posting) { //絞り込み機能
		Connection connection = null;
		try {
			connection = getConnection();
			UserMessageDao messageDao = new UserMessageDao();
			List<UserMessage> ret = messageDao.getPostingSearch(connection, posting);
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
