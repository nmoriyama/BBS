package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comments;
import exception.SQLRuntimeException;

public class CommentDao {
	//コメントの投稿実行
	public static void insert (Connection connection, Comments comment) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO comments (posting_id, body, record_date, user_id");
			sql.append(" ) VALUES ( ");
			sql.append(" ?, ");
			sql.append(" ?, ");
			sql.append(" CURRENT_TIMESTAMP, ");
			sql.append(" ? ");
			sql.append(" );");
		
			ps = connection.prepareStatement(sql.toString());
			
			ps.setInt(1,  comment.getPostingId());
			ps.setString(2,  comment.getBody());
			ps.setInt(3,  comment.getUserId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
		
	//コメントの削除実行
	public static void delete (Connection connection, String commentId) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ?;");
			
			ps = connection.prepareStatement(sql.toString());	
			
			ps.setString(1,  commentId);
			
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}	
	}
}
