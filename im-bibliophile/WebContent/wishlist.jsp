<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.*, com.bibliophile.*" %>   
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>IM BIBLIOPHILE</title>
	<link rel="stylesheet" type="text/css" href="css/wishlist.css" />
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
	</div>
	
	<div class="main-two">
		<h1>Your Wishlist...</h1>
		
		<div >
			<a href="addwishbook.jsp"><button class="add-btn">Add Book</button></a>
		</div>
		
			<table  id="table-design">
			<thead>
				<tr>
					<th>Book Name</th>
					<th>Description</th>
					<th>Progress</th>
					<th>Date</th>
					<th>Action</th>
				</tr>
			</thead>
			<tbody class="tablebody">
				<c:forEach var="tempbook" items="${WISH_BOOKS}">
				
				<c:url var="templink" value="userServlet">
						<c:param name="command" value="LOADWISHBOOK" />
						<c:param name="wishusername" value="${tempbook.wishusername}" />
						<c:param name="bookname" value="${tempbook.bookname}" />
				</c:url>
				<c:url var="deletelink" value="userServlet">
					<c:param name="command" value="DELETEWISHBOOK" />
					<c:param name="wishusername" value="${tempbook.wishusername}" />
					<c:param name="bookname" value="${tempbook.bookname }"/>
				</c:url>
					
						<tr>
							<td>${tempbook.bookname}</td>
							<td>${tempbook.description}</td>
							<td>${tempbook.progress}</td>
							<td>${tempbook.date}</td>
							<td><a href="${templink}" style="text-decoration:none;">Edit</a>
								<a href="${deletelink}" style="text-decoration:none;"
								onclick="if (!(confirm('Are you sure you want to delete this book?'))) return false">
								Delete</a>
							</td>
						</tr>
				</c:forEach>
			</tbody>
			</table>
	</div>

</body>
</html>