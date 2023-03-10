package model;

public class Students extends Users{

	private int RegistrationNumber;
	private String department;
	
	public Students() {
		
	}
	
	public void setRegistrationNumber(int RegistrationNumber) {
		this.RegistrationNumber=RegistrationNumber;
	}
	
	public int getRegistrationNumber() {
		return this.RegistrationNumber;
	}
	
	
	public void setDepartment(String department) {
		this.department=department;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
}
