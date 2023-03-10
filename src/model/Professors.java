package model;

public class Professors extends Users{

	private int AFM=0;
	private String department=null;
	
	public void setAFM(int AFM) {
		this.AFM=AFM;
	}
	
	public int getAFM() {
		return this.AFM;
	}
	
	public void setDepartment(String department) {
		this.department=department;
	}
	
	public String getDepartment() {
		return this.department;
	}

}
