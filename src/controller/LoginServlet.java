package controller;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;



import dao.SystemDao;
import dao.SecretaryDao;
import dao.StudentDao;
import dao.ProfessorDao;
import controller.*;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemDao dao;
	private StudentDao studentDao;
	private SecretaryDao secretaryDao;
	private ProfessorDao professorDao;
	
	public LoginServlet() {
	        super();
	        dao=new SystemDao();
	        studentDao = new StudentDao();
	        secretaryDao = new SecretaryDao();
	        professorDao = new ProfessorDao();
	    }
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String usernamevalidation=dao.loginusernameCheck(username);
		
		if (usernamevalidation!=username) 
		{
			request.setAttribute("message", usernamevalidation);
			request.setAttribute("username", username);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}else 
		{
			password=password+dao.getSalt(username);
			MessageDigest digest;
			try {
					digest = MessageDigest.getInstance("SHA-1");
					byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
					password=dao.bytesToHex(encodedhash);
					String passwordvalidation=dao.passwordCheck(username, password);
					
					if (passwordvalidation=="You logged in!") 
						
					{
						String role=dao.getRole(username);
						HttpSession session = request.getSession(true);
						synchronized(session) 
						{	
							session.setAttribute("username", username);
							session.setAttribute("role", role);		
														
							if (role.equals("student")) 
							{
								String RegistrationNumber = studentDao.getStudentRegistrationNumber(username);
								session.setAttribute("registrationnumber", RegistrationNumber);
								RequestDispatcher view = request.getRequestDispatcher("/student.jsp");
								view.forward(request, response);
							}
							else if (role.equals("professor")) 
							{ 
								RequestDispatcher view = request.getRequestDispatcher("/professor.jsp");
								view.forward(request, response);
							
							}
							else if (role.equals("secretary"))
							{ 
								RequestDispatcher view = request.getRequestDispatcher("/secretary.jsp");
								view.forward(request, response);
							}
						}
					}									
					else 
					{
						request.setAttribute("message", passwordvalidation);
						RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
						view.forward(request, response);
					}
				} 
				catch (NoSuchAlgorithmException e) 
				{
					e.printStackTrace();
				}
		}	
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(req, resp);
	}
}



