package bbsSystem.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import bbsSystem.exception.SQLRuntimeException;
public class DBUtil {
	
	private static final String URL = "jdbc:mysql://localhost:3306/bbs";			
	private static final String USER = "root";
	private static final String PASSWORD = "1qazxsw2";
	
	/**
	 * コネクションを取得します。
	 *
	 * @return
	 */
	public static Connection getConnection() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = DriverManager.getConnection(URL, USER,
					PASSWORD);
			connection.setAutoCommit(false);
	
			return connection;
		} catch (SQLException e) {
			throw new SQLRuntimeException(e);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			return null; 
		}
		
	}
	
	public static void commit(Connection connection) {
		try {
			connection.commit();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}
	
	public static void rollback(Connection connection) {
		try {
			connection.rollback();
		} catch(SQLException e) {
			throw new SQLRuntimeException(e);
		}
	}

}
