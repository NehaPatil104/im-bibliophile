<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IM BIBLIOPHILE</title>
<link rel="stylesheet" type="text/css" href="css/addbookform.css" />
	<link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
</head>
<body>
<%
	
		response.setHeader("Cache-Control" , "no-cache, no-store, must-revalidate");
	
		if(session.getAttribute("username")==null)
		{
			response.sendRedirect("Login.jsp");
		}
	
	%>
<div class="sidenav">
		<h1>${username}</h1>
		<form action="userServlet" method="get" >
			<input type="hidden" name="command" value="LISTBOOKS" />
			<input type="hidden" name="uname" value="${username}" />
			<input type="submit" value="Bookshelf" id="bookshelf-btn">
		</form>
		
		<a href="addbook.jsp">Add Book</a>
		
		<form action="userServlet" method="get">
			<input type="hidden" name="command" value="LISTWISHBOOK" />
			<input type="hidden" name="username" value="${username}"/>
			<input type="submit" value="Wish List" id="bookshelf-btn"/>
		</form>	
		
		<form action="userServlet" method="post">
			<input type="hidden" name="command" value="SIGNOUT" />
			<input type="submit" value="Sign out"  id="bookshelf-btn" >
		</form>	
		
	</div>
	
	<div class="form-deco">
		<h1>Add your book! :)</h1>
		<form action="userServlet" method="get">
			  <input type="hidden" name="command" value="ADDBOOK">
			  <input type="hidden" name="username" value="${username}">
			  <label for="bname">Book Name</label><br>
			  <input type="text" id="bname" name="bookname"><br><br>
			  <label for="lname">Author</label><br>
			  <input type="text" id="lname" name="author"><br><br>
			  <label for="dat">Date</label><br>
			  <input type="date" id="dat" name="date" min="1900-01-01" max="2099-01-01"><br><br>
			  <label for="review">Review</label><br>
			  <textarea id="review" name="review"></textarea><br><br>
			  <input type="submit" value="Save" >
		</form>
	</div>
</body>
</html>