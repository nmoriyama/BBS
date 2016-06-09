package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Postings;
import dao.PostingsDao;

public class PostingService {
	public void register(Postings posting) {
		Connection connection = null;
		try {
			connection = getConnection();

			PostingsDao messageDao = new PostingsDao();

			messageDao.insert(connection, posting);

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
		try{	
			connection = getConnection();
			PostingsDao.delete(connection, id);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
