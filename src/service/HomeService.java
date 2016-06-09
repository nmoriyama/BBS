package service;

import static utils.CloseableUtil.*;
import static utils.DBUtil.*;

import java.sql.Connection;
import java.util.List;

import beans.Postings;
import beans.UserComments;
import beans.UserPostings;
import dao.CommentsDao;
import dao.PostingsDao;

public class HomeService {
	public List<UserComments> getComment() {
		Connection connection = null;
		try {
			connection = getConnection();
			CommentsDao CommentsDao = new CommentsDao();
			List<UserComments> ret = CommentsDao.getComments(connection);
			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	
	public List<String> getCategory() {
		Connection connection = null;
		try {
			connection = getConnection();
			PostingsDao PostingsDao = new PostingsDao();
			List<String> ret = PostingsDao.getCategory(connection);

			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	
	public List<String> getDate() {  //絞込み初期値取得
		Connection connection = null;
		try {
			connection = getConnection();
			PostingsDao PostingsDao = new PostingsDao();
			List<String> ret = PostingsDao.getDate(connection);

			commit(connection);
			return ret;
		} catch (RuntimeException e) {
			rollback(connection);
			throw e;
		} finally {
			close(connection);
		}
	}
	
	public List<UserPostings> getPostingSurch(Postings posting, List<String> search) { //絞り込み機能
		Connection connection = null;
		try {
			connection = getConnection();
			PostingsDao PostingsDao = new PostingsDao();

			List<UserPostings> ret = PostingsDao.getPostingSearch(connection, posting, search);
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
