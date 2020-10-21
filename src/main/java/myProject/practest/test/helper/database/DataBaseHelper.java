package myProject.practest.test.helper.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import myProject.practest.test.helper.logger.LoggerHelper;

public class DataBaseHelper {

	private static Logger log = LoggerHelper.getLogger(DataBaseHelper.class);
	private static String url = "jdbc:sqlserver://10.25.31.97;databaseName=CS_WEB_V1_QA;integratedSecurity=false";
	private static String userName = "sqluserweb";
	private static String password = "sqlU$erWeb1";
	private static String driverName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	private static Connection connection;
	private static String dbQuery = "SELECT Value,Name FROM APPCONFIG where settingstype = 'WILDCARD' order by Name";
	//private static DataBaseHelper instance = null;
	private static String name;
	private static String value;

	public DataBaseHelper() {
		connection = getSingleInstanceConnection();
	}

	/*
	 * public static DataBaseHelper getInstance() { if (instance == null) { instance
	 * = new DataBaseHelper(); } return instance; }
	 */
	private static Connection getSingleInstanceConnection() {

		try {
			Class.forName(driverName);

			try {
				connection = DriverManager.getConnection(url, userName, password);
				if (connection != null) {
					log.info("Connection to database..");
				}
			}

			catch (SQLException e) {
				log.error("Failed to create db connection" + e);
			}
		} catch (ClassNotFoundException e) {
			log.error("Driver not found" + e);
		}
		return connection;

	}

	public Connection getConnection() {
		return connection;
	}

	public static ResultSet getResultSet(String dbExeQuery) throws SQLException {
		try {
			// instance = DataBaseHelper.getInstance();
			connection = getSingleInstanceConnection();
			log.info("Executing query :" + dbExeQuery);
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(dbExeQuery);
			return rs;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	/*
	 * public static void main(String arg[]) throws SQLException { ResultSet rs =
	 * getResultSet(dbQuery); while(rs.next()) { name = rs.getString("Name"); value
	 * = rs.getString("Value"); System.out.println(name+"------"+value); } }
	 */
}
