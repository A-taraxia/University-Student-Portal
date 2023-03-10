package model;

public class Secretaries extends Users{

	private int phonenumber=0;
	private String department=null;
	
	public void setphonenumber(int phonenumber) {
		this.phonenumber=phonenumber;
	}
	
	public int getphonenumber() {
		return this.phonenumber;
	}
	
	public void setDepartment(String department) {
		this.department=department;
	}
	
	public String getDepartment() {
		return this.department;
	}
	public String getCourse(String course) {
		return this.course;
	}

}
