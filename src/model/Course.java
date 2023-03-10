/**
 * Course class
 */
package model;

public class Course {
	
	private String courseID;
	private String courseName;
	private String courseSemester;
	private Professors professor;
	
	public Course() {
		
	}
	
	/**
	 * @return the courseID
	 */
	public String getCourseID() {
		return courseID;
	}
	
	/**
	 * @param courseID the courseID to set
	 * @return 
	 */
	public void setCourseID(String courseID) {
		this.courseID = courseID;
	}
	
	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}
	
	/**
	 * @param courseName the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	
	/**
	 * @return the courseSemester
	 */
	public String getCourseSemester() {
		return courseSemester;
	}
	
	/**
	 * @param courseSemester the courseSemester to set
	 */
	public void setCourseSemester(String courseSemester) {
		this.courseSemester = courseSemester;
	}
	
	/**
	 * @return the professor
	 */
	public Professors getProfessor() {
		return professor;
	}
	
	/**
	 * @param string the professor to set
	 */
	public void setProfessor(Professors professor) {
		this.professor = professor;
	}	
//	
}