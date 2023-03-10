package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.*;
import utilities.DbUtil;



public class SecretaryDao {
	private static Connection connection;
	
	public SecretaryDao() {
        connection = DbUtil.getConnection();
    }
	
	public int getphonenumber(String username) {
		int phonenumber=0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT Phonenumber from SECRETARIES where USERS_username=?");
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) 
            { 
            	phonenumber=rs.getInt("Phonenumber");
        	} 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return phonenumber;
	}
	
	
	public ArrayList<Course> viewCourses() {
		ArrayList<Course> list= new ArrayList<Course>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT CourseTitle,CourseSemester,PROFESSORS_AFM from COURSE ");
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				Course course = new Course();
				Professors professor = new Professors();
            	course.setCourseName(rs.getString("CourseTitle"));
            	course.setCourseSemester(rs.getString("CourseSemester"));
            	professor.setAFM(rs.getInt("PROFESSORS_AFM"));
            	course.setProfessor(professor);
            	list.add(course);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return list;
	}
	public ArrayList<Course> getcourses() {
		ArrayList<Course> list= new ArrayList<Course>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT CourseTitle from COURSE ");
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
	public ArrayList<Professors> getprof() {
		ArrayList<Professors> list= new ArrayList<Professors>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT AFM from PROFESSORS ");
			ResultSet rs = preparedStatement.executeQuery(); 
			while (rs.next()) {
				Professors prof = new Professors();
            	prof.setAFM(rs.getInt("AFM"));
            	list.add(prof);
            }

		}
		catch(SQLException e) {
            e.printStackTrace();
        } 
		return list;
	}
	public static void Submit(String course, int afm) {
		PreparedStatement preparedStatement1;
		try {
			preparedStatement1 = connection.prepareStatement("UPDATE COURSE SET PROFESSORS_AFM=? WHERE CourseTitle=?");
			preparedStatement1.setInt(1, afm);
			preparedStatement1.setString(2, course);
			preparedStatement1.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	

}
