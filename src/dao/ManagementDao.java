package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.ManagementUsers;
import exception.SQLRuntimeException;

public class ManagementDao {
	public  List<ManagementUsers> select(Connection connection) {
		PreparedStatement ps = null;
		try {
			//投稿を表示
			String sql = "SELECT * FROM management ;";
			
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<ManagementUsers> ret = toUserList(rs);
			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private static List<ManagementUsers> toUserList(ResultSet rs) throws SQLException {
		List<ManagementUsers> ret = new ArrayList<ManagementUsers>();
	
 		try {
 			while (rs.next()) {
 				int id = Integer.parseInt(rs.getString("id"));
 				String loginId = rs.getString("login_id");
 				String password = rs.getString("password");
 				String account = rs.getString("account");
 				String status = rs.getString("status");
 				String branchName = rs.getString("branchName");
 				String positionName = rs.getString("positionName");
 				
 				ManagementUsers posting = new ManagementUsers();
 				
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
