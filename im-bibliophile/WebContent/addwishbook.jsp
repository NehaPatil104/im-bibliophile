<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IM BIBLIOPHILE</title>
<link rel="stylesheet" type="text/css" href="css/addwishbook.css" />
	<link href="https://fonts.googleapis.com/css2?family=Lora:ital,wght@0,400;0,700;1,500&display=swap" rel="stylesheet">
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
			<input type="submit" value="Wish List" class="signout-btn"/>
		</form>	
		
		<form action="userServlet" method="post">
			<input type="hidden" name="command" value="SIGNOUT" />
			<input type="submit" value="Sign out"  id="bookshelf-btn" >
		</form>		
	</div>
	
	<div class="form-deco">
		<h1>Add your To Do! :)</h1>
		<form action="userServlet" method="get">
			  <input type="hidden" name="command" value="ADDWISHBOOK">
			  <input type="hidden" name="wishusername" value="${username}">
			  <label for="bname">Book Name</label><br>
			  <input type="text" id="bname" name="bookname"><br><br>
			  <label for="descript" class="label-input">Description</label><br>
			  <textarea id="descript" name="description"></textarea><br><br>
			  <label class="label-input">Progress</label><br>
				  <select name="progress" style="width:50%;padding: 12px 20px;margin: 8px 0;border: 2px solid #49C9AF;border-radius: 4px;outline:none;" >
				    <option value="In progress">In progress</option>
				    <option value="Finished">Finished</option>
				    <option value="Not Started">Not yet</option>
				  </select>
				<br>
			  <label for="dat">Date</label><br>
			  <input type="date" id="dat" name="date" min="1900-01-01" max="2099-01-01"><br><br>
			  <input type="submit" value="Save" >
		</form>
	</div>
</body>
</html>