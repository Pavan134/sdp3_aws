<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Subscribe</title>
</head>
<body>
<form action="/subscribers" method="post">
<table align="center" border="1">
<tr><td>Enter Your Email-ID:</td><td><input type="email" name="email"></td></tr> 
<tr><td>Enter Your Name:</td><td><input type="text" name="user_name"></td></tr> 
<tr><td>Enter Your Phone Number:</td><td><input type="number" name="phone_number"></td></tr> 
<tr><td><input type="submit" value="Subscribe"></td></tr>
</table>
</form>
</body>
</html>