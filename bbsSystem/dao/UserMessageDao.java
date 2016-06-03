package bbsSystem.dao;

import static bbsSystem.utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
<<<<<<< HEAD
import java.text.SimpleDateFormat;
=======
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
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
<<<<<<< HEAD
			sql.append(" AND ? >= registration_date ");  //
			sql.append(" AND ? <= registration_date "); 
			if (StringUtils.isEmpty(posting.getSurchCategory()) != true) {
				sql.append(" AND ? = category ");          //カテゴリーに
			}
			
			sql.append(" ORDER BY registration_date DESC ");
		
			ps = connection.prepareStatement(sql.toString());
			
=======
			sql.append(" AND ? <= registration_date ");  //
			sql.append(" AND ? >= registration_date "); 
			if (StringUtils.isEmpty(posting.getSurchCategory()) != true) {
				sql.append(" AND ? = category ");          //カテゴリーに
			}
			sql.append(" ORDER BY registration_date DESC ");
			ps = connection.prepareStatement(sql.toString());
			
			//System.out.println(ps.setString();
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
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
<<<<<<< HEAD
		
 		try {
 			while (rs.next()) {
 				UserMessage posting = new UserMessage();
=======
		Timestamp lastDate = null;
		UserMessage posting = new UserMessage();
 		try {
 			int count = 0;
 			while (rs.next()) {
 				
 				
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
 				int id = rs.getInt("id");
 				String body = rs.getString("body");
 				Timestamp date = rs.getTimestamp("registration_date");
 				int branchId = rs.getInt("branch_id");
 				String subject = rs.getString("subject");
 				String category = rs.getString("category");
 				String account = rs.getString("account");
<<<<<<< HEAD

=======
 				if (count == 0) {
 					System.out.println(date);
 					posting.setFirstDate(date);
 				}
 				lastDate = date;
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
 				
 				posting.setId(id);
 				posting.setBody(body);
 				posting.setDate(date);
 				posting.setBranchId(branchId);
 				posting.setSubject(subject);
 				posting.setCategory(category);
 				posting.setAccount(account);
 				
 				ret.add(posting);
<<<<<<< HEAD
 			}
 			return ret;
 		} finally {
 			close(rs);
 		}
	}
	
	
	public List<String> getDate(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM postings  INNER JOIN users ON user_id = users.id ORDER BY registration_date DESC");
			
			ps = connection.prepareStatement(sql.toString());
			
			ResultSet rs = ps.executeQuery();
			List<String> ret = toDateList(rs);
		
			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	
	private List<String> toDateList(ResultSet rs) throws SQLException {
		List<String> ret = new ArrayList<String>();
		String lastYear = null;
		String lastMonth = null;
		String lastDay = null;
 		try {
 			int count = 0;
 			while (rs.next()) {
 				String year = new SimpleDateFormat("yyyy").format(rs.getTimestamp("registration_date"));
 				String month = new SimpleDateFormat("MM").format(rs.getTimestamp("registration_date"));
 				String day = new SimpleDateFormat("dd").format(rs.getTimestamp("registration_date"));

 				if (count == 0) {
 					
 					ret.add(year);
 					ret.add(month);
 					ret.add(day);
 				}
 				lastYear = year;
 				lastMonth = month;
 				lastDay = day;
 				
 				count ++;
 			}
 			ret.add(lastYear);
 			ret.add(lastMonth);
 			ret.add(lastDay);
=======
 				count ++;
 			}
 			posting.setLastDate(lastDate);
 			ret.add(posting);
 			System.out.println(lastDate);
>>>>>>> 8d617aec4efbb8f139cf04341be0a1ac52545286
 			return ret;
 		} finally {
 			close(rs);
 		}
	}
}
