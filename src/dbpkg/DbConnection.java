package dbpkg;

import java.sql.*;

public class DbConnection {

	//DB connection strings
	static final String DB_URL_CORBZ = "jdbc:oracle:thin:@(DESCRIPTION =\r\n"
			+ "    (ADDRESS = (PROTOCOL = TCP)(HOST = usoh4sldbs7.affinionds.com)(PORT = 1521))\r\n"
			+ "    (CONNECT_DATA =\r\n" + "      (SERVER = DEDICATED)\r\n"
			+ "      (SERVICE_NAME = corbz.staging.cuc.com)\r\n" + "    )\r\n" + "  )";

	static final String DB_URL_PRDCT = "jdbc:oracle:thin:@(DESCRIPTION =\r\n"
			+ "    (ADDRESS = (PROTOCOL = TCP)(HOST = usoh4sldbs8.affinionds.com)(PORT = 1521))\r\n"
			+ "    (CONNECT_DATA =\r\n" + "      (SERVER = DEDICATED)\r\n"
			+ "      (SERVICE_NAME = prdct.staging.cuc.com)\r\n" + "    )\r\n" + "  )";

	static final String DB_URL_USAGE = "jdbc:oracle:thin:@(DESCRIPTION =\r\n"
			+ "    (ADDRESS = (PROTOCOL = TCP)(HOST = usoh4sldbs13.affinionds.com)(PORT = 1521))\r\n"
			+ "    (CONNECT_DATA =\r\n" + "      (SERVER = DEDICATED)\r\n"
			+ "      (SERVICE_NAME = usage.staging.cuc.com)\r\n" + "    )\r\n" + "  )";

	static final String DB_URL_WEBSYS = "jdbc:oracle:thin:@(DESCRIPTION =\r\n"
			+ "    (ADDRESS = (PROTOCOL = TCP)(HOST = usoh4sldbs4.affinionds.com)(PORT = 1521))\r\n"
			+ "    (CONNECT_DATA =\r\n" + "      (SERVER = DEDICATED)\r\n"
			+ "      (SERVICE_NAME = websys.staging.cuc.com)\r\n" + "    )\r\n" + "  )";

	// Database credentials
	static final String USER = "QA_TESTING";
	static final String PASS = "certitude";

	public Connection dbConnect(String DB) throws Exception {

		Connection conn = null;
		String DB_URL = null;
		
		switch(DB.toLowerCase()) {
		case "corbz":
			DB_URL = DB_URL_CORBZ;
			break;
		case "prdct":
			DB_URL = DB_URL_PRDCT;
			break;
		case "usage":
			DB_URL = DB_URL_USAGE;
			break;
		case "websys":
			DB_URL = DB_URL_WEBSYS;
			break;
		default:
			throw new Exception("DB URL selection error!");
		}

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

		} catch (SQLException se) {
			throw new SQLException("SQL Connecting error!!!", se);
		} catch (NullPointerException npe) {
			throw npe;
		} catch (Exception e) {
			throw e;
		}

		return conn;
	}

	public ResultSet runSelectSql(Connection conn, String sql) throws Exception {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Execute a query
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			if (!rs.next()) {
				//System.out.println("Zero rows returned.");
			}
		} catch (SQLException se) {
			throw new SQLException("Error running statement: \n"+sql, se);
		} catch (Exception e) {
			throw new Exception("Error running statement!", e);
		}
		return rs;

	}

	public boolean runUpdateSql(Connection conn, String sql) throws Exception {

		Statement stmt = null;
		int rs = 0;
		try {
			// Execute a query
			stmt = conn.createStatement();
			conn.setAutoCommit(false);
			rs = stmt.executeUpdate(sql);
			if (rs > 0) {
				conn.commit();
				conn.setAutoCommit(true);
				return true;
			} else {
				conn.setAutoCommit(true);
				return false;
			}
		} catch (SQLException se) {
			throw new SQLException("Error running statement: \n"+sql, se);
		} catch (Exception e) {
			throw new Exception("Error running statement!", e);
		}

	}

	public void closeConn(Connection conn) throws Exception {

		try {
			// Clean-up environment
			conn.close();
		} catch (SQLException se) {
			throw new SQLException("Error closing connection", se);
		} catch (Exception e) {
			throw new Exception("Error closing connection", e);
		} finally {
			// finally block used to close resources
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				throw new SQLException("Error closing connection", se);
			}
		}
	}
}
