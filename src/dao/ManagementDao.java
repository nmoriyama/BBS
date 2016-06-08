package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Management;
import exception.SQLRuntimeException;

public class ManagementDao {
	public  List<Management> select(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT users.id, login_id, password, account, branches.name AS branchName,  "
					+ " positions.name AS positionName ,status "
					+ " FROM users INNER JOIN branches ON"
					+ " users.branch_id = branches.id "
					+ " INNER JOIN positions ON"
					+ " users.position_id = positions.id "
					+ " ORDER BY  "
					+ " users.position_id "
					+ " ASC;");
			
			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<Management> ret = toUserList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private static List<Management> toUserList(ResultSet rs) throws SQLException {
		List<Management> ret = new ArrayList<Management>();
 		try {
 			while (rs.next()) {
 				int id = Integer.parseInt(rs.getString("id"));
 				String loginId = rs.getString("login_id");
 				String password = rs.getString("password");
 				String account = rs.getString("account");
 				String status = rs.getString("status");
 				String branchName = rs.getString("branchName");
 				String positionName = rs.getString("positionName");
 				
 				Management posting = new Management();
 				
 				posting.setId(id);
 				posting.setLoginId(loginId);
 				posting.setPassword(password);
 				posting.setAccount(account);
 				posting.setStatus(status);
 				posting.setBranchName(branchName);
 				posting.setPositionName(positionName);
 				
 				ret.add(posting);
 			}
 			return ret;
 		} finally {
 			close(rs);
 		}
	}
}
