<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>University Grading Management System - Professor's menu</title>
<link rel="icon" type="image/x-icon" href="./images/favicon.ico">
<meta name="description" content="University Grades App">
<script src="./javascript/error.js" type="text/javascript"></script>
<link rel="stylesheet" type="text/css" href="css/profiles.css">
<link rel="stylesheet" type="text/css" href="css/table.css">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<meta name="viewport" content="width=device-width, initial-scale=1">
</head>
<body>
	<% 
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
	response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
	response.setHeader("Expires", "0"); // Proxies.

	if(session.getAttribute("username")==null)
	{
		response.sendRedirect("index.jsp");
	}else{

		if(!(session.getAttribute("role")).equals("professor"))
		{		
			
			session.removeAttribute("username"); 		
			session.invalidate();
			request.setAttribute("msg","Access to page denied, you were logged out for security reasons.");
			RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index.jsp");
			requestDispatcher.forward(request,response);
		}
	}
%>

<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">

		<h1 class="brand brand-name navbar-left" style="font-family:Georgia" > University Grading Management System - Professor's Menu </h1>
        </div>
       
         <%String name="professor"; %><div style="text-align:left;color:white;size:15px;">Welcome, <%= session.getAttribute("username") %> </div>
        
        <div class="collapse navbar-collapse navbar-right" id="myNavbar">

		<ul class="nav navbar-nav">
		<li><a href="professor?action=viewCourses">View Graded Courses</a></li>
		<li><a href="professor?action=viewCourses2">Grade Courses</a></li>
		<li><a href="logout">Log out</a></li>
	</ul>
        </div>
    </div>
</nav>

	<%if (request.getAttribute("action")==("viewCourses")) 
	{
	%>
	<form action="professor?action=viewGrades" method="post">
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Choose Course</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<select name="courses">
					<c:forEach items="${sessionScope.list}" var="Course">
						<option value="<c:out value="${Course.courseName}" />"><c:out value="${Course.courseName}" /></option>
					</c:forEach>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<div style="  margin: 0;position:absolute;left: 50%; -ms-transform: translate(-50%, -50%);transform: translate(-50%, -50%);">
	<button class="btn">Submit</button>
	</div>
	</form>
	
<%} %>

<%if (request.getAttribute("action")==("viewGrades")) 
	{
	%>
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Title</th>
				<th>Student</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${sessionScope.list}" var="Grade">
			<tr>
				<td><c:out value="${Grade.course.courseName}" /></td>
				<td><c:out value="${Grade.student.registrationNumber}" /></td>	
				<td><c:out value="${Grade.grade}" /></td>				
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%} %>
	
		<%if (request.getAttribute("action")==("viewCourses2")) 
	{
	%>
	<form action="professor?action=insertGrade" method="post">
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Choose Course</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<select name="courses2">
					<c:forEach items="${sessionScope.list}" var="Course">
						<option value="<c:out value="${Course.courseName}" />"><c:out value="${Course.courseName}" /></option>
					</c:forEach>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<div style="  margin: 0;position:absolute;left: 50%; -ms-transform: translate(-50%, -50%);transform: translate(-50%, -50%);">
	<button class="btn">Submit</button>
	</div>
	</form>
	
<%} %>
	<%if (request.getAttribute("action")==("insertGrade")) 
	{
	%>
	<form action="professor?action=Submit" method="post">
	<input type="hidden" name="courses" value="${sessionScope.courses}">
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<caption ><c:out value="${sessionScope.courses}" /></caption>
		<thead>
			<tr>
				<th>Registration Number</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<select name="student">
						<c:forEach items="${sessionScope.list}" var="Students">
								<option value="<c:out value="${Students.registrationNumber}" />"><c:out value="${Students.registrationNumber}" /></option>							
						</c:forEach>
					</select>
				</td>
				<td>				
					<select name="grade">
						<option value="0">0</option>
						<option value="1">1</option>
						<option value="2">2</option>
						<option value="3">3</option>
						<option value="4">4</option>
						<option value="5">5</option>
						<option value="6">6</option>
						<option value="7">7</option>
						<option value="8">8</option>
						<option value="9">9</option>
						<option value="10">10</option>
					</select>
				</td>
			</tr>
		</tbody>
	</table>
	<div style="  margin: 0;position:absolute;left: 50%; -ms-transform: translate(-50%, -50%);transform: translate(-50%, -50%);">
	<button class="btn" >Submit</button>
	</div>
	</form>
<%} %>
	
</body>
</html>