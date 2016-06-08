package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Comment;
import exception.SQLRuntimeException;

public class CommentDao {
	public static void insert(Connection connection, Comment comment){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into comments (posting_id, body, record_date, user_id");
			sql.append(" ) values ( ");
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
}
