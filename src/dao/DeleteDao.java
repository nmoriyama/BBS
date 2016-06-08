package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import exception.SQLRuntimeException;

public class DeleteDao {
	public static void delete(Connection connection, String number){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM postings WHERE id = ?;");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1,  number);

			ps.executeUpdate();
	} catch(SQLException e) {
		throw new SQLRuntimeException(e);
	} finally {
		close(ps);
	}
}	
}
