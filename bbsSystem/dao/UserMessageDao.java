package bbsSystem.dao;

import static bbsSystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import bbsSystem.beans.Posting;
import bbsSystem.beans.UserMessage;
import bbsSystem.exception.SQLRuntimeException;

public class UserMessageDao {
	public List<UserMessage> getUserMessages(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM comments INNER JOIN users ON user_id = users.id");
			
			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserMessageList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}

	public List<UserMessage> getPosting(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM postings  INNER JOIN users ON user_id = users.id ORDER BY registration_date DESC");
			
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserPostingList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	public List<UserMessage> getPostingSearch(Connection connection,Posting posting) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM postings  INNER JOIN users ON user_id = users.id ");
			sql.append(" AND ? <= registration_date ");  //
			sql.append(" AND ? >= registration_date "); 
			if (StringUtils.isEmpty(posting.getSurchCategory()) != true) {
				sql.append(" AND ? = category ");          //カテゴリーに
			}
			sql.append(" ORDER BY registration_date DESC ");
			ps = connection.prepareStatement(sql.toString());
			
			//System.out.println(ps.setString();
			ps.setString(1,  posting.getFromDate());
			ps.setString(2,  posting.getToDate());
			if (StringUtils.isEmpty(posting.getSurchCategory()) != true) {
				ps.setString(3,  posting.getSurchCategory());
			}

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserPostingList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	private List<UserMessage> toUserMessageList(ResultSet rs) throws SQLException {
		List<UserMessage> ret = new ArrayList<UserMessage>();
 		try {
 			while (rs.next()) {
 				UserMessage posting = new UserMessage();
 				
 				int id = rs.getInt("id");
 				String body = rs.getString("body");
 				Timestamp date = rs.getTimestamp("registration_date");
 				int userId = rs.getInt("user_id");
 				int postingId = rs.getInt("posting_id");
 				int branchId = rs.getInt("branch_id");
 				String account = rs.getString("account");
 				
 				
 				posting.setPostingId(postingId);
 				posting.setBranchId(branchId);
 				posting.setId(id);
 				posting.setBody(body);
 				posting.setDate(date);
 				posting.setUserId(userId);
 				posting.setAccount(account);
 				ret.add(posting);
 			}
 			return ret;
 		} finally {
 			close(rs);
 		}
	}
	
	private List<UserMessage> toUserPostingList(ResultSet rs) throws SQLException {
		List<UserMessage> ret = new ArrayList<UserMessage>();
		Timestamp lastDate = null;
		
 		try {
 			int count = 0;
 			while (rs.next()) {
 				UserMessage posting = new UserMessage();
 				int id = rs.getInt("id");
 				String body = rs.getString("body");
 				Timestamp date = rs.getTimestamp("registration_date");
 				int branchId = rs.getInt("branch_id");
 				String subject = rs.getString("subject");
 				String category = rs.getString("category");
 				String account = rs.getString("account");
 				System.out.println(body);
 				if (count == 0) {
 					System.out.println(date);
 					posting.setFirstDate(date);
 				}
 				lastDate = date;
 				
 				posting.setId(id);
 				posting.setBody(body);
 				posting.setDate(date);
 				posting.setBranchId(branchId);
 				posting.setSubject(subject);
 				posting.setCategory(category);
 				posting.setAccount(account);
 				
 				ret.add(posting);
 				count ++;
 			}
 			System.out.println(lastDate);
 			return ret;
 		} finally {
 			close(rs);
 		}
	}
	
/*	private List<UserMessage> getPostingSearch(Connection connection, Posting posting) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM postings  INNER JOIN users ON user_id = users.id ");
			sql.append(" AND ? <= registration_date ");  //
			sql.append(" AND ? >= registration_date "); 
			if (StringUtils.isEmpty(posting.getSurchCategory()) != true) {
				sql.append(" AND ? = category ");          //カテゴリーに
			}
			sql.append(" ORDER BY registration_date DESC ");
			ps = connection.prepareStatement(sql.toString());
			
			//System.out.println(ps.setString();
			ps.setString(1,  posting.getFromDate());
			ps.setString(2,  posting.getToDate());
			if (StringUtils.isEmpty(posting.getSurchCategory()) != true) {
				ps.setString(3,  posting.getSurchCategory());
			}

			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserPostingList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
		
	}*/
}
