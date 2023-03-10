package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import model.*;
import utilities.DbUtil;



public class ProfessorDao {
	
	private Connection connection;
	
	public ProfessorDao() {
        connection = DbUtil.getConnection();
    }
	
	public Professors GetProfessorDetails (String username) {
		Professors professor=new Professors();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT * from PROFESSORS where USERS_username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				professor.setAFM(rs.getInt("AFM"));
				professor.setUsername(rs.getString("USERS_username"));
				professor.setName(rs.getString("Name"));
				professor.setSurname(rs.getString("Surname"));
				professor.setDepartment(rs.getString("Department"));
				professor.setRole("student");
            }
		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return professor;
	}
	
	public String getAFM(String username){
		String afm="";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT AFM from PROFESSORS where USERS_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	afm=rs.getString("AFM");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return afm;
	}
	
	public ArrayList<Course> viewGradedCourses (String afm) {
		ArrayList<Course> list= new ArrayList<Course>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT CourseTitle FROM COURSE inner JOIN COURSE_HAS_STUDENTS Where (PROFESSORS_AFM=? AND COURSE_idCOURSE=idCOURSE)");
			preparedStatement.setString(1, afm);
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

	public ArrayList<Grades> showgrades(String afm, String courses) {
		ArrayList<Grades> list= new ArrayList<Grades>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT STUDENTS_RegistrationNumber,GradeCourseStudent FROM COURSE inner JOIN COURSE_HAS_STUDENTS Where (PROFESSORS_AFM=? AND CourseTitle=? AND COURSE_idCOURSE=idCOURSE and GradeCourseStudent is not null )");
			preparedStatement.setString(1, afm);
			preparedStatement.setString(2, courses);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Grades grade = new Grades();
				Course course = new Course();
				Students student= new Students(); 
				course.setCourseName(courses);
				grade.setCourse(course);
				student.setRegistrationNumber(rs.getInt("STUDENTS_RegistrationNumber"));
				grade.setStudent(student);
				grade.setGrade(rs.getString("GradeCourseStudent"));
				list.add(grade);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 		
		return list;
	}

	
	public ArrayList<Students> insertgrades(String afm, String courses) {
		ArrayList<Students> list= new ArrayList<Students>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT STUDENTS_RegistrationNumber,GradeCourseStudent FROM COURSE inner JOIN COURSE_HAS_STUDENTS Where (PROFESSORS_AFM=? AND CourseTitle=? AND COURSE_idCOURSE=idCOURSE and GradeCourseStudent is null )");
			preparedStatement.setString(1, afm);
			preparedStatement.setString(2, courses);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Students student= new Students(); 
				student.setRegistrationNumber(rs.getInt("STUDENTS_RegistrationNumber"));
				list.add(student);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 		
		return list;
	}
	public void submit(String student, String grade,String courses) {
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE COURSE_HAS_STUDENTS SET GradeCourseStudent=? WHERE STUDENTS_RegistrationNumber=? And COURSE_idCOURSE=(Select idCOURSE From Course Where CourseTitle=?)");
			preparedStatement.setString(1, grade);
			preparedStatement.setString(2, student);
			preparedStatement.setString(3, courses);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
}