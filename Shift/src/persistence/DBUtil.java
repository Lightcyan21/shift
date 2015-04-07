package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	
	private static final String MYSQL_DRIVER = "com.mysql.jdbc.Driver";
	private static final String MYSQL_URL = "jdbc:mysql://10.10.10.0/PrjGManage";
	private static final String MYSQL_USER = "PrjGManage";
	private static final String MYSQL_PASSWORD = "PrjGManage";
	
	/**
	 * Opens a database connection to mysql database.
	 * User has to make sure that the connection will be closed.
	 * @return database connection yeah!
	 */
	public static Connection getConnection(){
		Connection connection = null;

			try {
				Class.forName(MYSQL_DRIVER);
				connection = DriverManager.getConnection(MYSQL_URL, MYSQL_USER, MYSQL_PASSWORD);
			} catch (SQLException e) {
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		

		return connection;
	}
}

