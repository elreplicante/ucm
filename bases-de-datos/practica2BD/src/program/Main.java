package program;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import transfer.EmployeeTransfer;
import dao.employee.EmployeeDAO;
import dao.employee.imp.EmployeeDAOImp;
import dao.factory.DAOFactory;
import exceptions.NotFoundException;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		EmployeeDAOImp employeeDAO = daoFactory.getEmployeeDAO();
		
		EmployeeTransfer employee = new EmployeeTransfer();
		employee.setDni("50734444D");
		employee.setName("Sergio Revilla");
		employee.setBirthDate(new Date(1978, 10, 02));
		employee.setSalary(40000.0);
		
		
		String driver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@192.168.56.101:1521/orcl";
		String user = "practica2";
		String pass = "practica2";
		
		try {
			if (driver != null) {
				Class.forName(driver);
			} 
			Connection conn = DriverManager.getConnection(url, user, pass);
		//	employeeDAO.create(conn, employee);
			EmployeeTransfer otherEmployee = employeeDAO.getObject(conn, "50734444D");
			otherEmployee.toString();
			employeeDAO.delete(conn, otherEmployee);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
			e.printStackTrace();
		} catch (NotFoundException e) {
			
			e.printStackTrace();
		}

	}

}
