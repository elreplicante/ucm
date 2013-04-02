package dao.factory.imp;

import dao.employee.EmployeeDAO;
import dao.employee.imp.EmployeeDAOImp;
import dao.factory.DAOFactory;

public class DAOFactoryImp extends DAOFactory {

	@Override
	public EmployeeDAOImp getEmployeeDAO() {
		
		return new EmployeeDAOImp();
	}

}
