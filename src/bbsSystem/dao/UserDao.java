package bbsSystem.dao;

import static bbsSystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bbsSystem.beans.InsertUser;
import bbsSystem.beans.UpdateUser;
import bbsSystem.beans.User;
import bbsSystem.exception.NoRowsUpdatedRuntimeException;
import bbsSystem.exception.SQLRuntimeException;

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
	public void insert(Connection connection, InsertUser insertUser) {
		PreparedStatement ps = null;
		try {
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

			ps = connection.prepareStatement(sql.toString());

			ps.setString(1,  insertUser.getLoginId());
			ps.setString(2,  insertUser.getPassword());
			ps.setString(3,  insertUser.getAccount());
			ps.setInt(4,  insertUser.getBranchId());
			ps.setInt(5,  insertUser.getPositionId());
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
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
	public void update(Connection connection, UpdateUser user, int passwordCheck, String OldId) {
		PreparedStatement ps = null;
		try {
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
			sql.append(" login_id = ? ");
			if (passwordCheck == 0) {
				//sql.append(" AND ");
				//sql.append(" password = ?; ");
			}
			
			ps = connection.prepareStatement(sql.toString());

			ps.setString(1,  user.getLoginId());
			if (passwordCheck == 0) {
				ps.setString(2,  user.getPassword());
				ps.setString(3,  user.getAccount());
				ps.setInt(4,  user.getBranchId());
				ps.setInt(5,  user.getPositionId());
				ps.setString(6,  OldId);
				//ps.setString(7,  user.getPassword());
			} else {
				ps.setString(2,  user.getAccount());
				ps.setInt(3,  user.getBranchId());
				ps.setInt(4,  user.getPositionId());
				ps.setString(5,  user.getLoginId());
			}
			
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
	
	public void status(Connection connection, User user) {

		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE users SET ");
			sql.append("status = ?");

			sql.append(" WHERE ");
			sql.append(" login_id = ? ;");
			ps = connection.prepareStatement(sql.toString());
			ps.setString(1,  user.getStatus());
			ps.setString(2,  user.getLoginId());
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
	
	public void delete(Connection connection,User user) {

		PreparedStatement ps = null;
		try {
			String sql = "DELETE FROM users WHERE login_id = ? ;";
			ps = connection.prepareStatement(sql);
			ps.setString(1, user.getLoginId());
			
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
