package bbsSystem.dao;

import static bbsSystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import bbsSystem.exception.SQLRuntimeException;

public class DeleteDao {
	public static void delete(Connection connection, String number){
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM postings WHERE id = ?;");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1,  number);
<<<<<<< HEAD
			
=======
			System.out.println(ps);
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
			ps.executeUpdate();
	} catch(SQLException e) {
		throw new SQLRuntimeException(e);
	} finally {
		close(ps);
	}
}	
}
