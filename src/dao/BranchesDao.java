package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Branches;
import exception.SQLRuntimeException;

public class BranchesDao {
	public List<Branches> getBranch(Connection connection) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches ;";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Branches> ret = toBranchList(rs);		
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<Branches> toBranchList(ResultSet rs) throws SQLException {
		List<Branches> ret = new ArrayList<Branches>();
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				Branches Branches = new Branches();
				Branches.setId(id);
				Branches.setName(name);
				ret.add(Branches);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
