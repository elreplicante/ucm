package program;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import util.JdbcUtils;

public class Main {

	public static void main(String[] args) throws IOException {

		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "";
		String user = "";
		String pass = "";
		if (args.length != 3) {
			throw new IOException();
		} else {
			url = args[2];
			user = args[0];
			pass = args[1];
			
		}

		try {
			Connection conn = DriverManager.getConnection(url, user, pass);
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		} 

	}

}
