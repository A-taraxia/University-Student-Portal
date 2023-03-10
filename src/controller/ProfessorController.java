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


@WebServlet("/professor")
public class ProfessorController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	   private ProfessorDao professorDao;   
	   private Professors professor;
	   
	    
	    public ProfessorController() {
	        super();
	        professorDao = new ProfessorDao();
	        
	    }

		
	    
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action = request.getParameter("action");
			HttpSession session=request.getSession(false);
			String username=(String) session.getAttribute("username");
			professor= professorDao.GetProfessorDetails(username);
			session.setAttribute("name", professor.getName());			
			String afm=professorDao.getAFM(username);		

			if(action.equalsIgnoreCase("viewCourses")) {
				request.setAttribute("action","viewCourses");
				ArrayList<Course>list=professorDao.viewGradedCourses(afm);
				session.setAttribute("list",list);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/professor.jsp");
				requestDispatcher.forward(request,response);
				
			}else if (action.equalsIgnoreCase("viewGrades")){
				request.setAttribute("action","viewGrades");
				String courses=request.getParameter("courses");
				ArrayList<Grades>list=professorDao.showgrades(afm,courses);
				session.setAttribute("list",list);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/professor.jsp");
				requestDispatcher.forward(request,response);
				
			}else if(action.equalsIgnoreCase("viewCourses2")) {
				request.setAttribute("action","viewCourses2");
				ArrayList<Course>list=professorDao.viewGradedCourses(afm);
				session.setAttribute("list",list);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/professor.jsp");
				requestDispatcher.forward(request,response);
				
			}else if (action.equalsIgnoreCase("insertGrade")){
				request.setAttribute("action","insertGrade");
				String courses=request.getParameter("courses2");
				ArrayList<Students>list=professorDao.insertgrades(afm,courses);
				session.setAttribute("list",list);
				session.setAttribute("courses",courses);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/professor.jsp");
				requestDispatcher.forward(request,response);
				
			}else if (action.equalsIgnoreCase("Submit")){
				request.setAttribute("action","submit");
				String student=request.getParameter("student");
				String grade=request.getParameter("grade");
				String courses=request.getParameter("courses");
				System.out.print(courses);
				professorDao.submit(student,grade,courses);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/professor.jsp");
				requestDispatcher.forward(request,response);
			}	
		}



		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
		}

}
