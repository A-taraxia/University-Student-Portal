package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;
import utilities.DbUtil;



public class StudentDao {

	private Connection connection;
		
	public StudentDao() {
        connection = DbUtil.getConnection();
    }
	
	public String getStudentRegistrationNumber(String username){
		String registrationNumber="";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT RegistrationNumber from STUDENTS where USERS_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	registrationNumber=rs.getString("registrationNumber");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registrationNumber;
	}
	
	
	public ArrayList<Grades> viewGrades(String rn) {
		ArrayList<Grades> list= new ArrayList<Grades>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT GradeCourseStudent,CourseTitle FROM COURSE inner JOIN COURSE_HAS_STUDENTS Where (STUDENTS_RegistrationNumber=? AND COURSE_idCOURSE=idCOURSE)");
			preparedStatement.setString(1, rn);
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				Grades grade = new Grades();
				Course course = new Course();
				course.setCourseName(rs.getString("CourseTitle"));
				grade.setCourse(course);
				grade.setGrade(rs.getString("GradeCourseStudent"));
            	list.add(grade);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 		
		
		return list;
	}
	
	
	public Students GetStudentDetails (String username) {
		Students student=new Students();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from STUDENTS where USERS_username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				student.setRegistrationNumber(rs.getInt("RegistrationNumber"));
            	student.setUsername(rs.getString("USERS_username"));
            	student.setName(rs.getString("Name"));
            	student.setSurname(rs.getString("Surname"));
            	student.setDepartment(rs.getString("Department"));
            	student.setRole("student");
            }
		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return student;
	}

	public ArrayList<Course> showsemester(String rn) {
		ArrayList<Course> list= new ArrayList<Course>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT CourseSemester FROM COURSE inner join COURSE_HAS_STUDENTS WHERE (STUDENTS_RegistrationNumber=? AND COURSE_idCOURSE=idCOURSE)");
			preparedStatement.setString(1, rn);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseSemester(rs.getString("CourseSemester"));
            	list.add(course);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 		
		return list;
	}

	public ArrayList<Grades> showgradessemester(String rn,String sem) {
		ArrayList<Grades> list= new ArrayList<Grades>();

		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT GradeCourseStudent,CourseTitle FROM COURSE inner JOIN COURSE_HAS_STUDENTS Where (STUDENTS_RegistrationNumber=? AND CourseSemester=? AND COURSE_idCOURSE=idCOURSE)");
			preparedStatement.setString(1, rn);
			preparedStatement.setString(2, sem);
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				Grades grade = new Grades();
				Course course = new Course();
				course.setCourseName(rs.getString("CourseTitle"));
				grade.setCourse(course);
				grade.setGrade(rs.getString("GradeCourseStudent"));
				list.add(grade);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return list;
	}
	public ArrayList<Course> viewCourses(String rn) {
		ArrayList<Course> list= new ArrayList<Course>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT CourseTitle FROM COURSE inner join COURSE_HAS_STUDENTS WHERE STUDENTS_RegistrationNumber=? AND COURSE_idCOURSE=idCOURSE");
			preparedStatement.setString(1, rn);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setCourseName(rs.getString("CourseTitle"));
            	list.add(course);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 		
		return list;
	}
	public ArrayList<Grades> showonegrade(String rn,String allcourses) {
		ArrayList<Grades> list= new ArrayList<Grades>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT GradeCourseStudent FROM COURSE inner JOIN COURSE_HAS_STUDENTS Where (STUDENTS_RegistrationNumber=? AND CourseTitle=? AND COURSE_idCOURSE=idCOURSE)");
			preparedStatement.setString(1, rn);
			preparedStatement.setString(2, allcourses);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Grades grade = new Grades();
				Course course = new Course();
				course.setCourseName(allcourses);
				grade.setCourse(course);
				grade.setGrade(rs.getString("GradeCourseStudent"));
				list.add(grade);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 		
		return list;
	}

	
	
}
