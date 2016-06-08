package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import beans.Posting;
import beans.UserMessage;
import exception.SQLRuntimeException;

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
			sql.append("SELECT * FROM postings  INNER JOIN users ON user_id = users.id ORDER BY record_date DESC");
			
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
	
	public List<UserMessage> getPostingSearch(Connection connection,Posting posting,int count) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM postings  INNER JOIN users ON user_id = users.id ");
			
			sql.append(" AND ? >= record_date ");  //
			sql.append(" AND ? <= record_date "); 
			
			if (count != 0 || posting.getSurchCategory() == "0") {
				sql.append(" AND ? = category ");          //カテゴリーに
			}
			
			sql.append(" ORDER BY record_date DESC ");
			ps = connection.prepareStatement(sql.toString());
		
			ps.setString(1,  posting.getFromDate());
			ps.setString(2,  posting.getToDate());
			
			if (count != 0 || posting.getSurchCategory() == "0") {
				ps.setString(3,  posting.getSurchCategory());
			}
			
			ResultSet rs = ps.executeQuery();
			List<UserMessage> ret = toUserPostingList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
		finally {
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
 				Timestamp date = rs.getTimestamp("record_date");
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

 		try {
 			while (rs.next()) {
 				UserMessage posting = new UserMessage();

 				int id = rs.getInt("id");
 				String body = rs.getString("body");
 				Timestamp date = rs.getTimestamp("record_date");
 				int branchId = rs.getInt("branch_id");
 				String subject = rs.getString("subject");
 				String category = rs.getString("category");
 				String account = rs.getString("account");

 				
 				posting.setId(id);
 				posting.setBody(body);
 				posting.setDate(date);
 				posting.setBranchId(branchId);
 				posting.setSubject(subject);
 				posting.setCategory(category);
 				posting.setAccount(account);
 				
 				ret.add(posting);

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
			sql.append("SELECT * FROM postings  INNER JOIN users ON user_id = users.id ORDER BY record_date DESC");
			
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
 				String year = new SimpleDateFormat("yyyy").format(rs.getTimestamp("record_date"));
 				String month = new SimpleDateFormat("MM").format(rs.getTimestamp("record_date"));
 				String day = new SimpleDateFormat("dd").format(rs.getTimestamp("record_date"));

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

 			return ret;
 		} finally {
 			close(rs);
 		}
	}
	

	public List<String> getCategory(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT category FROM postings;");
			
			ps = connection.prepareStatement(sql.toString());
			
			ResultSet rs = ps.executeQuery();
			List<String> ret = toCategoryList(rs);
			
			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<String> toCategoryList(ResultSet rs) throws SQLException {
		List<String> ret = new ArrayList<String>();

 		while (rs.next()) {
 			String category = rs.getString("category");
 			if (ret.contains(category) != true){
 				ret.add(category);
 			}
 		}
 		return ret;
	}
}
