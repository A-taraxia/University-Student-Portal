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


@WebServlet("/secretary")
public class SecretaryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	   private SecretaryDao secretaryDao;   
	   private Secretaries secretary;
	   
	    
	    public SecretaryController() {
	        super();
	        secretaryDao = new SecretaryDao();
	        
	    }

		
	    
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String action = request.getParameter("action");
			HttpSession session=request.getSession(false);
			String username=(String) session.getAttribute("username");
			String role=(String) session.getAttribute("role");
			secretary= secretaryDao.GetSecretaryDetails(username);
			session.setAttribute("name", secretary.getName());			
			
			if(action.equalsIgnoreCase("viewCourses")) {
				
				request.setAttribute("action","viewCourses");
				ArrayList<Course>list=secretaryDao.viewCourses();
				session.setAttribute("list",list);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/secretary.jsp");
				requestDispatcher.forward(request,response);
				
			}else if (action.equalsIgnoreCase("setprof")){
				request.setAttribute("action","setprof");
				ArrayList<Course>list=secretaryDao.getcourses();
				session.setAttribute("list",list);
				ArrayList<Professors>list2=secretaryDao.getprof();
				session.setAttribute("list2",list2);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/secretary.jsp");
				requestDispatcher.forward(request,response);
				
			}else if (action.equalsIgnoreCase("Submit")){
				request.setAttribute("action","submit");
				String coursename=request.getParameter("newcourse");
				int prof=Integer.parseInt(request.getParameter("newAFM"));
				SecretaryDao.Submit(coursename,prof);
				RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/secretary.jsp");
				requestDispatcher.forward(request,response);
			}
				
		}



		@Override
		protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			doPost(req, resp);
		}

}
