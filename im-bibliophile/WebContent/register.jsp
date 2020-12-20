<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IM BIBLIOPHILE</title>
<link rel="stylesheet" type="text/css" href="css/register.css">
<link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
</head>
<body>
	
	<div class="login">
		  <h1 class="logo">IM BIBLIOPHILE</h1>
		  <form action="userServlet" method="post" >
		  	<input type="hidden" name="command" value="REGISTER" />
			  	<input type="text" name="uname" placeholder="Username" required>
			   	<input type="password" id="myInput" name="upass" placeholder="Password"><br>
			   	<input type="checkbox" style="font-size:15px;" onclick="myFunction()" id="check-one"><label for="check-one">Show Password</label>
			   <br><input type="checkbox" name="remember_me" id="remember_me" required>
				<label for="remember_me">Agree with terms and policy </label>
			   
			    <input type="submit" value="Continue" class="login-btn" style="margin-top:10px;" required>
		  </form>
		  <div class="login-help">
		  	<p>Already have an account?<br><br> <a href="Login.jsp">Sign in</a></p>
		</div>
	</div>
	<script type="text/javascript">
	function myFunction() {
	  var x = document.getElementById("myInput");
	  if (x.type === "password") {
	    x.type = "text";
	  } else {
	    x.type = "password";
	  }
	}
	</script>
</body>
</html>