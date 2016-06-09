package dao;

import static utils.CloseableUtil.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import beans.Comments;
import beans.UserComments;
import exception.SQLRuntimeException;

public class CommentsDao {
	//コメントの投稿実行
	public static void insert(Connection connection, Comments comment) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append(" INSERT INTO comments (posting_id, body, record_date, user_id");
			sql.append(" ) VALUES ( ");
			sql.append(" ?, ");
			sql.append(" ?, ");
			sql.append(" CURRENT_TIMESTAMP, ");
			sql.append(" ? ");
			sql.append(" ) ;");
		
			ps = connection.prepareStatement(sql.toString());
			
			ps.setInt(1, comment.getPostingId());
			ps.setString(2, comment.getBody());
			ps.setInt(3, comment.getUserId());
			
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	public List<UserComments> getComments(Connection connection) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			//投稿を表示
			sql.append("SELECT * FROM UserComments ;");
			
			ps = connection.prepareStatement(sql.toString());

			ResultSet rs = ps.executeQuery();
			List<UserComments> ret = toUserCommentList(rs);

			return ret;
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}
	}
	
	private List<UserComments> toUserCommentList(ResultSet rs) throws SQLException {
		List<UserComments> ret = new ArrayList<UserComments>();
 		try {
 			while (rs.next()) {
 				UserComments comment = new UserComments();
 				
 				int id = rs.getInt("id");
 				String body = rs.getString("body");
 				int userId = rs.getInt("user_id");
 				int postingId = rs.getInt("posting_id");
 				int branchId = rs.getInt("branch_id");
 				String account = rs.getString("account");
 				
 				comment.setId(id);
 				comment.setPostingId(postingId);
 				comment.setBranchId(branchId);
 				comment.setBody(body);
 				comment.setUserId(userId);
 				comment.setAccount(account);
 				ret.add(comment);
 			}
 			return ret;
 		} finally {
 			close(rs);
 		}
	}
	
	//コメントの削除実行
	public static void delete(Connection connection, String commentId) {
		PreparedStatement ps = null;
		try {
			StringBuilder sql = new StringBuilder();
			sql.append("DELETE FROM comments WHERE id = ? ;");
			
			ps = connection.prepareStatement(sql.toString());	
			
			ps.setString(1,  commentId);
			ps.executeUpdate();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		} finally {
			close(ps);
		}	
	}
}
