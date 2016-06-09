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

import beans.Postings;
import beans.UserPostings;
import exception.SQLRuntimeException;

public class PostingsDao {
	public void insert(Connection connection, Postings posting) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("INSERT INTO postings (subject, body, category, record_date, user_id)");
			sql.append(" VALUES (");
			sql.append(" ? ,");
			sql.append(" ? ,");
			sql.append(" ? ,");
			sql.append(" CURRENT_TIMESTAMP, ");
			sql.append(" ?");
			sql.append(" ) ;");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, posting.getSubject());
			ps.setString(2, posting.getBody());
			ps.setString(3, posting.getCategory());
			ps.setInt(4, posting.getUserId());

			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}		
	
	public List<String> getCategory(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT category FROM postings ;");
			
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
	
	public List<String> getDate(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM postings INNER JOIN users ON user_id = users.id ORDER BY record_date ASC ;");
			
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

	public List<UserPostings> getPosting(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM UserPostings ;");
			
			ps = connection.prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			List<UserPostings> ret = toUserPostingList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	public List<UserPostings> getPostingSearch(Connection connection, Postings posting, List<String> search) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM postings INNER JOIN users ON user_id = users.id ");
			sql.append(" AND ? <= record_date "); 
			sql.append(" AND ? >= record_date ");  //
			
			if (!search.get(2).isEmpty()) {
				sql.append(" AND ? = category ");          //カテゴリーに
			}
			
			sql.append(" ORDER BY record_date DESC ;");
			ps = connection.prepareStatement(sql.toString());
		
			ps.setString(1, search.get(0));
			ps.setString(2, search.get(1));
			if (!search.get(2).isEmpty()) {
				ps.setString(3, search.get(2));
			}
			ResultSet rs = ps.executeQuery();
			List<UserPostings> ret = toUserPostingList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
		finally {
			close(ps);
		}
	}

	private List<UserPostings> toUserPostingList(ResultSet rs) throws SQLException {
		List<UserPostings> ret = new ArrayList<UserPostings>();
 		try {
 			while (rs.next()) {
 				UserPostings posting = new UserPostings();

 				int postingId = rs.getInt("id");
 				String body = rs.getString("body");
 				Timestamp date = rs.getTimestamp("record_date");
 				int branchId = rs.getInt("branch_id");
 				String subject = rs.getString("subject");
 				String category = rs.getString("category");
 				String account = rs.getString("account");
 				
 				posting.setId(postingId);
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
	
	public static void delete(Connection connection, String postingId) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM postings WHERE id = ? ;");
			
			ps = connection.prepareStatement(sql.toString());
			
			ps.setString(1, postingId);

			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}	
}
