package program;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

import dao.DataAccessObject;

import util.JdbcUtils;

public class Main {

	public static void main(String[] args) throws IOException {
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "";
		String user = "";
		String pass = "";
		if (args.length != 3) {
			throw new IOException("Invalid arguments");
		}
		url = args[2];
		user = args[0];
		pass = args[1];

		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, pass);
			System.out.println("CONEXIÓN REALIZADA CON ÉXITO\n");
			DataAccessObject dao = new DataAccessObject(conn);
			int option = -1;
			while (option != 0) {
				option = getUserOption();
				executeOption(dao, option);			
			}
			
		} catch (SQLException e) {
			JdbcUtils.printSQLException(e);
		} finally {
			try {
				conn.close();
			} catch (SQLException e) {
				JdbcUtils.printSQLException(e);
			}
		}
	}

	private static void executeOption(DataAccessObject dao, int option) {
		switch (option) {
		case 0:
			break;
			
		case 1:
			dao.executeSqlScript("createTables.sql");
			break;
			
		case 2:
			dao.executeSqlScript("dropTables.sql");
			break;
			
		case 3:
			dao.executeSqlScript("insertData.sql");
			break;

		default:
			System.out.println("Escoge una opción del menú.");
			break;
		}
	}

	private static int getUserOption() {
		Scanner scan = new Scanner(System.in);
		showMenu();
		return scan.nextInt();		
	}

	private static void showMenu() {
		System.out.println("MENU:");
		System.out.println("0.- Salir de la aplicación");
		System.out.println("1.- Crear tablas.");
		System.out.println("2.- Eliminar tablas.");
		System.out.println("3.- Insertar datos.");	
	}

}
