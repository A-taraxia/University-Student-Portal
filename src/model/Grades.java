package model;

public class Grades {
	
	private String Grade;
	private Professors professor;
	private Students student;
	private Course course;
	
	public Grades() {
		
	}

	public String getGrade() {
		return Grade;
	}
	
	public void setGrade(String Grade) {
		this.Grade = Grade;
	}
	

	public Professors getProfessor() {
		return professor;
	}
	
	public void setProfessor(Professors professor) {
		this.professor = professor;
	}	
	
	public Students getStudent() {
		return student;
	}
	
	public void setStudent(Students student) {
		this.student = student;
	}	
	public Course getCourse() {
		return course;
	}
	public void setCourse(Course course) {
		this.course=course;
	}
	
}