package controller;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.SystemDao;



@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SystemDao dao;
       
    
    public RegisterServlet() {
        super();
        dao=new SystemDao();
    }

    
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String salt=dao.getAlphaNumericString(16);
		String username=request.getParameter("newusername");
		String name=request.getParameter("newname");
		String surname=request.getParameter("newsurname");
		String department=request.getParameter("newdepartment");
		String password=request.getParameter("newpassword1")+salt;
		String role=request.getParameter("role");
		System.out.println("Role= "+role);
		int registrationnumber=Integer.parseInt(request.getParameter("registrationnumber"));
		String usernamevalidation=dao.signupusernameCheck(username);
		if (usernamevalidation=="ok") 
		{
			MessageDigest digest;
			try {
					digest = MessageDigest.getInstance("SHA-1");
					byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
					password=dao.bytesToHex(encodedhash);
					password =""+password;
					System.out.println("pass = "+ password);
					HttpSession session = request.getSession();
					dao.signup(registrationnumber, username, name, surname, department, role, password, salt);
					synchronized(session) 
					{
						
						session.setAttribute("username", username);
						session.setAttribute("name",name);
						session.setAttribute("surname", surname);
						session.setAttribute("role", role);

						if (role.equals("secretary")) 
						{
							request.setAttribute("role", "secretary");
							request.setAttribute("username",username);
							RequestDispatcher dispatcher =request.getRequestDispatcher("/secretary.jsp");
							dispatcher.forward(request, response);
						}
						else if (role.equals("student")) 
						{
//							System.out.println("role= "+role);
							request.setAttribute("role", "student");
							request.setAttribute("username",username);
							RequestDispatcher dispatcher =request.getRequestDispatcher("/student.jsp");
							dispatcher.forward(request, response);
						}
						else if (role.equals("professor"))
						{
							request.setAttribute("role", "profesor");
							request.setAttribute("username",username);
							RequestDispatcher dispatcher =request.getRequestDispatcher("/professor.jsp");
							dispatcher.forward(request, response);
						}
					}	
				}
			catch (NoSuchAlgorithmException e) 
					{
						e.printStackTrace();
					}
		}
		else 
		{
			request.setAttribute("message", usernamevalidation);
			request.setAttribute("user", username);
			RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
			view.forward(request, response);
		}	
	}
}
