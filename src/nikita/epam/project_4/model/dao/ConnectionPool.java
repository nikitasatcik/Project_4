package nikita.epam.project_4.model.dao;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class ConnectionPool {
private static final String DATABASE_NAME = "jdbc/travel_agency";
	private static DataSource dataSource;

	static {
		if (dataSource == null) {
			try {
				Context initContext = new InitialContext();
				Context envContext = (Context) initContext.lookup("java:/comp/env");
				dataSource = (DataSource) envContext.lookup(DATABASE_NAME);
			} catch (NamingException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}

	public static void closeConnection(Connection con){
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				System.out.println("cannot close connection");
			}
		}
	}
	private ConnectionPool() {}
}