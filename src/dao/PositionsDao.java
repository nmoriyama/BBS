package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Positions;
import exception.SQLRuntimeException;

public class PositionsDao {
	public List<Positions> getPosition (Connection connection) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM positions ;";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Positions> ret = toPositionList(rs);		
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<Positions> toPositionList(ResultSet rs) throws SQLException {
		List<Positions> ret = new ArrayList<Positions>();
		try {
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				Positions Position = new Positions();
				Position.setId(id);
				Position.setName(name);
				ret.add(Position);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
}
