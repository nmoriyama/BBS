package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Branches;
import beans.Positions;
import beans.Users;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {
	
	//ログイン
	public Users getUser(Connection connection, String loginId,
			String password) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ? ;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			List<Users> userList = toUserList(rs);

			if (userList.isEmpty() == true) {
				return null;
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<Users> toUserList(ResultSet rs) throws SQLException {
		List<Users> ret = new ArrayList<Users>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String account = rs.getString("account");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
				String status = rs.getString("status");

				Users user = new Users();

				user.setId(id);
				user.setLoginId(loginId);
				user.setPassword(password);
				user.setAccount(account);
				user.setBranchId(branchId);
				user.setPositionId(positionId);
				user.setStatus(status);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	
	//ユーザー編集
	public Users getUpdateUser(Connection connection, String id) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ? ;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			List<Users> userList = toUserList(rs);
			
			return userList.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	//ユーザー登録
	public String insert(Connection connection, Users insertUser) {
		PreparedStatement SearchPs = null;
		PreparedStatement ps = null;
		String message = new String();
		try {
			StringBuilder SearchSql = new StringBuilder();
			SearchSql.append("SELECT * FROM users WHERE login_id = ? ;");
			SearchPs = connection.prepareStatement(SearchSql.toString());
			SearchPs.setString(1,  insertUser.getLoginId());
			ResultSet rs = SearchPs.executeQuery();
			if (rs.next()) {
				message = "そのログインIDは既に登録されています";
			} else {
				StringBuilder sql = new StringBuilder();
				sql.append("INSERT INTO users (login_id,password, account, branch_id, position_id, status) ");
				sql.append("VALUES (");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("2");
				sql.append(") ;");
				
				ps = connection.prepareStatement(sql.toString());
				
				ps.setString(1,  insertUser.getLoginId());
				ps.setString(2,  insertUser.getPassword());
				ps.setString(3,  insertUser.getAccount());
				ps.setInt(4,  insertUser.getBranchId());
				ps.setInt(5,  insertUser.getPositionId());
				ps.executeUpdate();
			}
			
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(SearchPs);
			close(ps);
		}
		return message;
	}	
	
	//ユーザー編集
	public String update(Connection connection, Users user, int passwordCheck) {
		String message = new String();
		PreparedStatement SurchPs = null;
		PreparedStatement ps = null;
		try {
			StringBuilder SearchSql = new StringBuilder();
			SearchSql.append("SELECT * FROM users WHERE login_id = ? ");
			SearchSql.append(" AND id != ? ;");
			SurchPs = connection.prepareStatement(SearchSql.toString());
			SurchPs.setString(1,  user.getLoginId());
			SurchPs.setInt(2,  user.getId());

			ResultSet rs = SurchPs.executeQuery();
			if (rs.next()) {
				message = "そのログインIDは既に登録されています";
			} else {
				
				//編集画面
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE users SET ");
				sql.append("login_id = ?, ");
				
				sql.append("account = ?, ");
				sql.append("branch_id = ?, ");
				sql.append("position_id = ? ");
				if (passwordCheck == 0) {
					sql.append("password = ?, ");
				}
				sql.append(" WHERE ");
				sql.append(" id = ? ;");
				
				ps = connection.prepareStatement(sql.toString());
				
				ps.setString(1,  user.getLoginId());
				
				if (passwordCheck == 0) {
					
					ps.setString(2,  user.getAccount());
					ps.setInt(3,  user.getBranchId());
					ps.setInt(4,  user.getPositionId());
					ps.setString(5,  user.getPassword());
					ps.setInt(6,  user.getId());
				} else {
					ps.setString(2,  user.getAccount());
					ps.setInt(3,  user.getBranchId());
					ps.setInt(4,  user.getPositionId());
					
					ps.setInt(5,  user.getId());
				}
				
				ps.executeUpdate();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(SurchPs);
			close(ps);
		}
		return message;
	}
	
	public void status(Connection connection, Users user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET ");
			sql.append("status = ?");

			sql.append(" WHERE ");
			sql.append(" id = ? ;");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1,  user.getStatus());
			ps.setInt(2,  user.getId());
			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
			
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	public void delete(Connection connection, String id) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM users WHERE id = " + id + ";";
			ps = connection.prepareStatement(sql);
			
			int count = ps.executeUpdate();
			if (count == 0) {
				throw new NoRowsUpdatedRuntimeException();
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<Positions> getPosition (Connection connection) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM positions ;";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Positions> Positions = new ArrayList<Positions>();
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				Positions Position = new Positions();
				Position.setId(id);
				Position.setName(name);
				Positions.add(Position);
			}
		
			return Positions;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	public List<Branches> getBranch (Connection connection) {
		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM branches ;";
			ps = connection.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			List<Branches> ret = new ArrayList<Branches>();
			while (rs.next()) {
				String name = rs.getString("name");
				int id = rs.getInt("id");
				Branches Branches = new Branches();
				Branches.setId(id);
				Branches.setName(name);
				ret.add(Branches);
			}
		
			return ret;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
}
