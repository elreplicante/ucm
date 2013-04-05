package dao;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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

	public void executeSqlScript(String fileName) {
		FileInputStream sqlScript;
		LectorSentenciasSQL sqlReader = null;
		try {
			sqlScript = new FileInputStream(fileName);
			sqlReader = new LectorSentenciasSQL(sqlScript);
		} catch (FileNotFoundException e) {
			System.err.println("No se encontró el archivo de script");
		}

		String query = "";
		while (query != null) {
			try {
				query = sqlReader.siguienteInstruccion();
				if (query != null) {
					ejecutaConsulta(query);
				}
				
			} catch (IOException e) {
				e.printStackTrace();
			}

		}

	}

	public void ejecutaConsulta(String query) {
		Statement stmt = null;
		ResultSet rs = null;
		try {
			// Crea el objeto que representa la consulta SQL
			stmt = connection.createStatement();

			// Ejecuta la consulta SQL
			stmt.execute(query);

			if (stmt.getResultSet() != null) {
				// Obtiene el objeto que representa el resultado de la consulta
				// SQL
				rs = stmt.getResultSet();

				// Recorre y muestra el resultado de la consulta
				while (rs.next()) {
					System.out.println(rs.getString(1));
				}

			}
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		} finally {
			
			//Cierra el objeto que represneta el resultado de la consulta 
			try {
				if (rs != null) {
					rs.close();
				}
			} catch (SQLException e) {
				JdbcUtils.printSQLException(e);
			}
			
			 //Cierra el objeto que representa la consulta SQL
			try {
				if (stmt != null) {
					stmt.close();
				}
			} catch (SQLException e) {
				JdbcUtils.printSQLException(e);
			}
		}
	}
}
