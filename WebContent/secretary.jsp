<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>University Grading Management System - Secretary's menu</title>
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

		if(!(session.getAttribute("role")).equals("secretary"))
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
                      
		<h1 class="brand brand-name navbar-left" style="font-family:Georgia" > University Grading Management System - Secretary's Menu </h1>
        </div>
       
         <%String name="secretary"; %><div style="text-align:left;color:white;size:15px;">Welcome, <%= session.getAttribute("username") %> </div>
        
        <div class="collapse navbar-collapse navbar-right" id="myNavbar">

	<ul class="nav navbar-nav">
		<li><a href="secretary?action=viewCourses">View Courses</a></li>
		<li><a href="secretary?action=setprof">Set professor for course</a></li>
		<li><a href="logout">Log out</a></li>
	</ul>
        </div>
    </div>
</nav>

	<%if (request.getAttribute("action")==("viewCourses")) 
	{
	%>
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Title</th>
				<th>Semester</th>
				<th>Professor</th>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${sessionScope.list}" var="Course">
			<tr>
				<td><c:out value="${Course.courseName}" /></td>
				<td><c:out value="${Course.courseSemester}" /></td>
				<td><c:out value="${Course.professor.AFM}" /></td>
			</tr>
		</c:forEach>
		</tbody>
	</table>


	<%} %>
	
	<%if (request.getAttribute("action")==("setprof")) 
	{
	%>
	<form action="secretary?action=Submit" method="post">
	<table id="tabl"
		style="border: 1px solid black; margin-top: 200px; margin-left: auto; margin-right: auto;">
		<thead>
			<tr>
				<th>Give Course Name</th>
				<th>Give Professor's AFM</th>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>
					<select name="newcourse">
					<c:forEach items="${sessionScope.list}" var="Course">
						<option value="<c:out value="${Course.courseName}" />"><c:out value="${Course.courseName}" /></option>
					</c:forEach>
					</select>
					</td>
					<td>				
					<select name="newAFM">
					<c:forEach items="${sessionScope.list2}" var="professor">
						<option value="<c:out value="${professor.AFM}" />"><c:out value="${professor.AFM}" /></option>
					</c:forEach>
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