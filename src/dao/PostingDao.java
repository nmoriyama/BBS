package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import beans.Posting;
import exception.SQLRuntimeException;

public class PostingDao {
	public void insert(Connection connection, Posting posting) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("insert into postings (subject, body, category, record_date, user_id)");
			sql.append("values (");
			sql.append("? ,");
			sql.append("? ,");
			sql.append("? ,");
			sql.append("CURRENT_TIMESTAMP, ");
			sql.append("?");
			sql.append(");");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1,  posting.getSubject());
			ps.setString(2,  posting.getBody());
			ps.setString(3,  posting.getCategory());
			ps.setInt(4,  posting.getUserId());

			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}		
}
