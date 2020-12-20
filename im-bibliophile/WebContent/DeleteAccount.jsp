<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>IM BIBLIOPHILE</title>
<link rel="stylesheet" type="text/css" href="css/login.css">
<link href='https://fonts.googleapis.com/css?family=Lora' rel='stylesheet'>
</head>
<body>

	<div class="login">
		  <h1 class="logo">IM BIBLIOPHILE</h1>
		  <form action="userServlet" method="post">
		 		<input type="hidden" name="command" value="DELETEACCOUNT" />
			  	<input type="text" name="uname"  placeholder="Username" required>
			   	<input type="password" name="upass" id="myInput"  placeholder="Password" maxlength="15" minlength="4" required><br>
			   	<input type="checkbox" onclick="myFunction()"><label style="font-size:15px;">Show Password</label><br>			   	
			    <input type="submit" value="Delete Account" class="delete-btn" onclick="return confirm('Are you sure you want to delete your account?');">
		  </form>
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