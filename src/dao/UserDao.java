package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.InsertUser;
import beans.UpdateUser;
import beans.User;
import exception.NoRowsUpdatedRuntimeException;
import exception.SQLRuntimeException;

public class UserDao {
	public User getUser(Connection connection, String loginId,
			String password) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE login_id = ? AND password = ?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, loginId);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			List<User> userList = toUserList(rs);
			if (userList.isEmpty() == true) {
				return null;
			} else if (7 <= userList.size()) {
				throw new IllegalStateException("2 <= userList.size()");
			} else {
				return userList.get(0);
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	public UpdateUser getUpdateUser(Connection connection, String id) {

		PreparedStatement ps = null;
		try {
			String sql = "SELECT * FROM users WHERE id = ? ;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs = ps.executeQuery();
			List<UpdateUser> userList = toUpdateUserList(rs);
			
			return userList.get(0);
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<UpdateUser> toUpdateUserList(ResultSet rs) throws SQLException {
		List<UpdateUser> ret = new ArrayList<UpdateUser>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String account = rs.getString("account");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");

				UpdateUser user = new UpdateUser();

				user.setId(id);
				user.setLoginId(loginId);
				user.setAccount(account);
				user.setBranchId(branchId);
				user.setPositionId(positionId);

				ret.add(user);
			}
			return ret;
		} finally {
			close(rs);
		}
	}
	
	public String insert(Connection connection, InsertUser insertUser) {
		PreparedStatement ps = null;
		String message = new String();
		try {
			
			StringBuilder SearchSql = new StringBuilder();
			SearchSql.append("SELECT * FROM users WHERE login_id = ? ;");
			ps = connection.prepareStatement(SearchSql.toString());
			ps.setString(1,  insertUser.getLoginId());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				message = "そのログインIDは既に登録されています";
			}else {
				StringBuilder sql = new StringBuilder();
				
				sql.append("insert into users (login_id,password, account, branch_id, position_id, status) ");
			
				sql.append("values (");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("? ,");
				sql.append("2");
				sql.append(")");
				
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
			close(ps);
		}
		return message;
	}

	

	private List<User> toUserList(ResultSet rs) throws SQLException {
		List<User> ret = new ArrayList<User>();
		try {
			while (rs.next()) {
				int id = rs.getInt("id");
				String loginId = rs.getString("login_id");
				String password = rs.getString("password");
				String account = rs.getString("account");
				int branchId = rs.getInt("branch_id");
				int positionId = rs.getInt("position_id");
				String status = rs.getString("status");

				User user = new User();

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
	
	
	public String update(Connection connection, UpdateUser user, int passwordCheck) {
		String message = new String() ;
		PreparedStatement ps = null;
		try {
			StringBuilder SearchSql = new StringBuilder();
			SearchSql.append("SELECT * FROM users WHERE login_id = ? ");
			SearchSql.append(" AND id != ? ;");
			ps = connection.prepareStatement(SearchSql.toString());
			ps.setString(1,  user.getLoginId());
			ps.setInt(2,  user.getId());

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				message = "そのログインIDは既に登録されています";
			}else {
				//編集画面
				StringBuilder sql = new StringBuilder();
				sql.append("UPDATE users SET ");
				sql.append("login_id = ?, ");
				if (passwordCheck == 0) {
					sql.append("password = ?, ");
				}
				sql.append("account = ?, ");
				sql.append("branch_id = ?, ");
				sql.append("position_id = ? ");

				sql.append(" WHERE ");
				sql.append(" id = ? ");
			
				ps.setString(1,  user.getLoginId());
				if (passwordCheck == 0) {
					ps.setString(2,  user.getPassword());
					ps.setString(3,  user.getAccount());
					ps.setInt(4,  user.getBranchId());
					ps.setInt(5,  user.getPositionId());
					ps.setInt(6,  user.getId());
				} else {
					ps.setString(2,  user.getAccount());
					ps.setInt(3,  user.getBranchId());
					ps.setInt(4,  user.getPositionId());
					ps.setInt(5,  user.getId());
				}
			
				int count = ps.executeUpdate();
				if (count == 0) {
					throw new NoRowsUpdatedRuntimeException();
				}
			}
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
		return message;
	}
	
	public void status(Connection connection, User user) {

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
}
