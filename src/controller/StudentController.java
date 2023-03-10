package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.*;

import dao.*;
import model.*;


@WebServlet("/student")
public class StudentController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
   private StudentDao studentDao;   
   private Students student;
   
    
    public StudentController() {
        super();
        studentDao = new StudentDao();
        
    }

	
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		HttpSession session=request.getSession(false);
		String username=(String) session.getAttribute("username");
		String role=(String) session.getAttribute("role");
		student= studentDao.GetStudentDetails(username);
		session.setAttribute("name", student.getName());
		String rn=studentDao.getStudentRegistrationNumber(username);		
		
		if(action.equalsIgnoreCase("viewStudentDetails")) {
			request.setAttribute("action","viewStudentDetails");	
			session.setAttribute("surname", student.getSurname());
			session.setAttribute("department", student.getDepartment());
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
			
		}else if (action.equalsIgnoreCase("viewStudentGrades")){
			request.setAttribute("action","viewStudentGrades");
			ArrayList<Grades>list=studentDao.viewGrades(rn);
			session.setAttribute("list",list);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);

			
		}else if (action.equalsIgnoreCase("viewSemester")){
			request.setAttribute("action","viewSemester");
			ArrayList<Course>list=studentDao.showsemester(rn);
			session.setAttribute("list",list);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
			
		}else if (action.equalsIgnoreCase("showgrades")){
			request.setAttribute("action","showgrades");
			String semester=request.getParameter("newsemester");
			ArrayList<Grades>list=studentDao.showgradessemester(rn,semester);
			session.setAttribute("list",list);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
			
		}else if (action.equalsIgnoreCase("viewCourses")){
			request.setAttribute("action","viewCourses");
			ArrayList<Course>list=studentDao.viewCourses(rn);
			session.setAttribute("list",list);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
			
		}else if (action.equalsIgnoreCase("showonegrade")){
			request.setAttribute("action","showonegrade");
			String allcourses=request.getParameter("allcourses");
			ArrayList<Grades>list=studentDao.showonegrade(rn,allcourses);
			session.setAttribute("list",list);
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/student.jsp");
			requestDispatcher.forward(request,response);
		}
	}



	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
	
	
	
}


