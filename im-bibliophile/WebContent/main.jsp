<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>IM BIBLIOPHILE</title>
	<link rel="stylesheet" type="text/css" href="css/main.css" />
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
		
		<form action="userServlet" method="get">
			<input type="hidden" name="command" value="LISTBOOKS" />
			<input type="hidden" name="uname" value="${username}" />
			<input type="submit" value="Bookshelf" class="signout-btn" >
		</form>
		
		<a href="addbook.jsp">Add Book</a>
		
		<form action="userServlet" method="get">
			<input type="hidden" name="command" value="LISTWISHBOOK" />
			<input type="hidden" name="username" value="${username}"/>
			<input type="submit" value="Wish List" class="signout-btn"/>
		</form>		
		
		<form action="userServlet" method="post">
			<input type="hidden" name="command" value="SIGNOUT" />
			<input type="submit" value="Sign out" class="signout-btn" >
		</form>
		
		<a href="DeleteAccount.jsp">Delete Account</a>
	</div>
	
	<div class="main">
		<h1>Welcome ${username}</h1>
	</div>

</body>
</html>