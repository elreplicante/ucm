package transfer;

import java.sql.Date;

public class EmployeeTransfer {

	private String name;
	private String dni;
	private double salary;
	private Date birthDate;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double d) {
		this.salary = d;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public EmployeeTransfer(String dni) {
		super();
		this.dni = dni;
	}

	public EmployeeTransfer() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", dni=" + dni + ", salary="
				+ salary + ", birthDate=" + birthDate + "]";
	}
	
	
	
	
	
}
