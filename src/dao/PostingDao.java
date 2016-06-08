package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Postings;
import exception.SQLRuntimeException;

public class PostingDao {
	public void insert (Connection connection, Postings posting) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postings (subject, body, category, record_date, user_id)");
			sql.append(" VALUES (");
			sql.append(" ? ,");
			sql.append(" ? ,");
			sql.append(" ? ,");
			sql.append(" CURRENT_TIMESTAMP, ");
			sql.append(" ?");
			sql.append(" );");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, posting.getSubject());
			ps.setString(2, posting.getBody());
			ps.setString(3, posting.getCategory());
			ps.setInt(4, posting.getUserId());

			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}		
	
	public static void delete (Connection connection, String postingId) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM postings WHERE id = ?;");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, postingId);

			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}	
}
