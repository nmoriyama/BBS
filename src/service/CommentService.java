package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Comment;
import dao.CommentDao;

public class CommentService {
	public void register(Comment comment) {

		Connection connection = null;
		try {
			connection = getConnection();
			//SQL文の実行に移動
			CommentDao.insert(connection, comment);

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
