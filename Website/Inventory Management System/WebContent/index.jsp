<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Inventory Management System</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } </script>
<link href="css/style.css" rel='stylesheet' type='text/css' />
<!--webfonts-->
<link href='css/Roboto.css' rel='stylesheet' type='text/css'>
<!--/webfonts-->
</head>
<body>
<!--start-main-->
<h1>Welcome!<span>Please login...</span></h1>
<div class="login-box">
		<form>
			<input type="text" data-bind="value: Username" class="text" value="Username" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Username';}" >
			<input type="password" data-bind="value: Password" value="Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Password';}">
		</form>
		<div class="clear"> </div>
		<div class="btn">
			<input type="submit" value="LOG IN" data-bind="click: login">
		</div>
		<div class="clear"> </div>
</div>
<div style="color: red; font-size: large;" data-bind="visible: alert" class="alert alert-danger">
  <strong>Please try again!</strong> Username and Password do not match.
</div>
<!--//End-login-form-->
<!--start-copyright-->
<div class="copy-right">
	<p>Glg204 <a href="#">Dr. Pascal Fares</a></p> 
</div>
<!--//end-copyright-->		
</body>
 <script type="text/javascript" src="js/jquery-2.2.3.min.js"></script>
 <script type="text/javascript" src="js/knockout-3.4.0.js"></script>
 <script type="text/javascript" src="js/index.js"></script>
</html>