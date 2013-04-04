package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import util.JdbcUtils;
import util.LectorSentenciasSQL;

public class DataAccessObject {

	private Connection connection;

	public DataAccessObject(Connection connection) {
		this.connection = connection;
	}

	public void executeSqlScript(String fileName) throws FileNotFoundException {
		
		FileInputStream sqlScript = new FileInputStream(fileName);
		LectorSentenciasSQL sqlReader = new LectorSentenciasSQL(sqlScript);

		Statement stmt = null;
		ResultSet rs = null;

		String query = "";

		
		
	}
}
