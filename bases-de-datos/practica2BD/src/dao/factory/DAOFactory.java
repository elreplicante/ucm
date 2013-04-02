package dao.factory;

import dao.employee.EmployeeDAO;
import dao.employee.imp.EmployeeDAOImp;
import dao.factory.imp.DAOFactoryImp;

public abstract class DAOFactory {
	// List of DAO types supported by the factory
	private static DAOFactory instance = null;

	  // There will be a method for each DAO that can be 
	  // created. The concrete factories will have to 
	  // implement these methods.
	  public abstract EmployeeDAOImp getEmployeeDAO();
	  

	  public static DAOFactory getDAOFactory() {
	  
		  if (instance == null) {
				instance = new DAOFactoryImp();
			}
			return instance;
	  }

}
