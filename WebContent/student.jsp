<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>University Grading Management System - Students' menu</title>
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

	if(!(session.getAttribute("role")).equals("student"))
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
                    
		<h1 class="brand brand-name navbar-left" style="font-family:Georgia" > University Grading Management System - Student's Menu </h1>
        </div>
       
        <%String name="student"; %><div style="text-align:left;color:white;size:15px;">Welcome, <%= session.getAttribute("username") %> </div>
        
        <div class="collapse navbar-collapse navbar-right" id="myNavbar">

	<ul class="nav navbar-nav">
		<li><a href="student?action=viewStudentDetails">View student's personal data</a></li>
		<li><a href="student?action=viewStudentGrades">Show student's all grades</a></li>
		<li><a href="student?action=viewSemester">Show student's semester's grade</a></li>
		<li><a href="student?action=viewCourses">Choose course to view grade</a></li>
		<li><a href="logout">Log out</a></li>
	</ul>
        </div>
    </div>
</nav>

	<%if (request.getAttribute("action")=="viewStudentDetails") 
	{
	%>
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Name</th>
				<th>Surname</th>
				<th>User name</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td data-column="Name"><%=session.getAttribute("name")%></td>
				<td data-column="Surname"><%=session.getAttribute("surname")%></td>
				<td data-column="Username"><%=session.getAttribute("username")%></td>
			</tr>
		</tbody>
	</table>


	<%} %>
	
	<%if (request.getAttribute("action")==("viewStudentGrades")) 
	{
	%>
	<table id="tabl" style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Course</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${sessionScope.list}" var="Grade">
			<tr>
				<td><c:out value="${Grade.course.courseName}" /></td>
				<td><c:out value="${Grade.grade}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


	<%} %>

	
	<%if (request.getAttribute("action")==("viewSemester")) 
	{
	%>
	<form action="student?action=showgrades" method="post">
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Choose Semester</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<select name="newsemester">
					<c:forEach items="${sessionScope.list}" var="Course">
						<option value="<c:out value="${Course.courseSemester}" />"><c:out value="${Course.courseSemester}" /></option>
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

	<%if (request.getAttribute("action")==("showgrades")) 
	{
	%>
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Course</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${sessionScope.list}" var="Grade">
			<tr>
				<td><c:out value="${Grade.course.courseName}" /></td>
				<td><c:out value="${Grade.grade}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


	<%} %>
	
	<%if (request.getAttribute("action")==("viewCourses")) 
	{
	%>
	<form action="student?action=showonegrade" method="post">
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
					<select name="allcourses">
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

	<%if (request.getAttribute("action")==("showonegrade")) 
	{
	%>
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Course</th>
				<th>Grade</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${sessionScope.list}" var="Grade">
			<tr>
				<td><c:out value="${Grade.course.courseName}" /></td>
				<td><c:out value="${Grade.grade}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<%} %>
	
	
</body>
</html>