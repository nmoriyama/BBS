package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;

import beans.Comments;
import dao.CommentsDao;

public class CommentService {
	public void register(Comments comment) {
		Connection connection = null;
		try {
			connection = getConnection();
			//SQL文の実行に移動
			CommentsDao.insert(connection, comment);
			commit(connection);
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
}
